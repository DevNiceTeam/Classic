package ai.orcbarracs;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.network.l2.s2c.ExShowScreenMessage;
import studio.lineage2.gameserver.network.l2.s2c.ExShowScreenMessage.ScreenMessageAlign;
import studio.lineage2.gameserver.utils.NpcUtils;

public class OrcBarracsF extends Fighter
{
	public OrcBarracsF(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		if(Rnd.chance(80))
		{
			NpcUtils.spawnSingle(23422, getActor().getLoc(), getActor().getReflection());
			killer.sendPacket(new ExShowScreenMessage(NpcString.A_POWERFUL_MONSTER_HAS_COME_TO_FACE_YOU, 5000, ScreenMessageAlign.TOP_CENTER, false));
		}
		super.onEvtDead(killer);
	}


}