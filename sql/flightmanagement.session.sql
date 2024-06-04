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
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO `airline` (
    `airline_id`,
    `user_id`,
    `airline_name`,
    `airline_country`,
    `airline_code`,
    `airline_description`
  )
VALUES (
    1,
    1,
    'Garuda Indonesia',
    'Indonesia',
    'GA',
    'Garuda Indonesia is the flag carrier of Indonesia.'
  ),
  (
    2,
    1,
    'Lion Air',
    'Indonesia',
    'JT',
    'Lion Air is an Indonesian low-cost airline.'
  ),
  (
    3,
    1,
    'Citi link',
    'Indonesia',
    'QG',
    'Citi link is a low-cost airline headquartered in Jakarta, Indonesia.'
  );
CREATE TABLE IF NOT EXISTS `merchant` (
  `merchant_id` BIGINT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `merchant_name` varchar(100) NOT NULL,
  `merchant_address` varchar(255) NOT NULL,
  `merchant_description` TEXT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`merchant_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
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
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO passenger (
    user_id,
    passenger_name,
    passenger_phone,
    identity_number
  )
VALUES (1, 'John Doe', '1234567890', 'ABC123XYZ');
CREATE TABLE IF NOT EXISTS `staff` (
  `staff_id` BIGINT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `staff_name` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`staff_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
CREATE TABLE IF NOT EXISTS `good` (
  `id` BIGINT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NULL DEFAULT '',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `stock` BIGINT NOT NULL DEFAULT 0,
  `category` varchar(100) NOT NULL DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
CREATE TABLE IF NOT EXISTS `flight` (
  `id` BIGINT NULL AUTO_INCREMENT,
  `airline_id` BIGINT NOT NULL,
  `flight_number` varchar(50) NOT NULL,
  `capacity` int NOT NULL,
  `departure_city` varchar(100) NOT NULL,
  `arrival_city` varchar(100) NOT NULL,
  `date_of_departure` datetime NOT NULL,
  `estimated_travel_time` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`airline_id`) REFERENCES `airline` (`airline_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO `flight` (
    `id`,
    `airline_id`,
    `flight_number`,
    `capacity`,
    `departure_city`,
    `arrival_city`,
    `date_of_departure`,
    `estimated_travel_time`
  )
VALUES (
    1,
    1,
    'ABC123',
    200,
    'Jakarta',
    'Bali',
    '2022-10-01 08:00:00',
    120
  ),
  (
    2,
    2,
    'DEF456',
    150,
    'Jakarta',
    'Surabaya',
    '2022-10-02 09:00:00',
    90
  );
CREATE TABLE IF NOT EXISTS `ticket` (
  `id` BIGINT NULL AUTO_INCREMENT,
  `flight_id` BIGINT NOT NULL,
  `passenger_id` BIGINT NOT NULL,
  `seat_class` varchar(50) NOT NULL,
  `seat_number` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`),
  FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`passenger_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;