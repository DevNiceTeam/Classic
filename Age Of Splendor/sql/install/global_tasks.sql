CREATE TABLE IF NOT EXISTS `global_tasks` (
	`id` int(11) NOT NULL auto_increment,
	`task` VARCHAR(50) NOT NULL DEFAULT '',
	`type` VARCHAR(50) NOT NULL DEFAULT '',
	`last_activation` int(20) NOT NULL DEFAULT '0',
	`param1` VARCHAR(100) NOT NULL DEFAULT '',
	`param2` VARCHAR(100) NOT NULL DEFAULT '',
	`param3` VARCHAR(255) NOT NULL DEFAULT '',
	PRIMARY KEY  (`id`)
) ENGINE=MyISAM;