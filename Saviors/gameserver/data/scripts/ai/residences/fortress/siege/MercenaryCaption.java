package ai.residences.fortress.siege;

import studio.lineage2.gameserver.ai.Fighter;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Skill;
import studio.lineage2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import studio.lineage2.gameserver.model.entity.residence.Fortress;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.utils.Location;
import npc.model.residences.fortress.siege.MercenaryCaptionInstance;

import java.util.Collections;
import java.util.List;

/**
 * @author VISTALL
 * @date 10:58/19.04.2011
 */
public class MercenaryCaption extends Fighter
{
	private List<Location> _points = Collections.emptyList();
	private int _tick = -1;

	public MercenaryCaption(NpcInstance actor)
	{
		super(actor);
		setMaxPursueRange(100);
	}

	@Override
	protected void onEvtSpawn()
	{
		super.onEvtSpawn();
		NpcInstance actor = getActor();

		Fortress f = actor.getFortress();
		FortressSiegeEvent event = f.getSiegeEvent();

		_points = event.getObjects(FortressSiegeEvent.MERCENARY_POINTS);
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

		if(randomWalk())
		{
			return true;
		}

		return false;
	}

	@Override
	protected void onEvtArrived()
	{
		if(_tick != -1)
		{
			startMove(false);
		}
	}

	@Override
	protected void onEvtAttacked(Creature attacker, Skill skill, int damage)
	{
		_tick = -1;
		super.onEvtAttacked(attacker, skill, damage);
	}

	public void startMove(boolean init)
	{
		if(init)
		{
			_tick = 0;
		}

		if(_tick == -1)
		{
			return;
		}

		if(_tick < _points.size())
		{
			addTaskMove(_points.get(_tick++), true);
			doTask();
		}
	}

	@Override
	public MercenaryCaptionInstance getActor()
	{
		return (MercenaryCaptionInstance) super.getActor();
	}
}
