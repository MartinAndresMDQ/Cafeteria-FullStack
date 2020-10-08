/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.14-MariaDB : Database - cafeteria
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cafeteria` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `cafeteria`;

/*Table structure for table `additional_order` */

DROP TABLE IF EXISTS `additional_order`;

CREATE TABLE `additional_order` (
  `order_id` int(11) NOT NULL,
  `additional_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`additional_id`),
  KEY `FKn2ee1k8w2j5y2j8oa5v6vif5t` (`additional_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

/*Data for the table `additional_order` */

/*Table structure for table `combination` */

DROP TABLE IF EXISTS `combination`;

CREATE TABLE `combination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `drink_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK483okl0d71ao6ked3simwngd6` (`drink_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

/*Data for the table `combination` */

/*Table structure for table `drink` */

DROP TABLE IF EXISTS `drink`;

CREATE TABLE `drink` (
  `DTYPE` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hidden` tinyint(4) DEFAULT 0,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `drink` */

insert  into `drink`(`DTYPE`,`id`,`hidden`,`name`,`price`) values 
('Drink',1,0,'Cafe',10.00),
('Drink',2,0,'Te',7.00),
('Additional',3,0,'Leche',5.00),
('Additional',4,0,'Cacao',10.00),
('Additional',5,0,'Chocolate',15.00),
('Additional',6,0,'Ron',20.00);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
