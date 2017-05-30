package npc.model;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.data.xml.holder.SkillHolder;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;

/**
 * @author VISTALL
 * @date 10:50/24.06.2011
 */
public class BlackJudeInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	public BlackJudeInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		if(command.equals("tryRemovePenalty"))
		{
			if(player.getDeathPenalty().getLevel() > 0)
			{
				showChatWindow(player, 2, false, "%price%", getPrice(player));
			}
			else
			{
				showChatWindow(player, 1, false);
			}
		}
		else if(command.equals("removePenalty"))
		{
			if(player.getDeathPenalty().getLevel() > 0)
			{
				if(player.getAdena() >= getPrice(player))
				{
					player.reduceAdena(getPrice(player), true);
					doCast(SkillHolder.getInstance().getSkillEntry(5077, 1), player, false);
				}
				else
				{
					player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				}
			}
			else
			{
				showChatWindow(player, 1, false);
			}
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}

	public int getPrice(Player player)
	{
		int playerLvl = player.getLevel();
		if(playerLvl <= 19)
		{
			return 3600; // Non-grade (confirmed)
		}
		else if(playerLvl >= 20 && playerLvl <= 39)
		{
			return 16400; // D-grade
		}
		else if(playerLvl >= 40 && playerLvl <= 51)
		{
			return 36200; // C-grade
		}
		else if(playerLvl >= 52 && playerLvl <= 60)
		{
			return 50400; // B-grade (confirmed)
		}
		else if(playerLvl >= 61 && playerLvl <= 75)
		{
			return 78200; // A-grade
		}
		else
		{
			return 102800; // S-grade
		}
	}
}
