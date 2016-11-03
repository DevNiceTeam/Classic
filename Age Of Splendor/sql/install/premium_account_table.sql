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
INSERT INTO `premium_account_table` VALUES ('1', 'Рейты x2 (1 день)', 'Rates x2 (1 day)', '2', '2', '2', '2', '2', '2', '2', '1', '0', '57', '1000', '5');
INSERT INTO `premium_account_table` VALUES ('2', 'Рейты x1.5 (2 дня)', 'Rates x1.5 (2 day\'s)', '1.5', '1.5', '1.5', '1.5', '1.5', '1.5', '1.5', '2', '0', '57', '50000', '5');
INSERT INTO `premium_account_table` VALUES ('3', 'Рейты x3 (10 часов)', 'Rates x3 (10 hour\'s)', '3', '3', '3', '3', '3', '3', '3', '10', '1', '57', '1000000', '5');
INSERT INTO `premium_account_table` VALUES ('4', 'Рейты x5 (1 час)', 'Rates x5 (1 hour)', '5', '5', '5', '5', '5', '5', '5', '1', '1', '57', '100000000', '5');
