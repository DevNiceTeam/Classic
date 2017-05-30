package npc.model;

import bosses.BaiumManager;
import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.GameObjectsStorage;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.Functions;
import studio.lineage2.gameserver.utils.Location;

/**
 * @author pchayka
 */

public final class BaiumGatekeeperInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	private static final int Baium = 29020;
	private static final int BaiumNpc = 29025;
	private static final Location TELEPORT_POSITION = new Location(113100, 14500, 10077);

	public BaiumGatekeeperInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		if(command.startsWith("request_entrance"))
		{
			NpcInstance baiumBoss = GameObjectsStorage.getByNpcId(Baium);
			if(baiumBoss != null)
			{
				showChatWindow(player, "default/31862-1.htm", false);
				return;
			}
			NpcInstance baiumNpc = GameObjectsStorage.getByNpcId(BaiumNpc);
			if(baiumNpc == null)
			{
				showChatWindow(player, "default/31862-2.htm", false);
				return;
			}
			player.setVar("baiumPermission", "granted", -1);
			player.teleToLocation(TELEPORT_POSITION);
		}
		else if(command.startsWith("request_wakeup"))
		{
			if(player.getVar("baiumPermission") == null || !player.getVar("baiumPermission").equalsIgnoreCase("granted"))
			{
				showChatWindow(player, "default/29025-1.htm", false);
				return;
			}
			if(isBusy())
			{
				showChatWindow(player, "default/29025-2.htm", false);
			}
			setBusy(true);
			Functions.npcSay(this, "You called my name! Now you gonna die!");
			BaiumManager.spawnBaium(this, player);
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}
}