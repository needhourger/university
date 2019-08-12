-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: employeesys
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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `EmployeeID` varchar(6) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `Sex` varchar(10) NOT NULL,
  `Branch` varchar(40) NOT NULL,
  `Birthday` date NOT NULL,
  `NativePlace` varchar(20) NOT NULL,
  `Marriage` varchar(20) NOT NULL,
  `IdentityID` varchar(18) NOT NULL,
  `Politics` varchar(20) NOT NULL,
  `Folk` varchar(20) NOT NULL,
  `Education` varchar(20) NOT NULL,
  `Department` varchar(40) NOT NULL,
  `GraduateDate` date NOT NULL,
  `Unversity` varchar(40) NOT NULL,
  `AccumulateID` varchar(20) NOT NULL,
  `AdministrationLevel` varchar(20) NOT NULL,
  `Duty` varchar(40) NOT NULL,
  `Position` varchar(20) NOT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rpmanage`
--

DROP TABLE IF EXISTS `rpmanage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rpmanage` (
  `RewardNO` int(11) NOT NULL,
  `EmployeeID` varchar(6) NOT NULL,
  `Positions` varchar(20) NOT NULL,
  `rewardPunish` varchar(10) NOT NULL,
  `Rewardcontent` varchar(50) NOT NULL,
  `Reason` varchar(100) NOT NULL,
  `Branch` varchar(40) NOT NULL,
  `HandleName` varchar(20) NOT NULL,
  `StartDate` date NOT NULL,
  `CancelDate` date NOT NULL,
  `CancelReason` varchar(100) NOT NULL,
  `Remark` varchar(400) NOT NULL,
  PRIMARY KEY (`RewardNO`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `rpmanage_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rpmanage`
--

LOCK TABLES `rpmanage` WRITE;
/*!40000 ALTER TABLE `rpmanage` DISABLE KEYS */;
/*!40000 ALTER TABLE `rpmanage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `translate`
--

DROP TABLE IF EXISTS `translate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `translate` (
  `TranslateNO` int(11) NOT NULL,
  `EmployeeID` varchar(6) NOT NULL,
  `TranslateDate` date NOT NULL,
  `PriorBranch` varchar(40) NOT NULL,
  `NextBranch` varchar(40) NOT NULL,
  `PriorDuty` varchar(40) NOT NULL,
  `NextDuty` varchar(40) NOT NULL,
  `HandleName` varchar(20) NOT NULL,
  `Remark` varchar(400) NOT NULL,
  PRIMARY KEY (`TranslateNO`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `translate_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `translate`
--

LOCK TABLES `translate` WRITE;
/*!40000 ALTER TABLE `translate` DISABLE KEYS */;
/*!40000 ALTER TABLE `translate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `username` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2019-07-09 12:08:23
