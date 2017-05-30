package zones;

import studio.lineage2.gameserver.listener.script.OnInitScriptListener;
import studio.lineage2.gameserver.listener.zone.OnZoneEnterLeaveListener;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Zone;
import studio.lineage2.gameserver.network.l2.components.CustomMessage;
import studio.lineage2.gameserver.utils.Location;
import studio.lineage2.gameserver.utils.ReflectionUtils;

public class EpicZone implements OnInitScriptListener
{
	private static ZoneListener _zoneListener;

	@Override
	public void onInit()
	{
		_zoneListener = new ZoneListener();
		Zone zone = ReflectionUtils.getZone("[queen_ant_epic]");
		zone.addListener(_zoneListener);
	}

	public class ZoneListener implements OnZoneEnterLeaveListener
	{
		@Override
		public void onZoneEnter(Zone zone, Creature cha)
		{
			if(zone.getParams() == null || !cha.isPlayable() || cha.getPlayer().isGM())
			{
				return;
			}
			if(cha.getLevel() > zone.getParams().getInteger("levelLimit"))
			{
				if(cha.isPlayer())
				{
					cha.getPlayer().sendMessage(new CustomMessage("scripts.zones.epic.banishMsg"));
				}
				cha.teleToLocation(Location.parseLoc(zone.getParams().getString("tele")));
			}
		}

		@Override
		public void onZoneLeave(Zone zone, Creature cha)
		{
			//
		}
	}
}
