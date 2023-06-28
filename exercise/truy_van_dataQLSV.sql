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
