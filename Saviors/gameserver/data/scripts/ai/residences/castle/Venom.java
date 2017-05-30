package ai.residences.castle;

import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.utils.Functions;
import studio.lineage2.gameserver.utils.NpcUtils;

/**
 * @author VISTALL
 * @date 22:01/23.05.2011
 * 29054
 */
public class Venom extends Fighter
{
	public Venom(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtSpawn()
	{
		super.onEvtSpawn();

		Functions.npcShout(getActor(), NpcString.WHO_DARES_TO_COVET_THE_THRONE_OF_OUR_CASTLE__LEAVE_IMMEDIATELY_OR_YOU_WILL_PAY_THE_PRICE_OF_YOUR_AUDACITY_WITH_YOUR_VERY_OWN_BLOOD);
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		super.onEvtDead(killer);

		Functions.npcShout(getActor(), NpcString.ITS_NOT_OVER_YET__IT_WONT_BE__OVER__LIKE_THIS__NEVER);

		NpcUtils.spawnSingle(29055, 12589, -49044, -3008, 120000);
	}
}
