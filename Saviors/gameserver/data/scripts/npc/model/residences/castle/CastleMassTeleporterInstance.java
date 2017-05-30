package npc.model.residences.castle;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.commons.threading.RunnableImpl;
import studio.lineage2.gameserver.ThreadPoolManager;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.World;
import studio.lineage2.gameserver.model.entity.events.impl.CastleSiegeEvent;
import studio.lineage2.gameserver.model.entity.events.impl.SiegeEvent;
import studio.lineage2.gameserver.model.entity.events.objects.SiegeToggleNpcObject;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.Functions;
import studio.lineage2.gameserver.utils.Location;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author VISTALL
 * @date 17:46/12.07.2011
 */
public class CastleMassTeleporterInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	private class TeleportTask extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			Functions.npcShout(CastleMassTeleporterInstance.this, NpcString.THE_DEFENDERS_OF_S1_CASTLE_WILL_BE_TELEPORTED_TO_THE_INNER_CASTLE, "#" + getCastle().getNpcStringName().getId());

			for(Player p : World.getAroundPlayers(CastleMassTeleporterInstance.this, 200, 50))
			{
				p.teleToLocation(Location.findPointToStay(_teleportLoc, 10, 100, p.getGeoIndex()));
			}

			_teleportTask = null;
		}
	}

	private Future<?> _teleportTask = null;
	private Location _teleportLoc;

	public CastleMassTeleporterInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
		_teleportLoc = Location.parseLoc(template.getAIParams().getString("teleport_loc"));
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		if(_teleportTask != null)
		{
			showChatWindow(player, "residence2/castle/CastleTeleportDelayed.htm", false);
			return;
		}

		_teleportTask = ThreadPoolManager.getInstance().schedule(new TeleportTask(), isAllTowersDead() ? 480000L : 30000L);

		showChatWindow(player, "residence2/castle/CastleTeleportDelayed.htm", false);
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		if(_teleportTask != null)
		{
			showChatWindow(player, "residence2/castle/CastleTeleportDelayed.htm", firstTalk);
		}
		else
		{
			if(isAllTowersDead())
			{
				showChatWindow(player, "residence2/castle/gludio_mass_teleporter002.htm", firstTalk);
			}
			else
			{
				showChatWindow(player, "residence2/castle/gludio_mass_teleporter001.htm", firstTalk);
			}
		}
	}

	private boolean isAllTowersDead()
	{
		SiegeEvent siegeEvent = getEvent(SiegeEvent.class);
		if(siegeEvent == null || !siegeEvent.isInProgress())
		{
			return false;
		}

		List<SiegeToggleNpcObject> towers = siegeEvent.getObjects(CastleSiegeEvent.CONTROL_TOWERS);
		for(SiegeToggleNpcObject t : towers)
		{
			if(t.isAlive())
			{
				return false;
			}
		}

		return true;
	}
}
