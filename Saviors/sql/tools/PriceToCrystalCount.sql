UPDATE `weapon` SET `crystal_count` = CEIL(`price`/550) WHERE `crystal_type` = 'd';
UPDATE `weapon` SET `crystal_count` = CEIL(`price`/2500) WHERE `crystal_type` = 'c';
UPDATE `weapon` SET `crystal_count` = CEIL(`price`/7500) WHERE `crystal_type` = 'b';
UPDATE `weapon` SET `crystal_count` = CEIL(`price`/12500) WHERE `crystal_type` = 'a';
UPDATE `weapon` SET `crystal_count` = CEIL(`price`/20000) WHERE `crystal_type` IN ('s', 's80', 's84');

UPDATE `armor` SET `crystal_count` = CEIL(`price`/550) WHERE `crystal_type` = 'd';
UPDATE `armor` SET `crystal_count` = CEIL(`price`/2500) WHERE `crystal_type` = 'c';
UPDATE `armor` SET `crystal_count` = CEIL(`price`/7500) WHERE `crystal_type` = 'b';
UPDATE `armor` SET `crystal_count` = CEIL(`price`/12500) WHERE `crystal_type` = 'a';
UPDATE `armor` SET `crystal_count` = CEIL(`price`/20000) WHERE `crystal_type` IN ('s', 's80', 's84');