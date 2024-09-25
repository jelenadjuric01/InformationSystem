-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: videostore
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `belongs`
--

DROP TABLE IF EXISTS `belongs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `belongs` (
  `IdV` int NOT NULL,
  `IdC` int NOT NULL,
  `IdB` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IdB`),
  UNIQUE KEY `addVC` (`IdV`,`IdC`),
  KEY `IdC_idx` (`IdC`),
  CONSTRAINT `IdC` FOREIGN KEY (`IdC`) REFERENCES `category` (`IdC`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `IdV` FOREIGN KEY (`IdV`) REFERENCES `video` (`IdV`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belongs`
--

LOCK TABLES `belongs` WRITE;
/*!40000 ALTER TABLE `belongs` DISABLE KEYS */;
INSERT INTO `belongs` VALUES (1,1,3),(4,6,6),(4,7,8),(5,5,9),(5,6,7),(6,2,10),(6,3,11),(6,4,12),(7,5,13);
/*!40000 ALTER TABLE `belongs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `IdC` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`IdC`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Komedija'),(2,'Akcija'),(3,'Misterija'),(4,'Triler'),(5,'Opustajuci'),(6,'Muzika'),(7,'Za decu');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package` (
  `IdP` int NOT NULL AUTO_INCREMENT,
  `Price` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdP`),
  CONSTRAINT `package_chk_1` CHECK ((`Price` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES (1,1999),(2,549),(3,2999),(4,349),(5,1699);
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `IdR` int NOT NULL AUTO_INCREMENT,
  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IdU` int NOT NULL,
  `IdV` int NOT NULL,
  `Rate` int NOT NULL,
  PRIMARY KEY (`IdR`),
  KEY `IdU_idx` (`IdU`),
  KEY `IdV_idx` (`IdV`),
  CONSTRAINT `IdUR` FOREIGN KEY (`IdU`) REFERENCES `user` (`IdU`) ON UPDATE CASCADE,
  CONSTRAINT `IdVR` FOREIGN KEY (`IdV`) REFERENCES `video` (`IdV`) ON UPDATE CASCADE,
  CONSTRAINT `rating_chk_1` CHECK ((`Rate` in (_utf8mb4'1',_utf8mb4'2',_utf8mb4'3',_utf8mb4'4',_utf8mb4'5')))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (4,'2024-06-14 14:06:34',6,6,3),(5,'2024-06-14 14:06:50',1,6,2),(6,'2024-06-14 14:07:52',5,4,4),(7,'2024-06-14 14:09:17',6,5,5),(8,'2024-06-14 14:09:42',6,7,4);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscription` (
  `IdS` int NOT NULL AUTO_INCREMENT,
  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Price` int NOT NULL DEFAULT '0',
  `IdU` int NOT NULL,
  `IdP` int NOT NULL,
  PRIMARY KEY (`IdS`),
  KEY `IdP_idx` (`IdP`),
  KEY `IdU_idx` (`IdU`),
  CONSTRAINT `IdP` FOREIGN KEY (`IdP`) REFERENCES `package` (`IdP`) ON UPDATE CASCADE,
  CONSTRAINT `IdUS` FOREIGN KEY (`IdU`) REFERENCES `user` (`IdU`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subscription_chk_1` CHECK ((`Price` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (2,'2024-06-12 23:20:12',249,1,1),(3,'2024-06-13 00:27:24',549,3,2),(4,'2024-06-14 14:14:16',549,1,2),(5,'2024-06-14 14:14:26',349,5,4),(6,'2024-06-14 14:14:38',2999,4,3);
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `town`
--

DROP TABLE IF EXISTS `town`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `town` (
  `IdT` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`IdT`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `town`
--

LOCK TABLES `town` WRITE;
/*!40000 ALTER TABLE `town` DISABLE KEYS */;
INSERT INTO `town` VALUES (1,'Beograd'),(2,'Novi Sad'),(3,'Bor'),(4,'Kragujevac'),(5,'Nis'),(6,'Kraljevo'),(7,'Kovin');
/*!40000 ALTER TABLE `town` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `IdU` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Lastname` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Year` int NOT NULL,
  `Sex` varchar(1) NOT NULL,
  `IdT` int NOT NULL,
  PRIMARY KEY (`IdU`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `IdT_idx` (`IdT`),
  CONSTRAINT `IdT` FOREIGN KEY (`IdT`) REFERENCES `town` (`IdT`) ON UPDATE CASCADE,
  CONSTRAINT `user_chk_1` CHECK ((`Year` >= 1950)),
  CONSTRAINT `user_chk_2` CHECK (((`Sex` = _utf8mb4'M') or (`Sex` = _utf8mb4'F')))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Jelena','Djuric','djuricjelena611@gmail.com',2001,'F',1),(3,'Kristina','Djoric','djorickristina@gmail.com',1995,'F',7),(4,'Dusan','Kaludjerovic','kaludjerovicd@gmail.com',1999,'M',1),(5,'Ivan','Zuvic','ivanzuvic47@gmail.com',2001,'M',1),(6,'Ena','Grubor','enagrubor01@gmail.com',2001,'F',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `IdV` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Duration` int NOT NULL DEFAULT '0',
  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IdU` int NOT NULL,
  PRIMARY KEY (`IdV`),
  KEY `IdU_idx` (`IdU`),
  CONSTRAINT `IdU` FOREIGN KEY (`IdU`) REFERENCES `user` (`IdU`) ON UPDATE CASCADE,
  CONSTRAINT `video_chk_1` CHECK ((`Duration` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,'Laugh',37,'2024-06-12 10:36:25',1),(4,'Pesma1',190,'2024-06-14 13:54:13',6),(5,'Pesma2',168,'2024-06-14 13:54:31',5),(6,'Strasan video',600,'2024-06-14 13:55:47',4),(7,'Kisa pada',6000,'2024-06-14 13:56:15',1);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watched`
--

DROP TABLE IF EXISTS `watched`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `watched` (
  `IdW` int NOT NULL AUTO_INCREMENT,
  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `StartSecond` int NOT NULL DEFAULT '0',
  `SecondsWatched` int NOT NULL DEFAULT '0',
  `IdU` int NOT NULL,
  `IdV` int NOT NULL,
  PRIMARY KEY (`IdW`),
  KEY `IdU_idx` (`IdU`),
  KEY `IdV_idx` (`IdV`),
  CONSTRAINT `IdUW` FOREIGN KEY (`IdU`) REFERENCES `user` (`IdU`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `IdVW` FOREIGN KEY (`IdV`) REFERENCES `video` (`IdV`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `watched_chk_1` CHECK ((`StartSecond` >= 0)),
  CONSTRAINT `watched_chk_2` CHECK ((`SecondsWatched` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watched`
--

LOCK TABLES `watched` WRITE;
/*!40000 ALTER TABLE `watched` DISABLE KEYS */;
INSERT INTO `watched` VALUES (1,'2024-06-12 23:40:39',5,34,1,1),(2,'2024-06-13 00:33:18',3,45,3,1),(3,'2024-06-14 14:11:37',5,56,3,6),(4,'2024-06-14 14:12:10',3,100,4,5),(5,'2024-06-14 14:12:48',0,15,6,6),(6,'2024-06-14 14:13:13',8,4,3,6),(7,'2024-06-14 14:13:34',0,1150,1,7),(8,'2024-06-14 14:13:50',6,32,5,7);
/*!40000 ALTER TABLE `watched` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-14 14:34:06
