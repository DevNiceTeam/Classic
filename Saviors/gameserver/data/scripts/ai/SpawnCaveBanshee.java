package ai;

import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.s2c.MagicSkillUse;

/**
 * Created by Averen on 15.04.2017.
 */
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

        //TODO [averen] спиздить метод
        //NpcUtils.spawnSingleRandom(banshee, actor.getSpawnedLoc(), 25);

        super.onEvtDead(killer);
    }
}
