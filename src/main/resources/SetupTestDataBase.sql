-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema test_banking_system
-- -----------------------------------------------------

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
  AUTO_INCREMENT = 90
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
  AUTO_INCREMENT = 26
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
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  AUTO_INCREMENT = 28
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
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
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
  AUTO_INCREMENT = 19
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test_banking_system`.`deposit_replenishments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_banking_system`.`deposit_replenishments` ;

CREATE TABLE IF NOT EXISTS `test_banking_system`.`deposit_replenishments` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `sender_account_number` BIGINT(19) NOT NULL,
  `receiver_account_number` BIGINT(19) NOT NULL,
  `amount` DECIMAL(12,2) NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `receiver_account_number_idx` (`receiver_account_number` ASC),
  CONSTRAINT `receiver_account_number`
  FOREIGN KEY (`receiver_account_number`)
  REFERENCES `test_banking_system`.`deposit_accounts` (`number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
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
  PRIMARY KEY (`id`),
  INDEX `sender_idx` (`sender_account_number` ASC),
  CONSTRAINT `sender`
  FOREIGN KEY (`sender_account_number`)
  REFERENCES `test_banking_system`.`credit_accounts` (`number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
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
  AUTO_INCREMENT = 4
  DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `test_banking_system`.`users` (`id`, `role`, `username`, `password`, `email`, `first_name`, `last_name`) VALUES ('1','ADMIN', 'romaniukv', 'YnhKhE8/dy/YGgtqezqJGapWwOeoMHVO', 'romaniukv255@gmail.com', 'Vika', 'Romaniuk');
INSERT INTO `test_banking_system`.`users` (`id`, `role`, `username`, `password`, `email`, `first_name`, `last_name`) VALUES ('2','USER', 'danchek', 'YnhKhE8/dy/OeoMHVO', 'example@gmail.com', 'Danil', 'Chekina');


INSERT INTO `test_banking_system`.`credit_accounts` (`id`, `number`, `balance`, `user_id`, `credit_limit`, `indebtedness`, `accrued_interest`, `credit_rate`, `status`, `expiration_date`) VALUES ('1', '3456789086453456', '500', '1', '10000', '345', '0', '3.5', 'OPENED', '21.05.2020');
INSERT INTO `test_banking_system`.`credit_accounts` (`id`, `number`, `balance`, `user_id`, `credit_limit`, `indebtedness`, `accrued_interest`, `credit_rate`, `status`, `expiration_date`) VALUES ('2', '3456789086456346', '600', '1', '1000', '400', '0', '2.5', 'UNDER_CONSIDERATION', '21.05.2020');
INSERT INTO `test_banking_system`.`credit_accounts` (`id`, `number`, `balance`, `user_id`, `credit_limit`, `indebtedness`, `accrued_interest`, `credit_rate`, `status`, `expiration_date`) VALUES ('3', '3456789086498790', '700', '1', '2500', '800', '0', '3', 'UNDER_CONSIDERATION', '21.05.2020');
INSERT INTO `test_banking_system`.`credit_accounts` (`id`, `number`, `balance`, `user_id`, `credit_limit`, `indebtedness`, `accrued_interest`, `credit_rate`, `status`, `expiration_date`) VALUES ('4', '3456789086473648', '800', '1', '2500', '800', '0', '3', 'UNDER_CONSIDERATION', '21.05.2020');
INSERT INTO `test_banking_system`.`credit_accounts` (`id`, `number`, `balance`, `user_id`, `credit_limit`, `indebtedness`, `accrued_interest`, `credit_rate`, `status`, `expiration_date`) VALUES ('5', '3456789234453456', '500', '2', '10000', '345', '0', '1.5', 'OPENED', '21.05.2018');

INSERT INTO `test_banking_system`.`deposit_accounts` (`id`, `number`, `balance`, `user_id`, `amount`, `rate`, `term`, `status`, `expiration_date`) VALUES ('1', '3647586974634567', '500', '1', '478', '10', '12', 'OPENED', '20.05.2020');
INSERT INTO `test_banking_system`.`deposit_accounts` (`id`, `number`, `balance`, `user_id`, `amount`, `rate`, `term`, `status`, `expiration_date`) VALUES ('2', '3647586974636543', '600', '1', '500', '12', '24', 'OPENED', '20.05.2020');
INSERT INTO `test_banking_system`.`deposit_accounts` (`id`, `number`, `balance`, `user_id`, `amount`, `rate`, `term`, `status`, `expiration_date`) VALUES ('3', '3647586974630989', '900', '1', '900', '13', '18', 'OPENED', '20.05.2020');
INSERT INTO `test_banking_system`.`deposit_accounts` (`id`, `number`, `balance`, `user_id`, `amount`, `rate`, `term`, `status`, `expiration_date`) VALUES ('4', '3657596974630989', '900', '1', '900', '13', '18', 'OPENED', '20.05.2020');

INSERT INTO `test_banking_system`.`deposit_replenishments` (`id`, `sender_account_number`, `receiver_account_number`, `amount`, `date`) VALUES ('1', '3456', '3647586974634567', '500', '20.05.2020');
INSERT INTO `test_banking_system`.`deposit_replenishments` (`id`, `sender_account_number`, `receiver_account_number`, `amount`, `date`) VALUES ('2', '6456', '3647586974634567', '500', '20.05.2020');
INSERT INTO `test_banking_system`.`deposit_replenishments` (`id`, `sender_account_number`, `receiver_account_number`, `amount`, `date`) VALUES ('3', '5465', '3647586974634567', '500', '20.05.2020');



