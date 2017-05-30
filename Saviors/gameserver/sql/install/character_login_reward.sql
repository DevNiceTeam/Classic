/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : classic

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2017-02-20 18:22:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `character_login_reward`
-- ----------------------------
DROP TABLE IF EXISTS `character_login_reward`;
CREATE TABLE `character_login_reward` (
  `player_id` int(11) NOT NULL,
  `last_date_reward` date DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of character_login_reward
-- ----------------------------
