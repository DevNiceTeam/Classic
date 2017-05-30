CREATE TABLE IF NOT EXISTS `fish` (
  `id` int(5) NOT NULL DEFAULT '0',
  `level` int(5) NOT NULL DEFAULT '0',
  `name` varchar(40) NOT NULL DEFAULT '',
  `hp` int(5) NOT NULL DEFAULT '0',
  `hpregen` int(3) NOT NULL DEFAULT '5',
  `fish_type` int(1) NOT NULL DEFAULT '0',
  `fish_group` int(1) NOT NULL DEFAULT '0',
  `fish_guts` int(4) NOT NULL DEFAULT '0',
  `guts_check_time` int(4) NOT NULL DEFAULT '0',
  `wait_time` int(5) NOT NULL DEFAULT '0',
  `combat_time` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`level`)
);

-- ----------------------------
-- Records for fish
-- ----------------------------
INSERT INTO `fish` VALUES ('45473', '1', 'Fresh Blue Mackerel', '100', '4', '1', '1', '500', '5000', '20000', '24000');
INSERT INTO `fish` VALUES ('45474', '2', 'Fresh Minnow', '150', '6', '1', '1', '506', '5000', '20000', '25000');
INSERT INTO `fish` VALUES ('45475', '3', 'Fresh Flatfish', '252', '8', '0', '1', '516', '5000', '20000', '32000');
INSERT INTO `fish` VALUES ('45476', '4', 'Fresh Mandarin Fish', '310', '10', '0', '1', '522', '5000', '20000', '32000');
INSERT INTO `fish` VALUES ('45477', '5', 'Fresh Rockfish ', '340', '11', '0', '1', '528', '4000', '20000', '32000');
INSERT INTO `fish` VALUES ('45478', '6', 'Fresh Goldfish', '370', '12', '0', '1', '534', '4000', '20000', '32000');
INSERT INTO `fish` VALUES ('45479', '7', 'Fresh Salmon ', '400', '13', '0', '1', '540', '4000', '20000', '32000');
INSERT INTO `fish` VALUES ('45480', '8', 'Fresh Eel ', '529', '17', '0', '1', '552', '4000', '20000', '31000');
INSERT INTO `fish` VALUES ('45481', '9', 'Fresh Marlin', '564', '18', '0', '1', '558', '4000', '20000', '31000');
INSERT INTO `fish` VALUES ('45482', '10', 'Fresh Catfish', '575', '19', '1', '1', '560', '4000', '20000', '30000');
INSERT INTO `fish` VALUES ('45483', '11', 'Fresh Tuna', '598', '19', '0', '1', '564', '4000', '20000', '31000');
INSERT INTO `fish` VALUES ('45484', '12', 'Fresh Carp', '633', '20', '0', '1', '570', '4000', '20000', '31000');
INSERT INTO `fish` VALUES ('45485', '13', 'Fresh Whale', '667', '21', '0', '1', '576', '3000', '20000', '32000');
INSERT INTO `fish` VALUES ('45486', '8', 'Wooden Treasure Chest', '529', '17', '0', '1', '552', '4000', '20000', '31000');
INSERT INTO `fish` VALUES ('45487', '9', 'Silver Treasure Chest', '564', '18', '0', '1', '558', '4000', '20000', '31000');
INSERT INTO `fish` VALUES ('45488', '13', 'Golden Treasure Chest', '667', '21', '0', '1', '576', '3000', '20000', '32000');
