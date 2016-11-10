package ai;

import l2s.gameserver.ai.Fighter;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.network.l2.s2c.MagicSkillUse;
import l2s.gameserver.utils.NpcUtils;

/*Created Nice Team*/
//by DXVSI

public class SpawnCaveBanshee extends Fighter
{
	private static final int banshee = 20412;

	public SpawnCaveBanshee(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		NpcInstance actor = getActor();

		actor.broadcastPacket(new MagicSkillUse(actor, 2046, 1, 1000, 0));

		NpcUtils.spawnSingleRandom(banshee, actor.getSpawnedLoc(), 25);	

		super.onEvtDead(killer);
	}
}
