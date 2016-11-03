/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : l2ft

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-07-18 14:13:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bbs_clanbonus`
-- ----------------------------
DROP TABLE IF EXISTS `bbs_clanbonus`;
CREATE TABLE `bbs_clanbonus` (
  `name` text NOT NULL,
  `players_count` int(11) DEFAULT NULL,
  `reward_lvl` int(11) DEFAULT NULL,
  `reward_rep` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`(16))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bbs_clanbonus
-- ----------------------------
