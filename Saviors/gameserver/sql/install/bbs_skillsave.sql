DROP TABLE IF EXISTS `bbs_skillsave`;
CREATE TABLE `bbs_skillsave` (
  `charId` int(10) DEFAULT NULL,
  `schameid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT '',
  `skills` varchar(500) DEFAULT '',
  PRIMARY KEY (`schameid`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
