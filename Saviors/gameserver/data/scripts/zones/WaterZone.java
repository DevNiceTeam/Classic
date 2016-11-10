package zones;

import l2s.commons.util.Rnd;
import l2s.gameserver.listener.script.OnInitScriptListener;
import l2s.gameserver.listener.zone.OnZoneEnterLeaveListener;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Zone;
import l2s.gameserver.model.Zone.ZoneType;
import l2s.gameserver.skills.AbnormalEffect;
import l2s.gameserver.utils.ReflectionUtils;

public class WaterZone implements OnInitScriptListener
{
	private static final AbnormalEffect[] _ae = new AbnormalEffect[]
	{ AbnormalEffect.SHAPE_BIKINI_A, AbnormalEffect.SHAPE_BIKINI_B, AbnormalEffect.SHAPE_BIKINI_C };

	private static ZoneListener _zoneListener;

	@Override
	public void onInit()
	{
		_zoneListener = new ZoneListener();

		for(Zone zone : ReflectionUtils.getZonesByType(ZoneType.water))
			zone.addListener(_zoneListener);
	}

	public class ZoneListener implements OnZoneEnterLeaveListener
	{
		@Override
		public void onZoneEnter(Zone zone, Creature cha)
		{
			if(!cha.isPlayer())
				return;

			cha.startAbnormalEffect(_ae[Rnd.get(_ae.length)]);
		}

		@Override
		public void onZoneLeave(Zone zone, Creature cha)
		{
			if(!cha.isPlayer())
				return;

			for(AbnormalEffect ae : _ae)
				cha.stopAbnormalEffect(ae);
		}
	}
}