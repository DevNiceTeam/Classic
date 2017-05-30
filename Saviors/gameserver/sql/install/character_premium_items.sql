DROP TABLE IF EXISTS `character_premium_items`;
CREATE TABLE `character_premium_items` (
  `char_id` int(11) NOT NULL,
  `pack_id` int(11) NOT NULL,
  `receive_time` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_count` bigint(20) unsigned NOT NULL,
  `sender` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`char_id`,`pack_id`,`receive_time`,`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;