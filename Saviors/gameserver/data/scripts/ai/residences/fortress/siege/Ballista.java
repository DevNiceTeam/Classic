package ai.residences.fortress.siege;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ai.DefaultAI;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.Skill;
import studio.lineage2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import studio.lineage2.gameserver.model.entity.events.impl.SiegeEvent;
import studio.lineage2.gameserver.model.instances.NpcInstance;

/**
 * Данный AI используется NPC Ballista на осадах фортов
 * - может быть уничтожена с использованием Ballista Bomb(1-5 штук)
 * - не использует random walk
 * - не отвечает на атаки
 *
 * @author SYS
 */
public class Ballista extends DefaultAI
{
	private static final int BALLISTA_BOMB_SKILL_ID = 2342;
	private int _bombsUseCounter;

	public Ballista(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtSeeSpell(Skill skill, Creature caster)
	{
		super.onEvtSeeSpell(skill, caster);

		NpcInstance actor = getActor();
		if(caster == null || skill.getId() != BALLISTA_BOMB_SKILL_ID)
		{
			return;
		}

		Player player = caster.getPlayer();
		FortressSiegeEvent siege = actor.getEvent(FortressSiegeEvent.class);
		FortressSiegeEvent siege2 = player.getEvent(FortressSiegeEvent.class);
		if(siege == null || siege != siege2 || siege.getSiegeClan(SiegeEvent.ATTACKERS, player.getClan()) == null)
		{
			return;
		}

		_bombsUseCounter++;
		if(Rnd.chance(20) || _bombsUseCounter > 4)
		{
			actor.doDie(caster);
		}
	}

	@Override
	protected boolean randomWalk()
	{
		return false;
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		_bombsUseCounter = 0;
		super.onEvtDead(killer);
	}
}