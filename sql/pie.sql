-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: mypie
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(70) DEFAULT NULL,
  `start_week` int(2) DEFAULT NULL,
  `end_week` int(2) DEFAULT NULL,
  `start_section` int(2) DEFAULT NULL,
  `term` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta`
--

DROP TABLE IF EXISTS `meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meta_key` varchar(50) DEFAULT NULL,
  `meta_value` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta`
--

LOCK TABLES `meta` WRITE;
/*!40000 ALTER TABLE `meta` DISABLE KEYS */;
INSERT INTO `meta` VALUES (1,'basePythonDir','/home/shudal/ras/'),(2,'baseVideoSrc','/home/shudal/Projects/VueProjects/mypie/public/assets/video/');
/*!40000 ALTER TABLE `meta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saying`
--

DROP TABLE IF EXISTS `saying`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saying` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `status` int(1) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `speed` int(2) DEFAULT NULL,
  `vol` int(2) DEFAULT NULL,
  `per` int(2) DEFAULT NULL,
  `pit` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saying`
--

LOCK TABLES `saying` WRITE;
/*!40000 ALTER TABLE `saying` DISABLE KEYS */;
INSERT INTO `saying` VALUES (1,'hi',1,'1559303154170',5,5,4,5),(2,'hello',1,'1559303174195',5,5,4,5),(3,'你是谁呀',1,'1559305651721',5,5,4,5),(5,'哈哈',-1,'1559308667675',5,5,4,5),(6,'哈哈',-1,'1559308725701',5,5,4,5),(7,'哈哈',-1,'1559308886320',5,5,4,5),(8,'哈哈',-1,'1559309317261',5,5,4,5),(9,'哈哈',-1,'1559309357685',5,5,4,5),(10,'哈哈',-1,'1559309867417',5,5,4,5),(11,'哈哈',1,'1559310352278',5,5,4,5),(12,'你是水阿',1,'1559310891926',5,5,4,5),(13,'你是水阿',1,'1559310897764',5,5,4,5),(14,'你怎么沙阿',1,'1559310911115',5,5,4,5),(15,'你怎么沙阿',1,'1559310980210',5,5,4,5),(16,'你怎么沙阿',1,'1559310984418',5,5,4,5),(17,'你怎么沙阿',1,'1559310989812',5,5,4,5),(18,'你怎么沙阿',1,'1559310996552',5,5,4,5),(19,'你怎么沙阿',1,'1559311000165',5,5,4,5),(20,'你怎么沙阿',1,'1559311005416',5,5,4,5),(21,'你怎么沙阿',1,'1559311100363',5,5,4,5),(22,'你怎么沙阿',1,'1559311826932',5,5,3,5),(23,'你怎么沙阿',1,'1559311833576',5,5,4,5),(24,'你怎么沙阿',1,'1559311840908',5,5,4,15),(25,'你怎么沙阿',1,'1559311846420',5,9,4,15),(26,'你是',1,'1559311858668',5,9,4,15),(27,'你是',1,'1559311869555',5,9,4,0),(28,'你是',1,'1559311873558',5,9,4,0),(29,'人家不想理你呀',1,'1559311894895',5,9,4,0),(30,'人家不想理你呀',1,'1559311901254',5,15,4,0),(31,'人家不想理你呀',1,'1559311907135',5,15,4,9),(32,'人家不想理你呀',1,'1559311912047',5,15,4,15),(33,'你是',1,'1559311923559',5,15,4,5),(34,'yi',1,'1559386284923',5,5,4,5);
/*!40000 ALTER TABLE `saying` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `role` tinyint(10) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Perci','shudal','22ea1c649c82946aa6e479e1ffd321e4a318b1b0',0,NULL,1);
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

-- Dump completed on 2019-06-01 20:32:55
