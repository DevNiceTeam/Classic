-- Uncomment following line only if you have single gameserver which is sharing DB with loginserver
-- SELECT * FROM accounts WHERE login NOT IN (SELECT account_name FROM characters);
-- SELECT * FROM characters WHERE account_name NOT IN (SELECT login FROM accounts);
SELECT * FROM character_friends WHERE char_id NOT IN (SELECT obj_Id FROM characters);
SELECT * FROM character_hennas WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
SELECT * FROM character_macroses WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
SELECT * FROM character_quests WHERE char_id NOT IN (SELECT obj_Id FROM characters);
SELECT * FROM character_recipebook WHERE char_id NOT IN (SELECT obj_Id FROM characters);
SELECT * FROM character_shortcuts WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
SELECT * FROM character_skills WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
SELECT * FROM character_skills_save WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
SELECT * FROM character_subclasses WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
SELECT * FROM clan_data WHERE leader_id NOT IN (SELECT obj_Id FROM characters WHERE clanid != 0);
SELECT * FROM ally_data WHERE leader_id NOT IN (SELECT clan_id FROM clan_data);
SELECT * FROM pets WHERE item_obj_id NOT IN (SELECT object_id FROM items);
SELECT * FROM siege_clans WHERE clan_id NOT IN (SELECT clan_id FROM clan_data);
SELECT * FROM items WHERE owner_id NOT IN (SELECT obj_Id FROM characters) AND owner_id NOT IN (SELECT clan_id FROM clan_data) AND owner_id NOT IN (SELECT objId FROM pets) AND owner_id NOT IN (SELECT id FROM npc);
