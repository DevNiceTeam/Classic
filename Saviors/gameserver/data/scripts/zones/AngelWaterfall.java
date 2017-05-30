package zones;

import studio.lineage2.gameserver.listener.script.OnInitScriptListener;
import studio.lineage2.gameserver.listener.zone.OnZoneEnterLeaveListener;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.Zone;
import studio.lineage2.gameserver.model.quest.QuestState;
import studio.lineage2.gameserver.utils.Location;
import studio.lineage2.gameserver.utils.ReflectionUtils;

public class AngelWaterfall implements OnInitScriptListener
{
	private static final String TELEPORT_ZONE_NAME = "[25_20_telzone_to_magmeld]";
	private static final Location TELEPORT_LOC = new Location(207559, 86429, -1000);

	private static ZoneListener _zoneListener;

	@Override
	public void onInit()
	{
		_zoneListener = new ZoneListener();
		Zone zone = ReflectionUtils.getZone(TELEPORT_ZONE_NAME);
		zone.addListener(_zoneListener);
	}

	public class ZoneListener implements OnZoneEnterLeaveListener
	{
		@Override
		public void onZoneEnter(Zone zone, Creature cha)
		{
			if(zone == null)
			{
				return;
			}

			if(cha == null)
			{
				return;
			}
			Player player = cha.getPlayer();
			if(player == null)
			{
				return;
			}

			QuestState qs = player.getQuestState(10301);
			if(qs != null && qs.getCond() == 3 && player.getVar("instance10301") == null)
			{
				player.processQuestEvent(qs.getQuest().getId(), "enterInstance", null);
				//player.setVar("instance10301", "true", -1);
			}
			else
			{
				cha.teleToLocation(TELEPORT_LOC);
			}
		}

		@Override
		public void onZoneLeave(Zone zone, Creature cha)
		{}
	}
}