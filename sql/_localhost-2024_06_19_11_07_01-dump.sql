/*!999999\- enable the sandbox mode */
-- MariaDB dump 10.19-11.4.2-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: flightmanagement
-- ------------------------------------------------------
-- Server version	11.4.2-MariaDB
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */
;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */
;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */
;
/*!40101 SET NAMES utf8mb4 */
;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */
;
/*!40103 SET TIME_ZONE='+00:00' */
;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */
;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */
;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */
;
--
-- Dumping data for table `airlines`
--

LOCK TABLES `airlines` WRITE;
/*!40000 ALTER TABLE `airlines` DISABLE KEYS */
;
INSERT INTO `airlines`
VALUES (
        1,
        1,
        'admin',
        'admin',
        'admin',
        'admin',
        '2024-06-19 02:44:34',
        '2024-06-19 02:44:34'
    ),
    (
        2,
        2,
        'jiahow',
        'jiahow',
        'jiahow',
        'jiahow',
        '2024-06-19 02:45:03',
        '2024-06-19 02:45:03'
    );
/*!40000 ALTER TABLE `airlines` ENABLE KEYS */
;
UNLOCK TABLES;
--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */
;
INSERT INTO `flights`
VALUES (
        1,
        2,
        'A',
        200,
        'a',
        'b',
        '2024-06-19 02:50:12',
        100,
        '2024-06-19 02:53:37',
        '2024-06-19 02:53:37'
    );
/*!40000 ALTER TABLE `flights` ENABLE KEYS */
;
UNLOCK TABLES;
--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */
;
INSERT INTO `goods`
VALUES (
        2,
        2,
        '11',
        '',
        1000.00,
        20,
        '',
        '2024-06-19 03:03:55',
        '2024-06-19 03:05:45'
    );
/*!40000 ALTER TABLE `goods` ENABLE KEYS */
;
UNLOCK TABLES;
LOCK TABLES `merchants` WRITE;
/*!40000 ALTER TABLE `merchants` DISABLE KEYS */
;
INSERT INTO `merchants`
VALUES (
        1,
        1,
        'admin',
        'admin',
        'admin',
        '2024-06-19 02:44:26',
        '2024-06-19 02:44:26'
    ),
    (
        2,
        2,
        'jiahow',
        'jiahow',
        'jiahow',
        '2024-06-19 02:44:57',
        '2024-06-19 02:44:57'
    );
/*!40000 ALTER TABLE `merchants` ENABLE KEYS */
;
UNLOCK TABLES;
--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */
;
INSERT INTO `orders`
VALUES (
        1,
        2,
        1,
        'canceled',
        '2024-06-19 02:54:35',
        '2024-06-19 02:54:49'
    ),
    (
        2,
        2,
        1,
        'canceled',
        '2024-06-19 02:54:51',
        '2024-06-19 02:55:04'
    ),
    (
        3,
        2,
        1,
        'unpaid',
        '2024-06-19 02:55:07',
        '2024-06-19 02:55:07'
    );
/*!40000 ALTER TABLE `orders` ENABLE KEYS */
;
UNLOCK TABLES;
--
-- Dumping data for table `passengers`
--

LOCK TABLES `passengers` WRITE;
/*!40000 ALTER TABLE `passengers` DISABLE KEYS */
;
INSERT INTO `passengers`
VALUES (
        1,
        1,
        'admin',
        'admin',
        'admin',
        '2024-06-19 02:44:17',
        '2024-06-19 02:44:17'
    ),
    (
        2,
        2,
        'jiahow',
        'jiahow',
        'jiahow',
        '2024-06-19 02:44:52',
        '2024-06-19 02:44:52'
    );
/*!40000 ALTER TABLE `passengers` ENABLE KEYS */
;
UNLOCK TABLES;
--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */
;
/*!40000 ALTER TABLE `requests` ENABLE KEYS */
;
UNLOCK TABLES;
--
-- Dumping data for table `staffs`
--

LOCK TABLES `staffs` WRITE;
/*!40000 ALTER TABLE `staffs` DISABLE KEYS */
;
INSERT INTO `staffs`
VALUES (
        1,
        1,
        'admin',
        '2024-06-19 02:44:41',
        '2024-06-19 02:44:41'
    ),
    (
        2,
        2,
        'jiahow',
        '2024-06-19 02:45:08',
        '2024-06-19 02:45:08'
    );
/*!40000 ALTER TABLE `staffs` ENABLE KEYS */
;
UNLOCK TABLES;
--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */
;
INSERT INTO `tickets`
VALUES (
        1,
        1,
        '10000',
        19,
        0.00,
        '2024-06-19 02:54:01',
        '2024-06-19 02:55:07'
    );
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */
;
UNLOCK TABLES;
--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */
;
INSERT INTO `users`
VALUES (
        1,
        'admin',
        'admin',
        'admin',
        15,
        '2024-06-19 02:44:17',
        '2024-06-19 02:44:41'
    ),
    (
        2,
        'jiahow',
        'jiahow',
        'jiahow',
        15,
        '2024-06-19 02:44:52',
        '2024-06-19 02:45:08'
    );
/*!40000 ALTER TABLE `users` ENABLE KEYS */
;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */
;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */
;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */
;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */
;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */
;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */
;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */
;
-- Dump completed on 2024-06-19 11:07:02