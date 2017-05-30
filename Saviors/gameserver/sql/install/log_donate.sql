DROP TABLE IF EXISTS `log_donate`;
CREATE TABLE `log_donate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `playerId` int(11) DEFAULT NULL,
  `colCount` bigint(20) DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `itemId` int(11) DEFAULT NULL,
  `count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;