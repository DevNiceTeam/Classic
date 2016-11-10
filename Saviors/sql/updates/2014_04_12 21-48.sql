/*
MySQL Data Transfer
Source Host: localhost
Source Database: l2ovdb
Target Host: localhost
Target Database: l2ovdb
Date: 03.02.2014 20:30:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for hidden_items
-- ----------------------------
DROP TABLE IF EXISTS `hidden_items`;
CREATE TABLE `hidden_items` (
  `obj_id` int(10) NOT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
