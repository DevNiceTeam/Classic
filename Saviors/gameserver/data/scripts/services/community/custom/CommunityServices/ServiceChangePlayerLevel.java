package services.community.custom.CommunityServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.community.custom.CommunityServices.handler.CommunityServicesHandler;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.base.Experience;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.utils.ItemFunctions;

/**
 * Created by Averen on 24.03.2017.
 */
public class ServiceChangePlayerLevel extends CommunityServicesHandler
{
    private static final Logger _log = LoggerFactory.getLogger(ServiceChangePlayerLevel.class);

    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_SERVICE_BUY_LEVEL_ENABLED)
        {
            super.onInit();
        }
    }

    @Override
    public String[] getServicesCommands()
    {
        return new String[]{ "serviceChangeCharacterLevel", "buyChangeCharacterLevel" };
    }

    @Override
    public void onServiceCommand(String bypass, Player player)
    {
        if(bypass.startsWith("serviceChangeCharacterLevel"))
        {
            String[] mBypass = bypass.split(" ");
            ShowPage(mBypass[1], player, "");
        }
        else if(bypass.startsWith("buyChangeCharacterLevel"))
        {
            String[] mBypass = bypass.split(" ");
            if(mBypass.length == 3)
            {
                if(mBypass[2].equals("next"))
                {
                    changeLevel(player, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_LEVEL_NEXT_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_LEVEL_NEXT_PRICE, mBypass[1], player.getLevel()+1);
                }
                else if(mBypass[2].equals("max"))
                {
                    changeLevel(player, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_LEVEL_MAX_ITEM, Config.COMMUNITYBOARD_SERVICE_BUY_CLAN_LEVEL_MAX_PRICE, mBypass[1], 80);
                }
            }
            else
            {
                ShowPage(mBypass[1], player, "Неверный параметр");
            }
        }
    }

    private void changeLevel(Player player, int itemId, long price, String page, int level)
    {
        if(player.getLevel() != 80)
        {
            if(ItemFunctions.deleteItem(player, itemId, price))
            {
                Long exp_add = Experience.LEVEL[level] - player.getExp();
                player.addExpAndSp(exp_add, 0, true);
                ShowPage(page, player, "Вы успешно повысили уровень.");
            }
            else
            {
                ShowPage(page, player, "У вас не достаточно средств.");
            }
        }
        else
        {
            ShowPage(page, player, "Вы уже имеете макимальный уровень.");
        }
    }

    private void ShowPage(String page, Player player, String massage)
    {
        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%level%", String.valueOf(player.getLevel())).replace("%massage%", massage);
        ShowBoardPacket.separateAndSend(html, player);
    }
}
