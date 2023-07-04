
-- P2. Tính số lượng đề tài mà một giáo viên đang thực hiện. 
delimiter //
create procedure sp_soluongdetai1(
	in pMaGV varchar(3),
    out pSLDT varchar(3)
)
begin
	 set pSLDT = (select count(distinct tgdt.madt) from thamgiadt tgdt where tgdt.magv = pMaGV);
end;
// delimiter
-- P6. Thực hiện thêm một phân công cho giáo viên thực hiện một công việc của đề tài:
	-- o Kiểm tra thông tin đầu vào hợp lệ: giáo viên phải tồn tại, công việc phải tồn tại, thời gian tham gia phải >0
	-- o Giáo viên chỉ tham gia đề tài cùng bộ môn với giáo viên làm chủ nhiệm đề tài đó..\
    delimiter //
-- kiem tra gv ton tai
create DEFINER=`root`@`localhost` procedure sp_kiemtraGVtontai(
	in pMaGV varchar(3),
    inout existsGV boolean
)
begin
	set existsGV = exists (select tgdt.magv from thamgiadt tgdt where tgdt.magv = pMaGV);
end;
// delimiter
delimiter //
-- kiem tra cong viec ton tai
create DEFINER=`root`@`localhost` procedure sp_kiemtraCVtontai(
	in pMaDT varchar(3),
    in pMaCV int,
    inout existsCV boolean
)
begin
	set existsCV = exists (select cv.madt from congviec cv where cv.madt = pMaDT) and exists (select cv.sott from congviec cv where cv.sott = pMaCV);
end;
// delimiter
-- thoi gian tham gia lon hon 0
delimiter //
create DEFINER=`root`@`localhost` procedure sp_kiemtraTime(
	in pNgayBD date,
    in pMaDT varchar(3),
    in pMaCV int,
    inout checkTime boolean
)
begin
	set @NgayKT = (select cv.ngaykt from congviec cv where cv.madt = pMaDT and cv.sott = pMaCV);
	set @songay = datediff(@NgayKT, pNgayBD);
    if(@songay > 0) then set checkTime = true;
    else set checkTime = false;
    end if;
end;
// delimiter
-- kiem tra giao vien cung bo mon
delimiter //
create DEFINER=`root`@`localhost` procedure sp_kiemtraGVcungBM(
	in pMaGV varchar(3),
    inout checkBM boolean
)
begin
	set @pMaBM = (select gv.mabm from giaovien gv where gv.magv = pMaGV);
    set checkBM = exists ( select cn.maBM from (select distinct gv.magv as maCN, gv.mabm as maBM from giaovien gv join detai dt on dt.gvcndt = gv.magv) as cn
    where cn.maCN != pMaGV and cn.maBM = @pMaBM);
end;
// delimiter
delimiter //
create DEFINER=`root`@`localhost` procedure sp_themphancong(
	in pMaGV varchar(3),
    in pNgayBD date,
    in pMaDT varchar(3),
    in pMaCV int,
    out pMessage varchar(50)
)
begin
	declare flag boolean default true;
    set @check1 = false;
    call quanlydetai.sp_kiemtraGVtontai(pMaGV, @check1);
    set @check2 = false;
    call quanlydetai.sp_kiemtraCVtontai(pMaDT, pMaCV, @check2);
    set @check3 = false;
    call quanlydetai.sp_kiemtraTime(pNgayBD, pMaDT, pMaCV, @check3);
    set @check4 = false;
    call quanlydetai.sp_kiemtraGVcungBM(pMaGV, @check4);
    set flag = @check1 and @chekc2 and @check3 and @check4;
    if (flag = false) then 
		set pMessage = 'loi roi.....';
	elseif (exists (select magv, madt, stt from thamgiadt where magv = pMaGV and madt = pMaDT and stt = pMaCV)) then set pMessage = 'bi trung troi'; select pMessage;
	else insert into thamgiadt (MAGV, MADT, STT) values (pMaGV, pMaDT, pMaCV);
		set pMessage = 'them thanh cong';
    end if;
end;

-- P7. Thực hiện xoá một giáo viên theo mã. Nếu giáo viên có thông tin liên quan (Có thân nhân, có làm đề tài, …) thì báo lỗi.
-- kiem tra co nhan than
delimiter //
create DEFINER=`root`@`localhost` procedure sp_ktThanNhan(
	in pMaGV varchar(3),
    inout checkNT boolean
)
begin
	set checkNT = exists ( select distinct nt.magv from nguoithan nt where nt.magv = pMaGV);
end;
// delimiter
delimiter //
create  DEFINER=`root`@`localhost` procedure sp_ktDeTai(
	in pMaGV varchar(3),
    inout checkDT boolean
)
begin
	set checkDT = exists ( select distinct tgdt.magv from thamgiadt tgdt where tgdt.magv = pMaGV);
end;
// delimiter
delimiter //
create  DEFINER=`root`@`localhost` procedure sp_xoaGV(
	in pMaGV varchar(3),
    out pMessage varchar(50)
)
begin
	set @flag = true;
    set @checkTN = false;
    set @checkDT = false;
    call quanlydetai.sp_ktThanNhan(pMaGV, @checkTN);
    call quanlydetai.sp_ktDeTai(pMaGV, @checkDT);
    set @flag = @checkTN or @checkDT;
    if (@flag = true ) then set pMessage = 'loi ko xoa duoc....';
    else delete from giaovien gv where gv.magv = pMaGV;
		set  pMessage = 'xoa thanh cong';
    end if;
end;