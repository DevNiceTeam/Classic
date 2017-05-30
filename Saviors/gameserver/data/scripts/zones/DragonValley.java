package zones;

import studio.lineage2.commons.threading.RunnableImpl;
import studio.lineage2.gameserver.ThreadPoolManager;
import studio.lineage2.gameserver.data.xml.holder.SkillHolder;
import studio.lineage2.gameserver.listener.script.OnInitScriptListener;
import studio.lineage2.gameserver.listener.zone.OnZoneEnterLeaveListener;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Party;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.Zone;
import studio.lineage2.gameserver.model.base.ClassId;
import studio.lineage2.gameserver.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author pchayka
 */

public class DragonValley implements OnInitScriptListener
{
	private static ZoneListener _zoneListener;
	private static Zone zone;
	private static final Map<ClassId, Double> weight = new HashMap<ClassId, Double>();
	private static List<Player> inzone = new ArrayList<Player>();
	private static ScheduledFuture<?> buffTask;

	static
	{
		weight.put(ClassId.DUELIST, 0.2);
		weight.put(ClassId.DREADNOUGHT, 0.7);
		weight.put(ClassId.PHOENIX_KNIGHT, 0.5);
		weight.put(ClassId.HELL_KNIGHT, 0.5);
		weight.put(ClassId.SAGITTARIUS, 0.3);
		weight.put(ClassId.ADVENTURER, 0.4);
		weight.put(ClassId.ARCHMAGE, 0.3);
		weight.put(ClassId.SOULTAKER, 0.3);
		weight.put(ClassId.ARCANA_LORD, 1.);
		weight.put(ClassId.CARDINAL, -0.6);
		weight.put(ClassId.HIEROPHANT, 0.);
		weight.put(ClassId.EVAS_TEMPLAR, 0.8);
		weight.put(ClassId.SWORD_MUSE, 0.5);
		weight.put(ClassId.WIND_RIDER, 0.4);
		weight.put(ClassId.MOONLIGHT_SENTINEL, 0.3);
		weight.put(ClassId.MYSTIC_MUSE, 0.3);
		weight.put(ClassId.ELEMENTAL_MASTER, 1.);
		weight.put(ClassId.EVAS_SAINT, -0.6);
		weight.put(ClassId.SHILLIEN_TEMPLAR, 0.8);
		weight.put(ClassId.SPECTRAL_DANCER, 0.5);
		weight.put(ClassId.GHOST_HUNTER, 0.4);
		weight.put(ClassId.GHOST_SENTINEL, 0.3);
		weight.put(ClassId.STORM_SCREAMER, 0.3);
		weight.put(ClassId.SPECTRAL_MASTER, 1.);
		weight.put(ClassId.SHILLIEN_SAINT, -0.6);
		weight.put(ClassId.TITAN, 0.3);
		weight.put(ClassId.GRAND_KHAVATARI, 0.2);
		weight.put(ClassId.DOMINATOR, 0.1);
		weight.put(ClassId.DOOMCRYER, 0.1);
		weight.put(ClassId.FORTUNE_SEEKER, 0.9);
		weight.put(ClassId.MAESTRO, 0.7);
	}

	public class ZoneListener implements OnZoneEnterLeaveListener
	{
		@Override
		public void onZoneEnter(Zone zone, Creature cha)
		{
			if(cha.isPlayer())
			{
				inzone.add(cha.getPlayer());
			}
		}

		@Override
		public void onZoneLeave(Zone zone, Creature cha)
		{
			if(cha.isPlayer() && inzone.contains(cha.getPlayer()))
			{
				inzone.remove(cha.getPlayer());
			}
		}
	}

	private class BuffTask extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			for(Player izp : inzone)
			{
				if(getBuffLevel(izp) > 0)
				{
					izp.altOnMagicUse(izp, SkillHolder.getInstance().getSkill(6885, getBuffLevel(izp)));
				}
			}
		}
	}

	private int getBuffLevel(Player pc)
	{
		if(pc.getParty() == null)
		{
			return 0;
		}
		Party party = pc.getParty();
		// Small party check
		if(party.getMemberCount() < 5)    // toCheck
		{
			return 0;
		}
		// Newbie party or Not in zone member check
		for(Player p : party)
		{
			if(p.getLevel() < 80 || !p.isInZone(zone))
			{
				return 0;
			}
		}

		double points = 0;
		int count = party.getMemberCount();

		for(Player p : party)
		{
			if(weight.get(p.getClassId()) != null)
			{
				points += weight.get(p.getClassId());
			}
		}

		return (int) Math.max(0, Math.min(3, Math.round(points * getCoefficient(count))));  // Brutally custom
	}

	private double getCoefficient(int count)
	{
		double cf;
		switch(count)
		{
			case 4:
				cf = 0.7;
				break;
			case 5:
				cf = 0.75;
				break;
			case 6:
				cf = 0.8;
				break;
			case 7:
				cf = 0.85;
				break;
			case 8:
				cf = 0.9;
				break;
			case 9:
				cf = 0.95;
				break;
			default:
				cf = 1;
		}
		return cf;
	}

	@Override
	public void onInit()
	{
		_zoneListener = new ZoneListener();
		zone = ReflectionUtils.getZone("[dragon_valley]");
		zone.addListener(_zoneListener);
		buffTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new BuffTask(), 1000L, 60000L);
	}
}
