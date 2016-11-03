/*
Navicat MySQL Data Transfer

Source Server         : dddd
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : dddd

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2016-09-19 15:03:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_bonus
-- ----------------------------
DROP TABLE IF EXISTS `account_bonus`;
CREATE TABLE `account_bonus` (
  `account` varchar(255) NOT NULL,
  `bonus` double NOT NULL,
  `bonus_expire` int(11) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of account_bonus
-- ----------------------------

-- ----------------------------
-- Table structure for account_variables
-- ----------------------------
DROP TABLE IF EXISTS `account_variables`;
CREATE TABLE `account_variables` (
  `account_name` varchar(45) NOT NULL DEFAULT '',
  `var` varchar(50) NOT NULL DEFAULT '',
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account_name`,`var`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_variables
-- ----------------------------

-- ----------------------------
-- Table structure for ally_data
-- ----------------------------
DROP TABLE IF EXISTS `ally_data`;
CREATE TABLE `ally_data` (
  `ally_id` int(11) NOT NULL DEFAULT '0',
  `ally_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `leader_id` int(11) NOT NULL DEFAULT '0',
  `expelled_member` int(10) unsigned NOT NULL DEFAULT '0',
  `crest` varbinary(192) DEFAULT NULL,
  PRIMARY KEY (`ally_id`),
  KEY `leader_id` (`leader_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ally_data
-- ----------------------------

-- ----------------------------
-- Table structure for bans
-- ----------------------------
DROP TABLE IF EXISTS `bans`;
CREATE TABLE `bans` (
  `account_name` varchar(45) DEFAULT NULL,
  `obj_Id` int(10) unsigned NOT NULL DEFAULT '0',
  `baned` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `unban` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `reason` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `GM` varchar(35) CHARACTER SET utf8 DEFAULT NULL,
  `endban` int(10) unsigned DEFAULT NULL,
  `karma` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of bans
-- ----------------------------

-- ----------------------------
-- Table structure for ban_hwid
-- ----------------------------
DROP TABLE IF EXISTS `ban_hwid`;
CREATE TABLE `ban_hwid` (
  `hwid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ban_hwid
-- ----------------------------

-- ----------------------------
-- Table structure for bbs_buffs
-- ----------------------------
DROP TABLE IF EXISTS `bbs_buffs`;
CREATE TABLE `bbs_buffs` (
  `char_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(256) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `skills` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`char_id`,`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of bbs_buffs
-- ----------------------------
INSERT INTO `bbs_buffs` VALUES ('0', 'Fighter;Ð’Ð¾Ð¸Ð½Ñƒ', '1068,1068,1040,1204,1077,1062,1087,1044,1268,1240,1242,1048,1045,1036,1086,1388,1397');
INSERT INTO `bbs_buffs` VALUES ('0', 'Mystic;ÐœÐ°Ð³Ñƒ', '1040,1040,1078,1204,1085,1062,1087,1044,1059,1048,1045,1036,1389,1397,1303');
INSERT INTO `bbs_buffs` VALUES ('0', 'Resistance;Ð¡Ð¾Ð¿Ñ€Ð¾Ñ‚Ð¸Ð²Ð»ÐµÐ½Ð¸Ðµ', '1035,1035,1033,1032,1392,1393,1259,270');
INSERT INTO `bbs_buffs` VALUES ('0', 'Dance\'s / Song\'s;ÐŸÐµÑÐ½Ð¸ / Ð¢Ð°Ð½Ñ†Ñ‹', '267,270,268,269,265,304,264,266,305,274,277,272,273,276,271,275,311,310');
INSERT INTO `bbs_buffs` VALUES ('0', 'Maximum speed;ÐœÐ°ÐºÑÐ¸Ð¼ÑƒÐ¼ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸', '1204,1204,1062,268');

-- ----------------------------
-- Table structure for bbs_clanbonus
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

-- ----------------------------
-- Table structure for bbs_clannotice
-- ----------------------------
DROP TABLE IF EXISTS `bbs_clannotice`;
CREATE TABLE `bbs_clannotice` (
  `clan_id` int(10) unsigned NOT NULL,
  `type` smallint(6) NOT NULL DEFAULT '0',
  `notice` text NOT NULL,
  PRIMARY KEY (`clan_id`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bbs_clannotice
-- ----------------------------

-- ----------------------------
-- Table structure for bbs_favorites
-- ----------------------------
DROP TABLE IF EXISTS `bbs_favorites`;
CREATE TABLE `bbs_favorites` (
  `fav_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `object_id` int(15) unsigned NOT NULL,
  `fav_bypass` varchar(35) NOT NULL,
  `fav_title` varchar(100) NOT NULL,
  `add_date` int(15) unsigned NOT NULL,
  PRIMARY KEY (`fav_id`),
  UNIQUE KEY `ix_obj_id_bypass` (`object_id`,`fav_bypass`),
  KEY `object_id` (`object_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bbs_favorites
-- ----------------------------

-- ----------------------------
-- Table structure for bbs_mail
-- ----------------------------
DROP TABLE IF EXISTS `bbs_mail`;
CREATE TABLE `bbs_mail` (
  `message_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `to_name` varchar(35) NOT NULL,
  `to_object_id` int(10) unsigned NOT NULL,
  `from_name` varchar(35) NOT NULL,
  `from_object_id` int(10) unsigned NOT NULL,
  `title` varchar(128) NOT NULL,
  `message` text NOT NULL,
  `post_date` int(15) unsigned NOT NULL,
  `read` smallint(6) NOT NULL DEFAULT '0',
  `box_type` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `to_object_id` (`to_object_id`),
  KEY `from_object_id` (`from_object_id`),
  KEY `read` (`read`),
  KEY `box_type` (`box_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bbs_mail
-- ----------------------------

-- ----------------------------
-- Table structure for bbs_memo
-- ----------------------------
DROP TABLE IF EXISTS `bbs_memo`;
CREATE TABLE `bbs_memo` (
  `memo_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(45) NOT NULL,
  `char_name` varchar(35) NOT NULL,
  `ip` varchar(16) NOT NULL,
  `title` varchar(128) NOT NULL,
  `memo` text NOT NULL,
  `post_date` int(15) unsigned NOT NULL,
  PRIMARY KEY (`memo_id`),
  KEY `account_name` (`account_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bbs_memo
-- ----------------------------

-- ----------------------------
-- Table structure for bbs_teleport_bm
-- ----------------------------
DROP TABLE IF EXISTS `bbs_teleport_bm`;
CREATE TABLE `bbs_teleport_bm` (
  `char_id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `x` mediumint(9) NOT NULL,
  `y` mediumint(9) NOT NULL,
  `z` mediumint(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bbs_teleport_bm
-- ----------------------------

-- ----------------------------
-- Table structure for castle
-- ----------------------------
DROP TABLE IF EXISTS `castle`;
CREATE TABLE `castle` (
  `id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `name` varchar(25) NOT NULL,
  `tax_percent` int(11) NOT NULL,
  `treasury` bigint(20) unsigned NOT NULL DEFAULT '0',
  `town_id` int(11) NOT NULL,
  `last_siege_date` int(10) unsigned NOT NULL,
  `own_date` int(10) unsigned NOT NULL,
  `siege_date` int(10) unsigned NOT NULL,
  `side` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of castle
-- ----------------------------
INSERT INTO `castle` VALUES ('1', 'Gludio', '0', '0', '6', '0', '0', '0', '0');
INSERT INTO `castle` VALUES ('2', 'Dion', '0', '0', '8', '0', '0', '0', '0');
INSERT INTO `castle` VALUES ('3', 'Giran', '0', '0', '9', '0', '0', '0', '0');
INSERT INTO `castle` VALUES ('4', 'Oren', '0', '0', '10', '0', '0', '0', '0');
INSERT INTO `castle` VALUES ('5', 'Aden', '0', '0', '11', '0', '0', '0', '0');
INSERT INTO `castle` VALUES ('6', 'Innadril', '0', '0', '13', '0', '0', '0', '0');
INSERT INTO `castle` VALUES ('7', 'Goddard', '0', '0', '15', '0', '0', '0', '0');
INSERT INTO `castle` VALUES ('8', 'Rune', '0', '0', '14', '0', '0', '0', '0');
INSERT INTO `castle` VALUES ('9', 'Schuttgart', '0', '0', '16', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for castle_damage_zones
-- ----------------------------
DROP TABLE IF EXISTS `castle_damage_zones`;
CREATE TABLE `castle_damage_zones` (
  `residence_id` int(11) NOT NULL,
  `zone` varchar(255) NOT NULL,
  PRIMARY KEY (`residence_id`,`zone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of castle_damage_zones
-- ----------------------------

-- ----------------------------
-- Table structure for castle_door_upgrade
-- ----------------------------
DROP TABLE IF EXISTS `castle_door_upgrade`;
CREATE TABLE `castle_door_upgrade` (
  `door_id` int(11) NOT NULL,
  `hp` int(11) NOT NULL,
  PRIMARY KEY (`door_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of castle_door_upgrade
-- ----------------------------

-- ----------------------------
-- Table structure for castle_hired_guards
-- ----------------------------
DROP TABLE IF EXISTS `castle_hired_guards`;
CREATE TABLE `castle_hired_guards` (
  `residence_id` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `x` int(11) NOT NULL DEFAULT '0',
  `y` int(11) NOT NULL DEFAULT '0',
  `z` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`residence_id`,`item_id`,`x`,`y`,`z`),
  KEY `id` (`residence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of castle_hired_guards
-- ----------------------------

-- ----------------------------
-- Table structure for castle_manor_procure
-- ----------------------------
DROP TABLE IF EXISTS `castle_manor_procure`;
CREATE TABLE `castle_manor_procure` (
  `castle_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `crop_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `can_buy` bigint(20) NOT NULL DEFAULT '0',
  `start_buy` bigint(20) NOT NULL DEFAULT '0',
  `price` bigint(20) NOT NULL DEFAULT '0',
  `reward_type` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `period` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`castle_id`,`crop_id`,`period`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of castle_manor_procure
-- ----------------------------

-- ----------------------------
-- Table structure for castle_manor_production
-- ----------------------------
DROP TABLE IF EXISTS `castle_manor_production`;
CREATE TABLE `castle_manor_production` (
  `castle_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `seed_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `can_produce` bigint(20) NOT NULL DEFAULT '0',
  `start_produce` bigint(20) NOT NULL DEFAULT '0',
  `seed_price` bigint(20) NOT NULL DEFAULT '0',
  `period` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`castle_id`,`seed_id`,`period`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of castle_manor_production
-- ----------------------------

-- ----------------------------
-- Table structure for characters
-- ----------------------------
DROP TABLE IF EXISTS `characters`;
CREATE TABLE `characters` (
  `account_name` varchar(45) NOT NULL DEFAULT '',
  `obj_Id` int(11) NOT NULL DEFAULT '0',
  `char_name` varchar(35) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `face` tinyint(3) unsigned DEFAULT NULL,
  `beautyFace` smallint(5) unsigned DEFAULT NULL,
  `hairStyle` tinyint(3) unsigned DEFAULT NULL,
  `beautyHairStyle` smallint(5) unsigned DEFAULT NULL,
  `hairColor` tinyint(3) unsigned DEFAULT NULL,
  `beautyHairColor` smallint(5) unsigned DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `x` mediumint(9) DEFAULT NULL,
  `y` mediumint(9) DEFAULT NULL,
  `z` mediumint(9) DEFAULT NULL,
  `karma` int(11) DEFAULT NULL,
  `pvpkills` int(11) DEFAULT NULL,
  `pkkills` int(11) DEFAULT NULL,
  `clanid` int(11) DEFAULT NULL,
  `createtime` int(10) unsigned NOT NULL DEFAULT '0',
  `deletetime` int(10) unsigned NOT NULL DEFAULT '0',
  `title` varchar(16) CHARACTER SET utf8 DEFAULT NULL,
  `rec_have` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `rec_left` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `accesslevel` tinyint(4) DEFAULT NULL,
  `online` tinyint(1) DEFAULT NULL,
  `onlinetime` int(10) unsigned NOT NULL DEFAULT '0',
  `lastAccess` int(10) unsigned NOT NULL DEFAULT '0',
  `leaveclan` int(10) unsigned NOT NULL DEFAULT '0',
  `deleteclan` int(10) unsigned NOT NULL DEFAULT '0',
  `nochannel` int(11) NOT NULL DEFAULT '0',
  `pledge_type` smallint(6) NOT NULL DEFAULT '-128',
  `pledge_rank` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `lvl_joined_academy` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `apprentice` int(10) unsigned NOT NULL DEFAULT '0',
  `key_bindings` varbinary(8192) DEFAULT NULL,
  `pcBangPoints` int(11) NOT NULL DEFAULT '0',
  `fame` int(11) NOT NULL DEFAULT '0',
  `bookmarks` int(10) unsigned NOT NULL DEFAULT '0',
  `bot_rating` int(11) NOT NULL DEFAULT '0',
  `used_world_chat_points` int(11) NOT NULL DEFAULT '0',
  `hide_head_accessories` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`obj_Id`),
  UNIQUE KEY `char_name` (`char_name`),
  KEY `account_name` (`account_name`),
  KEY `clanid` (`clanid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of characters
-- ----------------------------

-- ----------------------------
-- Table structure for character_blocklist
-- ----------------------------
DROP TABLE IF EXISTS `character_blocklist`;
CREATE TABLE `character_blocklist` (
  `obj_Id` int(11) NOT NULL,
  `target_Id` int(11) NOT NULL,
  `memo` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`obj_Id`,`target_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_blocklist
-- ----------------------------

-- ----------------------------
-- Table structure for character_bookmarks
-- ----------------------------
DROP TABLE IF EXISTS `character_bookmarks`;
CREATE TABLE `character_bookmarks` (
  `char_Id` int(11) NOT NULL,
  `idx` tinyint(3) unsigned NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 NOT NULL,
  `acronym` varchar(4) CHARACTER SET utf8 NOT NULL,
  `icon` tinyint(3) unsigned NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `z` int(11) NOT NULL,
  PRIMARY KEY (`char_Id`,`idx`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_bookmarks
-- ----------------------------

-- ----------------------------
-- Table structure for character_effects_save
-- ----------------------------
DROP TABLE IF EXISTS `character_effects_save`;
CREATE TABLE `character_effects_save` (
  `object_id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `skill_level` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `left_time` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `is_self` tinyint(1) NOT NULL,
  PRIMARY KEY (`object_id`,`skill_id`,`id`,`is_self`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_effects_save
-- ----------------------------

-- ----------------------------
-- Table structure for character_friends
-- ----------------------------
DROP TABLE IF EXISTS `character_friends`;
CREATE TABLE `character_friends` (
  `char_id` int(11) NOT NULL DEFAULT '0',
  `friend_id` int(11) NOT NULL DEFAULT '0',
  `memo` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`char_id`,`friend_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_friends
-- ----------------------------

-- ----------------------------
-- Table structure for character_group_reuse
-- ----------------------------
DROP TABLE IF EXISTS `character_group_reuse`;
CREATE TABLE `character_group_reuse` (
  `object_id` int(11) NOT NULL,
  `reuse_group` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `end_time` bigint(20) NOT NULL,
  `reuse` bigint(20) NOT NULL,
  PRIMARY KEY (`object_id`,`reuse_group`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_group_reuse
-- ----------------------------

-- ----------------------------
-- Table structure for character_hennas
-- ----------------------------
DROP TABLE IF EXISTS `character_hennas`;
CREATE TABLE `character_hennas` (
  `char_obj_id` int(11) NOT NULL DEFAULT '0',
  `symbol_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `class_index` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `draw_time` int(10) unsigned NOT NULL DEFAULT '0',
  `is_premium` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`char_obj_id`,`class_index`,`draw_time`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_hennas
-- ----------------------------

-- ----------------------------
-- Table structure for character_instances
-- ----------------------------
DROP TABLE IF EXISTS `character_instances`;
CREATE TABLE `character_instances` (
  `obj_id` int(11) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL DEFAULT '0',
  `reuse` bigint(20) NOT NULL DEFAULT '0',
  UNIQUE KEY `prim` (`obj_id`,`id`),
  KEY `obj_id` (`obj_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_instances
-- ----------------------------

-- ----------------------------
-- Table structure for character_macroses
-- ----------------------------
DROP TABLE IF EXISTS `character_macroses`;
CREATE TABLE `character_macroses` (
  `char_obj_id` int(11) NOT NULL DEFAULT '0',
  `id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `icon` tinyint(3) unsigned DEFAULT NULL,
  `name` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `descr` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `acronym` varchar(4) CHARACTER SET utf8 DEFAULT NULL,
  `commands` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`char_obj_id`,`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_macroses
-- ----------------------------

-- ----------------------------
-- Table structure for character_mail
-- ----------------------------
DROP TABLE IF EXISTS `character_mail`;
CREATE TABLE `character_mail` (
  `char_id` int(11) NOT NULL,
  `message_id` int(11) NOT NULL,
  `is_sender` tinyint(1) NOT NULL,
  PRIMARY KEY (`char_id`,`message_id`),
  KEY `message_id` (`message_id`),
  CONSTRAINT `character_mail_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `mail` (`message_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_mail
-- ----------------------------

-- ----------------------------
-- Table structure for character_mentoring
-- ----------------------------
DROP TABLE IF EXISTS `character_mentoring`;
CREATE TABLE `character_mentoring` (
  `mentor` int(11) NOT NULL DEFAULT '0',
  `mentee` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`mentor`,`mentee`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_mentoring
-- ----------------------------

-- ----------------------------
-- Table structure for character_minigame_score
-- ----------------------------
DROP TABLE IF EXISTS `character_minigame_score`;
CREATE TABLE `character_minigame_score` (
  `object_id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`object_id`,`score`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_minigame_score
-- ----------------------------

-- ----------------------------
-- Table structure for character_post_friends
-- ----------------------------
DROP TABLE IF EXISTS `character_post_friends`;
CREATE TABLE `character_post_friends` (
  `object_id` int(11) NOT NULL,
  `post_friend` int(11) NOT NULL,
  PRIMARY KEY (`object_id`,`post_friend`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_post_friends
-- ----------------------------

-- ----------------------------
-- Table structure for character_premium_items
-- ----------------------------
DROP TABLE IF EXISTS `character_premium_items`;
CREATE TABLE `character_premium_items` (
  `char_id` int(11) NOT NULL,
  `receive_time` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_count` bigint(20) unsigned NOT NULL,
  `sender` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`char_id`,`receive_time`,`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_premium_items
-- ----------------------------

-- ----------------------------
-- Table structure for character_product_history
-- ----------------------------
DROP TABLE IF EXISTS `character_product_history`;
CREATE TABLE `character_product_history` (
  `char_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `last_purchase_time` int(11) NOT NULL,
  PRIMARY KEY (`char_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_product_history
-- ----------------------------

-- ----------------------------
-- Table structure for character_quests
-- ----------------------------
DROP TABLE IF EXISTS `character_quests`;
CREATE TABLE `character_quests` (
  `char_id` int(11) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL,
  `var` varchar(40) CHARACTER SET utf8 NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`char_id`,`id`,`var`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_quests
-- ----------------------------

-- ----------------------------
-- Table structure for character_recipebook
-- ----------------------------
DROP TABLE IF EXISTS `character_recipebook`;
CREATE TABLE `character_recipebook` (
  `char_id` int(11) NOT NULL DEFAULT '0',
  `id` smallint(5) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`char_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_recipebook
-- ----------------------------

-- ----------------------------
-- Table structure for character_shortcuts
-- ----------------------------
DROP TABLE IF EXISTS `character_shortcuts`;
CREATE TABLE `character_shortcuts` (
  `object_id` int(11) NOT NULL DEFAULT '0',
  `slot` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `page` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `type` tinyint(3) unsigned DEFAULT NULL,
  `shortcut_id` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `class_index` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `character_type` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`object_id`,`slot`,`page`,`class_index`),
  KEY `shortcut_id` (`shortcut_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_shortcuts
-- ----------------------------

-- ----------------------------
-- Table structure for character_skills
-- ----------------------------
DROP TABLE IF EXISTS `character_skills`;
CREATE TABLE `character_skills` (
  `char_obj_id` int(11) NOT NULL DEFAULT '0',
  `skill_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `skill_level` smallint(5) unsigned NOT NULL DEFAULT '0',
  `class_index` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`char_obj_id`,`skill_id`,`class_index`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_skills
-- ----------------------------

-- ----------------------------
-- Table structure for character_skills_save
-- ----------------------------
DROP TABLE IF EXISTS `character_skills_save`;
CREATE TABLE `character_skills_save` (
  `char_obj_id` int(11) NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_level` smallint(5) unsigned NOT NULL DEFAULT '0',
  `class_index` smallint(6) NOT NULL DEFAULT '0',
  `end_time` bigint(20) NOT NULL DEFAULT '0',
  `reuse_delay_org` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`char_obj_id`,`skill_id`,`class_index`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_skills_save
-- ----------------------------

-- ----------------------------
-- Table structure for character_subclasses
-- ----------------------------
DROP TABLE IF EXISTS `character_subclasses`;
CREATE TABLE `character_subclasses` (
  `char_obj_id` int(11) NOT NULL,
  `class_id` tinyint(3) unsigned NOT NULL,
  `level` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `exp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `sp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `curHp` decimal(11,4) unsigned NOT NULL DEFAULT '0.0000',
  `curMp` decimal(11,4) unsigned NOT NULL DEFAULT '0.0000',
  `curCp` decimal(11,4) unsigned NOT NULL DEFAULT '0.0000',
  `maxHp` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `maxMp` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `maxCp` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`char_obj_id`,`class_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_subclasses
-- ----------------------------

-- ----------------------------
-- Table structure for character_summons_save
-- ----------------------------
DROP TABLE IF EXISTS `character_summons_save`;
CREATE TABLE `character_summons_save` (
  `owner_obj_id` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_id` smallint(5) unsigned NOT NULL,
  `summon_index` smallint(5) unsigned NOT NULL,
  `skill_level` smallint(5) unsigned NOT NULL,
  `curHp` mediumint(8) unsigned DEFAULT NULL,
  `curMp` mediumint(8) unsigned DEFAULT NULL,
  `time` int(10) unsigned NOT NULL,
  PRIMARY KEY (`owner_obj_id`,`skill_id`,`summon_index`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_summons_save
-- ----------------------------

-- ----------------------------
-- Table structure for character_variables
-- ----------------------------
DROP TABLE IF EXISTS `character_variables`;
CREATE TABLE `character_variables` (
  `obj_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(86) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `value` varchar(300) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `expire_time` bigint(20) NOT NULL DEFAULT '0',
  UNIQUE KEY `prim` (`obj_id`,`name`),
  KEY `obj_id` (`obj_id`),
  KEY `name` (`name`),
  KEY `value` (`value`),
  KEY `expire_time` (`expire_time`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of character_variables
-- ----------------------------

-- ----------------------------
-- Table structure for clanhall
-- ----------------------------
DROP TABLE IF EXISTS `clanhall`;
CREATE TABLE `clanhall` (
  `id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `name` varchar(40) NOT NULL DEFAULT '',
  `last_siege_date` int(10) unsigned NOT NULL,
  `own_date` int(10) unsigned NOT NULL,
  `siege_date` int(10) unsigned NOT NULL,
  `auction_min_bid` bigint(20) NOT NULL,
  `auction_length` int(11) NOT NULL,
  `auction_desc` text,
  `cycle` int(11) NOT NULL,
  `paid_cycle` int(11) NOT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clanhall
-- ----------------------------
INSERT INTO `clanhall` VALUES ('21', 'Fortress of Resistance', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('22', 'Moonstone Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('23', 'Onyx Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('24', 'Topaz Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('25', 'Ruby Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('26', 'Crystal Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('27', 'Onyx Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('28', 'Sapphire Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('29', 'Moonstone Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('30', 'Emerald Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('31', 'The Atramental Barracks', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('32', 'The Scarlet Barracks', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('33', 'The Viridian Barracks', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('34', 'Devastated Castle', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('35', 'Bandit Stronghold', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('36', 'The Golden Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('37', 'The Silver Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('38', 'The Mithril Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('39', 'Silver Manor', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('40', 'Gold Manor', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('41', 'The Bronze Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('42', 'The Golden Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('43', 'The Silver Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('44', 'The Mithril Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('45', 'The Bronze Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('46', 'Silver Manor', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('47', 'Moonstone Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('48', 'Onyx Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('49', 'Emerald Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('50', 'Sapphire Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('51', 'Mont Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('52', 'Astaire Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('53', 'Aria Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('54', 'Yiana Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('55', 'Roien Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('56', 'Luna Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('57', 'Traban Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('58', 'Eisen Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('59', 'Heavy Metal Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('60', 'Molten Ore Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('61', 'Titan Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('62', 'Rainbow Springs', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('63', 'Wild Beast Reserve', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO `clanhall` VALUES ('64', 'Fortress of the Dead', '0', '0', '0', '0', '0', null, '0', '0');

-- ----------------------------
-- Table structure for clan_data
-- ----------------------------
DROP TABLE IF EXISTS `clan_data`;
CREATE TABLE `clan_data` (
  `clan_id` int(11) NOT NULL DEFAULT '0',
  `clan_level` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `hasCastle` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `hasHideout` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `ally_id` int(11) NOT NULL DEFAULT '0',
  `crest` varbinary(256) DEFAULT NULL,
  `reputation_score` int(11) NOT NULL DEFAULT '0',
  `warehouse` int(11) NOT NULL DEFAULT '0',
  `expelled_member` int(10) unsigned NOT NULL DEFAULT '0',
  `leaved_ally` int(10) unsigned NOT NULL DEFAULT '0',
  `dissolved_ally` int(10) unsigned NOT NULL DEFAULT '0',
  `auction_bid_at` int(11) NOT NULL DEFAULT '0',
  `academy_graduates` int(11) NOT NULL DEFAULT '0',
  `castle_defend_count` int(11) NOT NULL DEFAULT '0',
  `disband_end` int(11) NOT NULL DEFAULT '0',
  `disband_penalty` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`clan_id`),
  KEY `ally_id` (`ally_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clan_data
-- ----------------------------

-- ----------------------------
-- Table structure for clan_largecrests
-- ----------------------------
DROP TABLE IF EXISTS `clan_largecrests`;
CREATE TABLE `clan_largecrests` (
  `clan_id` int(11) NOT NULL DEFAULT '0',
  `crest_part` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `data` blob,
  PRIMARY KEY (`clan_id`,`crest_part`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clan_largecrests
-- ----------------------------

-- ----------------------------
-- Table structure for clan_leader_request
-- ----------------------------
DROP TABLE IF EXISTS `clan_leader_request`;
CREATE TABLE `clan_leader_request` (
  `clan_id` int(11) NOT NULL,
  `new_leader_id` int(11) NOT NULL,
  `time` bigint(20) NOT NULL,
  PRIMARY KEY (`clan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clan_leader_request
-- ----------------------------

-- ----------------------------
-- Table structure for clan_privs
-- ----------------------------
DROP TABLE IF EXISTS `clan_privs`;
CREATE TABLE `clan_privs` (
  `clan_id` int(11) NOT NULL DEFAULT '0',
  `rank` int(11) NOT NULL DEFAULT '0',
  `privilleges` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`clan_id`,`rank`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clan_privs
-- ----------------------------

-- ----------------------------
-- Table structure for clan_search_clan_applicants
-- ----------------------------
DROP TABLE IF EXISTS `clan_search_clan_applicants`;
CREATE TABLE `clan_search_clan_applicants` (
  `char_id` int(11) NOT NULL,
  `preffered_clan_id` int(11) NOT NULL,
  `char_name` varchar(255) NOT NULL,
  `char_level` int(11) NOT NULL,
  `char_class_id` int(11) NOT NULL,
  `search_type` enum('SLT_FRIEND_LIST','SLT_PLEDGE_MEMBER_LIST','SLT_ADDITIONAL_FRIEND_LIST','SLT_ADDITIONAL_LIST','SLT_ANY') NOT NULL DEFAULT 'SLT_ANY',
  `desc` varchar(255) NOT NULL DEFAULT '',
  `timestamp` int(11) DEFAULT NULL,
  PRIMARY KEY (`char_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clan_search_clan_applicants
-- ----------------------------

-- ----------------------------
-- Table structure for clan_search_registered_clans
-- ----------------------------
DROP TABLE IF EXISTS `clan_search_registered_clans`;
CREATE TABLE `clan_search_registered_clans` (
  `clan_id` int(11) NOT NULL,
  `search_type` enum('SLT_FRIEND_LIST','SLT_PLEDGE_MEMBER_LIST','SLT_ADDITIONAL_FRIEND_LIST','SLT_ADDITIONAL_LIST','SLT_ANY') NOT NULL DEFAULT 'SLT_ANY',
  `title` varchar(64) NOT NULL DEFAULT '',
  `desc` varchar(255) NOT NULL,
  `timestamp` int(11) DEFAULT NULL,
  PRIMARY KEY (`clan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clan_search_registered_clans
-- ----------------------------

-- ----------------------------
-- Table structure for clan_search_waiting_players
-- ----------------------------
DROP TABLE IF EXISTS `clan_search_waiting_players`;
CREATE TABLE `clan_search_waiting_players` (
  `char_id` int(11) NOT NULL,
  `char_name` varchar(255) NOT NULL,
  `char_level` int(11) NOT NULL,
  `char_class_id` int(11) NOT NULL,
  `search_type` enum('SLT_FRIEND_LIST','SLT_PLEDGE_MEMBER_LIST','SLT_ADDITIONAL_FRIEND_LIST','SLT_ADDITIONAL_LIST','SLT_ANY') DEFAULT NULL,
  `timestamp` int(11) DEFAULT NULL,
  PRIMARY KEY (`char_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clan_search_waiting_players
-- ----------------------------

-- ----------------------------
-- Table structure for clan_skills
-- ----------------------------
DROP TABLE IF EXISTS `clan_skills`;
CREATE TABLE `clan_skills` (
  `clan_id` int(11) NOT NULL DEFAULT '0',
  `skill_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `skill_level` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`clan_id`,`skill_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clan_skills
-- ----------------------------

-- ----------------------------
-- Table structure for clan_subpledges
-- ----------------------------
DROP TABLE IF EXISTS `clan_subpledges`;
CREATE TABLE `clan_subpledges` (
  `clan_id` int(10) unsigned NOT NULL DEFAULT '0',
  `type` smallint(6) NOT NULL DEFAULT '0',
  `name` varchar(45) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `leader_id` int(10) unsigned NOT NULL DEFAULT '0',
  `upgraded` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`clan_id`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clan_subpledges
-- ----------------------------

-- ----------------------------
-- Table structure for clan_subpledges_skills
-- ----------------------------
DROP TABLE IF EXISTS `clan_subpledges_skills`;
CREATE TABLE `clan_subpledges_skills` (
  `clan_id` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL,
  `skill_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `skill_level` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`clan_id`,`type`,`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clan_subpledges_skills
-- ----------------------------

-- ----------------------------
-- Table structure for clan_wars
-- ----------------------------
DROP TABLE IF EXISTS `clan_wars`;
CREATE TABLE `clan_wars` (
  `attacker_clan` int(11) NOT NULL,
  `opposing_clan` int(11) NOT NULL,
  `period` enum('PREPARATION','MUTUAL','PEACE') NOT NULL DEFAULT 'PREPARATION',
  `period_start_time` int(11) NOT NULL DEFAULT '0',
  `last_kill_time` int(11) NOT NULL DEFAULT '0',
  `attackers_kill_counter` int(11) NOT NULL DEFAULT '0',
  `opposers_kill_counter` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`attacker_clan`,`opposing_clan`),
  UNIQUE KEY `attacker_clan` (`attacker_clan`),
  UNIQUE KEY `opposing_clan` (`opposing_clan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clan_wars
-- ----------------------------

-- ----------------------------
-- Table structure for couples
-- ----------------------------
DROP TABLE IF EXISTS `couples`;
CREATE TABLE `couples` (
  `id` int(11) NOT NULL,
  `player1Id` int(11) NOT NULL DEFAULT '0',
  `player2Id` int(11) NOT NULL DEFAULT '0',
  `maried` varchar(5) DEFAULT NULL,
  `affiancedDate` bigint(20) DEFAULT '0',
  `weddingDate` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of couples
-- ----------------------------

-- ----------------------------
-- Table structure for cursed_weapons
-- ----------------------------
DROP TABLE IF EXISTS `cursed_weapons`;
CREATE TABLE `cursed_weapons` (
  `item_id` smallint(5) unsigned NOT NULL,
  `player_id` int(10) unsigned NOT NULL DEFAULT '0',
  `player_karma` int(10) unsigned NOT NULL DEFAULT '0',
  `player_pkkills` int(10) unsigned NOT NULL DEFAULT '0',
  `nb_kills` int(10) unsigned NOT NULL DEFAULT '0',
  `x` int(11) NOT NULL DEFAULT '0',
  `y` int(11) NOT NULL DEFAULT '0',
  `z` int(11) NOT NULL DEFAULT '0',
  `end_time` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cursed_weapons
-- ----------------------------

-- ----------------------------
-- Table structure for custom_heroes
-- ----------------------------
DROP TABLE IF EXISTS `custom_heroes`;
CREATE TABLE `custom_heroes` (
  `hero_id` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`hero_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom_heroes
-- ----------------------------

-- ----------------------------
-- Table structure for custom_item_auction
-- ----------------------------
DROP TABLE IF EXISTS `custom_item_auction`;
CREATE TABLE `custom_item_auction` (
  `auctionId` int(11) NOT NULL,
  `auctionItemId` int(11) NOT NULL,
  `itemId` int(7) NOT NULL,
  `itemCount` bigint(20) NOT NULL,
  `itemEnchant` int(11) NOT NULL,
  `bidItemId` int(7) NOT NULL,
  `bidItemStartCount` bigint(20) NOT NULL,
  PRIMARY KEY (`auctionId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom_item_auction
-- ----------------------------

-- ----------------------------
-- Table structure for custom_stats_addon
-- ----------------------------
DROP TABLE IF EXISTS `custom_stats_addon`;
CREATE TABLE `custom_stats_addon` (
  `level` int(10) NOT NULL,
  `mulHP` double NOT NULL DEFAULT '1',
  `mulMP` double NOT NULL DEFAULT '1',
  `mulPAtkSpd` double NOT NULL DEFAULT '1',
  `mulMAtkSpd` double NOT NULL DEFAULT '1',
  `mulPAtk` double NOT NULL DEFAULT '1',
  `mulMAtk` double NOT NULL DEFAULT '1',
  `mulPDef` double NOT NULL DEFAULT '1',
  `mulMDef` double NOT NULL DEFAULT '1',
  `minAttrHPMP` int(10) NOT NULL DEFAULT '0',
  `minAttrPAtkMAtk` int(10) NOT NULL DEFAULT '0',
  `minAttrPatkMAtkSpd` int(10) NOT NULL DEFAULT '0',
  `minAttrPDef` int(10) NOT NULL DEFAULT '0',
  `minAttrMDef` int(10) NOT NULL DEFAULT '0',
  `randomAddonPatkMatkSpd` int(10) NOT NULL DEFAULT '0',
  `randomAddonPatkMAtk` int(10) NOT NULL DEFAULT '0',
  `randomAddonPdef` int(10) NOT NULL DEFAULT '0',
  `randomAddonMdef` int(10) NOT NULL DEFAULT '0',
  `forRaid` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`level`,`forRaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom_stats_addon
-- ----------------------------
INSERT INTO `custom_stats_addon` VALUES ('80', '2', '2.5', '2.1', '2.1', '2.5', '2', '2', '2', '2', '2', '2', '2', '2', '400', '410', '400', '400', '1');

-- ----------------------------
-- Table structure for epic_boss_spawn
-- ----------------------------
DROP TABLE IF EXISTS `epic_boss_spawn`;
CREATE TABLE `epic_boss_spawn` (
  `bossId` smallint(5) unsigned NOT NULL,
  `respawnDate` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`bossId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of epic_boss_spawn
-- ----------------------------
INSERT INTO `epic_boss_spawn` VALUES ('29068', '0', '0');
INSERT INTO `epic_boss_spawn` VALUES ('29020', '0', '0');
INSERT INTO `epic_boss_spawn` VALUES ('29028', '0', '0');
INSERT INTO `epic_boss_spawn` VALUES ('29062', '0', '0');
INSERT INTO `epic_boss_spawn` VALUES ('29065', '0', '0');
INSERT INTO `epic_boss_spawn` VALUES ('29099', '0', '0');

-- ----------------------------
-- Table structure for event_data
-- ----------------------------
DROP TABLE IF EXISTS `event_data`;
CREATE TABLE `event_data` (
  `charId` int(15) NOT NULL,
  `score` int(5) DEFAULT NULL,
  PRIMARY KEY (`charId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of event_data
-- ----------------------------

-- ----------------------------
-- Table structure for fake_players
-- ----------------------------
DROP TABLE IF EXISTS `fake_players`;
CREATE TABLE `fake_players` (
  `id` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `x` mediumint(9) DEFAULT NULL,
  `y` mediumint(9) DEFAULT NULL,
  `z` mediumint(9) DEFAULT NULL,
  `path_id` tinyint(3) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fake_players
-- ----------------------------

-- ----------------------------
-- Table structure for games
-- ----------------------------
DROP TABLE IF EXISTS `games`;
CREATE TABLE `games` (
  `id` int(11) NOT NULL DEFAULT '0',
  `idnr` int(11) NOT NULL DEFAULT '0',
  `number1` int(11) NOT NULL DEFAULT '0',
  `number2` int(11) NOT NULL DEFAULT '0',
  `prize` int(11) NOT NULL DEFAULT '0',
  `newprize` int(11) NOT NULL DEFAULT '0',
  `prize1` int(11) NOT NULL DEFAULT '0',
  `prize2` int(11) NOT NULL DEFAULT '0',
  `prize3` int(11) NOT NULL DEFAULT '0',
  `enddate` decimal(20,0) NOT NULL DEFAULT '0',
  `finished` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`idnr`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of games
-- ----------------------------

-- ----------------------------
-- Table structure for global_tasks
-- ----------------------------
DROP TABLE IF EXISTS `global_tasks`;
CREATE TABLE `global_tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task` varchar(50) NOT NULL DEFAULT '',
  `type` varchar(50) NOT NULL DEFAULT '',
  `last_activation` int(20) NOT NULL DEFAULT '0',
  `param1` varchar(100) NOT NULL DEFAULT '',
  `param2` varchar(100) NOT NULL DEFAULT '',
  `param3` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of global_tasks
-- ----------------------------

-- ----------------------------
-- Table structure for hidden_items
-- ----------------------------
DROP TABLE IF EXISTS `hidden_items`;
CREATE TABLE `hidden_items` (
  `obj_id` int(10) NOT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hidden_items
-- ----------------------------

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `object_id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `item_id` int(7) NOT NULL,
  `count` bigint(20) NOT NULL,
  `enchant_level` int(11) NOT NULL,
  `loc` varchar(32) NOT NULL,
  `loc_data` int(11) NOT NULL,
  `life_time` int(11) NOT NULL,
  `custom_type1` int(5) NOT NULL,
  `custom_type2` int(5) NOT NULL,
  `custom_flags` int(11) NOT NULL,
  PRIMARY KEY (`object_id`),
  KEY `owner_id` (`owner_id`),
  KEY `loc` (`loc`),
  KEY `item_id` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of items
-- ----------------------------

-- ----------------------------
-- Table structure for items_delayed
-- ----------------------------
DROP TABLE IF EXISTS `items_delayed`;
CREATE TABLE `items_delayed` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `item_id` smallint(5) unsigned NOT NULL,
  `count` int(10) unsigned NOT NULL DEFAULT '1',
  `enchant_level` smallint(5) unsigned NOT NULL DEFAULT '0',
  `attribute` smallint(6) NOT NULL DEFAULT '-1',
  `attribute_level` smallint(6) NOT NULL DEFAULT '-1',
  `flags` int(11) NOT NULL DEFAULT '0',
  `payment_status` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `key_owner_id` (`owner_id`),
  KEY `key_item_id` (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of items_delayed
-- ----------------------------

-- ----------------------------
-- Table structure for l2exchange_shop
-- ----------------------------
DROP TABLE IF EXISTS `l2exchange_shop`;
CREATE TABLE `l2exchange_shop` (
  `account` varchar(255) NOT NULL,
  `count` int(255) DEFAULT NULL,
  PRIMARY KEY (`account`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

-- ----------------------------
-- Records of l2exchange_shop
-- ----------------------------

-- ----------------------------
-- Table structure for l2exchange_shop_color
-- ----------------------------
DROP TABLE IF EXISTS `l2exchange_shop_color`;
CREATE TABLE `l2exchange_shop_color` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

-- ----------------------------
-- Records of l2exchange_shop_color
-- ----------------------------

-- ----------------------------
-- Table structure for l2exchange_shop_itemlist
-- ----------------------------
DROP TABLE IF EXISTS `l2exchange_shop_itemlist`;
CREATE TABLE `l2exchange_shop_itemlist` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `itemid` int(255) NOT NULL DEFAULT '0',
  `count` int(255) DEFAULT NULL,
  `cost` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=cp1251;

-- ----------------------------
-- Records of l2exchange_shop_itemlist
-- ----------------------------

-- ----------------------------
-- Table structure for l2exchange_shop_log
-- ----------------------------
DROP TABLE IF EXISTS `l2exchange_shop_log`;
CREATE TABLE `l2exchange_shop_log` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `comment` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2767 DEFAULT CHARSET=cp1251;

-- ----------------------------
-- Records of l2exchange_shop_log
-- ----------------------------

-- ----------------------------
-- Table structure for l2scripts_ban_ip
-- ----------------------------
DROP TABLE IF EXISTS `l2scripts_ban_ip`;
CREATE TABLE `l2scripts_ban_ip` (
  `type` varchar(255) NOT NULL,
  `ip` varchar(20) NOT NULL,
  `time` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=cp1251 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of l2scripts_ban_ip
-- ----------------------------

-- ----------------------------
-- Table structure for l2scripts_log
-- ----------------------------
DROP TABLE IF EXISTS `l2scripts_log`;
CREATE TABLE `l2scripts_log` (
  `ip` varchar(255) NOT NULL,
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `nick` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `param` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

-- ----------------------------
-- Records of l2scripts_log
-- ----------------------------

-- ----------------------------
-- Table structure for l2scripts_player_reuze
-- ----------------------------
DROP TABLE IF EXISTS `l2scripts_player_reuze`;
CREATE TABLE `l2scripts_player_reuze` (
  `type` varchar(45) NOT NULL,
  `player_id` varchar(20) NOT NULL,
  `time` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=cp1251 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of l2scripts_player_reuze
-- ----------------------------

-- ----------------------------
-- Table structure for l2scripts_reuze_ip
-- ----------------------------
DROP TABLE IF EXISTS `l2scripts_reuze_ip`;
CREATE TABLE `l2scripts_reuze_ip` (
  `type` text NOT NULL,
  `ip` text NOT NULL,
  `time` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=cp1251;

-- ----------------------------
-- Records of l2scripts_reuze_ip
-- ----------------------------

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
-- Records of lfc_arena
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
-- Records of lfc_global_values
-- ----------------------------
INSERT INTO `lfc_global_values` VALUES ('1', '2', '100');

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
-- Records of lfc_out
-- ----------------------------

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
-- Records of lfc_stats_battle
-- ----------------------------
INSERT INTO `lfc_stats_battle` VALUES ('9', 'Arena 9', 'Arena 9', 'dddd', '123', '57', '1', '286');
INSERT INTO `lfc_stats_battle` VALUES ('1', 'Arena 1', 'Arena 1', 'dddd', '123', '11111', '1', '287');

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
-- Records of lfc_stats_global
-- ----------------------------
INSERT INTO `lfc_stats_global` VALUES ('123', '0', '2', '1', '0');
INSERT INTO `lfc_stats_global` VALUES ('dddd', '2', '0', '1', '100');

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NOT NULL,
  `sender_name` varchar(32) CHARACTER SET utf8 NOT NULL,
  `receiver_id` int(10) NOT NULL,
  `receiver_name` varchar(32) CHARACTER SET utf8 NOT NULL,
  `expire_time` int(11) NOT NULL,
  `topic` tinytext CHARACTER SET utf8 NOT NULL,
  `body` text CHARACTER SET utf8 NOT NULL,
  `price` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  `unread` tinyint(4) NOT NULL DEFAULT '1',
  `returned` tinyint(4) NOT NULL DEFAULT '0',
  `system_topic` int(10) NOT NULL DEFAULT '0',
  `system_body` int(10) NOT NULL DEFAULT '0',
  `system_params` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mail
-- ----------------------------

-- ----------------------------
-- Table structure for mail_attachments
-- ----------------------------
DROP TABLE IF EXISTS `mail_attachments`;
CREATE TABLE `mail_attachments` (
  `message_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  UNIQUE KEY `item_id` (`item_id`),
  KEY `messageId` (`message_id`),
  CONSTRAINT `mail_attachments_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `mail` (`message_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mail_attachments
-- ----------------------------

-- ----------------------------
-- Table structure for mmotop_10_now_month
-- ----------------------------
DROP TABLE IF EXISTS `mmotop_10_now_month`;
CREATE TABLE `mmotop_10_now_month` (
  `Name` varchar(255) NOT NULL,
  `Counts` int(255) DEFAULT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mmotop_10_now_month
-- ----------------------------
INSERT INTO `mmotop_10_now_month` VALUES ('123123', '1');

-- ----------------------------
-- Table structure for mmotop_prizi
-- ----------------------------
DROP TABLE IF EXISTS `mmotop_prizi`;
CREATE TABLE `mmotop_prizi` (
  `prizid` int(255) NOT NULL DEFAULT '0',
  `itemid` int(10) NOT NULL DEFAULT '0',
  `kolvo` int(10) DEFAULT NULL,
  `kolvo-mes` varchar(255) DEFAULT NULL,
  `rozdano` int(10) DEFAULT NULL,
  `chance` int(3) DEFAULT NULL,
  PRIMARY KEY (`prizid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mmotop_prizi
-- ----------------------------
INSERT INTO `mmotop_prizi` VALUES ('0', '6673', '5', 'unlimit', '0', '100');

-- ----------------------------
-- Table structure for mmotop_voted_ip
-- ----------------------------
DROP TABLE IF EXISTS `mmotop_voted_ip`;
CREATE TABLE `mmotop_voted_ip` (
  `id` int(10) NOT NULL DEFAULT '0',
  `charid` int(255) DEFAULT NULL,
  `charname` varchar(255) DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  `date_vote` date DEFAULT '0000-00-00',
  `time_vote` time DEFAULT NULL,
  `date_deliver` date DEFAULT NULL,
  `time_deliver` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mmotop_voted_ip
-- ----------------------------
INSERT INTO `mmotop_voted_ip` VALUES ('0', '123123', '123123', '123.123.123.123', '2009-07-08', '00:00:00', '2009-07-19', '00:00:00');

-- ----------------------------
-- Table structure for mmotop_winners
-- ----------------------------
DROP TABLE IF EXISTS `mmotop_winners`;
CREATE TABLE `mmotop_winners` (
  `id` int(255) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '',
  `prizid` int(11) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mmotop_winners
-- ----------------------------
INSERT INTO `mmotop_winners` VALUES ('0', 'Admin', '15', '2009-12-23', '00:00:00');

-- ----------------------------
-- Table structure for nextpay_l2_order
-- ----------------------------
DROP TABLE IF EXISTS `nextpay_l2_order`;
CREATE TABLE `nextpay_l2_order` (
  `order_id` int(11) NOT NULL DEFAULT '0',
  `date_created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `product_id` int(11) NOT NULL DEFAULT '0',
  `volute` int(11) NOT NULL DEFAULT '0',
  `product_count` int(11) NOT NULL DEFAULT '0',
  `server` int(11) NOT NULL DEFAULT '0',
  `char_name` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `profit` float NOT NULL DEFAULT '0',
  `comment` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of nextpay_l2_order
-- ----------------------------

-- ----------------------------
-- Table structure for nextpay_sms
-- ----------------------------
DROP TABLE IF EXISTS `nextpay_sms`;
CREATE TABLE `nextpay_sms` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `date_created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `amount_usd` float NOT NULL DEFAULT '0',
  `number` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `text` text COLLATE utf8_bin,
  `prefix` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `hash` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `country` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `op` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `sms_date` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `amount` float NOT NULL DEFAULT '0',
  `phone` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `last_modified` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(11) NOT NULL DEFAULT '0',
  `eup` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of nextpay_sms
-- ----------------------------

-- ----------------------------
-- Table structure for npcbuffer_buff_list
-- ----------------------------
DROP TABLE IF EXISTS `npcbuffer_buff_list`;
CREATE TABLE `npcbuffer_buff_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buff_class` int(2) DEFAULT NULL,
  `buffType` varchar(10) DEFAULT NULL,
  `buffId` int(5) DEFAULT '0',
  `buffLevel` int(5) DEFAULT NULL,
  `forClass` tinyint(1) DEFAULT NULL,
  `canUse` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of npcbuffer_buff_list
-- ----------------------------
INSERT INTO `npcbuffer_buff_list` VALUES ('2', '0', 'buff', '1040', '3', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('3', '0', 'buff', '1043', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('4', '0', 'buff', '1044', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('5', '0', 'buff', '1045', '6', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('6', '0', 'buff', '1047', '4', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('7', '0', 'buff', '1048', '6', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('8', '0', 'buff', '1059', '3', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('9', '0', 'buff', '1068', '3', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('10', '0', 'buff', '1077', '3', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('11', '0', 'buff', '1085', '3', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('12', '0', 'buff', '1086', '2', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('13', '0', 'buff', '1087', '3', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('14', '0', 'buff', '1204', '2', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('15', '0', 'buff', '1240', '3', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('16', '0', 'buff', '1242', '3', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('17', '0', 'buff', '1243', '6', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('18', '0', 'buff', '1257', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('19', '0', 'buff', '1268', '4', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('20', '0', 'buff', '1303', '2', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('21', '0', 'buff', '1304', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('22', '0', 'buff', '1307', '3', '2', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('23', '0', 'buff', '1311', '6', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('24', '0', 'buff', '1397', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('25', '0', 'buff', '1460', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('26', '0', 'buff', '1232', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('27', '0', 'buff', '1238', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('28', '0', 'special', '1323', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('29', '0', 'special', '1388', '3', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('30', '0', 'special', '1389', '3', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('31', '1', 'song', '264', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('32', '1', 'song', '265', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('33', '1', 'song', '266', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('34', '1', 'song', '267', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('35', '1', 'song', '268', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('36', '1', 'song', '269', '1', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('37', '1', 'song', '270', '1', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('38', '1', 'song', '304', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('39', '1', 'song', '305', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('40', '1', 'song', '306', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('41', '1', 'song', '308', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('42', '1', 'song', '349', '1', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('43', '1', 'song', '363', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('44', '1', 'song', '364', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('45', '1', 'song', '529', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('46', '1', 'song', '764', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('47', '1', 'song', '914', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('48', '2', 'dance', '271', '1', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('49', '2', 'dance', '272', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('50', '2', 'dance', '273', '1', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('51', '2', 'dance', '274', '1', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('52', '2', 'dance', '275', '1', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('53', '2', 'dance', '276', '1', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('54', '2', 'dance', '277', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('55', '2', 'dance', '307', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('56', '2', 'dance', '309', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('57', '2', 'dance', '310', '1', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('58', '2', 'dance', '311', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('59', '2', 'dance', '365', '1', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('60', '2', 'dance', '366', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('61', '2', 'dance', '530', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('62', '2', 'dance', '765', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('63', '2', 'dance', '915', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('64', '3', 'resist', '1461', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('65', '3', 'chant', '1002', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('66', '3', 'chant', '1006', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('67', '3', 'chant', '1007', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('68', '3', 'chant', '1009', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('69', '3', 'chant', '1251', '2', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('70', '3', 'chant', '1252', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('71', '3', 'chant', '1253', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('72', '3', 'chant', '1284', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('73', '3', 'chant', '1308', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('74', '3', 'chant', '1309', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('75', '3', 'chant', '1310', '4', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('76', '3', 'chant', '1362', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('77', '3', 'special', '1499', '1', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('78', '3', 'special', '1500', '1', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('79', '3', 'special', '1501', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('80', '3', 'special', '1502', '1', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('81', '3', 'special', '1503', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('82', '3', 'special', '1504', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('83', '3', 'special', '1519', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('84', '4', 'others', '825', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('85', '4', 'others', '826', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('86', '4', 'others', '827', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('87', '4', 'others', '828', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('88', '4', 'others', '829', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('89', '4', 'others', '830', '1', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('90', '5', 'others', '834', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('91', '5', 'others', '1442', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('92', '5', 'others', '1443', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('93', '5', 'others', '1444', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('94', '6', 'cubic', '67', '7', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('95', '6', 'cubic', '10', '8', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('96', '6', 'cubic', '22', '7', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('97', '6', 'cubic', '33', '8', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('98', '6', 'cubic', '278', '6', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('99', '6', 'cubic', '449', '4', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('100', '6', 'cubic', '1279', '9', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('101', '6', 'cubic', '1280', '9', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('102', '6', 'cubic', '1281', '9', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('103', '6', 'cubic', '1328', '8', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('104', '6', 'cubic', '1329', '9', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('105', '6', 'cubic', '1330', '8', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('106', '6', 'cubic', '779', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('107', '7', 'special', '1062', '2', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('108', '7', 'special', '1355', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('109', '7', 'special', '1356', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('110', '7', 'special', '1357', '1', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('111', '7', 'special', '1363', '1', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('112', '7', 'special', '1413', '1', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('114', '7', 'special', '1457', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('115', '7', 'special', '4699', '13', '0', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('116', '7', 'special', '4700', '13', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('117', '7', 'special', '4702', '13', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('118', '7', 'special', '4703', '13', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('119', '8', 'resist', '1032', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('120', '8', 'resist', '1033', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('121', '8', 'resist', '1035', '4', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('122', '8', 'resist', '1078', '6', '1', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('123', '8', 'resist', '1182', '3', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('124', '8', 'resist', '1189', '3', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('125', '8', 'resist', '1191', '3', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('126', '8', 'resist', '1259', '4', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('127', '8', 'resist', '1352', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('128', '8', 'resist', '1353', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('129', '8', 'resist', '1354', '1', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('130', '8', 'resist', '1392', '3', '3', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('131', '8', 'resist', '1393', '3', '2', '1');
INSERT INTO `npcbuffer_buff_list` VALUES ('132', '8', 'overlord', '1003', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('133', '8', 'overlord', '1004', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('134', '8', 'overlord', '1005', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('135', '8', 'overlord', '1008', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('136', '8', 'overlord', '1249', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('137', '8', 'overlord', '1250', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('138', '8', 'overlord', '1260', '3', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('139', '8', 'overlord', '1261', '2', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('140', '8', 'overlord', '1282', '2', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('141', '8', 'overlord', '1364', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('142', '8', 'overlord', '1365', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('143', '8', 'overlord', '1415', '1', '3', '0');
INSERT INTO `npcbuffer_buff_list` VALUES ('144', '8', 'overlord', '1416', '1', '3', '0');

-- ----------------------------
-- Table structure for npcbuffer_scheme_contents
-- ----------------------------
DROP TABLE IF EXISTS `npcbuffer_scheme_contents`;
CREATE TABLE `npcbuffer_scheme_contents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scheme_id` int(11) DEFAULT NULL,
  `skill_id` int(8) DEFAULT NULL,
  `skill_level` int(4) DEFAULT NULL,
  `buff_class` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3115782 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of npcbuffer_scheme_contents
-- ----------------------------
INSERT INTO `npcbuffer_scheme_contents` VALUES ('1396683', '43572', '1303', '2', '0');
INSERT INTO `npcbuffer_scheme_contents` VALUES ('1396684', '43572', '1085', '3', '0');
INSERT INTO `npcbuffer_scheme_contents` VALUES ('1396685', '43573', '1085', '3', '0');
INSERT INTO `npcbuffer_scheme_contents` VALUES ('1396686', '43573', '1303', '2', '0');
INSERT INTO `npcbuffer_scheme_contents` VALUES ('1396687', '43572', '1397', '3', '0');

-- ----------------------------
-- Table structure for npcbuffer_scheme_list
-- ----------------------------
DROP TABLE IF EXISTS `npcbuffer_scheme_list`;
CREATE TABLE `npcbuffer_scheme_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` varchar(40) DEFAULT NULL,
  `scheme_name` varchar(36) DEFAULT NULL,
  `mod_accepted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101881 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of npcbuffer_scheme_list
-- ----------------------------
INSERT INTO `npcbuffer_scheme_list` VALUES ('43572', '268482203', 'buff', null);
INSERT INTO `npcbuffer_scheme_list` VALUES ('43573', '268481735', 'lips', null);
INSERT INTO `npcbuffer_scheme_list` VALUES ('43574', '268483826', 'poleur', null);
INSERT INTO `npcbuffer_scheme_list` VALUES ('43575', '268481992', 'Arcos', null);
INSERT INTO `npcbuffer_scheme_list` VALUES ('43576', '268481926', '12', null);
INSERT INTO `npcbuffer_scheme_list` VALUES ('43577', '268488037', '1111111111', null);
INSERT INTO `npcbuffer_scheme_list` VALUES ('43578', '268489076', 'mag', null);
INSERT INTO `npcbuffer_scheme_list` VALUES ('43579', '268486308', 'PvE', null);

-- ----------------------------
-- Table structure for online
-- ----------------------------
DROP TABLE IF EXISTS `online`;
CREATE TABLE `online` (
  `index` int(1) NOT NULL,
  `totalOnline` int(6) NOT NULL,
  `totalOffline` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of online
-- ----------------------------
INSERT INTO `online` VALUES ('0', '0', '0');

-- ----------------------------
-- Table structure for pa_free_table
-- ----------------------------
DROP TABLE IF EXISTS `pa_free_table`;
CREATE TABLE `pa_free_table` (
  `account_name` varchar(255) NOT NULL,
  PRIMARY KEY (`account_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pa_free_table
-- ----------------------------

-- ----------------------------
-- Table structure for petitions
-- ----------------------------
DROP TABLE IF EXISTS `petitions`;
CREATE TABLE `petitions` (
  `serv_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `act_time` int(10) unsigned NOT NULL DEFAULT '0',
  `petition_type` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `actor` int(10) unsigned NOT NULL DEFAULT '0',
  `location_x` mediumint(9) DEFAULT NULL,
  `location_y` mediumint(9) DEFAULT NULL,
  `location_z` smallint(6) DEFAULT NULL,
  `petition_text` text CHARACTER SET utf8 NOT NULL,
  `STR_actor` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `STR_actor_account` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `petition_status` tinyint(3) unsigned NOT NULL DEFAULT '0',
  KEY `actor` (`actor`),
  KEY `petition_status` (`petition_status`),
  KEY `petition_type` (`petition_type`),
  KEY `serv_id` (`serv_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of petitions
-- ----------------------------

-- ----------------------------
-- Table structure for pets
-- ----------------------------
DROP TABLE IF EXISTS `pets`;
CREATE TABLE `pets` (
  `item_obj_id` int(11) NOT NULL DEFAULT '0',
  `objId` int(11) DEFAULT NULL,
  `name` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `curHp` mediumint(8) unsigned DEFAULT NULL,
  `curMp` mediumint(8) unsigned DEFAULT NULL,
  `exp` bigint(20) DEFAULT NULL,
  `sp` int(10) unsigned DEFAULT NULL,
  `fed` int(10) unsigned DEFAULT NULL,
  `max_fed` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`item_obj_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pets
-- ----------------------------

-- ----------------------------
-- Table structure for pets_skills
-- ----------------------------
DROP TABLE IF EXISTS `pets_skills`;
CREATE TABLE `pets_skills` (
  `templateId` int(6) NOT NULL DEFAULT '0',
  `minLvl` int(2) NOT NULL DEFAULT '0',
  `skillId` int(5) NOT NULL DEFAULT '0',
  `skillLvl` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`templateId`,`skillId`,`skillLvl`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pets_skills
-- ----------------------------
INSERT INTO `pets_skills` VALUES ('12311', '1', '4710', '0');
INSERT INTO `pets_skills` VALUES ('12311', '1', '4711', '0');
INSERT INTO `pets_skills` VALUES ('12312', '1', '4712', '0');
INSERT INTO `pets_skills` VALUES ('12312', '1', '4713', '0');
INSERT INTO `pets_skills` VALUES ('12526', '1', '4710', '0');
INSERT INTO `pets_skills` VALUES ('12526', '1', '4711', '0');
INSERT INTO `pets_skills` VALUES ('12527', '1', '4712', '0');
INSERT INTO `pets_skills` VALUES ('12527', '1', '4713', '0');
INSERT INTO `pets_skills` VALUES ('12621', '1', '4289', '0');
INSERT INTO `pets_skills` VALUES ('12780', '1', '4717', '0');
INSERT INTO `pets_skills` VALUES ('12780', '1', '4718', '0');
INSERT INTO `pets_skills` VALUES ('12781', '1', '4717', '0');
INSERT INTO `pets_skills` VALUES ('12781', '1', '4718', '0');
INSERT INTO `pets_skills` VALUES ('12782', '1', '4717', '0');
INSERT INTO `pets_skills` VALUES ('12782', '1', '4718', '0');
INSERT INTO `pets_skills` VALUES ('14038', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14039', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14040', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14041', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14042', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14043', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14044', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14045', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14046', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14047', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14048', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14049', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14050', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14051', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14052', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14053', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14054', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14055', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14056', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14057', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14058', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14059', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14060', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14061', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14062', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14063', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14064', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14065', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14066', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14067', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14068', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14069', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14070', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14071', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14072', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14073', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14074', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14074', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14075', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14075', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14076', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14076', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14077', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14077', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14078', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14078', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14079', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14079', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14080', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14080', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14081', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14081', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14082', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14082', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14083', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14083', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14084', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14084', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14085', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14085', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14086', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14086', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14087', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14087', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14088', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14088', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14089', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14089', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14090', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14090', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14091', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14091', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14092', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14092', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14093', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14093', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14094', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14094', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14095', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14095', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14096', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14096', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14097', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14097', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14098', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14098', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14099', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14099', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14100', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14100', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14101', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14101', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14102', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14102', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14103', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14103', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14104', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14104', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14105', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14105', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14106', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14106', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14107', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14107', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14108', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14108', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14109', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14109', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14110', '1', '4708', '0');
INSERT INTO `pets_skills` VALUES ('14110', '1', '4709', '0');
INSERT INTO `pets_skills` VALUES ('14111', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14112', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14113', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14114', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14115', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14116', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14117', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14118', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14119', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14120', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14121', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14122', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14123', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14124', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14125', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14126', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14127', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14128', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14129', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14130', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14131', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14132', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14133', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14134', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14135', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14136', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14137', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14138', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14139', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14140', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14141', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14142', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14143', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14144', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14145', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14146', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14147', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14148', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14149', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14150', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14151', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14152', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14153', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14154', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14155', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14156', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14157', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14158', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14159', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14160', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14161', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14162', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14163', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14164', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14165', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14166', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14167', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14168', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14169', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14170', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14171', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14172', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14173', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14174', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14175', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14176', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14177', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14178', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14179', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14180', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14181', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14182', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14183', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14184', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14185', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14186', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14187', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14188', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14189', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14190', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14191', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14192', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14193', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14194', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14195', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14196', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14197', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14198', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14199', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14200', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14201', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14202', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14203', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14204', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14205', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14206', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14207', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14208', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14209', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14210', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14211', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14212', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14213', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14214', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14215', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14216', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14217', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14218', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14219', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14220', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14221', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14222', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14223', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14224', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14225', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14226', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14227', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14228', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14229', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14230', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14231', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14232', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14233', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14234', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14235', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14236', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14237', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14238', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14239', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14240', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14241', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14242', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14243', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14244', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14245', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14246', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14247', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14248', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14249', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14250', '1', '4378', '0');
INSERT INTO `pets_skills` VALUES ('14251', '1', '4699', '1');
INSERT INTO `pets_skills` VALUES ('14251', '1', '4700', '1');
INSERT INTO `pets_skills` VALUES ('14251', '1', '4701', '1');
INSERT INTO `pets_skills` VALUES ('14251', '1', '5638', '1');
INSERT INTO `pets_skills` VALUES ('14251', '1', '5639', '1');
INSERT INTO `pets_skills` VALUES ('14251', '1', '5640', '1');
INSERT INTO `pets_skills` VALUES ('14252', '1', '4699', '1');
INSERT INTO `pets_skills` VALUES ('14252', '1', '4700', '1');
INSERT INTO `pets_skills` VALUES ('14252', '1', '4701', '1');
INSERT INTO `pets_skills` VALUES ('14252', '1', '5638', '1');
INSERT INTO `pets_skills` VALUES ('14252', '1', '5639', '1');
INSERT INTO `pets_skills` VALUES ('14252', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14253', '1', '4699', '1');
INSERT INTO `pets_skills` VALUES ('14253', '1', '4700', '1');
INSERT INTO `pets_skills` VALUES ('14253', '1', '4701', '1');
INSERT INTO `pets_skills` VALUES ('14253', '1', '5638', '2');
INSERT INTO `pets_skills` VALUES ('14253', '1', '5639', '1');
INSERT INTO `pets_skills` VALUES ('14253', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14254', '1', '4699', '2');
INSERT INTO `pets_skills` VALUES ('14254', '1', '4700', '2');
INSERT INTO `pets_skills` VALUES ('14254', '1', '4701', '2');
INSERT INTO `pets_skills` VALUES ('14254', '1', '5638', '2');
INSERT INTO `pets_skills` VALUES ('14254', '1', '5639', '2');
INSERT INTO `pets_skills` VALUES ('14254', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14255', '1', '4699', '2');
INSERT INTO `pets_skills` VALUES ('14255', '1', '4700', '2');
INSERT INTO `pets_skills` VALUES ('14255', '1', '4701', '2');
INSERT INTO `pets_skills` VALUES ('14255', '1', '5638', '2');
INSERT INTO `pets_skills` VALUES ('14255', '1', '5639', '2');
INSERT INTO `pets_skills` VALUES ('14255', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14256', '1', '4699', '2');
INSERT INTO `pets_skills` VALUES ('14256', '1', '4700', '2');
INSERT INTO `pets_skills` VALUES ('14256', '1', '4701', '2');
INSERT INTO `pets_skills` VALUES ('14256', '1', '5638', '2');
INSERT INTO `pets_skills` VALUES ('14256', '1', '5639', '2');
INSERT INTO `pets_skills` VALUES ('14256', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14257', '1', '4699', '3');
INSERT INTO `pets_skills` VALUES ('14257', '1', '4700', '3');
INSERT INTO `pets_skills` VALUES ('14257', '1', '4701', '3');
INSERT INTO `pets_skills` VALUES ('14257', '1', '5638', '2');
INSERT INTO `pets_skills` VALUES ('14257', '1', '5639', '2');
INSERT INTO `pets_skills` VALUES ('14257', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14258', '1', '4699', '3');
INSERT INTO `pets_skills` VALUES ('14258', '1', '4700', '3');
INSERT INTO `pets_skills` VALUES ('14258', '1', '4701', '3');
INSERT INTO `pets_skills` VALUES ('14258', '1', '5638', '3');
INSERT INTO `pets_skills` VALUES ('14258', '1', '5639', '3');
INSERT INTO `pets_skills` VALUES ('14258', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14259', '1', '4699', '3');
INSERT INTO `pets_skills` VALUES ('14259', '1', '4700', '3');
INSERT INTO `pets_skills` VALUES ('14259', '1', '4701', '3');
INSERT INTO `pets_skills` VALUES ('14259', '1', '5638', '3');
INSERT INTO `pets_skills` VALUES ('14259', '1', '5639', '3');
INSERT INTO `pets_skills` VALUES ('14259', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14260', '1', '4699', '3');
INSERT INTO `pets_skills` VALUES ('14260', '1', '4700', '3');
INSERT INTO `pets_skills` VALUES ('14260', '1', '4701', '3');
INSERT INTO `pets_skills` VALUES ('14260', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14260', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14260', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14265', '1', '4699', '4');
INSERT INTO `pets_skills` VALUES ('14265', '1', '4700', '4');
INSERT INTO `pets_skills` VALUES ('14265', '1', '4701', '4');
INSERT INTO `pets_skills` VALUES ('14265', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14265', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14265', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14266', '1', '4699', '4');
INSERT INTO `pets_skills` VALUES ('14266', '1', '4700', '4');
INSERT INTO `pets_skills` VALUES ('14266', '1', '4701', '4');
INSERT INTO `pets_skills` VALUES ('14266', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14266', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14266', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14267', '1', '4699', '4');
INSERT INTO `pets_skills` VALUES ('14267', '1', '4700', '4');
INSERT INTO `pets_skills` VALUES ('14267', '1', '4701', '4');
INSERT INTO `pets_skills` VALUES ('14267', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14267', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14267', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14268', '1', '4699', '5');
INSERT INTO `pets_skills` VALUES ('14268', '1', '4700', '5');
INSERT INTO `pets_skills` VALUES ('14268', '1', '4701', '5');
INSERT INTO `pets_skills` VALUES ('14268', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14268', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14268', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14269', '1', '4699', '5');
INSERT INTO `pets_skills` VALUES ('14269', '1', '4700', '5');
INSERT INTO `pets_skills` VALUES ('14269', '1', '4701', '5');
INSERT INTO `pets_skills` VALUES ('14269', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14269', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14269', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14270', '1', '4699', '5');
INSERT INTO `pets_skills` VALUES ('14270', '1', '4700', '5');
INSERT INTO `pets_skills` VALUES ('14270', '1', '4701', '5');
INSERT INTO `pets_skills` VALUES ('14270', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14270', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14270', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14271', '1', '4699', '6');
INSERT INTO `pets_skills` VALUES ('14271', '1', '4700', '6');
INSERT INTO `pets_skills` VALUES ('14271', '1', '4701', '6');
INSERT INTO `pets_skills` VALUES ('14271', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14271', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14271', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14272', '1', '4699', '6');
INSERT INTO `pets_skills` VALUES ('14272', '1', '4700', '6');
INSERT INTO `pets_skills` VALUES ('14272', '1', '4701', '6');
INSERT INTO `pets_skills` VALUES ('14272', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14272', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14272', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14273', '1', '4699', '6');
INSERT INTO `pets_skills` VALUES ('14273', '1', '4700', '6');
INSERT INTO `pets_skills` VALUES ('14273', '1', '4701', '6');
INSERT INTO `pets_skills` VALUES ('14273', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14273', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14273', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14274', '1', '4699', '7');
INSERT INTO `pets_skills` VALUES ('14274', '1', '4700', '7');
INSERT INTO `pets_skills` VALUES ('14274', '1', '4701', '7');
INSERT INTO `pets_skills` VALUES ('14274', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14274', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14274', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14275', '1', '4699', '7');
INSERT INTO `pets_skills` VALUES ('14275', '1', '4700', '7');
INSERT INTO `pets_skills` VALUES ('14275', '1', '4701', '7');
INSERT INTO `pets_skills` VALUES ('14275', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14275', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14275', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14276', '1', '4699', '7');
INSERT INTO `pets_skills` VALUES ('14276', '1', '4700', '7');
INSERT INTO `pets_skills` VALUES ('14276', '1', '4701', '7');
INSERT INTO `pets_skills` VALUES ('14276', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14276', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14276', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14277', '1', '4699', '8');
INSERT INTO `pets_skills` VALUES ('14277', '1', '4700', '8');
INSERT INTO `pets_skills` VALUES ('14277', '1', '4701', '8');
INSERT INTO `pets_skills` VALUES ('14277', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14277', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14277', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14278', '1', '4699', '8');
INSERT INTO `pets_skills` VALUES ('14278', '1', '4700', '8');
INSERT INTO `pets_skills` VALUES ('14278', '1', '4701', '8');
INSERT INTO `pets_skills` VALUES ('14278', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14278', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14278', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14279', '1', '4699', '8');
INSERT INTO `pets_skills` VALUES ('14279', '1', '4700', '8');
INSERT INTO `pets_skills` VALUES ('14279', '1', '4701', '8');
INSERT INTO `pets_skills` VALUES ('14279', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14279', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14279', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14280', '1', '4699', '9');
INSERT INTO `pets_skills` VALUES ('14280', '1', '4700', '9');
INSERT INTO `pets_skills` VALUES ('14280', '1', '4701', '9');
INSERT INTO `pets_skills` VALUES ('14280', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14280', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14280', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14281', '1', '4699', '9');
INSERT INTO `pets_skills` VALUES ('14281', '1', '4700', '9');
INSERT INTO `pets_skills` VALUES ('14281', '1', '4701', '9');
INSERT INTO `pets_skills` VALUES ('14281', '1', '5638', '9');
INSERT INTO `pets_skills` VALUES ('14281', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14281', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14282', '1', '4699', '9');
INSERT INTO `pets_skills` VALUES ('14282', '1', '4700', '9');
INSERT INTO `pets_skills` VALUES ('14282', '1', '4701', '9');
INSERT INTO `pets_skills` VALUES ('14282', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14282', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14282', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14283', '1', '4699', '10');
INSERT INTO `pets_skills` VALUES ('14283', '1', '4700', '10');
INSERT INTO `pets_skills` VALUES ('14283', '1', '4701', '10');
INSERT INTO `pets_skills` VALUES ('14283', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14283', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14283', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14284', '1', '4699', '10');
INSERT INTO `pets_skills` VALUES ('14284', '1', '4700', '10');
INSERT INTO `pets_skills` VALUES ('14284', '1', '4701', '10');
INSERT INTO `pets_skills` VALUES ('14284', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14284', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14284', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14285', '1', '4699', '10');
INSERT INTO `pets_skills` VALUES ('14285', '1', '4700', '10');
INSERT INTO `pets_skills` VALUES ('14285', '1', '4701', '10');
INSERT INTO `pets_skills` VALUES ('14285', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14285', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14285', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14286', '1', '4699', '11');
INSERT INTO `pets_skills` VALUES ('14286', '1', '4700', '11');
INSERT INTO `pets_skills` VALUES ('14286', '1', '4701', '11');
INSERT INTO `pets_skills` VALUES ('14286', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14286', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14286', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14287', '1', '4699', '11');
INSERT INTO `pets_skills` VALUES ('14287', '1', '4700', '11');
INSERT INTO `pets_skills` VALUES ('14287', '1', '4701', '11');
INSERT INTO `pets_skills` VALUES ('14287', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14287', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14287', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14288', '1', '4699', '11');
INSERT INTO `pets_skills` VALUES ('14288', '1', '4700', '11');
INSERT INTO `pets_skills` VALUES ('14288', '1', '4701', '11');
INSERT INTO `pets_skills` VALUES ('14288', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14288', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14288', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14289', '1', '4699', '12');
INSERT INTO `pets_skills` VALUES ('14289', '1', '4700', '12');
INSERT INTO `pets_skills` VALUES ('14289', '1', '4701', '12');
INSERT INTO `pets_skills` VALUES ('14289', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14289', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14289', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14290', '1', '4699', '12');
INSERT INTO `pets_skills` VALUES ('14290', '1', '4700', '12');
INSERT INTO `pets_skills` VALUES ('14290', '1', '4701', '12');
INSERT INTO `pets_skills` VALUES ('14290', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14290', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14290', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14291', '1', '4699', '12');
INSERT INTO `pets_skills` VALUES ('14291', '1', '4700', '12');
INSERT INTO `pets_skills` VALUES ('14291', '1', '4701', '12');
INSERT INTO `pets_skills` VALUES ('14291', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14291', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14291', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14292', '1', '4699', '13');
INSERT INTO `pets_skills` VALUES ('14292', '1', '4700', '13');
INSERT INTO `pets_skills` VALUES ('14292', '1', '4701', '13');
INSERT INTO `pets_skills` VALUES ('14292', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14292', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14292', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14293', '1', '4699', '13');
INSERT INTO `pets_skills` VALUES ('14293', '1', '4700', '13');
INSERT INTO `pets_skills` VALUES ('14293', '1', '4701', '13');
INSERT INTO `pets_skills` VALUES ('14293', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14293', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14293', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14294', '1', '4699', '13');
INSERT INTO `pets_skills` VALUES ('14294', '1', '4700', '13');
INSERT INTO `pets_skills` VALUES ('14294', '1', '4701', '13');
INSERT INTO `pets_skills` VALUES ('14294', '1', '5638', '4');
INSERT INTO `pets_skills` VALUES ('14294', '1', '5639', '4');
INSERT INTO `pets_skills` VALUES ('14294', '1', '5640', '2');
INSERT INTO `pets_skills` VALUES ('14295', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14296', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14297', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14298', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14299', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14300', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14301', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14302', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14303', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14304', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14305', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14306', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14307', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14308', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14309', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14310', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14311', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14312', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14313', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14314', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14315', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14316', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14317', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14318', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14319', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14320', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14321', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14322', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14323', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14324', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14325', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14326', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14327', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14328', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14329', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14330', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14331', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14332', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14333', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14334', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14335', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14336', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14337', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14338', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14339', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14340', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14341', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14342', '1', '4025', '0');
INSERT INTO `pets_skills` VALUES ('14343', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14344', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14345', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14346', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14347', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14348', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14349', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14350', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14351', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14352', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14353', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14354', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14355', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14356', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14357', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14358', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14359', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14360', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14361', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14362', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14363', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14364', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14365', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14366', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14367', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14368', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14369', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14370', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14371', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14372', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14373', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14374', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14375', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14376', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14377', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14378', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14379', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14380', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14381', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14382', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14383', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14384', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14385', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14386', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14387', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14388', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14389', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14390', '1', '4261', '0');
INSERT INTO `pets_skills` VALUES ('14391', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14392', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14393', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14394', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14395', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14396', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14397', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14398', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14399', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14400', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14401', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14402', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14403', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14404', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14405', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14406', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14407', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14408', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14409', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14410', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14411', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14412', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14413', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14414', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14415', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14416', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14417', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14418', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14419', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14420', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14421', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14422', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14423', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14424', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14425', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14426', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14427', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14428', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14429', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14430', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14431', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14432', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14433', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14434', '1', '4137', '0');
INSERT INTO `pets_skills` VALUES ('14435', '1', '4702', '1');
INSERT INTO `pets_skills` VALUES ('14435', '1', '4703', '1');
INSERT INTO `pets_skills` VALUES ('14435', '1', '4704', '1');
INSERT INTO `pets_skills` VALUES ('14435', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14435', '1', '5646', '1');
INSERT INTO `pets_skills` VALUES ('14435', '1', '5647', '1');
INSERT INTO `pets_skills` VALUES ('14435', '1', '5648', '1');
INSERT INTO `pets_skills` VALUES ('14436', '1', '4702', '1');
INSERT INTO `pets_skills` VALUES ('14436', '1', '4703', '1');
INSERT INTO `pets_skills` VALUES ('14436', '1', '4704', '1');
INSERT INTO `pets_skills` VALUES ('14436', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14436', '1', '5646', '1');
INSERT INTO `pets_skills` VALUES ('14436', '1', '5647', '1');
INSERT INTO `pets_skills` VALUES ('14436', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14437', '1', '4702', '1');
INSERT INTO `pets_skills` VALUES ('14437', '1', '4703', '1');
INSERT INTO `pets_skills` VALUES ('14437', '1', '4704', '1');
INSERT INTO `pets_skills` VALUES ('14437', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14437', '1', '5646', '1');
INSERT INTO `pets_skills` VALUES ('14437', '1', '5647', '1');
INSERT INTO `pets_skills` VALUES ('14437', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14438', '1', '4702', '2');
INSERT INTO `pets_skills` VALUES ('14438', '1', '4703', '2');
INSERT INTO `pets_skills` VALUES ('14438', '1', '4704', '2');
INSERT INTO `pets_skills` VALUES ('14438', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14438', '1', '5646', '1');
INSERT INTO `pets_skills` VALUES ('14438', '1', '5647', '1');
INSERT INTO `pets_skills` VALUES ('14438', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14439', '1', '4702', '2');
INSERT INTO `pets_skills` VALUES ('14439', '1', '4703', '2');
INSERT INTO `pets_skills` VALUES ('14439', '1', '4704', '2');
INSERT INTO `pets_skills` VALUES ('14439', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14439', '1', '5646', '1');
INSERT INTO `pets_skills` VALUES ('14439', '1', '5647', '1');
INSERT INTO `pets_skills` VALUES ('14439', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14440', '1', '4702', '2');
INSERT INTO `pets_skills` VALUES ('14440', '1', '4703', '2');
INSERT INTO `pets_skills` VALUES ('14440', '1', '4704', '2');
INSERT INTO `pets_skills` VALUES ('14440', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14440', '1', '5646', '1');
INSERT INTO `pets_skills` VALUES ('14440', '1', '5647', '1');
INSERT INTO `pets_skills` VALUES ('14440', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14441', '1', '4702', '3');
INSERT INTO `pets_skills` VALUES ('14441', '1', '4703', '3');
INSERT INTO `pets_skills` VALUES ('14441', '1', '4704', '3');
INSERT INTO `pets_skills` VALUES ('14441', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14441', '1', '5646', '1');
INSERT INTO `pets_skills` VALUES ('14441', '1', '5647', '1');
INSERT INTO `pets_skills` VALUES ('14441', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14442', '1', '4702', '3');
INSERT INTO `pets_skills` VALUES ('14442', '1', '4703', '3');
INSERT INTO `pets_skills` VALUES ('14442', '1', '4704', '3');
INSERT INTO `pets_skills` VALUES ('14442', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14442', '1', '5646', '1');
INSERT INTO `pets_skills` VALUES ('14442', '1', '5647', '2');
INSERT INTO `pets_skills` VALUES ('14442', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14443', '1', '4702', '3');
INSERT INTO `pets_skills` VALUES ('14443', '1', '4703', '3');
INSERT INTO `pets_skills` VALUES ('14443', '1', '4704', '3');
INSERT INTO `pets_skills` VALUES ('14443', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14443', '1', '5646', '1');
INSERT INTO `pets_skills` VALUES ('14443', '1', '5647', '2');
INSERT INTO `pets_skills` VALUES ('14443', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14444', '1', '4702', '3');
INSERT INTO `pets_skills` VALUES ('14444', '1', '4703', '3');
INSERT INTO `pets_skills` VALUES ('14444', '1', '4704', '3');
INSERT INTO `pets_skills` VALUES ('14444', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14444', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14444', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14444', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14449', '1', '4702', '4');
INSERT INTO `pets_skills` VALUES ('14449', '1', '4703', '4');
INSERT INTO `pets_skills` VALUES ('14449', '1', '4704', '4');
INSERT INTO `pets_skills` VALUES ('14449', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14449', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14449', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14449', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14450', '1', '4702', '4');
INSERT INTO `pets_skills` VALUES ('14450', '1', '4703', '4');
INSERT INTO `pets_skills` VALUES ('14450', '1', '4704', '4');
INSERT INTO `pets_skills` VALUES ('14450', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14450', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14450', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14450', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14451', '1', '4702', '4');
INSERT INTO `pets_skills` VALUES ('14451', '1', '4703', '4');
INSERT INTO `pets_skills` VALUES ('14451', '1', '4704', '4');
INSERT INTO `pets_skills` VALUES ('14451', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14451', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14451', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14451', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14452', '1', '4702', '5');
INSERT INTO `pets_skills` VALUES ('14452', '1', '4703', '5');
INSERT INTO `pets_skills` VALUES ('14452', '1', '4704', '5');
INSERT INTO `pets_skills` VALUES ('14452', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14452', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14452', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14452', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14453', '1', '4702', '5');
INSERT INTO `pets_skills` VALUES ('14453', '1', '4703', '5');
INSERT INTO `pets_skills` VALUES ('14453', '1', '4704', '5');
INSERT INTO `pets_skills` VALUES ('14453', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14453', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14453', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14453', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14454', '1', '4702', '5');
INSERT INTO `pets_skills` VALUES ('14454', '1', '4703', '5');
INSERT INTO `pets_skills` VALUES ('14454', '1', '4704', '5');
INSERT INTO `pets_skills` VALUES ('14454', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14454', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14454', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14454', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14455', '1', '4702', '6');
INSERT INTO `pets_skills` VALUES ('14455', '1', '4703', '6');
INSERT INTO `pets_skills` VALUES ('14455', '1', '4704', '6');
INSERT INTO `pets_skills` VALUES ('14455', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14455', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14455', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14455', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14456', '1', '4702', '6');
INSERT INTO `pets_skills` VALUES ('14456', '1', '4703', '6');
INSERT INTO `pets_skills` VALUES ('14456', '1', '4704', '6');
INSERT INTO `pets_skills` VALUES ('14456', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14456', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14456', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14456', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14457', '1', '4702', '6');
INSERT INTO `pets_skills` VALUES ('14457', '1', '4703', '6');
INSERT INTO `pets_skills` VALUES ('14457', '1', '4704', '6');
INSERT INTO `pets_skills` VALUES ('14457', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14457', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14457', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14457', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14458', '1', '4702', '7');
INSERT INTO `pets_skills` VALUES ('14458', '1', '4703', '7');
INSERT INTO `pets_skills` VALUES ('14458', '1', '4704', '7');
INSERT INTO `pets_skills` VALUES ('14458', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14458', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14458', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14458', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14459', '1', '4702', '7');
INSERT INTO `pets_skills` VALUES ('14459', '1', '4703', '7');
INSERT INTO `pets_skills` VALUES ('14459', '1', '4704', '7');
INSERT INTO `pets_skills` VALUES ('14459', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14459', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14459', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14459', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14460', '1', '4702', '7');
INSERT INTO `pets_skills` VALUES ('14460', '1', '4703', '7');
INSERT INTO `pets_skills` VALUES ('14460', '1', '4704', '7');
INSERT INTO `pets_skills` VALUES ('14460', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14460', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14460', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14460', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14461', '1', '4702', '8');
INSERT INTO `pets_skills` VALUES ('14461', '1', '4703', '8');
INSERT INTO `pets_skills` VALUES ('14461', '1', '4704', '8');
INSERT INTO `pets_skills` VALUES ('14461', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14461', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14461', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14461', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14462', '1', '4702', '8');
INSERT INTO `pets_skills` VALUES ('14462', '1', '4703', '8');
INSERT INTO `pets_skills` VALUES ('14462', '1', '4704', '8');
INSERT INTO `pets_skills` VALUES ('14462', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14462', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14462', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14462', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14463', '1', '4702', '8');
INSERT INTO `pets_skills` VALUES ('14463', '1', '4703', '8');
INSERT INTO `pets_skills` VALUES ('14463', '1', '4704', '8');
INSERT INTO `pets_skills` VALUES ('14463', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14463', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14463', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14463', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14464', '1', '4702', '9');
INSERT INTO `pets_skills` VALUES ('14464', '1', '4703', '9');
INSERT INTO `pets_skills` VALUES ('14464', '1', '4704', '9');
INSERT INTO `pets_skills` VALUES ('14464', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14464', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14464', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14464', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14465', '1', '4702', '9');
INSERT INTO `pets_skills` VALUES ('14465', '1', '4703', '9');
INSERT INTO `pets_skills` VALUES ('14465', '1', '4704', '9');
INSERT INTO `pets_skills` VALUES ('14465', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14465', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14465', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14465', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14466', '1', '4702', '9');
INSERT INTO `pets_skills` VALUES ('14466', '1', '4703', '9');
INSERT INTO `pets_skills` VALUES ('14466', '1', '4704', '9');
INSERT INTO `pets_skills` VALUES ('14466', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14466', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14466', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14466', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14467', '1', '4702', '10');
INSERT INTO `pets_skills` VALUES ('14467', '1', '4703', '10');
INSERT INTO `pets_skills` VALUES ('14467', '1', '4704', '10');
INSERT INTO `pets_skills` VALUES ('14467', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14467', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14467', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14467', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14468', '1', '4702', '10');
INSERT INTO `pets_skills` VALUES ('14468', '1', '4703', '10');
INSERT INTO `pets_skills` VALUES ('14468', '1', '4704', '10');
INSERT INTO `pets_skills` VALUES ('14468', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14468', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14468', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14468', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14469', '1', '4702', '10');
INSERT INTO `pets_skills` VALUES ('14469', '1', '4703', '10');
INSERT INTO `pets_skills` VALUES ('14469', '1', '4704', '10');
INSERT INTO `pets_skills` VALUES ('14469', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14469', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14469', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14469', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14470', '1', '4702', '11');
INSERT INTO `pets_skills` VALUES ('14470', '1', '4703', '11');
INSERT INTO `pets_skills` VALUES ('14470', '1', '4704', '11');
INSERT INTO `pets_skills` VALUES ('14470', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14470', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14470', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14470', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14471', '1', '4702', '11');
INSERT INTO `pets_skills` VALUES ('14471', '1', '4703', '11');
INSERT INTO `pets_skills` VALUES ('14471', '1', '4704', '11');
INSERT INTO `pets_skills` VALUES ('14471', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14471', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14471', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14471', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14472', '1', '4702', '11');
INSERT INTO `pets_skills` VALUES ('14472', '1', '4703', '11');
INSERT INTO `pets_skills` VALUES ('14472', '1', '4704', '11');
INSERT INTO `pets_skills` VALUES ('14472', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14472', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14472', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14472', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14473', '1', '4702', '12');
INSERT INTO `pets_skills` VALUES ('14473', '1', '4703', '12');
INSERT INTO `pets_skills` VALUES ('14473', '1', '4704', '12');
INSERT INTO `pets_skills` VALUES ('14473', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14473', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14473', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14473', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14474', '1', '4702', '12');
INSERT INTO `pets_skills` VALUES ('14474', '1', '4703', '12');
INSERT INTO `pets_skills` VALUES ('14474', '1', '4704', '12');
INSERT INTO `pets_skills` VALUES ('14474', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14474', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14474', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14474', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14475', '1', '4702', '12');
INSERT INTO `pets_skills` VALUES ('14475', '1', '4703', '12');
INSERT INTO `pets_skills` VALUES ('14475', '1', '4704', '12');
INSERT INTO `pets_skills` VALUES ('14475', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14475', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14475', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14475', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14476', '1', '4702', '13');
INSERT INTO `pets_skills` VALUES ('14476', '1', '4703', '13');
INSERT INTO `pets_skills` VALUES ('14476', '1', '4704', '13');
INSERT INTO `pets_skills` VALUES ('14476', '1', '5643', '13');
INSERT INTO `pets_skills` VALUES ('14476', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14476', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14476', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14477', '1', '4702', '13');
INSERT INTO `pets_skills` VALUES ('14477', '1', '4703', '13');
INSERT INTO `pets_skills` VALUES ('14477', '1', '4704', '13');
INSERT INTO `pets_skills` VALUES ('14477', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14477', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14477', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14477', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14478', '1', '4702', '13');
INSERT INTO `pets_skills` VALUES ('14478', '1', '4703', '13');
INSERT INTO `pets_skills` VALUES ('14478', '1', '4704', '13');
INSERT INTO `pets_skills` VALUES ('14478', '1', '5643', '1');
INSERT INTO `pets_skills` VALUES ('14478', '1', '5646', '2');
INSERT INTO `pets_skills` VALUES ('14478', '1', '5647', '3');
INSERT INTO `pets_skills` VALUES ('14478', '1', '5648', '2');
INSERT INTO `pets_skills` VALUES ('14479', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14480', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14481', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14482', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14483', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14484', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14485', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14486', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14487', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14488', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14489', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14490', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14491', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14492', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14493', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14494', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14495', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14496', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14497', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14498', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14499', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14500', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14501', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14502', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14503', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14504', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14505', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14506', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14507', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14508', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14509', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14510', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14511', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14512', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14513', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14514', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14515', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14516', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14517', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14518', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14519', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14520', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14521', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14522', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14523', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14524', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14525', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14526', '1', '4233', '1');
INSERT INTO `pets_skills` VALUES ('14527', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14528', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14529', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14530', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14531', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14532', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14533', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14534', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14535', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14536', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14537', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14538', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14539', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14540', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14541', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14542', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14543', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14544', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14545', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14546', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14547', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14548', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14549', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14550', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14551', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14552', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14553', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14554', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14555', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14556', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14557', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14558', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14559', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14560', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14561', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14562', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14563', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14564', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14565', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14566', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14567', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14568', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14569', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14570', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14571', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14572', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14573', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14574', '1', '4260', '0');
INSERT INTO `pets_skills` VALUES ('14575', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14575', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14575', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14576', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14576', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14576', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14577', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14577', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14577', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14578', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14578', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14578', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14579', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14579', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14579', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14580', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14580', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14580', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14581', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14581', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14581', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14582', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14582', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14582', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14583', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14583', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14583', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14584', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14584', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14584', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14585', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14585', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14585', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14586', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14586', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14586', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14587', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14587', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14587', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14588', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14588', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14588', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14589', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14589', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14589', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14590', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14590', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14590', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14591', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14591', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14591', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14592', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14592', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14592', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14593', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14593', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14593', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14594', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14594', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14594', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14595', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14595', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14595', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14596', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14596', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14596', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14597', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14597', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14597', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14598', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14598', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14598', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14599', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14599', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14599', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14600', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14600', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14600', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14601', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14601', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14601', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14602', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14602', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14602', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14603', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14603', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14603', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14604', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14604', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14604', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14605', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14605', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14605', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14606', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14606', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14606', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14607', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14607', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14607', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14608', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14608', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14608', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14609', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14609', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14609', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14610', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14610', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14610', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14611', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14611', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14611', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14612', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14612', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14612', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14613', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14613', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14613', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14614', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14614', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14614', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14615', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14615', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14615', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14616', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14616', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14616', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14617', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14617', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14617', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14618', '1', '4138', '0');
INSERT INTO `pets_skills` VALUES ('14618', '1', '4140', '0');
INSERT INTO `pets_skills` VALUES ('14618', '1', '4259', '0');
INSERT INTO `pets_skills` VALUES ('14619', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14619', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14619', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14619', '1', '5652', '0');
INSERT INTO `pets_skills` VALUES ('14619', '1', '5653', '0');
INSERT INTO `pets_skills` VALUES ('14619', '1', '5654', '0');
INSERT INTO `pets_skills` VALUES ('14620', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14620', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14620', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14620', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14620', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14620', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14621', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14621', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14621', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14621', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14621', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14621', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14622', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14622', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14622', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14622', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14622', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14622', '1', '5654', '2');
INSERT INTO `pets_skills` VALUES ('14623', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14623', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14623', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14623', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14623', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14623', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14624', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14624', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14624', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14624', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14624', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14624', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14625', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14625', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14625', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14625', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14625', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14625', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14626', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14626', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14626', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14626', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14626', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14626', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14627', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14627', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14627', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14627', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14627', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14627', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14628', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14628', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14628', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14628', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14628', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14628', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14629', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14629', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14629', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14629', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14629', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14629', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14630', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14630', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14630', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14630', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14630', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14630', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14631', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14631', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14631', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14631', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14631', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14631', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14632', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14632', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14632', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14632', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14632', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14632', '1', '5654', '2');
INSERT INTO `pets_skills` VALUES ('14633', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14633', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14633', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14633', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14633', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14633', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14634', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14634', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14634', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14634', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14634', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14634', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14635', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14635', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14635', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14635', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14635', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14635', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14636', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14636', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14636', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14636', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14636', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14636', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14637', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14637', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14637', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14637', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14637', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14637', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14638', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14638', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14638', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14638', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14638', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14638', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14639', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14639', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14639', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14639', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14639', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14639', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14640', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14640', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14640', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14640', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14640', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14640', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14641', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14641', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14641', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14641', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14641', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14641', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14642', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14642', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14642', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14642', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14642', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14642', '1', '5654', '2');
INSERT INTO `pets_skills` VALUES ('14643', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14643', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14643', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14643', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14643', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14643', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14644', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14644', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14644', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14644', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14644', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14644', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14645', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14645', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14645', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14645', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14645', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14645', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14646', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14646', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14646', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14646', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14646', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14646', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14647', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14647', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14647', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14647', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14647', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14647', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14648', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14648', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14648', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14648', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14648', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14648', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14649', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14649', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14649', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14649', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14649', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14649', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14650', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14650', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14650', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14650', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14650', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14650', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14651', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14651', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14651', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14651', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14651', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14651', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14652', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14652', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14652', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14652', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14652', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14652', '1', '5654', '2');
INSERT INTO `pets_skills` VALUES ('14653', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14653', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14653', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14653', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14653', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14653', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14654', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14654', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14654', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14654', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14654', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14654', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14655', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14655', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14655', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14655', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14655', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14655', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14656', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14656', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14656', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14656', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14656', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14656', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14657', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14657', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14657', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14657', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14657', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14657', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14658', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14658', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14658', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14658', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14658', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14658', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14659', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14659', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14659', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14659', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14659', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14659', '1', '5654', '3');
INSERT INTO `pets_skills` VALUES ('14660', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14660', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14660', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14660', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14660', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14660', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14661', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14661', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14661', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14661', '1', '5652', '2');
INSERT INTO `pets_skills` VALUES ('14661', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14661', '1', '5654', '1');
INSERT INTO `pets_skills` VALUES ('14662', '1', '4705', '0');
INSERT INTO `pets_skills` VALUES ('14662', '1', '4706', '0');
INSERT INTO `pets_skills` VALUES ('14662', '1', '4707', '0');
INSERT INTO `pets_skills` VALUES ('14662', '1', '5652', '3');
INSERT INTO `pets_skills` VALUES ('14662', '1', '5653', '2');
INSERT INTO `pets_skills` VALUES ('14662', '1', '5654', '2');
INSERT INTO `pets_skills` VALUES ('14663', '1', '4068', '3');
INSERT INTO `pets_skills` VALUES ('14664', '1', '4068', '3');
INSERT INTO `pets_skills` VALUES ('14665', '1', '4068', '4');
INSERT INTO `pets_skills` VALUES ('14666', '1', '4068', '5');
INSERT INTO `pets_skills` VALUES ('14667', '1', '4068', '5');
INSERT INTO `pets_skills` VALUES ('14668', '1', '4068', '6');
INSERT INTO `pets_skills` VALUES ('14669', '1', '4068', '6');
INSERT INTO `pets_skills` VALUES ('14670', '1', '4068', '7');
INSERT INTO `pets_skills` VALUES ('14671', '1', '4068', '7');
INSERT INTO `pets_skills` VALUES ('14672', '1', '4068', '8');
INSERT INTO `pets_skills` VALUES ('14673', '1', '4068', '8');
INSERT INTO `pets_skills` VALUES ('14674', '1', '4068', '8');
INSERT INTO `pets_skills` VALUES ('14675', '1', '4068', '8');
INSERT INTO `pets_skills` VALUES ('14676', '1', '4068', '8');
INSERT INTO `pets_skills` VALUES ('14677', '1', '4068', '8');
INSERT INTO `pets_skills` VALUES ('14678', '1', '4068', '9');
INSERT INTO `pets_skills` VALUES ('14679', '1', '4068', '9');
INSERT INTO `pets_skills` VALUES ('14680', '1', '4068', '9');
INSERT INTO `pets_skills` VALUES ('14681', '1', '4068', '9');
INSERT INTO `pets_skills` VALUES ('14682', '1', '4068', '9');
INSERT INTO `pets_skills` VALUES ('14683', '1', '4068', '9');
INSERT INTO `pets_skills` VALUES ('14684', '1', '4068', '10');
INSERT INTO `pets_skills` VALUES ('14685', '1', '4068', '10');
INSERT INTO `pets_skills` VALUES ('14686', '1', '4068', '10');
INSERT INTO `pets_skills` VALUES ('14687', '1', '4068', '10');
INSERT INTO `pets_skills` VALUES ('14688', '1', '4068', '10');
INSERT INTO `pets_skills` VALUES ('14689', '1', '4068', '10');
INSERT INTO `pets_skills` VALUES ('14690', '1', '4068', '11');
INSERT INTO `pets_skills` VALUES ('14691', '1', '4068', '11');
INSERT INTO `pets_skills` VALUES ('14692', '1', '4068', '11');
INSERT INTO `pets_skills` VALUES ('14693', '1', '4068', '11');
INSERT INTO `pets_skills` VALUES ('14694', '1', '4068', '11');
INSERT INTO `pets_skills` VALUES ('14695', '1', '4068', '11');
INSERT INTO `pets_skills` VALUES ('14696', '1', '4068', '12');
INSERT INTO `pets_skills` VALUES ('14697', '1', '4068', '12');
INSERT INTO `pets_skills` VALUES ('14698', '1', '4068', '12');
INSERT INTO `pets_skills` VALUES ('14699', '1', '4068', '12');
INSERT INTO `pets_skills` VALUES ('14700', '1', '4068', '12');
INSERT INTO `pets_skills` VALUES ('14701', '1', '4068', '12');
INSERT INTO `pets_skills` VALUES ('14702', '1', '4139', '6');
INSERT INTO `pets_skills` VALUES ('14703', '1', '4139', '6');
INSERT INTO `pets_skills` VALUES ('14704', '1', '4139', '6');
INSERT INTO `pets_skills` VALUES ('14705', '1', '4139', '7');
INSERT INTO `pets_skills` VALUES ('14706', '1', '4139', '7');
INSERT INTO `pets_skills` VALUES ('14707', '1', '4139', '8');
INSERT INTO `pets_skills` VALUES ('14708', '1', '4139', '8');
INSERT INTO `pets_skills` VALUES ('14709', '1', '4139', '8');
INSERT INTO `pets_skills` VALUES ('14710', '1', '4139', '8');
INSERT INTO `pets_skills` VALUES ('14711', '1', '4139', '8');
INSERT INTO `pets_skills` VALUES ('14712', '1', '4139', '8');
INSERT INTO `pets_skills` VALUES ('14713', '1', '4139', '9');
INSERT INTO `pets_skills` VALUES ('14714', '1', '4139', '9');
INSERT INTO `pets_skills` VALUES ('14715', '1', '4139', '9');
INSERT INTO `pets_skills` VALUES ('14716', '1', '4139', '9');
INSERT INTO `pets_skills` VALUES ('14717', '1', '4139', '9');
INSERT INTO `pets_skills` VALUES ('14718', '1', '4139', '9');
INSERT INTO `pets_skills` VALUES ('14719', '1', '4139', '10');
INSERT INTO `pets_skills` VALUES ('14720', '1', '4139', '10');
INSERT INTO `pets_skills` VALUES ('14721', '1', '4139', '10');
INSERT INTO `pets_skills` VALUES ('14722', '1', '4139', '10');
INSERT INTO `pets_skills` VALUES ('14723', '1', '4139', '10');
INSERT INTO `pets_skills` VALUES ('14724', '1', '4139', '10');
INSERT INTO `pets_skills` VALUES ('14725', '1', '4139', '11');
INSERT INTO `pets_skills` VALUES ('14726', '1', '4139', '11');
INSERT INTO `pets_skills` VALUES ('14727', '1', '4139', '11');
INSERT INTO `pets_skills` VALUES ('14728', '1', '4139', '11');
INSERT INTO `pets_skills` VALUES ('14729', '1', '4139', '11');
INSERT INTO `pets_skills` VALUES ('14730', '1', '4139', '11');
INSERT INTO `pets_skills` VALUES ('14731', '1', '4139', '12');
INSERT INTO `pets_skills` VALUES ('14732', '1', '4139', '12');
INSERT INTO `pets_skills` VALUES ('14733', '1', '4139', '12');
INSERT INTO `pets_skills` VALUES ('14734', '1', '4139', '12');
INSERT INTO `pets_skills` VALUES ('14735', '1', '4139', '12');
INSERT INTO `pets_skills` VALUES ('14736', '1', '4139', '12');
INSERT INTO `pets_skills` VALUES ('14737', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14737', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14738', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14738', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14739', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14739', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14740', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14740', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14741', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14741', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14742', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14742', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14743', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14743', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14744', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14744', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14745', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14745', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14746', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14746', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14747', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14747', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14748', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14748', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14749', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14749', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14750', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14750', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14751', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14751', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14752', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14752', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14753', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14753', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14754', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14754', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14755', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14755', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14756', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14756', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14757', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14757', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14758', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14758', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14759', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14759', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14760', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14760', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14761', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14761', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14762', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14762', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14763', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14763', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14764', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14764', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14765', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14765', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14766', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14766', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14767', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('14767', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('14768', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14769', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14770', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14771', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14772', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14773', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14774', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14775', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14776', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14777', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14778', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14779', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14780', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14781', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14782', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14783', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14784', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14785', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14786', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14787', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14788', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14789', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14790', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14791', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14792', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14793', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14794', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14795', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14796', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14797', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14798', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('14799', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14800', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14801', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14801', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14802', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14802', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14803', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14803', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14803', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14804', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14804', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14804', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14805', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14805', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14805', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14806', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14806', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14806', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14807', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14807', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14807', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14808', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14808', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14808', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14809', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14809', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14809', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14810', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14810', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14810', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14811', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14811', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14811', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14812', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14812', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14812', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14813', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14813', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14813', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14814', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14814', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14814', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14815', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14815', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14815', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14816', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14816', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14816', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14817', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14817', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14817', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14818', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14818', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14818', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14819', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14819', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14819', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14820', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14820', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14820', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14821', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14821', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14821', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14822', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14822', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14822', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14823', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14823', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14823', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14824', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14824', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14824', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14825', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14825', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14825', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14826', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14826', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14826', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14827', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14827', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14827', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14828', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14828', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14828', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14829', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14829', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14829', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14830', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14830', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14830', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14831', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14831', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14831', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14832', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14832', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14832', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14833', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14833', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14833', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14834', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14834', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14834', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14835', '1', '6094', '0');
INSERT INTO `pets_skills` VALUES ('14835', '1', '6095', '0');
INSERT INTO `pets_skills` VALUES ('14835', '1', '6096', '0');
INSERT INTO `pets_skills` VALUES ('14836', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14836', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14836', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14837', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14837', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14838', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14838', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14838', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14839', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14839', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14840', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14840', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14841', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14841', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14842', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14842', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14843', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14843', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14844', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14844', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14845', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14845', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14846', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14846', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14847', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14847', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14848', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14848', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14849', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14849', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14850', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14850', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14851', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14851', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14852', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14852', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14853', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14853', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14854', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14854', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14855', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14855', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14856', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14856', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14857', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14857', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14858', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14858', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14859', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14859', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14860', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14860', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14861', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14861', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14862', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14862', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14863', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14863', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14864', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14864', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14865', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14865', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14866', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14866', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14867', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14867', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14868', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14868', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14869', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('14869', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('14870', '1', '5580', '1');
INSERT INTO `pets_skills` VALUES ('14870', '1', '5581', '1');
INSERT INTO `pets_skills` VALUES ('14870', '1', '5582', '1');
INSERT INTO `pets_skills` VALUES ('14870', '1', '5583', '1');
INSERT INTO `pets_skills` VALUES ('14871', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14871', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14871', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14872', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14872', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14872', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14873', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14873', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14873', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14874', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14874', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14874', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14875', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14875', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14875', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14876', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14876', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14876', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14877', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14877', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14877', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14878', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14878', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14878', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14879', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14879', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14879', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14880', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14880', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14880', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14881', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14881', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14881', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14882', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14882', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14882', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14883', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14883', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14883', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14884', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14884', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14884', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14885', '1', '5135', '1');
INSERT INTO `pets_skills` VALUES ('14885', '1', '5136', '1');
INSERT INTO `pets_skills` VALUES ('14885', '1', '5137', '1');
INSERT INTO `pets_skills` VALUES ('14886', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14886', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14887', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14887', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14888', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14888', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14889', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14889', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14890', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14890', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14891', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14891', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14892', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14892', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14893', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14893', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14894', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14894', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14895', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14895', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14896', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14896', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14897', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14897', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14898', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14898', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14899', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14899', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14900', '1', '5138', '1');
INSERT INTO `pets_skills` VALUES ('14900', '1', '5139', '1');
INSERT INTO `pets_skills` VALUES ('14901', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14901', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14901', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14902', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14902', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14902', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14903', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14903', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14903', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14904', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14904', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14904', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14905', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14905', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14905', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14906', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14906', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14906', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14907', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14907', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14907', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14908', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14908', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14908', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14909', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14909', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14909', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14910', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14910', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14910', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14911', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14911', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14911', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14912', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14912', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14912', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14913', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14913', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14913', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14914', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14914', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14914', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14915', '1', '5140', '1');
INSERT INTO `pets_skills` VALUES ('14915', '1', '5141', '1');
INSERT INTO `pets_skills` VALUES ('14915', '1', '5142', '1');
INSERT INTO `pets_skills` VALUES ('14916', '61', '5761', '1');
INSERT INTO `pets_skills` VALUES ('14917', '61', '5761', '2');
INSERT INTO `pets_skills` VALUES ('14918', '1', '6041', '1');
INSERT INTO `pets_skills` VALUES ('14918', '1', '6042', '1');
INSERT INTO `pets_skills` VALUES ('14918', '1', '6043', '1');
INSERT INTO `pets_skills` VALUES ('14918', '1', '6044', '1');
INSERT INTO `pets_skills` VALUES ('14929', '1', '11278', '1');
INSERT INTO `pets_skills` VALUES ('14929', '1', '11279', '1');
INSERT INTO `pets_skills` VALUES ('14930', '1', '11280', '1');
INSERT INTO `pets_skills` VALUES ('14930', '1', '11281', '1');
INSERT INTO `pets_skills` VALUES ('14931', '1', '11282', '1');
INSERT INTO `pets_skills` VALUES ('14931', '1', '11283', '1');
INSERT INTO `pets_skills` VALUES ('14932', '1', '10794', '1');
INSERT INTO `pets_skills` VALUES ('14932', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14932', '1', '10797', '1');
INSERT INTO `pets_skills` VALUES ('14932', '1', '10798', '1');
INSERT INTO `pets_skills` VALUES ('14936', '1', '10051', '1');
INSERT INTO `pets_skills` VALUES ('14936', '1', '10052', '1');
INSERT INTO `pets_skills` VALUES ('14936', '1', '10053', '1');
INSERT INTO `pets_skills` VALUES ('14936', '1', '10054', '1');
INSERT INTO `pets_skills` VALUES ('14937', '1', '10051', '2');
INSERT INTO `pets_skills` VALUES ('14937', '1', '10052', '2');
INSERT INTO `pets_skills` VALUES ('14937', '1', '10053', '2');
INSERT INTO `pets_skills` VALUES ('14937', '1', '10054', '2');
INSERT INTO `pets_skills` VALUES ('14938', '1', '10051', '3');
INSERT INTO `pets_skills` VALUES ('14938', '1', '10052', '3');
INSERT INTO `pets_skills` VALUES ('14938', '1', '10053', '3');
INSERT INTO `pets_skills` VALUES ('14938', '1', '10054', '3');
INSERT INTO `pets_skills` VALUES ('14939', '1', '10051', '4');
INSERT INTO `pets_skills` VALUES ('14939', '1', '10052', '4');
INSERT INTO `pets_skills` VALUES ('14939', '1', '10053', '4');
INSERT INTO `pets_skills` VALUES ('14939', '1', '10054', '4');
INSERT INTO `pets_skills` VALUES ('14940', '1', '10794', '2');
INSERT INTO `pets_skills` VALUES ('14940', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14940', '1', '10797', '2');
INSERT INTO `pets_skills` VALUES ('14940', '1', '10798', '2');
INSERT INTO `pets_skills` VALUES ('14941', '1', '10794', '3');
INSERT INTO `pets_skills` VALUES ('14941', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14941', '1', '10797', '3');
INSERT INTO `pets_skills` VALUES ('14941', '1', '10798', '3');
INSERT INTO `pets_skills` VALUES ('14942', '1', '10794', '4');
INSERT INTO `pets_skills` VALUES ('14942', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14942', '1', '10797', '4');
INSERT INTO `pets_skills` VALUES ('14942', '1', '10798', '4');
INSERT INTO `pets_skills` VALUES ('14947', '1', '11278', '2');
INSERT INTO `pets_skills` VALUES ('14947', '1', '11279', '2');
INSERT INTO `pets_skills` VALUES ('14948', '1', '11278', '3');
INSERT INTO `pets_skills` VALUES ('14948', '1', '11279', '3');
INSERT INTO `pets_skills` VALUES ('14949', '1', '11278', '4');
INSERT INTO `pets_skills` VALUES ('14949', '1', '11279', '4');
INSERT INTO `pets_skills` VALUES ('14950', '1', '11278', '5');
INSERT INTO `pets_skills` VALUES ('14950', '1', '11279', '5');
INSERT INTO `pets_skills` VALUES ('14951', '1', '11278', '6');
INSERT INTO `pets_skills` VALUES ('14951', '1', '11279', '6');
INSERT INTO `pets_skills` VALUES ('14952', '1', '11278', '7');
INSERT INTO `pets_skills` VALUES ('14952', '1', '11279', '7');
INSERT INTO `pets_skills` VALUES ('14953', '1', '11278', '8');
INSERT INTO `pets_skills` VALUES ('14953', '1', '11279', '8');
INSERT INTO `pets_skills` VALUES ('14954', '1', '11280', '2');
INSERT INTO `pets_skills` VALUES ('14954', '1', '11281', '2');
INSERT INTO `pets_skills` VALUES ('14955', '1', '11280', '3');
INSERT INTO `pets_skills` VALUES ('14955', '1', '11281', '3');
INSERT INTO `pets_skills` VALUES ('14956', '1', '11280', '4');
INSERT INTO `pets_skills` VALUES ('14956', '1', '11281', '4');
INSERT INTO `pets_skills` VALUES ('14957', '1', '11280', '5');
INSERT INTO `pets_skills` VALUES ('14957', '1', '11281', '5');
INSERT INTO `pets_skills` VALUES ('14958', '1', '11280', '6');
INSERT INTO `pets_skills` VALUES ('14958', '1', '11281', '6');
INSERT INTO `pets_skills` VALUES ('14959', '1', '11280', '7');
INSERT INTO `pets_skills` VALUES ('14959', '1', '11281', '7');
INSERT INTO `pets_skills` VALUES ('14960', '1', '11280', '8');
INSERT INTO `pets_skills` VALUES ('14960', '1', '11281', '8');
INSERT INTO `pets_skills` VALUES ('14961', '1', '11282', '2');
INSERT INTO `pets_skills` VALUES ('14961', '1', '11283', '2');
INSERT INTO `pets_skills` VALUES ('14962', '1', '11282', '3');
INSERT INTO `pets_skills` VALUES ('14962', '1', '11283', '3');
INSERT INTO `pets_skills` VALUES ('14963', '1', '11282', '4');
INSERT INTO `pets_skills` VALUES ('14963', '1', '11283', '4');
INSERT INTO `pets_skills` VALUES ('14964', '1', '11282', '5');
INSERT INTO `pets_skills` VALUES ('14964', '1', '11283', '5');
INSERT INTO `pets_skills` VALUES ('14965', '1', '11282', '6');
INSERT INTO `pets_skills` VALUES ('14965', '1', '11283', '6');
INSERT INTO `pets_skills` VALUES ('14966', '1', '11282', '7');
INSERT INTO `pets_skills` VALUES ('14966', '1', '11283', '7');
INSERT INTO `pets_skills` VALUES ('14967', '1', '11282', '8');
INSERT INTO `pets_skills` VALUES ('14967', '1', '11283', '8');
INSERT INTO `pets_skills` VALUES ('14971', '1', '11278', '9');
INSERT INTO `pets_skills` VALUES ('14971', '1', '11279', '9');
INSERT INTO `pets_skills` VALUES ('14972', '1', '11280', '9');
INSERT INTO `pets_skills` VALUES ('14972', '1', '11281', '9');
INSERT INTO `pets_skills` VALUES ('14973', '1', '11282', '9');
INSERT INTO `pets_skills` VALUES ('14973', '1', '11283', '9');
INSERT INTO `pets_skills` VALUES ('14974', '1', '10051', '5');
INSERT INTO `pets_skills` VALUES ('14974', '1', '10052', '5');
INSERT INTO `pets_skills` VALUES ('14974', '1', '10053', '5');
INSERT INTO `pets_skills` VALUES ('14974', '1', '10054', '5');
INSERT INTO `pets_skills` VALUES ('14975', '1', '10051', '6');
INSERT INTO `pets_skills` VALUES ('14975', '1', '10052', '6');
INSERT INTO `pets_skills` VALUES ('14975', '1', '10053', '6');
INSERT INTO `pets_skills` VALUES ('14975', '1', '10054', '6');
INSERT INTO `pets_skills` VALUES ('14976', '1', '10051', '101');
INSERT INTO `pets_skills` VALUES ('14976', '1', '10052', '101');
INSERT INTO `pets_skills` VALUES ('14976', '1', '10053', '101');
INSERT INTO `pets_skills` VALUES ('14976', '1', '10054', '101');
INSERT INTO `pets_skills` VALUES ('14977', '1', '10051', '102');
INSERT INTO `pets_skills` VALUES ('14977', '1', '10052', '102');
INSERT INTO `pets_skills` VALUES ('14977', '1', '10053', '102');
INSERT INTO `pets_skills` VALUES ('14977', '1', '10054', '102');
INSERT INTO `pets_skills` VALUES ('14978', '1', '10051', '103');
INSERT INTO `pets_skills` VALUES ('14978', '1', '10052', '103');
INSERT INTO `pets_skills` VALUES ('14978', '1', '10053', '103');
INSERT INTO `pets_skills` VALUES ('14978', '1', '10054', '103');
INSERT INTO `pets_skills` VALUES ('14979', '1', '10051', '104');
INSERT INTO `pets_skills` VALUES ('14979', '1', '10052', '104');
INSERT INTO `pets_skills` VALUES ('14979', '1', '10053', '104');
INSERT INTO `pets_skills` VALUES ('14979', '1', '10054', '104');
INSERT INTO `pets_skills` VALUES ('14980', '1', '10051', '105');
INSERT INTO `pets_skills` VALUES ('14980', '1', '10052', '105');
INSERT INTO `pets_skills` VALUES ('14980', '1', '10053', '105');
INSERT INTO `pets_skills` VALUES ('14980', '1', '10054', '105');
INSERT INTO `pets_skills` VALUES ('14981', '1', '10051', '106');
INSERT INTO `pets_skills` VALUES ('14981', '1', '10052', '106');
INSERT INTO `pets_skills` VALUES ('14981', '1', '10053', '106');
INSERT INTO `pets_skills` VALUES ('14981', '1', '10054', '106');
INSERT INTO `pets_skills` VALUES ('14982', '1', '10051', '107');
INSERT INTO `pets_skills` VALUES ('14982', '1', '10052', '107');
INSERT INTO `pets_skills` VALUES ('14982', '1', '10053', '107');
INSERT INTO `pets_skills` VALUES ('14982', '1', '10054', '107');
INSERT INTO `pets_skills` VALUES ('14983', '1', '10051', '108');
INSERT INTO `pets_skills` VALUES ('14983', '1', '10052', '108');
INSERT INTO `pets_skills` VALUES ('14983', '1', '10053', '108');
INSERT INTO `pets_skills` VALUES ('14983', '1', '10054', '108');
INSERT INTO `pets_skills` VALUES ('14984', '1', '10051', '109');
INSERT INTO `pets_skills` VALUES ('14984', '1', '10052', '109');
INSERT INTO `pets_skills` VALUES ('14984', '1', '10053', '109');
INSERT INTO `pets_skills` VALUES ('14984', '1', '10054', '109');
INSERT INTO `pets_skills` VALUES ('14985', '1', '10051', '110');
INSERT INTO `pets_skills` VALUES ('14985', '1', '10052', '110');
INSERT INTO `pets_skills` VALUES ('14985', '1', '10053', '110');
INSERT INTO `pets_skills` VALUES ('14985', '1', '10054', '110');
INSERT INTO `pets_skills` VALUES ('14986', '1', '10794', '5');
INSERT INTO `pets_skills` VALUES ('14986', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14986', '1', '10797', '5');
INSERT INTO `pets_skills` VALUES ('14986', '1', '10798', '5');
INSERT INTO `pets_skills` VALUES ('14987', '1', '10794', '6');
INSERT INTO `pets_skills` VALUES ('14987', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14987', '1', '10797', '6');
INSERT INTO `pets_skills` VALUES ('14987', '1', '10798', '6');
INSERT INTO `pets_skills` VALUES ('14988', '1', '10794', '7');
INSERT INTO `pets_skills` VALUES ('14988', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14988', '1', '10797', '7');
INSERT INTO `pets_skills` VALUES ('14988', '1', '10798', '7');
INSERT INTO `pets_skills` VALUES ('14989', '1', '10794', '8');
INSERT INTO `pets_skills` VALUES ('14989', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14989', '1', '10797', '8');
INSERT INTO `pets_skills` VALUES ('14989', '1', '10798', '8');
INSERT INTO `pets_skills` VALUES ('14990', '1', '10794', '101');
INSERT INTO `pets_skills` VALUES ('14990', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14990', '1', '10797', '101');
INSERT INTO `pets_skills` VALUES ('14990', '1', '10798', '101');
INSERT INTO `pets_skills` VALUES ('14991', '1', '10794', '102');
INSERT INTO `pets_skills` VALUES ('14991', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14991', '1', '10797', '102');
INSERT INTO `pets_skills` VALUES ('14991', '1', '10798', '102');
INSERT INTO `pets_skills` VALUES ('14992', '1', '10794', '103');
INSERT INTO `pets_skills` VALUES ('14992', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14992', '1', '10797', '103');
INSERT INTO `pets_skills` VALUES ('14992', '1', '10798', '103');
INSERT INTO `pets_skills` VALUES ('14993', '1', '10794', '104');
INSERT INTO `pets_skills` VALUES ('14993', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14993', '1', '10797', '104');
INSERT INTO `pets_skills` VALUES ('14993', '1', '10798', '104');
INSERT INTO `pets_skills` VALUES ('14994', '1', '10794', '105');
INSERT INTO `pets_skills` VALUES ('14994', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14994', '1', '10797', '105');
INSERT INTO `pets_skills` VALUES ('14994', '1', '10798', '105');
INSERT INTO `pets_skills` VALUES ('14995', '1', '10794', '106');
INSERT INTO `pets_skills` VALUES ('14995', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14995', '1', '10797', '106');
INSERT INTO `pets_skills` VALUES ('14995', '1', '10798', '106');
INSERT INTO `pets_skills` VALUES ('14996', '1', '10794', '107');
INSERT INTO `pets_skills` VALUES ('14996', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14996', '1', '10797', '107');
INSERT INTO `pets_skills` VALUES ('14996', '1', '10798', '107');
INSERT INTO `pets_skills` VALUES ('14997', '1', '10794', '108');
INSERT INTO `pets_skills` VALUES ('14997', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14997', '1', '10797', '108');
INSERT INTO `pets_skills` VALUES ('14997', '1', '10798', '108');
INSERT INTO `pets_skills` VALUES ('14998', '1', '10794', '109');
INSERT INTO `pets_skills` VALUES ('14998', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14998', '1', '10797', '109');
INSERT INTO `pets_skills` VALUES ('14998', '1', '10798', '109');
INSERT INTO `pets_skills` VALUES ('14999', '1', '10794', '110');
INSERT INTO `pets_skills` VALUES ('14999', '1', '10795', '1');
INSERT INTO `pets_skills` VALUES ('14999', '1', '10797', '110');
INSERT INTO `pets_skills` VALUES ('14999', '1', '10798', '110');
INSERT INTO `pets_skills` VALUES ('15022', '1', '11278', '101');
INSERT INTO `pets_skills` VALUES ('15022', '1', '11279', '101');
INSERT INTO `pets_skills` VALUES ('15023', '1', '11278', '102');
INSERT INTO `pets_skills` VALUES ('15023', '1', '11279', '102');
INSERT INTO `pets_skills` VALUES ('15024', '1', '11278', '103');
INSERT INTO `pets_skills` VALUES ('15024', '1', '11279', '103');
INSERT INTO `pets_skills` VALUES ('15025', '1', '11278', '104');
INSERT INTO `pets_skills` VALUES ('15025', '1', '11279', '104');
INSERT INTO `pets_skills` VALUES ('15026', '1', '11278', '105');
INSERT INTO `pets_skills` VALUES ('15026', '1', '11279', '105');
INSERT INTO `pets_skills` VALUES ('15027', '1', '11278', '106');
INSERT INTO `pets_skills` VALUES ('15027', '1', '11279', '106');
INSERT INTO `pets_skills` VALUES ('15028', '1', '11278', '107');
INSERT INTO `pets_skills` VALUES ('15028', '1', '11279', '107');
INSERT INTO `pets_skills` VALUES ('15029', '1', '11278', '108');
INSERT INTO `pets_skills` VALUES ('15029', '1', '11279', '108');
INSERT INTO `pets_skills` VALUES ('15030', '1', '11278', '109');
INSERT INTO `pets_skills` VALUES ('15030', '1', '11279', '109');
INSERT INTO `pets_skills` VALUES ('15031', '1', '11278', '110');
INSERT INTO `pets_skills` VALUES ('15031', '1', '11279', '110');
INSERT INTO `pets_skills` VALUES ('15032', '1', '11280', '101');
INSERT INTO `pets_skills` VALUES ('15032', '1', '11281', '101');
INSERT INTO `pets_skills` VALUES ('15033', '1', '11280', '102');
INSERT INTO `pets_skills` VALUES ('15033', '1', '11281', '102');
INSERT INTO `pets_skills` VALUES ('15034', '1', '11280', '103');
INSERT INTO `pets_skills` VALUES ('15034', '1', '11281', '103');
INSERT INTO `pets_skills` VALUES ('15035', '1', '11280', '104');
INSERT INTO `pets_skills` VALUES ('15035', '1', '11281', '104');
INSERT INTO `pets_skills` VALUES ('15036', '1', '11280', '105');
INSERT INTO `pets_skills` VALUES ('15036', '1', '11281', '105');
INSERT INTO `pets_skills` VALUES ('15037', '1', '11280', '106');
INSERT INTO `pets_skills` VALUES ('15037', '1', '11281', '106');
INSERT INTO `pets_skills` VALUES ('15038', '1', '11280', '107');
INSERT INTO `pets_skills` VALUES ('15038', '1', '11281', '107');
INSERT INTO `pets_skills` VALUES ('15039', '1', '11280', '108');
INSERT INTO `pets_skills` VALUES ('15039', '1', '11281', '108');
INSERT INTO `pets_skills` VALUES ('15040', '1', '11280', '109');
INSERT INTO `pets_skills` VALUES ('15040', '1', '11281', '109');
INSERT INTO `pets_skills` VALUES ('15041', '1', '11280', '110');
INSERT INTO `pets_skills` VALUES ('15041', '1', '11281', '110');
INSERT INTO `pets_skills` VALUES ('15042', '1', '11282', '101');
INSERT INTO `pets_skills` VALUES ('15042', '1', '11283', '101');
INSERT INTO `pets_skills` VALUES ('15043', '1', '11282', '102');
INSERT INTO `pets_skills` VALUES ('15043', '1', '11283', '102');
INSERT INTO `pets_skills` VALUES ('15044', '1', '11282', '103');
INSERT INTO `pets_skills` VALUES ('15044', '1', '11283', '103');
INSERT INTO `pets_skills` VALUES ('15045', '1', '11282', '104');
INSERT INTO `pets_skills` VALUES ('15045', '1', '11283', '104');
INSERT INTO `pets_skills` VALUES ('15046', '1', '11282', '105');
INSERT INTO `pets_skills` VALUES ('15046', '1', '11283', '105');
INSERT INTO `pets_skills` VALUES ('15047', '1', '11282', '106');
INSERT INTO `pets_skills` VALUES ('15047', '1', '11283', '106');
INSERT INTO `pets_skills` VALUES ('15048', '1', '11282', '107');
INSERT INTO `pets_skills` VALUES ('15048', '1', '11283', '107');
INSERT INTO `pets_skills` VALUES ('15049', '1', '11282', '108');
INSERT INTO `pets_skills` VALUES ('15049', '1', '11283', '108');
INSERT INTO `pets_skills` VALUES ('15050', '1', '11282', '109');
INSERT INTO `pets_skills` VALUES ('15050', '1', '11283', '109');
INSERT INTO `pets_skills` VALUES ('15051', '1', '11282', '110');
INSERT INTO `pets_skills` VALUES ('15051', '1', '11283', '110');
INSERT INTO `pets_skills` VALUES ('15054', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('15054', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('15055', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('15055', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('15056', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('15056', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('15057', '1', '4079', '1');
INSERT INTO `pets_skills` VALUES ('15057', '1', '14767', '1');
INSERT INTO `pets_skills` VALUES ('15058', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('15059', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('15060', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('15061', '1', '4230', '1');
INSERT INTO `pets_skills` VALUES ('15062', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('15062', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('15063', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('15063', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('15064', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('15064', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('15065', '1', '5110', '1');
INSERT INTO `pets_skills` VALUES ('15065', '1', '5111', '1');
INSERT INTO `pets_skills` VALUES ('15066', '1', '11323', '1');
INSERT INTO `pets_skills` VALUES ('15066', '1', '11324', '1');
INSERT INTO `pets_skills` VALUES ('15067', '1', '11323', '2');
INSERT INTO `pets_skills` VALUES ('15067', '1', '11324', '2');
INSERT INTO `pets_skills` VALUES ('15068', '1', '11323', '3');
INSERT INTO `pets_skills` VALUES ('15068', '1', '11324', '3');
INSERT INTO `pets_skills` VALUES ('15069', '1', '11323', '4');
INSERT INTO `pets_skills` VALUES ('15069', '1', '11324', '4');
INSERT INTO `pets_skills` VALUES ('15070', '1', '11323', '5');
INSERT INTO `pets_skills` VALUES ('15070', '1', '11324', '5');
INSERT INTO `pets_skills` VALUES ('15071', '1', '11323', '6');
INSERT INTO `pets_skills` VALUES ('15071', '1', '11324', '6');
INSERT INTO `pets_skills` VALUES ('15072', '1', '11323', '7');
INSERT INTO `pets_skills` VALUES ('15072', '1', '11324', '7');
INSERT INTO `pets_skills` VALUES ('15073', '1', '11323', '8');
INSERT INTO `pets_skills` VALUES ('15073', '1', '11324', '8');
INSERT INTO `pets_skills` VALUES ('15074', '1', '11325', '1');
INSERT INTO `pets_skills` VALUES ('15074', '1', '11326', '1');
INSERT INTO `pets_skills` VALUES ('15075', '1', '11325', '2');
INSERT INTO `pets_skills` VALUES ('15075', '1', '11326', '2');
INSERT INTO `pets_skills` VALUES ('15076', '1', '11325', '3');
INSERT INTO `pets_skills` VALUES ('15076', '1', '11326', '3');
INSERT INTO `pets_skills` VALUES ('15077', '1', '11325', '4');
INSERT INTO `pets_skills` VALUES ('15077', '1', '11326', '4');
INSERT INTO `pets_skills` VALUES ('15078', '1', '11325', '5');
INSERT INTO `pets_skills` VALUES ('15078', '1', '11326', '5');
INSERT INTO `pets_skills` VALUES ('15079', '1', '11325', '6');
INSERT INTO `pets_skills` VALUES ('15079', '1', '11326', '6');
INSERT INTO `pets_skills` VALUES ('15080', '1', '11325', '7');
INSERT INTO `pets_skills` VALUES ('15080', '1', '11326', '7');
INSERT INTO `pets_skills` VALUES ('15081', '1', '11325', '8');
INSERT INTO `pets_skills` VALUES ('15081', '1', '11326', '8');
INSERT INTO `pets_skills` VALUES ('15082', '1', '11327', '1');
INSERT INTO `pets_skills` VALUES ('15082', '1', '11328', '1');
INSERT INTO `pets_skills` VALUES ('15083', '1', '11327', '2');
INSERT INTO `pets_skills` VALUES ('15083', '1', '11328', '2');
INSERT INTO `pets_skills` VALUES ('15084', '1', '11327', '3');
INSERT INTO `pets_skills` VALUES ('15084', '1', '11328', '3');
INSERT INTO `pets_skills` VALUES ('15085', '1', '11327', '4');
INSERT INTO `pets_skills` VALUES ('15085', '1', '11328', '4');
INSERT INTO `pets_skills` VALUES ('15086', '1', '11327', '5');
INSERT INTO `pets_skills` VALUES ('15086', '1', '11328', '5');
INSERT INTO `pets_skills` VALUES ('15087', '1', '11327', '6');
INSERT INTO `pets_skills` VALUES ('15087', '1', '11328', '6');
INSERT INTO `pets_skills` VALUES ('15088', '1', '11327', '7');
INSERT INTO `pets_skills` VALUES ('15088', '1', '11328', '7');
INSERT INTO `pets_skills` VALUES ('15089', '1', '11327', '8');
INSERT INTO `pets_skills` VALUES ('15089', '1', '11328', '8');
INSERT INTO `pets_skills` VALUES ('15090', '1', '11341', '1');
INSERT INTO `pets_skills` VALUES ('15090', '1', '11342', '1');
INSERT INTO `pets_skills` VALUES ('15091', '1', '11341', '2');
INSERT INTO `pets_skills` VALUES ('15091', '1', '11342', '2');
INSERT INTO `pets_skills` VALUES ('15092', '1', '11341', '3');
INSERT INTO `pets_skills` VALUES ('15092', '1', '11342', '3');
INSERT INTO `pets_skills` VALUES ('15093', '1', '11341', '4');
INSERT INTO `pets_skills` VALUES ('15093', '1', '11342', '4');
INSERT INTO `pets_skills` VALUES ('15094', '1', '11341', '5');
INSERT INTO `pets_skills` VALUES ('15094', '1', '11342', '5');
INSERT INTO `pets_skills` VALUES ('15095', '1', '11341', '6');
INSERT INTO `pets_skills` VALUES ('15095', '1', '11342', '6');
INSERT INTO `pets_skills` VALUES ('15096', '1', '11341', '7');
INSERT INTO `pets_skills` VALUES ('15096', '1', '11342', '7');
INSERT INTO `pets_skills` VALUES ('15097', '1', '11341', '8');
INSERT INTO `pets_skills` VALUES ('15097', '1', '11342', '8');
INSERT INTO `pets_skills` VALUES ('15098', '1', '11343', '1');
INSERT INTO `pets_skills` VALUES ('15098', '1', '11344', '1');
INSERT INTO `pets_skills` VALUES ('15099', '1', '11343', '2');
INSERT INTO `pets_skills` VALUES ('15099', '1', '11344', '2');
INSERT INTO `pets_skills` VALUES ('15100', '1', '11343', '3');
INSERT INTO `pets_skills` VALUES ('15100', '1', '11344', '3');
INSERT INTO `pets_skills` VALUES ('15101', '1', '11343', '4');
INSERT INTO `pets_skills` VALUES ('15101', '1', '11344', '4');
INSERT INTO `pets_skills` VALUES ('15102', '1', '11343', '5');
INSERT INTO `pets_skills` VALUES ('15102', '1', '11344', '5');
INSERT INTO `pets_skills` VALUES ('15103', '1', '11343', '6');
INSERT INTO `pets_skills` VALUES ('15103', '1', '11344', '6');
INSERT INTO `pets_skills` VALUES ('15104', '1', '11343', '7');
INSERT INTO `pets_skills` VALUES ('15104', '1', '11344', '7');
INSERT INTO `pets_skills` VALUES ('15105', '1', '11343', '8');
INSERT INTO `pets_skills` VALUES ('15105', '1', '11344', '8');
INSERT INTO `pets_skills` VALUES ('15106', '1', '11345', '1');
INSERT INTO `pets_skills` VALUES ('15106', '1', '11346', '1');
INSERT INTO `pets_skills` VALUES ('15107', '1', '11345', '2');
INSERT INTO `pets_skills` VALUES ('15107', '1', '11346', '2');
INSERT INTO `pets_skills` VALUES ('15108', '1', '11345', '3');
INSERT INTO `pets_skills` VALUES ('15108', '1', '11346', '3');
INSERT INTO `pets_skills` VALUES ('15109', '1', '11345', '4');
INSERT INTO `pets_skills` VALUES ('15109', '1', '11346', '4');
INSERT INTO `pets_skills` VALUES ('15110', '1', '11345', '5');
INSERT INTO `pets_skills` VALUES ('15110', '1', '11346', '5');
INSERT INTO `pets_skills` VALUES ('15111', '1', '11345', '6');
INSERT INTO `pets_skills` VALUES ('15111', '1', '11346', '6');
INSERT INTO `pets_skills` VALUES ('15112', '1', '11345', '7');
INSERT INTO `pets_skills` VALUES ('15112', '1', '11346', '7');
INSERT INTO `pets_skills` VALUES ('15113', '1', '11345', '8');
INSERT INTO `pets_skills` VALUES ('15113', '1', '11346', '8');
INSERT INTO `pets_skills` VALUES ('15114', '1', '11332', '1');
INSERT INTO `pets_skills` VALUES ('15114', '1', '11333', '1');
INSERT INTO `pets_skills` VALUES ('15115', '1', '11332', '2');
INSERT INTO `pets_skills` VALUES ('15115', '1', '11333', '2');
INSERT INTO `pets_skills` VALUES ('15116', '1', '11332', '3');
INSERT INTO `pets_skills` VALUES ('15116', '1', '11333', '3');
INSERT INTO `pets_skills` VALUES ('15117', '1', '11332', '4');
INSERT INTO `pets_skills` VALUES ('15117', '1', '11333', '4');
INSERT INTO `pets_skills` VALUES ('15118', '1', '11332', '5');
INSERT INTO `pets_skills` VALUES ('15118', '1', '11333', '5');
INSERT INTO `pets_skills` VALUES ('15119', '1', '11332', '6');
INSERT INTO `pets_skills` VALUES ('15119', '1', '11333', '6');
INSERT INTO `pets_skills` VALUES ('15120', '1', '11332', '7');
INSERT INTO `pets_skills` VALUES ('15120', '1', '11333', '7');
INSERT INTO `pets_skills` VALUES ('15121', '1', '11332', '8');
INSERT INTO `pets_skills` VALUES ('15121', '1', '11333', '8');
INSERT INTO `pets_skills` VALUES ('15122', '1', '11334', '1');
INSERT INTO `pets_skills` VALUES ('15122', '1', '11335', '1');
INSERT INTO `pets_skills` VALUES ('15123', '1', '11334', '2');
INSERT INTO `pets_skills` VALUES ('15123', '1', '11335', '2');
INSERT INTO `pets_skills` VALUES ('15124', '1', '11334', '3');
INSERT INTO `pets_skills` VALUES ('15124', '1', '11335', '3');
INSERT INTO `pets_skills` VALUES ('15125', '1', '11334', '4');
INSERT INTO `pets_skills` VALUES ('15125', '1', '11335', '4');
INSERT INTO `pets_skills` VALUES ('15126', '1', '11334', '5');
INSERT INTO `pets_skills` VALUES ('15126', '1', '11335', '5');
INSERT INTO `pets_skills` VALUES ('15127', '1', '11334', '6');
INSERT INTO `pets_skills` VALUES ('15127', '1', '11335', '6');
INSERT INTO `pets_skills` VALUES ('15128', '1', '11334', '7');
INSERT INTO `pets_skills` VALUES ('15128', '1', '11335', '7');
INSERT INTO `pets_skills` VALUES ('15129', '1', '11334', '8');
INSERT INTO `pets_skills` VALUES ('15129', '1', '11335', '8');
INSERT INTO `pets_skills` VALUES ('15130', '1', '11336', '1');
INSERT INTO `pets_skills` VALUES ('15130', '1', '11337', '1');
INSERT INTO `pets_skills` VALUES ('15131', '1', '11336', '2');
INSERT INTO `pets_skills` VALUES ('15131', '1', '11337', '2');
INSERT INTO `pets_skills` VALUES ('15132', '1', '11336', '3');
INSERT INTO `pets_skills` VALUES ('15132', '1', '11337', '3');
INSERT INTO `pets_skills` VALUES ('15133', '1', '11336', '4');
INSERT INTO `pets_skills` VALUES ('15133', '1', '11337', '4');
INSERT INTO `pets_skills` VALUES ('15134', '1', '11336', '5');
INSERT INTO `pets_skills` VALUES ('15134', '1', '11337', '5');
INSERT INTO `pets_skills` VALUES ('15135', '1', '11336', '6');
INSERT INTO `pets_skills` VALUES ('15135', '1', '11337', '6');
INSERT INTO `pets_skills` VALUES ('15136', '1', '11336', '7');
INSERT INTO `pets_skills` VALUES ('15136', '1', '11337', '7');
INSERT INTO `pets_skills` VALUES ('15137', '1', '11336', '8');
INSERT INTO `pets_skills` VALUES ('15137', '1', '11337', '8');
INSERT INTO `pets_skills` VALUES ('15138', '1', '10087', '1');
INSERT INTO `pets_skills` VALUES ('15138', '1', '10088', '1');
INSERT INTO `pets_skills` VALUES ('15139', '1', '10087', '2');
INSERT INTO `pets_skills` VALUES ('15139', '1', '10088', '2');
INSERT INTO `pets_skills` VALUES ('15140', '1', '10087', '3');
INSERT INTO `pets_skills` VALUES ('15140', '1', '10088', '3');
INSERT INTO `pets_skills` VALUES ('15141', '1', '10087', '4');
INSERT INTO `pets_skills` VALUES ('15141', '1', '10088', '4');
INSERT INTO `pets_skills` VALUES ('16025', '1', '5442', '1');
INSERT INTO `pets_skills` VALUES ('16025', '60', '5442', '2');
INSERT INTO `pets_skills` VALUES ('16025', '65', '5442', '3');
INSERT INTO `pets_skills` VALUES ('16025', '70', '5442', '4');
INSERT INTO `pets_skills` VALUES ('16025', '60', '5443', '1');
INSERT INTO `pets_skills` VALUES ('16025', '65', '5443', '2');
INSERT INTO `pets_skills` VALUES ('16025', '70', '5443', '3');
INSERT INTO `pets_skills` VALUES ('16025', '75', '5443', '4');
INSERT INTO `pets_skills` VALUES ('16025', '65', '5444', '1');
INSERT INTO `pets_skills` VALUES ('16025', '70', '5444', '2');
INSERT INTO `pets_skills` VALUES ('16025', '70', '5445', '1');
INSERT INTO `pets_skills` VALUES ('16034', '1', '5189', '1');
INSERT INTO `pets_skills` VALUES ('16034', '1', '5190', '1');
INSERT INTO `pets_skills` VALUES ('16034', '1', '5193', '1');
INSERT INTO `pets_skills` VALUES ('16034', '1', '5194', '1');
INSERT INTO `pets_skills` VALUES ('16034', '1', '5195', '1');
INSERT INTO `pets_skills` VALUES ('16034', '1', '5201', '1');
INSERT INTO `pets_skills` VALUES ('16034', '1', '5587', '1');
INSERT INTO `pets_skills` VALUES ('16034', '1', '5590', '1');
INSERT INTO `pets_skills` VALUES ('16034', '1', '5771', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5186', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5187', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5189', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5191', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5195', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5586', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5587', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5588', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5589', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5590', '1');
INSERT INTO `pets_skills` VALUES ('16035', '1', '5771', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5186', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5187', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5189', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5193', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5194', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5195', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5586', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5587', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5588', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5590', '1');
INSERT INTO `pets_skills` VALUES ('16036', '1', '5771', '1');
INSERT INTO `pets_skills` VALUES ('16037', '1', '5442', '1');
INSERT INTO `pets_skills` VALUES ('16037', '60', '5442', '2');
INSERT INTO `pets_skills` VALUES ('16037', '65', '5442', '3');
INSERT INTO `pets_skills` VALUES ('16037', '70', '5442', '4');
INSERT INTO `pets_skills` VALUES ('16037', '60', '5443', '1');
INSERT INTO `pets_skills` VALUES ('16037', '65', '5443', '2');
INSERT INTO `pets_skills` VALUES ('16037', '70', '5443', '3');
INSERT INTO `pets_skills` VALUES ('16037', '75', '5443', '4');
INSERT INTO `pets_skills` VALUES ('16037', '65', '5444', '1');
INSERT INTO `pets_skills` VALUES ('16037', '70', '5444', '2');
INSERT INTO `pets_skills` VALUES ('16037', '70', '5445', '1');
INSERT INTO `pets_skills` VALUES ('16037', '70', '5584', '1');
INSERT INTO `pets_skills` VALUES ('16038', '1', '4710', '0');
INSERT INTO `pets_skills` VALUES ('16038', '1', '4711', '0');
INSERT INTO `pets_skills` VALUES ('16038', '1', '5585', '0');
INSERT INTO `pets_skills` VALUES ('16039', '1', '4712', '0');
INSERT INTO `pets_skills` VALUES ('16039', '1', '4713', '0');
INSERT INTO `pets_skills` VALUES ('16039', '1', '5585', '0');
INSERT INTO `pets_skills` VALUES ('16041', '1', '5442', '1');
INSERT INTO `pets_skills` VALUES ('16041', '60', '5442', '2');
INSERT INTO `pets_skills` VALUES ('16041', '65', '5442', '3');
INSERT INTO `pets_skills` VALUES ('16041', '70', '5442', '4');
INSERT INTO `pets_skills` VALUES ('16041', '60', '5443', '1');
INSERT INTO `pets_skills` VALUES ('16041', '65', '5443', '2');
INSERT INTO `pets_skills` VALUES ('16041', '70', '5443', '3');
INSERT INTO `pets_skills` VALUES ('16041', '75', '5443', '4');
INSERT INTO `pets_skills` VALUES ('16041', '65', '5444', '1');
INSERT INTO `pets_skills` VALUES ('16041', '70', '5444', '2');
INSERT INTO `pets_skills` VALUES ('16041', '70', '5445', '1');
INSERT INTO `pets_skills` VALUES ('16042', '1', '5442', '1');
INSERT INTO `pets_skills` VALUES ('16042', '60', '5442', '2');
INSERT INTO `pets_skills` VALUES ('16042', '65', '5442', '3');
INSERT INTO `pets_skills` VALUES ('16042', '70', '5442', '4');
INSERT INTO `pets_skills` VALUES ('16042', '60', '5443', '1');
INSERT INTO `pets_skills` VALUES ('16042', '65', '5443', '2');
INSERT INTO `pets_skills` VALUES ('16042', '70', '5443', '3');
INSERT INTO `pets_skills` VALUES ('16042', '75', '5443', '4');
INSERT INTO `pets_skills` VALUES ('16042', '65', '5444', '1');
INSERT INTO `pets_skills` VALUES ('16042', '70', '5444', '2');
INSERT INTO `pets_skills` VALUES ('16042', '70', '5445', '1');
INSERT INTO `pets_skills` VALUES ('16042', '70', '5584', '1');
INSERT INTO `pets_skills` VALUES ('16043', '25', '5749', '1');
INSERT INTO `pets_skills` VALUES ('16043', '30', '5749', '2');
INSERT INTO `pets_skills` VALUES ('16043', '35', '5749', '3');
INSERT INTO `pets_skills` VALUES ('16043', '40', '5749', '4');
INSERT INTO `pets_skills` VALUES ('16043', '45', '5749', '5');
INSERT INTO `pets_skills` VALUES ('16043', '50', '5749', '6');
INSERT INTO `pets_skills` VALUES ('16043', '55', '5749', '7');
INSERT INTO `pets_skills` VALUES ('16043', '60', '5749', '8');
INSERT INTO `pets_skills` VALUES ('16043', '65', '5749', '9');
INSERT INTO `pets_skills` VALUES ('16043', '70', '5749', '10');
INSERT INTO `pets_skills` VALUES ('16043', '75', '5749', '11');
INSERT INTO `pets_skills` VALUES ('16043', '80', '5749', '12');
INSERT INTO `pets_skills` VALUES ('16043', '25', '5750', '1');
INSERT INTO `pets_skills` VALUES ('16043', '30', '5750', '2');
INSERT INTO `pets_skills` VALUES ('16043', '35', '5750', '3');
INSERT INTO `pets_skills` VALUES ('16043', '40', '5750', '4');
INSERT INTO `pets_skills` VALUES ('16043', '45', '5750', '5');
INSERT INTO `pets_skills` VALUES ('16043', '50', '5750', '6');
INSERT INTO `pets_skills` VALUES ('16043', '55', '5750', '7');
INSERT INTO `pets_skills` VALUES ('16043', '60', '5750', '8');
INSERT INTO `pets_skills` VALUES ('16043', '65', '5750', '9');
INSERT INTO `pets_skills` VALUES ('16043', '70', '5750', '10');
INSERT INTO `pets_skills` VALUES ('16043', '75', '5750', '11');
INSERT INTO `pets_skills` VALUES ('16043', '80', '5750', '12');
INSERT INTO `pets_skills` VALUES ('16043', '25', '5751', '1');
INSERT INTO `pets_skills` VALUES ('16043', '30', '5751', '2');
INSERT INTO `pets_skills` VALUES ('16043', '35', '5751', '3');
INSERT INTO `pets_skills` VALUES ('16043', '40', '5751', '4');
INSERT INTO `pets_skills` VALUES ('16043', '45', '5751', '5');
INSERT INTO `pets_skills` VALUES ('16043', '50', '5751', '6');
INSERT INTO `pets_skills` VALUES ('16043', '55', '5751', '7');
INSERT INTO `pets_skills` VALUES ('16043', '60', '5751', '8');
INSERT INTO `pets_skills` VALUES ('16043', '65', '5751', '9');
INSERT INTO `pets_skills` VALUES ('16043', '70', '5751', '10');
INSERT INTO `pets_skills` VALUES ('16043', '75', '5751', '11');
INSERT INTO `pets_skills` VALUES ('16043', '80', '5751', '12');
INSERT INTO `pets_skills` VALUES ('16043', '25', '5752', '1');
INSERT INTO `pets_skills` VALUES ('16043', '30', '5752', '2');
INSERT INTO `pets_skills` VALUES ('16043', '35', '5752', '3');
INSERT INTO `pets_skills` VALUES ('16043', '40', '5752', '4');
INSERT INTO `pets_skills` VALUES ('16043', '45', '5752', '5');
INSERT INTO `pets_skills` VALUES ('16043', '50', '5752', '6');
INSERT INTO `pets_skills` VALUES ('16043', '55', '5752', '7');
INSERT INTO `pets_skills` VALUES ('16043', '60', '5752', '8');
INSERT INTO `pets_skills` VALUES ('16043', '65', '5752', '9');
INSERT INTO `pets_skills` VALUES ('16043', '70', '5752', '10');
INSERT INTO `pets_skills` VALUES ('16043', '75', '5752', '11');
INSERT INTO `pets_skills` VALUES ('16043', '80', '5752', '12');
INSERT INTO `pets_skills` VALUES ('16043', '1', '5753', '1');
INSERT INTO `pets_skills` VALUES ('16044', '25', '5745', '1');
INSERT INTO `pets_skills` VALUES ('16044', '30', '5745', '2');
INSERT INTO `pets_skills` VALUES ('16044', '35', '5745', '3');
INSERT INTO `pets_skills` VALUES ('16044', '40', '5745', '4');
INSERT INTO `pets_skills` VALUES ('16044', '45', '5745', '5');
INSERT INTO `pets_skills` VALUES ('16044', '50', '5745', '6');
INSERT INTO `pets_skills` VALUES ('16044', '55', '5745', '7');
INSERT INTO `pets_skills` VALUES ('16044', '60', '5745', '8');
INSERT INTO `pets_skills` VALUES ('16044', '65', '5745', '9');
INSERT INTO `pets_skills` VALUES ('16044', '70', '5745', '10');
INSERT INTO `pets_skills` VALUES ('16044', '75', '5745', '11');
INSERT INTO `pets_skills` VALUES ('16044', '80', '5745', '12');
INSERT INTO `pets_skills` VALUES ('16044', '25', '5746', '1');
INSERT INTO `pets_skills` VALUES ('16044', '30', '5746', '2');
INSERT INTO `pets_skills` VALUES ('16044', '35', '5746', '3');
INSERT INTO `pets_skills` VALUES ('16044', '40', '5746', '4');
INSERT INTO `pets_skills` VALUES ('16044', '45', '5746', '5');
INSERT INTO `pets_skills` VALUES ('16044', '50', '5746', '6');
INSERT INTO `pets_skills` VALUES ('16044', '55', '5746', '7');
INSERT INTO `pets_skills` VALUES ('16044', '60', '5746', '8');
INSERT INTO `pets_skills` VALUES ('16044', '65', '5746', '9');
INSERT INTO `pets_skills` VALUES ('16044', '70', '5746', '10');
INSERT INTO `pets_skills` VALUES ('16044', '75', '5746', '11');
INSERT INTO `pets_skills` VALUES ('16044', '80', '5746', '12');
INSERT INTO `pets_skills` VALUES ('16044', '25', '5747', '1');
INSERT INTO `pets_skills` VALUES ('16044', '30', '5747', '2');
INSERT INTO `pets_skills` VALUES ('16044', '35', '5747', '3');
INSERT INTO `pets_skills` VALUES ('16044', '40', '5747', '4');
INSERT INTO `pets_skills` VALUES ('16044', '45', '5747', '5');
INSERT INTO `pets_skills` VALUES ('16044', '50', '5747', '6');
INSERT INTO `pets_skills` VALUES ('16044', '55', '5747', '7');
INSERT INTO `pets_skills` VALUES ('16044', '60', '5747', '8');
INSERT INTO `pets_skills` VALUES ('16044', '65', '5747', '9');
INSERT INTO `pets_skills` VALUES ('16044', '70', '5747', '10');
INSERT INTO `pets_skills` VALUES ('16044', '75', '5747', '11');
INSERT INTO `pets_skills` VALUES ('16044', '80', '5747', '12');
INSERT INTO `pets_skills` VALUES ('16044', '25', '5748', '1');
INSERT INTO `pets_skills` VALUES ('16044', '30', '5748', '2');
INSERT INTO `pets_skills` VALUES ('16044', '35', '5748', '3');
INSERT INTO `pets_skills` VALUES ('16044', '40', '5748', '4');
INSERT INTO `pets_skills` VALUES ('16044', '45', '5748', '5');
INSERT INTO `pets_skills` VALUES ('16044', '50', '5748', '6');
INSERT INTO `pets_skills` VALUES ('16044', '55', '5748', '7');
INSERT INTO `pets_skills` VALUES ('16044', '60', '5748', '8');
INSERT INTO `pets_skills` VALUES ('16044', '65', '5748', '9');
INSERT INTO `pets_skills` VALUES ('16044', '70', '5748', '10');
INSERT INTO `pets_skills` VALUES ('16044', '75', '5748', '11');
INSERT INTO `pets_skills` VALUES ('16044', '80', '5748', '12');
INSERT INTO `pets_skills` VALUES ('16044', '1', '5753', '1');
INSERT INTO `pets_skills` VALUES ('16045', '1', '5186', '2');
INSERT INTO `pets_skills` VALUES ('16045', '1', '5187', '4');
INSERT INTO `pets_skills` VALUES ('16045', '1', '5189', '6');
INSERT INTO `pets_skills` VALUES ('16045', '1', '5192', '2');
INSERT INTO `pets_skills` VALUES ('16045', '1', '5586', '3');
INSERT INTO `pets_skills` VALUES ('16045', '1', '5587', '3');
INSERT INTO `pets_skills` VALUES ('16045', '1', '5588', '3');
INSERT INTO `pets_skills` VALUES ('16045', '1', '5589', '3');
INSERT INTO `pets_skills` VALUES ('16045', '25', '5745', '1');
INSERT INTO `pets_skills` VALUES ('16045', '30', '5745', '2');
INSERT INTO `pets_skills` VALUES ('16045', '35', '5745', '3');
INSERT INTO `pets_skills` VALUES ('16045', '40', '5745', '4');
INSERT INTO `pets_skills` VALUES ('16045', '45', '5745', '5');
INSERT INTO `pets_skills` VALUES ('16045', '50', '5745', '6');
INSERT INTO `pets_skills` VALUES ('16045', '55', '5745', '7');
INSERT INTO `pets_skills` VALUES ('16045', '60', '5745', '8');
INSERT INTO `pets_skills` VALUES ('16045', '65', '5745', '9');
INSERT INTO `pets_skills` VALUES ('16045', '70', '5745', '10');
INSERT INTO `pets_skills` VALUES ('16045', '75', '5745', '11');
INSERT INTO `pets_skills` VALUES ('16045', '80', '5745', '12');
INSERT INTO `pets_skills` VALUES ('16045', '1', '5753', '1');
INSERT INTO `pets_skills` VALUES ('16045', '25', '6046', '1');
INSERT INTO `pets_skills` VALUES ('16045', '30', '6046', '2');
INSERT INTO `pets_skills` VALUES ('16045', '35', '6046', '3');
INSERT INTO `pets_skills` VALUES ('16045', '40', '6046', '4');
INSERT INTO `pets_skills` VALUES ('16045', '45', '6046', '5');
INSERT INTO `pets_skills` VALUES ('16045', '50', '6046', '6');
INSERT INTO `pets_skills` VALUES ('16045', '55', '6046', '7');
INSERT INTO `pets_skills` VALUES ('16045', '60', '6046', '8');
INSERT INTO `pets_skills` VALUES ('16045', '65', '6046', '9');
INSERT INTO `pets_skills` VALUES ('16045', '70', '6046', '10');
INSERT INTO `pets_skills` VALUES ('16045', '75', '6046', '11');
INSERT INTO `pets_skills` VALUES ('16045', '80', '6046', '12');
INSERT INTO `pets_skills` VALUES ('16045', '25', '6047', '1');
INSERT INTO `pets_skills` VALUES ('16045', '30', '6047', '2');
INSERT INTO `pets_skills` VALUES ('16045', '35', '6047', '3');
INSERT INTO `pets_skills` VALUES ('16045', '40', '6047', '4');
INSERT INTO `pets_skills` VALUES ('16045', '45', '6047', '5');
INSERT INTO `pets_skills` VALUES ('16045', '50', '6047', '6');
INSERT INTO `pets_skills` VALUES ('16045', '55', '6047', '7');
INSERT INTO `pets_skills` VALUES ('16045', '60', '6047', '8');
INSERT INTO `pets_skills` VALUES ('16045', '65', '6047', '9');
INSERT INTO `pets_skills` VALUES ('16045', '70', '6047', '10');
INSERT INTO `pets_skills` VALUES ('16045', '75', '6047', '11');
INSERT INTO `pets_skills` VALUES ('16045', '80', '6047', '12');
INSERT INTO `pets_skills` VALUES ('16045', '25', '6048', '1');
INSERT INTO `pets_skills` VALUES ('16045', '30', '6048', '2');
INSERT INTO `pets_skills` VALUES ('16045', '35', '6048', '3');
INSERT INTO `pets_skills` VALUES ('16045', '40', '6048', '4');
INSERT INTO `pets_skills` VALUES ('16045', '45', '6048', '5');
INSERT INTO `pets_skills` VALUES ('16045', '50', '6048', '6');
INSERT INTO `pets_skills` VALUES ('16045', '55', '6048', '7');
INSERT INTO `pets_skills` VALUES ('16045', '60', '6048', '8');
INSERT INTO `pets_skills` VALUES ('16045', '65', '6048', '9');
INSERT INTO `pets_skills` VALUES ('16045', '70', '6048', '10');
INSERT INTO `pets_skills` VALUES ('16045', '75', '6048', '11');
INSERT INTO `pets_skills` VALUES ('16045', '80', '6048', '12');
INSERT INTO `pets_skills` VALUES ('16046', '25', '5752', '1');
INSERT INTO `pets_skills` VALUES ('16046', '30', '5752', '2');
INSERT INTO `pets_skills` VALUES ('16046', '35', '5752', '3');
INSERT INTO `pets_skills` VALUES ('16046', '40', '5752', '4');
INSERT INTO `pets_skills` VALUES ('16046', '45', '5752', '5');
INSERT INTO `pets_skills` VALUES ('16046', '50', '5752', '6');
INSERT INTO `pets_skills` VALUES ('16046', '55', '5752', '7');
INSERT INTO `pets_skills` VALUES ('16046', '60', '5752', '8');
INSERT INTO `pets_skills` VALUES ('16046', '65', '5752', '9');
INSERT INTO `pets_skills` VALUES ('16046', '70', '5752', '10');
INSERT INTO `pets_skills` VALUES ('16046', '75', '5752', '11');
INSERT INTO `pets_skills` VALUES ('16046', '80', '5752', '12');
INSERT INTO `pets_skills` VALUES ('16046', '1', '5753', '1');
INSERT INTO `pets_skills` VALUES ('16046', '1', '5771', '1');
INSERT INTO `pets_skills` VALUES ('16051', '25', '5749', '1');
INSERT INTO `pets_skills` VALUES ('16051', '30', '5749', '2');
INSERT INTO `pets_skills` VALUES ('16051', '35', '5749', '3');
INSERT INTO `pets_skills` VALUES ('16051', '40', '5749', '4');
INSERT INTO `pets_skills` VALUES ('16051', '45', '5749', '5');
INSERT INTO `pets_skills` VALUES ('16051', '50', '5749', '6');
INSERT INTO `pets_skills` VALUES ('16051', '55', '5749', '7');
INSERT INTO `pets_skills` VALUES ('16051', '60', '5749', '8');
INSERT INTO `pets_skills` VALUES ('16051', '65', '5749', '9');
INSERT INTO `pets_skills` VALUES ('16051', '70', '5749', '10');
INSERT INTO `pets_skills` VALUES ('16051', '75', '5749', '11');
INSERT INTO `pets_skills` VALUES ('16051', '80', '5749', '12');
INSERT INTO `pets_skills` VALUES ('16051', '25', '5750', '1');
INSERT INTO `pets_skills` VALUES ('16051', '30', '5750', '2');
INSERT INTO `pets_skills` VALUES ('16051', '35', '5750', '3');
INSERT INTO `pets_skills` VALUES ('16051', '40', '5750', '4');
INSERT INTO `pets_skills` VALUES ('16051', '45', '5750', '5');
INSERT INTO `pets_skills` VALUES ('16051', '50', '5750', '6');
INSERT INTO `pets_skills` VALUES ('16051', '55', '5750', '7');
INSERT INTO `pets_skills` VALUES ('16051', '60', '5750', '8');
INSERT INTO `pets_skills` VALUES ('16051', '65', '5750', '9');
INSERT INTO `pets_skills` VALUES ('16051', '70', '5750', '10');
INSERT INTO `pets_skills` VALUES ('16051', '75', '5750', '11');
INSERT INTO `pets_skills` VALUES ('16051', '80', '5750', '12');
INSERT INTO `pets_skills` VALUES ('16051', '25', '5751', '1');
INSERT INTO `pets_skills` VALUES ('16051', '30', '5751', '2');
INSERT INTO `pets_skills` VALUES ('16051', '35', '5751', '3');
INSERT INTO `pets_skills` VALUES ('16051', '40', '5751', '4');
INSERT INTO `pets_skills` VALUES ('16051', '45', '5751', '5');
INSERT INTO `pets_skills` VALUES ('16051', '50', '5751', '6');
INSERT INTO `pets_skills` VALUES ('16051', '55', '5751', '7');
INSERT INTO `pets_skills` VALUES ('16051', '60', '5751', '8');
INSERT INTO `pets_skills` VALUES ('16051', '65', '5751', '9');
INSERT INTO `pets_skills` VALUES ('16051', '70', '5751', '10');
INSERT INTO `pets_skills` VALUES ('16051', '75', '5751', '11');
INSERT INTO `pets_skills` VALUES ('16051', '80', '5751', '12');
INSERT INTO `pets_skills` VALUES ('16051', '25', '5752', '1');
INSERT INTO `pets_skills` VALUES ('16051', '30', '5752', '2');
INSERT INTO `pets_skills` VALUES ('16051', '35', '5752', '3');
INSERT INTO `pets_skills` VALUES ('16051', '40', '5752', '4');
INSERT INTO `pets_skills` VALUES ('16051', '45', '5752', '5');
INSERT INTO `pets_skills` VALUES ('16051', '50', '5752', '6');
INSERT INTO `pets_skills` VALUES ('16051', '55', '5752', '7');
INSERT INTO `pets_skills` VALUES ('16051', '60', '5752', '8');
INSERT INTO `pets_skills` VALUES ('16051', '65', '5752', '9');
INSERT INTO `pets_skills` VALUES ('16051', '70', '5752', '10');
INSERT INTO `pets_skills` VALUES ('16051', '75', '5752', '11');
INSERT INTO `pets_skills` VALUES ('16051', '80', '5752', '12');
INSERT INTO `pets_skills` VALUES ('16051', '1', '5753', '1');
INSERT INTO `pets_skills` VALUES ('16052', '25', '6049', '1');
INSERT INTO `pets_skills` VALUES ('16052', '30', '6049', '2');
INSERT INTO `pets_skills` VALUES ('16052', '35', '6049', '3');
INSERT INTO `pets_skills` VALUES ('16052', '40', '6049', '4');
INSERT INTO `pets_skills` VALUES ('16053', '55', '5771', '1');
INSERT INTO `pets_skills` VALUES ('16053', '25', '6050', '1');
INSERT INTO `pets_skills` VALUES ('16053', '30', '6050', '2');
INSERT INTO `pets_skills` VALUES ('16053', '35', '6050', '3');
INSERT INTO `pets_skills` VALUES ('16053', '40', '6050', '4');
INSERT INTO `pets_skills` VALUES ('16053', '45', '6050', '5');
INSERT INTO `pets_skills` VALUES ('16053', '50', '6050', '6');
INSERT INTO `pets_skills` VALUES ('16053', '55', '6050', '7');
INSERT INTO `pets_skills` VALUES ('16053', '60', '6050', '8');
INSERT INTO `pets_skills` VALUES ('16053', '65', '6050', '9');
INSERT INTO `pets_skills` VALUES ('16053', '70', '6050', '10');
INSERT INTO `pets_skills` VALUES ('16053', '75', '6050', '11');
INSERT INTO `pets_skills` VALUES ('16053', '80', '6050', '12');
INSERT INTO `pets_skills` VALUES ('16053', '25', '6051', '1');
INSERT INTO `pets_skills` VALUES ('16053', '30', '6051', '2');
INSERT INTO `pets_skills` VALUES ('16053', '35', '6051', '3');
INSERT INTO `pets_skills` VALUES ('16053', '40', '6051', '4');
INSERT INTO `pets_skills` VALUES ('16053', '45', '6051', '5');
INSERT INTO `pets_skills` VALUES ('16053', '50', '6051', '6');
INSERT INTO `pets_skills` VALUES ('16053', '55', '6051', '7');
INSERT INTO `pets_skills` VALUES ('16053', '60', '6051', '8');
INSERT INTO `pets_skills` VALUES ('16053', '65', '6051', '9');
INSERT INTO `pets_skills` VALUES ('16053', '70', '6051', '10');
INSERT INTO `pets_skills` VALUES ('16053', '75', '6051', '11');
INSERT INTO `pets_skills` VALUES ('16053', '80', '6051', '12');
INSERT INTO `pets_skills` VALUES ('16053', '25', '6052', '1');
INSERT INTO `pets_skills` VALUES ('16053', '30', '6052', '2');
INSERT INTO `pets_skills` VALUES ('16053', '35', '6052', '3');
INSERT INTO `pets_skills` VALUES ('16053', '40', '6052', '4');
INSERT INTO `pets_skills` VALUES ('16053', '45', '6052', '5');
INSERT INTO `pets_skills` VALUES ('16053', '50', '6052', '6');
INSERT INTO `pets_skills` VALUES ('16053', '55', '6052', '7');
INSERT INTO `pets_skills` VALUES ('16053', '60', '6052', '8');
INSERT INTO `pets_skills` VALUES ('16053', '65', '6052', '9');
INSERT INTO `pets_skills` VALUES ('16053', '70', '6052', '10');
INSERT INTO `pets_skills` VALUES ('16053', '75', '6052', '11');
INSERT INTO `pets_skills` VALUES ('16053', '80', '6052', '12');
INSERT INTO `pets_skills` VALUES ('16053', '25', '6053', '1');
INSERT INTO `pets_skills` VALUES ('16053', '30', '6053', '2');
INSERT INTO `pets_skills` VALUES ('16053', '35', '6053', '3');
INSERT INTO `pets_skills` VALUES ('16053', '40', '6053', '4');
INSERT INTO `pets_skills` VALUES ('16053', '55', '6054', '1');
INSERT INTO `pets_skills` VALUES ('16067', '25', '6199', '1');
INSERT INTO `pets_skills` VALUES ('16067', '30', '6199', '2');
INSERT INTO `pets_skills` VALUES ('16067', '35', '6199', '3');
INSERT INTO `pets_skills` VALUES ('16067', '40', '6199', '4');
INSERT INTO `pets_skills` VALUES ('16067', '45', '6199', '5');
INSERT INTO `pets_skills` VALUES ('16067', '50', '6199', '6');
INSERT INTO `pets_skills` VALUES ('16067', '55', '6199', '7');
INSERT INTO `pets_skills` VALUES ('16068', '1', '6205', '0');
INSERT INTO `pets_skills` VALUES ('16068', '1', '6206', '0');
INSERT INTO `pets_skills` VALUES ('16068', '1', '6207', '0');

-- ----------------------------
-- Table structure for premium_account_table
-- ----------------------------
DROP TABLE IF EXISTS `premium_account_table`;
CREATE TABLE `premium_account_table` (
  `groupId` int(5) NOT NULL,
  `groupName_ru` varchar(255) NOT NULL,
  `groupName_en` varchar(255) NOT NULL,
  `exp` double NOT NULL DEFAULT '1',
  `sp` double NOT NULL DEFAULT '1',
  `adena` double NOT NULL DEFAULT '1',
  `drop` double NOT NULL DEFAULT '1',
  `spoil` double NOT NULL DEFAULT '1',
  `qdrop` double NOT NULL DEFAULT '1',
  `qreward` double NOT NULL DEFAULT '1',
  `delay` int(10) NOT NULL DEFAULT '1',
  `isHours` int(1) NOT NULL DEFAULT '0',
  `itemId` int(5) NOT NULL DEFAULT '57',
  `itemCount` bigint(10) NOT NULL DEFAULT '1000000',
  `enchant_add` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`groupId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of premium_account_table
-- ----------------------------
INSERT INTO `premium_account_table` VALUES ('1', 'Ð ÐµÐ¹Ñ‚Ñ‹ x2 (1 Ð´ÐµÐ½ÑŒ)', 'Rates x2 (1 day)', '2', '2', '2', '2', '2', '2', '2', '1', '0', '57', '1000', '5');
INSERT INTO `premium_account_table` VALUES ('2', 'Ð ÐµÐ¹Ñ‚Ñ‹ x1.5 (2 Ð´Ð½Ñ)', 'Rates x1.5 (2 day\'s)', '1.5', '1.5', '1.5', '1.5', '1.5', '1.5', '1.5', '2', '0', '57', '50000', '5');
INSERT INTO `premium_account_table` VALUES ('3', 'Ð ÐµÐ¹Ñ‚Ñ‹ x3 (10 Ñ‡Ð°ÑÐ¾Ð²)', 'Rates x3 (10 hour\'s)', '3', '3', '3', '3', '3', '3', '3', '10', '1', '57', '1000000', '5');
INSERT INTO `premium_account_table` VALUES ('4', 'Ð ÐµÐ¹Ñ‚Ñ‹ x5 (1 Ñ‡Ð°Ñ)', 'Rates x5 (1 hour)', '5', '5', '5', '5', '5', '5', '5', '1', '1', '57', '100000000', '5');

-- ----------------------------
-- Table structure for pvp_system_log
-- ----------------------------
DROP TABLE IF EXISTS `pvp_system_log`;
CREATE TABLE `pvp_system_log` (
  `killer` varchar(255) NOT NULL,
  `victim` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pvp_system_log
-- ----------------------------

-- ----------------------------
-- Table structure for raidboss_points
-- ----------------------------
DROP TABLE IF EXISTS `raidboss_points`;
CREATE TABLE `raidboss_points` (
  `owner_id` int(11) NOT NULL,
  `boss_id` smallint(5) unsigned NOT NULL,
  `points` int(11) NOT NULL DEFAULT '0',
  KEY `owner_id` (`owner_id`),
  KEY `boss_id` (`boss_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of raidboss_points
-- ----------------------------

-- ----------------------------
-- Table structure for raidboss_status
-- ----------------------------
DROP TABLE IF EXISTS `raidboss_status`;
CREATE TABLE `raidboss_status` (
  `id` int(11) NOT NULL,
  `current_hp` int(11) DEFAULT NULL,
  `current_mp` int(11) DEFAULT NULL,
  `respawn_delay` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of raidboss_status
-- ----------------------------

-- ----------------------------
-- Table structure for residence_functions
-- ----------------------------
DROP TABLE IF EXISTS `residence_functions`;
CREATE TABLE `residence_functions` (
  `id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `lvl` smallint(5) unsigned NOT NULL DEFAULT '0',
  `lease` int(11) NOT NULL DEFAULT '0',
  `rate` bigint(20) NOT NULL DEFAULT '0',
  `endTime` int(11) NOT NULL DEFAULT '0',
  `inDebt` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of residence_functions
-- ----------------------------

-- ----------------------------
-- Table structure for server_variables
-- ----------------------------
DROP TABLE IF EXISTS `server_variables`;
CREATE TABLE `server_variables` (
  `name` varchar(86) NOT NULL DEFAULT '',
  `value` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of server_variables
-- ----------------------------

-- ----------------------------
-- Table structure for siege_clans
-- ----------------------------
DROP TABLE IF EXISTS `siege_clans`;
CREATE TABLE `siege_clans` (
  `residence_id` int(11) NOT NULL DEFAULT '0',
  `clan_id` int(11) NOT NULL DEFAULT '0',
  `type` varchar(255) NOT NULL,
  `param` bigint(20) NOT NULL,
  `date` bigint(20) NOT NULL,
  PRIMARY KEY (`residence_id`,`clan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of siege_clans
-- ----------------------------

-- ----------------------------
-- Table structure for siege_players
-- ----------------------------
DROP TABLE IF EXISTS `siege_players`;
CREATE TABLE `siege_players` (
  `residence_id` int(11) NOT NULL,
  `object_id` int(11) NOT NULL,
  `clan_id` int(11) NOT NULL,
  PRIMARY KEY (`residence_id`,`object_id`,`clan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of siege_players
-- ----------------------------

-- ----------------------------
-- Table structure for spawns
-- ----------------------------
DROP TABLE IF EXISTS `spawns`;
CREATE TABLE `spawns` (
  `npc_id` smallint(5) unsigned NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `z` int(11) NOT NULL,
  `heading` int(11) NOT NULL,
  `respawn` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`npc_id`,`x`,`y`,`z`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of spawns
-- ----------------------------

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `id` int(10) NOT NULL DEFAULT '0',
  `HWID` varchar(32) NOT NULL DEFAULT '',
  `vote` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`HWID`,`vote`),
  KEY `Index 2` (`id`,`vote`),
  KEY `Index 3` (`id`),
  KEY `Index 4` (`HWID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vote
-- ----------------------------
