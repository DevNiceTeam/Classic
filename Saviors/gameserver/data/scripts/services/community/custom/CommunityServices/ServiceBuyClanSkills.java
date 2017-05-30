package services.community.custom.CommunityServices;

import services.community.custom.CommunityServices.handler.CommunityServicesHandler;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.data.xml.holder.SkillAcquireHolder;
import studio.lineage2.gameserver.data.xml.holder.SkillHolder;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.Skill;
import studio.lineage2.gameserver.model.SkillLearn;
import studio.lineage2.gameserver.model.base.AcquireType;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.utils.ItemFunctions;

import java.util.Collection;

/**
 * Created by Averen on 26.03.2017.
 */
public class ServiceBuyClanSkills extends CommunityServicesHandler
{
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_SKILLS_ENABLED)
        {
            super.onInit();
        }
    }

    @Override
    public String[] getServicesCommands()
    {
        return new String[] { "serviceBuyClanSkills", "buyClanSkills" };
    }

    @Override
    public void onServiceCommand(String bypass, Player player)
    {
        if(bypass.startsWith("serviceBuyClanSkills"))
        {
            String[] mBypass = bypass.split(" ");
            ShowPage(mBypass[1], player, "");
        }
        else if(bypass.startsWith("buyClanSkills"))
        {
            String[] mBypass = bypass.split(" ");

            if(player.getClan().getLeader().getPlayer() != player)
            {
                ShowPage(mBypass[1], player, "Вы не являетесь лидером клана.");
                return;
            }

            if(player.getClan() != null)
            {
                final Collection<SkillLearn> skills = SkillAcquireHolder.getInstance().getAvailableSkills(player, AcquireType.CLAN);
                if(skills.size() > 0)
                {
                    if(ItemFunctions.deleteItem(player, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_SKILLS_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_SKILLS_PRICE * skills.size()))
                    {
                        for(SkillLearn s : skills)
                        {
                            Skill skill = SkillHolder.getInstance().getSkill(s.getId(), 1);

                            assert skill != null;
                            skill = SkillHolder.getInstance().getSkill(s.getId(), skill.getMaxLevel());

                            assert skill != null;
                            player.getClan().addSkill(skill.getEntry(), true);
                        }
                        ShowPage(mBypass[1], player, "Вы получили все доступные умения.");
                    }
                    else
                    {
                        ShowPage(mBypass[1], player, "У вас не достаточно средств.");
                    }
                }
                else
                {
                    ShowPage(mBypass[1], player, "Нет доступных умений.");
                }
            }
            else
            {
                ShowPage(mBypass[1], player, "Вы не состоите в клане.");
            }
        }
    }

    private void ShowPage(String page, Player player, String massage)
    {
        String skillsCount;
        String price;

        if(player.getClan() != null)
        {
            final Collection<SkillLearn> skills = SkillAcquireHolder.getInstance().getAvailableSkills(player, AcquireType.CLAN);
            if(skills.size() > 0)
            {
                price = String.valueOf(skills.size() * Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_SKILLS_PRICE);
                skillsCount = String.valueOf(skills.size());
            }
            else
            {
                price = "0";
                skillsCount = "Нет доступных умений.";
            }

        }
        else
        {
            price = "0";
            skillsCount = "Нет доступных умений.";
        }

        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%skills%", skillsCount).replace("%massage%", massage).replace("%price%", price);
        ShowBoardPacket.separateAndSend(html, player);
    }
}
