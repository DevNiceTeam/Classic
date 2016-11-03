/*
MySQL Data Transfer
Source Host: localhost
Source Database: l2ovdb
Target Host: localhost
Target Database: l2ovdb
Date: 05.11.2012 19:39:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for lfc_arena
-- ----------------------------
DROP TABLE IF EXISTS `lfc_arena`;
CREATE TABLE `lfc_arena` (
  `arena_id` int(3) NOT NULL,
  `name_ru` varchar(255) NOT NULL,
  `name_en` varchar(255) NOT NULL,
  `category` int(3) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `key_id` int(5) NOT NULL DEFAULT '0',
  `key_value` int(10) NOT NULL DEFAULT '0',
  `key_count` int(5) NOT NULL DEFAULT '0',
  `is_real_money` int(1) NOT NULL DEFAULT '0',
  `coupon_id` int(5) NOT NULL DEFAULT '0',
  `coupon_count` int(10) NOT NULL DEFAULT '0',
  `coupon_value` int(5) NOT NULL DEFAULT '0',
  `hero_type` varchar(255) NOT NULL DEFAULT 'none',
  `hero_lengh` bigint(10) NOT NULL DEFAULT '0',
  `min_level` int(3) NOT NULL DEFAULT '1',
  `max_level` int(3) NOT NULL DEFAULT '99',
  PRIMARY KEY (`arena_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lfc_global_values
-- ----------------------------
DROP TABLE IF EXISTS `lfc_global_values`;
CREATE TABLE `lfc_global_values` (
  `dummy_slot` int(1) NOT NULL,
  `all_battles` int(10) NOT NULL,
  `all_money` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lfc_out
-- ----------------------------
DROP TABLE IF EXISTS `lfc_out`;
CREATE TABLE `lfc_out` (
  `name` varchar(255) NOT NULL,
  `wmr` varchar(255) NOT NULL,
  `count_coupons` int(2) NOT NULL,
  `total_wmr_out` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lfc_stats_battle
-- ----------------------------
DROP TABLE IF EXISTS `lfc_stats_battle`;
CREATE TABLE `lfc_stats_battle` (
  `arena_id` int(3) NOT NULL,
  `arena_name_en` varchar(255) NOT NULL,
  `arena_name_ru` varchar(255) NOT NULL,
  `winner` varchar(255) NOT NULL,
  `looser` varchar(255) NOT NULL,
  `won_item` int(5) NOT NULL,
  `item_count` int(100) NOT NULL,
  `place_id` int(100) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lfc_stats_global
-- ----------------------------
DROP TABLE IF EXISTS `lfc_stats_global`;
CREATE TABLE `lfc_stats_global` (
  `char_name` varchar(255) NOT NULL,
  `win_count` int(10) NOT NULL,
  `loose_count` int(10) NOT NULL,
  `pay_battle_count` int(10) NOT NULL,
  `money_win` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `lfc_arena` VALUES ('1', 'Arena 1', 'Arena 1', '1', 'Cat 1', '21111', '50', '1', '1', '11111', '1', '100', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('2', 'Arena 2', 'Arena 2', '1', 'Cat 1', '21111', '50', '1', '1', '11111', '1', '100', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('3', 'Arena 3', 'Arena 3', '1', 'Cat 1', '21111', '50', '1', '1', '11111', '1', '100', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('4', 'Arena 4', 'Arena 4', '1', 'Cat 1', '21111', '50', '1', '1', '11111', '1', '100', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('5', 'Arena 5', 'Arena 5', '1', 'Cat 1', '21111', '50', '1', '1', '11111', '1', '100', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('6', 'Arena 6', 'Arena 6', '1', 'Cat 1', '0', '0', '0', '0', '57', '1', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('7', 'Arena 7', 'Arena 7', '1', 'Cat 1', '0', '0', '0', '0', '57', '1', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('8', 'Arena 8', 'Arena 8', '1', 'Cat 1', '0', '0', '0', '0', '57', '1', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('9', 'Arena 9', 'Arena 9', '1', 'Cat 1', '0', '0', '0', '0', '57', '1', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('10', 'Arena 10', 'Arena 10', '1', 'Cat 1', '0', '0', '0', '0', '57', '1', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('11', 'Arena 11', 'Arena 11', '2', 'Cat 2', '21112', '100', '1', '1', '11112', '1', '200', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('12', 'Arena 12', 'Arena 12', '2', 'Cat 2', '21112', '100', '1', '1', '11112', '1', '200', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('13', 'Arena 13', 'Arena 13', '2', 'Cat 2', '21112', '100', '1', '1', '11112', '1', '200', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('14', 'Arena 14', 'Arena 14', '2', 'Cat 2', '21112', '100', '1', '1', '11112', '1', '200', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('15', 'Arena 15', 'Arena 15', '2', 'Cat 2', '21112', '100', '1', '1', '11112', '1', '200', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('16', 'Arena 16', 'Arena 16', '2', 'Cat 2', '0', '0', '0', '0', '57', '2', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('17', 'Arena 17', 'Arena 17', '2', 'Cat 2', '0', '0', '0', '0', '57', '2', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('18', 'Arena 18', 'Arena 18', '2', 'Cat 2', '0', '0', '0', '0', '57', '2', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('19', 'Arena 19', 'Arena 19', '2', 'Cat 2', '0', '0', '0', '0', '57', '2', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('20', 'Arena 20', 'Arena 20', '2', 'Cat 2', '0', '0', '0', '0', '57', '2', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('21', 'Arena 21', 'Arena 21', '3', 'Cat 3', '21113', '150', '1', '1', '11113', '1', '300', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('22', 'Arena 22', 'Arena 22', '3', 'Cat 3', '21113', '150', '1', '1', '11113', '1', '300', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('23', 'Arena 23', 'Arena 23', '3', 'Cat 3', '21113', '150', '1', '1', '11113', '1', '300', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('24', 'Arena 24', 'Arena 24', '3', 'Cat 3', '21113', '150', '1', '1', '11113', '1', '300', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('25', 'Arena 25', 'Arena 25', '3', 'Cat 3', '21113', '150', '1', '1', '11113', '1', '300', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('26', 'Arena 26', 'Arena 26', '3', 'Cat 3', '0', '0', '0', '0', '57', '4', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('27', 'Arena 27', 'Arena 27', '3', 'Cat 3', '0', '0', '0', '0', '57', '4', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('28', 'Arena 28', 'Arena 28', '3', 'Cat 3', '0', '0', '0', '0', '57', '4', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('29', 'Arena 29', 'Arena 29', '3', 'Cat 3', '0', '0', '0', '0', '57', '4', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('30', 'Arena 30', 'Arena 30', '3', 'Cat 3', '0', '0', '0', '0', '57', '4', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('31', 'Arena 31', 'Arena 31', '4', 'Cat 4', '21114', '200', '1', '1', '11114', '1', '400', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('32', 'Arena 32', 'Arena 32', '4', 'Cat 4', '21114', '200', '1', '1', '11114', '1', '400', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('33', 'Arena 33', 'Arena 33', '4', 'Cat 4', '21114', '200', '1', '1', '11114', '1', '400', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('34', 'Arena 34', 'Arena 34', '4', 'Cat 4', '21114', '200', '1', '1', '11114', '1', '400', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('35', 'Arena 35', 'Arena 35', '4', 'Cat 4', '21114', '200', '1', '1', '11114', '1', '400', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('36', 'Arena 36', 'Arena 36', '4', 'Cat 4', '0', '0', '0', '0', '57', '8', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('37', 'Arena 37', 'Arena 37', '4', 'Cat 4', '0', '0', '0', '0', '57', '8', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('38', 'Arena 38', 'Arena 38', '4', 'Cat 4', '0', '0', '0', '0', '57', '8', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('39', 'Arena 39', 'Arena 39', '4', 'Cat 4', '0', '0', '0', '0', '57', '8', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('40', 'Arena 40', 'Arena 40', '4', 'Cat 4', '0', '0', '0', '0', '57', '8', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('41', 'Arena 41', 'Arena 41', '5', 'Cat 5', '21115', '250', '1', '1', '11115', '1', '500', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('42', 'Arena 42', 'Arena 42', '5', 'Cat 5', '21115', '250', '1', '1', '11115', '1', '500', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('43', 'Arena 43', 'Arena 43', '5', 'Cat 5', '21115', '250', '1', '1', '11115', '1', '500', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('44', 'Arena 44', 'Arena 44', '5', 'Cat 5', '21115', '250', '1', '1', '11115', '1', '500', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('45', 'Arena 45', 'Arena 45', '5', 'Cat 5', '21115', '250', '1', '1', '11115', '1', '500', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('46', 'Arena 46', 'Arena 46', '5', 'Cat 5', '0', '0', '0', '0', '57', '16', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('47', 'Arena 47', 'Arena 47', '5', 'Cat 5', '0', '0', '0', '0', '57', '16', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('48', 'Arena 48', 'Arena 48', '5', 'Cat 5', '0', '0', '0', '0', '57', '16', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('49', 'Arena 49', 'Arena 49', '5', 'Cat 5', '0', '0', '0', '0', '57', '16', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('50', 'Arena 50', 'Arena 50', '5', 'Cat 5', '0', '0', '0', '0', '57', '16', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('51', 'Arena 51', 'Arena 51', '6', 'Cat 6', '21116', '300', '1', '1', '11116', '1', '600', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('52', 'Arena 52', 'Arena 52', '6', 'Cat 6', '21116', '300', '1', '1', '11116', '1', '600', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('53', 'Arena 53', 'Arena 53', '6', 'Cat 6', '21116', '300', '1', '1', '11116', '1', '600', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('54', 'Arena 54', 'Arena 54', '6', 'Cat 6', '21116', '300', '1', '1', '11116', '1', '600', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('55', 'Arena 55', 'Arena 55', '6', 'Cat 6', '21116', '300', '1', '1', '11116', '1', '600', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('56', 'Arena 56', 'Arena 56', '6', 'Cat 6', '0', '0', '0', '0', '57', '32', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('57', 'Arena 57', 'Arena 57', '6', 'Cat 6', '0', '0', '0', '0', '57', '32', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('58', 'Arena 58', 'Arena 58', '6', 'Cat 6', '0', '0', '0', '0', '57', '32', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('59', 'Arena 59', 'Arena 59', '6', 'Cat 6', '0', '0', '0', '0', '57', '32', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_arena` VALUES ('60', 'Arena 60', 'Arena 60', '6', 'Cat 6', '0', '0', '0', '0', '57', '32', '0', 'none', '0', '1', '85');
INSERT INTO `lfc_global_values` VALUES ('1', '2', '100');
INSERT INTO `lfc_stats_battle` VALUES ('9', 'Arena 9', 'Arena 9', 'dddd', '123', '57', '1', '286');
INSERT INTO `lfc_stats_battle` VALUES ('1', 'Arena 1', 'Arena 1', 'dddd', '123', '11111', '1', '287');
INSERT INTO `lfc_stats_global` VALUES ('123', '0', '2', '1', '0');
INSERT INTO `lfc_stats_global` VALUES ('dddd', '2', '0', '1', '100');
