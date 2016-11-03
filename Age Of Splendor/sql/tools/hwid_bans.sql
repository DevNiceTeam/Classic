#
# Table structure for table 'hwid_bans'
#
CREATE TABLE `hwid_bans` (
  `Date` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `HWID` varchar(32) NOT NULL default '',
  `comment` varchar(255) default NULL,
  PRIMARY KEY  (`HWID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;