UPDATE character_effects_save SET effect_cur_time = effect_cur_time / 1000;
ALTER TABLE character_effects_save CHANGE `effect_cur_time` `usage_time` INT NOT NULL;
ALTER TABLE character_effects_save DROP COLUMN effect_count;
ALTER TABLE character_effects_save DROP COLUMN duration;