CREATE SCHEMA `quanlysinhvien` ;
CREATE TABLE `quanlysinhvien`.`class` (
  `ClassID` INT NOT NULL AUTO_INCREMENT,
  `ClassName` VARCHAR(60) NOT NULL,
  `StartDate` DATETIME NOT NULL,
  `Status` BIT NULL,
  PRIMARY KEY (`ClassID`));
  CREATE TABLE `quanlysinhvien`.`student` (
  `StudentID` INT NOT NULL AUTO_INCREMENT,
  `StudentName` VARCHAR(30) NOT NULL,
  `Address` VARCHAR(50) NULL,
  `Phone` VARCHAR(20) NULL,
  `Status` BIT NULL,
  `ClassID` INT NOT NULL,
  PRIMARY KEY (`StudentID`));
  CREATE TABLE `quanlysinhvien`.`subject` (
  `SubID` INT NOT NULL AUTO_INCREMENT,
  `SubName` VARCHAR(30) NOT NULL,
  `Credit` TINYINT NOT NULL DEFAULT 1 check (Credit >= 1),
  `Status` BIT NULL DEFAULT 1,
  PRIMARY KEY (`SubID`));
CREATE TABLE `quanlysinhvien`.`mark` (
  `MarkID` INT NOT NULL AUTO_INCREMENT,
  `SubID` INT NOT NULL,
  `StudentID` INT NOT NULL,
  `Mark` FLOAT NULL DEFAULT 0 CHECK (Mark BETWEEN 0 AND 100),
  `ExamTimes` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`MarkID`),
  UNIQUE INDEX `SubID_UNIQUE` (`SubID` ASC) VISIBLE,
  UNIQUE INDEX `StudentID_UNIQUE` (`StudentID` ASC) VISIBLE);
  ALTER TABLE `quanlysinhvien`.`student` 
ADD CONSTRAINT `fk_classID`
  FOREIGN KEY (`ClassID`)
  REFERENCES `quanlysinhvien`.`class` (`ClassID`);
  ALTER TABLE `quanlysinhvien`.`mark` 
ADD CONSTRAINT `fk_subID`
  FOREIGN KEY (`SubID`)
  REFERENCES `quanlysinhvien`.`subject` (`SubID`),
ADD CONSTRAINT `fk_studentID`
  FOREIGN KEY (`StudentID`)
  REFERENCES `quanlysinhvien`.`student` (`StudentID`);
  