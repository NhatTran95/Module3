USE quanlysinhvien;
SELECT * FROM student;
SELECT * FROM student
where Status = true;
SELECT * FROM subject
where Credit < 10;
SELECT StudentID, StudentName, ClassName
from student
join class on student.ClassID = class.ClassID
where ClassName = 'A1';
SELECT S.StudentID, S.StudentName, Sub.SubName, M.Mark
from student S
join mark M on S.StudentID = M.StudentID 
join subject Sub on M.SubID = Sub.SubID
where Sub.SubName = 'CF';
select * from student
where StudentName like 'h%';
select * from class
where month(StartDate) = 12;
select * from subject
where Credit between 3 and 5;
UPDATE `student` SET `ClassID` = '2' WHERE (`StudentID` = '1');
select s.StudentName, sub.Subname, m.Mark
from student s
join mark as m on m.StudentID = s.StudentID
join subject as sub on sub.SubID = m.SubID
order by m.Mark desc, s.StudentName asc;
-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
select *
from subject 
where credit >= all ( select credit from subject );
-- c2:
select max(credit)
from subject;
-- c3:
select subname, credit
from subject
where credit = (select max(s.credit) from subject s);
-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
select distinct sub.subid, sub.subname, m.mark 
from subject sub, mark m
where sub.subid = m.subid and m.mark >= all (select mark from mark);
-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, 
-- xếp hạng theo thứ tự điểm giảm dần
select s.studentid, s.studentname, s.classid, avg(m.mark) as DTB
from student s join mark m on m.studentId = s.studentId
group by m.studentid
order by DTB desc;
-- Sử dụng hàm count để hiển thị số lượng sinh viên ở từng nơi
select address, count(*) as SLSV
from student
group by address;
-- Tính điểm trung bình các môn học của mỗi học viên bằng cách sử dụng hàm AVG
 select s.studentid, s.studentname, s.classid, avg(m.mark) as DTB
from student s join mark m on m.studentId = s.studentId
group by m.studentid;
-- Hiển thị những bạn học viên co điểm trung bình các môn học lớn hơn 10
select s.studentid, s.studentname, s.classid, avg(m.mark) as DTB
from student s join mark m on m.studentId = s.studentId
group by m.studentid
having DTB > 10;
-- Hiển thị thông tin các học viên có điểm trung bình lớn nhất.
select s.studentid, s.studentname, s.classid, avg(m.mark) as DTB
from student s join mark m on m.studentId = s.studentId
group by m.studentid
having DTB >= all (select avg(mark) from mark group by mark.studentid);

