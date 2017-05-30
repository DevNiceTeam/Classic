package handler.items;

import studio.lineage2.gameserver.handler.items.ItemHandler;
import studio.lineage2.gameserver.handler.items.impl.DefaultItemHandler;
import studio.lineage2.gameserver.listener.script.OnLoadScriptListener;

/**
 * @author Bonux
 */
public abstract class ScriptItemHandler extends DefaultItemHandler implements OnLoadScriptListener
{
	@Override
	public void onLoad()
	{
		ItemHandler.getInstance().registerItemHandler(this);
	}
}
