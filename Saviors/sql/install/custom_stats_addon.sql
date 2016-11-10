/*
MySQL Data Transfer
Source Host: localhost
Source Database: tauti
Target Host: localhost
Target Database: tauti
Date: 14.05.2013 3:44:02
*/

SET FOREIGN_KEY_CHECKS=0;
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
-- Records 
-- ----------------------------
INSERT INTO `custom_stats_addon` VALUES ('80', '2', '2.5', '2.1', '2.1', '2.5', '2', '2', '2', '2', '2', '2', '2', '2', '400', '410', '400', '400', '1');
