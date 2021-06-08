-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(150) NOT NULL,
  `full_stock` int NOT NULL,
  `remaining_stock` int NOT NULL,
  `total_pre_booking` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'L\'étranger','Albert Camus',3,0,0),(2,'Bel-Ami','Guy de Maupassant',3,0,1),(3,'Ne tirez pas sur l\'oiseau moqueur','Harper Lee',3,3,0),(4,'Sami et Julie, L\'anniversaire de Julie','Emmanuelle Massonaud',3,1,2),(5,'Arsène Lupin, Gentleman Cambrioleur','Maurice Leblanc',3,3,0),(6,'On va déguster l\'Italie','François-Régis Gaudry',3,3,0),(7,'La familia grande','Camille Kouchner',3,3,0),(8,'L\'Inconnu de la poste','Florence Aubenas',3,0,0),(9,'Les cinq blessures qui empêchent d\'être soi-même','Lise Bourbeau',3,3,0),(10,'Si ça saigne','Stephen King',3,3,0),(11,'Tout le bleu du ciel','Melissa Da Costa',3,3,0),(12,'Ce n\'était pas de l\'amour','Betty Mannechez, Joëlle Mignot',3,0,3);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (172),(172),(172);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lending`
--

DROP TABLE IF EXISTS `lending`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lending` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lending_date` datetime NOT NULL,
  `due_date` date NOT NULL,
  `is_extended` tinyint(1) DEFAULT NULL,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `books_lendings_fk` (`book_id`),
  KEY `users_lendings_fk` (`user_id`),
  CONSTRAINT `books_lendings_fk` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `users_lendings_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lending`
--

LOCK TABLES `lending` WRITE;
/*!40000 ALTER TABLE `lending` DISABLE KEYS */;
INSERT INTO `lending` VALUES (1,'2020-12-11 00:00:00','2021-06-04',0,1,1),(12,'2021-01-08 00:00:00','2021-06-26',0,3,2),(13,'2021-01-08 00:00:00','2021-07-01',1,2,2),(50,'2021-02-15 00:00:00','2021-07-24',1,1,8),(51,'2021-05-25 00:00:00','2021-06-20',0,3,4),(52,'2021-05-20 00:00:00','2021-06-17',0,3,12);
/*!40000 ALTER TABLE `lending` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notified`
--

DROP TABLE IF EXISTS `notified`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notified` (
  `id` int NOT NULL AUTO_INCREMENT,
  `notified_date` date NOT NULL,
  `user_id` int NOT NULL,
  `prebooking_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `users_notifieds_fk` (`user_id`),
  KEY `prebookings_notifieds_fk` (`prebooking_id`),
  CONSTRAINT `prebookings_notifieds_fk` FOREIGN KEY (`prebooking_id`) REFERENCES `prebooking` (`id`) ON DELETE CASCADE,
  CONSTRAINT `users_notifieds_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notified`
--

LOCK TABLES `notified` WRITE;
/*!40000 ALTER TABLE `notified` DISABLE KEYS */;
/*!40000 ALTER TABLE `notified` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prebooking`
--

DROP TABLE IF EXISTS `prebooking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prebooking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `books_prebookings_fk` (`book_id`),
  KEY `users_prebookings_fk` (`user_id`),
  CONSTRAINT `books_prebookings_fk` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `users_prebookings_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prebooking`
--

LOCK TABLES `prebooking` WRITE;
/*!40000 ALTER TABLE `prebooking` DISABLE KEYS */;
INSERT INTO `prebooking` VALUES (155,'2021-06-04 21:35:15',1,4),(156,'2021-06-04 22:48:08',2,4);
/*!40000 ALTER TABLE `prebooking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `date_of_birth` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `roles` varchar(45) NOT NULL,
  `enabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Morêt','Linda','2001-11-13','17 Avenue des Peupliers, 95400 Villiers-Le-Bel','0956786767','lindamoret8@gmail.com','12345ID','$2a$10$o.m28e1ov91PgFyIoF89g.hFwfECvJ07kv.pRzJL6bzdf4tr0HSVe','ADMIN',_binary ''),(2,'Darcot','Jean','1996-09-21','86 rue des Roses, 95400 Villiers-Le-Bel','0989765767','jdarcot@gmail.com','67899ID','$2a$10$nX5syXvqpS/RzFWQZ1Ql5uOZLA.Y1q98zMDeyHSXyC4J2fcvfDsE2','USER',_binary ''),(3,'Rensberg','Sabrina','1992-04-10','86 rue des Platanes, 95400 Villiers-Le-Bel','0980729727','srensberg@gmail.com','69000ID','$2a$10$wDgN9ekNGKEe0Re1.2aiUekttXW90cDNNpgSTo6q7YWGVicC5TI9i','USER',_binary '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-08 13:53:59
