package npc.model.residences.clanhall;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.dao.SiegeClanDAO;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.events.impl.ClanHallMiniGameEvent;
import studio.lineage2.gameserver.model.entity.events.objects.CMGSiegeClanObject;
import studio.lineage2.gameserver.model.entity.events.objects.SiegeClanObject;
import studio.lineage2.gameserver.model.entity.residence.ClanHall;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.ItemFunctions;
import studio.lineage2.gameserver.utils.TimeUtils;

/**
 * @author VISTALL
 * @date 8:01/07.05.2011
 */
public class RainbowMessengerInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	public static final int ITEM_ID = 8034;

	public RainbowMessengerInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	public void onBypassFeedback(final Player player, final String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		ClanHall clanHall = getClanHall();
		ClanHallMiniGameEvent miniGameEvent = clanHall.getSiegeEvent();
		if(command.equalsIgnoreCase("register"))
		{
			if(miniGameEvent.isRegistrationOver())
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti014.htm", false);
				return;
			}

			Clan clan = player.getClan();
			if(clan == null || clan.getLevel() < 3 || clan.getAllSize() <= 5)
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti011.htm", false);
				return;
			}
			if(clan.isPlacedForDisband())
			{
				player.sendPacket(SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
				return;
			}
			if(clan.getLeaderId() != player.getObjectId())
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti010.htm", false);
				return;
			}
			if(clan.getHasHideout() > 0)
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti012.htm", false);
				return;
			}

			if(miniGameEvent.getSiegeClan(ClanHallMiniGameEvent.ATTACKERS, clan) != null)
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti013.htm", false);
				return;
			}

			long count = player.getInventory().getCountOf(ITEM_ID);
			if(count == 0)
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti008.htm", false);
			}
			else
			{
				if(!player.consumeItem(ITEM_ID, count, true))
				{
					return;
				}

				CMGSiegeClanObject siegeClanObject = new CMGSiegeClanObject(ClanHallMiniGameEvent.ATTACKERS, clan, count);
				miniGameEvent.addObject(ClanHallMiniGameEvent.ATTACKERS, siegeClanObject);
				SiegeClanDAO.getInstance().insert(clanHall, siegeClanObject);

				showChatWindow(player, "residence2/clanhall/messenger_yetti009.htm", false);
			}
		}
		else if(command.equalsIgnoreCase("cancel"))
		{
			if(miniGameEvent.isRegistrationOver())
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti017.htm", false);
				return;
			}

			Clan clan = player.getClan();
			if(clan == null || clan.getLevel() < 3)
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti011.htm", false);
				return;
			}
			if(clan.getLeaderId() != player.getObjectId())
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti010.htm", false);
				return;
			}

			SiegeClanObject siegeClanObject = miniGameEvent.getSiegeClan(ClanHallMiniGameEvent.ATTACKERS, clan);
			if(siegeClanObject == null)
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti016.htm", false);
			}
			else
			{
				miniGameEvent.removeObject(ClanHallMiniGameEvent.ATTACKERS, siegeClanObject);
				SiegeClanDAO.getInstance().delete(clanHall, siegeClanObject);

				ItemFunctions.addItem(player, ITEM_ID, siegeClanObject.getParam() / 2L, true);

				showChatWindow(player, "residence2/clanhall/messenger_yetti005.htm", false);
			}
		}
		else if(command.equalsIgnoreCase("refund"))
		{
			if(miniGameEvent.isRegistrationOver())
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti010.htm", false);
				return;
			}

			Clan clan = player.getClan();
			if(clan == null || clan.getLevel() < 3)
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti011.htm", false);
				return;
			}
			if(clan.getLeaderId() != player.getObjectId())
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti010.htm", false);
				return;
			}

			SiegeClanObject siegeClanObject = miniGameEvent.getSiegeClan(ClanHallMiniGameEvent.REFUND, clan);
			if(siegeClanObject == null)
			{
				showChatWindow(player, "residence2/clanhall/messenger_yetti020.htm", false);
			}
			else
			{
				miniGameEvent.removeObject(ClanHallMiniGameEvent.REFUND, siegeClanObject);
				SiegeClanDAO.getInstance().delete(clanHall, siegeClanObject);

				ItemFunctions.addItem(player, ITEM_ID, siegeClanObject.getParam(), true);

				showChatWindow(player, "residence2/clanhall/messenger_yetti019.htm", false);
			}
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		ClanHall clanHall = getClanHall();
		Clan clan = clanHall.getOwner();
		HtmlMessage msg = new HtmlMessage(this).setPlayVoice(firstTalk);
		if(clan != null)
		{
			msg.setFile("residence2/clanhall/messenger_yetti001.htm");
			msg.replace("%owner_name%", clan.getName());
		}
		else
		{
			msg.setFile("residence2/clanhall/messenger_yetti001a.htm");
		}
		msg.replace("%siege_date%", TimeUtils.toSimpleFormat(clanHall.getSiegeDate()));

		player.sendPacket(msg);
	}
}
