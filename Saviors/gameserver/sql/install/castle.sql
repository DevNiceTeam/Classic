CREATE TABLE IF NOT EXISTS `castle` (
  `id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `name` varchar(25) NOT NULL,
  `tax_percent` int(11) NOT NULL,
  `treasury` bigint(20) unsigned NOT NULL DEFAULT '0',
  `town_id` int(11) NOT NULL,
  `last_siege_date` INT UNSIGNED NOT NULL,
  `own_date` INT UNSIGNED NOT NULL,
  `siege_date` INT UNSIGNED NOT NULL,
  `side` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of castle
-- ----------------------------
INSERT INTO castle VALUES ('5', 'Aden', '0', '0', '11', '0', '0', '0', '1');
INSERT INTO castle VALUES ('2', 'Dion', '0', '0', '8', '0', '0', '0', '1');
INSERT INTO castle VALUES ('3', 'Giran', '0', '0', '9', '0', '0', '0', '1');
INSERT INTO castle VALUES ('1', 'Gludio', '0', '0', '6', '0', '0', '0', '1');
INSERT INTO castle VALUES ('7', 'Goddard', '0', '0', '15', '0', '0', '0', '1');
INSERT INTO castle VALUES ('6', 'Innadril', '0', '0', '13', '0', '0', '0', '1');
INSERT INTO castle VALUES ('4', 'Oren', '0', '0', '10', '0', '0', '0', '1');
INSERT INTO castle VALUES ('8', 'Rune', '0', '0', '14', '0', '0', '0', '1');
INSERT INTO castle VALUES ('9', 'Schuttgart', '0', '0', '16', '0', '0', '0', '1');
