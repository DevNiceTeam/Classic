package handler.dailymissions;

import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.listener.CharListener;
import studio.lineage2.gameserver.listener.actor.player.OnLevelChangeListener;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.actor.instances.player.DailyMission;
import studio.lineage2.gameserver.network.l2.s2c.ExOneDayReceiveRewardList;
import studio.lineage2.gameserver.templates.dailymissions.DailyMissionStatus;
import studio.lineage2.gameserver.templates.dailymissions.DailyMissionTemplate;

import java.util.Collection;

/**
 * @author Bonux
 **/
public class LevelUpDailyMissionHandler extends ScriptDailyMissionHandler
{
	private class HandlerListeners implements OnLevelChangeListener
	{
		@Override
		public void onLevelChange(Player player, int oldLvl, int newLvl)
		{
			if(!Config.EX_USE_TO_DO_LIST)
			{
				return;
			}

			Collection<DailyMissionTemplate> missionTemplates = player.getDailyMissionList().getAvailableMissions();
			for(DailyMissionTemplate missionTemplate : missionTemplates)
			{
				if(missionTemplate.getHandler() != LevelUpDailyMissionHandler.this)
				{
					continue;
				}

				if(player.getDailyMissionList().getStatus(missionTemplate) != DailyMissionStatus.AVAILABLE)
				{
					continue;
				}

				int requiredLevel = missionTemplate.getValue();
				if(oldLvl > newLvl && (oldLvl < requiredLevel || newLvl >= requiredLevel))
				{
					continue;
				}

				if(oldLvl < newLvl && (oldLvl >= requiredLevel || newLvl < requiredLevel))
				{
					continue;
				}

				player.sendPacket(new ExOneDayReceiveRewardList(player));
				break;
			}
		}
	}

	private final HandlerListeners _handlerListeners = new HandlerListeners();

	@Override
	public CharListener getListener()
	{
		return _handlerListeners;
	}

	@Override
	public DailyMissionStatus getStatus(Player player, DailyMission mission, DailyMissionTemplate missionTemplate)
	{
		if(mission != null && mission.isCompleted())
		{
			return DailyMissionStatus.COMPLETED;
		}
		if(player.getBaseSubClass().getLevel() >= missionTemplate.getValue())
		{
			return DailyMissionStatus.AVAILABLE;
		}
		return DailyMissionStatus.NOT_AVAILABLE;
	}

	@Override
	public boolean isReusable()
	{
		return false;
	}
}
