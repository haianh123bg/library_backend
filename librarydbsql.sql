-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema library_manager_system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library_manager_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library_manager_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `library_manager_system` ;

CREATE USER 'library_admin'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON library_manager_system.* TO 'library_admin'@'localhost';
-- -----------------------------------------------------
-- Table `library_manager_system`.`tblauthor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_manager_system`.`tblauthor` (
  `author_id` INT NOT NULL AUTO_INCREMENT,
  `author_name` VARCHAR(255) NULL DEFAULT NULL,
  `author_date_of_birth` DATE NULL DEFAULT NULL,
  `author_description` TEXT NULL DEFAULT NULL,
  `author_image` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`author_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library_manager_system`.`tblcategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_manager_system`.`tblcategory` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NULL DEFAULT NULL,
  `category_total_book` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library_manager_system`.`tblpublisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_manager_system`.`tblpublisher` (
  `publisher_id` INT NOT NULL AUTO_INCREMENT,
  `publisher_name` VARCHAR(255) NULL DEFAULT NULL,
  `publisher_phone_number` VARCHAR(20) NULL DEFAULT NULL,
  `publisher_address` TEXT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`publisher_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library_manager_system`.`tblbook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_manager_system`.`tblbook` (
  `book_id` INT NOT NULL AUTO_INCREMENT,
  `book_name` VARCHAR(255) NULL DEFAULT NULL,
  `book_publishing_year` YEAR NULL DEFAULT NULL,
  `book_description` TEXT NULL DEFAULT NULL,
  `book_price` DECIMAL(10,2) NULL DEFAULT NULL,
  `book_inventory_number` INT NULL DEFAULT NULL,
  `book_page_number` INT NULL DEFAULT NULL,
  `book_status` VARCHAR(50) NULL DEFAULT NULL,
  `book_language` VARCHAR(50) NULL DEFAULT NULL,
  `author_id` INT NULL DEFAULT NULL,
  `category_id` INT NULL DEFAULT NULL,
  `publisher_id` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `book_weight` FLOAT NULL,
  `book_size` VARCHAR(45) NULL,
  PRIMARY KEY (`book_id`),
  INDEX `author_id` (`author_id` ASC) VISIBLE,
  INDEX `category_id` (`category_id` ASC) VISIBLE,
  INDEX `publisher_id` (`publisher_id` ASC) VISIBLE,
  CONSTRAINT `tblbook_ibfk_1`
    FOREIGN KEY (`author_id`)
    REFERENCES `library_manager_system`.`tblauthor` (`author_id`),
  CONSTRAINT `tblbook_ibfk_2`
    FOREIGN KEY (`category_id`)
    REFERENCES `library_manager_system`.`tblcategory` (`category_id`),
  CONSTRAINT `tblbook_ibfk_3`
    FOREIGN KEY (`publisher_id`)
    REFERENCES `library_manager_system`.`tblpublisher` (`publisher_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library_manager_system`.`tbluser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_manager_system`.`tbluser` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(255) NULL DEFAULT NULL,
  `user_image` VARCHAR(255) NULL DEFAULT NULL,
  `user_phone_number` VARCHAR(20) NULL DEFAULT NULL,
  `user_address` TEXT NULL DEFAULT NULL,
  `user_account_name` VARCHAR(255) NULL DEFAULT NULL,
  `user_account_password` VARCHAR(255) NULL DEFAULT NULL,
  `user_role` VARCHAR(50) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_money` DECIMAL NULL DEFAULT 0,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library_manager_system`.`tblborrowing_form`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_manager_system`.`tblborrowing_form` (
  `borrowing_form_id` INT NOT NULL AUTO_INCREMENT,
  `borrowing_form_date` DATE NULL DEFAULT NULL,
  `borrowing_form_type` VARCHAR(50) NULL DEFAULT NULL,
  `borrowing_form_deposit` DECIMAL(10,2) NULL DEFAULT NULL,
  `borrowing_form_due_date` DATE NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`borrowing_form_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `book_id_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `tblborrowing_form_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `library_manager_system`.`tbluser` (`user_id`),
  CONSTRAINT `tblborrowing_form_ibfk_2`
    FOREIGN KEY (`book_id`)
    REFERENCES `library_manager_system`.`tblbook` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library_manager_system`.`tblreturn_slip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_manager_system`.`tblreturn_slip` (
  `return_slip_id` INT NOT NULL AUTO_INCREMENT,
  `return_slip_date` DATE NULL DEFAULT NULL,
  `return_slip_refund` DECIMAL(10,2) NULL DEFAULT NULL,
  `return_slip_late_fee` DECIMAL(10,2) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`return_slip_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `book_id_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `tblreturn_slip_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `library_manager_system`.`tbluser` (`user_id`),
  CONSTRAINT `tblreturn_slip_ibfk_2`
    FOREIGN KEY (`book_id`)
    REFERENCES `library_manager_system`.`tblbook` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `library_manager_system`.`tblimage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_manager_system`.`tblimage` (
  `image_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `image_link` VARCHAR(125) NOT NULL,
  `image_avatar` TINYINT NULL,
  PRIMARY KEY (`image_id`),
  CONSTRAINT `tblimage_ibfk_1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library_manager_system`.`tblbook` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_manager_system`.`tblcoupon_code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_manager_system`.`tblcoupon_code` (
  `coupon_code_id` INT NOT NULL,
  `book_id` INT NULL,
  `coupon_code_count` INT NULL,
  `coupon_code_sale_price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`coupon_code_id`),
  INDEX `book_id_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `tblcoupon_code_ibfk_1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library_manager_system`.`tblbook` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
