package handler.admincommands;

import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.GameObject;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.base.TeamType;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;

/**
 * @author VISTALL
 * @date 2:52/29.06.2011
 */
public class AdminTeam extends ScriptAdminCommand
{
	enum Commands
	{
		admin_setteam
	}

	@Override
	public boolean useAdminCommand(Enum<?> comm, String[] wordList, String fullString, Player activeChar)
	{
		TeamType team = TeamType.NONE;
		if(wordList.length >= 2)
		{
			for(TeamType t : TeamType.values())
			{
				if(wordList[1].equalsIgnoreCase(t.name()))
				{
					team = t;
				}
			}
		}

		GameObject object = activeChar.getTarget();
		if(object == null || !object.isCreature())
		{
			activeChar.sendPacket(SystemMsg.INVALID_TARGET);
			return false;
		}

		((Creature) object).setTeam(team);
		return true;
	}

	@Override
	public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}
