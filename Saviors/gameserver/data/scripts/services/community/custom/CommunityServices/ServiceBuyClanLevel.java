package services.community.custom.CommunityServices;

import services.community.custom.CommunityServices.handler.CommunityServicesHandler;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.utils.ItemFunctions;

/**
 * Created by Averen on 27.03.2017.
 */
public class ServiceBuyClanLevel extends CommunityServicesHandler
{
    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_LEVEL_ENABLED)
        {
            super.onInit();
        }
    }

    @Override
    public String[] getServicesCommands()
    {
        return new String[] { "serviceBuyClanLevel", "buyClanLevelNext", "buyClanLevelMax" };
    }

    @Override
    public void onServiceCommand(String bypass, Player player)
    {
        if(bypass.startsWith("serviceBuyClanLevel"))
        {
            String[] mBypass = bypass.split(" ");
            ShowPage(mBypass[1], player, "");
        }
        else if(bypass.startsWith("buyClanLevelNext"))
        {
            String[] mBypass = bypass.split(" ");

            if(player.getClan().getLeader().getPlayer() != player)
            {
                ShowPage(mBypass[1], player, "Вы не являетесь лидером клана.");
                return;
            }

            if(player.getClan() == null)
            {
                ShowPage(mBypass[1], player, "Вы не состоите в клане.");
                return;
            }

            if(player.getClan().getLevel() == 5)
            {
                ShowPage(mBypass[1], player, "Клан уже максимального уровня.");
                return;
            }

            if(ItemFunctions.deleteItem(player, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_LEVEL_NEXT_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_LEVEL_NEXT_PRICE))
            {
                player.getClan().setLevel(player.getClan().getLevel() + 1);

                player.getClan().updateClanInDB();

                player.getClan().broadcastClanStatus(true, true, true);

                ShowPage(mBypass[1], player, "Вы успешно повысили уровень клана до " + player.getClan().getLevel() + " .");
            }
            else
            {
                ShowPage(mBypass[1], player, "У вас не достаточно средств.");
            }
        }
        else if(bypass.startsWith("buyClanLevelMax"))
        {
            String[] mBypass = bypass.split(" ");

            if(player.getClan().getLeader().getPlayer() != player)
            {
                ShowPage(mBypass[1], player, "Вы не являетесь лидером клана.");
                return;
            }

            if(player.getClan() == null)
            {
                ShowPage(mBypass[1], player, "Вы не состоите в клане.");
                return;
            }

            if(player.getClan().getLevel() == 5)
            {
                ShowPage(mBypass[1], player, "Клан уже максимального уровня.");
                return;
            }

            if(ItemFunctions.deleteItem(player, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_LEVEL_MAX_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_LEVEL_MAX_PRICE))
            {
                player.getClan().setLevel(5);
                player.getClan().updateClanInDB();

                player.getClan().broadcastClanStatus(true, true, true);

                ShowPage(mBypass[1], player, "Вы успешно повысили уровень клана до 5 .");
            }
            else
            {
                ShowPage(mBypass[1], player, "У вас не достаточно средств.");
            }
        }
    }

    private void ShowPage(String page, Player player, String massage)
    {
        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%level%", String.valueOf(player.getClan() != null ? player.getClan().getLevel() : "Вы не состоите в клане.")).replace("%massage%", massage);
        ShowBoardPacket.separateAndSend(html, player);
    }

}
