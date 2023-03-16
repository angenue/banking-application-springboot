-- Banking System Database

CREATE DATABASE IF NOT EXISTS `banking_system`;
USE `banking_system`;

DROP TABLE IF EXISTS transaction_details;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS person;
CREATE TABLE IF NOT EXISTS `person` (
    `username` VARCHAR(100) NOT NULL,
    `acc_password` VARCHAR(100) NOT NULL,
	`first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    unique (`email`),
    PRIMARY KEY (`username`)
);
CREATE TABLE IF NOT EXISTS `account` (
	`username` VARCHAR(100) DEFAULT NULL,
	`account_number` INT NOT NULL AUTO_INCREMENT,
    `acc_type` VARCHAR(10) NOT NULL,
    `acc_balance` INT NOT NULL,
    PRIMARY KEY (`account_number`),
    FOREIGN KEY (`username`)
        REFERENCES `person` (`username`)
)  AUTO_INCREMENT=100000;

CREATE TABLE IF NOT EXISTS `transaction_details` (
	`account_number` INT DEFAULT NULL,
    `transaction_id` INT NOT NULL AUTO_INCREMENT,
    `transaction_amount` INT NOT NULL,
    `transaction_date` date NOT NULL,
    `transaction_type` VARCHAR (10) NOT NULL,
    PRIMARY KEY (`transaction_id`),
    FOREIGN KEY (`account_number`)
        REFERENCES `account` (`account_number`)
);



