-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema test_banking_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test_banking_system` DEFAULT CHARACTER SET utf8 ;
USE `test_banking_system` ;

-- -----------------------------------------------------
-- Table `test_banking_system`.`accounts_numbers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`accounts_numbers` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`accounts_numbers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` BIGINT(19) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 74
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_banking_system`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`users` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role` ENUM('ADMIN', 'USER') NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_banking_system`.`credit_accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`credit_accounts` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`credit_accounts` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` BIGINT(19) NOT NULL,
  `balance` DECIMAL(12,2) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `credit_limit` DECIMAL(12,2) NOT NULL,
  `indebtedness` DECIMAL(12,2) NULL DEFAULT NULL,
  `accrued_interest` DECIMAL(12,2) NULL DEFAULT NULL,
  `credit_rate` DECIMAL(4,2) NOT NULL,
  `status` ENUM('OPENED', 'CLOSED', 'UNDER_CONSIDERATION') NOT NULL,
  `expiration_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_banking_system`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 54
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_banking_system`.`credit_limits`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`credit_limits` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`credit_limits` (
  `limit` DECIMAL(12,2) NOT NULL,
  `rate` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`limit`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_banking_system`.`deposit_accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`deposit_accounts` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`deposit_accounts` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` BIGINT(19) NOT NULL,
  `balance` DECIMAL(12,2) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `amount` DECIMAL(12,2) NOT NULL,
  `rate` DECIMAL(4,2) NOT NULL,
  `term` INT(11) NOT NULL,
  `status` ENUM('OPENED', 'CLOSED', 'UNDER_CONSIDERATION') NOT NULL,
  `expiration_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC),
  INDEX `userId_idx` (`user_id` ASC),
  CONSTRAINT `userId`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_banking_system`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_banking_system`.`deposit_catalog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`deposit_catalog` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`deposit_catalog` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `rate` DECIMAL(4,2) NOT NULL,
  `term` INT(11) NOT NULL,
  `available` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_banking_system`.`payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`payments` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`payments` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `sender` VARCHAR(100) NOT NULL,
  `sender_account_number` BIGINT(19) NOT NULL,
  `receiver` VARCHAR(100) NOT NULL,
  `receiver_account_number` BIGINT(19) NOT NULL,
  `amount` DECIMAL(12,2) NOT NULL,
  `purpose` TEXT NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_banking_system`.`transactions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`transactions` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`transactions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `sender_account_number` BIGINT(19) NOT NULL,
  `receiver_account_number` BIGINT(19) NOT NULL,
  `amount` DECIMAL(12,2) NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_banking_system`.`deposit_replenishments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`deposit_replenishments` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`deposit_replenishments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sender_account_number` BIGINT(19) NOT NULL,
  `receiver_account_number` BIGINT(19) NOT NULL,
  `amount` DECIMAL(12,2) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `test_banking_system`.`users` (`role`, `username`, `password`, `email`, `first_name`, `last_name`) VALUES ('ADMIN', 'romaniukv', 'YnhKhE8/dy/YGgtqezqJGapWwOeoMHVO', 'romaniukv255@gmail.com', 'Vika', 'Romaniuk');

