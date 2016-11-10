package handler.dailymissions;

import java.util.Collection;

import l2s.gameserver.Config;
import l2s.gameserver.listener.CharListener;
import l2s.gameserver.listener.actor.player.OnLevelChangeListener;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.actor.instances.player.DailyMission;
import l2s.gameserver.network.l2.s2c.ExOneDayReceiveRewardList;
import l2s.gameserver.templates.dailymissions.DailyMissionStatus;
import l2s.gameserver.templates.dailymissions.DailyMissionTemplate;

public class LevelUpDailyMissionHandler extends ScriptDailyMissionHandler
{
	private class HandlerListeners implements OnLevelChangeListener
	{
		@Override
		public void onLevelChange(Player player, int oldLvl, int newLvl)
		{
			if(!Config.EX_USE_TO_DO_LIST)
				return;

			Collection<DailyMissionTemplate> missionTemplates = player.getDailyMissionList().getAvailableMissions();
			for(DailyMissionTemplate missionTemplate : missionTemplates)
			{
				if(missionTemplate.getHandler() != LevelUpDailyMissionHandler.this)
					continue;

				if(player.getDailyMissionList().getStatus(missionTemplate) != DailyMissionStatus.AVAILABLE)
					continue;

				int requiredLevel = missionTemplate.getValue();
				if(oldLvl > newLvl && (oldLvl < requiredLevel || newLvl >= requiredLevel))
					continue;

				if(oldLvl < newLvl && (oldLvl >= requiredLevel || newLvl < requiredLevel))
					continue;

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
			return DailyMissionStatus.COMPLETED;
		if(player.getBaseSubClass().getLevel() >= missionTemplate.getValue())
			return DailyMissionStatus.AVAILABLE;
		return DailyMissionStatus.NOT_AVAILABLE;
	}

	@Override
	public boolean isReusable()
	{
		return false;
	}
}
