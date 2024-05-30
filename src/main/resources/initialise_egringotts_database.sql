-- Initialise E-Gringotts Database
-- NOTE: You ONLY have to run these commands: "CREATE SCHEMA egringotts;" AND "USE egringotts;"!
-- The rest will be taken care of by the Java Spring Boot application.

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema egringotts
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema egringotts
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `egringotts` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `egringotts` ;

-- -----------------------------------------------------
-- Table `egringotts`.`security_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`security_question` (
                                                                `security_question_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                                `question` VARCHAR(255) NULL DEFAULT NULL,
                                                                PRIMARY KEY (`security_question_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`user_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`user_image` (
                                                         `user_image_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                         `image_path` VARCHAR(255) NULL DEFAULT NULL,
                                                         PRIMARY KEY (`user_image_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`user` (
                                                   `role` VARCHAR(31) NOT NULL,
                                                   `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                   `type` VARCHAR(255) NULL DEFAULT NULL,
                                                   PRIMARY KEY (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`address` (
                                                      `address_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `country` VARCHAR(255) NULL DEFAULT NULL,
                                                      `postcode` VARCHAR(255) NULL DEFAULT NULL,
                                                      `state` VARCHAR(255) NULL DEFAULT NULL,
                                                      `street_name_1` VARCHAR(255) NULL DEFAULT NULL,
                                                      `street_name_2` VARCHAR(255) NULL DEFAULT NULL,
                                                      `town` VARCHAR(255) NULL DEFAULT NULL,
                                                      `type` VARCHAR(255) NULL DEFAULT NULL,
                                                      PRIMARY KEY (`address_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`account` (
                                                      `account_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `closing_date` DATETIME(6) NULL DEFAULT NULL,
                                                      `creation_date` DATETIME(6) NULL DEFAULT NULL,
                                                      `date_of_birth` DATETIME(6) NULL DEFAULT NULL,
                                                      `email_address` VARCHAR(255) NULL DEFAULT NULL,
                                                      `full_name` VARCHAR(255) NULL DEFAULT NULL,
                                                      `gender` VARCHAR(255) NULL DEFAULT NULL,
                                                      `last_login_date` DATETIME(6) NULL DEFAULT NULL,
                                                      `password` VARCHAR(255) NULL DEFAULT NULL,
                                                      `security_pin` INT NULL DEFAULT NULL,
                                                      `telephone_number` VARCHAR(255) NULL DEFAULT NULL,
                                                      `username` VARCHAR(255) NULL DEFAULT NULL,
                                                      `address_id` BIGINT NULL DEFAULT NULL,
                                                      `security_question_id` BIGINT NULL DEFAULT NULL,
                                                      `user_id` BIGINT NULL DEFAULT NULL,
                                                      `user_image_id` BIGINT NULL DEFAULT NULL,
                                                      PRIMARY KEY (`account_id`),
                                                      UNIQUE INDEX `UKq4mt85fqye0pwgamg8vlw8mrb` (`address_id` ASC) VISIBLE,
                                                      UNIQUE INDEX `UKh6dr47em6vg85yuwt4e2roca4` (`user_id` ASC) VISIBLE,
                                                      UNIQUE INDEX `UKnmpov5sg0inlsc5cen86nonsw` (`user_image_id` ASC) VISIBLE,
                                                      INDEX `FK5c7qbhfu3c0xl8kesk2gq4x78` (`security_question_id` ASC) VISIBLE,
                                                      CONSTRAINT `FK5c7qbhfu3c0xl8kesk2gq4x78`
                                                          FOREIGN KEY (`security_question_id`)
                                                              REFERENCES `egringotts`.`security_question` (`security_question_id`),
                                                      CONSTRAINT `FK5kqhbp0algux19yi50k3egksc`
                                                          FOREIGN KEY (`user_image_id`)
                                                              REFERENCES `egringotts`.`user_image` (`user_image_id`),
                                                      CONSTRAINT `FK7m8ru44m93ukyb61dfxw0apf6`
                                                          FOREIGN KEY (`user_id`)
                                                              REFERENCES `egringotts`.`user` (`user_id`),
                                                      CONSTRAINT `FK9lna4d7ow9qbs27m5psafys58`
                                                          FOREIGN KEY (`address_id`)
                                                              REFERENCES `egringotts`.`address` (`address_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`balance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`balance` (
                                                      `balance_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `balance` DOUBLE NULL DEFAULT NULL,
                                                      `currency` VARCHAR(255) NULL DEFAULT NULL,
                                                      `account_id` BIGINT NULL DEFAULT NULL,
                                                      PRIMARY KEY (`balance_id`),
                                                      INDEX `FKxhe1wv9gspc3wl0w66bptmgs` (`account_id` ASC) VISIBLE,
                                                      CONSTRAINT `FKxhe1wv9gspc3wl0w66bptmgs`
                                                          FOREIGN KEY (`account_id`)
                                                              REFERENCES `egringotts`.`account` (`account_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`card` (
                                                   `card_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                   `card_number` VARCHAR(255) NULL DEFAULT NULL,
                                                   `cvv` VARCHAR(255) NULL DEFAULT NULL,
                                                   `expiry_date` DATETIME(6) NULL DEFAULT NULL,
                                                   `account_id` BIGINT NULL DEFAULT NULL,
                                                   PRIMARY KEY (`card_id`),
                                                   INDEX `FK8v67eys6tqflsm6hrdgru2phu` (`account_id` ASC) VISIBLE,
                                                   CONSTRAINT `FK8v67eys6tqflsm6hrdgru2phu`
                                                       FOREIGN KEY (`account_id`)
                                                           REFERENCES `egringotts`.`account` (`account_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`security_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`security_answer` (
                                                              `security_answer_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                              `answer` VARCHAR(255) NULL DEFAULT NULL,
                                                              `account_id` BIGINT NULL DEFAULT NULL,
                                                              `security_question_id` BIGINT NULL DEFAULT NULL,
                                                              PRIMARY KEY (`security_answer_id`),
                                                              UNIQUE INDEX `UKrd3gs4dsd0upjyuqwvyfe7d2y` (`account_id` ASC) VISIBLE,
                                                              INDEX `FKsj66ipd0qcy3n5ipxtcmg6s4n` (`security_question_id` ASC) VISIBLE,
                                                              CONSTRAINT `FK83gvi9llp5xd44ex8a39gja6g`
                                                                  FOREIGN KEY (`account_id`)
                                                                      REFERENCES `egringotts`.`account` (`account_id`),
                                                              CONSTRAINT `FKsj66ipd0qcy3n5ipxtcmg6s4n`
                                                                  FOREIGN KEY (`security_question_id`)
                                                                      REFERENCES `egringotts`.`security_question` (`security_question_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`transaction` (
                                                          `transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                          `amount` DOUBLE NULL DEFAULT NULL,
                                                          `category` VARCHAR(255) NULL DEFAULT NULL,
                                                          `currency` VARCHAR(255) NULL DEFAULT NULL,
                                                          `date` DATETIME(6) NULL DEFAULT NULL,
                                                          `from_account_balance` DOUBLE NULL DEFAULT NULL,
                                                          `from_account_id` BIGINT NULL DEFAULT NULL,
                                                          `to_account_id` BIGINT NULL DEFAULT NULL,
                                                          PRIMARY KEY (`transaction_id`),
                                                          INDEX `FKrff4jlxetafju1e5cks5mfcnk` (`from_account_id` ASC) VISIBLE,
                                                          INDEX `FKluqt8k2pa8d4gmggx4rhl5vgv` (`to_account_id` ASC) VISIBLE,
                                                          CONSTRAINT `FKluqt8k2pa8d4gmggx4rhl5vgv`
                                                              FOREIGN KEY (`to_account_id`)
                                                                  REFERENCES `egringotts`.`account` (`account_id`),
                                                          CONSTRAINT `FKrff4jlxetafju1e5cks5mfcnk`
                                                              FOREIGN KEY (`from_account_id`)
                                                                  REFERENCES `egringotts`.`account` (`account_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
