package ai;

import l2s.commons.util.Rnd;
import l2s.gameserver.ai.CtrlEvent;
import l2s.gameserver.ai.Fighter;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.network.l2.s2c.MagicSkillUse;
import l2s.gameserver.utils.NpcUtils;

/*Created Nice Team*/
//by DXVSI

public class SpawnKonoriks extends Fighter
{
	private static final int knoriks = 20405;

	public SpawnKonoriks(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		NpcInstance actor = getActor();

		actor.broadcastPacket(new MagicSkillUse(actor, 2046, 1, 1000, 0));

		NpcUtils.spawnSingleRandom(knoriks, actor.getSpawnedLoc(), 25);

		if(killer != null)
		{
			actor.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, killer, Rnd.get(1, 100));
		}

		super.onEvtDead(killer);
	}
}
