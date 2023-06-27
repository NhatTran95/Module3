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