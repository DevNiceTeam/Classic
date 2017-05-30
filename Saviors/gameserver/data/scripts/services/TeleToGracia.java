package services;

import studio.lineage2.gameserver.handler.bypass.Bypass;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.utils.Functions;

/**
 Author: Bonux
 **/
public class TeleToGracia
{
	@Bypass("services.TeleToGracia:tele")
	public void tele(Player player, NpcInstance npc, String... arg)
	{
		if(player != null && npc != null)
		{
			if(player.getLevel() < 75)
			{
				Functions.show("teleporter/" + npc.getNpcId() + "-4.htm", player);
			}
			else if(player.getAdena() >= 150000)
			{
				player.reduceAdena(150000, true);
				player.teleToLocation(-149406, 255247, -80);
			}
			else
			{
				Functions.show("teleporter/" + npc.getNpcId() + "-2.htm", player);
			}
		}
	}
}
