CREATE TABLE IF NOT EXISTS `clanhall` (
  `id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `name` varchar(40) NOT NULL DEFAULT '',
  `last_siege_date` INT UNSIGNED NOT NULL,
  `own_date` INT UNSIGNED NOT NULL,
  `siege_date` INT UNSIGNED NOT NULL,
  `auction_min_bid` bigint(20) NOT NULL,
  `auction_length` int(11) NOT NULL,
  `auction_desc` text,
  `cycle` int(11) NOT NULL,
  `paid_cycle` int(11) NOT NULL,
  PRIMARY KEY (`id`,`name`)
);

-- ----------------------------
-- Records of clanhall
-- ----------------------------
INSERT INTO clanhall VALUES ('21', 'Fortress of Resistance', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('22', 'Moonstone Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('23', 'Onyx Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('24', 'Topaz Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('25', 'Ruby Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('26', 'Crystal Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('27', 'Onyx Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('28', 'Sapphire Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('29', 'Moonstone Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('30', 'Emerald Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('31', 'The Atramental Barracks', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('32', 'The Scarlet Barracks', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('33', 'The Viridian Barracks', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('34', 'Devastated Castle', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('35', 'Bandit Stronghold', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('36', 'The Golden Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('37', 'The Silver Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('38', 'The Mithril Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('39', 'Silver Manor', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('40', 'Gold Manor', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('41', 'The Bronze Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('42', 'The Golden Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('43', 'The Silver Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('44', 'The Mithril Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('45', 'The Bronze Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('46', 'Silver Manor', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('47', 'Moonstone Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('48', 'Onyx Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('49', 'Emerald Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('50', 'Sapphire Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('51', 'Mont Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('52', 'Astaire Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('53', 'Aria Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('54', 'Yiana Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('55', 'Roien Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('56', 'Luna Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('57', 'Traban Chamber', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('58', 'Eisen Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('59', 'Heavy Metal Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('60', 'Molten Ore Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('61', 'Titan Hall', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('62', 'Rainbow Springs', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('63', 'Wild Beast Reserve', '0', '0', '0', '0', '0', null, '0', '0');
INSERT INTO clanhall VALUES ('64', 'Fortress of the Dead', '0', '0', '0', '0', '0', null, '0', '0');
