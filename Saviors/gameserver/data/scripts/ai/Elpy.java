package ai;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.geodata.GeoEngine;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Skill;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.utils.Location;

/**
 * Created by Averen on 15.04.2017.
 */
public class Elpy extends Fighter
{
    public Elpy(NpcInstance actor)
    {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, Skill skill, int damage)
    {
        NpcInstance actor = getActor();
        if(attacker != null && Rnd.chance(50))
        {
            Location pos = Location.findPointToStay(actor, 150, 200);
            if(GeoEngine.canMoveToCoord(actor.getX(), actor.getY(), actor.getZ(), pos.x, pos.y, pos.z, actor.getGeoIndex()))
            {
                actor.setRunning();
                addTaskMove(pos, false);
            }
        }
    }

    @Override
    public boolean checkAggression(Creature target)
    {
        return false;
    }

    @Override
    protected void onEvtAggression(Creature target, int aggro)
    {

    }
}