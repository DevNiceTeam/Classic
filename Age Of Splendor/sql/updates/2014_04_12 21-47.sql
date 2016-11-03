/*
MySQL Data Transfer
Source Host: localhost
Source Database: l2ovdb
Target Host: localhost
Target Database: l2ovdb
Date: 06.10.2012 18:26:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for custom_heroes
-- ----------------------------
DROP TABLE IF EXISTS `custom_heroes`;
CREATE TABLE `custom_heroes` (
  `hero_id` int(11) NOT NULL,
  `time` bigint(11) NOT NULL,
  PRIMARY KEY (`hero_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
