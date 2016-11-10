CREATE TABLE IF NOT EXISTS `epic_boss_spawn` (
	`bossId` SMALLINT UNSIGNED NOT NULL,
	`respawnDate` INT NOT NULL,
	`state` INT NOT NULL,
	PRIMARY KEY  (`bossId`)
) ENGINE=MyISAM;

REPLACE INTO `epic_boss_spawn` (`bossId`, `respawnDate`, `state`) VALUES
(29068,'0',0),
(29020,'0',0),
(29028,'0',0),
(29062,'0',0),
(29065,'0',0),
(29099,'0',0);