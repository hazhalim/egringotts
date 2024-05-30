-- E-Gringotts Database Initialisation
-- NOTE: Only run "CREATE SCHEMA egringotts;" and "USE egringotts;"! The Spring boot application will take care of the rest.

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
-- Table `egringotts`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`address` (
                                                      `addressid` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `country` VARCHAR(255) NULL DEFAULT NULL,
                                                      `postcode` VARCHAR(255) NULL DEFAULT NULL,
                                                      `state` VARCHAR(255) NULL DEFAULT NULL,
                                                      `street_name1` VARCHAR(255) NULL DEFAULT NULL,
                                                      `street_name2` VARCHAR(255) NULL DEFAULT NULL,
                                                      `town` VARCHAR(255) NULL DEFAULT NULL,
                                                      `type` VARCHAR(255) NULL DEFAULT NULL,
                                                      PRIMARY KEY (`addressid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`user` (
                                                   `role` VARCHAR(31) NOT NULL,
                                                   `userid` BIGINT NOT NULL AUTO_INCREMENT,
                                                   `type` VARCHAR(255) NULL DEFAULT NULL,
                                                   PRIMARY KEY (`userid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`user_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`user_image` (
                                                         `user_imageid` BIGINT NOT NULL AUTO_INCREMENT,
                                                         `image_path` VARCHAR(255) NULL DEFAULT NULL,
                                                         PRIMARY KEY (`user_imageid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`security_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`security_question` (
                                                                `security_questionid` BIGINT NOT NULL AUTO_INCREMENT,
                                                                `question` VARCHAR(255) NULL DEFAULT NULL,
                                                                PRIMARY KEY (`security_questionid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`account` (
                                                      `accountid` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `closing_date` DATETIME(6) NULL DEFAULT NULL,
                                                      `creation_date` DATETIME(6) NULL DEFAULT NULL,
                                                      `date_of_birth` DATETIME(6) NULL DEFAULT NULL,
                                                      `email_address` VARCHAR(255) NULL DEFAULT NULL,
                                                      `full_name` VARCHAR(255) NULL DEFAULT NULL,
                                                      `gender` VARCHAR(255) NULL DEFAULT NULL,
                                                      `last_login_date` DATETIME(6) NULL DEFAULT NULL,
                                                      `password` VARCHAR(255) NULL DEFAULT NULL,
                                                      `securitypin` INT NULL DEFAULT NULL,
                                                      `telephone_number` VARCHAR(255) NULL DEFAULT NULL,
                                                      `username` VARCHAR(255) NULL DEFAULT NULL,
                                                      `addressid` BIGINT NULL DEFAULT NULL,
                                                      `security_questionid` BIGINT NULL DEFAULT NULL,
                                                      `userid` BIGINT NULL DEFAULT NULL,
                                                      `user_imageid` BIGINT NULL DEFAULT NULL,
                                                      PRIMARY KEY (`accountid`),
                                                      UNIQUE INDEX `UK2p8h03e7j9v244cfas86fp464` (`addressid` ASC) VISIBLE,
                                                      UNIQUE INDEX `UKeemtigya6ecjvaugout5us2rn` (`userid` ASC) VISIBLE,
                                                      UNIQUE INDEX `UKaicwogr8ubhl4vmancvnet49d` (`user_imageid` ASC) VISIBLE,
                                                      INDEX `FKyh1s80qaja0b5bc5qq02y2od` (`security_questionid` ASC) VISIBLE,
                                                      CONSTRAINT `FK3pjl09e8wdsrvons775l2418a`
                                                          FOREIGN KEY (`addressid`)
                                                              REFERENCES `egringotts`.`address` (`addressid`),
                                                      CONSTRAINT `FKn1bafc2a3wjs4dsu0ke873w0v`
                                                          FOREIGN KEY (`userid`)
                                                              REFERENCES `egringotts`.`user` (`userid`),
                                                      CONSTRAINT `FKr7a7ch9tv5ck60jcubq4xlxx8`
                                                          FOREIGN KEY (`user_imageid`)
                                                              REFERENCES `egringotts`.`user_image` (`user_imageid`),
                                                      CONSTRAINT `FKyh1s80qaja0b5bc5qq02y2od`
                                                          FOREIGN KEY (`security_questionid`)
                                                              REFERENCES `egringotts`.`security_question` (`security_questionid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`balance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`balance` (
                                                      `balanceid` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `balance` DOUBLE NULL DEFAULT NULL,
                                                      `currency` VARCHAR(255) NULL DEFAULT NULL,
                                                      `accountid` BIGINT NULL DEFAULT NULL,
                                                      PRIMARY KEY (`balanceid`),
                                                      INDEX `FKphqkhvkpmwv8wepwkp54hpygw` (`accountid` ASC) VISIBLE,
                                                      CONSTRAINT `FKphqkhvkpmwv8wepwkp54hpygw`
                                                          FOREIGN KEY (`accountid`)
                                                              REFERENCES `egringotts`.`account` (`accountid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`card` (
                                                   `cardid` BIGINT NOT NULL AUTO_INCREMENT,
                                                   `card_number` VARCHAR(255) NULL DEFAULT NULL,
                                                   `cvv` VARCHAR(255) NULL DEFAULT NULL,
                                                   `expiry_date` DATETIME(6) NULL DEFAULT NULL,
                                                   `accountid` BIGINT NULL DEFAULT NULL,
                                                   PRIMARY KEY (`cardid`),
                                                   INDEX `FKdstqpl1k7gyj8g2crwmtymw03` (`accountid` ASC) VISIBLE,
                                                   CONSTRAINT `FKdstqpl1k7gyj8g2crwmtymw03`
                                                       FOREIGN KEY (`accountid`)
                                                           REFERENCES `egringotts`.`account` (`accountid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`security_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`security_answer` (
                                                              `security_answerid` BIGINT NOT NULL AUTO_INCREMENT,
                                                              `answer` VARCHAR(255) NULL DEFAULT NULL,
                                                              `accountid` BIGINT NULL DEFAULT NULL,
                                                              `security_questionid` BIGINT NULL DEFAULT NULL,
                                                              PRIMARY KEY (`security_answerid`),
                                                              UNIQUE INDEX `UK2s40b6t5rle90akfogjk3cxub` (`accountid` ASC) VISIBLE,
                                                              INDEX `FKaxaa2aepknxsodypwqhrxm7i4` (`security_questionid` ASC) VISIBLE,
                                                              CONSTRAINT `FKaxaa2aepknxsodypwqhrxm7i4`
                                                                  FOREIGN KEY (`security_questionid`)
                                                                      REFERENCES `egringotts`.`security_question` (`security_questionid`),
                                                              CONSTRAINT `FKeiyts1dw90s6n1qe05x8dtx95`
                                                                  FOREIGN KEY (`accountid`)
                                                                      REFERENCES `egringotts`.`account` (`accountid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`transaction` (
                                                          `transactionid` BIGINT NOT NULL AUTO_INCREMENT,
                                                          `amount` DOUBLE NULL DEFAULT NULL,
                                                          `category` VARCHAR(255) NULL DEFAULT NULL,
                                                          `currency` VARCHAR(255) NULL DEFAULT NULL,
                                                          `date` DATETIME(6) NULL DEFAULT NULL,
                                                          `from_account_balance` DOUBLE NULL DEFAULT NULL,
                                                          `from_accountid` BIGINT NULL DEFAULT NULL,
                                                          `to_accountid` BIGINT NULL DEFAULT NULL,
                                                          PRIMARY KEY (`transactionid`),
                                                          INDEX `FKpo11xuutpx6nqtfkruokrsfm9` (`from_accountid` ASC) VISIBLE,
                                                          INDEX `FKo3my61p7p5gfscetd4dqqtflx` (`to_accountid` ASC) VISIBLE,
                                                          CONSTRAINT `FKo3my61p7p5gfscetd4dqqtflx`
                                                              FOREIGN KEY (`to_accountid`)
                                                                  REFERENCES `egringotts`.`account` (`accountid`),
                                                          CONSTRAINT `FKpo11xuutpx6nqtfkruokrsfm9`
                                                              FOREIGN KEY (`from_accountid`)
                                                                  REFERENCES `egringotts`.`account` (`accountid`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
