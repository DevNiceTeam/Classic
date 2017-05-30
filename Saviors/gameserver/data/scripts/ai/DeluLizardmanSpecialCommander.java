package ai;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ai.CtrlEvent;
import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Skill;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.utils.Functions;

import java.util.List;

/**
 * @author pchayka
 */
public class DeluLizardmanSpecialCommander extends Fighter
{
	private boolean _shouted = false;

	public DeluLizardmanSpecialCommander(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtSpawn()
	{
		_shouted = false;
		super.onEvtSpawn();
	}

	@Override
	protected void onEvtAttacked(Creature attacker, Skill skill, int damage)
	{
		NpcInstance actor = getActor();

		if(Rnd.chance(40) && !_shouted)
		{
			_shouted = true;
			Functions.npcSay(actor, "Come on my fellows, assist me here!");

			List<NpcInstance> around = actor.getAroundNpc(1000, 300);
			if(around != null && !around.isEmpty())
			{
				for(NpcInstance npc : around)
				{
					if(npc.isMonster())
					{
						npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 5000);
					}
				}
			}
		}
		super.onEvtAttacked(attacker, skill, damage);
	}
}