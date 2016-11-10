package handler.dailymissions;

import l2s.gameserver.listener.CharListener;
import l2s.gameserver.listener.actor.player.OnQuestFinishListener;
import l2s.gameserver.model.Player;

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
