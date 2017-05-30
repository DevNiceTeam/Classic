package services.community;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import services.community.custom.CommunityServices.handler.CommunityServicesHandler;
import services.community.custom.CommunityServices.handler.CommunityServicesHolder;
import studio.lineage2.commons.dbutils.DbUtils;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.GameTimeController;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.data.xml.holder.MultiSellHolder;
import studio.lineage2.gameserver.database.DatabaseFactory;
import studio.lineage2.gameserver.handler.bbs.CommunityBoardHolder;
import studio.lineage2.gameserver.handler.bbs.CommunityBoard;
import studio.lineage2.gameserver.handler.bypass.BypassHolder;
import studio.lineage2.gameserver.model.GameObjectsStorage;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studio.lineage2.gameserver.utils.TradeHelper;

/**
 * Modified by Averen on 18.03.2017.
 */
public final class CommunityBoardHandler extends ScriptsCommunityHandler
{
    private static final Logger _log = LoggerFactory.getLogger(CommunityBoard.class);

    @Override
    public void onInit()
    {
        super.onInit();

        if(Config.COMMUNITYBOARD_ENABLED)
            _log.info("CommunityBoard: service loaded.");
    }

    @Override
    public String[] getBypassCommands()
    {
        return new String[]
                { "_bbshome", "_bbslink", "_bbsmultisell", "_bbspage", "_bbsservices", "_bbschatlink" };
    }

    @Override
    protected void doBypassCommand(Player player, String bypass)
    {
        StringTokenizer st = new StringTokenizer(bypass, "_");
        String cmd = st.nextToken();
        String html = "";
        if("bbshome".equals(cmd))
        {
            StringTokenizer p = new StringTokenizer(Config.BBS_DEFAULT, "_");
            String dafault = p.nextToken();
            if(dafault.equals(cmd))
            {
                html = HtmCache.getInstance().getHtml("scripts/services/community/bbs_top.htm", player);

                int favCount = 0;
                Connection con = null;
                PreparedStatement statement = null;
                ResultSet rset = null;
                try
                {
                    con = DatabaseFactory.getInstance().getConnection();
                    statement = con.prepareStatement("SELECT count(*) as cnt FROM `bbs_favorites` WHERE `object_id` = ?");
                    statement.setInt(1, player.getObjectId());
                    rset = statement.executeQuery();
                    if(rset.next())
                        favCount = rset.getInt("cnt");
                }
                catch(Exception ignored)
                {}
                finally
                {
                    DbUtils.closeQuietly(con, statement, rset);
                }

                html = html.replace("<?fav_count?>", String.valueOf(favCount));
                html = html.replace("<?clan_count?>", String.valueOf(CommunityClan.getClanList(null, false).size()));
                html = html.replace("<?market_count?>", String.valueOf(CommunityBoardHolder.getInstance().getIntProperty("col_count")));
            }
            else
            {
                CommunityBoard handler = CommunityBoardHolder.getInstance().getCommunityHandler(Config.BBS_DEFAULT);
                if(handler != null)
                    handler.onBypassCommand(player, Config.BBS_DEFAULT);
                return;
            }
        }
        else if("bbslink".equals(cmd))
            html = HtmCache.getInstance().getHtml("scripts/services/community/bbs_homepage.htm", player);
        else if(bypass.startsWith("_bbspage"))
        {
            //Example: "bypass _bbspage:index".
            String[] b = bypass.split(":");
            String page = b[1];
            html = HtmCache.getInstance().getHtml("scripts/services/community/pages/" + page + ".htm", player);
            if(page.equals("index"))
            {
                html = html.replaceFirst("%nick%", String.valueOf(player.getName()));

                html = html.replaceFirst("%prof%", String.valueOf(getProffesionNameById(player.getClassId().getId())));

                html = html.replaceFirst("%lvl%", String.valueOf(player.getLevel()));

                html = html.replaceFirst("%clan%", player.getClan() != null ? String.valueOf(player.getClan().getName()) : "<font color=\"FF0000\">нет</font>");

                html = html.replaceFirst("%pvp%", String.valueOf(player.getPvpKills() + "/" + player.getPkKills()));

                html = html.replaceFirst("%mytime%", String.valueOf(OnlineTime(player.getOnlineTime())));

                html = html.replaceFirst("%premium%", player.hasPremiumAccount()?String.valueOf(player.getNetConnection().getPremiumAccountExpire()):"<font color=\"LEVEL\"><a action=\"bypass _bbsscripts:services.RateBonus:list\">Купить премиум-аккаунт</a></font>");

                html = html.replaceFirst("%servhwid%", "<a action=\"bypass -h user_lock\">Привязать</a>");

                html = html.replaceFirst("%servip%", "<a action=\"bypass -h user_lock\">Привязать</a>");

                html = html.replaceFirst("%ip%", player.getIP());

                html = html.replaceFirst("%time%", getTimeInServer(player));
                html = html.replaceFirst("%online%", String.valueOf(GameObjectsStorage.getPlayers().size()));
                html = html.replaceFirst("%trade%", String.valueOf(TradeHelper.getOfflineTradersCount()));
            }

        }
        else if(bypass.startsWith("_bbsmultisell"))
        {
            if(!CheckCondition(player))
            {
                onWrongCondition(player);
                return;
            }

            //Example: "_bbsmultisell:10000;_bbspage:index" or "_bbsmultisell:10000;_bbshome" or "_bbsmultisell:10000"...
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");
            String pBypass = st2.hasMoreTokens() ? st2.nextToken() : null;
            if(pBypass != null)
            {
                CommunityBoard handler = CommunityBoardHolder.getInstance().getCommunityHandler(pBypass);
                if(handler != null)
                    handler.onBypassCommand(player, pBypass);
            }

            int listId = Integer.parseInt(mBypass[1]);
            MultiSellHolder.getInstance().SeparateAndSend(listId, player, 0);
            return;
        }
        else if(bypass.startsWith("_bbsservices"))
        {
            String[] mBypass = bypass.split(":");

            if(mBypass.length >= 2)
            {
                CommunityServicesHandler serviceHandler = CommunityServicesHolder.getInstance().getHandler(mBypass[1]);
                if(serviceHandler != null)
                {
                    serviceHandler.onServiceCommand(bypass.replace("_bbsservices:", ""), player);
                }
                else
                {
                    player.sendMessage("Сервис отключен.");
                }
            }
            return;
        }
        else if(bypass.startsWith("_bbshtmbypass"))
        {
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String command = st2.nextToken().substring(14);
            String pBypass = st2.hasMoreTokens() ? st2.nextToken() : null;
            if(pBypass != null)
                onBypassCommand(player, pBypass);

            String word = command.split("\\s+")[0];
            String args = command.substring(word.length()).trim();

            Pair<Object, Method> b = BypassHolder.getInstance().getBypass(word);
            if(b != null)
            {
                try
                {
                    b.getValue().invoke(b.getKey(), player, null, StringUtils.isEmpty(args) ? new String[0] : args.split("\\s+"));
                }
                catch(Exception e)
                {
                    _log.error("Exception: " + e, e);
                }
            }
            else
                _log.warn("Cannot find bbs html bypass: " + command);
            return;
        }
        else if(bypass.startsWith("_bbschatlink"))
        {
            //Example: "bypass _bbschatlink:common/augmentation_01.htm".
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] lBypass = st2.nextToken().split(":");
            String pBypass = st2.hasMoreTokens() ? st2.nextToken() : null;
            if(pBypass != null)
            {
                CommunityBoard handler = CommunityBoardHolder.getInstance().getCommunityHandler(pBypass);
                if(handler != null)
                    handler.onBypassCommand(player, pBypass);
            }

            String link = lBypass[1];
            HtmlMessage msg = new HtmlMessage(5);
            msg.setFile(link);
            player.sendPacket(msg);
            return;
        }

        if(html == null)
            return;

        ShowBoardPacket.separateAndSend(html, player);
    }

    @Override
    protected void doWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5)
    {}

    private String getTimeInServer(Player player)
    {
        int h = GameTimeController.getInstance().getGameHour();
        int m = GameTimeController.getInstance().getGameMin();
        String nd;
        if(GameTimeController.getInstance().isNowNight()) {
            nd = player.isLangRus()?"Ночь.":"Night.";
        } else {
            nd = player.isLangRus()?"День.":"Day.";
        }

        String strH;
        if(h < 10) {
            strH = "0" + h;
        } else {
            strH = "" + h;
        }

        String strM;
        if(m < 10) {
            strM = "0" + m;
        } else {
            strM = "" + m;
        }

        String time = strH + ":" + strM;
        return time;
    }

    String OnlineTime(int time)
    {
        long onlinetimeH;
        int onlinetimeM;
        if(time / 60 / 60 - 0.5 <= 0)
            onlinetimeH = 0;
        else
            onlinetimeH = Math.round(time / 60 / 60 - 0.5);
        onlinetimeM = Math.round((time / 60 / 60 - onlinetimeH) * 60);
        return "" + onlinetimeH + " ч. " + onlinetimeM + " м.";
    }
}
