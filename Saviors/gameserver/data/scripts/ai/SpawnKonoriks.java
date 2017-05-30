package ai;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ai.CtrlEvent;
import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.s2c.MagicSkillUse;

/**
 * Created by Averen on 15.04.2017.
 */
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

        //TODO [averen] спиздить метод
        //NpcUtils.spawnSingleRandom(knoriks, actor.getSpawnedLoc(), 25);

        if(killer != null)
        {
            actor.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, killer, Rnd.get(1, 100));
        }

        super.onEvtDead(killer);
    }
}
