CREATE TABLE IF NOT EXISTS `fake_players` (
	`id` mediumint unsigned NOT NULL DEFAULT '0',
	`x` mediumint DEFAULT NULL,
	`y` mediumint DEFAULT NULL,
	`z` mediumint DEFAULT NULL,
	`path_id` tinyint(3) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`)
);
