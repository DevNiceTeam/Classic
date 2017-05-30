package npc.model.residences.fortress.peace;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.dao.SiegeClanDAO;
import studio.lineage2.gameserver.data.xml.holder.ResidenceHolder;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import studio.lineage2.gameserver.model.entity.events.impl.SiegeEvent;
import studio.lineage2.gameserver.model.entity.events.objects.SiegeClanObject;
import studio.lineage2.gameserver.model.entity.residence.Castle;
import studio.lineage2.gameserver.model.entity.residence.Fortress;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.model.pledge.Privilege;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.network.l2.s2c.SystemMessagePacket;
import studio.lineage2.gameserver.templates.item.ItemTemplate;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;

public class SuspiciousMerchantInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	public SuspiciousMerchantInstance(int objectID, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectID, template, set);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		Fortress fortress = getFortress();
		FortressSiegeEvent siegeEvent = fortress.getSiegeEvent();

		if(command.equalsIgnoreCase("register"))
		{
			Clan clan = player.getClan();
			if(clan == null)
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery002.htm", false);
				return;
			}

			if(clan.isPlacedForDisband())
			{
				player.sendPacket(SystemMsg.YOU_HAVE_ALREADY_REQUESTED_THE_DISSOLUTION_OF_YOUR_CLAN);
				return;
			}

			if(clan.getHasFortress() == fortress.getId())
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery014.htm", false, "%clan_name%", clan.getName());
				return;
			}

			if(!player.hasPrivilege(Privilege.CS_FS_SIEGE_WAR))
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery012.htm", false);
				return;
			}

			if(clan.getCastle() > 0)
			{
				Castle relatedCastle = null;
				for(Castle castle : fortress.getRelatedCastles())
				{
					if(castle.getId() == clan.getCastle())
					{
						relatedCastle = castle;
					}
				}

				if(relatedCastle != null)
				{
					if(fortress.getContractState() == Fortress.CONTRACT_WITH_CASTLE)
					{
						showChatWindow(player, "residence2/fortress/fortress_ordery022.htm", false);
						return;
					}

					if(relatedCastle.getSiegeEvent().isRegistrationOver())
					{
						showChatWindow(player, "residence2/fortress/fortress_ordery022.htm", false);
						return;
					}
				}
				else
				{
					showChatWindow(player, "residence2/fortress/fortress_ordery021.htm", false);
					return;
				}
			}

			int attackersSize = siegeEvent.getObjects(SiegeEvent.ATTACKERS).size();

			if(attackersSize == 0)
			{
				if(!player.consumeItem(ItemTemplate.ITEM_ID_ADENA, 250000L, true))
				{
					showChatWindow(player, "residence2/fortress/fortress_ordery003.htm", false);
					return;
				}
			}

			SiegeClanObject siegeClan = siegeEvent.getSiegeClan(FortressSiegeEvent.ATTACKERS, clan);
			if(siegeClan != null)
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery007.htm", false);
				return;
			}

			// 1 рега возможна всего
			for(Fortress $ : ResidenceHolder.getInstance().getResidenceList(Fortress.class))
			{
				if($.getSiegeEvent().getSiegeClan(FortressSiegeEvent.ATTACKERS, clan) != null)
				{
					showChatWindow(player, "residence2/fortress/fortress_ordery006.htm", false);
					return;
				}
			}

			// если у нас есть форт, запрещаем регатся на форт, если на носу осада своего форта(во избежания абуза, участия в 2 осадах)
			if(clan.getHasFortress() > 0 && fortress.getSiegeDate().getTimeInMillis() > 0)
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery006.htm", false);
				return;
			}

			if(siegeEvent.isRegistrationOver())
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery006.htm", false);
				return;
			}

			siegeClan = new SiegeClanObject(FortressSiegeEvent.ATTACKERS, clan, 0);
			siegeEvent.addObject(FortressSiegeEvent.ATTACKERS, siegeClan);
			SiegeClanDAO.getInstance().insert(fortress, siegeClan);

			siegeEvent.reCalcNextTime(false);

			player.sendPacket(new SystemMessagePacket(SystemMsg.YOUR_CLAN_HAS_BEEN_REGISTERED_TO_S1S_FORTRESS_BATTLE).addResidenceName(fortress));
			showChatWindow(player, "residence2/fortress/fortress_ordery005.htm", false);
		}
		else if(command.equalsIgnoreCase("cancel"))
		{
			Clan clan = player.getClan();
			if(clan == null || !player.hasPrivilege(Privilege.CS_FS_SIEGE_WAR))
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery010.htm", false);
				return;
			}

			SiegeClanObject siegeClan = siegeEvent.getSiegeClan(FortressSiegeEvent.ATTACKERS, clan);
			if(siegeClan != null)
			{
				siegeEvent.removeObject(FortressSiegeEvent.ATTACKERS, siegeClan);
				SiegeClanDAO.getInstance().delete(fortress, siegeClan);

				siegeEvent.reCalcNextTime(false);

				showChatWindow(player, "residence2/fortress/fortress_ordery009.htm", false);
			}
			else
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery011.htm", false);
			}
		}
		else if(command.equalsIgnoreCase("state"))
		{
			int attackersSize = siegeEvent.getObjects(SiegeEvent.ATTACKERS).size();
			if(attackersSize == 0)
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery019.htm", false);
			}
			else
			{
				showChatWindow(player, "residence2/fortress/fortress_ordery020.htm", false);
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
		HtmlMessage html = new HtmlMessage(this).setPlayVoice(firstTalk);
		Fortress fortress = getFortress();
		if(fortress.getOwner() != null)
		{
			html.setFile("residence2/fortress/fortress_ordery001a.htm");
			html.replace("%clan_name%", fortress.getOwner().getName());
		}
		else
		{
			html.setFile("residence2/fortress/fortress_ordery001.htm");
		}

		player.sendPacket(html);
	}
}