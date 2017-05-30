package ai.residences;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.ai.CtrlIntention;
import studio.lineage2.gameserver.ai.DefaultAI;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Playable;
import studio.lineage2.gameserver.model.World;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.utils.Location;

import java.util.List;


public abstract class SiegeGuard extends DefaultAI
{
	public SiegeGuard(NpcInstance actor)
	{
		super(actor);
		setMaxPursueRange(1000);
	}

	@Override
	public int getMaxPathfindFails()
	{
		return Integer.MAX_VALUE;
	}

	@Override
	public int getMaxAttackTimeout()
	{
		return 0;
	}

	@Override
	protected boolean randomWalk()
	{
		return false;
	}

	@Override
	protected boolean randomAnimation()
	{
		return false;
	}

	@Override
	public boolean canSeeInSilentMove(Playable target)
	{
		// Осадные гварды могут видеть игроков в режиме Silent Move с вероятностью 10%
		return !target.isSilentMoving() || Rnd.chance(10);
	}

	@Override
	protected boolean isAggressive()
	{
		return true;
	}

	@Override
	protected boolean isGlobalAggro()
	{
		return true;
	}

	@Override
	protected void onEvtAggression(Creature target, int aggro)
	{
		NpcInstance actor = getActor();
		if(actor.isDead())
		{
			return;
		}
		if(target == null || !actor.isAutoAttackable(target))
		{
			return;
		}
		super.onEvtAggression(target, aggro);
	}

	@Override
	protected boolean thinkActive()
	{
		NpcInstance actor = getActor();
		if(actor.isActionsDisabled())
		{
			return true;
		}

		if(_def_think)
		{
			if(doTask())
			{
				clearTasks();
			}
			return true;
		}

		long now = System.currentTimeMillis();
		if(now - _checkAggroTimestamp > Config.AGGRO_CHECK_INTERVAL)
		{
			_checkAggroTimestamp = now;

			List<Creature> targets = World.getAroundCharacters(actor);
			while(!targets.isEmpty())
			{
				Creature target = getNearestTarget(targets);
				if(target == null)
				{
					break;
				}

				if(checkAggression(target))
				{
					actor.getAggroList().addDamageHate(target, 0, 2);

					if(target.isServitor())
					{
						actor.getAggroList().addDamageHate(target.getPlayer(), 0, 1);
					}

					startRunningTask(AI_TASK_ATTACK_DELAY);
					setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);

					return true;
				}

				targets.remove(target);
			}
		}

		Location sloc = actor.getSpawnedLoc();
		// Проверка на расстояние до точки спауна
		if(!actor.isInRange(sloc, 250))
		{
			teleportHome();
			return true;
		}

		return false;
	}

	@Override
	protected Creature prepareTarget()
	{
		NpcInstance actor = getActor();
		if(actor.isDead())
		{
			return null;
		}

		// Новая цель исходя из агрессивности
		List<Creature> hateList = actor.getAggroList().getHateList(getMaxPursueRange());
		Creature hated = null;
		for(Creature cha : hateList)
		{
			//Не подходит, очищаем хейт
			if(!checkTarget(cha, getMaxPursueRange()))
			{
				actor.getAggroList().remove(cha, true);
				continue;
			}
			hated = cha;
			break;
		}

		if(hated != null)
		{
			setAttackTarget(hated);
			return hated;
		}

		return null;
	}

	@Override
	protected boolean canAttackCharacter(Creature target)
	{
		return getActor().isAutoAttackable(target);
	}
}