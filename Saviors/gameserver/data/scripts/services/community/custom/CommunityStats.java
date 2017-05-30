package services.community.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.community.ScriptsCommunityHandler;
import studio.lineage2.commons.dbutils.DbUtils;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.database.DatabaseFactory;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * TODO: [Averen] переделать
 * */
public class CommunityStats extends ScriptsCommunityHandler
{
    private static final Logger _log = LoggerFactory.getLogger(CommunityStats.class);

    private long lUpdateTime;
    private int nCounter;

    /**
     * Имплементированые методы скриптов
     */
    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_ENABLED)
        {
            super.onInit();
            _log.info("CommunityBoard: Stats service loaded.");
            lUpdateTime = 0L;
            nCounter = 0;
        }
    }

    private static CBStatMan pbBStats = new CBStatMan();

    public String[] getBypassCommands()
    {
        return new String[] { "_bbsstat", "_bbsstat:index" };
    }

    private static class CBStatMan
    {
        private String[] asCharNameTopPvP = new String[10];
        private int[] anCharPvPCount = new int[10];
        private int[] anCharPvPOnline = new int[10];
        private String[] asCharNameTopPk = new String[10];
        private int[] anCharPkCount = new int[10];
        private int[] anCharPkOnline = new int[10];
    }

    public void onBypassCommand(Player player, String command)
    {
        if(command.equals("_bbsstat"))
        {
            if(lUpdateTime + 300L < System.currentTimeMillis() / 1000L)
            {
                selectTopPK(player);
                selectTopPVP(player);

                lUpdateTime = (System.currentTimeMillis() / 1000L);
            }
            showAllStats(player, 1);
        }
        else if(command.startsWith("_bbsstat:index"))
        {
            selectTopPK(player);
            selectTopPVP(player);
            showAllStats(player, 1);
        }
        else
        {
            ShowBoardPacket.separateAndSend("<html><body><br><br><center>В bbsstat функция: " + command + " пока не реализована</center><br><br></body></html>", player);
        }
    }

    private void showAllStats(Player player, int nPage)
    {
        nCounter = 0;
        if(nPage == 1)
        {
            String content = HtmCache.getInstance().getIfExists(Config.BBS_HOME_DIR + "pages/stats.htm", player);
            while(nCounter < 10)
            {
                if(pbBStats.asCharNameTopPvP[nCounter] != null)
                {
                    content = content.replace("%Top_PvP_Name_" + nCounter + "%", pbBStats.asCharNameTopPvP[nCounter]);
                    content = content.replace("%Top_PvP_Count_" + nCounter + "%", Integer.toString(pbBStats.anCharPvPCount[nCounter]));
                    content = content.replace("%Top_PvP_Online_" + nCounter + "%", pbBStats.anCharPvPOnline[nCounter] == 1 ? "<font color=\"66FF33\">да</font>" : "<font color=\"B59A75\">нет</font>");
                }
                else
                {
                    content = content.replace("%Top_PvP_Name_" + nCounter + "%", player.isLangRus() ? "Нет данных" : "No data");
                    content = content.replace("%Top_PvP_Count_" + nCounter + "%", "<font name=\"hs12\">0</font>");
                    content = content.replace("%Top_PvP_Online_" + nCounter + "%", "");
                }

                if(pbBStats.asCharNameTopPk[nCounter] != null)
                {
                    content = content.replace("%Top_Pk_Name_" + nCounter + "%", pbBStats.asCharNameTopPk[nCounter]);
                    content = content.replace("%Top_Pk_Count_" + nCounter + "%", Integer.toString(pbBStats.anCharPkCount[nCounter]));
                    content = content.replace("%Top_Pk_Online_" + nCounter + "%", pbBStats.anCharPkOnline[nCounter] == 1 ? "<font color=\"66FF33\">да</font>" : "<font color=\"B59A75\">нет</font>");
                }
                else
                {
                    content = content.replace("%Top_Pk_Name_" + nCounter + "%", player.isLangRus() ? "Нет данных" : "No data");
                    content = content.replace("%Top_Pk_Count_" + nCounter + "%", "<font name=\"hs12\">0</font>");
                    content = content.replace("%Top_Pk_Online_" + nCounter + "%", "");
                }
                nCounter += 1;
            }
            ShowBoardPacket.separateAndSend(content, player);
        }
    }

    public void selectTopPK(Player player)
    {
        Connection conPK = null;
        PreparedStatement statementPK = null;
        ResultSet rsPK = null;
        nCounter = 0;
        try
        {
            conPK = DatabaseFactory.getInstance().getConnection();
            statementPK = conPK.prepareStatement("SELECT char_name, pkkills, online FROM characters ORDER BY pkkills DESC LIMIT 10;");
            rsPK = statementPK.executeQuery();
            while(rsPK.next())
            {
                if(!rsPK.getString("char_name").isEmpty())
                {
                    pbBStats.asCharNameTopPk[nCounter] = rsPK.getString("char_name");
                    pbBStats.anCharPkCount[nCounter] = rsPK.getInt("pkkills");
                    pbBStats.anCharPkOnline[nCounter] = rsPK.getInt("online");
                }
                else
                {
                    pbBStats.asCharNameTopPk[nCounter] = (player.isLangRus() ? "Нет" : "No");
                    pbBStats.anCharPkCount[nCounter] = 0;
                    pbBStats.anCharPkOnline[nCounter] = 0;
                }
                nCounter += 1;
            }
        }
        catch(Exception e) {}finally
        {
            DbUtils.closeQuietly(conPK, statementPK, rsPK);
        }
    }

    public void selectTopPVP(Player player)
    {
        Connection conPVP = null;
        PreparedStatement statementPVP = null;
        ResultSet rsPVP = null;
        nCounter = 0;
        try
        {
            conPVP = DatabaseFactory.getInstance().getConnection();
            statementPVP = conPVP.prepareStatement("SELECT char_name, pvpkills, online FROM characters ORDER BY pvpkills DESC LIMIT 10;");
            rsPVP = statementPVP.executeQuery();
            while (rsPVP.next())
            {
                if(!rsPVP.getString("char_name").isEmpty())
                {
                    pbBStats.asCharNameTopPvP[nCounter] = rsPVP.getString("char_name");
                    pbBStats.anCharPvPCount[nCounter] = rsPVP.getInt("pvpkills");
                    pbBStats.anCharPvPOnline[nCounter] = rsPVP.getInt("online");
                }
                else
                {
                    pbBStats.asCharNameTopPvP[nCounter] = (player.isLangRus() ? "Нет" : "No");
                    pbBStats.anCharPvPCount[nCounter] = 0;
                    pbBStats.anCharPvPOnline[nCounter] = 0;
                }
                nCounter += 1;
            }
        }
        catch (Exception e)
        {
            _log.error("Error Community Stats:" + e, e);
        }
        finally
        {
            DbUtils.closeQuietly(conPVP, statementPVP, rsPVP);
        }
    }

    /**
     * Не используемый, но вызываемый метод имплемента
     */
    @Override
    public void onWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5)
    {}
}
