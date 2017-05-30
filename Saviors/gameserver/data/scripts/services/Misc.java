package services;

import studio.lineage2.gameserver.handler.bypass.Bypass;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.utils.Functions;
import studio.lineage2.gameserver.utils.ItemFunctions;

/**
 * @author pchayka
 */
public class Misc
{
	@Bypass("services.Misc:assembleAntharasCrystal")
	public void assembleAntharasCrystal(Player player, NpcInstance npc, String[] param)
	{
		if(!NpcInstance.canBypassCheck(player, player.getLastNpc()))
		{
			return;
		}

		if(ItemFunctions.getItemCount(player, 17266) < 1 || ItemFunctions.getItemCount(player, 17267) < 1)
		{
			Functions.show("teleporter/32864-2.htm", player);
			return;
		}
		if(ItemFunctions.deleteItem(player, 17266, 1, true) && ItemFunctions.deleteItem(player, 17267, 1, true))
		{
			ItemFunctions.addItem(player, 17268, 1, true);
			Functions.show("teleporter/32864-3.htm", player);
			return;
		}
	}
}