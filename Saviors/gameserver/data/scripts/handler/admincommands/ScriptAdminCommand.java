package handler.admincommands;

import studio.lineage2.gameserver.handler.admincommands.AdminCommandHandler;
import studio.lineage2.gameserver.handler.admincommands.IAdminCommandHandler;
import studio.lineage2.gameserver.listener.script.OnInitScriptListener;

/**
 * @author VISTALL
 * @date 22:57/08.04.2011
 */
public abstract class ScriptAdminCommand implements IAdminCommandHandler, OnInitScriptListener
{
	@Override
	public void onInit()
	{
		AdminCommandHandler.getInstance().registerAdminCommandHandler(this);
	}
}
