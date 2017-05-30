package services.community.custom;

import services.community.ScriptsCommunityHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.dao.CommunityBoardTeleportDAO;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.data.xml.holder.CommunityTeleportPointsHolder;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.World;
import studio.lineage2.gameserver.model.Zone;
import studio.lineage2.gameserver.model.base.TeamType;
import studio.lineage2.gameserver.model.entity.Reflection;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;
import studio.lineage2.gameserver.templates.CommunityTeleportPoint;
import studio.lineage2.gameserver.utils.Location;

/**
 * Modefied by Averen on 22.03.2017.
 */
public class CommunityTeleport extends ScriptsCommunityHandler
{
    private static final Logger _log = LoggerFactory.getLogger(CommunityTeleport.class);


    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_ENABLED)
        {
            super.onInit();
            _log.info("CommunityBoard: Teleport Community service loaded.");
        }
    }

    @Override
    public String[] getBypassCommands()
    {
        return new String[] {
                "_bbsteleport",
                "_bbsgotopoint",
                "_bbstsave",
                "_bbstrestore",
                "_bbstdelete"
        };
    }

    @Override
    public void onBypassCommand(Player player, String bypass)
    {
        if(!CheckCondition(player))
            return;

        if(bypass.startsWith("_bbsteleport"))
        {
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");
            ShowHtml(mBypass.length == 1 ? "index" : mBypass[1], player);
        }
        else if(bypass.startsWith("_bbsgotopoint"))
        {
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");

            CommunityTeleportPoint point = null;

            if(mBypass[2].equals("saved"))
            {
                int pointId = Integer.parseInt(mBypass[3]);
                for(CommunityTeleportPoint tpoint : CommunityBoardTeleportDAO.getInstance().getPointsByCharacterId(player.getObjectId()))
                {
                    if(pointId == tpoint.getId())
                    {
                        point = tpoint;
                    }
                }
            }
            else
            {
                int pointId = Integer.parseInt(mBypass[2]);
                point = CommunityTeleportPointsHolder.getInstance().getData(pointId);
            }


            List<Zone> zones = new ArrayList<>();

            if(point == null)
                return;

            World.getZones(zones, new Location(point.getX(), point.getY(), point.getZ()), Reflection.createReflection(0));

            int price;
            if (Config.COMMUNITYBOARD_BOARD_ALT_ENABLED)
            {
                if (player.getLevel() < 20)
                    price = Config.COMMUNITYBOARD_TELEPORT_PICE_NG;
                else if (player.getLevel() >= 20 && player.getLevel() <40)
                    price = Config.COMMUNITYBOARD_TELEPORT_PICE_D;
                else if (player.getLevel() >= 40 && player.getLevel() <52)
                    price = Config.COMMUNITYBOARD_TELEPORT_PICE_C;
                else if (player.getLevel() >= 52 && player.getLevel() <61)
                    price = Config.COMMUNITYBOARD_TELEPORT_PICE_B;
                else if (player.getLevel() >= 61 && player.getLevel() <76)
                    price = Config.COMMUNITYBOARD_TELEPORT_PICE_A;
                else if (player.getLevel() >= 76 && player.getLevel() <80)
                    price = Config.COMMUNITYBOARD_TELEPORT_PICE_S;
                else if (player.getLevel() >= 80 && player.getLevel() <84)
                    price = Config.COMMUNITYBOARD_TELEPORT_PICE_S80;
                else
                    price = Config.COMMUNITYBOARD_TELEPORT_PICE_S84;
            }
            else
                price = Config.COMMUNITYBOARD_TELE_PICE;
            String page = mBypass[1];

            if(player.getAdena() < price)
            {
                if (player.isLangRus())
                    player.sendMessage("Недостаточно сердств!");
                else
                    player.sendMessage("It is not enough money!");
                ShowHtml(page, player);
                return;
            }

            for(Zone zone : zones)
                if(zone.getType() == Zone.ZoneType.SIEGE)
                {
                    if (player.isLangRus())
                        player.sendMessage("Невозможно телепортироваться в местность, где активна осада!");
                    else
                        player.sendMessage("Unable to teleport to the area where the siege is active!");
                    ShowHtml(page, player);
                    return;
                }
            player.teleToLocation(point.getX(), point.getY(), point.getZ(), 0);

            player.reduceAdena(price, true);
            ShowHtml(page, player);
        }
        else if(bypass.startsWith("_bbstsave"))
        {
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");
            int price = Config.COMMUNITYBOARD_SAVE_TELE_PICE;
            try
            {
                String name = mBypass[2].substring(1);

                if(Config.COMMUNITYBOARD_SAVE_TELE_PREMIUM)
                {
                    if(!player.hasPremiumAccount())
                    {
                        if (player.isLangRus())
                            player.sendMessage("Доступно только для игроков с ПА!");
                        else
                            player.sendMessage("Available only to players from PA!");
                        ShowHtml(mBypass[1], player);
                        return;
                    }
                }
                if (player.isInZone("[baium_epic]") || player.isInZone("[antharas_epic]") || player.isInZone("[valakas_epic]") || player.isInZone("[sailren_epic]") || player.isInZone("[baylor_epic]") || player.isInZone("[queen_ant_epic]") || player.isInZone("[FourSepulchers1]") || player.isInZone("[FourSepulchers2]") || player.isInZone("[FourSepulchers3]") || player.isInZone("[FourSepulchers4]") || player.isInZone("[Frintezza]") || player.isInZone("[LastImperialTomb]") || player.isInZone("[vanhalter_epic]") || player.isInZone("[beleth_epic]") ||player.isInZone("[freya_normal_epic]") || player.isInZone("[tiat_room_epic]") || player.isInZone("[freya_landing_room_epic]"))
                {
                    if (player.isLangRus())
                        player.sendMessage("Здесь сохранить точку нельзя!");
                    else
                        player.sendMessage("It can store the point!");
                    ShowHtml(mBypass[1], player);
                    return;
                }

                if(player.getAdena() < price)
                {
                    if (player.isLangRus())
                        player.sendMessage("Недостаточно сердств!");
                    else
                        player.sendMessage("It is not enough money!");
                    ShowHtml(mBypass[1], player);
                    return;
                }

                if(CommunityBoardTeleportDAO.getInstance().getTeleportPointCount(player) >= Config.COMMUNITYBOARD_SAVE_TELE_COUNT)
                {
                    if (player.isLangRus())
                        player.sendMessage("Превышено максимално допустимое количество точек возвращения!");
                    else
                        player.sendMessage("Exceeded the maximum number of return points!");
                    ShowHtml(mBypass[1], player);
                    return;
                }
                if(!CommunityBoardTeleportDAO.getInstance().checkTeleportNameExist(player, name))
                {
                    if (player.isLangRus())
                        player.sendMessage("Точка с таким названием уже существует!");
                    else
                        player.sendMessage("The point with this name already exists!");
                    ShowHtml(mBypass[1], player);
                    return;
                }

                if(name.length() > 15)
                    name = name.substring(0, 15);

                if(name.length() > 0)
                {
                    CommunityBoardTeleportDAO.getInstance().teleportPointAdd(player, name);
                }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                if(player.isLangRus())
                    player.sendMessage("Вы не ввели имя для сохранения!");
                else
                    player.sendMessage("You did not enter a name to save!");
                return;
            }
            player.reduceAdena(price, true);
            ShowHtml(mBypass[1], player);
        }
        else if(bypass.startsWith("_bbstdelete"))
        {
            StringTokenizer st2 = new StringTokenizer(bypass, ";");
            String[] mBypass = st2.nextToken().split(":");
            CommunityBoardTeleportDAO.getInstance().teleportPointRemove(player.getObjectId(), Integer.parseInt(mBypass[2]));
            ShowHtml(mBypass[1], player);
        }
    }

    @Override
    public void onWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5){}





    private void ShowHtml(String name, Player player)
    {
        if(name.equals("home"))
        {
            onBypassCommand(player, Config.BBS_DEFAULT);
        }

        String html = HtmCache.getInstance().getIfExists(Config.BBS_HOME_DIR + "pages/teleport/" + name + ".htm", player);

        if (Config.COMMUNITYBOARD_BOARD_ALT_ENABLED)
        {
            if (player.getLevel() < 20)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_TELEPORT_PICE_NG));
            else if (player.getLevel() >= 20 && player.getLevel() <40)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_TELEPORT_PICE_D));
            else if (player.getLevel() >= 40 && player.getLevel() <52)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_TELEPORT_PICE_C));
            else if (player.getLevel() >= 52 && player.getLevel() <61)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_TELEPORT_PICE_B));
            else if (player.getLevel() >= 61 && player.getLevel() <76)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_TELEPORT_PICE_A));
            else if (player.getLevel() >= 76 && player.getLevel() <80)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_TELEPORT_PICE_S));
            else if (player.getLevel() >= 80 && player.getLevel() <84)
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_TELEPORT_PICE_S80));
            else
                html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_TELEPORT_PICE_S84));
        }
        else
            html = html.replace("%price%", GetStringCount(Config.COMMUNITYBOARD_TELE_PICE));

        html = html.replace("%save_pice%", GetStringCount(Config.COMMUNITYBOARD_SAVE_TELE_PICE));

        StringBuilder content = new StringBuilder("");
        content.append("<table width=220>");
        for(CommunityTeleportPoint point : CommunityBoardTeleportDAO.getInstance().getPointsByCharacterId(player.getObjectId()))
        {
            content.append("<tr>");
            content.append("<td>");
            content.append("<button value=\"").append(point.getName()).append("\" action=\"bypass _bbsgotopoint:index:saved:").append(point.getId()).append(";\" width=100 height=20 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\">");
            content.append("</td>");
            content.append("<td>");
            content.append("<button value=\"Удалить\" action=\"bypass _bbstdelete:index:").append(point.getId()).append(";\" width=100 height=20 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\">");
            content.append("</td>");
            content.append("</tr>");
        }
        content.append("</table>");

        if(Config.COMMUNITYBOARD_SAVE_TELE_PREMIUM)
        {
            if(!player.hasPremiumAccount())
            {
                html = html.replace("%list_teleport%",  "<table width=250><tr><td><center><font color=F2C202>Только для игроков с Премиум Аккаунтом</font></center></td></tr></table>");
                ShowBoardPacket.separateAndSend(html, player);
            }
        }

        html = html.replace("%list_teleport%",  content.toString());
        ShowBoardPacket.separateAndSend(html, player);
    }

    private static boolean CheckCondition(Player player)
    {
        if(!Config.COMMUNITYBOARD_TELEPORT_ENABLED)
        {
            if (player.isLangRus())
                player.sendMessage("Функция телепорта отключена.");
            else
                player.sendMessage("Teleport function is disabled.");
            return false;
        }

        if(player == null)
            return false;

        if(!Config.USE_BBS_TELEPORT_IS_COMBAT && (player.getPvpFlag() != 0 || player.isInDuel() || player.isInCombat() || player.isAttackingNow()))
        {
            if (player.isLangRus())
                player.sendMessage("Во время боя нельзя использовать данную функцию.");
            else
                player.sendMessage("During combat, you can not use this feature.");
            return false;
        }

        if(player.isInOlympiadMode())
        {
            if (player.isLangRus())
                player.sendMessage("Во время Олимпиады нельзя использовать данную функцию.");
            else
                player.sendMessage("During the Olympics you can not use this feature.");
            return false;
        }

        if(player.getReflection().getId() != 0 && !Config.COMMUNITYBOARD_INSTANCE_ENABLED)
        {
            player.sendMessage("Телепорт доступен только в обычном мире.");
            return false;
        }

        if (!Config.COMMUNITYBOARD_EVENTS_ENABLED)
        {
            if (player.getTeam() != TeamType.NONE)
            {
                if (player.isLangRus())
                    player.sendMessage("Нельзя использовать телепорт во время эвентов.");
                else
                    player.sendMessage("You can not use Teleport during Events.");
                return false;
            }
        }

        if(!Config.COMMUNITYBOARD_TELEPORT_SIEGE_ENABLED && player.isInZone(Zone.ZoneType.SIEGE))
        {
            if (player.isLangRus())
                player.sendMessage("В зоне, находящейся в осаде, использовать телепорт запрещено.");
            else
                player.sendMessage("In the zone, located in the siege, use the teleport is prohibited.");
            return false;
        }
        return true;
    }
}
