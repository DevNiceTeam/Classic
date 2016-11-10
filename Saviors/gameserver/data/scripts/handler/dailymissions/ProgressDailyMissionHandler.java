package handler.dailymissions;

import java.util.Collection;

import l2s.gameserver.Config;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.actor.instances.player.DailyMission;
import l2s.gameserver.network.l2.s2c.ExOneDayReceiveRewardList;
import l2s.gameserver.templates.dailymissions.DailyMissionStatus;
import l2s.gameserver.templates.dailymissions.DailyMissionTemplate;

public abstract class ProgressDailyMissionHandler extends ScriptDailyMissionHandler
{
	@Override
	public boolean haveProgress(DailyMissionTemplate missionTemplate)
	{
		return missionTemplate.getValue() > 1;
	}

	@Override
	public DailyMissionStatus getStatus(Player player, DailyMission mission, DailyMissionTemplate missionTemplate)
	{
		if(mission != null)
		{
			if(mission.isCompleted())
				return DailyMissionStatus.COMPLETED;
			if(mission.getValue() >= missionTemplate.getValue())
				return DailyMissionStatus.AVAILABLE;
		}
		return DailyMissionStatus.NOT_AVAILABLE;
	}

	protected void progressMission(Player player, int addValue)
	{
		if(!Config.EX_USE_TO_DO_LIST)
			return;

		boolean update = false;

		Collection<DailyMissionTemplate> missionTemplates = player.getDailyMissionList().getAvailableMissions();
		for(DailyMissionTemplate missionTemplate : missionTemplates)
		{
			if(missionTemplate.getHandler() != this)
				continue;

			DailyMission mission = player.getDailyMissionList().get(missionTemplate, true);
			if(mission.isCompleted())
				continue;

			mission.setValue(mission.getValue() + addValue);
			update = true;
		}

		if(update)
			player.sendPacket(new ExOneDayReceiveRewardList(player));
	}
}
