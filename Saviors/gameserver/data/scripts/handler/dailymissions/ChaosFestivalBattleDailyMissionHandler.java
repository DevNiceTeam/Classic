package handler.dailymissions;

import studio.lineage2.gameserver.listener.CharListener;
import studio.lineage2.gameserver.listener.actor.player.OnChaosFestivalFinishBattleListener;
import studio.lineage2.gameserver.model.Player;

/**
 * @author Bonux
 **/
public class ChaosFestivalBattleDailyMissionHandler extends ProgressDailyMissionHandler
{
	private class HandlerListeners implements OnChaosFestivalFinishBattleListener
	{
		@Override
		public void onChaosFestivalFinishBattle(Player player, boolean winner)
		{
			if(!winner) // TODO: Проверить на оффе.
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
