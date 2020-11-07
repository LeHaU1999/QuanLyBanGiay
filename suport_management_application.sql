-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: suport_management_application
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `bill_detail`
--

DROP TABLE IF EXISTS `bill_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_detail` (
  `id_bill` char(10) NOT NULL,
  `id_product` char(10) NOT NULL,
  `type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  PRIMARY KEY (`id_bill`,`id_product`),
  KEY `fk_bill_products` (`id_product`),
  CONSTRAINT `fk_bill_detail` FOREIGN KEY (`id_bill`) REFERENCES `bills` (`id_bill`),
  CONSTRAINT `fk_bill_products` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_detail`
--

LOCK TABLES `bill_detail` WRITE;
/*!40000 ALTER TABLE `bill_detail` DISABLE KEYS */;
INSERT INTO `bill_detail` VALUES ('HD001','PRDT001','Giày Nam',35,1,649),('HD001','PRDT002','Giày Nữ',35,2,649),('HD001','PRDT003','Giày Nam',40,4,899),('HD001','PRDT004','Giày Nữ',40,8,899),('HD002','PRDT001','Giày Nam',35,2,649),('HD003','PRDT003','Giày Nam',40,1,899),('HD003','PRDT004','Giày Nữ',40,1,899),('HD004','PRDT003','Giày Nam',40,1,899),('HD004','PRDT004','Giày Nữ',40,1,899),('HD005','PRDT003','Giày Nam',40,1,899),('HD005','PRDT004','Giày Nữ',40,1,899);
/*!40000 ALTER TABLE `bill_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bills` (
  `dayBill` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_bill` char(10) NOT NULL,
  `id_customer` char(10) DEFAULT NULL,
  `nameCustomer` varchar(45) NOT NULL,
  `sdt` char(10) DEFAULT NULL,
  `id_employee` char(10) NOT NULL,
  `totalMoney` double DEFAULT NULL,
  PRIMARY KEY (`id_bill`),
  KEY `fk_employees_bill` (`id_employee`),
  CONSTRAINT `fk_employees_bill` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES ('Dec 25, 2019','HD001','KH0001','NGUYEN VAN A','0123456789','EMP003',12735),('Dec 25, 2019','HD002','KH0002','NGUYEN VAN B','0399690875','EMP003',1298),('Dec 25, 2019','HD003','KH0003','NGUYEN VAN C','123456789','EMP003',1798),('Dec 25, 2019','HD004','KH0004','NGUYEN VAN D','123546789','EMP003',1798),('Dec 26, 2019','HD005','KH0004','NGUYEN VAN D','123546789','EMP003',1798);
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id_employee` char(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dayOfBirth` varchar(45) DEFAULT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `startWorkingDay` varchar(45) DEFAULT NULL,
  `position` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('EMP001','Lê Ngọc Hậu','0907630985','Nam','Nov 10, 1999',' Q.Phú Nhuận ,TPHCM','Dec 25, 2019','Quản Trị Viên',20000),('EMP002','Nguyễn Dương','0000000000','Nam','Nov 10, 1999','Q.Phú Nhuận, Tp.HCM','Dec 25, 2019','Quản  Lý',8000),('EMP003','Nguyễn Lan','0000000000','Nam','Nov 10, 1999','Q.Bình Tân, TP.HCM','Dec 25, 2019','Nhân Viên',8000),('EMP004','Nguyễn Bình','12345098','Nam','Dec 5, 1999','Q.10, TPHCM','Dec 24, 2019','Nhân Viên',5000);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producers`
--

DROP TABLE IF EXISTS `producers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producers` (
  `id_producer` char(10) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_producer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producers`
--

LOCK TABLES `producers` WRITE;
/*!40000 ALTER TABLE `producers` DISABLE KEYS */;
INSERT INTO `producers` VALUES ('PDC01','ADIDAS'),('PDC02','NIKE'),('PDC03','PUMA'),('PDC04','Bitis Hunter'),('PDC05','5');
/*!40000 ALTER TABLE `producers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id_product` char(10) NOT NULL,
  `name` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `size` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `unit` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `moneyInput` double DEFAULT NULL,
  `moneyOutput` double DEFAULT NULL,
  `ngayNhap` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_producer` char(10) NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `fk_producers_products` (`id_producer`),
  CONSTRAINT `fk_producers_products` FOREIGN KEY (`id_producer`) REFERENCES `producers` (`id_producer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('PRDT001','Giày Thể Thao M35',35,35,'Giày Nam','Đôi',500,649,'Dec 25, 2019','PDC01'),('PRDT002','Giày Thể Thao F35',35,35,'Giày Nữ','Đôi',500,649,'Dec 25, 2019','PDC01'),('PRDT003','Bitis Hunter X M40',40,40,'Giày Nam','Đôi',699,899,'Dec 25, 2019','PDC01'),('PRDT004','Bitis Hunter X F40',40,40,'Giày Nữ','Đôi',699,899,'Dec 25, 2019','PDC01');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_role` char(10) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('01','ADMIN','Quản TRị Viên'),('02','MANAGER','Quản Lý'),('03','USER','Nhân Viên');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_user` char(10) NOT NULL,
  `id_employee` char(10) NOT NULL,
  `accounts` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passwords` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id_role` char(10) NOT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fk_employees_users` (`id_employee`),
  KEY `fk_roles_users` (`id_role`),
  CONSTRAINT `fk_employees_users` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id_employee`),
  CONSTRAINT `fk_roles_users` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('USER001','EMP001','admin','admin','01'),('USER002','EMP002','manager','manager','02'),('USER003','EMP003','user','user','03');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'suport_management_application'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-26  6:00:57
