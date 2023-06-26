CREATE TABLE `student_manager`.`class` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `student_manager`.`teacher` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(45) NULL,
  `age` INT NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));