-- Uncomment following line only if you have single gameserver which is sharing DB with loginserver
-- DELETE FROM accounts WHERE login NOT IN (SELECT account_name FROM characters);
-- DELETE FROM characters WHERE account_name NOT IN (SELECT login FROM accounts);
-- DELETE FROM characters WHERE account_name IN (SELECT login FROM accounts where access_level < 0);
DELETE FROM character_friends WHERE char_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM character_hennas WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM character_macroses WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM character_quests WHERE char_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM character_recipebook WHERE char_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM character_shortcuts WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM character_skills WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM character_skills_save WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM character_subclasses WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM character_variables WHERE obj_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM clan_data WHERE leader_id NOT IN (SELECT obj_Id FROM characters WHERE clanid != 0);
DELETE FROM ally_data WHERE leader_id NOT IN (SELECT clan_id FROM clan_data);
DELETE FROM pets WHERE item_obj_id NOT IN (SELECT object_id FROM items);
DELETE FROM siege_clans WHERE clan_id NOT IN (SELECT clan_id FROM clan_data);

DELETE FROM items WHERE owner_id NOT IN (SELECT obj_Id FROM characters) AND owner_id NOT IN (SELECT clan_id FROM clan_data) AND owner_id NOT IN (SELECT objId FROM pets);

UPDATE characters SET clanid=0 WHERE clanid NOT IN (SELECT clan_id FROM clan_data);
UPDATE clan_data SET ally_id=0 WHERE ally_id NOT IN (SELECT ally_id FROM ally_data);

DELETE FROM heroes WHERE char_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM olympiad_nobles WHERE char_id NOT IN (SELECT obj_Id FROM characters);
DELETE FROM seven_signs WHERE char_obj_id NOT IN (SELECT obj_Id FROM characters);