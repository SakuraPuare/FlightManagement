drop database flightmanagement;
create database flightmanagement;
USE `flightmanagement`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` BIGINT AUTO_INCREMENT,
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
INSERT INTO `users` (
    `username`,
    `password`,
    `email`,
    `role`
  )
VALUES (
    'admin',
    'admin',
    'sakurapuare@sakurapuare.com',
    15
  ),
  (
    'airline',
    'airline',
    'airline@example.com',
    4
  ),
  (
    'merchant',
    'merchant',
    'merchant@example.com',
    2
  ),
  ('staff', 'staff', 'staff@example.com', 8),
  (
    'passenger',
    'passenger',
    'passenger@example.com',
    1
  );
CREATE TABLE IF NOT EXISTS `airlines` (
  `airline_id` BIGINT AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `airline_name` varchar(100) NOT NULL,
  `airline_country` varchar(100) NOT NULL,
  `airline_code` varchar(10) NOT NULL,
  `airline_description` varchar(255) NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`airline_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO `airlines` (
    `user_id`,
    `airline_name`,
    `airline_country`,
    `airline_code`,
    `airline_description`
  )
VALUES (
    1,
    'ADMIN AIRLINES',
    'Indonesia',
    'GA',
    'Garuda Indonesia is the flag carrier of Indonesia.'
  ),
  (
    2,
    'Lion Air',
    'Indonesia',
    'JT',
    'Lion Air is an Indonesian low-cost airline.'
  );
CREATE TABLE IF NOT EXISTS `merchants` (
  `merchant_id` BIGINT AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `merchant_name` varchar(100) NOT NULL,
  `merchant_address` varchar(255) NOT NULL,
  `merchant_description` TEXT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`merchant_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO `merchants` (
    `user_id`,
    `merchant_name`,
    `merchant_address`,
    `merchant_description`
  )
VALUES (
    1,
    ' ADMIN MERCHANT ',
    ' Jl.Admin No.1 ',
    ' The best merchant in the world '
  ),
  (
    3,
    ' MERCHANT 2 ',
    ' Jl.Merchant No.2 ',
    ' The second best merchant in the world '
  );
CREATE TABLE IF NOT EXISTS `passengers` (
  `passenger_id` BIGINT AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `passenger_name` varchar(100) NOT NULL,
  `passenger_phone` varchar(20) NOT NULL,
  `identity_number` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`passenger_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO passengers (
    user_id,
    passenger_name,
    passenger_phone,
    identity_number
  )
VALUES (1, ' John Doe ', ' 1234567890 ', ' ABC123XYZ '),
  (4, ' Jane Doe ', ' 0987654321 ', ' XYZ123ABC ');
CREATE TABLE IF NOT EXISTS `staffs` (
  `staff_id` BIGINT AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `staff_name` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`staff_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO staffs (user_id, staff_name)
VALUES (1, ' John Doe ');
CREATE TABLE IF NOT EXISTS `goods` (
  `id` BIGINT AUTO_INCREMENT,
  `merchant_id` BIGINT NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NULL DEFAULT '',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `stock` BIGINT NOT NULL DEFAULT 0,
  `category` varchar(100) NOT NULL DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`merchant_id`) REFERENCES `merchants` (`merchant_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO `goods` (
    `merchant_id`,
    `name`,
    `description`,
    `price`,
    `stock`,
    `category`
  )
VALUES (
    1,
    'Laptop',
    'Laptop for work',
    10000000.00,
    10,
    'Electronics'
  ),
  (
    1,
    'Smartphone',
    'Smartphone for work',
    5000000.00,
    20,
    'Electronics'
  ),
  (
    2,
    'T-Shirt',
    'T-Shirt for daily use',
    100000.00,
    50,
    'Fashion'
  );
CREATE TABLE IF NOT EXISTS `flights` (
  `id` BIGINT AUTO_INCREMENT,
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
  FOREIGN KEY (`airline_id`) REFERENCES `airlines` (`airline_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO `flights` (
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
    'ABC123',
    200,
    'Jakarta',
    'Bali',
    '2022-10-01 08:00:00',
    120
  ),
  (
    2,
    'DEF456',
    150,
    'Jakarta',
    'Surabaya',
    '2022-10-02 09:00:00',
    90
  );
CREATE TABLE IF NOT EXISTS `tickets` (
  `id` BIGINT AUTO_INCREMENT,
  `flight_id` BIGINT NOT NULL,
  `seat_class` varchar(50) NOT NULL,
  `quota` BIGINT NOT NULL DEFAULT 0,
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`flight_id`) REFERENCES `flights` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO `tickets` (
    `flight_id`,
    `seat_class`,
    `quota`,
    `price`
  )
VALUES (
    1,
    'Economy',
    100,
    1000000.00
  ),
  (
    1,
    'Business',
    50,
    2000000.00
  ),
  (2, 'Economy', 75, 750000.00),
  (
    2,
    'Business',
    50,
    1500000.00
  );
CREATE TABLE IF NOT EXISTS `orders` (
  `id` BIGINT AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `ticket_id` BIGINT NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT ' unpaid ',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
CREATE TABLE IF NOT EXISTS `luggages` (
  `id` BIGINT AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `order_id` BIGINT NOT NULL,
  `weight` decimal(10, 2) NOT NULL,
  `staff_id` BIGINT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  FOREIGN KEY (`staff_id`) REFERENCES `staffs` (`staff_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
CREATE TABLE IF NOT EXISTS `requests` (
  `id` BIGINT AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `info` varchar(255) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'unhandled',
  `handler_id` BIGINT NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  FOREIGN KEY (`handler_id`) REFERENCES `staffs` (`staff_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;