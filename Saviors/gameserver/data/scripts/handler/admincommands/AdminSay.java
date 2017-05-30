package handler.admincommands;

import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.GameObject;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.components.ChatType;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.network.l2.s2c.L2GameServerPacket;
import studio.lineage2.gameserver.network.l2.s2c.NSPacket;
import studio.lineage2.gameserver.network.l2.s2c.SayPacket2;

public class AdminSay extends ScriptAdminCommand
{
	private enum Commands
	{
		admin_say
	}

	@Override
	public boolean useAdminCommand(Enum<?> comm, String[] wordList, String fullString, Player activeChar)
	{
		GameObject target = activeChar.getTarget();
		if(target == null || !target.isCreature())
		{
			target = activeChar;
		}

		if(wordList.length < 3)
		{
			return false;
		}

		ChatType type = ChatType.valueOf(wordList[1]);
		NpcString npcString = NpcString.NONE;
		String[] arg = new String[5];
		try
		{
			npcString = NpcString.valueOf(wordList[2]);
		}
		catch(IllegalArgumentException e)
		{
			arg[0] = wordList[2];
		}

		L2GameServerPacket packet;
		if(target.isNpc())
		{
			packet = new NSPacket((NpcInstance) target, type, npcString.getId(), arg);
		}
		else
		{
			packet = new SayPacket2(target.getObjectId(), type, target.getName(), npcString, arg);
		}

		((Creature) target).broadcastPacket(packet);
		return true;
	}

	@Override
	public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}