CREATE TABLE IF NOT EXISTS `fortress` (
  `id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL,
  `state` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `castle_id` int(11) NOT NULL,
  `last_siege_date` bigint(20) NOT NULL,
  `own_date` bigint(20) NOT NULL,
  `siege_date` bigint(20) NOT NULL,
  `supply_count` bigint(20) NOT NULL,
  `facility_0` int(20) NOT NULL,
  `facility_1` int(11) NOT NULL,
  `facility_2` int(11) NOT NULL,
  `facility_3` int(11) NOT NULL,
  `facility_4` int(11) NOT NULL,
  `cycle` int(11) NOT NULL,
  `reward_count` int(11) NOT NULL,
  `paid_cycle` int(11) NOT NULL,
  `supply_spawn` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of fortress
-- ----------------------------
INSERT INTO fortress VALUES ('101', 'Shanty Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('102', 'Southern Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('103', 'Hive Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('104', 'Valley Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('105', 'Ivory Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('106', 'Narsell Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('107', 'Bayou Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('108', 'White Sands Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('109', 'Borderland Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('110', 'Swamp Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('111', 'Archaic Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('112', 'Floran Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('113', 'Cloud Mountain Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('114', 'Tanor Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('115', 'Dragonspine Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('116', 'Antharas Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('117', 'Western Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('118', 'Hunters Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('119', 'Aaru Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('120', 'Demon Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
INSERT INTO fortress VALUES ('121', 'Monastic Fortress', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0','0');
