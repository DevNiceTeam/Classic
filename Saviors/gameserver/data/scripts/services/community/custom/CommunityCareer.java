package services.community.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import services.community.ScriptsCommunityHandler;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.base.ClassId;
import studio.lineage2.gameserver.model.base.ClassLevel;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.network.l2.s2c.SystemMessage;
import studio.lineage2.gameserver.utils.ItemFunctions;
import studio.lineage2.gameserver.utils.Util;

import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * Modified by Averen on 19.03.2017.
 */
public class CommunityCareer extends ScriptsCommunityHandler
{
    private static final Logger _log = LoggerFactory.getLogger(CommunityCareer.class);

    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_ENABLED)
        {
            super.onInit();
            _log.info("CommunityBoard: Manage Career service loaded.");
        }
    }

    @Override
    public String[] getBypassCommands()
    {
        return new String[] { "_bbscareer;", "_setProfession;"};
    }

    @Override
    public void onBypassCommand(Player activeChar, String command)
    {
        if(!CheckCondition(activeChar))
            return;

        if(command.startsWith("_bbscareer;"))
        {
            ClassId classId = activeChar.getClassId();
            ClassLevel classLevel = classId.getClassLevel();
            StringBuilder html = new StringBuilder();
            ArrayList<ClassId> AvailableProfessions;

            html.append("<center><table width=755>");
            html.append("<tr><td WIDTH=20 align=left valign=top></td>");
            html.append("<td WIDTH=690 align=left valign=top><font color=LEVEL>»</font> Добро пожаловать ").append(activeChar.getName()).append(".</td></tr>");

            html.append("<tr><td WIDTH=20 align=left valign=top></td>");
            html.append("<td WIDTH=690 align=left valign=top><font color=LEVEL>»</font> Ваша текущая профессия <font color=LEVEL>").append(getProffesionNameById(activeChar.getClassId().getId())).append("</font>.</td></tr></table>");


            if(classLevel == ClassLevel.NONE && Config.COMMUNITYBOARD_CAREER_LIST.contains(1))
            {
                //first
                if(activeChar.getLevel() >= 20)
                {
                    AvailableProfessions = getAvailableProfessions(activeChar);
                    html.append(renderProfessionList(AvailableProfessions));
                }
                else
                {
                    html.append("Для того чтобы сменить вашу профессию вы должны достичь: <font color=F2C202>20-го уровня</font><br>");
                }

            }
            else if(classLevel == ClassLevel.FIRST && Config.COMMUNITYBOARD_CAREER_LIST.contains(2))
            {
                //second
                if(activeChar.getLevel() >= 40)
                {
                    AvailableProfessions = getAvailableProfessions(activeChar);
                    html.append(renderProfessionList(AvailableProfessions));
                }
                else
                {
                    html.append("Для того чтобы сменить вашу профессию вы должны достичь: <font color=F2C202>40-го уровня</font><br>");
                }
            }
            else if(classLevel == ClassLevel.SECOND && Config.COMMUNITYBOARD_CAREER_LIST.contains(3))
            {
                //third
                if(activeChar.getLevel() >= 76)
                {
                    AvailableProfessions = getAvailableProfessions(activeChar);
                    html.append(renderProfessionList(AvailableProfessions));
                }
                else
                {
                    html.append("Для того чтобы сменить вашу профессию вы должны достичь: <font color=F2C202>76-го уровня</font><br>");
                }
            }
            else
            {
                html.append("Для вас больше нет доступных профессий, либо Класс мастер в данный момент недоступен.<br>");
            }
            String content = HtmCache.getInstance().getIfExists(Config.BBS_HOME_DIR + "pages/career.htm", activeChar);
            content = content.replace("%career%", html.toString());
            ShowBoardPacket.separateAndSend(content, activeChar);
        }
        if(command.startsWith("_setProfession;"))
        {
            StringTokenizer st = new StringTokenizer(command, ";");
            int professionId;

            st.nextToken();
            professionId = Integer.parseInt(st.nextToken());

            ArrayList<ClassId> AvailableProfessions = getAvailableProfessions(activeChar);

            for(ClassId classId : AvailableProfessions)
            {
                if(classId.getId() == professionId)
                {
                    if(checkPrise(activeChar))
                    {
                        setProfession(activeChar, professionId);
                        onBypassCommand(activeChar, "_bbscareer;");
                    }
                    else
                    {
                        if(Config.COMMUNITYBOARD_CAREER_PRICE_ITEM  == 57)
                        {
                            activeChar.sendPacket(new SystemMessage(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA));
                        }
                        else
                        {
                            activeChar.sendMessage("Недостаточно средств!");
                        }
                    }
                }
            }

        }
    }

    private void setProfession(Player activeChar, int classId)
    {
        if(activeChar.getClassId().getClassLevel() == ClassLevel.THIRD)
        {
            activeChar.sendPacket(SystemMsg.CONGRATULATIONS__YOUVE_COMPLETED_YOUR_THIRDCLASS_TRANSFER_QUEST);
        }
        else
        {
            activeChar.sendPacket(SystemMsg.CONGRATULATIONS__YOUVE_COMPLETED_A_CLASS_TRANSFER);
        }

        activeChar.setClassId(classId, false);
        activeChar.broadcastUserInfo(true);
    }

    private boolean checkPrise(Player activeChar)
    {

        if(activeChar.getClassId().getClassLevel() == ClassLevel.NONE && (Config.COMMUNITYBOARD_CAREER_PRICE_LIST[1] == 0 || ItemFunctions.deleteItem(activeChar, Config.COMMUNITYBOARD_CAREER_PRICE_ITEM, Config.COMMUNITYBOARD_CAREER_PRICE_LIST[1], true)))
        {
            return true;
        }
        else if(activeChar.getClassId().getClassLevel() == ClassLevel.FIRST && (Config.COMMUNITYBOARD_CAREER_PRICE_LIST[2] == 0 || ItemFunctions.deleteItem(activeChar, Config.COMMUNITYBOARD_CAREER_PRICE_ITEM, Config.COMMUNITYBOARD_CAREER_PRICE_LIST[2], true)))
        {
            return true;
        }
        else if(activeChar.getClassId().getClassLevel() == ClassLevel.SECOND && (Config.COMMUNITYBOARD_CAREER_PRICE_LIST[3] == 0 || ItemFunctions.deleteItem(activeChar, Config.COMMUNITYBOARD_CAREER_PRICE_ITEM, Config.COMMUNITYBOARD_CAREER_PRICE_LIST[3], true)))
        {
            return true;
        }
        return false;
    }

    private StringBuilder renderProfessionList(ArrayList<ClassId> AvailableProfessions)
    {
        StringBuilder sb = new StringBuilder();
        for(ClassId prof : AvailableProfessions)
        {
            sb.append("<table border=0 cellspacing=0 cellpadding=0><tr><td width=755><center><img src=\"l2ui.squaregray\" width=\"720\" height=\"1\"></center></td></tr></table>");
            sb.append("<table border=0 cellspacing=4 cellpadding=3><tr>");
            sb.append("<td FIXWIDTH=50 align=right valign=top><img src=\"icon.etc_royal_membership_i00\" width=32 height=32></td>");
            sb.append("<td FIXWIDTH=576 align=left valign=top><font color=\"0099FF\">").append(getProffesionNameById(prof.getId())).append(".</font>&nbsp;<br1>›&nbsp;Стоимость: ").append(Util.formatAdena(Config.COMMUNITYBOARD_CAREER_PRICE_LIST[ClassLevel.NONE.ordinal()+1])).append(" Adena.</td>");

            sb.append("<td FIXWIDTH=95 align=center valign=top><button value=\"Сменить\" action=\"bypass _setProfession;").append(prof.getId()).append(";").append(Config.COMMUNITYBOARD_CAREER_PRICE_LIST[ClassLevel.NONE.ordinal()+1]).append("\" back=\"l2ui_ct1.button.button_df_small_down\" fore=\"l2ui_ct1.button.button_df_small\" width=\"80\" height=\"25\"/>");
            sb.append("</td></tr></table>");
        }
        return sb;
    }

    private ArrayList<ClassId> getAvailableProfessions(Player activeChar)
    {
        ArrayList<ClassId> profs = new ArrayList<>();
        for (ClassId cid : ClassId.values())
        {
            if ((cid.childOf(activeChar.getClassId())) && (cid.getClassLevel().ordinal() == activeChar.getClassId().getClassLevel().ordinal()  + 1))
            {
                profs.add(cid);
            }
        }
        return profs;
    }

    @Override
    public void onWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5)
    {}

    private static boolean CheckCondition(Player player)
    {
        if(player == null)
            return false;

        if(!Config.USE_BBS_PROF_IS_COMBAT && (player.getPvpFlag() != 0 || player.isInDuel() || player.isInCombat() || player.isAttackingNow()))
        {
            if (player.isLangRus())
                player.sendMessage("Во время боя нельзя использовать данную функцию.");
            else
                player.sendMessage("During combat, you can not use this feature.");
            return false;
        }

        return true;
    }
}
