package handler.dailymissions;

import studio.lineage2.gameserver.handler.dailymissions.DailyMissionHandlerHolder;
import studio.lineage2.gameserver.handler.dailymissions.impl.DefaultDailyMissionHandler;
import studio.lineage2.gameserver.listener.script.OnLoadScriptListener;

/**
 * @author Bonux
 */
public abstract class ScriptDailyMissionHandler extends DefaultDailyMissionHandler implements OnLoadScriptListener
{
	@Override
	public void onLoad()
	{
		DailyMissionHandlerHolder.getInstance().registerHandler(this);
	}
}
