CREATE TABLE IF NOT EXISTS `underground_coliseum_history` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `wins` int(11) NOT NULL,
  PRIMARY KEY (`id`,`name`)
);

