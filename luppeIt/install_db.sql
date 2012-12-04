/*
Navicat MySQL Data Transfer

Source Server         : Local MySql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : luppe_it

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2012-11-28 01:53:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `action`
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `action_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of action
-- ----------------------------

-- ----------------------------
-- Table structure for `action_parameter`
-- ----------------------------
DROP TABLE IF EXISTS `action_parameter`;
CREATE TABLE `action_parameter` (
  `action_parameter_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_parameter_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `action_parameter_type_id` int(11) NOT NULL,
  `action_id` int(11) NOT NULL,
  PRIMARY KEY (`action_parameter_id`),
  KEY `action_parameter_type_id_fk1` (`action_parameter_type_id`),
  KEY `action_parameter_action_id_fk1` (`action_id`),
  CONSTRAINT `action_parameter_action_id_fk1` FOREIGN KEY (`action_id`) REFERENCES `action` (`action_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `action_parameter_type_id_fk1` FOREIGN KEY (`action_parameter_type_id`) REFERENCES `action_parameter_type` (`action_parameter_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of action_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for `action_parameter_type`
-- ----------------------------
DROP TABLE IF EXISTS `action_parameter_type`;
CREATE TABLE `action_parameter_type` (
  `action_parameter_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_parameter_type_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `action_paramter_type_class` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `action_parameter_type_class` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`action_parameter_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of action_parameter_type
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `category_status_id` int(11) NOT NULL,
  PRIMARY KEY (`category_id`),
  KEY `category_status_id_fk1` (`category_status_id`),
  CONSTRAINT `category_status_id_fk1` FOREIGN KEY (`category_status_id`) REFERENCES `category_status` (`category_status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Automotive', '1');
INSERT INTO `category` VALUES ('2', 'World News', '1');
INSERT INTO `category` VALUES ('3', 'Politics', '1');
INSERT INTO `category` VALUES ('4', 'Arts & Culture', '1');
INSERT INTO `category` VALUES ('5', 'Business & Investing', '1');
INSERT INTO `category` VALUES ('6', 'Sports', '1');
INSERT INTO `category` VALUES ('7', 'Movies', '1');
INSERT INTO `category` VALUES ('8', 'Music', '1');
INSERT INTO `category` VALUES ('9', 'Technology', '1');
INSERT INTO `category` VALUES ('10', 'Science', '1');
INSERT INTO `category` VALUES ('11', 'Web', '1');
INSERT INTO `category` VALUES ('12', 'Social Media', '1');
INSERT INTO `category` VALUES ('13', 'Fashion', '1');
INSERT INTO `category` VALUES ('14', 'Health', '1');
INSERT INTO `category` VALUES ('15', 'Travel', '1');

-- ----------------------------
-- Table structure for `category_status`
-- ----------------------------
DROP TABLE IF EXISTS `category_status`;
CREATE TABLE `category_status` (
  `category_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`category_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of category_status
-- ----------------------------
INSERT INTO `category_status` VALUES ('1', 'active');
INSERT INTO `category_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `country`
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `country_code` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', 'default', 'default');

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `resource_status_id` int(11) NOT NULL,
  PRIMARY KEY (`resource_id`),
  KEY `resource_status_id` (`resource_status_id`),
  CONSTRAINT `resource_status_id` FOREIGN KEY (`resource_status_id`) REFERENCES `resource_status` (`resource_status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', 'npr.org', '1');

-- ----------------------------
-- Table structure for `resource_status`
-- ----------------------------
DROP TABLE IF EXISTS `resource_status`;
CREATE TABLE `resource_status` (
  `resource_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`resource_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of resource_status
-- ----------------------------
INSERT INTO `resource_status` VALUES ('1', 'active');
INSERT INTO `resource_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `rss_resource`
-- ----------------------------
DROP TABLE IF EXISTS `rss_resource`;
CREATE TABLE `rss_resource` (
  `rss_resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `rss_resource_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `parent_resource_id` int(11) NOT NULL,
  `rss_resource_status_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `next_feed_date` bigint(20) NOT NULL,
  `update_interval_minute` int(11) NOT NULL,
  PRIMARY KEY (`rss_resource_id`),
  KEY `parent_resource_id_fk1` (`parent_resource_id`),
  KEY `rss_resource_status_id_fk1` (`rss_resource_status_id`),
  KEY `rss_resource_category_fk1` (`category_id`),
  CONSTRAINT `parent_resource_id_fk1` FOREIGN KEY (`parent_resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rss_resource_category_fk1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rss_resource_status_id_fk1` FOREIGN KEY (`rss_resource_status_id`) REFERENCES `rss_resource_status` (`rss_resource_status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of rss_resource
-- ----------------------------
INSERT INTO `rss_resource` VALUES ('1', 'npr.org | Politics', 'http://www.npr.org/rss/rss.php?id=1014', '1', '1', '3', '2012-11-26 00:00:00', '30');

-- ----------------------------
-- Table structure for `rss_resource_status`
-- ----------------------------
DROP TABLE IF EXISTS `rss_resource_status`;
CREATE TABLE `rss_resource_status` (
  `rss_resource_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `rss_resource_status_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`rss_resource_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of rss_resource_status
-- ----------------------------
INSERT INTO `rss_resource_status` VALUES ('1', 'active');
INSERT INTO `rss_resource_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `share`
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `share_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `description` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8_unicode_ci,
  `url` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `luppe_count` int(11) NOT NULL DEFAULT '0',
  `dig_count` int(11) NOT NULL DEFAULT '0',
  `view_count` int(11) NOT NULL DEFAULT '0',
  `category_id` int(11) NOT NULL,
  `share_status_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `last_modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`share_id`),
  KEY `share_status_id` (`share_status_id`),
  KEY `resource_id` (`resource_id`),
  KEY `user_id` (`user_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `resource_id` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `share_status_id` FOREIGN KEY (`share_status_id`) REFERENCES `share_status` (`share_status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of share
-- ----------------------------

-- ----------------------------
-- Table structure for `share_photo`
-- ----------------------------
DROP TABLE IF EXISTS `share_photo`;
CREATE TABLE `share_photo` (
  `share_photo_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `share_id` bigint(20) NOT NULL,
  PRIMARY KEY (`share_photo_id`),
  KEY `share_id_fk2` (`share_id`),
  CONSTRAINT `share_id_fk2` FOREIGN KEY (`share_id`) REFERENCES `share` (`share_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of share_photo
-- ----------------------------

-- ----------------------------
-- Table structure for `share_status`
-- ----------------------------
DROP TABLE IF EXISTS `share_status`;
CREATE TABLE `share_status` (
  `share_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `share_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`share_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of share_status
-- ----------------------------
INSERT INTO `share_status` VALUES ('1', 'active');
INSERT INTO `share_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `share_tag`
-- ----------------------------
DROP TABLE IF EXISTS `share_tag`;
CREATE TABLE `share_tag` (
  `share_tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `share_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  `truth` int(11) NOT NULL,
  PRIMARY KEY (`share_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tag_status_id` int(11) NOT NULL,
  PRIMARY KEY (`tag_id`),
  KEY `tag_status_id_fk1` (`tag_status_id`),
  CONSTRAINT `tag_status_id_fk1` FOREIGN KEY (`tag_status_id`) REFERENCES `tag_status` (`tag_status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'programming', '1');
INSERT INTO `tag` VALUES ('2', 'java', '1');
INSERT INTO `tag` VALUES ('3', 'computer', '1');
INSERT INTO `tag` VALUES ('4', 'engineering', '1');
INSERT INTO `tag` VALUES ('5', 'play framework', '1');

-- ----------------------------
-- Table structure for `tag_status`
-- ----------------------------
DROP TABLE IF EXISTS `tag_status`;
CREATE TABLE `tag_status` (
  `tag_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`tag_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tag_status
-- ----------------------------
INSERT INTO `tag_status` VALUES ('1', 'active');
INSERT INTO `tag_status` VALUES ('2', 'passive');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(11) DEFAULT NULL,
  `trust` int(11) NOT NULL DEFAULT '50',
  `user_status_id` int(11) NOT NULL,
  `country_id` int(11) DEFAULT NULL,
  `user_type_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_status_id` (`user_status_id`),
  KEY `country_id` (`country_id`),
  KEY `user_type_id` (`user_type_id`),
  CONSTRAINT `country_id` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_status_id` FOREIGN KEY (`user_status_id`) REFERENCES `user_status` (`user_status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_type_id` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'faruk.kuscan@gmail.com', '123456', 'faruk.kuscan@gmail.com', '0', '0', '2', '1', '1');
INSERT INTO `user` VALUES ('7', 'kkflmed@gmail.com', '123456', 'kkflmed@gmail.com', '0', '0', '2', '1', '1');

-- ----------------------------
-- Table structure for `user_action_1`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_1`;
CREATE TABLE `user_action_1` (
  `user_action_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_action_id`),
  KEY `action_id_fk1` (`action_id`),
  KEY `user_id_fk1` (`user_id`),
  CONSTRAINT `action_id_fk1` FOREIGN KEY (`action_id`) REFERENCES `action` (`action_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_action_1
-- ----------------------------

-- ----------------------------
-- Table structure for `user_action_parameter_value_1`
-- ----------------------------
DROP TABLE IF EXISTS `user_action_parameter_value_1`;
CREATE TABLE `user_action_parameter_value_1` (
  `user_action_id` bigint(20) NOT NULL,
  `action_parameter_id` int(11) NOT NULL,
  `parameter_value` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_action_id`,`action_parameter_id`),
  KEY `action_parameter_id_fk1` (`action_parameter_id`),
  CONSTRAINT `action_parameter_id_fk1` FOREIGN KEY (`action_parameter_id`) REFERENCES `action_parameter` (`action_parameter_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_action_id_fk1` FOREIGN KEY (`user_action_id`) REFERENCES `user_action_1` (`user_action_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_action_parameter_value_1
-- ----------------------------

-- ----------------------------
-- Table structure for `user_confirmation`
-- ----------------------------
DROP TABLE IF EXISTS `user_confirmation`;
CREATE TABLE `user_confirmation` (
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `confirmation_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_confirmation
-- ----------------------------
INSERT INTO `user_confirmation` VALUES ('faruk.kuscan@gmail.com', 'hMXDnMToegOPwFHXwuR6');
INSERT INTO `user_confirmation` VALUES ('kkflmed@gmail.com', 'LtuW3WAzSIq7g5tiG7yS');

-- ----------------------------
-- Table structure for `user_status`
-- ----------------------------
DROP TABLE IF EXISTS `user_status`;
CREATE TABLE `user_status` (
  `user_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_status_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_status
-- ----------------------------
INSERT INTO `user_status` VALUES ('1', 'registered');
INSERT INTO `user_status` VALUES ('2', 'active');

-- ----------------------------
-- Table structure for `user_type`
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type` (
  `user_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO `user_type` VALUES ('1', 'default');
