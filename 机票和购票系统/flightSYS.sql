-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: flightsys
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_airplane`
--

DROP TABLE IF EXISTS `t_airplane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_airplane` (
  `A_NO` int(11) NOT NULL,
  `A_MODEL` varchar(20) NOT NULL,
  `A_CAPACITY` int(11) NOT NULL,
  PRIMARY KEY (`A_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_airplane`
--

LOCK TABLES `t_airplane` WRITE;
/*!40000 ALTER TABLE `t_airplane` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_airplane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_flight`
--

DROP TABLE IF EXISTS `t_flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_flight` (
  `F_NO` int(11) NOT NULL,
  `F_A_NO` int(11) NOT NULL,
  `F_START` varchar(25) NOT NULL,
  `F_DIST` varchar(25) NOT NULL,
  `F_PRICE` float NOT NULL,
  `F_TIME` timestamp NOT NULL,
  `F_PSG_NUM` int(11) NOT NULL,
  PRIMARY KEY (`F_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_flight`
--

LOCK TABLES `t_flight` WRITE;
/*!40000 ALTER TABLE `t_flight` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_order` (
  `O_NO` int(11) NOT NULL,
  `O_U_NO` int(11) NOT NULL,
  `O_F_NO` int(11) NOT NULL,
  `O_IS_PAYED` tinyint(1) NOT NULL,
  `O_IS_CANCELED` tinyint(1) NOT NULL,
  `O_TIME` timestamp NOT NULL,
  PRIMARY KEY (`O_NO`),
  KEY `O_U_NO` (`O_U_NO`),
  KEY `O_F_NO` (`O_F_NO`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`O_U_NO`) REFERENCES `t_user` (`U_NO`),
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`O_F_NO`) REFERENCES `t_airplane` (`A_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_user` (
  `U_NO` int(11) NOT NULL,
  `U_USERNAME` varchar(20) NOT NULL,
  `U_PASSWORD` varchar(20) NOT NULL,
  `U_NUCKNAME` varchar(20) NOT NULL,
  `U_ID` varchar(20) NOT NULL,
  `U_TYPE` varchar(20) NOT NULL,
  `U_BALANCE` int(11) NOT NULL,
  PRIMARY KEY (`U_NO`),
  UNIQUE KEY `U_USERNAME` (`U_USERNAME`),
  UNIQUE KEY `U_ID` (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-09 13:17:45
