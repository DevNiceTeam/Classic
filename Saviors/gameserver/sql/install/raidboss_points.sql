CREATE TABLE IF NOT EXISTS `raidboss_points` (
	`owner_id` INT NOT NULL,
	`boss_id` SMALLINT UNSIGNED NOT NULL,
	`points` INT NOT NULL DEFAULT '0',
	KEY `owner_id` (`owner_id`),
	KEY `boss_id` (`boss_id`)
) ENGINE=MyISAM;