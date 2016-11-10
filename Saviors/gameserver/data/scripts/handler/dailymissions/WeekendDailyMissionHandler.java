package handler.dailymissions;

import java.util.Calendar;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.actor.instances.player.DailyMission;
import l2s.gameserver.templates.dailymissions.DailyMissionStatus;
import l2s.gameserver.templates.dailymissions.DailyMissionTemplate;

public class WeekendDailyMissionHandler extends ScriptDailyMissionHandler
{
	@Override
	public DailyMissionStatus getStatus(Player player, DailyMission mission, DailyMissionTemplate missionTemplate)
	{
		if(mission != null && mission.isCompleted())
			return DailyMissionStatus.COMPLETED;

		Calendar currentCalendar = Calendar.getInstance();
		if(currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return DailyMissionStatus.AVAILABLE;

		return DailyMissionStatus.NOT_AVAILABLE;
	}
}
