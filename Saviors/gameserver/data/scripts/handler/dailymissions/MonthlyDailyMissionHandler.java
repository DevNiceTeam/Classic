package handler.dailymissions;

import java.util.Calendar;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.actor.instances.player.DailyMission;
import l2s.gameserver.templates.dailymissions.DailyMissionStatus;
import l2s.gameserver.templates.dailymissions.DailyMissionTemplate;

public class MonthlyDailyMissionHandler extends ScriptDailyMissionHandler
{
	@Override
	public DailyMissionStatus getStatus(Player player, DailyMission mission, DailyMissionTemplate missionTemplate)
	{
		if(mission != null && mission.isCompleted())
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(mission.getValue() * 1000L);
			if(Calendar.getInstance().get(Calendar.MONTH) <= calendar.get(Calendar.MONTH))
				return DailyMissionStatus.COMPLETED;
		}
		return DailyMissionStatus.AVAILABLE;
	}

	@Override
	public boolean isReusable()
	{
		return false;
	}
}
