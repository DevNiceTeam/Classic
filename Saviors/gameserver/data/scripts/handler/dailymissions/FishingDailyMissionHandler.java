package handler.dailymissions;

import studio.lineage2.gameserver.listener.CharListener;
import studio.lineage2.gameserver.listener.actor.player.OnFishingListener;
import studio.lineage2.gameserver.model.Player;

/**
 * @author Bonux
 **/
public class FishingDailyMissionHandler extends ProgressDailyMissionHandler
{
	private class HandlerListeners implements OnFishingListener
	{
		@Override
		public void onFishing(Player player, boolean success)
		{
			if(!success) // TODO: Проверить на оффе.
			{
				return;
			}

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
