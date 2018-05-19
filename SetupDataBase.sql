-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Schema banking_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `banking_system` DEFAULT CHARACTER SET utf8 ;
USE `banking_system` ;

-- -----------------------------------------------------
-- Table `banking_system`.`accounts_numbers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banking_system`.`accounts_numbers` ;

CREATE TABLE IF NOT EXISTS `banking_system`.`accounts_numbers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number` BIGINT(19) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 71
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `banking_system`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banking_system`.`users` ;

CREATE TABLE IF NOT EXISTS `banking_system`.`users` (
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
  AUTO_INCREMENT = 7
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `banking_system`.`credit_accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banking_system`.`credit_accounts` ;

CREATE TABLE IF NOT EXISTS `banking_system`.`credit_accounts` (
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
  REFERENCES `banking_system`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 52
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `banking_system`.`credit_limits`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banking_system`.`credit_limits` ;

CREATE TABLE IF NOT EXISTS `banking_system`.`credit_limits` (
  `limit` DECIMAL(12,2) NOT NULL,
  `rate` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`limit`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `banking_system`.`deposit_accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banking_system`.`deposit_accounts` ;

CREATE TABLE IF NOT EXISTS `banking_system`.`deposit_accounts` (
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
  REFERENCES `banking_system`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `banking_system`.`deposit_catalog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banking_system`.`deposit_catalog` ;

CREATE TABLE IF NOT EXISTS `banking_system`.`deposit_catalog` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `rate` DECIMAL(4,2) NOT NULL,
  `term` INT(11) NOT NULL,
  `available` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `banking_system`.`transactions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banking_system`.`transactions` ;

CREATE TABLE IF NOT EXISTS `banking_system`.`transactions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `from_number` BIGINT(19) NOT NULL,
  `to_number` BIGINT(19) NOT NULL,
  `amount` DECIMAL(12,2) NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `banking_system`.`payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banking_system`.`payments` ;

CREATE TABLE IF NOT EXISTS `banking_system`.`payments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sender` VARCHAR(100) NOT NULL,
  `sender_account_number` BIGINT(19) NOT NULL,
  `receiver` VARCHAR(100) NOT NULL,
  `receiver_account_number` BIGINT(19) NOT NULL,
  `amount` DECIMAL(12,2) NOT NULL,
  `purpose` TEXT NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `banking_system`.`credit_limits` (`limit`, `rate`) VALUES ('500', '4');

INSERT INTO `banking_system`.`credit_limits` (`limit`, `rate`) VALUES ('1000', '3.5');

INSERT INTO `banking_system`.`credit_limits` (`limit`, `rate`) VALUES ('2500', '3');

INSERT INTO `banking_system`.`credit_limits` (`limit`, `rate`) VALUES ('5000', '3');

INSERT INTO `banking_system`.`credit_limits` (`limit`, `rate`) VALUES ('10000', '3');


INSERT INTO `banking_system`.`users` (`role`, `username`, `password`, `email`, `first_name`, `last_name`) VALUES ('ADMIN', 'romaniukv', 'geronimo11', 'admin@g.com', 'Vika', 'Romaniuk');

INSERT INTO `banking_system`.`deposit_catalog` (`name`, `rate`, `term`, `available`) VALUES ('Standard', '13', '12', '1');

INSERT INTO `banking_system`.`deposit_catalog` (`name`, `rate`, `term`, `available`) VALUES ('Standard', '10', '18', '1');

INSERT INTO `banking_system`.`deposit_catalog` (`name`, `rate`, `term`, `available`) VALUES ('Standard', '10', '24', '1');

INSERT INTO `banking_system`.`deposit_catalog` (`name`, `rate`, `term`, `available`) VALUES ('Standard +', '12', '6', '1');

INSERT INTO `banking_system`.`deposit_catalog` (`name`, `rate`, `term`, `available`) VALUES ('Standard +', '10.5', '3', '1');

INSERT INTO `banking_system`.`deposit_catalog` (`name`, `rate`, `term`, `available`) VALUES ('Standard +', '10', '1', '1');

INSERT INTO `banking_system`.`accounts_numbers` (`number`) VALUES ('456700000000000');

