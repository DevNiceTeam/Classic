package services.community.custom.CommunityServices;

import services.community.custom.CommunityServices.handler.CommunityServicesHandler;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.utils.ItemFunctions;

/**
 * Created by Averen on 24.03.2017.
 */
public class ServiceBuyClanReputation extends CommunityServicesHandler
{
    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_REPUTATION_ENABLED)
        {
            super.onInit();
        }
    }

    @Override
    public String[] getServicesCommands()
    {
        return new String[] { "serviceBuyClanReputation", "buy500ClanReputation", "buy1000ClanReputation", "buy5000ClanReputation"};
    }

    @Override
    public void onServiceCommand(String bypass, Player player)
    {
        if(bypass.startsWith("serviceBuyClanReputation"))
        {
            String[] mBypass = bypass.split(" ");
            ShowPage(mBypass[1], player, "");
        }
        else if(bypass.startsWith("buy500ClanReputation"))
        {
            String[] mBypass = bypass.split(" ");
            if(!checkClanExist(player))
            {
                ShowPage(mBypass[1], player, "Вы не состоите в клане.");
                return;
            }

            if(!checkClanLeader(player))
            {
                ShowPage(mBypass[1], player, "Вы не являетесь лидером клана.");
                return;
            }
            addClanReputation(player, 500, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_REPUTATION_500_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_REPUTATION_500_PRICE, mBypass[1]);
        }
        else if(bypass.startsWith("buy1000ClanReputation"))
        {
            String[] mBypass = bypass.split(" ");
            if(!checkClanExist(player))
            {
                ShowPage(mBypass[1], player, "Вы не состоите в клане.");
                return;
            }

            if(!checkClanLeader(player))
            {
                ShowPage(mBypass[1], player, "Вы не являетесь лидером клана.");
                return;
            }

            addClanReputation(player, 1000, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_REPUTATION_1000_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_REPUTATION_1000_PRICE, mBypass[1]);
        }
        else if(bypass.startsWith("buy5000ClanReputation"))
        {
            String[] mBypass = bypass.split(" ");
            if(!checkClanExist(player))
            {
                ShowPage(mBypass[1], player, "Вы не состоите в клане.");
                return;
            }

            if(!checkClanLeader(player))
            {
                ShowPage(mBypass[1], player, "Вы не являетесь лидером клана.");
                return;
            }

            addClanReputation(player, 5000, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_REPUTATION_5000_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_REPUTATION_5000_PRICE, mBypass[1]);
        }
    }

    private void addClanReputation(Player player, int reputation, int item, int price, String bypass)
    {
        if(ItemFunctions.deleteItem(player, item, price))
        {
            player.getClan().incReputation(reputation, false, "Service Clan Reputation " + reputation);
            ShowPage(bypass, player, "Вы успешно получили " + reputation + " едениц репутации клана.");
        }
        else
        {
            ShowPage(bypass, player, "У вас не достаточно средств.");
        }
    }

    private boolean checkClanLeader(Player player)
    {
        if(player.getClan().getLeader().getPlayer() == player)
        {
            return true;
        }
        return false;
    }

    private boolean checkClanExist(Player player)
    {
        if(player.getClan() != null)
        {
            return true;
        }
        return false;
    }

    private void ShowPage(String page, Player player, String massage)
    {
        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%reputation%", String.valueOf(player.getClan() != null ? player.getClan().getReputationScore() : "нет.")).replace("%massage%", massage);
        ShowBoardPacket.separateAndSend(html, player);
    }

}
