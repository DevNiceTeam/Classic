DROP TABLE IF EXISTS `log_lucky_game`;
CREATE TABLE `log_lucky_game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `playerId` int(11) DEFAULT NULL,
  `game` int(11) DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `itemId` int(11) DEFAULT NULL,
  `count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;