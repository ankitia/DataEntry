-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: database-1.ckxezqztkeob.ap-south-1.rds.amazonaws.com    Database: data_entry
-- ------------------------------------------------------
-- Server version	5.7.26-log

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED='';

--
-- Table structure for table `TableName`
--

DROP TABLE IF EXISTS `TableName`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TableName` (
  `name` varchar(4) DEFAULT NULL,
  `description` varchar(53) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `data_entry`
--

DROP TABLE IF EXISTS `data_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_entry` (
  `data_entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `job_title` varchar(1000) DEFAULT NULL,
  `name_source` varchar(500) DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `contact_email_source` varchar(500) DEFAULT NULL,
  `contact_phone` varchar(500) NOT NULL,
  `contact_phone_source` varchar(500) DEFAULT NULL,
  `company_name` varchar(500) DEFAULT NULL,
  `company_name_source` varchar(500) DEFAULT NULL,
  `company_website` varchar(500) DEFAULT NULL,
  `company_website_source` varchar(500) DEFAULT NULL,
  `company_email` varchar(500) DEFAULT NULL,
  `company_email_source` varchar(500) DEFAULT NULL,
  `company_phone` varchar(100) DEFAULT NULL,
  `company_phone_source` varchar(500) DEFAULT NULL,
  `property_name` varchar(500) DEFAULT NULL,
  `property_website` varchar(500) DEFAULT NULL,
  `property_source` varchar(500) DEFAULT NULL,
  `contact_us_form_link` varchar(500) DEFAULT NULL,
  `chk_contact_found` int(11) NOT NULL DEFAULT '0',
  `chk_contact_us_form` int(11) NOT NULL DEFAULT '0',
  `chk_site_not_valid` int(11) NOT NULL DEFAULT '0',
  `chk_site_down` int(11) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ipaddress` varchar(100) DEFAULT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `url_id` int(11) NOT NULL DEFAULT '0',
  `remarks` varchar(100) DEFAULT NULL,
  `address` varchar(2000) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `zip_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`data_entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17215 DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `data_remarks`
--

DROP TABLE IF EXISTS `data_remarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_remarks` (
  `data_remarks_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'Active',
  PRIMARY KEY (`data_remarks_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `drop_email_list`
--

DROP TABLE IF EXISTS `drop_email_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drop_email_list` (
  `drop_email_list_id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(255) NOT NULL,
  PRIMARY KEY (`drop_email_list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23028 DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `email_data`
--

DROP TABLE IF EXISTS `email_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_data` (
  `email_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `email_number` int(11) NOT NULL DEFAULT '0',
  `subject` varchar(2000) DEFAULT NULL,
  `email_from` varchar(500) DEFAULT NULL,
  `email_to` varchar(2000) DEFAULT NULL,
  `received_date` varchar(100) DEFAULT NULL,
  `send_date` varchar(100) DEFAULT NULL,
  `content` blob,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `master_email_id` int(11) DEFAULT NULL,
  `contact_list` varchar(1000) DEFAULT NULL,
  `email_list` varchar(1000) DEFAULT NULL,
  `employee_name` varchar(100) DEFAULT NULL,
  `content1` longtext,
  `status` varchar(10) NOT NULL DEFAULT 'Active',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`email_data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10476 DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `email_entry`
--

DROP TABLE IF EXISTS `email_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_entry` (
  `email_entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `job_title` varchar(1000) DEFAULT NULL,
  `name_source` varchar(500) DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `contact_email_source` varchar(500) DEFAULT NULL,
  `contact_phone` varchar(500) NOT NULL,
  `contact_phone_source` varchar(500) DEFAULT NULL,
  `company_name` varchar(500) DEFAULT NULL,
  `company_name_source` varchar(500) DEFAULT NULL,
  `company_website` varchar(500) DEFAULT NULL,
  `company_website_source` varchar(500) DEFAULT NULL,
  `company_email` varchar(500) DEFAULT NULL,
  `company_email_source` varchar(500) DEFAULT NULL,
  `company_phone` varchar(100) DEFAULT NULL,
  `company_phone_source` varchar(500) DEFAULT NULL,
  `property_name` varchar(500) DEFAULT NULL,
  `property_website` varchar(500) DEFAULT NULL,
  `property_source` varchar(500) DEFAULT NULL,
  `contact_us_form_link` varchar(500) DEFAULT NULL,
  `chk_contact_found` int(11) NOT NULL DEFAULT '0',
  `chk_contact_us_form` int(11) NOT NULL DEFAULT '0',
  `chk_site_not_valid` int(11) NOT NULL DEFAULT '0',
  `chk_site_down` int(11) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ipaddress` varchar(100) DEFAULT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `url_id` int(11) NOT NULL DEFAULT '0',
  `remarks` varchar(100) DEFAULT NULL,
  `address` varchar(2000) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `zip_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`email_entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1593 DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `master_data_entry_url`
--

DROP TABLE IF EXISTS `master_data_entry_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_data_entry_url` (
  `master_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(1000) DEFAULT '',
  `status` varchar(10) NOT NULL DEFAULT 'Active',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`master_data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45067 DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `master_email`
--

DROP TABLE IF EXISTS `master_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_email` (
  `master_email_id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(500) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `last_count` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '0',
  `host` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`master_email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `useremail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_role` int(11) DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'Active',
  `mobile_number` varchar(15) DEFAULT NULL,
  `approved_link` varchar(10) NOT NULL DEFAULT '0',
  `approved_link_scrap2` varchar(11) NOT NULL DEFAULT '0',
  `approved_link_scrap3` varchar(11) NOT NULL DEFAULT '0',
  `company_link` varchar(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=latin1 COMMENT='latin1_swedish_ci';
/*!40101 SET character_set_client = @saved_cs_client */;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-24 11:11:18
