package handler.dailymissions;

import studio.lineage2.gameserver.listener.CharListener;
import studio.lineage2.gameserver.listener.actor.player.OnQuestFinishListener;
import studio.lineage2.gameserver.model.Player;

/**
 * @author Bonux
 **/
public class QuestCompleteDailyMissionHandler extends ProgressDailyMissionHandler
{
	private class HandlerListeners implements OnQuestFinishListener
	{
		@Override
		public void onQuestFinish(Player player, int questId)
		{
			progressMission(player, 1);
		}
	}

	private final HandlerListeners _handlerListeners = new HandlerListeners();

	@Override
	public CharListener getListener()
	{
		return _handlerListeners;
	}
}
