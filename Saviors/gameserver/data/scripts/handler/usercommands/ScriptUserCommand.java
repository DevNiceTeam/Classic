package handler.usercommands;

import studio.lineage2.gameserver.handler.usercommands.IUserCommandHandler;
import studio.lineage2.gameserver.handler.usercommands.UserCommandHandler;
import studio.lineage2.gameserver.listener.script.OnInitScriptListener;

/**
 * @author VISTALL
 * @date 16:53/24.06.2011
 */
public abstract class ScriptUserCommand implements IUserCommandHandler, OnInitScriptListener
{
	@Override
	public void onInit()
	{
		UserCommandHandler.getInstance().registerUserCommandHandler(this);
	}
}