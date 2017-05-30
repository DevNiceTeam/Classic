package handler.dailymissions;

import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.actor.instances.player.DailyMission;
import studio.lineage2.gameserver.templates.dailymissions.DailyMissionStatus;
import studio.lineage2.gameserver.templates.dailymissions.DailyMissionTemplate;

import java.util.Calendar;

/**
 * @author Bonux
 **/
public class WeekendDailyMissionHandler extends ScriptDailyMissionHandler
{
	@Override
	public DailyMissionStatus getStatus(Player player, DailyMission mission, DailyMissionTemplate missionTemplate)
	{
		if(mission != null && mission.isCompleted())
		{
			return DailyMissionStatus.COMPLETED;
		}

		Calendar currentCalendar = Calendar.getInstance();
		if(currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
		{
			return DailyMissionStatus.AVAILABLE;
		}

		return DailyMissionStatus.NOT_AVAILABLE;
	}
}
