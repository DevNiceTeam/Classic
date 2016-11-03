package handler.onshiftaction;

import l2s.gameserver.handler.admincommands.impl.AdminEditChar;
import l2s.gameserver.model.Player;

/**
 * @author VISTALL
 * @date 2:51/19.08.2011
 */
public class OnShiftAction_Player extends ScriptOnShiftActionHandler<Player>
{
	@Override
	public Class<Player> getClazz()
	{
		return Player.class;
	}

	@Override
	public boolean call(Player p, Player player)
	{
		if(!player.getPlayerAccess().CanViewChar)
			return false;

		AdminEditChar.showCharacterList(player, p);
		return true;
	}
}
