CREATE TABLE IF NOT EXISTS `augment_tmp` (
	`object_id` int(11) NOT NULL,
	`augmentation_high` int(11) NOT NULL,
	`augmentation_low` int(11) NOT NULL,
	PRIMARY KEY  (`object_id`)
) ENGINE=MyISAM;

DELETE FROM `augment_tmp`;

INSERT INTO `augment_tmp`
(object_id, augmentation_high, augmentation_low)
SELECT object_id, (augmentation_id DIV 65536), MOD(augmentation_id, 65536)
FROM items WHERE augmentation_id > 0;

CREATE TABLE IF NOT EXISTS `augment_update` (
	`old` INT NOT NULL,
	`new` INT NOT NULL
) ENGINE=MyISAM;

INSERT INTO `augment_update` VALUES
-- was 3165
(14623, 14584),
(14635, 14584),
(14801, 14762),
(14813, 14762),
(14979, 14940),
(14991, 14940),
(15157, 15118),
(15169, 15118),
(15335, 15296),
(15513, 15474),
(15525, 15474),
(15691, 15652),
(15703, 15652),
(15869, 15830),
(15881, 15830),
(16047, 16008),
(16225, 16186),
-- was 3167
(14627, 14634),
(14805, 14812),
(13983, 14990),
(15161, 15168),
(15339, 15346),
(15517, 15524),
(15695, 15702),
(15873, 15880),
(16051, 16058),
(16229, 16236),
-- was 3168
(14628, 14635),
(14806, 14813),
(14984, 14991),
(15162, 15169),
(15340, 15347),
(15518, 15525),
(15696, 15703),
(15874, 15881),
(16052, 16059),
(16230, 16237),
-- was 3169
(14622, 14582),
(14800, 14760),
(14978, 14938),
(15156, 15116),
(15334, 15294),
(15512, 15472),
(15690, 15650),
(15868, 15828),
(16046, 16006),
(16224, 16184),
-- was 3170
(14624, 14590),
(14802, 14768),
(14980, 14946),
(15158, 15124),
(15336, 15302),
(15514, 15480),
(15692, 15658),
(15870, 15836),
(16048, 16014),
(16226, 16192),
-- was 3171
(14626, 14631),
(14804, 14809),
(14982, 14987),
(15160, 15165),
(15338, 15343),
(15516, 15521),
(15694, 15699),
(15872, 15877),
(16050, 16055),
(16228, 16233),
-- was 3172
(14625, 14603),
(14803, 14781),
(14981, 14959),
(15159, 15137),
(15337, 15315),
(15515, 15493),
(15693, 15671),
(15871, 15849),
(16049, 16027),
(16227, 16205);

UPDATE `augment_tmp`, `augment_update`
SET augment_tmp.augmentation_high = augment_update.new
WHERE augment_tmp.augmentation_high = augment_update.old;

UPDATE `items`, `augment_tmp`
SET items.augmentation_id = (augment_tmp.augmentation_high * 65536 + augment_tmp.augmentation_low)
WHERE items.object_id = augment_tmp.object_id;

DROP TABLE `augment_tmp`;
DROP TABLE `augment_update`;
