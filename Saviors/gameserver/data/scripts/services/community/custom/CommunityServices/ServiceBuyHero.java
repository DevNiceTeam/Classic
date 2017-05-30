package services.community.custom.CommunityServices;

import services.community.custom.CommunityServices.handler.CommunityServicesHandler;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.Hero;
import studio.lineage2.gameserver.model.entity.olympiad.Olympiad;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.network.l2.s2c.SocialActionPacket;
import studio.lineage2.gameserver.templates.StatsSet;
import studio.lineage2.gameserver.utils.ItemFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Averen on 27.03.2017.
 */
public class ServiceBuyHero extends CommunityServicesHandler
{
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_SERVICE_BUY_HERO_ENABLED)
        {
            super.onInit();
        }
    }

    @Override
    public String[] getServicesCommands()
    {
        return new String[]{ "serviceBuyHero", "buyHero" };
    }

    @Override
    public void onServiceCommand(String bypass, Player player)
    {
        if(bypass.startsWith("serviceBuyHero"))
        {
            String[] mBypass = bypass.split(" ");
            ShowPage(mBypass[1], player, "");
        }
        else if(bypass.startsWith("buyHero"))
        {
            String[] mBypass = bypass.split(" ");

            if(mBypass.length < 3)
            {
                ShowPage(mBypass[1], player, "Неверный параметр.");
                return;
            }

            if(player.isHero())
            {
                ShowPage(mBypass[1], player, "Вы уже являетесь героем.");
                return;
            }

            if(mBypass[2].equals("day"))
            {
                if(ItemFunctions.deleteItem(player, Config.COMMUNITYBOARD_SERVICE_BUY_HERO_DAY_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_HERO_DAY_PRICE))
                {
                    setHero(player, 1);
                    ShowPage(mBypass[1], player, "Вы успешно приобрели статус героя на день.");
                }
                else
                {
                    ShowPage(mBypass[1], player, "У вас не достаточно средств.");
                }
            }
            else if(mBypass[2].equals("week"))
            {
                if(ItemFunctions.deleteItem(player, Config.COMMUNITYBOARD_SERVICE_BUY_HERO_WEEK_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_HERO_WEEK_PRICE))
                {
                    setHero(player, 7);
                    ShowPage(mBypass[1], player, "Вы успешно приобрели статус героя на неделю.");
                }
                else
                {
                    ShowPage(mBypass[1], player, "У вас не достаточно средств.");
                }
            }
            else if(mBypass[2].equals("month"))
            {
                if(ItemFunctions.deleteItem(player, Config.COMMUNITYBOARD_SERVICE_BUY_HERO_MONTH_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_HERO_MONTH_PRICE))
                {
                    setHero(player, 30);
                    ShowPage(mBypass[1], player, "Вы успешно приобрели статус героя на месяц.");
                }
                else
                {
                    ShowPage(mBypass[1], player, "У вас не достаточно средств.");
                }
            }



        }
    }

    private void setHero(Player player, int days)
    {
        player.setVar("HeroPeriod", (System.currentTimeMillis() + 60 * 1000 * 60 * 24 * days), -1);

        player.setHero(true);
        if(player.isHero())
        {
            player.broadcastPacket(new SocialActionPacket(player.getObjectId(), 16));
        }
        player.broadcastUserInfo(true);
    }

    private void ShowPage(String page, Player player, String massage)
    {
        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%hero%", String.valueOf(player.isHero()?"Герой" : "Не герой")).replace("%massage%", massage);
        ShowBoardPacket.separateAndSend(html, player);
    }
}
