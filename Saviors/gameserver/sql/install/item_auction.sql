CREATE TABLE IF NOT EXISTS `item_auction` (
  `auctionId` int(11) NOT NULL,
  `instanceId` int(11) NOT NULL,
  `auctionItemId` int(11) NOT NULL,
  `startingTime` bigint(20) NOT NULL,
  `endingTime` bigint(20) NOT NULL,
  `auctionStateId` tinyint(1) NOT NULL,
  PRIMARY KEY (`auctionId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;