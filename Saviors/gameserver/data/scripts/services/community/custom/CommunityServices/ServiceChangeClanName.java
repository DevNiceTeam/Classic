package services.community.custom.CommunityServices;

import services.community.custom.CommunityServices.handler.CommunityServicesHandler;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.utils.ItemFunctions;
import studio.lineage2.gameserver.utils.Util;

/**
 * Created by Averen on 25.03.2017.
 */
public class ServiceChangeClanName extends CommunityServicesHandler
{
    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_SERVICE_CHANGE_CLAN_NAME_ENABLED)
        {
            super.onInit();
        }
    }

    @Override
    public String[] getServicesCommands()
    {
        return new String[]{ "serviceChangeClanName", "buyChangeClanName" };
    }

    @Override
    public void onServiceCommand(String bypass, Player player)
    {
        if(bypass.startsWith("serviceChangeClanName"))
        {
            String[] mBypass = bypass.split(" ");
            ShowPage(mBypass[1], player, "");
        }
        else if(bypass.startsWith("buyChangeClanName"))
        {
            String[] mBypass = bypass.split(" ");
            String name;

            if(mBypass.length == 3)
            {
                name = mBypass[2];
                if(!Util.isMatchingRegexp(name, Config.CLAN_NAME_TEMPLATE))
                {
                    ShowPage(mBypass[1], player, "Неверное имя.");
                    return;
                }

                Clan clan = player.getClan();
                if(clan != null)
                {
                    if(ItemFunctions.deleteItem(player, Config.COMMUNITYBOARD_SERVICE_CHANGE_CLAN_NAME_ITEM, Config.COMMUNITYBOARD_SERVICE_CHANGE_CLAN_NAME_PRICE))
                    {
                        player.getClan().getSubUnit(Clan.SUBUNIT_MAIN_CLAN).setName(name, true);
                        player.getClan().broadcastClanStatus(true, true, true);
                        ShowPage(mBypass[1], player, "Вы успешно сменили название клана.");
                    }
                    else
                    {
                        ShowPage(mBypass[1], player, "У вас не достаточно средств.");
                    }

                }
                else
                {
                    ShowPage(mBypass[1], player, "Вы не состоите в клане.");
                }
            }
            else
            {
                ShowPage(mBypass[1], player, "Неверное имя.");
            }
        }
    }

    private void ShowPage(String page, Player player, String massage)
    {
        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%clanname%", player.getClan() != null ? player.getClan().getName() : "Вы не состоите в клане.").replace("%massage%", massage);
        ShowBoardPacket.separateAndSend(html, player);
    }

}
