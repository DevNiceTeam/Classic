package services;

import studio.lineage2.gameserver.handler.bypass.Bypass;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.utils.ItemFunctions;

public class TakeBeastHandler
{
	private final int BEAST_WHIP = 15473;

	@Bypass("services.TakeBeastHandler:show")
	public void show(Player player, NpcInstance npc, String[] param)
	{
		String htmltext;
		if(player.getLevel() < 82)
		{
			htmltext = npc.getNpcId() + "-1.htm";
		}
		else if(ItemFunctions.getItemCount(player, BEAST_WHIP) > 0)
		{
			htmltext = npc.getNpcId() + "-2.htm";
		}
		else
		{
			ItemFunctions.addItem(player, BEAST_WHIP, 1);
			htmltext = npc.getNpcId() + "-3.htm";
		}

		npc.showChatWindow(player, "default/" + htmltext, false);
	}
}
