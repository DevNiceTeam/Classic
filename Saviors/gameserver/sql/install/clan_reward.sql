/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : classic

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2017-04-17 21:10:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `clan_reward`
-- ----------------------------
DROP TABLE IF EXISTS `clan_reward`;
CREATE TABLE `clan_reward` (
  `clan_id` int(11) NOT NULL,
  `exp` int(11) DEFAULT NULL,
  `login` int(11) DEFAULT NULL,
  `yesterday_exp` int(11) DEFAULT NULL,
  `yesterday_login` int(11) DEFAULT NULL,
  PRIMARY KEY (`clan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clan_reward
-- ----------------------------
