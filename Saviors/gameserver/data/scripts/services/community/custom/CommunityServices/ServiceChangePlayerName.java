package services.community.custom.CommunityServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.community.custom.CommunityServices.handler.CommunityServicesHandler;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.dao.CommunityBoardServicesDAO;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.utils.ItemFunctions;
import studio.lineage2.gameserver.utils.Util;

/**
 * Created by Averen on 23.03.2017.
 */
public class ServiceChangePlayerName extends CommunityServicesHandler
{

    private static final Logger _log = LoggerFactory.getLogger(ServiceChangePlayerName.class);

    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_SERVICE_CHANGE_NICK_ENABLED)
        {
            super.onInit();
        }
    }

    @Override
    public String[] getServicesCommands()
    {
        return new String[] { "serviceChangeCharacterName", "buyChangeCharacterName" };
    }

    @Override
    public void onServiceCommand(String bypass, Player player)
    {
        if(bypass.startsWith("serviceChangeCharacterName"))
        {
            String[] mBypass = bypass.split(" ");
            ShowPage(mBypass[1], player);
        }
        else if(bypass.startsWith("buyChangeCharacterName"))
        {
            String[] mBypass = bypass.split(" ");
            String name;

            if(mBypass.length == 3)
            {
                name = mBypass[2];
                if(!Util.isMatchingRegexp(name, Config.CNAME_TEMPLATE))
                {
                    ShowPageWithMassage(mBypass[1], player, "Неверный ник.");
                    return;
                }
            }
            else
            {
                ShowPageWithMassage(mBypass[1], player, "Неверный ник.");
                return;
            }

            if(CommunityBoardServicesDAO.getInstance().checkNameExist(name))
            {
                ShowPageWithMassage(mBypass[1], player, "Данное имя уже используется.");
                return;
            }

            if(ItemFunctions.deleteItem(player, Config.COMMUNITYBOARD_SERVICE_CHANGE_NICK_ITEM, Config.COMMUNITYBOARD_SERVICE_CHANGE_NICK_PRICE))
            {
                CommunityBoardServicesDAO.getInstance().updateName(name, player.getObjectId());
                player.setName(name);
                player.broadcastCharInfo();
                ShowPageWithMassage(mBypass[1], player, "Вы успешно сменили имя.");
            }
            else
            {
                ShowPageWithMassage(mBypass[1], player, "У вас не достаточно средств.");
            }
        }
    }

    private void ShowPage(String page, Player player)
    {
        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%name%", player.getName()).replace("%massage%", "").replace("%content%", "");
        ShowBoardPacket.separateAndSend(html, player);
    }

    private void ShowPageWithMassage(String page, Player player, String massage)
    {
        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%name%", player.getName()).replace("%content%", "").replace("%massage%", massage);
        ShowBoardPacket.separateAndSend(html, player);
    }
}
