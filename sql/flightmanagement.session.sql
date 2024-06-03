drop database flightmanagement;
create database flightmanagement;
USE `flightmanagement`;
CREATE TABLE IF NOT EXISTS `user` (
    `user_id` BIGINT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` varchar(255) NOT NULL,
    `email` varchar(100) NULL,
    `role` int NOT NULL DEFAULT 0,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `email` (`email`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO `user` (
        `user_id`,
        `username`,
        `password`,
        `email`,
        `role`
    )
VALUES (
        1,
        'admin',
        'admin',
        'sakurapuare@sakurapuare.com',
        15
    );
CREATE TABLE IF NOT EXISTS `airline` (
    `airline_id` BIGINT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `airline_name` varchar(100) NOT NULL,
    `airline_country` varchar(100) NOT NULL,
    `airline_code` varchar(10) NOT NULL,
    `airline_description` varchar(255) NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`airline_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
CREATE TABLE IF NOT EXISTS `merchant` (
    `merchant_id` BIGINT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `merchant_name` varchar(100) NOT NULL,
    `merchant_address` varchar(255) NOT NULL,
    `merchant_description` TEXT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`merchant_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
CREATE TABLE IF NOT EXISTS `passenger` (
    `passenger_id` BIGINT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `passenger_name` varchar(100) NOT NULL,
    `passenger_phone` varchar(20) NOT NULL,
    `identity_number` varchar(20) NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`passenger_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
CREATE TABLE IF NOT EXISTS `staff` (
    `staff_id` BIGINT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `staff_name` varchar(100) NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`staff_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
CREATE TABLE IF NOT EXISTS `good` (
    `id` BIGINT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `description` varchar(255) NULL DEFAULT "",
    `price` decimal(10, 2) NOT NULL DEFAULT 0.00,
    `stock` BIGINT NOT NULL DEFAULT 0,
    `category` varchar(100) NOT NULL DEFAULT "",
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;