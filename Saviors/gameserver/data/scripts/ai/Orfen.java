package ai;

import studio.lineage2.commons.text.PrintfFormat;
import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Skill;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.utils.Functions;
import studio.lineage2.gameserver.utils.Location;
import npc.model.OrfenInstance;

public class Orfen extends Fighter
{
	public static final PrintfFormat[] MsgOnRecall = {
			new PrintfFormat("%s. пойми, тебе со мной не совладать!"),
			new PrintfFormat("%s. ты узнаешь, что значит настоящий ужас!"),
			new PrintfFormat("Глупо! Как ты смеешь бросать мне вызов, %s! Берегись!"),
			new PrintfFormat("%s. Ты думаешь, у тебя что-то получится?!")
	};

	public final Skill[] _paralyze;

	public Orfen(NpcInstance actor)
	{
		super(actor);
		_paralyze = getActor().getTemplate().getDebuffSkills();
	}

	@Override
	protected boolean thinkActive()
	{
		if(super.thinkActive())
		{
			return true;
		}
		OrfenInstance actor = getActor();

		if(actor.isTeleported() && actor.getCurrentHpPercents() > 95)
		{
			actor.setTeleported(false);
			return true;
		}

		return false;
	}

	@Override
	protected boolean createNewTask()
	{
		return defaultNewTask();
	}

	@Override
	protected void onEvtAttacked(Creature attacker, Skill skill, int damage)
	{
		super.onEvtAttacked(attacker, skill, damage);
		OrfenInstance actor = getActor();
		if(actor.isCastingNow())
		{
			return;
		}

		double distance = actor.getDistance(attacker);

		// if(attacker.isMuted() &&)
		if(distance > 300 && distance < 1000 && _damSkills.length > 0 && Rnd.chance(10))
		{
			Functions.npcSay(actor, MsgOnRecall[Rnd.get(MsgOnRecall.length - 1)].sprintf(attacker.getName()));
			teleToLocation(attacker, Location.findFrontPosition(actor, attacker, 0, 50));
			Skill r_skill = _damSkills[Rnd.get(_damSkills.length)];
			if(canUseSkill(r_skill, attacker, -1))
			{
				addTaskAttack(attacker, r_skill, 1000000);
			}
		}
		else if(_paralyze.length > 0 && Rnd.chance(20))
		{
			Skill r_skill = _paralyze[Rnd.get(_paralyze.length)];
			if(canUseSkill(r_skill, attacker, -1))
			{
				addTaskAttack(attacker, r_skill, 1000000);
			}
		}
	}

	@Override
	protected void onEvtSeeSpell(Skill skill, Creature caster)
	{
		super.onEvtSeeSpell(skill, caster);
		OrfenInstance actor = getActor();
		if(actor.isCastingNow())
		{
			return;
		}

		double distance = actor.getDistance(caster);
		if(_damSkills.length > 0 && skill.getEffectPoint() > 0 && distance < 1000 && Rnd.chance(20))
		{
			Functions.npcSay(actor, MsgOnRecall[Rnd.get(MsgOnRecall.length)].sprintf(caster.getName()));
			teleToLocation(caster, Location.findFrontPosition(actor, caster, 0, 50));
			Skill r_skill = _damSkills[Rnd.get(_damSkills.length)];
			if(canUseSkill(r_skill, caster, -1))
			{
				addTaskAttack(caster, r_skill, 1000000);
			}
		}
	}

	@Override
	public OrfenInstance getActor()
	{
		return (OrfenInstance) super.getActor();
	}

	private void teleToLocation(Creature attacker, Location loc)
	{
		attacker.teleToLocation(loc);
	}
}