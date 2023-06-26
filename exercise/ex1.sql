CREATE TABLE `shopping`.`products` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(45) NULL,
  `price` DOUBLE NULL,
  `description` VARCHAR(255) NULL,
  `deleted` TINYINT NULL,
  `category_id` BIGINT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `shopping`.`categories` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(45) NULL,
  `deleted` TINYINT NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `shopping`.`orders` (
  `id` BIGINT NOT NULL,
  `total` DOUBLE NULL,
  `created_at` DATETIME NULL,
  `fullname` VARCHAR(255) NULL,
  `phone` CHAR(10) NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `shopping`.`orderitems` (
  `id` BIGINT NOT NULL,
  `id_order` BIGINT NULL,
  `id_product` BIGINT NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`id`));
  ALTER TABLE `shopping`.`products` 
ADD CONSTRAINT `fk_category_id`
  FOREIGN KEY (`category_id`)
  REFERENCES `shopping`.`categories` (`id`);
  ALTER TABLE `shopping`.`orderitems` 
ADD CONSTRAINT `fk_order_id`
  FOREIGN KEY (`id_order`)
  REFERENCES `shopping`.`orders` (`id`),
ADD CONSTRAINT `fk_product_id`
  FOREIGN KEY (`id_product`)
  REFERENCES `shopping`.`products` (`id`);
