-- MySQL Workbench Forward Engineering

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
                                                      `security_pin` VARCHAR(255) NULL DEFAULT NULL,
                                                      `telephone_number` VARCHAR(255) NULL DEFAULT NULL,
                                                      `username` VARCHAR(255) NULL DEFAULT NULL,
                                                      `address_id` BIGINT NULL DEFAULT NULL,
                                                      `security_question_id` BIGINT NULL DEFAULT NULL,
                                                      `user_id` BIGINT NULL DEFAULT NULL,
                                                      PRIMARY KEY (`account_id`),
                                                      UNIQUE INDEX `UKq4mt85fqye0pwgamg8vlw8mrb` (`address_id` ASC) VISIBLE,
                                                      UNIQUE INDEX `UKh6dr47em6vg85yuwt4e2roca4` (`user_id` ASC) VISIBLE,
                                                      INDEX `FK5c7qbhfu3c0xl8kesk2gq4x78` (`security_question_id` ASC) VISIBLE,
                                                      CONSTRAINT `FK5c7qbhfu3c0xl8kesk2gq4x78`
                                                          FOREIGN KEY (`security_question_id`)
                                                              REFERENCES `egringotts`.`security_question` (`security_question_id`),
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
-- Table `egringotts`.`currency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`currency` (
                                                       `currency_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                       `abbreviation` VARCHAR(255) NULL DEFAULT NULL,
                                                       `name` VARCHAR(255) NULL DEFAULT NULL,
                                                       PRIMARY KEY (`currency_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `egringotts`.`balance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`balance` (
                                                      `balance_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `balance` DOUBLE NULL DEFAULT NULL,
                                                      `account_id` BIGINT NULL DEFAULT NULL,
                                                      `currency_id` BIGINT NULL DEFAULT NULL,
                                                      PRIMARY KEY (`balance_id`),
                                                      INDEX `FKxhe1wv9gspc3wl0w66bptmgs` (`account_id` ASC) VISIBLE,
                                                      INDEX `FK45r0l2sc0cdja19no0orl9i95` (`currency_id` ASC) VISIBLE,
                                                      CONSTRAINT `FK45r0l2sc0cdja19no0orl9i95`
                                                          FOREIGN KEY (`currency_id`)
                                                              REFERENCES `egringotts`.`currency` (`currency_id`),
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
                                                   `type` VARCHAR(255) NULL DEFAULT NULL,
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
-- Table `egringotts`.`currency_exchange`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `egringotts`.`currency_exchange` (
                                                                `currency_exchange_rate_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                                `processing_fee` DOUBLE NULL DEFAULT NULL,
                                                                `rate` DOUBLE NULL DEFAULT NULL,
                                                                `from_currency_id` BIGINT NULL DEFAULT NULL,
                                                                `to_currency_id` BIGINT NULL DEFAULT NULL,
                                                                PRIMARY KEY (`currency_exchange_rate_id`),
                                                                INDEX `FK7v1dcryjsgv54jy3hf3s9ug7e` (`from_currency_id` ASC) VISIBLE,
                                                                INDEX `FKpf6um4qb983kv3hyjyxajr2w7` (`to_currency_id` ASC) VISIBLE,
                                                                CONSTRAINT `FK7v1dcryjsgv54jy3hf3s9ug7e`
                                                                    FOREIGN KEY (`from_currency_id`)
                                                                        REFERENCES `egringotts`.`currency` (`currency_id`),
                                                                CONSTRAINT `FKpf6um4qb983kv3hyjyxajr2w7`
                                                                    FOREIGN KEY (`to_currency_id`)
                                                                        REFERENCES `egringotts`.`currency` (`currency_id`))
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
                                                          `date` DATETIME(6) NULL DEFAULT NULL,
                                                          `description` VARCHAR(255) NULL DEFAULT NULL,
                                                          `payment_method` VARCHAR(255) NULL DEFAULT NULL,
                                                          `receipt_file_path` VARCHAR(255) NULL DEFAULT NULL,
                                                          `type` VARCHAR(255) NULL DEFAULT NULL,
                                                          `card_id` BIGINT NULL DEFAULT NULL,
                                                          `currency_id` BIGINT NULL DEFAULT NULL,
                                                          `from_account_id` BIGINT NULL DEFAULT NULL,
                                                          `from_account_balance_id` BIGINT NULL DEFAULT NULL,
                                                          `to_account_id` BIGINT NULL DEFAULT NULL,
                                                          PRIMARY KEY (`transaction_id`),
                                                          UNIQUE INDEX `UKbyn371gf817ljah73cb05vsav` (`card_id` ASC) VISIBLE,
                                                          INDEX `FKlcx7g8g7x4fyns9k6vesu3n9n` (`currency_id` ASC) VISIBLE,
                                                          INDEX `FKrff4jlxetafju1e5cks5mfcnk` (`from_account_id` ASC) VISIBLE,
                                                          INDEX `FK3hkkcim0w83trc8nd2aqyorlc` (`from_account_balance_id` ASC) VISIBLE,
                                                          INDEX `FKluqt8k2pa8d4gmggx4rhl5vgv` (`to_account_id` ASC) VISIBLE,
                                                          CONSTRAINT `FK3hkkcim0w83trc8nd2aqyorlc`
                                                              FOREIGN KEY (`from_account_balance_id`)
                                                                  REFERENCES `egringotts`.`balance` (`balance_id`),
                                                          CONSTRAINT `FK484i2t8acnct6xy8ylevl40go`
                                                              FOREIGN KEY (`card_id`)
                                                                  REFERENCES `egringotts`.`card` (`card_id`),
                                                          CONSTRAINT `FKlcx7g8g7x4fyns9k6vesu3n9n`
                                                              FOREIGN KEY (`currency_id`)
                                                                  REFERENCES `egringotts`.`currency` (`currency_id`),
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
