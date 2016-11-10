CREATE TABLE IF NOT EXISTS  `account_bonus` (
  `account` varchar(255) NOT NULL,
  `bonus` double NOT NULL,
  `bonus_expire` int(11) NOT NULL,
  PRIMARY KEY (`account`)
);