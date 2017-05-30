package ai.residences;

import studio.lineage2.gameserver.model.instances.NpcInstance;

public class SiegeGuardFighter extends SiegeGuard
{
	public SiegeGuardFighter(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected boolean createNewTask()
	{
		return defaultFightTask();
	}

	@Override
	public int getRatePHYS()
	{
		return getActor().getTemplate().getChanceSkillPhys() > 0 ? getActor().getTemplate().getChanceSkillPhys() : 25;
	}

	@Override
	public int getRateDOT()
	{
		return getActor().getTemplate().getChanceSkillDot() > 0 ? getActor().getTemplate().getChanceSkillDot() : 50;
	}

	@Override
	public int getRateDEBUFF()
	{
		return getActor().getTemplate().getChanceSkillDebuff() > 0 ? getActor().getTemplate().getChanceSkillDebuff() : 50;
	}

	@Override
	public int getRateDAM()
	{
		return getActor().getTemplate().getChanceSkillDam() > 0 ? getActor().getTemplate().getChanceSkillDam() : 75;
	}

	@Override
	public int getRateSTUN()
	{
		return getActor().getTemplate().getChanceSkillStun() > 0 ? getActor().getTemplate().getChanceSkillStun() : 50;
	}

	@Override
	public int getRateBUFF()
	{
		return getActor().getTemplate().getChanceSkillBuff() > 0 ? getActor().getTemplate().getChanceSkillBuff() : 10;
	}

	@Override
	public int getRateHEAL()
	{
		return getActor().getTemplate().getChanceSkillHeal() > 0 ? getActor().getTemplate().getChanceSkillHeal() : 50;
	}
}