package handler.dailymissions;

import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.actor.instances.player.DailyMission;
import studio.lineage2.gameserver.templates.dailymissions.DailyMissionStatus;
import studio.lineage2.gameserver.templates.dailymissions.DailyMissionTemplate;

import java.util.Calendar;

/**
 * @author Bonux
 **/
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
			{
				return DailyMissionStatus.COMPLETED;
			}
		}
		return DailyMissionStatus.AVAILABLE;
	}

	@Override
	public boolean isReusable()
	{
		return false;
	}
}
