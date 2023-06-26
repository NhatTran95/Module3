CREATE SCHEMA `quanlybanhang` ;
CREATE TABLE `quanlybanhang`.`customer` (
  `cID` BIGINT NOT NULL,
  `cName` VARCHAR(45) NOT NULL,
  `cAge` INT NOT NULL,
  PRIMARY KEY (`cID`));
  CREATE TABLE `quanlybanhang`.`order` (
  `oID` BIGINT NOT NULL AUTO_INCREMENT,
  `cID` BIGINT NOT NULL,
  `oDate` DATETIME NOT NULL,
  `oTotalPrice` DOUBLE NOT NULL,
  PRIMARY KEY (`oID`),
  INDEX `fk_cID_idx` (`cID` ASC) VISIBLE,
  CONSTRAINT `fk_cID`
    FOREIGN KEY (`cID`)
    REFERENCES `quanlybanhang`.`customer` (`cID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    CREATE TABLE `quanlybanhang`.`product` (
  `pID` BIGINT NOT NULL,
  `pName` VARCHAR(45) NOT NULL,
  `pPrice` DOUBLE NOT NULL,
  PRIMARY KEY (`pID`));
  CREATE TABLE `quanlybanhang`.`orderdetail` (
  `oID` BIGINT NOT NULL,
  `pID` BIGINT NOT NULL,
  `odQTY` INT NOT NULL,
  PRIMARY KEY (`oID`, `pID`),
  INDEX `fk_pID_idx` (`pID` ASC) VISIBLE,
  CONSTRAINT `fk_oID`
    FOREIGN KEY (`oID`)
    REFERENCES `quanlybanhang`.`order` (`oID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pID`
    FOREIGN KEY (`pID`)
    REFERENCES `quanlybanhang`.`product` (`pID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);