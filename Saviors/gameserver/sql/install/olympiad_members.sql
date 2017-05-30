/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : classic

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2017-04-18 00:01:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `olympiad_members`
-- ----------------------------
DROP TABLE IF EXISTS `olympiad_members`;
CREATE TABLE `olympiad_members` (
  `char_id` int(11) NOT NULL DEFAULT '0',
  `olympiad_points` smallint(6) NOT NULL DEFAULT '0',
  `olympiad_points_past` smallint(6) NOT NULL DEFAULT '0',
  `olympiad_points_past_static` smallint(6) NOT NULL DEFAULT '0',
  `competitions_done` smallint(6) unsigned NOT NULL DEFAULT '0',
  `competitions_win` smallint(6) unsigned NOT NULL DEFAULT '0',
  `competitions_loose` smallint(6) unsigned NOT NULL DEFAULT '0',
  `game_count` int(11) NOT NULL,
  PRIMARY KEY (`char_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of olympiad_members
-- ----------------------------
