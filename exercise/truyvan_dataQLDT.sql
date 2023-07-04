use quanlydetai;
-- Câu 1: Cho biết số lượng giáo viên của toàn trường
select count(MAGV) as SLGV
from giaovien;
-- Câu 2: Cho biết số lượng giáo viên của bộ môn HTTT
select MABM, count(*) as SLGV
from giaovien
where MABM = 'HTTT';
-- Câu 3: Tính số lượng giáo viên có người quản lý về mặt chuyên môn
select count(GVQlcm) as SLGV
from giaovien
where gvqlcm is not null;
-- Câu 4: Tính số lượng giáo viên làm nhiệm vụ quản lý chuyên môn cho giáo viên khác mà thuộc bộ môn HTTT
select count(distinct GVQlcm) as SLGV
from giaovien
where gvqlcm != 'null' and MABM = 'HTTT';
-- Câu 5:  Tính lương trung bình của giáo viên bộ môn Hệ thống thông tin
select avg(Luong) as LuongTB
from giaovien
where mabm = 'HTTT';
-- Câu 6: Với mỗi bộ môn cho biết bộ môn (MAMB) và số lượng giáo viên của bộ môn đó.
select MABM, count(*) as SLGV
from giaovien
group by mabm;
-- c2:
select *, (select count(*) from giaovien where mabm = bm.mabm) as SLGV
from bomon bm;

-- Câu 7: Với mỗi giáo viên, cho biết MAGV và số lượng công việc mà giáo viên đó có tham gia
select gv.MAGV, count(*) SLCV
from giaovien gv join thamgiadt tdt on tdt.magv = gv.magv
join congviec cv on cv.madt = tdt.madt
group by gv.magv;
-- Câu 8: Với mỗi giáo viên, cho biết MAGV và số lượng đề tài mà giáo viên đó có tham gia.
select gv.magv, count(distinct tdt.madt) SLDT
from giaovien gv join thamgiadt tdt on tdt.magv = gv.magv
group by gv.magv;
SELECT MAGV, COUNT( DISTINCT MADT ) AS SLDT
FROM THAMGIADT
GROUP BY MAGV;
-- Câu 9:  Với mỗi bộ môn, cho biết số đề tài mà giáo viên của bộ môn đó chủ trì
SELECT GV.MABM, COUNT(*)
FROM DETAI DT, GIAOVIEN GV
WHERE DT.GVCNDT = GV.MAGV
GROUP BY GV.MABM ;
-- c2:
SELECT GV.MABM, COUNT(*)
FROM GIAOVIEN GV join detai dt on dt.gvcndt = gv.magv
GROUP BY GV.MABM ;
-- c3:
select *, (select count(*) from giaovien gv, detai dt where gv.mabm = bm.mabm and gv.magv = dt.gvcndt) as SLDT
from bomon bm;
-- Câu 10: Với mỗn bộ môn cho biết tên bộ môn và số lượng giáo viên của bộ môn đó.
SELECT BM.TENBM, COUNT(*)
FROM GIAOVIEN GV, BOMON BM
WHERE GV.MABM=BM.MABM
GROUP BY BM.MABM;
-- c2:
select *, (select count(*) from giaovien where mabm = bm.mabm) as SLGV
from bomon bm;
-- tìm xem mỗi bộ môn có bao nhiêu giáo viên nam nếu không có hiển thị là 0
SELECT *, (SELECT count(*)
			FROM giaovien where PHAI = 'NAM' and MABM = bm.MABM) as sl
FROM bomon bm;
-- Cho biết những giáo viên có lương lớn hơn lương trung bình của bộ môn mà giáo
-- viên đó làm việc
SELECT *, (SELECT AVG(GV2.LUONG) FROM GIAOVIEN GV2 WHERE GV2.MABM = GV.MABM) AS LTB
FROM giaovien GV
WHERE GV.LUONG > (SELECT AVG(GV2.LUONG) FROM GIAOVIEN GV2 WHERE GV2.MABM = GV.MABM);
-- CHO BIẾT THÔNG TIN CÁC TRƯỞNG BM CÓ THAM GIA ĐỀ TÀi
SELECT DISTINCT BM.TRUONGBM, GV.HOTEN, BM.TENBM, DT.TENDT
FROM BOMON BM JOIN THAMGIADT TGDT ON BM.TRUONGBM = TGDT.MAGV
	JOIN GIAOVIEN GV ON GV.MAGV = BM.TRUONGBM
    join detai dt on tgdt.madt = dt.madt;
-- P2. Tính số lượng đề tài mà một giáo viên đang thực hiện. 
select magv, count(distinct madt) as SLDT 
from thamgiadt
group by magv;
delimiter //
create procedure sp_soluongdetai1(
	in pMaGV varchar(3),
    out pSLDT varchar(3)
)
begin
	 set pSLDT = (select count(distinct tgdt.madt) from thamgiadt tgdt where tgdt.magv = pMaGV);
end;
// delimiter
delimiter //
create procedure sp_soluongdetai2(
	in pMaGV varchar(3)
)
begin
	set @flag = exists (select * from thamgiadt tgdt where tgdt.magv = pMaGV );
	if (pMaGV = '') then select gv.magv, gv.hoten, count(distinct tgdt.madt) as SLDT from giaovien gv join thamgiadt tgdt on tgdt.magv = gv.magv group by gv.magv;
	elseif (not exists (select * from giaovien gv where gv.magv = pMaGV )) then set  @pMessage = 'giao vien khong ton tai'; select @pMessage;
	elseif (@flag = false) then set @pMessage = 'giao vien khong tham gia dt';
    select gv.magv, gv.hoten, @pMessage as SLDT from giaovien gv where gv.magv = pMaGV;
    else
    select gv.magv, gv.hoten, count(distinct tgdt.madt) as SLDT from thamgiadt tgdt, giaovien gv where tgdt.magv = pMaGV and tgdt.magv = gv.magv group by gv.magv;
    end if;
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
