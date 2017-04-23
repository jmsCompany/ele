CREATE DATABASE  IF NOT EXISTS `ele` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ele`;
-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: ele
-- ------------------------------------------------------
-- Server version	5.6.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `id_sub_sub_loc` int(11) DEFAULT NULL,
  `project` varchar(64) DEFAULT NULL,
  `start` timestamp NULL DEFAULT NULL,
  `end` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbq5q8a7etqionbkajy8la810a` (`status`),
  KEY `FKto8l27ro7n4y6sttasnj88cji` (`id_sub_sub_loc`),
  CONSTRAINT `FKbq5q8a7etqionbkajy8la810a` FOREIGN KEY (`status`) REFERENCES `dic` (`id`),
  CONSTRAINT `FKto8l27ro7n4y6sttasnj88cji` FOREIGN KEY (`id_sub_sub_loc`) REFERENCES `sub_sub_location` (`id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`status`) REFERENCES `dic` (`id`),
  CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`id_sub_sub_loc`) REFERENCES `sub_sub_location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_data`
--

DROP TABLE IF EXISTS `customer_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_customer` int(11) DEFAULT NULL,
  `customer_name` varchar(64) DEFAULT NULL,
  `customer_code` varchar(64) DEFAULT NULL,
  `time_duration` varchar(64) DEFAULT NULL,
  `capacity` float DEFAULT NULL,
  `customer_address` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_data`
--

LOCK TABLES `customer_data` WRITE;
/*!40000 ALTER TABLE `customer_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dic`
--

DROP TABLE IF EXISTS `dic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) DEFAULT NULL,
  `descr` varchar(128) DEFAULT NULL,
  `id_parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKopray59cws00swwb8ib8yevbe` (`id_parent`),
  CONSTRAINT `FKopray59cws00swwb8ib8yevbe` FOREIGN KEY (`id_parent`) REFERENCES `dic_dic` (`id`),
  CONSTRAINT `dic_ibfk_1` FOREIGN KEY (`id_parent`) REFERENCES `dic_dic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dic`
--

LOCK TABLES `dic` WRITE;
/*!40000 ALTER TABLE `dic` DISABLE KEYS */;
INSERT INTO `dic` VALUES (1,'项目部','项目部',1),(2,'财务部','财务部1',1);
/*!40000 ALTER TABLE `dic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dic_dic`
--

DROP TABLE IF EXISTS `dic_dic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dic_dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `descr` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dic_dic`
--

LOCK TABLES `dic_dic` WRITE;
/*!40000 ALTER TABLE `dic_dic` DISABLE KEYS */;
INSERT INTO `dic_dic` VALUES (1,'部门','部门');
/*!40000 ALTER TABLE `dic_dic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info`
--

DROP TABLE IF EXISTS `info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `id_project_step` int(11) DEFAULT NULL,
  `c1` varchar(64) DEFAULT NULL,
  `c2` varchar(64) DEFAULT NULL,
  `c3` varchar(64) DEFAULT NULL,
  `c4` varchar(64) DEFAULT NULL,
  `c5` varchar(64) DEFAULT NULL,
  `c6` varchar(64) DEFAULT NULL,
  `c7` varchar(64) DEFAULT NULL,
  `c8` varchar(64) DEFAULT NULL,
  `c9` varchar(64) DEFAULT NULL,
  `c10` varchar(64) DEFAULT NULL,
  `c11` varchar(64) DEFAULT NULL,
  `c12` varchar(64) DEFAULT NULL,
  `c13` varchar(64) DEFAULT NULL,
  `c14` varchar(64) DEFAULT NULL,
  `c15` varchar(64) DEFAULT NULL,
  `c16` varchar(64) DEFAULT NULL,
  `c17` varchar(64) DEFAULT NULL,
  `c18` varchar(64) DEFAULT NULL,
  `c19` varchar(64) DEFAULT NULL,
  `c20` varchar(64) DEFAULT NULL,
  `c21` varchar(64) DEFAULT NULL,
  `c22` varchar(64) DEFAULT NULL,
  `c23` varchar(64) DEFAULT NULL,
  `c24` varchar(64) DEFAULT NULL,
  `c25` varchar(64) DEFAULT NULL,
  `c26` varchar(64) DEFAULT NULL,
  `c27` varchar(64) DEFAULT NULL,
  `c28` varchar(64) DEFAULT NULL,
  `c29` varchar(64) DEFAULT NULL,
  `c30` varchar(64) DEFAULT NULL,
  `c31` varchar(64) DEFAULT NULL,
  `c32` varchar(64) DEFAULT NULL,
  `c33` varchar(64) DEFAULT NULL,
  `c34` varchar(64) DEFAULT NULL,
  `c35` varchar(64) DEFAULT NULL,
  `c36` varchar(64) DEFAULT NULL,
  `c37` varchar(64) DEFAULT NULL,
  `c38` varchar(64) DEFAULT NULL,
  `c39` varchar(64) DEFAULT NULL,
  `c40` varchar(64) DEFAULT NULL,
  `c41` varchar(64) DEFAULT NULL,
  `c42` varchar(64) DEFAULT NULL,
  `c43` varchar(64) DEFAULT NULL,
  `c44` varchar(64) DEFAULT NULL,
  `c45` varchar(64) DEFAULT NULL,
  `c46` varchar(64) DEFAULT NULL,
  `c47` varchar(64) DEFAULT NULL,
  `c48` varchar(64) DEFAULT NULL,
  `c49` varchar(64) DEFAULT NULL,
  `c50` varchar(64) DEFAULT NULL,
  `c51` varchar(64) DEFAULT NULL,
  `c52` varchar(64) DEFAULT NULL,
  `c53` varchar(64) DEFAULT NULL,
  `c54` varchar(64) DEFAULT NULL,
  `c55` varchar(64) DEFAULT NULL,
  `c56` varchar(64) DEFAULT NULL,
  `c57` varchar(64) DEFAULT NULL,
  `c58` varchar(64) DEFAULT NULL,
  `c59` varchar(64) DEFAULT NULL,
  `c60` varchar(64) DEFAULT NULL,
  `c61` varchar(64) DEFAULT NULL,
  `c62` varchar(64) DEFAULT NULL,
  `c63` varchar(64) DEFAULT NULL,
  `c64` varchar(64) DEFAULT NULL,
  `d1` timestamp NULL DEFAULT NULL,
  `d2` timestamp NULL DEFAULT NULL,
  `d3` timestamp NULL DEFAULT NULL,
  `d4` timestamp NULL DEFAULT NULL,
  `d5` timestamp NULL DEFAULT NULL,
  `d6` timestamp NULL DEFAULT NULL,
  `d7` timestamp NULL DEFAULT NULL,
  `d8` timestamp NULL DEFAULT NULL,
  `d9` timestamp NULL DEFAULT NULL,
  `d10` timestamp NULL DEFAULT NULL,
  `d11` timestamp NULL DEFAULT NULL,
  `s1` varchar(64) DEFAULT NULL,
  `s2` varchar(64) DEFAULT NULL,
  `s3` varchar(64) DEFAULT NULL,
  `s4` varchar(64) DEFAULT NULL,
  `s5` varchar(64) DEFAULT NULL,
  `s6` varchar(64) DEFAULT NULL,
  `s7` varchar(64) DEFAULT NULL,
  `s8` varchar(64) DEFAULT NULL,
  `s9` varchar(64) DEFAULT NULL,
  `s10` varchar(64) DEFAULT NULL,
  `s11` varchar(64) DEFAULT NULL,
  `p1` varchar(128) DEFAULT NULL,
  `p2` varchar(128) DEFAULT NULL,
  `p3` varchar(128) DEFAULT NULL,
  `p4` varchar(128) DEFAULT NULL,
  `p5` varchar(128) DEFAULT NULL,
  `p6` varchar(128) DEFAULT NULL,
  `p7` varchar(128) DEFAULT NULL,
  `p8` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaenssecig12myk4vs8jcc68em` (`id_project_step`),
  CONSTRAINT `FKaenssecig12myk4vs8jcc68em` FOREIGN KEY (`id_project_step`) REFERENCES `project_steps` (`id`),
  CONSTRAINT `info_ibfk_1` FOREIGN KEY (`id_project_step`) REFERENCES `project_steps` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info`
--

LOCK TABLES `info` WRITE;
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) DEFAULT NULL,
  `id_customer` int(11) DEFAULT NULL,
  `bank` varchar(64) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `radio` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `amount_permonth` float DEFAULT NULL,
  `payment_time` varchar(20) DEFAULT NULL,
  `unit_price` float DEFAULT NULL,
  `subsidy_price` float DEFAULT NULL,
  `estimate_capacity` float DEFAULT NULL,
  `estimate_income_permonth` float DEFAULT NULL,
  `estimate_income_peryear` float DEFAULT NULL,
  `act_capacity` float DEFAULT NULL,
  `act_income_permonth` float DEFAULT NULL,
  `act_income_peryear` float DEFAULT NULL,
  `recharge` float DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `risk` float DEFAULT NULL,
  `id_project_step` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5k43pf4sfdlqja7bwhiwbr2co` (`id_customer`),
  KEY `FK1830jlm8hispmk6swrlsiylhn` (`id_project_step`),
  CONSTRAINT `FK1830jlm8hispmk6swrlsiylhn` FOREIGN KEY (`id_project_step`) REFERENCES `project_steps` (`id`),
  CONSTRAINT `FK5k43pf4sfdlqja7bwhiwbr2co` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `loan_ibfk_2` FOREIGN KEY (`id_project_step`) REFERENCES `project_steps` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(64) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'北京市',0),(2,'天津市',0),(3,'河北省',0),(4,'山西省',0),(5,'内蒙古自治区',0),(6,'辽宁省',0),(7,'吉林省',0),(8,'黑龙江省',0),(9,'上海市',1),(10,'江苏省',1),(11,'浙江省',0),(12,'安徽省',0),(13,'福建省',0),(14,'江西省',0),(15,'山东省',0),(16,'河南省',0),(17,'湖北省',0),(18,'湖南省',0),(19,'广东省',0),(20,'广西壮族自治区',0),(21,'海南省',0),(22,'重庆市',0),(23,'四川省',0),(24,'贵州省',0),(25,'云南省',0),(26,'西藏自治区',0),(27,'陕西省',0),(28,'甘肃省',0),(29,'青海省',0),(30,'宁夏回族自治区',0),(31,'新疆维吾尔自治区',0),(32,'香港特别行政区',0),(33,'澳门特别行政区',0),(34,'台湾省',0);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `url` varchar(20) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe2p1xajuva4t6f84fboesmul2` (`user`),
  CONSTRAINT `FKe2p1xajuva4t6f84fboesmul2` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noti`
--

DROP TABLE IF EXISTS `noti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `noti` (
  `id` int(11) NOT NULL DEFAULT '0',
  `id_step` int(11) DEFAULT NULL,
  `start` varchar(64) DEFAULT NULL,
  `end` varchar(64) DEFAULT NULL,
  `delay` varchar(64) DEFAULT NULL,
  `delay_more` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj91hprviyv72amt3yomup9qdc` (`id_step`),
  CONSTRAINT `FKj91hprviyv72amt3yomup9qdc` FOREIGN KEY (`id_step`) REFERENCES `steps` (`id`),
  CONSTRAINT `noti_ibfk_1` FOREIGN KEY (`id_step`) REFERENCES `steps` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noti`
--

LOCK TABLES `noti` WRITE;
/*!40000 ALTER TABLE `noti` DISABLE KEYS */;
/*!40000 ALTER TABLE `noti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `descr` varchar(64) DEFAULT NULL,
  `menu_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pages`
--

LOCK TABLES `pages` WRITE;
/*!40000 ALTER TABLE `pages` DISABLE KEYS */;
/*!40000 ALTER TABLE `pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_steps`
--

DROP TABLE IF EXISTS `project_steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_steps` (
  `id` int(11) NOT NULL DEFAULT '0',
  `id_step` int(11) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `forcast_days` int(11) DEFAULT NULL,
  `lasted_days` int(11) DEFAULT NULL,
  `start` timestamp NULL DEFAULT NULL,
  `end` timestamp NULL DEFAULT NULL,
  `act_days` int(11) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `progress` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `id_customer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbpe0l6fo2jx461il4y16vegc9` (`id_customer`),
  KEY `FKi0w9rdcffiiyn6qneo5t505ao` (`department`),
  KEY `FKegjkshefsfd1ibus2wwia2u7f` (`progress`),
  KEY `FKafoo116pky344tcslk2kcjjhx` (`status`),
  KEY `FKntpns5qxhekpsh2fwc48v8vts` (`id_step`),
  KEY `FK68g627xfxs6lwc87leebklfkt` (`user`),
  CONSTRAINT `FK68g627xfxs6lwc87leebklfkt` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  CONSTRAINT `FKafoo116pky344tcslk2kcjjhx` FOREIGN KEY (`status`) REFERENCES `dic` (`id`),
  CONSTRAINT `FKbpe0l6fo2jx461il4y16vegc9` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKegjkshefsfd1ibus2wwia2u7f` FOREIGN KEY (`progress`) REFERENCES `dic` (`id`),
  CONSTRAINT `FKi0w9rdcffiiyn6qneo5t505ao` FOREIGN KEY (`department`) REFERENCES `dic` (`id`),
  CONSTRAINT `FKntpns5qxhekpsh2fwc48v8vts` FOREIGN KEY (`id_step`) REFERENCES `steps` (`id`),
  CONSTRAINT `project_steps_ibfk_1` FOREIGN KEY (`id_step`) REFERENCES `steps` (`id`),
  CONSTRAINT `project_steps_ibfk_2` FOREIGN KEY (`department`) REFERENCES `dic` (`id`),
  CONSTRAINT `project_steps_ibfk_3` FOREIGN KEY (`progress`) REFERENCES `dic` (`id`),
  CONSTRAINT `project_steps_ibfk_4` FOREIGN KEY (`status`) REFERENCES `dic` (`id`),
  CONSTRAINT `project_steps_ibfk_5` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  CONSTRAINT `project_steps_ibfk_6` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_steps`
--

LOCK TABLES `project_steps` WRITE;
/*!40000 ALTER TABLE `project_steps` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_steps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_locations`
--

DROP TABLE IF EXISTS `role_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_locations` (
  `id_role` int(11) NOT NULL,
  `id_sub_sub_location` int(11) NOT NULL,
  `seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_role`,`id_sub_sub_location`),
  KEY `FKmay0ip6fe5rd60uqn257gs1og` (`id_sub_sub_location`),
  CONSTRAINT `FKhg5hoof6nio2mi262gpct4o0p` FOREIGN KEY (`id_role`) REFERENCES `dic` (`id`),
  CONSTRAINT `FKmay0ip6fe5rd60uqn257gs1og` FOREIGN KEY (`id_sub_sub_location`) REFERENCES `sub_sub_location` (`id`),
  CONSTRAINT `role_locations_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `dic` (`id`),
  CONSTRAINT `role_locations_ibfk_2` FOREIGN KEY (`id_sub_sub_location`) REFERENCES `sub_sub_location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_locations`
--

LOCK TABLES `role_locations` WRITE;
/*!40000 ALTER TABLE `role_locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_pages`
--

DROP TABLE IF EXISTS `role_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_pages` (
  `id_role` int(11) NOT NULL,
  `id_page` int(11) NOT NULL,
  `seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_role`,`id_page`),
  KEY `FKl25cmuskjf60lr3ugek9flgj4` (`id_page`),
  CONSTRAINT `FKl25cmuskjf60lr3ugek9flgj4` FOREIGN KEY (`id_page`) REFERENCES `pages` (`id`),
  CONSTRAINT `FKowibt9rywcrdc6udrtmvieof1` FOREIGN KEY (`id_role`) REFERENCES `dic` (`id`),
  CONSTRAINT `role_pages_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `dic` (`id`),
  CONSTRAINT `role_pages_ibfk_2` FOREIGN KEY (`id_page`) REFERENCES `pages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_pages`
--

LOCK TABLES `role_pages` WRITE;
/*!40000 ALTER TABLE `role_pages` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sign_event`
--

DROP TABLE IF EXISTS `sign_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sign_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_event` int(11) DEFAULT NULL,
  `id_sign_workflow_steps` int(11) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `sign_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sign_event`
--

LOCK TABLES `sign_event` WRITE;
/*!40000 ALTER TABLE `sign_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `sign_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sign_workflow`
--

DROP TABLE IF EXISTS `sign_workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sign_workflow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sign_workflow`
--

LOCK TABLES `sign_workflow` WRITE;
/*!40000 ALTER TABLE `sign_workflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `sign_workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sign_workflow_steps`
--

DROP TABLE IF EXISTS `sign_workflow_steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sign_workflow_steps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_sign_workflow` int(11) DEFAULT NULL,
  `lvl` int(11) DEFAULT NULL,
  `content` varchar(64) DEFAULT NULL,
  `id_ signatory` int(11) DEFAULT NULL,
  `id_department` int(11) DEFAULT NULL,
  `id_signatory` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sign_workflow_steps`
--

LOCK TABLES `sign_workflow_steps` WRITE;
/*!40000 ALTER TABLE `sign_workflow_steps` DISABLE KEYS */;
/*!40000 ALTER TABLE `sign_workflow_steps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `so`
--

DROP TABLE IF EXISTS `so`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `so` (
  `id` int(11) NOT NULL DEFAULT '0',
  `id_customer` int(11) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `c1` varchar(64) DEFAULT NULL,
  `c2` varchar(64) DEFAULT NULL,
  `c3` varchar(64) DEFAULT NULL,
  `c4` varchar(64) DEFAULT NULL,
  `c5` varchar(64) DEFAULT NULL,
  `c6` varchar(64) DEFAULT NULL,
  `c7` varchar(64) DEFAULT NULL,
  `c8` varchar(64) DEFAULT NULL,
  `c9` varchar(64) DEFAULT NULL,
  `c10` varchar(64) DEFAULT NULL,
  `c11` varchar(64) DEFAULT NULL,
  `c12` varchar(64) DEFAULT NULL,
  `c13` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa5uxo9s4qwgvu9117jsl3c998` (`id_customer`),
  CONSTRAINT `FKa5uxo9s4qwgvu9117jsl3c998` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `so_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `so`
--

LOCK TABLES `so` WRITE;
/*!40000 ALTER TABLE `so` DISABLE KEYS */;
/*!40000 ALTER TABLE `so` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steps`
--

DROP TABLE IF EXISTS `steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `steps` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `forcast_days` int(11) DEFAULT NULL,
  `lasted_days` int(11) DEFAULT NULL,
  `form` varchar(64) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `start_email` varchar(64) DEFAULT NULL,
  `end_email` varchar(64) DEFAULT NULL,
  `delay_email` varchar(64) DEFAULT NULL,
  `delay_more` varchar(64) DEFAULT NULL,
  `delay_more_email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2nf6cq081a8n8y4n7vvtue4y` (`department`),
  CONSTRAINT `FK2nf6cq081a8n8y4n7vvtue4y` FOREIGN KEY (`department`) REFERENCES `dic` (`id`),
  CONSTRAINT `steps_ibfk_1` FOREIGN KEY (`department`) REFERENCES `dic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steps`
--

LOCK TABLES `steps` WRITE;
/*!40000 ALTER TABLE `steps` DISABLE KEYS */;
/*!40000 ALTER TABLE `steps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_location`
--

DROP TABLE IF EXISTS `sub_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_location` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(64) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `id_location` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK23659cvu7lj9c2d2hak1m2xp8` (`id_location`),
  CONSTRAINT `FK23659cvu7lj9c2d2hak1m2xp8` FOREIGN KEY (`id_location`) REFERENCES `location` (`id`),
  CONSTRAINT `sub_location_ibfk_1` FOREIGN KEY (`id_location`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_location`
--

LOCK TABLES `sub_location` WRITE;
/*!40000 ALTER TABLE `sub_location` DISABLE KEYS */;
INSERT INTO `sub_location` VALUES (1,'北京市',0,1),(2,'天津市',0,2),(3,'石家庄市',0,3),(4,'唐山市',0,3),(5,'秦皇岛市',0,3),(6,'邯郸市',0,3),(7,'邢台市',0,3),(8,'保定市',0,3),(9,'张家口市',0,3),(10,'承德市',0,3),(11,'沧州市',0,3),(12,'廊坊市',0,3),(13,'衡水市',0,3),(14,'太原市',0,4),(15,'大同市',0,4),(16,'阳泉市',0,4),(17,'长治市',0,4),(18,'晋城市',0,4),(19,'朔州市',0,4),(20,'晋中市',0,4),(21,'运城市',0,4),(22,'忻州市',0,4),(23,'临汾市',0,4),(24,'吕梁市',0,4),(25,'呼和浩特市',0,5),(26,'包头市',0,5),(27,'乌海市',0,5),(28,'赤峰市',0,5),(29,'通辽市',0,5),(30,'鄂尔多斯市',0,5),(31,'呼伦贝尔市',0,5),(32,'巴彦淖尔市',0,5),(33,'乌兰察布市',0,5),(34,'兴安盟',0,5),(35,'锡林郭勒盟',0,5),(36,'阿拉善盟',0,5),(37,'沈阳市',0,6),(38,'大连市',0,6),(39,'鞍山市',0,6),(40,'抚顺市',0,6),(41,'本溪市',0,6),(42,'丹东市',0,6),(43,'锦州市',0,6),(44,'营口市',0,6),(45,'阜新市',0,6),(46,'辽阳市',0,6),(47,'盘锦市',0,6),(48,'铁岭市',0,6),(49,'朝阳市',0,6),(50,'葫芦岛市',0,6),(51,'长春市',0,7),(52,'吉林市',0,7),(53,'四平市',0,7),(54,'辽源市',0,7),(55,'通化市',0,7),(56,'白山市',0,7),(57,'松原市',0,7),(58,'白城市',0,7),(59,'延边朝鲜族自治州',0,7),(60,'哈尔滨市',0,8),(61,'齐齐哈尔市',0,8),(62,'鸡西市',0,8),(63,'鹤岗市',0,8),(64,'双鸭山市',0,8),(65,'大庆市',0,8),(66,'伊春市',0,8),(67,'佳木斯市',0,8),(68,'七台河市',0,8),(69,'牡丹江市',0,8),(70,'黑河市',0,8),(71,'绥化市',0,8),(72,'大兴安岭地区',0,8),(73,'上海市',1,9),(74,'南京市',1,10),(75,'无锡市',1,10),(76,'徐州市',1,10),(77,'常州市',1,10),(78,'苏州市',1,10),(79,'南通市',0,10),(80,'连云港市',0,10),(81,'淮安市',0,10),(82,'盐城市',0,10),(83,'扬州市',0,10),(84,'镇江市',0,10),(85,'泰州市',0,10),(86,'宿迁市',0,10),(87,'杭州市',0,11),(88,'宁波市',0,11),(89,'温州市',0,11),(90,'嘉兴市',0,11),(91,'湖州市',0,11),(92,'绍兴市',0,11),(93,'金华市',0,11),(94,'衢州市',0,11),(95,'舟山市',0,11),(96,'台州市',0,11),(97,'丽水市',0,11),(98,'合肥市',0,12),(99,'芜湖市',0,12),(100,'蚌埠市',0,12),(101,'淮南市',0,12),(102,'马鞍山市',0,12),(103,'淮北市',0,12),(104,'铜陵市',0,12),(105,'安庆市',0,12),(106,'黄山市',0,12),(107,'滁州市',0,12),(108,'阜阳市',0,12),(109,'宿州市',0,12),(110,'巢湖市',0,12),(111,'六安市',0,12),(112,'亳州市',0,12),(113,'池州市',0,12),(114,'宣城市',0,12),(115,'福州市',0,13),(116,'厦门市',0,13),(117,'莆田市',0,13),(118,'三明市',0,13),(119,'泉州市',0,13),(120,'漳州市',0,13),(121,'南平市',0,13),(122,'龙岩市',0,13),(123,'宁德市',0,13),(124,'南昌市',0,14),(125,'景德镇市',0,14),(126,'萍乡市',0,14),(127,'九江市',0,14),(128,'新余市',0,14),(129,'鹰潭市',0,14),(130,'赣州市',0,14),(131,'吉安市',0,14),(132,'宜春市',0,14),(133,'抚州市',0,14),(134,'上饶市',0,14),(135,'济南市',0,15),(136,'青岛市',0,15),(137,'淄博市',0,15),(138,'枣庄市',0,15),(139,'东营市',0,15),(140,'烟台市',0,15),(141,'潍坊市',0,15),(142,'济宁市',0,15),(143,'泰安市',0,15),(144,'威海市',0,15),(145,'日照市',0,15),(146,'莱芜市',0,15),(147,'临沂市',0,15),(148,'德州市',0,15),(149,'聊城市',0,15),(150,'滨州市',0,15),(151,'荷泽市',0,15),(152,'郑州市',0,16),(153,'开封市',0,16),(154,'洛阳市',0,16),(155,'平顶山市',0,16),(156,'安阳市',0,16),(157,'鹤壁市',0,16),(158,'新乡市',0,16),(159,'焦作市',0,16),(160,'濮阳市',0,16),(161,'许昌市',0,16),(162,'漯河市',0,16),(163,'三门峡市',0,16),(164,'南阳市',0,16),(165,'商丘市',0,16),(166,'信阳市',0,16),(167,'周口市',0,16),(168,'驻马店市',0,16),(169,'武汉市',0,17),(170,'黄石市',0,17),(171,'十堰市',0,17),(172,'宜昌市',0,17),(173,'襄樊市',0,17),(174,'鄂州市',0,17),(175,'荆门市',0,17),(176,'孝感市',0,17),(177,'荆州市',0,17),(178,'黄冈市',0,17),(179,'咸宁市',0,17),(180,'随州市',0,17),(181,'恩施土家族苗族自治州',0,17),(182,'神农架',0,17),(183,'长沙市',0,18),(184,'株洲市',0,18),(185,'湘潭市',0,18),(186,'衡阳市',0,18),(187,'邵阳市',0,18),(188,'岳阳市',0,18),(189,'常德市',0,18),(190,'张家界市',0,18),(191,'益阳市',0,18),(192,'郴州市',0,18),(193,'永州市',0,18),(194,'怀化市',0,18),(195,'娄底市',0,18),(196,'湘西土家族苗族自治州',0,18),(197,'广州市',0,19),(198,'韶关市',0,19),(199,'深圳市',0,19),(200,'珠海市',0,19),(201,'汕头市',0,19),(202,'佛山市',0,19),(203,'江门市',0,19),(204,'湛江市',0,19),(205,'茂名市',0,19),(206,'肇庆市',0,19),(207,'惠州市',0,19),(208,'梅州市',0,19),(209,'汕尾市',0,19),(210,'河源市',0,19),(211,'阳江市',0,19),(212,'清远市',0,19),(213,'东莞市',0,19),(214,'中山市',0,19),(215,'潮州市',0,19),(216,'揭阳市',0,19),(217,'云浮市',0,19),(218,'南宁市',0,20),(219,'柳州市',0,20),(220,'桂林市',0,20),(221,'梧州市',0,20),(222,'北海市',0,20),(223,'防城港市',0,20),(224,'钦州市',0,20),(225,'贵港市',0,20),(226,'玉林市',0,20),(227,'百色市',0,20),(228,'贺州市',0,20),(229,'河池市',0,20),(230,'来宾市',0,20),(231,'崇左市',0,20),(232,'海口市',0,21),(233,'三亚市',0,21),(234,'重庆市',0,22),(235,'成都市',0,23),(236,'自贡市',0,23),(237,'攀枝花市',0,23),(238,'泸州市',0,23),(239,'德阳市',0,23),(240,'绵阳市',0,23),(241,'广元市',0,23),(242,'遂宁市',0,23),(243,'内江市',0,23),(244,'乐山市',0,23),(245,'南充市',0,23),(246,'眉山市',0,23),(247,'宜宾市',0,23),(248,'广安市',0,23),(249,'达州市',0,23),(250,'雅安市',0,23),(251,'巴中市',0,23),(252,'资阳市',0,23),(253,'阿坝藏族羌族自治州',0,23),(254,'甘孜藏族自治州',0,23),(255,'凉山彝族自治州',0,23),(256,'贵阳市',0,24),(257,'六盘水市',0,24),(258,'遵义市',0,24),(259,'安顺市',0,24),(260,'铜仁地区',0,24),(261,'黔西南布依族苗族自治州',0,24),(262,'毕节地区',0,24),(263,'黔东南苗族侗族自治州',0,24),(264,'黔南布依族苗族自治州',0,24),(265,'昆明市',0,25),(266,'曲靖市',0,25),(267,'玉溪市',0,25),(268,'保山市',0,25),(269,'昭通市',0,25),(270,'丽江市',0,25),(271,'思茅市',0,25),(272,'临沧市',0,25),(273,'楚雄彝族自治州',0,25),(274,'红河哈尼族彝族自治州',0,25),(275,'文山壮族苗族自治州',0,25),(276,'西双版纳傣族自治州',0,25),(277,'大理白族自治州',0,25),(278,'德宏傣族景颇族自治州',0,25),(279,'怒江傈僳族自治州',0,25),(280,'迪庆藏族自治州',0,25),(281,'拉萨市',0,26),(282,'昌都地区',0,26),(283,'山南地区',0,26),(284,'日喀则地区',0,26),(285,'那曲地区',0,26),(286,'阿里地区',0,26),(287,'林芝地区',0,26),(288,'西安市',0,27),(289,'铜川市',0,27),(290,'宝鸡市',0,27),(291,'咸阳市',0,27),(292,'渭南市',0,27),(293,'延安市',0,27),(294,'汉中市',0,27),(295,'榆林市',0,27),(296,'安康市',0,27),(297,'商洛市',0,27),(298,'兰州市',0,28),(299,'嘉峪关市',0,28),(300,'金昌市',0,28),(301,'白银市',0,28),(302,'天水市',0,28),(303,'武威市',0,28),(304,'张掖市',0,28),(305,'平凉市',0,28),(306,'酒泉市',0,28),(307,'庆阳市',0,28),(308,'定西市',0,28),(309,'陇南市',0,28),(310,'临夏回族自治州',0,28),(311,'甘南藏族自治州',0,28),(312,'西宁市',0,29),(313,'海东地区',0,29),(314,'海北藏族自治州',0,29),(315,'黄南藏族自治州',0,29),(316,'海南藏族自治州',0,29),(317,'果洛藏族自治州',0,29),(318,'玉树藏族自治州',0,29),(319,'海西蒙古族藏族自治州',0,29),(320,'银川市',0,30),(321,'石嘴山市',0,30),(322,'吴忠市',0,30),(323,'固原市',0,30),(324,'中卫市',0,30),(325,'乌鲁木齐市',0,31),(326,'克拉玛依市',0,31),(327,'吐鲁番地区',0,31),(328,'哈密地区',0,31),(329,'昌吉回族自治州',0,31),(330,'博尔塔拉蒙古自治州',0,31),(331,'巴音郭楞蒙古自治州',0,31),(332,'阿克苏地区',0,31),(333,'克孜勒苏柯尔克孜自治州',0,31),(334,'喀什地区',0,31),(335,'和田地区',0,31),(336,'伊犁哈萨克自治州',0,31),(337,'塔城地区',0,31),(338,'阿勒泰地区',0,31),(339,'石河子市',0,31),(340,'阿拉尔市',0,31),(341,'图木舒克市',0,31),(342,'五家渠市',0,31),(343,'香港特别行政区',0,32),(344,'澳门特别行政区',0,33),(345,'台湾省',0,34);
/*!40000 ALTER TABLE `sub_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_sub_location`
--

DROP TABLE IF EXISTS `sub_sub_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_sub_location` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(64) DEFAULT NULL,
  `id_sub_loc` int(11) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKseekyjifl9tcc2iyr6wid8n55` (`id_sub_loc`),
  CONSTRAINT `FKseekyjifl9tcc2iyr6wid8n55` FOREIGN KEY (`id_sub_loc`) REFERENCES `sub_location` (`id`),
  CONSTRAINT `sub_sub_location_ibfk_1` FOREIGN KEY (`id_sub_loc`) REFERENCES `sub_location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_sub_location`
--

LOCK TABLES `sub_sub_location` WRITE;
/*!40000 ALTER TABLE `sub_sub_location` DISABLE KEYS */;
INSERT INTO `sub_sub_location` VALUES (1,'东城区',1,0),(2,'西城区',1,0),(3,'崇文区',1,0),(4,'宣武区',1,0),(5,'朝阳区',1,0),(6,'丰台区',1,0),(7,'石景山区',1,0),(8,'海淀区',1,0),(9,'门头沟区',1,0),(10,'房山区',1,0),(11,'通州区',1,0),(12,'顺义区',1,0),(13,'昌平区',1,0),(14,'大兴区',1,0),(15,'怀柔区',1,0),(16,'平谷区',1,0),(17,'密云县',1,0),(18,'延庆县',1,0),(19,'和平区',2,0),(20,'河东区',2,0),(21,'河西区',2,0),(22,'南开区',2,0),(23,'河北区',2,0),(24,'红桥区',2,0),(25,'塘沽区',2,0),(26,'汉沽区',2,0),(27,'大港区',2,0),(28,'东丽区',2,0),(29,'西青区',2,0),(30,'津南区',2,0),(31,'北辰区',2,0),(32,'武清区',2,0),(33,'宝坻区',2,0),(34,'宁河县',2,0),(35,'静海县',2,0),(36,'蓟县',2,0),(37,'长安区',3,0),(38,'桥东区',3,0),(39,'桥西区',3,0),(40,'新华区',3,0),(41,'井陉矿区',3,0),(42,'裕华区',3,0),(43,'井陉县',3,0),(44,'正定县',3,0),(45,'栾城县',3,0),(46,'行唐县',3,0),(47,'灵寿县',3,0),(48,'高邑县',3,0),(49,'深泽县',3,0),(50,'赞皇县',3,0),(51,'无极县',3,0),(52,'平山县',3,0),(53,'元氏县',3,0),(54,'赵县',3,0),(55,'辛集市',3,0),(56,'藁城市',3,0),(57,'晋州市',3,0),(58,'新乐市',3,0),(59,'鹿泉市',3,0),(60,'路南区',4,0),(61,'路北区',4,0),(62,'古冶区',4,0),(63,'开平区',4,0),(64,'丰南区',4,0),(65,'丰润区',4,0),(66,'滦县',4,0),(67,'滦南县',4,0),(68,'乐亭县',4,0),(69,'迁西县',4,0),(70,'玉田县',4,0),(71,'唐海县',4,0),(72,'遵化市',4,0),(73,'迁安市',4,0),(74,'海港区',5,0),(75,'山海关区',5,0),(76,'北戴河区',5,0),(77,'青龙满族自治县',5,0),(78,'昌黎县',5,0),(79,'抚宁县',5,0),(80,'卢龙县',5,0),(81,'邯山区',6,0),(82,'丛台区',6,0),(83,'复兴区',6,0),(84,'峰峰矿区',6,0),(85,'邯郸县',6,0),(86,'临漳县',6,0),(87,'成安县',6,0),(88,'大名县',6,0),(89,'涉县',6,0),(90,'磁县',6,0),(91,'肥乡县',6,0),(92,'永年县',6,0),(93,'邱县',6,0),(94,'鸡泽县',6,0),(95,'广平县',6,0),(96,'馆陶县',6,0),(97,'魏县',6,0),(98,'曲周县',6,0),(99,'武安市',6,0),(100,'桥东区',7,0),(101,'桥西区',7,0),(102,'邢台县',7,0),(103,'临城县',7,0),(104,'内丘县',7,0),(105,'柏乡县',7,0),(106,'隆尧县',7,0),(107,'任县',7,0),(108,'南和县',7,0),(109,'宁晋县',7,0),(110,'巨鹿县',7,0),(111,'新河县',7,0),(112,'广宗县',7,0),(113,'平乡县',7,0),(114,'威县',7,0),(115,'清河县',7,0),(116,'临西县',7,0),(117,'南宫市',7,0),(118,'沙河市',7,0),(119,'新市区',8,0),(120,'北市区',8,0),(121,'南市区',8,0),(122,'满城县',8,0),(123,'清苑县',8,0),(124,'涞水县',8,0),(125,'阜平县',8,0),(126,'徐水县',8,0),(127,'定兴县',8,0),(128,'唐县',8,0),(129,'高阳县',8,0),(130,'容城县',8,0),(131,'涞源县',8,0),(132,'望都县',8,0),(133,'安新县',8,0),(134,'易县',8,0),(135,'曲阳县',8,0),(136,'蠡县',8,0),(137,'顺平县',8,0),(138,'博野县',8,0),(139,'雄县',8,0),(140,'涿州市',8,0),(141,'定州市',8,0),(142,'安国市',8,0),(143,'高碑店市',8,0),(144,'桥东区',9,0),(145,'桥西区',9,0),(146,'宣化区',9,0),(147,'下花园区',9,0),(148,'宣化县',9,0),(149,'张北县',9,0),(150,'康保县',9,0),(151,'沽源县',9,0),(152,'尚义县',9,0),(153,'蔚县',9,0),(154,'阳原县',9,0),(155,'怀安县',9,0),(156,'万全县',9,0),(157,'怀来县',9,0),(158,'涿鹿县',9,0),(159,'赤城县',9,0),(160,'崇礼县',9,0),(161,'双桥区',10,0),(162,'双滦区',10,0),(163,'鹰手营子矿区',10,0),(164,'承德县',10,0),(165,'兴隆县',10,0),(166,'平泉县',10,0),(167,'滦平县',10,0),(168,'隆化县',10,0),(169,'丰宁满族自治县',10,0),(170,'宽城满族自治县',10,0),(171,'围场满族蒙古族自治县',10,0),(172,'新华区',11,0),(173,'运河区',11,0),(174,'沧县',11,0),(175,'青县',11,0),(176,'东光县',11,0),(177,'海兴县',11,0),(178,'盐山县',11,0),(179,'肃宁县',11,0),(180,'南皮县',11,0),(181,'吴桥县',11,0),(182,'献县',11,0),(183,'孟村回族自治县',11,0),(184,'泊头市',11,0),(185,'任丘市',11,0),(186,'黄骅市',11,0),(187,'河间市',11,0),(188,'安次区',12,0),(189,'广阳区',12,0),(190,'固安县',12,0),(191,'永清县',12,0),(192,'香河县',12,0),(193,'大城县',12,0),(194,'文安县',12,0),(195,'大厂回族自治县',12,0),(196,'霸州市',12,0),(197,'三河市',12,0),(198,'桃城区',13,0),(199,'枣强县',13,0),(200,'武邑县',13,0),(201,'武强县',13,0),(202,'饶阳县',13,0),(203,'安平县',13,0),(204,'故城县',13,0),(205,'景县',13,0),(206,'阜城县',13,0),(207,'冀州市',13,0),(208,'深州市',13,0),(209,'小店区',14,0),(210,'迎泽区',14,0),(211,'杏花岭区',14,0),(212,'尖草坪区',14,0),(213,'万柏林区',14,0),(214,'晋源区',14,0),(215,'清徐县',14,0),(216,'阳曲县',14,0),(217,'娄烦县',14,0),(218,'古交市',14,0),(219,'城区',15,0),(220,'矿区',15,0),(221,'南郊区',15,0),(222,'新荣区',15,0),(223,'阳高县',15,0),(224,'天镇县',15,0),(225,'广灵县',15,0),(226,'灵丘县',15,0),(227,'浑源县',15,0),(228,'左云县',15,0),(229,'大同县',15,0),(230,'城区',16,0),(231,'矿区',16,0),(232,'郊区',16,0),(233,'平定县',16,0),(234,'盂县',16,0),(235,'城区',17,0),(236,'郊区',17,0),(237,'长治县',17,0),(238,'襄垣县',17,0),(239,'屯留县',17,0),(240,'平顺县',17,0),(241,'黎城县',17,0),(242,'壶关县',17,0),(243,'长子县',17,0),(244,'武乡县',17,0),(245,'沁县',17,0),(246,'沁源县',17,0),(247,'潞城市',17,0),(248,'城区',18,0),(249,'沁水县',18,0),(250,'阳城县',18,0),(251,'陵川县',18,0),(252,'泽州县',18,0),(253,'高平市',18,0),(254,'朔城区',19,0),(255,'平鲁区',19,0),(256,'山阴县',19,0),(257,'应县',19,0),(258,'右玉县',19,0),(259,'怀仁县',19,0),(260,'榆次区',20,0),(261,'榆社县',20,0),(262,'左权县',20,0),(263,'和顺县',20,0),(264,'昔阳县',20,0),(265,'寿阳县',20,0),(266,'太谷县',20,0),(267,'祁县',20,0),(268,'平遥县',20,0),(269,'灵石县',20,0),(270,'介休市',20,0),(271,'盐湖区',21,0),(272,'临猗县',21,0),(273,'万荣县',21,0),(274,'闻喜县',21,0),(275,'稷山县',21,0),(276,'新绛县',21,0),(277,'绛县',21,0),(278,'垣曲县',21,0),(279,'夏县',21,0),(280,'平陆县',21,0),(281,'芮城县',21,0),(282,'永济市',21,0),(283,'河津市',21,0),(284,'忻府区',22,0),(285,'定襄县',22,0),(286,'五台县',22,0),(287,'代县',22,0),(288,'繁峙县',22,0),(289,'宁武县',22,0),(290,'静乐县',22,0),(291,'神池县',22,0),(292,'五寨县',22,0),(293,'岢岚县',22,0),(294,'河曲县',22,0),(295,'保德县',22,0),(296,'偏关县',22,0),(297,'原平市',22,0),(298,'尧都区',23,0),(299,'曲沃县',23,0),(300,'翼城县',23,0),(301,'襄汾县',23,0),(302,'洪洞县',23,0),(303,'古县',23,0),(304,'安泽县',23,0),(305,'浮山县',23,0),(306,'吉县',23,0),(307,'乡宁县',23,0),(308,'大宁县',23,0),(309,'隰县',23,0),(310,'永和县',23,0),(311,'蒲县',23,0),(312,'汾西县',23,0),(313,'侯马市',23,0),(314,'霍州市',23,0),(315,'离石区',24,0),(316,'文水县',24,0),(317,'交城县',24,0),(318,'兴县',24,0),(319,'临县',24,0),(320,'柳林县',24,0),(321,'石楼县',24,0),(322,'岚县',24,0),(323,'方山县',24,0),(324,'中阳县',24,0),(325,'交口县',24,0),(326,'孝义市',24,0),(327,'汾阳市',24,0),(328,'新城区',25,0),(329,'回民区',25,0),(330,'玉泉区',25,0),(331,'赛罕区',25,0),(332,'土默特左旗',25,0),(333,'托克托县',25,0),(334,'和林格尔县',25,0),(335,'清水河县',25,0),(336,'武川县',25,0),(337,'东河区',26,0),(338,'昆都仑区',26,0),(339,'青山区',26,0),(340,'石拐区',26,0),(341,'白云矿区',26,0),(342,'九原区',26,0),(343,'土默特右旗',26,0),(344,'固阳县',26,0),(345,'达尔罕茂明安联合旗',26,0),(346,'海勃湾区',27,0),(347,'海南区',27,0),(348,'乌达区',27,0),(349,'红山区',28,0),(350,'元宝山区',28,0),(351,'松山区',28,0),(352,'阿鲁科尔沁旗',28,0),(353,'巴林左旗',28,0),(354,'巴林右旗',28,0),(355,'林西县',28,0),(356,'克什克腾旗',28,0),(357,'翁牛特旗',28,0),(358,'喀喇沁旗',28,0),(359,'宁城县',28,0),(360,'敖汉旗',28,0),(361,'科尔沁区',29,0),(362,'科尔沁左翼中旗',29,0),(363,'科尔沁左翼后旗',29,0),(364,'开鲁县',29,0),(365,'库伦旗',29,0),(366,'奈曼旗',29,0),(367,'扎鲁特旗',29,0),(368,'霍林郭勒市',29,0),(369,'东胜区',30,0),(370,'达拉特旗',30,0),(371,'准格尔旗',30,0),(372,'鄂托克前旗',30,0),(373,'鄂托克旗',30,0),(374,'杭锦旗',30,0),(375,'乌审旗',30,0),(376,'伊金霍洛旗',30,0),(377,'海拉尔区',31,0),(378,'阿荣旗',31,0),(379,'莫力达瓦达斡尔族自治旗',31,0),(380,'鄂伦春自治旗',31,0),(381,'鄂温克族自治旗',31,0),(382,'陈巴尔虎旗',31,0),(383,'新巴尔虎左旗',31,0),(384,'新巴尔虎右旗',31,0),(385,'满洲里市',31,0),(386,'牙克石市',31,0),(387,'扎兰屯市',31,0),(388,'额尔古纳市',31,0),(389,'根河市',31,0),(390,'临河区',32,0),(391,'五原县',32,0),(392,'磴口县',32,0),(393,'乌拉特前旗',32,0),(394,'乌拉特中旗',32,0),(395,'乌拉特后旗',32,0),(396,'杭锦后旗',32,0),(397,'集宁区',33,0),(398,'卓资县',33,0),(399,'化德县',33,0),(400,'商都县',33,0),(401,'兴和县',33,0),(402,'凉城县',33,0),(403,'察哈尔右翼前旗',33,0),(404,'察哈尔右翼中旗',33,0),(405,'察哈尔右翼后旗',33,0),(406,'四子王旗',33,0),(407,'丰镇市',33,0),(408,'乌兰浩特市',34,0),(409,'阿尔山市',34,0),(410,'科尔沁右翼前旗',34,0),(411,'科尔沁右翼中旗',34,0),(412,'扎赉特旗',34,0),(413,'突泉县',34,0),(414,'二连浩特市',35,0),(415,'锡林浩特市',35,0),(416,'阿巴嘎旗',35,0),(417,'苏尼特左旗',35,0),(418,'苏尼特右旗',35,0),(419,'东乌珠穆沁旗',35,0),(420,'西乌珠穆沁旗',35,0),(421,'太仆寺旗',35,0),(422,'镶黄旗',35,0),(423,'正镶白旗',35,0),(424,'正蓝旗',35,0),(425,'多伦县',35,0),(426,'阿拉善左旗',36,0),(427,'阿拉善右旗',36,0),(428,'额济纳旗',36,0),(429,'和平区',37,0),(430,'沈河区',37,0),(431,'大东区',37,0),(432,'皇姑区',37,0),(433,'铁西区',37,0),(434,'苏家屯区',37,0),(435,'东陵区',37,0),(436,'新城子区',37,0),(437,'于洪区',37,0),(438,'辽中县',37,0),(439,'康平县',37,0),(440,'法库县',37,0),(441,'新民市',37,0),(442,'中山区',38,0),(443,'西岗区',38,0),(444,'沙河口区',38,0),(445,'甘井子区',38,0),(446,'旅顺口区',38,0),(447,'金州区',38,0),(448,'长海县',38,0),(449,'瓦房店市',38,0),(450,'普兰店市',38,0),(451,'庄河市',38,0),(452,'铁东区',39,0),(453,'铁西区',39,0),(454,'立山区',39,0),(455,'千山区',39,0),(456,'台安县',39,0),(457,'岫岩满族自治县',39,0),(458,'海城市',39,0),(459,'新抚区',40,0),(460,'东洲区',40,0),(461,'望花区',40,0),(462,'顺城区',40,0),(463,'抚顺县',40,0),(464,'新宾满族自治县',40,0),(465,'清原满族自治县',40,0),(466,'平山区',41,0),(467,'溪湖区',41,0),(468,'明山区',41,0),(469,'南芬区',41,0),(470,'本溪满族自治县',41,0),(471,'桓仁满族自治县',41,0),(472,'元宝区',42,0),(473,'振兴区',42,0),(474,'振安区',42,0),(475,'宽甸满族自治县',42,0),(476,'东港市',42,0),(477,'凤城市',42,0),(478,'古塔区',43,0),(479,'凌河区',43,0),(480,'太和区',43,0),(481,'黑山县',43,0),(482,'义县',43,0),(483,'凌海市',43,0),(484,'北宁市',43,0),(485,'站前区',44,0),(486,'西市区',44,0),(487,'鲅鱼圈区',44,0),(488,'老边区',44,0),(489,'盖州市',44,0),(490,'大石桥市',44,0),(491,'海州区',45,0),(492,'新邱区',45,0),(493,'太平区',45,0),(494,'清河门区',45,0),(495,'细河区',45,0),(496,'阜新蒙古族自治县',45,0),(497,'彰武县',45,0),(498,'白塔区',46,0),(499,'文圣区',46,0),(500,'宏伟区',46,0),(501,'弓长岭区',46,0),(502,'太子河区',46,0),(503,'辽阳县',46,0),(504,'灯塔市',46,0),(505,'双台子区',47,0),(506,'兴隆台区',47,0),(507,'大洼县',47,0),(508,'盘山县',47,0),(509,'银州区',48,0),(510,'清河区',48,0),(511,'铁岭县',48,0),(512,'西丰县',48,0),(513,'昌图县',48,0),(514,'调兵山市',48,0),(515,'开原市',48,0),(516,'双塔区',49,0),(517,'龙城区',49,0),(518,'朝阳县',49,0),(519,'建平县',49,0),(520,'喀喇沁左翼蒙古族自治县',49,0),(521,'北票市',49,0),(522,'凌源市',49,0),(523,'连山区',50,0),(524,'龙港区',50,0),(525,'南票区',50,0),(526,'绥中县',50,0),(527,'建昌县',50,0),(528,'兴城市',50,0),(529,'南关区',51,0),(530,'宽城区',51,0),(531,'朝阳区',51,0),(532,'二道区',51,0),(533,'绿园区',51,0),(534,'双阳区',51,0),(535,'农安县',51,0),(536,'九台市',51,0),(537,'榆树市',51,0),(538,'德惠市',51,0),(539,'昌邑区',52,0),(540,'龙潭区',52,0),(541,'船营区',52,0),(542,'丰满区',52,0),(543,'永吉县',52,0),(544,'蛟河市',52,0),(545,'桦甸市',52,0),(546,'舒兰市',52,0),(547,'磐石市',52,0),(548,'铁西区',53,0),(549,'铁东区',53,0),(550,'梨树县',53,0),(551,'伊通满族自治县',53,0),(552,'公主岭市',53,0),(553,'双辽市',53,0),(554,'龙山区',54,0),(555,'西安区',54,0),(556,'东丰县',54,0),(557,'东辽县',54,0),(558,'东昌区',55,0),(559,'二道江区',55,0),(560,'通化县',55,0),(561,'辉南县',55,0),(562,'柳河县',55,0),(563,'梅河口市',55,0),(564,'集安市',55,0),(565,'八道江区',56,0),(566,'抚松县',56,0),(567,'靖宇县',56,0),(568,'长白朝鲜族自治县',56,0),(569,'江源县',56,0),(570,'临江市',56,0),(571,'宁江区',57,0),(572,'前郭尔罗斯蒙古族自治县',57,0),(573,'长岭县',57,0),(574,'乾安县',57,0),(575,'扶余县',57,0),(576,'洮北区',58,0),(577,'镇赉县',58,0),(578,'通榆县',58,0),(579,'洮南市',58,0),(580,'大安市',58,0),(581,'延吉市',59,0),(582,'图们市',59,0),(583,'敦化市',59,0),(584,'珲春市',59,0),(585,'龙井市',59,0),(586,'和龙市',59,0),(587,'汪清县',59,0),(588,'安图县',59,0),(589,'道里区',60,0),(590,'南岗区',60,0),(591,'道外区',60,0),(592,'香坊区',60,0),(593,'动力区',60,0),(594,'平房区',60,0),(595,'松北区',60,0),(596,'呼兰区',60,0),(597,'依兰县',60,0),(598,'方正县',60,0),(599,'宾县',60,0),(600,'巴彦县',60,0),(601,'木兰县',60,0),(602,'通河县',60,0),(603,'延寿县',60,0),(604,'阿城市',60,0),(605,'双城市',60,0),(606,'尚志市',60,0),(607,'五常市',60,0),(608,'龙沙区',61,0),(609,'建华区',61,0),(610,'铁锋区',61,0),(611,'昂昂溪区',61,0),(612,'富拉尔基区',61,0),(613,'碾子山区',61,0),(614,'梅里斯达斡尔族区',61,0),(615,'龙江县',61,0),(616,'依安县',61,0),(617,'泰来县',61,0),(618,'甘南县',61,0),(619,'富裕县',61,0),(620,'克山县',61,0),(621,'克东县',61,0),(622,'拜泉县',61,0),(623,'讷河市',61,0),(624,'鸡冠区',62,0),(625,'恒山区',62,0),(626,'滴道区',62,0),(627,'梨树区',62,0),(628,'城子河区',62,0),(629,'麻山区',62,0),(630,'鸡东县',62,0),(631,'虎林市',62,0),(632,'密山市',62,0),(633,'向阳区',63,0),(634,'工农区',63,0),(635,'南山区',63,0),(636,'兴安区',63,0),(637,'东山区',63,0),(638,'兴山区',63,0),(639,'萝北县',63,0),(640,'绥滨县',63,0),(641,'尖山区',64,0),(642,'岭东区',64,0),(643,'四方台区',64,0),(644,'宝山区',64,0),(645,'集贤县',64,0),(646,'友谊县',64,0),(647,'宝清县',64,0),(648,'饶河县',64,0),(649,'萨尔图区',65,0),(650,'龙凤区',65,0),(651,'让胡路区',65,0),(652,'红岗区',65,0),(653,'大同区',65,0),(654,'肇州县',65,0),(655,'肇源县',65,0),(656,'林甸县',65,0),(657,'杜尔伯特蒙古族自治县',65,0),(658,'伊春区',66,0),(659,'南岔区',66,0),(660,'友好区',66,0),(661,'西林区',66,0),(662,'翠峦区',66,0),(663,'新青区',66,0),(664,'美溪区',66,0),(665,'金山屯区',66,0),(666,'五营区',66,0),(667,'乌马河区',66,0),(668,'汤旺河区',66,0),(669,'带岭区',66,0),(670,'乌伊岭区',66,0),(671,'红星区',66,0),(672,'上甘岭区',66,0),(673,'嘉荫县',66,0),(674,'铁力市',66,0),(675,'永红区',67,0),(676,'向阳区',67,0),(677,'前进区',67,0),(678,'东风区',67,0),(679,'郊区',67,0),(680,'桦南县',67,0),(681,'桦川县',67,0),(682,'汤原县',67,0),(683,'抚远县',67,0),(684,'同江市',67,0),(685,'富锦市',67,0),(686,'新兴区',68,0),(687,'桃山区',68,0),(688,'茄子河区',68,0),(689,'勃利县',68,0),(690,'东安区',69,0),(691,'阳明区',69,0),(692,'爱民区',69,0),(693,'西安区',69,0),(694,'东宁县',69,0),(695,'林口县',69,0),(696,'绥芬河市',69,0),(697,'海林市',69,0),(698,'宁安市',69,0),(699,'穆棱市',69,0),(700,'爱辉区',70,0),(701,'嫩江县',70,0),(702,'逊克县',70,0),(703,'孙吴县',70,0),(704,'北安市',70,0),(705,'五大连池市',70,0),(706,'北林区',71,0),(707,'望奎县',71,0),(708,'兰西县',71,0),(709,'青冈县',71,0),(710,'庆安县',71,0),(711,'明水县',71,0),(712,'绥棱县',71,0),(713,'安达市',71,0),(714,'肇东市',71,0),(715,'海伦市',71,0),(716,'呼玛县',72,0),(717,'塔河县',72,0),(718,'漠河县',72,0),(719,'黄浦区',73,0),(720,'卢湾区',73,0),(721,'徐汇区',73,0),(722,'长宁区',73,0),(723,'静安区',73,0),(724,'普陀区',73,0),(725,'闸北区',73,0),(726,'虹口区',73,0),(727,'杨浦区',73,0),(728,'闵行区',73,0),(729,'宝山区',73,0),(730,'嘉定区',73,0),(731,'浦东新区',73,0),(732,'金山区',73,0),(733,'松江区',73,0),(734,'青浦区',73,0),(735,'南汇区',73,0),(736,'奉贤区',73,0),(737,'崇明县',73,0),(738,'玄武区',74,0),(739,'白下区',74,0),(740,'秦淮区',74,0),(741,'建邺区',74,0),(742,'鼓楼区',74,0),(743,'下关区',74,0),(744,'浦口区',74,0),(745,'栖霞区',74,0),(746,'雨花台区',74,0),(747,'江宁区',74,0),(748,'六合区',74,0),(749,'溧水县',74,0),(750,'高淳县',74,0),(751,'崇安区',75,0),(752,'南长区',75,0),(753,'北塘区',75,0),(754,'锡山区',75,0),(755,'惠山区',75,0),(756,'滨湖区',75,0),(757,'江阴市',75,0),(758,'宜兴市',75,0),(759,'鼓楼区',76,0),(760,'云龙区',76,0),(761,'九里区',76,0),(762,'贾汪区',76,0),(763,'泉山区',76,0),(764,'丰县',76,0),(765,'沛县',76,0),(766,'铜山县',76,0),(767,'睢宁县',76,0),(768,'新沂市',76,0),(769,'邳州市',76,0),(770,'天宁区',77,0),(771,'钟楼区',77,0),(772,'戚墅堰区',77,0),(773,'新北区',77,0),(774,'武进区',77,0),(775,'溧阳市',77,0),(776,'金坛市',77,0),(777,'沧浪区',78,0),(778,'平江区',78,0),(779,'金阊区',78,0),(780,'虎丘区',78,0),(781,'吴中区',78,0),(782,'相城区',78,0),(783,'常熟市',78,0),(784,'张家港市',78,0),(785,'昆山市',78,0),(786,'吴江市',78,0),(787,'太仓市',78,0),(788,'崇川区',79,0),(789,'港闸区',79,0),(790,'海安县',79,0),(791,'如东县',79,0),(792,'启东市',79,0),(793,'如皋市',79,0),(794,'通州市',79,0),(795,'海门市',79,0),(796,'连云区',80,0),(797,'新浦区',80,0),(798,'海州区',80,0),(799,'赣榆县',80,0),(800,'东海县',80,0),(801,'灌云县',80,0),(802,'灌南县',80,0),(803,'清河区',81,0),(804,'楚州区',81,0),(805,'淮阴区',81,0),(806,'清浦区',81,0),(807,'涟水县',81,0),(808,'洪泽县',81,0),(809,'盱眙县',81,0),(810,'金湖县',81,0),(811,'亭湖区',82,0),(812,'盐都区',82,0),(813,'响水县',82,0),(814,'滨海县',82,0),(815,'阜宁县',82,0),(816,'射阳县',82,0),(817,'建湖县',82,0),(818,'东台市',82,0),(819,'大丰市',82,0),(820,'广陵区',83,0),(821,'邗江区',83,0),(822,'维扬区',83,0),(823,'宝应县',83,0),(824,'仪征市',83,0),(825,'高邮市',83,0),(826,'江都市',83,0),(827,'京口区',84,0),(828,'润州区',84,0),(829,'丹徒区',84,0),(830,'丹阳市',84,0),(831,'扬中市',84,0),(832,'句容市',84,0),(833,'海陵区',85,0),(834,'高港区',85,0),(835,'兴化市',85,0),(836,'靖江市',85,0),(837,'泰兴市',85,0),(838,'姜堰市',85,0),(839,'宿城区',86,0),(840,'宿豫区',86,0),(841,'沭阳县',86,0),(842,'泗阳县',86,0),(843,'泗洪县',86,0),(844,'上城区',87,0),(845,'下城区',87,0),(846,'江干区',87,0),(847,'拱墅区',87,0),(848,'西湖区',87,0),(849,'滨江区',87,0),(850,'萧山区',87,0),(851,'余杭区',87,0),(852,'桐庐县',87,0),(853,'淳安县',87,0),(854,'建德市',87,0),(855,'富阳市',87,0),(856,'临安市',87,0),(857,'海曙区',88,0),(858,'江东区',88,0),(859,'江北区',88,0),(860,'北仑区',88,0),(861,'镇海区',88,0),(862,'鄞州区',88,0),(863,'象山县',88,0),(864,'宁海县',88,0),(865,'余姚市',88,0),(866,'慈溪市',88,0),(867,'奉化市',88,0),(868,'鹿城区',89,0),(869,'龙湾区',89,0),(870,'瓯海区',89,0),(871,'洞头县',89,0),(872,'永嘉县',89,0),(873,'平阳县',89,0),(874,'苍南县',89,0),(875,'文成县',89,0),(876,'泰顺县',89,0),(877,'瑞安市',89,0),(878,'乐清市',89,0),(879,'秀城区',90,0),(880,'秀洲区',90,0),(881,'嘉善县',90,0),(882,'海盐县',90,0),(883,'海宁市',90,0),(884,'平湖市',90,0),(885,'桐乡市',90,0),(886,'吴兴区',91,0),(887,'南浔区',91,0),(888,'德清县',91,0),(889,'长兴县',91,0),(890,'安吉县',91,0),(891,'越城区',92,0),(892,'绍兴县',92,0),(893,'新昌县',92,0),(894,'诸暨市',92,0),(895,'上虞市',92,0),(896,'嵊州市',92,0),(897,'婺城区',93,0),(898,'金东区',93,0),(899,'武义县',93,0),(900,'浦江县',93,0),(901,'磐安县',93,0),(902,'兰溪市',93,0),(903,'义乌市',93,0),(904,'东阳市',93,0),(905,'永康市',93,0),(906,'柯城区',94,0),(907,'衢江区',94,0),(908,'常山县',94,0),(909,'开化县',94,0),(910,'龙游县',94,0),(911,'江山市',94,0),(912,'定海区',95,0),(913,'普陀区',95,0),(914,'岱山县',95,0),(915,'嵊泗县',95,0),(916,'椒江区',96,0),(917,'黄岩区',96,0),(918,'路桥区',96,0),(919,'玉环县',96,0),(920,'三门县',96,0),(921,'天台县',96,0),(922,'仙居县',96,0),(923,'温岭市',96,0),(924,'临海市',96,0),(925,'莲都区',97,0),(926,'青田县',97,0),(927,'缙云县',97,0),(928,'遂昌县',97,0),(929,'松阳县',97,0),(930,'云和县',97,0),(931,'庆元县',97,0),(932,'景宁畲族自治县',97,0),(933,'龙泉市',97,0),(934,'瑶海区',98,0),(935,'庐阳区',98,0),(936,'蜀山区',98,0),(937,'包河区',98,0),(938,'长丰县',98,0),(939,'肥东县',98,0),(940,'肥西县',98,0),(941,'镜湖区',99,0),(942,'马塘区',99,0),(943,'新芜区',99,0),(944,'鸠江区',99,0),(945,'芜湖县',99,0),(946,'繁昌县',99,0),(947,'南陵县',99,0),(948,'龙子湖区',100,0),(949,'蚌山区',100,0),(950,'禹会区',100,0),(951,'淮上区',100,0),(952,'怀远县',100,0),(953,'五河县',100,0),(954,'固镇县',100,0),(955,'大通区',101,0),(956,'田家庵区',101,0),(957,'谢家集区',101,0),(958,'八公山区',101,0),(959,'潘集区',101,0),(960,'凤台县',101,0),(961,'金家庄区',102,0),(962,'花山区',102,0),(963,'雨山区',102,0),(964,'当涂县',102,0),(965,'杜集区',103,0),(966,'相山区',103,0),(967,'烈山区',103,0),(968,'濉溪县',103,0),(969,'铜官山区',104,0),(970,'狮子山区',104,0),(971,'郊区',104,0),(972,'铜陵县',104,0),(973,'迎江区',105,0),(974,'大观区',105,0),(975,'郊区',105,0),(976,'怀宁县',105,0),(977,'枞阳县',105,0),(978,'潜山县',105,0),(979,'太湖县',105,0),(980,'宿松县',105,0),(981,'望江县',105,0),(982,'岳西县',105,0),(983,'桐城市',105,0),(984,'屯溪区',106,0),(985,'黄山区',106,0),(986,'徽州区',106,0),(987,'歙县',106,0),(988,'休宁县',106,0),(989,'黟县',106,0),(990,'祁门县',106,0),(991,'琅琊区',107,0),(992,'南谯区',107,0),(993,'来安县',107,0),(994,'全椒县',107,0),(995,'定远县',107,0),(996,'凤阳县',107,0),(997,'天长市',107,0),(998,'明光市',107,0),(999,'颍州区',108,0),(1000,'颍东区',108,0),(1001,'颍泉区',108,0),(1002,'临泉县',108,0),(1003,'太和县',108,0),(1004,'阜南县',108,0),(1005,'颍上县',108,0),(1006,'界首市',108,0),(1007,'埇桥区',109,0),(1008,'砀山县',109,0),(1009,'萧县',109,0),(1010,'灵璧县',109,0),(1011,'泗县',109,0),(1012,'居巢区',110,0),(1013,'庐江县',110,0),(1014,'无为县',110,0),(1015,'含山县',110,0),(1016,'和县',110,0),(1017,'金安区',111,0),(1018,'裕安区',111,0),(1019,'寿县',111,0),(1020,'霍邱县',111,0),(1021,'舒城县',111,0),(1022,'金寨县',111,0),(1023,'霍山县',111,0),(1024,'谯城区',112,0),(1025,'涡阳县',112,0),(1026,'蒙城县',112,0),(1027,'利辛县',112,0),(1028,'贵池区',113,0),(1029,'东至县',113,0),(1030,'石台县',113,0),(1031,'青阳县',113,0),(1032,'宣州区',114,0),(1033,'郎溪县',114,0),(1034,'广德县',114,0),(1035,'泾县',114,0),(1036,'绩溪县',114,0),(1037,'旌德县',114,0),(1038,'宁国市',114,0),(1039,'鼓楼区',115,0),(1040,'台江区',115,0),(1041,'仓山区',115,0),(1042,'马尾区',115,0),(1043,'晋安区',115,0),(1044,'闽侯县',115,0),(1045,'连江县',115,0),(1046,'罗源县',115,0),(1047,'闽清县',115,0),(1048,'永泰县',115,0),(1049,'平潭县',115,0),(1050,'福清市',115,0),(1051,'长乐市',115,0),(1052,'思明区',116,0),(1053,'海沧区',116,0),(1054,'湖里区',116,0),(1055,'集美区',116,0),(1056,'同安区',116,0),(1057,'翔安区',116,0),(1058,'城厢区',117,0),(1059,'涵江区',117,0),(1060,'荔城区',117,0),(1061,'秀屿区',117,0),(1062,'仙游县',117,0),(1063,'梅列区',118,0),(1064,'三元区',118,0),(1065,'明溪县',118,0),(1066,'清流县',118,0),(1067,'宁化县',118,0),(1068,'大田县',118,0),(1069,'尤溪县',118,0),(1070,'沙县',118,0),(1071,'将乐县',118,0),(1072,'泰宁县',118,0),(1073,'建宁县',118,0),(1074,'永安市',118,0),(1075,'鲤城区',119,0),(1076,'丰泽区',119,0),(1077,'洛江区',119,0),(1078,'泉港区',119,0),(1079,'惠安县',119,0),(1080,'安溪县',119,0),(1081,'永春县',119,0),(1082,'德化县',119,0),(1083,'金门县',119,0),(1084,'石狮市',119,0),(1085,'晋江市',119,0),(1086,'南安市',119,0),(1087,'芗城区',120,0),(1088,'龙文区',120,0),(1089,'云霄县',120,0),(1090,'漳浦县',120,0),(1091,'诏安县',120,0),(1092,'长泰县',120,0),(1093,'东山县',120,0),(1094,'南靖县',120,0),(1095,'平和县',120,0),(1096,'华安县',120,0),(1097,'龙海市',120,0),(1098,'延平区',121,0),(1099,'顺昌县',121,0),(1100,'浦城县',121,0),(1101,'光泽县',121,0),(1102,'松溪县',121,0),(1103,'政和县',121,0),(1104,'邵武市',121,0),(1105,'武夷山市',121,0),(1106,'建瓯市',121,0),(1107,'建阳市',121,0),(1108,'新罗区',122,0),(1109,'长汀县',122,0),(1110,'永定县',122,0),(1111,'上杭县',122,0),(1112,'武平县',122,0),(1113,'连城县',122,0),(1114,'漳平市',122,0),(1115,'蕉城区',123,0),(1116,'霞浦县',123,0),(1117,'古田县',123,0),(1118,'屏南县',123,0),(1119,'寿宁县',123,0),(1120,'周宁县',123,0),(1121,'柘荣县',123,0),(1122,'福安市',123,0),(1123,'福鼎市',123,0),(1124,'东湖区',124,0),(1125,'西湖区',124,0),(1126,'青云谱区',124,0),(1127,'湾里区',124,0),(1128,'青山湖区',124,0),(1129,'南昌县',124,0),(1130,'新建县',124,0),(1131,'安义县',124,0),(1132,'进贤县',124,0),(1133,'昌江区',125,0),(1134,'珠山区',125,0),(1135,'浮梁县',125,0),(1136,'乐平市',125,0),(1137,'安源区',126,0),(1138,'湘东区',126,0),(1139,'莲花县',126,0),(1140,'上栗县',126,0),(1141,'芦溪县',126,0),(1142,'庐山区',127,0),(1143,'浔阳区',127,0),(1144,'九江县',127,0),(1145,'武宁县',127,0),(1146,'修水县',127,0),(1147,'永修县',127,0),(1148,'德安县',127,0),(1149,'星子县',127,0),(1150,'都昌县',127,0),(1151,'湖口县',127,0),(1152,'彭泽县',127,0),(1153,'瑞昌市',127,0),(1154,'渝水区',128,0),(1155,'分宜县',128,0),(1156,'月湖区',129,0),(1157,'余江县',129,0),(1158,'贵溪市',129,0),(1159,'章贡区',130,0),(1160,'赣县',130,0),(1161,'信丰县',130,0),(1162,'大余县',130,0),(1163,'上犹县',130,0),(1164,'崇义县',130,0),(1165,'安远县',130,0),(1166,'龙南县',130,0),(1167,'定南县',130,0),(1168,'全南县',130,0),(1169,'宁都县',130,0),(1170,'于都县',130,0),(1171,'兴国县',130,0),(1172,'会昌县',130,0),(1173,'寻乌县',130,0),(1174,'石城县',130,0),(1175,'瑞金市',130,0),(1176,'南康市',130,0),(1177,'吉州区',131,0),(1178,'青原区',131,0),(1179,'吉安县',131,0),(1180,'吉水县',131,0),(1181,'峡江县',131,0),(1182,'新干县',131,0),(1183,'永丰县',131,0),(1184,'泰和县',131,0),(1185,'遂川县',131,0),(1186,'万安县',131,0),(1187,'安福县',131,0),(1188,'永新县',131,0),(1189,'井冈山市',131,0),(1190,'袁州区',132,0),(1191,'奉新县',132,0),(1192,'万载县',132,0),(1193,'上高县',132,0),(1194,'宜丰县',132,0),(1195,'靖安县',132,0),(1196,'铜鼓县',132,0),(1197,'丰城市',132,0),(1198,'樟树市',132,0),(1199,'高安市',132,0),(1200,'临川区',133,0),(1201,'南城县',133,0),(1202,'黎川县',133,0),(1203,'南丰县',133,0),(1204,'崇仁县',133,0),(1205,'乐安县',133,0),(1206,'宜黄县',133,0),(1207,'金溪县',133,0),(1208,'资溪县',133,0),(1209,'东乡县',133,0),(1210,'广昌县',133,0),(1211,'信州区',134,0),(1212,'上饶县',134,0),(1213,'广丰县',134,0),(1214,'玉山县',134,0),(1215,'铅山县',134,0),(1216,'横峰县',134,0),(1217,'弋阳县',134,0),(1218,'余干县',134,0),(1219,'鄱阳县',134,0),(1220,'万年县',134,0),(1221,'婺源县',134,0),(1222,'德兴市',134,0),(1223,'历下区',135,0),(1224,'市中区',135,0),(1225,'槐荫区',135,0),(1226,'天桥区',135,0),(1227,'历城区',135,0),(1228,'长清区',135,0),(1229,'平阴县',135,0),(1230,'济阳县',135,0),(1231,'商河县',135,0),(1232,'章丘市',135,0),(1233,'市南区',136,0),(1234,'市北区',136,0),(1235,'四方区',136,0),(1236,'黄岛区',136,0),(1237,'崂山区',136,0),(1238,'李沧区',136,0),(1239,'城阳区',136,0),(1240,'胶州市',136,0),(1241,'即墨市',136,0),(1242,'平度市',136,0),(1243,'胶南市',136,0),(1244,'莱西市',136,0),(1245,'淄川区',137,0),(1246,'张店区',137,0),(1247,'博山区',137,0),(1248,'临淄区',137,0),(1249,'周村区',137,0),(1250,'桓台县',137,0),(1251,'高青县',137,0),(1252,'沂源县',137,0),(1253,'市中区',138,0),(1254,'薛城区',138,0),(1255,'峄城区',138,0),(1256,'台儿庄区',138,0),(1257,'山亭区',138,0),(1258,'滕州市',138,0),(1259,'东营区',139,0),(1260,'河口区',139,0),(1261,'垦利县',139,0),(1262,'利津县',139,0),(1263,'广饶县',139,0),(1264,'芝罘区',140,0),(1265,'福山区',140,0),(1266,'牟平区',140,0),(1267,'莱山区',140,0),(1268,'长岛县',140,0),(1269,'龙口市',140,0),(1270,'莱阳市',140,0),(1271,'莱州市',140,0),(1272,'蓬莱市',140,0),(1273,'招远市',140,0),(1274,'栖霞市',140,0),(1275,'海阳市',140,0),(1276,'潍城区',141,0),(1277,'寒亭区',141,0),(1278,'坊子区',141,0),(1279,'奎文区',141,0),(1280,'临朐县',141,0),(1281,'昌乐县',141,0),(1282,'青州市',141,0),(1283,'诸城市',141,0),(1284,'寿光市',141,0),(1285,'安丘市',141,0),(1286,'高密市',141,0),(1287,'昌邑市',141,0),(1288,'市中区',142,0),(1289,'任城区',142,0),(1290,'微山县',142,0),(1291,'鱼台县',142,0),(1292,'金乡县',142,0),(1293,'嘉祥县',142,0),(1294,'汶上县',142,0),(1295,'泗水县',142,0),(1296,'梁山县',142,0),(1297,'曲阜市',142,0),(1298,'兖州市',142,0),(1299,'邹城市',142,0),(1300,'泰山区',143,0),(1301,'岱岳区',143,0),(1302,'宁阳县',143,0),(1303,'东平县',143,0),(1304,'新泰市',143,0),(1305,'肥城市',143,0),(1306,'环翠区',144,0),(1307,'文登市',144,0),(1308,'荣成市',144,0),(1309,'乳山市',144,0),(1310,'东港区',145,0),(1311,'岚山区',145,0),(1312,'五莲县',145,0),(1313,'莒县',145,0),(1314,'莱城区',146,0),(1315,'钢城区',146,0),(1316,'兰山区',147,0),(1317,'罗庄区',147,0),(1318,'河东区',147,0),(1319,'沂南县',147,0),(1320,'郯城县',147,0),(1321,'沂水县',147,0),(1322,'苍山县',147,0),(1323,'费县',147,0),(1324,'平邑县',147,0),(1325,'莒南县',147,0),(1326,'蒙阴县',147,0),(1327,'临沭县',147,0),(1328,'德城区',148,0),(1329,'陵县',148,0),(1330,'宁津县',148,0),(1331,'庆云县',148,0),(1332,'临邑县',148,0),(1333,'齐河县',148,0),(1334,'平原县',148,0),(1335,'夏津县',148,0),(1336,'武城县',148,0),(1337,'乐陵市',148,0),(1338,'禹城市',148,0),(1339,'东昌府区',149,0),(1340,'阳谷县',149,0),(1341,'莘县',149,0),(1342,'茌平县',149,0),(1343,'东阿县',149,0),(1344,'冠县',149,0),(1345,'高唐县',149,0),(1346,'临清市',149,0),(1347,'滨城区',150,0),(1348,'惠民县',150,0),(1349,'阳信县',150,0),(1350,'无棣县',150,0),(1351,'沾化县',150,0),(1352,'博兴县',150,0),(1353,'邹平县',150,0),(1354,'牡丹区',151,0),(1355,'曹县',151,0),(1356,'单县',151,0),(1357,'成武县',151,0),(1358,'巨野县',151,0),(1359,'郓城县',151,0),(1360,'鄄城县',151,0),(1361,'定陶县',151,0),(1362,'东明县',151,0),(1363,'中原区',152,0),(1364,'二七区',152,0),(1365,'管城回族区',152,0),(1366,'金水区',152,0),(1367,'上街区',152,0),(1368,'惠济区',152,0),(1369,'中牟县',152,0),(1370,'巩义市',152,0),(1371,'荥阳市',152,0),(1372,'新密市',152,0),(1373,'新郑市',152,0),(1374,'登封市',152,0),(1375,'龙亭区',153,0),(1376,'顺河回族区',153,0),(1377,'鼓楼区',153,0),(1378,'南关区',153,0),(1379,'郊区',153,0),(1380,'杞县',153,0),(1381,'通许县',153,0),(1382,'尉氏县',153,0),(1383,'开封县',153,0),(1384,'兰考县',153,0),(1385,'老城区',154,0),(1386,'西工区',154,0),(1387,'廛河回族区',154,0),(1388,'涧西区',154,0),(1389,'吉利区',154,0),(1390,'洛龙区',154,0),(1391,'孟津县',154,0),(1392,'新安县',154,0),(1393,'栾川县',154,0),(1394,'嵩县',154,0),(1395,'汝阳县',154,0),(1396,'宜阳县',154,0),(1397,'洛宁县',154,0),(1398,'伊川县',154,0),(1399,'偃师市',154,0),(1400,'新华区',155,0),(1401,'卫东区',155,0),(1402,'石龙区',155,0),(1403,'湛河区',155,0),(1404,'宝丰县',155,0),(1405,'叶县',155,0),(1406,'鲁山县',155,0),(1407,'郏县',155,0),(1408,'舞钢市',155,0),(1409,'汝州市',155,0),(1410,'文峰区',156,0),(1411,'北关区',156,0),(1412,'殷都区',156,0),(1413,'龙安区',156,0),(1414,'安阳县',156,0),(1415,'汤阴县',156,0),(1416,'滑县',156,0),(1417,'内黄县',156,0),(1418,'林州市',156,0),(1419,'鹤山区',157,0),(1420,'山城区',157,0),(1421,'淇滨区',157,0),(1422,'浚县',157,0),(1423,'淇县',157,0),(1424,'红旗区',158,0),(1425,'卫滨区',158,0),(1426,'凤泉区',158,0),(1427,'牧野区',158,0),(1428,'新乡县',158,0),(1429,'获嘉县',158,0),(1430,'原阳县',158,0),(1431,'延津县',158,0),(1432,'封丘县',158,0),(1433,'长垣县',158,0),(1434,'卫辉市',158,0),(1435,'辉县市',158,0),(1436,'解放区',159,0),(1437,'中站区',159,0),(1438,'马村区',159,0),(1439,'山阳区',159,0),(1440,'修武县',159,0),(1441,'博爱县',159,0),(1442,'武陟县',159,0),(1443,'温县',159,0),(1444,'济源市',159,0),(1445,'沁阳市',159,0),(1446,'孟州市',159,0),(1447,'华龙区',160,0),(1448,'清丰县',160,0),(1449,'南乐县',160,0),(1450,'范县',160,0),(1451,'台前县',160,0),(1452,'濮阳县',160,0),(1453,'魏都区',161,0),(1454,'许昌县',161,0),(1455,'鄢陵县',161,0),(1456,'襄城县',161,0),(1457,'禹州市',161,0),(1458,'长葛市',161,0),(1459,'源汇区',162,0),(1460,'郾城区',162,0),(1461,'召陵区',162,0),(1462,'舞阳县',162,0),(1463,'临颍县',162,0),(1464,'市辖区',163,0),(1465,'湖滨区',163,0),(1466,'渑池县',163,0),(1467,'陕县',163,0),(1468,'卢氏县',163,0),(1469,'义马市',163,0),(1470,'灵宝市',163,0),(1471,'宛城区',164,0),(1472,'卧龙区',164,0),(1473,'南召县',164,0),(1474,'方城县',164,0),(1475,'西峡县',164,0),(1476,'镇平县',164,0),(1477,'内乡县',164,0),(1478,'淅川县',164,0),(1479,'社旗县',164,0),(1480,'唐河县',164,0),(1481,'新野县',164,0),(1482,'桐柏县',164,0),(1483,'邓州市',164,0),(1484,'梁园区',165,0),(1485,'睢阳区',165,0),(1486,'民权县',165,0),(1487,'睢县',165,0),(1488,'宁陵县',165,0),(1489,'柘城县',165,0),(1490,'虞城县',165,0),(1491,'夏邑县',165,0),(1492,'永城市',165,0),(1493,'浉河区',166,0),(1494,'平桥区',166,0),(1495,'罗山县',166,0),(1496,'光山县',166,0),(1497,'新县',166,0),(1498,'商城县',166,0),(1499,'固始县',166,0),(1500,'潢川县',166,0),(1501,'淮滨县',166,0),(1502,'息县',166,0),(1503,'川汇区',167,0),(1504,'扶沟县',167,0),(1505,'西华县',167,0),(1506,'商水县',167,0),(1507,'沈丘县',167,0),(1508,'郸城县',167,0),(1509,'淮阳县',167,0),(1510,'太康县',167,0),(1511,'鹿邑县',167,0),(1512,'项城市',167,0),(1513,'驿城区',168,0),(1514,'西平县',168,0),(1515,'上蔡县',168,0),(1516,'平舆县',168,0),(1517,'正阳县',168,0),(1518,'确山县',168,0),(1519,'泌阳县',168,0),(1520,'汝南县',168,0),(1521,'遂平县',168,0),(1522,'新蔡县',168,0),(1523,'江岸区',169,0),(1524,'江汉区',169,0),(1525,'硚口区',169,0),(1526,'汉阳区',169,0),(1527,'武昌区',169,0),(1528,'青山区',169,0),(1529,'洪山区',169,0),(1530,'东西湖区',169,0),(1531,'汉南区',169,0),(1532,'蔡甸区',169,0),(1533,'江夏区',169,0),(1534,'黄陂区',169,0),(1535,'新洲区',169,0),(1536,'黄石港区',170,0),(1537,'西塞山区',170,0),(1538,'下陆区',170,0),(1539,'铁山区',170,0),(1540,'阳新县',170,0),(1541,'大冶市',170,0),(1542,'茅箭区',171,0),(1543,'张湾区',171,0),(1544,'郧县',171,0),(1545,'郧西县',171,0),(1546,'竹山县',171,0),(1547,'竹溪县',171,0),(1548,'房县',171,0),(1549,'丹江口市',171,0),(1550,'西陵区',172,0),(1551,'伍家岗区',172,0),(1552,'点军区',172,0),(1553,'猇亭区',172,0),(1554,'夷陵区',172,0),(1555,'远安县',172,0),(1556,'兴山县',172,0),(1557,'秭归县',172,0),(1558,'长阳土家族自治县',172,0),(1559,'五峰土家族自治县',172,0),(1560,'宜都市',172,0),(1561,'当阳市',172,0),(1562,'枝江市',172,0),(1563,'襄城区',173,0),(1564,'樊城区',173,0),(1565,'襄阳区',173,0),(1566,'南漳县',173,0),(1567,'谷城县',173,0),(1568,'保康县',173,0),(1569,'老河口市',173,0),(1570,'枣阳市',173,0),(1571,'宜城市',173,0),(1572,'梁子湖区',174,0),(1573,'华容区',174,0),(1574,'鄂城区',174,0),(1575,'东宝区',175,0),(1576,'掇刀区',175,0),(1577,'京山县',175,0),(1578,'沙洋县',175,0),(1579,'钟祥市',175,0),(1580,'孝南区',176,0),(1581,'孝昌县',176,0),(1582,'大悟县',176,0),(1583,'云梦县',176,0),(1584,'应城市',176,0),(1585,'安陆市',176,0),(1586,'汉川市',176,0),(1587,'沙市区',177,0),(1588,'荆州区',177,0),(1589,'公安县',177,0),(1590,'监利县',177,0),(1591,'江陵县',177,0),(1592,'石首市',177,0),(1593,'洪湖市',177,0),(1594,'松滋市',177,0),(1595,'黄州区',178,0),(1596,'团风县',178,0),(1597,'红安县',178,0),(1598,'罗田县',178,0),(1599,'英山县',178,0),(1600,'浠水县',178,0),(1601,'蕲春县',178,0),(1602,'黄梅县',178,0),(1603,'麻城市',178,0),(1604,'武穴市',178,0),(1605,'咸安区',179,0),(1606,'嘉鱼县',179,0),(1607,'通城县',179,0),(1608,'崇阳县',179,0),(1609,'通山县',179,0),(1610,'赤壁市',179,0),(1611,'曾都区',180,0),(1612,'广水市',180,0),(1613,'恩施市',181,0),(1614,'利川市',181,0),(1615,'建始县',181,0),(1616,'巴东县',181,0),(1617,'宣恩县',181,0),(1618,'咸丰县',181,0),(1619,'来凤县',181,0),(1620,'鹤峰县',181,0),(1621,'仙桃市',182,0),(1622,'潜江市',182,0),(1623,'天门市',182,0),(1624,'神农架林区',182,0),(1625,'芙蓉区',183,0),(1626,'天心区',183,0),(1627,'岳麓区',183,0),(1628,'开福区',183,0),(1629,'雨花区',183,0),(1630,'长沙县',183,0),(1631,'望城县',183,0),(1632,'宁乡县',183,0),(1633,'浏阳市',183,0),(1634,'荷塘区',184,0),(1635,'芦淞区',184,0),(1636,'石峰区',184,0),(1637,'天元区',184,0),(1638,'株洲县',184,0),(1639,'攸县',184,0),(1640,'茶陵县',184,0),(1641,'炎陵县',184,0),(1642,'醴陵市',184,0),(1643,'雨湖区',185,0),(1644,'岳塘区',185,0),(1645,'湘潭县',185,0),(1646,'湘乡市',185,0),(1647,'韶山市',185,0),(1648,'珠晖区',186,0),(1649,'雁峰区',186,0),(1650,'石鼓区',186,0),(1651,'蒸湘区',186,0),(1652,'南岳区',186,0),(1653,'衡阳县',186,0),(1654,'衡南县',186,0),(1655,'衡山县',186,0),(1656,'衡东县',186,0),(1657,'祁东县',186,0),(1658,'耒阳市',186,0),(1659,'常宁市',186,0),(1660,'双清区',187,0),(1661,'大祥区',187,0),(1662,'北塔区',187,0),(1663,'邵东县',187,0),(1664,'新邵县',187,0),(1665,'邵阳县',187,0),(1666,'隆回县',187,0),(1667,'洞口县',187,0),(1668,'绥宁县',187,0),(1669,'新宁县',187,0),(1670,'城步苗族自治县',187,0),(1671,'武冈市',187,0),(1672,'岳阳楼区',188,0),(1673,'云溪区',188,0),(1674,'君山区',188,0),(1675,'岳阳县',188,0),(1676,'华容县',188,0),(1677,'湘阴县',188,0),(1678,'平江县',188,0),(1679,'汨罗市',188,0),(1680,'临湘市',188,0),(1681,'武陵区',189,0),(1682,'鼎城区',189,0),(1683,'安乡县',189,0),(1684,'汉寿县',189,0),(1685,'澧县',189,0),(1686,'临澧县',189,0),(1687,'桃源县',189,0),(1688,'石门县',189,0),(1689,'津市市',189,0),(1690,'永定区',190,0),(1691,'武陵源区',190,0),(1692,'慈利县',190,0),(1693,'桑植县',190,0),(1694,'资阳区',191,0),(1695,'赫山区',191,0),(1696,'南县',191,0),(1697,'桃江县',191,0),(1698,'安化县',191,0),(1699,'沅江市',191,0),(1700,'北湖区',192,0),(1701,'苏仙区',192,0),(1702,'桂阳县',192,0),(1703,'宜章县',192,0),(1704,'永兴县',192,0),(1705,'嘉禾县',192,0),(1706,'临武县',192,0),(1707,'汝城县',192,0),(1708,'桂东县',192,0),(1709,'安仁县',192,0),(1710,'资兴市',192,0),(1711,'芝山区',193,0),(1712,'冷水滩区',193,0),(1713,'祁阳县',193,0),(1714,'东安县',193,0),(1715,'双牌县',193,0),(1716,'道县',193,0),(1717,'江永县',193,0),(1718,'宁远县',193,0),(1719,'蓝山县',193,0),(1720,'新田县',193,0),(1721,'江华瑶族自治县',193,0),(1722,'鹤城区',194,0),(1723,'中方县',194,0),(1724,'沅陵县',194,0),(1725,'辰溪县',194,0),(1726,'溆浦县',194,0),(1727,'会同县',194,0),(1728,'麻阳苗族自治县',194,0),(1729,'新晃侗族自治县',194,0),(1730,'芷江侗族自治县',194,0),(1731,'靖州苗族侗族自治县',194,0),(1732,'通道侗族自治县',194,0),(1733,'洪江市',194,0),(1734,'娄星区',195,0),(1735,'双峰县',195,0),(1736,'新化县',195,0),(1737,'冷水江市',195,0),(1738,'涟源市',195,0),(1739,'吉首市',196,0),(1740,'泸溪县',196,0),(1741,'凤凰县',196,0),(1742,'花垣县',196,0),(1743,'保靖县',196,0),(1744,'古丈县',196,0),(1745,'永顺县',196,0),(1746,'龙山县',196,0),(1747,'东山区',197,0),(1748,'荔湾区',197,0),(1749,'越秀区',197,0),(1750,'海珠区',197,0),(1751,'天河区',197,0),(1752,'芳村区',197,0),(1753,'白云区',197,0),(1754,'黄埔区',197,0),(1755,'番禺区',197,0),(1756,'花都区',197,0),(1757,'增城市',197,0),(1758,'从化市',197,0),(1759,'武江区',198,0),(1760,'浈江区',198,0),(1761,'曲江区',198,0),(1762,'始兴县',198,0),(1763,'仁化县',198,0),(1764,'翁源县',198,0),(1765,'乳源瑶族自治县',198,0),(1766,'新丰县',198,0),(1767,'乐昌市',198,0),(1768,'南雄市',198,0),(1769,'罗湖区',199,0),(1770,'福田区',199,0),(1771,'南山区',199,0),(1772,'宝安区',199,0),(1773,'龙岗区',199,0),(1774,'盐田区',199,0),(1775,'香洲区',200,0),(1776,'斗门区',200,0),(1777,'金湾区',200,0),(1778,'龙湖区',201,0),(1779,'金平区',201,0),(1780,'濠江区',201,0),(1781,'潮阳区',201,0),(1782,'潮南区',201,0),(1783,'澄海区',201,0),(1784,'南澳县',201,0),(1785,'禅城区',202,0),(1786,'南海区',202,0),(1787,'顺德区',202,0),(1788,'三水区',202,0),(1789,'高明区',202,0),(1790,'蓬江区',203,0),(1791,'江海区',203,0),(1792,'新会区',203,0),(1793,'台山市',203,0),(1794,'开平市',203,0),(1795,'鹤山市',203,0),(1796,'恩平市',203,0),(1797,'赤坎区',204,0),(1798,'霞山区',204,0),(1799,'坡头区',204,0),(1800,'麻章区',204,0),(1801,'遂溪县',204,0),(1802,'徐闻县',204,0),(1803,'廉江市',204,0),(1804,'雷州市',204,0),(1805,'吴川市',204,0),(1806,'茂南区',205,0),(1807,'茂港区',205,0),(1808,'电白县',205,0),(1809,'高州市',205,0),(1810,'化州市',205,0),(1811,'信宜市',205,0),(1812,'端州区',206,0),(1813,'鼎湖区',206,0),(1814,'广宁县',206,0),(1815,'怀集县',206,0),(1816,'封开县',206,0),(1817,'德庆县',206,0),(1818,'高要市',206,0),(1819,'四会市',206,0),(1820,'惠城区',207,0),(1821,'惠阳区',207,0),(1822,'博罗县',207,0),(1823,'惠东县',207,0),(1824,'龙门县',207,0),(1825,'梅江区',208,0),(1826,'梅县',208,0),(1827,'大埔县',208,0),(1828,'丰顺县',208,0),(1829,'五华县',208,0),(1830,'平远县',208,0),(1831,'蕉岭县',208,0),(1832,'兴宁市',208,0),(1833,'城区',209,0),(1834,'海丰县',209,0),(1835,'陆河县',209,0),(1836,'陆丰市',209,0),(1837,'源城区',210,0),(1838,'紫金县',210,0),(1839,'龙川县',210,0),(1840,'连平县',210,0),(1841,'和平县',210,0),(1842,'东源县',210,0),(1843,'江城区',211,0),(1844,'阳西县',211,0),(1845,'阳东县',211,0),(1846,'阳春市',211,0),(1847,'清城区',212,0),(1848,'佛冈县',212,0),(1849,'阳山县',212,0),(1850,'连山壮族瑶族自治县',212,0),(1851,'连南瑶族自治县',212,0),(1852,'清新县',212,0),(1853,'英德市',212,0),(1854,'连州市',212,0),(1855,'湘桥区',215,0),(1856,'潮安县',215,0),(1857,'饶平县',215,0),(1858,'榕城区',216,0),(1859,'揭东县',216,0),(1860,'揭西县',216,0),(1861,'惠来县',216,0),(1862,'普宁市',216,0),(1863,'云城区',217,0),(1864,'新兴县',217,0),(1865,'郁南县',217,0),(1866,'云安县',217,0),(1867,'罗定市',217,0),(1868,'兴宁区',218,0),(1869,'青秀区',218,0),(1870,'江南区',218,0),(1871,'西乡塘区',218,0),(1872,'良庆区',218,0),(1873,'邕宁区',218,0),(1874,'武鸣县',218,0),(1875,'隆安县',218,0),(1876,'马山县',218,0),(1877,'上林县',218,0),(1878,'宾阳县',218,0),(1879,'横县',218,0),(1880,'城中区',219,0),(1881,'鱼峰区',219,0),(1882,'柳南区',219,0),(1883,'柳北区',219,0),(1884,'柳江县',219,0),(1885,'柳城县',219,0),(1886,'鹿寨县',219,0),(1887,'融安县',219,0),(1888,'融水苗族自治县',219,0),(1889,'三江侗族自治县',219,0),(1890,'秀峰区',220,0),(1891,'叠彩区',220,0),(1892,'象山区',220,0),(1893,'七星区',220,0),(1894,'雁山区',220,0),(1895,'阳朔县',220,0),(1896,'临桂县',220,0),(1897,'灵川县',220,0),(1898,'全州县',220,0),(1899,'兴安县',220,0),(1900,'永福县',220,0),(1901,'灌阳县',220,0),(1902,'龙胜各族自治县',220,0),(1903,'资源县',220,0),(1904,'平乐县',220,0),(1905,'荔蒲县',220,0),(1906,'恭城瑶族自治县',220,0),(1907,'万秀区',221,0),(1908,'蝶山区',221,0),(1909,'长洲区',221,0),(1910,'苍梧县',221,0),(1911,'藤县',221,0),(1912,'蒙山县',221,0),(1913,'岑溪市',221,0),(1914,'海城区',222,0),(1915,'银海区',222,0),(1916,'铁山港区',222,0),(1917,'合浦县',222,0),(1918,'港口区',223,0),(1919,'防城区',223,0),(1920,'上思县',223,0),(1921,'东兴市',223,0),(1922,'钦南区',224,0),(1923,'钦北区',224,0),(1924,'灵山县',224,0),(1925,'浦北县',224,0),(1926,'港北区',225,0),(1927,'港南区',225,0),(1928,'覃塘区',225,0),(1929,'平南县',225,0),(1930,'桂平市',225,0),(1931,'玉州区',226,0),(1932,'容县',226,0),(1933,'陆川县',226,0),(1934,'博白县',226,0),(1935,'兴业县',226,0),(1936,'北流市',226,0),(1937,'右江区',227,0),(1938,'田阳县',227,0),(1939,'田东县',227,0),(1940,'平果县',227,0),(1941,'德保县',227,0),(1942,'靖西县',227,0),(1943,'那坡县',227,0),(1944,'凌云县',227,0),(1945,'乐业县',227,0),(1946,'田林县',227,0),(1947,'西林县',227,0),(1948,'隆林各族自治县',227,0),(1949,'八步区',228,0),(1950,'昭平县',228,0),(1951,'钟山县',228,0),(1952,'富川瑶族自治县',228,0),(1953,'金城江区',229,0),(1954,'南丹县',229,0),(1955,'天峨县',229,0),(1956,'凤山县',229,0),(1957,'东兰县',229,0),(1958,'罗城仫佬族自治县',229,0),(1959,'环江毛南族自治县',229,0),(1960,'巴马瑶族自治县',229,0),(1961,'都安瑶族自治县',229,0),(1962,'大化瑶族自治县',229,0),(1963,'宜州市',229,0),(1964,'兴宾区',230,0),(1965,'忻城县',230,0),(1966,'象州县',230,0),(1967,'武宣县',230,0),(1968,'金秀瑶族自治县',230,0),(1969,'合山市',230,0),(1970,'江洲区',231,0),(1971,'扶绥县',231,0),(1972,'宁明县',231,0),(1973,'龙州县',231,0),(1974,'大新县',231,0),(1975,'天等县',231,0),(1976,'凭祥市',231,0),(1977,'秀英区',232,0),(1978,'龙华区',232,0),(1979,'琼山区',232,0),(1980,'美兰区',232,0),(1981,'五指山市',233,0),(1982,'琼海市',233,0),(1983,'儋州市',233,0),(1984,'文昌市',233,0),(1985,'万宁市',233,0),(1986,'东方市',233,0),(1987,'定安县',233,0),(1988,'屯昌县',233,0),(1989,'澄迈县',233,0),(1990,'临高县',233,0),(1991,'白沙黎族自治县',233,0),(1992,'昌江黎族自治县',233,0),(1993,'乐东黎族自治县',233,0),(1994,'陵水黎族自治县',233,0),(1995,'保亭黎族苗族自治县',233,0),(1996,'琼中黎族苗族自治县',233,0),(1997,'西沙群岛',233,0),(1998,'南沙群岛',233,0),(1999,'中沙群岛的岛礁及其海域',233,0),(2000,'万州区',234,0),(2001,'涪陵区',234,0),(2002,'渝中区',234,0),(2003,'大渡口区',234,0),(2004,'江北区',234,0),(2005,'沙坪坝区',234,0),(2006,'九龙坡区',234,0),(2007,'南岸区',234,0),(2008,'北碚区',234,0),(2009,'万盛区',234,0),(2010,'双桥区',234,0),(2011,'渝北区',234,0),(2012,'巴南区',234,0),(2013,'黔江区',234,0),(2014,'长寿区',234,0),(2015,'綦江县',234,0),(2016,'潼南县',234,0),(2017,'铜梁县',234,0),(2018,'大足县',234,0),(2019,'荣昌县',234,0),(2020,'璧山县',234,0),(2021,'梁平县',234,0),(2022,'城口县',234,0),(2023,'丰都县',234,0),(2024,'垫江县',234,0),(2025,'武隆县',234,0),(2026,'忠县',234,0),(2027,'开县',234,0),(2028,'云阳县',234,0),(2029,'奉节县',234,0),(2030,'巫山县',234,0),(2031,'巫溪县',234,0),(2032,'石柱土家族自治县',234,0),(2033,'秀山土家族苗族自治县',234,0),(2034,'酉阳土家族苗族自治县',234,0),(2035,'彭水苗族土家族自治县',234,0),(2036,'江津市',234,0),(2037,'合川市',234,0),(2038,'永川市',234,0),(2039,'南川市',234,0),(2040,'锦江区',235,0),(2041,'青羊区',235,0),(2042,'金牛区',235,0),(2043,'武侯区',235,0),(2044,'成华区',235,0),(2045,'龙泉驿区',235,0),(2046,'青白江区',235,0),(2047,'新都区',235,0),(2048,'温江区',235,0),(2049,'金堂县',235,0),(2050,'双流县',235,0),(2051,'郫县',235,0),(2052,'大邑县',235,0),(2053,'蒲江县',235,0),(2054,'新津县',235,0),(2055,'都江堰市',235,0),(2056,'彭州市',235,0),(2057,'邛崃市',235,0),(2058,'崇州市',235,0),(2059,'自流井区',236,0),(2060,'贡井区',236,0),(2061,'大安区',236,0),(2062,'沿滩区',236,0),(2063,'荣县',236,0),(2064,'富顺县',236,0),(2065,'东区',237,0),(2066,'西区',237,0),(2067,'仁和区',237,0),(2068,'米易县',237,0),(2069,'盐边县',237,0),(2070,'江阳区',238,0),(2071,'纳溪区',238,0),(2072,'龙马潭区',238,0),(2073,'泸县',238,0),(2074,'合江县',238,0),(2075,'叙永县',238,0),(2076,'古蔺县',238,0),(2077,'旌阳区',239,0),(2078,'中江县',239,0),(2079,'罗江县',239,0),(2080,'广汉市',239,0),(2081,'什邡市',239,0),(2082,'绵竹市',239,0),(2083,'涪城区',240,0),(2084,'游仙区',240,0),(2085,'三台县',240,0),(2086,'盐亭县',240,0),(2087,'安县',240,0),(2088,'梓潼县',240,0),(2089,'北川羌族自治县',240,0),(2090,'平武县',240,0),(2091,'江油市',240,0),(2092,'市中区',241,0),(2093,'元坝区',241,0),(2094,'朝天区',241,0),(2095,'旺苍县',241,0),(2096,'青川县',241,0),(2097,'剑阁县',241,0),(2098,'苍溪县',241,0),(2099,'船山区',242,0),(2100,'安居区',242,0),(2101,'蓬溪县',242,0),(2102,'射洪县',242,0),(2103,'大英县',242,0),(2104,'市中区',243,0),(2105,'东兴区',243,0),(2106,'威远县',243,0),(2107,'资中县',243,0),(2108,'隆昌县',243,0),(2109,'市中区',244,0),(2110,'沙湾区',244,0),(2111,'五通桥区',244,0),(2112,'金口河区',244,0),(2113,'犍为县',244,0),(2114,'井研县',244,0),(2115,'夹江县',244,0),(2116,'沐川县',244,0),(2117,'峨边彝族自治县',244,0),(2118,'马边彝族自治县',244,0),(2119,'峨眉山市',244,0),(2120,'顺庆区',245,0),(2121,'高坪区',245,0),(2122,'嘉陵区',245,0),(2123,'南部县',245,0),(2124,'营山县',245,0),(2125,'蓬安县',245,0),(2126,'仪陇县',245,0),(2127,'西充县',245,0),(2128,'阆中市',245,0),(2129,'东坡区',246,0),(2130,'仁寿县',246,0),(2131,'彭山县',246,0),(2132,'洪雅县',246,0),(2133,'丹棱县',246,0),(2134,'青神县',246,0),(2135,'翠屏区',247,0),(2136,'宜宾县',247,0),(2137,'南溪县',247,0),(2138,'江安县',247,0),(2139,'长宁县',247,0),(2140,'高县',247,0),(2141,'珙县',247,0),(2142,'筠连县',247,0),(2143,'兴文县',247,0),(2144,'屏山县',247,0),(2145,'广安区',248,0),(2146,'岳池县',248,0),(2147,'武胜县',248,0),(2148,'邻水县',248,0),(2149,'华蓥市',248,0),(2150,'通川区',249,0),(2151,'达县',249,0),(2152,'宣汉县',249,0),(2153,'开江县',249,0),(2154,'大竹县',249,0),(2155,'渠县',249,0),(2156,'万源市',249,0),(2157,'雨城区',250,0),(2158,'名山县',250,0),(2159,'荥经县',250,0),(2160,'汉源县',250,0),(2161,'石棉县',250,0),(2162,'天全县',250,0),(2163,'芦山县',250,0),(2164,'宝兴县',250,0),(2165,'巴州区',251,0),(2166,'通江县',251,0),(2167,'南江县',251,0),(2168,'平昌县',251,0),(2169,'雁江区',252,0),(2170,'安岳县',252,0),(2171,'乐至县',252,0),(2172,'简阳市',252,0),(2173,'汶川县',253,0),(2174,'理县',253,0),(2175,'茂县',253,0),(2176,'松潘县',253,0),(2177,'九寨沟县',253,0),(2178,'金川县',253,0),(2179,'小金县',253,0),(2180,'黑水县',253,0),(2181,'马尔康县',253,0),(2182,'壤塘县',253,0),(2183,'阿坝县',253,0),(2184,'若尔盖县',253,0),(2185,'红原县',253,0),(2186,'康定县',254,0),(2187,'泸定县',254,0),(2188,'丹巴县',254,0),(2189,'九龙县',254,0),(2190,'雅江县',254,0),(2191,'道孚县',254,0),(2192,'炉霍县',254,0),(2193,'甘孜县',254,0),(2194,'新龙县',254,0),(2195,'德格县',254,0),(2196,'白玉县',254,0),(2197,'石渠县',254,0),(2198,'色达县',254,0),(2199,'理塘县',254,0),(2200,'巴塘县',254,0),(2201,'乡城县',254,0),(2202,'稻城县',254,0),(2203,'得荣县',254,0),(2204,'西昌市',255,0),(2205,'木里藏族自治县',255,0),(2206,'盐源县',255,0),(2207,'德昌县',255,0),(2208,'会理县',255,0),(2209,'会东县',255,0),(2210,'宁南县',255,0),(2211,'普格县',255,0),(2212,'布拖县',255,0),(2213,'金阳县',255,0),(2214,'昭觉县',255,0),(2215,'喜德县',255,0),(2216,'冕宁县',255,0),(2217,'越西县',255,0),(2218,'甘洛县',255,0),(2219,'美姑县',255,0),(2220,'雷波县',255,0),(2221,'南明区',256,0),(2222,'云岩区',256,0),(2223,'花溪区',256,0),(2224,'乌当区',256,0),(2225,'白云区',256,0),(2226,'小河区',256,0),(2227,'开阳县',256,0),(2228,'息烽县',256,0),(2229,'修文县',256,0),(2230,'清镇市',256,0),(2231,'钟山区',257,0),(2232,'六枝特区',257,0),(2233,'水城县',257,0),(2234,'盘县',257,0),(2235,'红花岗区',258,0),(2236,'汇川区',258,0),(2237,'遵义县',258,0),(2238,'桐梓县',258,0),(2239,'绥阳县',258,0),(2240,'正安县',258,0),(2241,'道真仡佬族苗族自治县',258,0),(2242,'务川仡佬族苗族自治县',258,0),(2243,'凤冈县',258,0),(2244,'湄潭县',258,0),(2245,'余庆县',258,0),(2246,'习水县',258,0),(2247,'赤水市',258,0),(2248,'仁怀市',258,0),(2249,'西秀区',259,0),(2250,'平坝县',259,0),(2251,'普定县',259,0),(2252,'镇宁布依族苗族自治县',259,0),(2253,'关岭布依族苗族自治县',259,0),(2254,'紫云苗族布依族自治县',259,0),(2255,'铜仁市',260,0),(2256,'江口县',260,0),(2257,'玉屏侗族自治县',260,0),(2258,'石阡县',260,0),(2259,'思南县',260,0),(2260,'印江土家族苗族自治县',260,0),(2261,'德江县',260,0),(2262,'沿河土家族自治县',260,0),(2263,'松桃苗族自治县',260,0),(2264,'万山特区',260,0),(2265,'兴义市',261,0),(2266,'兴仁县',261,0),(2267,'普安县',261,0),(2268,'晴隆县',261,0),(2269,'贞丰县',261,0),(2270,'望谟县',261,0),(2271,'册亨县',261,0),(2272,'安龙县',261,0),(2273,'毕节市',262,0),(2274,'大方县',262,0),(2275,'黔西县',262,0),(2276,'金沙县',262,0),(2277,'织金县',262,0),(2278,'纳雍县',262,0),(2279,'威宁彝族回族苗族自治县',262,0),(2280,'赫章县',262,0),(2281,'凯里市',263,0),(2282,'黄平县',263,0),(2283,'施秉县',263,0),(2284,'三穗县',263,0),(2285,'镇远县',263,0),(2286,'岑巩县',263,0),(2287,'天柱县',263,0),(2288,'锦屏县',263,0),(2289,'剑河县',263,0),(2290,'台江县',263,0),(2291,'黎平县',263,0),(2292,'榕江县',263,0),(2293,'从江县',263,0),(2294,'雷山县',263,0),(2295,'麻江县',263,0),(2296,'丹寨县',263,0),(2297,'都匀市',264,0),(2298,'福泉市',264,0),(2299,'荔波县',264,0),(2300,'贵定县',264,0),(2301,'瓮安县',264,0),(2302,'独山县',264,0),(2303,'平塘县',264,0),(2304,'罗甸县',264,0),(2305,'长顺县',264,0),(2306,'龙里县',264,0),(2307,'惠水县',264,0),(2308,'三都水族自治县',264,0),(2309,'五华区',265,0),(2310,'盘龙区',265,0),(2311,'官渡区',265,0),(2312,'西山区',265,0),(2313,'东川区',265,0),(2314,'呈贡县',265,0),(2315,'晋宁县',265,0),(2316,'富民县',265,0),(2317,'宜良县',265,0),(2318,'石林彝族自治县',265,0),(2319,'嵩明县',265,0),(2320,'禄劝彝族苗族自治县',265,0),(2321,'寻甸回族彝族自治县',265,0),(2322,'安宁市',265,0),(2323,'麒麟区',266,0),(2324,'马龙县',266,0),(2325,'陆良县',266,0),(2326,'师宗县',266,0),(2327,'罗平县',266,0),(2328,'富源县',266,0),(2329,'会泽县',266,0),(2330,'沾益县',266,0),(2331,'宣威市',266,0),(2332,'红塔区',267,0),(2333,'江川县',267,0),(2334,'澄江县',267,0),(2335,'通海县',267,0),(2336,'华宁县',267,0),(2337,'易门县',267,0),(2338,'峨山彝族自治县',267,0),(2339,'新平彝族傣族自治县',267,0),(2340,'元江哈尼族彝族傣族自治县',267,0),(2341,'隆阳区',268,0),(2342,'施甸县',268,0),(2343,'腾冲县',268,0),(2344,'龙陵县',268,0),(2345,'昌宁县',268,0),(2346,'昭阳区',269,0),(2347,'鲁甸县',269,0),(2348,'巧家县',269,0),(2349,'盐津县',269,0),(2350,'大关县',269,0),(2351,'永善县',269,0),(2352,'绥江县',269,0),(2353,'镇雄县',269,0),(2354,'彝良县',269,0),(2355,'威信县',269,0),(2356,'水富县',269,0),(2357,'古城区',270,0),(2358,'玉龙纳西族自治县',270,0),(2359,'永胜县',270,0),(2360,'华坪县',270,0),(2361,'宁蒗彝族自治县',270,0),(2362,'翠云区',271,0),(2363,'普洱哈尼族彝族自治县',271,0),(2364,'墨江哈尼族自治县',271,0),(2365,'景东彝族自治县',271,0),(2366,'景谷傣族彝族自治县',271,0),(2367,'镇沅彝族哈尼族拉祜族自治县',271,0),(2368,'江城哈尼族彝族自治县',271,0),(2369,'孟连傣族拉祜族佤族自治县',271,0),(2370,'澜沧拉祜族自治县',271,0),(2371,'西盟佤族自治县',271,0),(2372,'临翔区',272,0),(2373,'凤庆县',272,0),(2374,'云县',272,0),(2375,'永德县',272,0),(2376,'镇康县',272,0),(2377,'双江拉祜族佤族布朗族傣族自治县',272,0),(2378,'耿马傣族佤族自治县',272,0),(2379,'沧源佤族自治县',272,0),(2380,'楚雄市',273,0),(2381,'双柏县',273,0),(2382,'牟定县',273,0),(2383,'南华县',273,0),(2384,'姚安县',273,0),(2385,'大姚县',273,0),(2386,'永仁县',273,0),(2387,'元谋县',273,0),(2388,'武定县',273,0),(2389,'禄丰县',273,0),(2390,'个旧市',274,0),(2391,'开远市',274,0),(2392,'蒙自县',274,0),(2393,'屏边苗族自治县',274,0),(2394,'建水县',274,0),(2395,'石屏县',274,0),(2396,'弥勒县',274,0),(2397,'泸西县',274,0),(2398,'元阳县',274,0),(2399,'红河县',274,0),(2400,'金平苗族瑶族傣族自治县',274,0),(2401,'绿春县',274,0),(2402,'河口瑶族自治县',274,0),(2403,'文山县',275,0),(2404,'砚山县',275,0),(2405,'西畴县',275,0),(2406,'麻栗坡县',275,0),(2407,'马关县',275,0),(2408,'丘北县',275,0),(2409,'广南县',275,0),(2410,'富宁县',275,0),(2411,'景洪市',276,0),(2412,'勐海县',276,0),(2413,'勐腊县',276,0),(2414,'大理市',277,0),(2415,'漾濞彝族自治县',277,0),(2416,'祥云县',277,0),(2417,'宾川县',277,0),(2418,'弥渡县',277,0),(2419,'南涧彝族自治县',277,0),(2420,'巍山彝族回族自治县',277,0),(2421,'永平县',277,0),(2422,'云龙县',277,0),(2423,'洱源县',277,0),(2424,'剑川县',277,0),(2425,'鹤庆县',277,0),(2426,'瑞丽市',278,0),(2427,'潞西市',278,0),(2428,'梁河县',278,0),(2429,'盈江县',278,0),(2430,'陇川县',278,0),(2431,'泸水县',279,0),(2432,'福贡县',279,0),(2433,'贡山独龙族怒族自治县',279,0),(2434,'兰坪白族普米族自治县',279,0),(2435,'香格里拉县',280,0),(2436,'德钦县',280,0),(2437,'维西傈僳族自治县',280,0),(2438,'城关区',281,0),(2439,'林周县',281,0),(2440,'当雄县',281,0),(2441,'尼木县',281,0),(2442,'曲水县',281,0),(2443,'堆龙德庆县',281,0),(2444,'达孜县',281,0),(2445,'墨竹工卡县',281,0),(2446,'昌都县',282,0),(2447,'江达县',282,0),(2448,'贡觉县',282,0),(2449,'类乌齐县',282,0),(2450,'丁青县',282,0),(2451,'察雅县',282,0),(2452,'八宿县',282,0),(2453,'左贡县',282,0),(2454,'芒康县',282,0),(2455,'洛隆县',282,0),(2456,'边坝县',282,0),(2457,'乃东县',283,0),(2458,'扎囊县',283,0),(2459,'贡嘎县',283,0),(2460,'桑日县',283,0),(2461,'琼结县',283,0),(2462,'曲松县',283,0),(2463,'措美县',283,0),(2464,'洛扎县',283,0),(2465,'加查县',283,0),(2466,'隆子县',283,0),(2467,'错那县',283,0),(2468,'浪卡子县',283,0),(2469,'日喀则市',284,0),(2470,'南木林县',284,0),(2471,'江孜县',284,0),(2472,'定日县',284,0),(2473,'萨迦县',284,0),(2474,'拉孜县',284,0),(2475,'昂仁县',284,0),(2476,'谢通门县',284,0),(2477,'白朗县',284,0),(2478,'仁布县',284,0),(2479,'康马县',284,0),(2480,'定结县',284,0),(2481,'仲巴县',284,0),(2482,'亚东县',284,0),(2483,'吉隆县',284,0),(2484,'聂拉木县',284,0),(2485,'萨嘎县',284,0),(2486,'岗巴县',284,0),(2487,'那曲县',285,0),(2488,'嘉黎县',285,0),(2489,'比如县',285,0),(2490,'聂荣县',285,0),(2491,'安多县',285,0),(2492,'申扎县',285,0),(2493,'索县',285,0),(2494,'班戈县',285,0),(2495,'巴青县',285,0),(2496,'尼玛县',285,0),(2497,'普兰县',286,0),(2498,'札达县',286,0),(2499,'噶尔县',286,0),(2500,'日土县',286,0),(2501,'革吉县',286,0),(2502,'改则县',286,0),(2503,'措勤县',286,0),(2504,'林芝县',287,0),(2505,'工布江达县',287,0),(2506,'米林县',287,0),(2507,'墨脱县',287,0),(2508,'波密县',287,0),(2509,'察隅县',287,0),(2510,'朗县',287,0),(2511,'新城区',288,0),(2512,'碑林区',288,0),(2513,'莲湖区',288,0),(2514,'灞桥区',288,0),(2515,'未央区',288,0),(2516,'雁塔区',288,0),(2517,'阎良区',288,0),(2518,'临潼区',288,0),(2519,'长安区',288,0),(2520,'蓝田县',288,0),(2521,'周至县',288,0),(2522,'户县',288,0),(2523,'高陵县',288,0),(2524,'王益区',289,0),(2525,'印台区',289,0),(2526,'耀州区',289,0),(2527,'宜君县',289,0),(2528,'渭滨区',290,0),(2529,'金台区',290,0),(2530,'陈仓区',290,0),(2531,'凤翔县',290,0),(2532,'岐山县',290,0),(2533,'扶风县',290,0),(2534,'眉县',290,0),(2535,'陇县',290,0),(2536,'千阳县',290,0),(2537,'麟游县',290,0),(2538,'凤县',290,0),(2539,'太白县',290,0),(2540,'秦都区',291,0),(2541,'杨凌区',291,0),(2542,'渭城区',291,0),(2543,'三原县',291,0),(2544,'泾阳县',291,0),(2545,'乾县',291,0),(2546,'礼泉县',291,0),(2547,'永寿县',291,0),(2548,'彬县',291,0),(2549,'长武县',291,0),(2550,'旬邑县',291,0),(2551,'淳化县',291,0),(2552,'武功县',291,0),(2553,'兴平市',291,0),(2554,'临渭区',292,0),(2555,'华县',292,0),(2556,'潼关县',292,0),(2557,'大荔县',292,0),(2558,'合阳县',292,0),(2559,'澄城县',292,0),(2560,'蒲城县',292,0),(2561,'白水县',292,0),(2562,'富平县',292,0),(2563,'韩城市',292,0),(2564,'华阴市',292,0),(2565,'宝塔区',293,0),(2566,'延长县',293,0),(2567,'延川县',293,0),(2568,'子长县',293,0),(2569,'安塞县',293,0),(2570,'志丹县',293,0),(2571,'吴旗县',293,0),(2572,'甘泉县',293,0),(2573,'富县',293,0),(2574,'洛川县',293,0),(2575,'宜川县',293,0),(2576,'黄龙县',293,0),(2577,'黄陵县',293,0),(2578,'汉台区',294,0),(2579,'南郑县',294,0),(2580,'城固县',294,0),(2581,'洋县',294,0),(2582,'西乡县',294,0),(2583,'勉县',294,0),(2584,'宁强县',294,0),(2585,'略阳县',294,0),(2586,'镇巴县',294,0),(2587,'留坝县',294,0),(2588,'佛坪县',294,0),(2589,'榆阳区',295,0),(2590,'神木县',295,0),(2591,'府谷县',295,0),(2592,'横山县',295,0),(2593,'靖边县',295,0),(2594,'定边县',295,0),(2595,'绥德县',295,0),(2596,'米脂县',295,0),(2597,'佳县',295,0),(2598,'吴堡县',295,0),(2599,'清涧县',295,0),(2600,'子洲县',295,0),(2601,'汉滨区',296,0),(2602,'汉阴县',296,0),(2603,'石泉县',296,0),(2604,'宁陕县',296,0),(2605,'紫阳县',296,0),(2606,'岚皋县',296,0),(2607,'平利县',296,0),(2608,'镇坪县',296,0),(2609,'旬阳县',296,0),(2610,'白河县',296,0),(2611,'商州区',297,0),(2612,'洛南县',297,0),(2613,'丹凤县',297,0),(2614,'商南县',297,0),(2615,'山阳县',297,0),(2616,'镇安县',297,0),(2617,'柞水县',297,0),(2618,'城关区',298,0),(2619,'七里河区',298,0),(2620,'西固区',298,0),(2621,'安宁区',298,0),(2622,'红古区',298,0),(2623,'永登县',298,0),(2624,'皋兰县',298,0),(2625,'榆中县',298,0),(2626,'金川区',300,0),(2627,'永昌县',300,0),(2628,'白银区',301,0),(2629,'平川区',301,0),(2630,'靖远县',301,0),(2631,'会宁县',301,0),(2632,'景泰县',301,0),(2633,'秦城区',302,0),(2634,'北道区',302,0),(2635,'清水县',302,0),(2636,'秦安县',302,0),(2637,'甘谷县',302,0),(2638,'武山县',302,0),(2639,'张家川回族自治县',302,0),(2640,'凉州区',303,0),(2641,'民勤县',303,0),(2642,'古浪县',303,0),(2643,'天祝藏族自治县',303,0),(2644,'甘州区',304,0),(2645,'肃南裕固族自治县',304,0),(2646,'民乐县',304,0),(2647,'临泽县',304,0),(2648,'高台县',304,0),(2649,'山丹县',304,0),(2650,'崆峒区',305,0),(2651,'泾川县',305,0),(2652,'灵台县',305,0),(2653,'崇信县',305,0),(2654,'华亭县',305,0),(2655,'庄浪县',305,0),(2656,'静宁县',305,0),(2657,'肃州区',306,0),(2658,'金塔县',306,0),(2659,'安西县',306,0),(2660,'肃北蒙古族自治县',306,0),(2661,'阿克塞哈萨克族自治县',306,0),(2662,'玉门市',306,0),(2663,'敦煌市',306,0),(2664,'西峰区',307,0),(2665,'庆城县',307,0),(2666,'环县',307,0),(2667,'华池县',307,0),(2668,'合水县',307,0),(2669,'正宁县',307,0),(2670,'宁县',307,0),(2671,'镇原县',307,0),(2672,'安定区',308,0),(2673,'通渭县',308,0),(2674,'陇西县',308,0),(2675,'渭源县',308,0),(2676,'临洮县',308,0),(2677,'漳县',308,0),(2678,'岷县',308,0),(2679,'武都区',309,0),(2680,'成县',309,0),(2681,'文县',309,0),(2682,'宕昌县',309,0),(2683,'康县',309,0),(2684,'西和县',309,0),(2685,'礼县',309,0),(2686,'徽县',309,0),(2687,'两当县',309,0),(2688,'临夏市',310,0),(2689,'临夏县',310,0),(2690,'康乐县',310,0),(2691,'永靖县',310,0),(2692,'广河县',310,0),(2693,'和政县',310,0),(2694,'东乡族自治县',310,0),(2695,'积石山保安族东乡族撒拉族自治县',310,0),(2696,'合作市',311,0),(2697,'临潭县',311,0),(2698,'卓尼县',311,0),(2699,'舟曲县',311,0),(2700,'迭部县',311,0),(2701,'玛曲县',311,0),(2702,'碌曲县',311,0),(2703,'夏河县',311,0),(2704,'城东区',312,0),(2705,'城中区',312,0),(2706,'城西区',312,0),(2707,'城北区',312,0),(2708,'大通回族土族自治县',312,0),(2709,'湟中县',312,0),(2710,'湟源县',312,0),(2711,'平安县',313,0),(2712,'民和回族土族自治县',313,0),(2713,'乐都县',313,0),(2714,'互助土族自治县',313,0),(2715,'化隆回族自治县',313,0),(2716,'循化撒拉族自治县',313,0),(2717,'门源回族自治县',314,0),(2718,'祁连县',314,0),(2719,'海晏县',314,0),(2720,'刚察县',314,0),(2721,'同仁县',315,0),(2722,'尖扎县',315,0),(2723,'泽库县',315,0),(2724,'河南蒙古族自治县',315,0),(2725,'共和县',316,0),(2726,'同德县',316,0),(2727,'贵德县',316,0),(2728,'兴海县',316,0),(2729,'贵南县',316,0),(2730,'玛沁县',317,0),(2731,'班玛县',317,0),(2732,'甘德县',317,0),(2733,'达日县',317,0),(2734,'久治县',317,0),(2735,'玛多县',317,0),(2736,'玉树县',318,0),(2737,'杂多县',318,0),(2738,'称多县',318,0),(2739,'治多县',318,0),(2740,'囊谦县',318,0),(2741,'曲麻莱县',318,0),(2742,'格尔木市',319,0),(2743,'德令哈市',319,0),(2744,'乌兰县',319,0),(2745,'都兰县',319,0),(2746,'天峻县',319,0),(2747,'兴庆区',320,0),(2748,'西夏区',320,0),(2749,'金凤区',320,0),(2750,'永宁县',320,0),(2751,'贺兰县',320,0),(2752,'灵武市',320,0),(2753,'大武口区',321,0),(2754,'惠农区',321,0),(2755,'平罗县',321,0),(2756,'利通区',322,0),(2757,'盐池县',322,0),(2758,'同心县',322,0),(2759,'青铜峡市',322,0),(2760,'原州区',323,0),(2761,'西吉县',323,0),(2762,'隆德县',323,0),(2763,'泾源县',323,0),(2764,'彭阳县',323,0),(2765,'沙坡头区',324,0),(2766,'中宁县',324,0),(2767,'海原县',324,0),(2768,'天山区',325,0),(2769,'沙依巴克区',325,0),(2770,'新市区',325,0),(2771,'水磨沟区',325,0),(2772,'头屯河区',325,0),(2773,'达坂城区',325,0),(2774,'东山区',325,0),(2775,'乌鲁木齐县',325,0),(2776,'独山子区',326,0),(2777,'克拉玛依区',326,0),(2778,'白碱滩区',326,0),(2779,'乌尔禾区',326,0),(2780,'吐鲁番市',327,0),(2781,'鄯善县',327,0),(2782,'托克逊县',327,0),(2783,'哈密市',328,0),(2784,'巴里坤哈萨克自治县',328,0),(2785,'伊吾县',328,0),(2786,'昌吉市',329,0),(2787,'阜康市',329,0),(2788,'米泉市',329,0),(2789,'呼图壁县',329,0),(2790,'玛纳斯县',329,0),(2791,'奇台县',329,0),(2792,'吉木萨尔县',329,0),(2793,'木垒哈萨克自治县',329,0),(2794,'博乐市',330,0),(2795,'精河县',330,0),(2796,'温泉县',330,0),(2797,'库尔勒市',331,0),(2798,'轮台县',331,0),(2799,'尉犁县',331,0),(2800,'若羌县',331,0),(2801,'且末县',331,0),(2802,'焉耆回族自治县',331,0),(2803,'和静县',331,0),(2804,'和硕县',331,0),(2805,'博湖县',331,0),(2806,'阿克苏市',332,0),(2807,'温宿县',332,0),(2808,'库车县',332,0),(2809,'沙雅县',332,0),(2810,'新和县',332,0),(2811,'拜城县',332,0),(2812,'乌什县',332,0),(2813,'阿瓦提县',332,0),(2814,'柯坪县',332,0),(2815,'阿图什市',333,0),(2816,'阿克陶县',333,0),(2817,'阿合奇县',333,0),(2818,'乌恰县',333,0),(2819,'喀什市',334,0),(2820,'疏附县',334,0),(2821,'疏勒县',334,0),(2822,'英吉沙县',334,0),(2823,'泽普县',334,0),(2824,'莎车县',334,0),(2825,'叶城县',334,0),(2826,'麦盖提县',334,0),(2827,'岳普湖县',334,0),(2828,'伽师县',334,0),(2829,'巴楚县',334,0),(2830,'塔什库尔干塔吉克自治县',334,0),(2831,'和田市',335,0),(2832,'和田县',335,0),(2833,'墨玉县',335,0),(2834,'皮山县',335,0),(2835,'洛浦县',335,0),(2836,'策勒县',335,0),(2837,'于田县',335,0),(2838,'民丰县',335,0),(2839,'伊宁市',336,0),(2840,'奎屯市',336,0),(2841,'伊宁县',336,0),(2842,'察布查尔锡伯自治县',336,0),(2843,'霍城县',336,0),(2844,'巩留县',336,0),(2845,'新源县',336,0),(2846,'昭苏县',336,0),(2847,'特克斯县',336,0),(2848,'尼勒克县',336,0),(2849,'塔城市',337,0),(2850,'乌苏市',337,0),(2851,'额敏县',337,0),(2852,'沙湾县',337,0),(2853,'托里县',337,0),(2854,'裕民县',337,0),(2855,'和布克赛尔蒙古自治县',337,0),(2856,'阿勒泰市',338,0),(2857,'布尔津县',338,0),(2858,'富蕴县',338,0),(2859,'福海县',338,0),(2860,'哈巴河县',338,0),(2861,'青河县',338,0),(2862,'吉木乃县',338,0);
/*!40000 ALTER TABLE `sub_sub_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer_sheet`
--

DROP TABLE IF EXISTS `transfer_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfer_sheet` (
  `id` int(11) NOT NULL DEFAULT '0',
  `code` varchar(20) DEFAULT NULL,
  `id_project_step` int(11) DEFAULT NULL,
  `c1` varchar(64) DEFAULT NULL,
  `c2` varchar(64) DEFAULT NULL,
  `c3` varchar(64) DEFAULT NULL,
  `c4` varchar(64) DEFAULT NULL,
  `c5` varchar(64) DEFAULT NULL,
  `c6` varchar(64) DEFAULT NULL,
  `c7` varchar(64) DEFAULT NULL,
  `c8` varchar(64) DEFAULT NULL,
  `c9` varchar(64) DEFAULT NULL,
  `c10` varchar(64) DEFAULT NULL,
  `c11` varchar(64) DEFAULT NULL,
  `c12` varchar(64) DEFAULT NULL,
  `c13` varchar(64) DEFAULT NULL,
  `c14` varchar(64) DEFAULT NULL,
  `c15` varchar(64) DEFAULT NULL,
  `c16` varchar(64) DEFAULT NULL,
  `c17` varchar(64) DEFAULT NULL,
  `c18` varchar(64) DEFAULT NULL,
  `c19` varchar(64) DEFAULT NULL,
  `c20` varchar(64) DEFAULT NULL,
  `c21` varchar(64) DEFAULT NULL,
  `c22` varchar(64) DEFAULT NULL,
  `c23` varchar(64) DEFAULT NULL,
  `c24` varchar(64) DEFAULT NULL,
  `c25` varchar(64) DEFAULT NULL,
  `c26` varchar(64) DEFAULT NULL,
  `c27` varchar(64) DEFAULT NULL,
  `c28` varchar(64) DEFAULT NULL,
  `c29` varchar(64) DEFAULT NULL,
  `c30` varchar(64) DEFAULT NULL,
  `c31` varchar(64) DEFAULT NULL,
  `c32` varchar(64) DEFAULT NULL,
  `c33` varchar(64) DEFAULT NULL,
  `c34` varchar(64) DEFAULT NULL,
  `c35` varchar(64) DEFAULT NULL,
  `c36` varchar(64) DEFAULT NULL,
  `c37` varchar(64) DEFAULT NULL,
  `c38` varchar(64) DEFAULT NULL,
  `c39` varchar(64) DEFAULT NULL,
  `c40` varchar(64) DEFAULT NULL,
  `c41` varchar(64) DEFAULT NULL,
  `c42` varchar(64) DEFAULT NULL,
  `c43` varchar(64) DEFAULT NULL,
  `c44` varchar(64) DEFAULT NULL,
  `c45` varchar(64) DEFAULT NULL,
  `c46` varchar(64) DEFAULT NULL,
  `c47` varchar(64) DEFAULT NULL,
  `c48` varchar(64) DEFAULT NULL,
  `c49` varchar(64) DEFAULT NULL,
  `c50` varchar(64) DEFAULT NULL,
  `c51` varchar(64) DEFAULT NULL,
  `c52` varchar(64) DEFAULT NULL,
  `c53` varchar(64) DEFAULT NULL,
  `c54` varchar(64) DEFAULT NULL,
  `c55` varchar(64) DEFAULT NULL,
  `c56` varchar(64) DEFAULT NULL,
  `c57` varchar(64) DEFAULT NULL,
  `c58` varchar(64) DEFAULT NULL,
  `c59` varchar(64) DEFAULT NULL,
  `c60` varchar(64) DEFAULT NULL,
  `c61` varchar(64) DEFAULT NULL,
  `c62` varchar(64) DEFAULT NULL,
  `c63` varchar(64) DEFAULT NULL,
  `c64` varchar(64) DEFAULT NULL,
  `d1` timestamp NULL DEFAULT NULL,
  `d2` timestamp NULL DEFAULT NULL,
  `d3` timestamp NULL DEFAULT NULL,
  `d4` timestamp NULL DEFAULT NULL,
  `d5` timestamp NULL DEFAULT NULL,
  `d6` timestamp NULL DEFAULT NULL,
  `d7` timestamp NULL DEFAULT NULL,
  `d8` timestamp NULL DEFAULT NULL,
  `d9` timestamp NULL DEFAULT NULL,
  `d10` timestamp NULL DEFAULT NULL,
  `d11` timestamp NULL DEFAULT NULL,
  `s1` varchar(64) DEFAULT NULL,
  `s2` varchar(64) DEFAULT NULL,
  `s3` varchar(64) DEFAULT NULL,
  `s4` varchar(64) DEFAULT NULL,
  `s5` varchar(64) DEFAULT NULL,
  `s6` varchar(64) DEFAULT NULL,
  `s7` varchar(64) DEFAULT NULL,
  `s8` varchar(64) DEFAULT NULL,
  `s9` varchar(64) DEFAULT NULL,
  `s10` varchar(64) DEFAULT NULL,
  `s11` varchar(64) DEFAULT NULL,
  `p1` varchar(128) DEFAULT NULL,
  `p2` varchar(128) DEFAULT NULL,
  `p3` varchar(128) DEFAULT NULL,
  `p4` varchar(128) DEFAULT NULL,
  `p5` varchar(128) DEFAULT NULL,
  `p6` varchar(128) DEFAULT NULL,
  `p7` varchar(128) DEFAULT NULL,
  `p8` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer_sheet`
--

LOCK TABLES `transfer_sheet` WRITE;
/*!40000 ALTER TABLE `transfer_sheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer_sheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `token` varchar(128) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `pos` int(11) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `emp_status` int(11) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKphrnghgbaei5gg47nphb4bbwx` (`emp_status`),
  KEY `FKa9v59c21mc34ivca23w26fdxs` (`pos`),
  KEY `FKsf1cqhuyge7s8arl9tq51u8ey` (`role`),
  KEY `users_ibfk_2` (`department`),
  CONSTRAINT `FKa9v59c21mc34ivca23w26fdxs` FOREIGN KEY (`pos`) REFERENCES `dic` (`id`),
  CONSTRAINT `FKg0tf96t7d7scmqsum83ayde2a` FOREIGN KEY (`department`) REFERENCES `dic` (`id`),
  CONSTRAINT `FKphrnghgbaei5gg47nphb4bbwx` FOREIGN KEY (`emp_status`) REFERENCES `dic` (`id`),
  CONSTRAINT `FKsf1cqhuyge7s8arl9tq51u8ey` FOREIGN KEY (`role`) REFERENCES `dic` (`id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role`) REFERENCES `dic` (`id`),
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`department`) REFERENCES `dic` (`id`),
  CONSTRAINT `users_ibfk_3` FOREIGN KEY (`pos`) REFERENCES `dic` (`id`),
  CONSTRAINT `users_ibfk_4` FOREIGN KEY (`emp_status`) REFERENCES `dic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$rlEvsuaPIc51FxFaNuwlq.40e4MKNHAZ.EdwSY6wtIBJXx0p7BNSG','1__$2a$10$Rh26MEnZH3PUdia.OMm1xeyQEQDLWx8KVBDHq.cm6MvI7JJnOj3.a',NULL,'ren@ecust.edu.cn',NULL,NULL,NULL,NULL,NULL,1,'2017-04-18 04:58:59');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-23 21:44:28
