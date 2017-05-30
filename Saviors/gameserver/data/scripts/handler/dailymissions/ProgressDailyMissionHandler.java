package handler.dailymissions;

import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.actor.instances.player.DailyMission;
import studio.lineage2.gameserver.network.l2.s2c.ExOneDayReceiveRewardList;
import studio.lineage2.gameserver.templates.dailymissions.DailyMissionStatus;
import studio.lineage2.gameserver.templates.dailymissions.DailyMissionTemplate;

import java.util.Collection;

/**
 * @author Bonux
 **/
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
			{
				return DailyMissionStatus.COMPLETED;
			}
			if(mission.getValue() >= missionTemplate.getValue())
			{
				return DailyMissionStatus.AVAILABLE;
			}
		}
		return DailyMissionStatus.NOT_AVAILABLE;
	}

	protected void progressMission(Player player, int addValue)
	{
		if(!Config.EX_USE_TO_DO_LIST)
		{
			return;
		}

		boolean update = false;

		Collection<DailyMissionTemplate> missionTemplates = player.getDailyMissionList().getAvailableMissions();
		for(DailyMissionTemplate missionTemplate : missionTemplates)
		{
			if(missionTemplate.getHandler() != this)
			{
				continue;
			}

			DailyMission mission = player.getDailyMissionList().get(missionTemplate, true);
			if(mission.isCompleted())
			{
				continue;
			}

			mission.setValue(mission.getValue() + addValue);
			update = true;
		}

		if(update)
		{
			player.sendPacket(new ExOneDayReceiveRewardList(player));
		}
	}
}
