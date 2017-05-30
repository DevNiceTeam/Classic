package services.community.custom;

import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.dao.CommunityBoardBufferDAO;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.data.xml.holder.SkillHolder;
import studio.lineage2.gameserver.model.*;
import studio.lineage2.gameserver.model.actor.instances.creature.Effect;
import studio.lineage2.gameserver.model.base.TeamType;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.skills.EffectUseType;
import studio.lineage2.gameserver.stats.Env;
import studio.lineage2.gameserver.templates.skill.EffectTemplate;
import studio.lineage2.gameserver.model.ManageBbsBuffer.SBufferScheme;

import services.community.ScriptsCommunityHandler;

import java.util.List;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Modefied by Averen on 18.03.2017.
 */
public class CommunityBuffer extends ScriptsCommunityHandler
{
    private static final Logger _log = LoggerFactory.getLogger(CommunityBuffer.class);
    private int time = Config.COMMUNITYBOARD_BUFF_TIME;
    private int time_dance_song = Config.COMMUNITYBOARD_BUFF_SONGDANCE_TIME;

    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_ENABLED)
        {
            super.onInit();
            _log.info("CommunityBoard: Buffer Community service loaded.");
            CommunityBoardBufferDAO.getInstance().select();
        }
    }

    @Override
    public String[] getBypassCommands()
    {
        return new String[]
        {
            "_bbsbuff",
            "_bbsbaim",
            "_bbsbsingle",
            "_bbsbsave",
            "_bbsbrestore",
            "_bbsbdelete",
            "_bbsbregen",
            "_bbsbcansel",
            "_bbsblist"
        };
    }

    @Override
    public void onBypassCommand(Player player, String bypass)
    {

        if(!CheckCondition(player))
        {
            return;
        }


        if(bypass.startsWith("_bbsbuff"))
        {
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");
            ShowHtml(mBypass.length == 1 ? "index" : mBypass[1], player);
        }
        if(bypass.startsWith("_bbsblist"))
        {
            if(!CheckCondition(player))
            {
                return;
            }

            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");
            int price;
            if (Config.COMMUNITYBOARD_BOARD_ALT_ENABLED)
            {
                price = getAltPrice(player);
            }
            else
                price = Config.COMMUNITYBOARD_BUFF_PICE * (mBypass[1].startsWith("mage") ? Config.COMMUNITI_LIST_MAGE_SUPPORT.size() : Config.COMMUNITI_LIST_FIGHTER_SUPPORT.size());

            if(player.getAdena() < price)
            {
                if (player.isLangRus())
                {
                    player.sendMessage("Недостаточно сердств!");
                }
                else
                {
                    player.sendMessage("It is not enough money!");
                }
                ShowHtml(mBypass[2], player);
                return;
            }

            GroupBuff(player, mBypass[1].startsWith("mage") ? Config.COMMUNITI_LIST_MAGE_SUPPORT : Config.COMMUNITI_LIST_FIGHTER_SUPPORT);
            player.reduceAdena(price, true);
            ShowHtml(mBypass[2], player);
        }
        else if(bypass.startsWith("_bbsbsingle"))
        {
            if(!CheckCondition(player))
            {
                return;
            }


            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");

            Servitor pet = player.getPet();
            int id = Integer.parseInt(mBypass[1]);
            int lvl = Integer.parseInt(mBypass[2]);
            int price;
            if (Config.COMMUNITYBOARD_BOARD_ALT_ENABLED)
            {
                price = getAltPrice(player);
            }
            else
            {
                price = Config.COMMUNITYBOARD_BUFF_PICE;
            }


            String page = mBypass[3];

            if(player.getAdena() < price)
            {
                if (player.isLangRus())
                {
                    player.sendMessage("Недостаточно сердств!");
                }
                else
                {
                    player.sendMessage("It is not enough money!");
                }
                ShowHtml(page, player);
                return;
            }

            if(!Config.COMMUNITYBOARD_BUFF_ALLOW.contains(id))
            {
                if (player.isLangRus())
                {
                    player.sendMessage("Недопустимый эффект!");
                }
                else
                    {
                    player.sendMessage("Invalid effect!");
                }
                ShowHtml(page, player);
                return;
            }

            Skill skill = SkillHolder.getInstance().getSkill(id, lvl);

            if(skill == null)
            {
                _log.error("Skill no exist: " + id);
            }

            setTime(skill);
            if(!player.getVarBoolean("isPlayerBuff") && pet != null)
            {
                if(id == 1413 && pet.getEffectList().getEffectsCount(1363) <= 0)
                {
                    pet.getEffectList().stopEffects(1363);
                }

                if(skill != null)
                {
                    for (EffectTemplate et : skill.getEffectTemplates(EffectUseType.NORMAL))
                    {
                        Env env = new Env(pet, pet, skill);
                        Effect effect = et.getEffect(env);
                        if (effect != null)
                        {
                            effect.setDuration(time);
                            pet.getEffectList().addEffect(effect);
                            pet.updateStats();
                            pet.sendChanges();
                            pet.updateEffectIcons();
                        }
                    }
                }
            }
            else
            {
                if(id == 1413 && player.getEffectList().getEffectsCount(1363) <= 0)
                {
                    player.getEffectList().stopEffects(1363);
                }

                if(skill != null)
                {

                    for (EffectTemplate et : skill.getEffectTemplates(EffectUseType.NORMAL))
                    {
                        Env env = new Env(player, player, skill);
                        Effect effect = et.getEffect(env);
                        if (effect != null)
                        {
                            effect.setDuration(time);
                            player.getEffectList().addEffect(effect);
                            player.sendChanges();
                            player.updateEffectIcons();
                            player.updateEffectIconsImpl();
                        }
                    }
                }
            }
            player.reduceAdena(price, true);
            ShowHtml(page, player);
        }
        else if(bypass.startsWith("_bbsbaim"))
        {
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");

            player.setVar("isPlayerBuff", player.getVarBoolean("isPlayerBuff") ? "0" : "1", -1);

            ShowHtml(mBypass[1], player);
        }
        else if(bypass.startsWith("_bbsbregen"))
        {
            if(CheckCondition(player)) {

                StringTokenizer st2 = new StringTokenizer(bypass, ";");
                String[] mBypass = st2.nextToken().split(":");

                int price;
                if (Config.COMMUNITYBOARD_BOARD_ALT_ENABLED)
                {
                    price = getAltPrice(player);
                }
                else
                {
                    price = Config.COMMUNITYBOARD_BUFF_PICE;
                }

                if (player.getAdena() < price * 10)
                {
                    if (player.isLangRus())
                    {
                        player.sendMessage("Недостаточно сердств!");
                    }
                    else
                    {
                        player.sendMessage("It is not enough money!");
                    }
                    ShowHtml(mBypass[1], player);
                    return;
                }

                if (!player.getVarBoolean("isPlayerBuff") && player.getPet() != null)
                {
                    player.getPet().setCurrentHpMp(player.getPet().getMaxHp(), player.getPet().getMaxMp());
                    player.getPet().setCurrentCp(player.getPet().getMaxCp());
                }
                else
                {
                    player.setCurrentHpMp(player.getMaxHp(), player.getMaxMp());
                    player.setCurrentCp(player.getMaxCp());
                }
                player.reduceAdena(price * 10, true);
                ShowHtml(mBypass[1], player);
            }
        }
        else if(bypass.startsWith("_bbsbcansel"))
        {
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");
            if(!player.getVarBoolean("isPlayerBuff") && player.getPet() != null)
            {
                if(player.getEffectList().getEffectsCount(Skill.SKILL_RAID_CURSE) <= 0)
                {
                    player.getPet().getEffectList().stopAllEffects();
                }
            }
            else
            {
                if (player.getEffectList().getEffectsCount(Skill.SKILL_RAID_CURSE) <= 0)
                {
                    player.getEffectList().stopAllEffects();
                }

                ShowHtml(mBypass[1], player);
            }
        }
        else if(bypass.startsWith("_bbsbsave"))
        {
            if(CheckCondition(player))
            {
                StringTokenizer st2 = new StringTokenizer(bypass, ";");
                String[] mBypass = st2.nextToken().split(":");
                try
                {
                    String name = mBypass[2].substring(1);

                    SBufferScheme scheme = new SBufferScheme();
                    if (ManageBbsBuffer.getCountOnePlayer(player.getObjectId()) >= 3)
                    {
                        if (player.isLangRus())
                        {
                            player.sendMessage("Превышено максимально допустимое количество схем!");
                        }
                        else
                        {
                            player.sendMessage("Exceeded the number of schemes!");
                        }

                        ShowHtml(mBypass[1], player);
                        return;
                    }
                    if (ManageBbsBuffer.existName(player.getObjectId(), name))
                    {
                        if (player.isLangRus())
                        {
                            player.sendMessage("Схема с таким названием уже существует!");
                        }
                        else
                        {
                            player.sendMessage("Scheme with that name already exists!");
                        }

                        ShowHtml(mBypass[1], player);
                        return;
                    }

                    if (name.length() > 15) {
                        name = name.substring(0, 15);
                    }

                    if (name.length() > 0) {
                        scheme.obj_id = player.getObjectId();
                        scheme.name = name;

                        Effect skill[] = player.getEffectList().getFirstEffects();
                        for (Effect effect : skill) {
                            if (Config.COMMUNITYBOARD_BUFF_ALLOW.contains(effect.getSkill().getId())) {
                                scheme.skills_id.add(effect.getSkill().getId());
                            }
                        }
                        CommunityBoardBufferDAO.getInstance().insert(scheme, player);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(player.isLangRus() ? "Вы не ввели имя для сохранения!" : "You did not enter a name to save!");
                    return;
                }

                ShowHtml(mBypass[1], player);
            }
        }
        else if(bypass.startsWith("_bbsbdelete")){
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");

            CommunityBoardBufferDAO.getInstance().delete(ManageBbsBuffer.getScheme(Integer.parseInt(mBypass[1]), player.getObjectId()));

            ShowHtml(mBypass[3], player);
        }
        else if(bypass.startsWith("_bbsbrestore")){
            if(!CheckCondition(player))
                return;
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");

            int price;
            if (Config.COMMUNITYBOARD_BOARD_ALT_ENABLED)
            {
                price = getAltPrice(player);
            }
            else
                price = Config.COMMUNITYBOARD_BUFF_SAVE_PICE;

            if(player.getAdena() < price)
            {
                if (player.isLangRus())
                    player.sendMessage("Недостаточно сердств!");
                else
                    player.sendMessage("It is not enough money!");
                ShowHtml(mBypass[3], player);
                return;
            }

            SBufferScheme scheme = ManageBbsBuffer.getScheme(Integer.parseInt(mBypass[1]), player.getObjectId());

            if(scheme == null)
                return;

            GroupBuff(player, scheme.skills_id);
            player.reduceAdena(price, true);
            ShowHtml(mBypass[3], player);
        }
    }


    @Override
    public void onWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5){}

    private void ShowHtml(String name, Player player)
    {

        String html = HtmCache.getInstance().getIfExists(Config.BBS_HOME_DIR + "pages/buffer/" + name + ".htm", player);
        if (player.isLangRus())
            html = html.replaceFirst("%aim%", player.getVarBoolean("isPlayerBuff") ? "Персонаж" : "Питомец");
        else
            html = html.replaceFirst("%aim%", player.getVarBoolean("isPlayerBuff") ? "Character" : "Pet");

        if (Config.COMMUNITYBOARD_BOARD_ALT_ENABLED)
        {
            if (player.getLevel() < 20)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_NG));
            else if (player.getLevel() >= 20 && player.getLevel() <40)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_D));
            else if (player.getLevel() >= 40 && player.getLevel() <52)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_C));
            else if (player.getLevel() >= 52 && player.getLevel() <61)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_B));
            else if (player.getLevel() >= 61 && player.getLevel() <76)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_A));
            else if (player.getLevel() >= 76 && player.getLevel() <80)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_S));
            else if (player.getLevel() >= 80 && player.getLevel() <84)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_S80));
            else
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_S84));
        }
        else
            html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE));

        if (Config.COMMUNITYBOARD_BOARD_ALT_ENABLED)
        {
            if (player.getLevel() < 20)
                html = html.replace("%group_pice%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_NG_GR));
            else if (player.getLevel() >= 20 && player.getLevel() <40)
                html = html.replace("%group_pice%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_D_GR));
            else if (player.getLevel() >= 40 && player.getLevel() <52)
                html = html.replace("%group_pice%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_C_GR));
            else if (player.getLevel() >= 52 && player.getLevel() <61)
                html = html.replace("%group_pice%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_B_GR));
            else if (player.getLevel() >= 61 && player.getLevel() <76)
                html = html.replace("%group_pice%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_A_GR));
            else if (player.getLevel() >= 76 && player.getLevel() <80)
                html = html.replace("%group_pice%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_S_GR));
            else if (player.getLevel() >= 80 && player.getLevel() <84)
                html = html.replace("%group_pice%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_S80_GR));
            else
                html = html.replace("%group_pice%", GetStringCount(Config.COMMUNITYBOARD_BUFF_PICE_S84_GR));
        }
        else
            html = html.replace("%group_pice%", GetStringCount(Config.COMMUNITYBOARD_BUFF_SAVE_PICE));

        StringBuilder content = new StringBuilder("");
        content.append("<table width=120>");
        for(SBufferScheme sm : ManageBbsBuffer.getSchemePlayer(player.getObjectId()))
        {
            content.append("<tr>");
            content.append("<td>");
            content.append("<button value=\"").append(sm.name).append("\" action=\"bypass _bbsbrestore:").append(sm.id).append(":").append(sm.name).append(":").append(name).append(";\" width=105 height=20 back=\"L2UI_ct1.Button_DF_Down\" fore=\"L2UI_ct1.Button_DF\">");
            content.append("</td>");
            content.append("<td>");
            content.append("<button value=\"-\" action=\"bypass _bbsbdelete:").append(sm.id).append(":").append(sm.name).append(":").append(name).append(";\" width=20 height=20 back=\"L2UI_ct1.Button_DF_Down\" fore=\"L2UI_ct1.Button_DF\">");
            content.append("</td>");
            content.append("</tr>");
        }
        content.append("</table>");

        html = html.replace("%list_sheme%",  content.toString());
        ShowBoardPacket.separateAndSend(html, player);
    }

    private void GroupBuff(Player player, List<Integer> list)
    {
        if(CheckCondition(player))
        {
            int time = Config.COMMUNITYBOARD_BUFF_TIME;
            Servitor pet = player.getPet();
            Skill skill;

            for (int i : list)
            {
                skill = SkillHolder.getInstance().getSkill(i, 1);

                if (skill == null)
                    return;

                int lvl = skill.getMaxLevel();

                skill = SkillHolder.getInstance().getSkill(i, lvl);

                if (!Config.COMMUNITYBOARD_BUFF_ALLOW.contains(i))
                    continue;


                if (!player.getVarBoolean("isPlayerBuff") && pet != null)
                {
                    if (i == 1413 && pet.getEffectList().getEffectsCount(1363) <= 0)
                        pet.getEffectList().stopEffects(1363);

                    if (skill == null)
                        return;

                    for (EffectTemplate et : skill.getEffectTemplates(EffectUseType.NORMAL))
                    {
                        Env env = new Env(pet, pet, skill);
                        Effect effect = et.getEffect(env);

                        if (effect == null)
                            return;

                        if (skill.isMusic())
                            effect.setDuration(time_dance_song);
                        else
                            effect.setDuration(time);
                        pet.getEffectList().addEffect(effect);
                        pet.updateEffectIcons();
                    }
                }
                else
                {
                    if (pet != null && i == 1413 && pet.getEffectList().getEffectsCount(1363) <= 0)
                    {
                        player.getEffectList().stopEffects(1363);
                    }

                    if(skill != null)
                    {
                        for (EffectTemplate et : skill.getEffectTemplates(EffectUseType.NORMAL))
                        {
                            Env env = new Env(player, player, skill);
                            Effect effect = et.getEffect(env);

                            if (effect == null)
                                return;

                            if (skill.isMusic())
                                effect.setDuration(time_dance_song);
                            else
                                effect.setDuration(time);
                            player.getEffectList().addEffect(effect);
                            player.sendChanges();
                            player.updateEffectIcons();
                        }
                    }
                }
            }
        }
    }

    private int getAltPrice(Player player)
    {
        int price;
        if(player.getLevel() < 20)
            price = Config.COMMUNITYBOARD_BUFF_PICE_NG_GR;
        else if (player.getLevel() >= 20 && player.getLevel() <40)
            price = Config.COMMUNITYBOARD_BUFF_PICE_D_GR;
        else if (player.getLevel() >= 40 && player.getLevel() <52)
            price = Config.COMMUNITYBOARD_BUFF_PICE_C_GR;
        else if (player.getLevel() >= 52 && player.getLevel() <61)
            price = Config.COMMUNITYBOARD_BUFF_PICE_B_GR;
        else if (player.getLevel() >= 61 && player.getLevel() <76)
            price = Config.COMMUNITYBOARD_BUFF_PICE_A_GR;
        else if (player.getLevel() >= 76 && player.getLevel() <80)
            price = Config.COMMUNITYBOARD_BUFF_PICE_S_GR;
        else if (player.getLevel() >= 80 && player.getLevel() <84)
            price = Config.COMMUNITYBOARD_BUFF_PICE_S80_GR;
        else
            price = Config.COMMUNITYBOARD_BUFF_PICE_S84_GR;
        return price;
    }

    private boolean CheckCondition(Player player)
    {
        if(player == null)
            return false;

        if(player.isDead())
            return false;

        if(Config.COMMUNITYBOARD_BUFFER_MAX_LVL_ALLOW && player.getLevel() > Config.COMMUNITYBOARD_BUFFER_MAX_LVL)
        {
            player.sendMessage(player.isLangRus() ? "Вам запрещено пользоваться этой функцией." : "You are not allowed to use this feature.");
            return false;
        }

        if(!Config.USE_BBS_BUFER_IS_CURSE_WEAPON && player.isCursedWeaponEquipped())
        {
            player.sendMessage(player.isLangRus() ? "Вам запрещено пользоваться этой функцией." : "You are not allowed to use this feature.");
            return false;
        }

        if (!Config.COMMUNITYBOARD_BUFFER_ENABLED)
        {
            if(player.isLangRus())
                player.sendMessage("Функция баффа отключена.");
            else
                player.sendMessage("Buff off function.");
            return false;
        }

        if(!Config.USE_BBS_BUFER_IS_COMBAT && (player.isInDuel() || player.isInCombat() || player.isAttackingNow()))
        {
            if(player.isLangRus())
                player.sendMessage("Во время боя нельзя использовать данную функцию.");
            else
                player.sendMessage("During combat, you can not use this feature.");
            return false;
        }

        if (player.isInOlympiadMode())
        {
            if(player.isLangRus())
                player.sendMessage("Во время Олимпиады нельзя использовать данную функцию.");
            else
                player.sendMessage("During the Olympics you can not use this feature.");
            return false;
        }

        if (player.getReflection().getId() != 0 && !Config.COMMUNITYBOARD_INSTANCE_ENABLED)
        {
            if(player.isLangRus())
                player.sendMessage("Бафф доступен только в обычном мире.");
            else
                player.sendMessage("Buff is only available in the real world.");
            return false;
        }

        if (!Config.COMMUNITYBOARD_EVENTS_ENABLED)
        {
            if(player.getTeam() != TeamType.NONE)
            {
                if (player.isLangRus())
                    player.sendMessage("Нельзя использовать бафф во время эвентов.");
                else
                    player.sendMessage("You can not use the buff during Events.");
                return false;
            }
        }

        if(!Config.COMMUNITYBOARD_BUFFER_SIEGE_ENABLED && player.isInZone(Zone.ZoneType.SIEGE))
        {
            if(player.isLangRus())
                player.sendMessage("В зоне, находящейся в осаде, использовать телепорт запрещено.");
            else
                player.sendMessage("In the zone, located in the siege, use the teleport is prohibited.");
            return false;
        }

        if(!Config.COMMUNITYBOARD_BUFFER_NO_IS_IN_PEACE_ENABLED && !player.isInPeaceZone())
        {
            player.sendMessage(player.isLangRus() ? "Эта функция доступна только в мирной зоне!" : "This feature is only available in a peaceful area!");
            return false;}

        return true;
    }

    private void setTime(Skill skill)
    {

        if(skill.getId() == 1356 || skill.getId() == 4699 || skill.getId() == 4700 ||
                skill.getId() == 4702 || skill.getId() == 4703 || skill.getId() == 1363 ||
                skill.getId() == 1355 || skill.getId() == 1357)
            time = Config.COMMUNITYBOARD_BUFF_PETS_TIME;
        else if(skill.getId() >= 1499 && skill.getId() <= 1504)
            time = Config.COMMUNITYBOARD_BUFF_COMBO_TIME;
        else if(skill.isMusic())
            time = Config.COMMUNITYBOARD_BUFF_SONGDANCE_TIME;
        else
            time = Config.COMMUNITYBOARD_BUFF_TIME;
    }
}
