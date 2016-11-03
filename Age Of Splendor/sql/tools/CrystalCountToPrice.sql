UPDATE `weapon` SET `price` = `crystal_count`*550 WHERE `crystal_type` = 'd';
UPDATE `weapon` SET `price` = `crystal_count`*2500 WHERE `crystal_type` = 'c';
UPDATE `weapon` SET `price` = `crystal_count`*7500 WHERE `crystal_type` = 'b';
UPDATE `weapon` SET `price` = `crystal_count`*12500 WHERE `crystal_type` = 'a';
UPDATE `weapon` SET `price` = `crystal_count`*20000 WHERE `crystal_type` IN ('s', 's80', 's84');

UPDATE `armor` SET `price` = `crystal_count`*550 WHERE `crystal_type` = 'd';
UPDATE `armor` SET `price` = `crystal_count`*2500 WHERE `crystal_type` = 'c';
UPDATE `armor` SET `price` = `crystal_count`*7500 WHERE `crystal_type` = 'b';
UPDATE `armor` SET `price` = `crystal_count`*12500 WHERE `crystal_type` = 'a';
UPDATE `armor` SET `price` = `crystal_count`*20000 WHERE `crystal_type` = 's';
UPDATE `armor` SET `price` = `crystal_count`*20000 WHERE `crystal_type` IN ('s', 's80', 's84');