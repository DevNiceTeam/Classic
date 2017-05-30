package services.community.custom.CommunityServices;

import services.community.custom.CommunityServices.handler.CommunityServicesHandler;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.data.xml.holder.PremiumAccountHolder;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.templates.item.data.ItemData;
import studio.lineage2.gameserver.templates.premiumaccount.PremiumAccountTemplate;
import studio.lineage2.gameserver.utils.ItemFunctions;
import studio.lineage2.gameserver.utils.Language;

/**
 * Created by Averen on 25.03.2017.
 */
public class ServiceBuyPremiumAccount extends CommunityServicesHandler
{
    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_SERVICE_BUY_PREMIUM_ENABLED)
        {
            super.onInit();
        }
    }

    @Override
    public String[] getServicesCommands()
    {
        return new String[]{ "serviceBuyPremiumAccount", "buyPremiumAccountType" };
    }

    @Override
    public void onServiceCommand(String bypass, Player player)
    {
        String[] mBypass = bypass.split(" ");

        if(mBypass[0].equals("serviceBuyPremiumAccount"))
        {
            if(player.hasPremiumAccount())
            {
                ShowPageWithPremiumType(mBypass[1], player, "", player.getPremiumAccount().getName(Language.RUSSIAN));
            }
            else
            {
                ShowPage(mBypass[1], player, "");
            }
        }
        else if(mBypass[0].equals("buyPremiumAccountType"))
        {
            if(mBypass.length < 4)
            {
                ShowPage(mBypass[1], player, "Неверный параметр.");
                return;
            }


            int type = Integer.parseInt(mBypass[2]);
            int delay = Integer.parseInt(mBypass[3]);

            if(!player.hasPremiumAccount())
            {
                PremiumAccountTemplate premium = PremiumAccountHolder.getInstance().getPremiumAccount(type);
                if(premium != null)
                {
                    if(!checkDelay(premium, delay))
                    {
                        ShowPage(mBypass[1], player, "Неверный параметр.");
                        return;
                    }

                    if(checkItems(player, premium, delay))
                    {
                        deleteItems(player, premium, delay);
                        player.givePremiumAccount(premium, delay);
                        ShowPage(mBypass[1], player, "Вы успешно активировали премиум аккаунт.");
                    }
                    else
                    {
                        ShowPage(mBypass[1], player, "У вас недостаточно средств.");
                    }

                }
                else
                {
                    ShowPage(mBypass[1], player, "Неверный параметр.");
                }
            }
            else
            {
                ShowPage(mBypass[1], player, "У вас уже активирован премиум аккаунт.");
            }

        }
    }

    private boolean checkDelay(PremiumAccountTemplate premium, int delay)
    {
        return premium.getFeeByDealy(delay) != null;
    }

    private boolean checkItems(Player player, PremiumAccountTemplate premium, int delay)
    {
        if(premium.getFeeByDealy(delay) == null)
        {
            return false;
        }
        for(ItemData item : premium.getFeeByDealy(delay))
        {
            if(!(ItemFunctions.getItemCount(player, item.getId()) >= item.getCount()))
            {
                return false;
            }
        }
        return true;
    }

    private void deleteItems(Player player, PremiumAccountTemplate premium, int delay)
    {
        for(ItemData item : premium.getFeeByDealy(delay))
        {
            ItemFunctions.deleteItem(player, item.getId(), item.getCount());
        }
    }

    private void ShowPage(String page, Player player, String massage)
    {
        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%accounttype%", "Обычный").replace("%massage%", massage);
        ShowBoardPacket.separateAndSend(html, player);
    }

    private void ShowPageWithPremiumType(String page, Player player, String massage, String premium)
    {
        String html;
        html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
        html = html.replace("%accounttype%", premium).replace("%massage%", massage);
        ShowBoardPacket.separateAndSend(html, player);
    }
}
