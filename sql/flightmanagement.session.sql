USE `flightmanagement`;
CREATE TABLE IF NOT EXISTS `user` (
    `user_id` int(11) NULL AUTO_INCREMENT,
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