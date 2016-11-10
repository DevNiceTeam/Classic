/*package handler.dailymissions;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.actor.instances.player.DailyMission;
import l2s.gameserver.templates.dailymissions.DailyMissionStatus;
import l2s.gameserver.templates.dailymissions.DailyMissionTemplate;

public class DualclassLevelUpDailyMissionHandler extends LevelUpDailyMissionHandler
{
	@Override
	public DailyMissionStatus getStatus(Player player, DailyMission mission, DailyMissionTemplate missionTemplate)
	{
		if(mission != null && mission.isCompleted())
			return DailyMissionStatus.COMPLETED;
		if(player.getDualClass() != null && player.getDualClass().getLevel() >= missionTemplate.getValue())
			return DailyMissionStatus.AVAILABLE;
		return DailyMissionStatus.NOT_AVAILABLE;
	}
}
*/