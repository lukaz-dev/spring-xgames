-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: xgames
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

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
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `format` varchar(20) NOT NULL,
  `platform` varchar(30) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `release_date` date NOT NULL,
  `size` decimal(10,1) NOT NULL,
  `stock` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'STRATEGY','jogos','DIGITAL','PLAYSTATION_4',9.90,'EA','2016-10-06',36.9,1000,'Battlefield 1'),(2,'ACTION','Jogo de mundo aberto','DIGITAL','PLAYSTATION_4',99.90,'Rockstar','2014-11-01',45.8,2570,'Grand Theft Auto V'),(3,'FPS','Jogo de tiro em primeira pessoa na Primeira Guerra Mundial','DIGITAL','PC',129.90,'EA','2016-12-12',23.6,570,'Battlefield 1'),(5,'SPORT',' Jogo de futebol','DISC','PLAYSTATION_4',125.79,'EA','2016-10-08',32.9,50,'FIFA 2017'),(6,'SPORT','Jogo de futebol','DIGITAL','XBOX_ONE',82.50,'Konami','2016-09-12',26.1,3200,'Pro Evolution Soccer 2017'),(7,'SPORT','jogo de futebol','DISC','XBOX_ONE',150.90,'EA','2017-06-01',22.7,223,'FIFA 2017'),(8,'ADVENTURE','Jogo do Batman','DISC','PLAYSTATION_4',89.90,'Rocksteady Studios','2015-10-12',52.9,50,'Batman Arkham Knight'),(11,'SPORT','Jogo de futebol','DIGITAL','PLAYSTATION_4',49.50,'EA','2016-12-12',52.9,525,'FIFA 2017'),(17,'ACTION','Jogo de simulação de corrida','DISC','PLAYSTATION_3',59.00,'Polyphony Digital','2013-11-11',35.6,60,'Gran Turismo 6'),(19,'SPORT','Jogo de futebol','DIGITAL','XBOX_ONE',89.90,'EA','2016-10-17',52.9,50,'FIFA 2017'),(21,'ACTION','Jogo de mundo aberto','DISC','PLAYSTATION_3',39.90,'Rockstar','2013-09-08',19.8,890,'Grand Theft Auto V'),(22,'SPORT','Jogo de futebol','DIGITAL','PLAYSTATION_4',45.50,'Konami','2016-12-12',23.6,652,'Pro Evolution Soccer 2017'),(32,'ACTION','jogo de mundo aberto','DISC','PLAYSTATION_4',99.90,'Rockstar','2014-12-12',45.8,500,'Grand Theft Auto V'),(33,'RACING','Jogo de corrida','DIGITAL','XBOX_ONE',69.50,'Microsoft','2016-10-10',35.6,450,'Forza Horizon 3'),(34,'RPG','Jogo de RPG mundo aberto','DIGITAL','PLAYSTATION_4',109.90,'CD Projekt RED','2015-05-19',52.9,758,'The Witcher 3: Wild Hunt'),(35,'RPG',' Jogo exclusivo de PS4','DISC','PLAYSTATION_4',159.90,'Guerrilla Games','2017-02-28',38.5,1250,'Horizon Zero Dawn'),(36,'ACTION','Jogo de tiro em terceira pessoa','DIGITAL','XBOX_ONE',79.90,'The Coalition','2016-10-11',35.6,1358,'Gears of War 4'),(37,'FPS','Jogo de tiro em primeira pessoa','DIGITAL','XBOX_ONE',49.90,'Microsoft','2015-10-27',56.9,980,'Halo 5: Guardians'),(38,'FPS','Jogo de tiro em primeira pessoa','DISC','PC',59.90,'Treyarch','2015-11-06',39.3,2150,'Call of Duty: Black Ops III'),(39,'FPS','Jogo de tiro em primeira pessoa','DISC','PLAYSTATION_4',59.00,'Treyarch','2015-11-06',37.9,1230,'Call of Duty: Black Ops III'),(40,'ACTION','Jogo online','DIGITAL','PC',109.90,'Blizzard','2016-05-24',22.7,2500,'Overwatch '),(41,'ADVENTURE','Jogo de aventura','DISC','PLAYSTATION_4',89.90,'Naughty Dog','2016-05-10',50.0,850,'Uncharted 4: A Thief\'s End'),(46,'RACING','Jogo de corrida','DISC','XBOX_ONE',139.50,'EA','2017-12-12',39.0,3000,'Forza Motorsport 7');
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'TABLE_GAMES'),(2,'NEW_GAME'),(3,'SEARCH_GAMES');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'','lukasgtx7@gmail.com','Oliver','Lukaz','$2a$10$xbDuxb73E/OlgE1rY0QAf.Kq4DPhadRukiInmvWbFRD0peGv3PeDG'),(2,'','luana@hotmail.com','Santos','Luana','$2a$10$EC7O9o0SImRllzNuNEPUXuH/twANXZZJkrI3XJ29n76T8mX2fb3Nu'),(4,'','bella@gmail.com','dos Anjos','Bella','$2a$10$Zhv7rzxE32WBdXZjnWG2YeGsxqqWfQ/RF3l4eDQ28KMZSnaZd4Pne');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,1),(4,1),(1,2),(1,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-18 11:59:03
