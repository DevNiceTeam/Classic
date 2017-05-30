package ai;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ThreadPoolManager;
import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.data.xml.holder.SkillHolder;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.entity.Reflection;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.s2c.PlaySoundPacket;
import studio.lineage2.gameserver.utils.Location;

/**
 * Ночной Закен. Уточнить поведение
 *
 * @author pchayka
 */
public class ZakenNightly extends Fighter
{
	private static final int doll_blader_b = 29023;
	private static final int vale_master_b = 29024;
	private static final int pirates_zombie_captain_b = 29026;
	private static final int pirates_zombie_b = 29027;

	private static final Location[] _locations = new Location[] {
			new Location(55272, 219112, -3496),
			new Location(56296, 218072, -3496),
			new Location(54232, 218072, -3496),
			new Location(54248, 220136, -3496),
			new Location(56296, 220136, -3496),
			new Location(55272, 219112, -3224),
			new Location(56296, 218072, -3224),
			new Location(54232, 218072, -3224),
			new Location(54248, 220136, -3224),
			new Location(56296, 220136, -3224),
			new Location(55272, 219112, -2952),
			new Location(56296, 218072, -2952),
			new Location(54232, 218072, -2952),
			new Location(54248, 220136, -2952),
			new Location(56296, 220136, -2952)
	};

	private long _teleportSelfTimer = 0L;
	private long _teleportSelfReuse = 30000L;          // 30 secs
	private NpcInstance actor = getActor();
	private int _stage = 0;

	public ZakenNightly(NpcInstance actor)
	{
		super(actor);
		setMaxPursueRange(Integer.MAX_VALUE / 2);
	}

	@Override
	protected void thinkAttack()
	{
		if(_teleportSelfTimer + _teleportSelfReuse < System.currentTimeMillis())
		{
			_teleportSelfTimer = System.currentTimeMillis();
			if(Rnd.chance(20))
			{
				actor.doCast(SkillHolder.getInstance().getSkillEntry(4222, 1), actor, false);
				ThreadPoolManager.getInstance().schedule(() -> {
					actor.teleToLocation(_locations[Rnd.get(_locations.length)]);
					actor.getAggroList().clear(true);
				}, 500);
			}
		}

		double actor_hp_precent = actor.getCurrentHpPercents();
		Reflection r = actor.getReflection();
		switch(_stage)
		{
			case 0:
				if(actor_hp_precent < 90)
				{
					r.addSpawnWithoutRespawn(pirates_zombie_captain_b, actor.getLoc(), 300);
					_stage++;
				}
				break;
			case 1:
				if(actor_hp_precent < 80)
				{
					r.addSpawnWithoutRespawn(doll_blader_b, actor.getLoc(), 300);
					_stage++;
				}
				break;
			case 2:
				if(actor_hp_precent < 70)
				{
					r.addSpawnWithoutRespawn(vale_master_b, actor.getLoc(), 300);
					r.addSpawnWithoutRespawn(vale_master_b, actor.getLoc(), 300);
					_stage++;
				}
				break;
			case 3:
				if(actor_hp_precent < 60)
				{
					for(int i = 0; i < 5; i++)
					{
						r.addSpawnWithoutRespawn(pirates_zombie_b, actor.getLoc(), 300);
					}
					_stage++;
				}
				break;
			case 4:
				if(actor_hp_precent < 50)
				{
					for(int i = 0; i < 5; i++)
					{
						r.addSpawnWithoutRespawn(doll_blader_b, actor.getLoc(), 300);
						r.addSpawnWithoutRespawn(pirates_zombie_b, actor.getLoc(), 300);
						r.addSpawnWithoutRespawn(vale_master_b, actor.getLoc(), 300);
						r.addSpawnWithoutRespawn(pirates_zombie_captain_b, actor.getLoc(), 300);
					}
					_stage++;
				}
				break;
			case 5:
				if(actor_hp_precent < 40)
				{
					for(int i = 0; i < 6; i++)
					{
						r.addSpawnWithoutRespawn(doll_blader_b, actor.getLoc(), 300);
						r.addSpawnWithoutRespawn(pirates_zombie_b, actor.getLoc(), 300);
						r.addSpawnWithoutRespawn(vale_master_b, actor.getLoc(), 300);
						r.addSpawnWithoutRespawn(pirates_zombie_captain_b, actor.getLoc(), 300);
					}
					_stage++;
				}
				break;
			case 6:
				if(actor_hp_precent < 30)
				{
					for(int i = 0; i < 7; i++)
					{
						r.addSpawnWithoutRespawn(doll_blader_b, actor.getLoc(), 300);
						r.addSpawnWithoutRespawn(pirates_zombie_b, actor.getLoc(), 300);
						r.addSpawnWithoutRespawn(vale_master_b, actor.getLoc(), 300);
						r.addSpawnWithoutRespawn(pirates_zombie_captain_b, actor.getLoc(), 300);
					}
					_stage++;
				}
				break;
			default:
				break;
		}
		super.thinkAttack();
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		Reflection r = actor.getReflection();
		r.setReenterTime(System.currentTimeMillis());
		actor.broadcastPacket(new PlaySoundPacket(PlaySoundPacket.Type.MUSIC, "BS02_D", 1, actor.getObjectId(), actor.getLoc()));
		super.onEvtDead(killer);
	}

	@Override
	protected void teleportHome()
	{
		return;
	}
}