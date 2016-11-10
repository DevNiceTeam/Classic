#
# Table structure for table 'hwid_bonus'
#
CREATE TABLE `hwid_bonus` (
  `HWID` varchar(32) NOT NULL default '',
  `type` varchar(255) NOT NULL default '',
  `value` int(10) default NULL,
  `time` timestamp NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`HWID`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;