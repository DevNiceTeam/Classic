package handler.dailymissions;

import studio.lineage2.gameserver.listener.CharListener;
import studio.lineage2.gameserver.listener.actor.player.OnOlympiadFinishBattleListener;
import studio.lineage2.gameserver.model.Player;

/**
 * @author Bonux
 **/
public class OlympiadBattleDailyMissionHandler extends ProgressDailyMissionHandler
{
	private class HandlerListeners implements OnOlympiadFinishBattleListener
	{
		@Override
		public void onOlympiadFinishBattle(Player player, boolean winner)
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
