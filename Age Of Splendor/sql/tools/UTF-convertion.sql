-- Base
ALTER DATABASE `l2pdb` DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
-- tables
ALTER TABLE `character_macroses` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `character_variables` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `characters` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `clan_data` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `ally_data` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `pets` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `server_variables` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `game_log` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `petitions` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `forums` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `posts` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
ALTER TABLE `topic` DEFAULT CHARACTER SET utf8, DEFAULT COLLATE utf8_general_ci;
-- Colums
ALTER TABLE `character_macroses` CHANGE `name` `name` VARCHAR( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,CHANGE `descr` `descr` VARCHAR( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL;
ALTER TABLE `character_variables` CHANGE `type` `type` VARCHAR( 86 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',CHANGE `name` `name` VARCHAR( 86 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',CHANGE `value` `value` VARCHAR( 255 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0';
ALTER TABLE `characters` CHANGE `account_name` `account_name` VARCHAR( 45 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,CHANGE `char_name` `char_name` VARCHAR( 35 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,CHANGE `title` `title` VARCHAR( 16 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL;
ALTER TABLE `clan_data` CHANGE `clan_name` `clan_name` VARCHAR( 45 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL;
ALTER TABLE `forums` CHANGE `forum_name` `forum_name` VARCHAR( 255 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL;
ALTER TABLE `game_log` CHANGE `actor_type` `actor_type` VARCHAR( 75 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,CHANGE `target_type` `target_type` VARCHAR( 75 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',CHANGE `etc_str1` `etc_str1` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,CHANGE `etc_str2` `etc_str2` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,CHANGE `etc_str3` `etc_str3` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,CHANGE `STR_actor` `STR_actor` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,CHANGE `STR_actor_account` `STR_actor_account` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,CHANGE `STR_target` `STR_target` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,CHANGE `STR_target_account` `STR_target_account` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL;
ALTER TABLE `petitions` CHANGE `petition_text` `petition_text` TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,CHANGE `STR_actor` `STR_actor` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,CHANGE `STR_actor_account` `STR_actor_account` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL;
ALTER TABLE `pets` CHANGE `name` `name` VARCHAR( 12 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL;
ALTER TABLE `posts` CHANGE `post_owner_name` `post_owner_name` VARCHAR( 255 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,CHANGE `post_txt` `post_txt` TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL;
ALTER TABLE `topic` CHANGE `topic_name` `topic_name` VARCHAR( 255 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,CHANGE `topic_ownername` `topic_ownername` VARCHAR( 255 ) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0';
