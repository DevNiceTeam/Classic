/*
MySQL Data Transfer
Source Host: localhost
Source Database: l2ovdb
Target Host: localhost
Target Database: l2ovdb
Date: 02.04.2012 0:06:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for pa_free_table
-- ----------------------------
DROP TABLE IF EXISTS `pa_free_table`;
CREATE TABLE `pa_free_table` (
  `account_name` varchar(255) NOT NULL,
  PRIMARY KEY (`account_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
