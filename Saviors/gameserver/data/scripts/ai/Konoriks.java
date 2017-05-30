package ai;

import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.model.instances.NpcInstance;

/**
 * Created by Averen on 15.04.2017.
 */
public class Konoriks extends Fighter
{
    public Konoriks(NpcInstance actor)
    {
        super(actor);
    }

    @Override
    protected boolean thinkActive()
    {
        NpcInstance actor = getActor();
        if(actor.isDead())
            return true;

        return super.thinkActive();
    }

    @Override
    protected boolean randomWalk()
    {
        return false;
    }
}
