package npc.model.residences.castle;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.data.xml.holder.MultiSellHolder;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.base.ClassLevel;
import studio.lineage2.gameserver.model.base.PledgeRank;
import studio.lineage2.gameserver.model.entity.residence.ResidenceSide;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.skills.skillclasses.Call;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.ItemFunctions;
import studio.lineage2.gameserver.utils.Location;

public class CourtInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	// Плащи
	private static final int CloakLight = 34925;
	private static final int CloakDark = 34926;
	private static final int CloakLightLeader = 34996;
	private static final int CloakDarkLeader = 34997;

	protected static final int COND_ALL_FALSE = 0;
	protected static final int COND_BUSY_BECAUSE_OF_SIEGE = 1;
	protected static final int COND_OWNER = 2;

	/**
	 * @param template
	 */
	public CourtInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
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
		int condition = validateCondition(player);
		if(condition <= COND_ALL_FALSE)
		{
			return;
		}
		else if(condition == COND_BUSY_BECAUSE_OF_SIEGE)
		{
			return;
		}
		else if((player.getClanPrivileges() & Clan.CP_CS_USE_FUNCTIONS) != Clan.CP_CS_USE_FUNCTIONS)
		{
			player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
			return;
		}
		else if(condition == COND_OWNER)
		{
			if(command.startsWith("Chat"))
			{
				int val = 0;
				try
				{
					val = Integer.parseInt(command.substring(5));
				}
				catch(IndexOutOfBoundsException ioobe)
				{
				}
				catch(NumberFormatException nfe)
				{
				}
				showChatWindow(player, val, false);
			}
			else if(command.startsWith("gotoleader"))
			{
				if(player.getClan() != null)
				{
					Player clanLeader = player.getClan().getLeader().getPlayer();
					if(clanLeader == null)
					{
						return;
					}

					if(clanLeader.getEffectList().containsEffects(3632))
					{
						if(Call.canSummonHere(clanLeader) != null)
						{
							return;
						}

						if(Call.canBeSummoned(player) == null)
						{
							player.teleToLocation(Location.findAroundPosition(clanLeader, 100));
						}
						return;
					}
					showChatWindow(player, "castle/CourtMagician/CourtMagician-nogate.htm", false);
				}
			}
			else if(command.equalsIgnoreCase("Cloak"))
			{
				if(player.isClanLeader())
				{
					int itemId = getCastle().getResidenceSide() == ResidenceSide.LIGHT ? CloakLightLeader : CloakDarkLeader;
					if(player.getInventory().getItemByItemId(itemId) != null)
					{
						showChatWindow(player, "castle/chamberlain/alreadyhavecloak.htm", false);
						return;
					}
					else
					{
						ItemFunctions.addItem(player, itemId, 1, true);
						showChatWindow(player, "castle/chamberlain/chamberlain-givecloak.htm", false);
						return;
					}
				}
				else
				{
					int itemId = getCastle().getResidenceSide() == ResidenceSide.LIGHT ? CloakLight : CloakDark;

					// Плащ может получить только маркиз и выше.
					if(player.getInventory().getItemByItemId(itemId) != null || player.getPledgeRank().ordinal() < PledgeRank.MARQUIS.ordinal())
					{
						showChatWindow(player, "castle/chamberlain/alreadyhavecloak.htm", false);
						return;
					}
					else
					{
						ItemFunctions.addItem(player, itemId, 1, true);
						showChatWindow(player, "castle/chamberlain/chamberlain-givecloak.htm", false);
						return;
					}
				}
			}
			else if(command.equalsIgnoreCase("exchangetalisman"))
			{
//				if(player.getClassId().isOfLevel(ClassLevel.AWAKED))
//				{
//					showChatWindow(player, "castle/CourtMagician/fortress_wizard007.htm", false);
//				}
//				else
//				{
//					showChatWindow(player, "castle/CourtMagician/fortress_wizard009.htm", false);
//				}
			}
			else if(command.equalsIgnoreCase("exchangeclasstalisman"))
			{
//				if(!player.getClassId().isOfLevel(ClassLevel.AWAKED))
//				{
//					showChatWindow(player, "castle/CourtMagician/fortress_wizard009.htm", false);
//					return;
//				}
			}
			else if(command.equalsIgnoreCase("exchangeactivetalisman"))
			{
//				if(!player.getClassId().isOfLevel(ClassLevel.AWAKED))
//				{
//					showChatWindow(player, "castle/CourtMagician/fortress_wizard009.htm", false);
//					return;
//				}

				if(player.getLevel() >= 85 && player.getLevel() < 90)
				{
					MultiSellHolder.getInstance().SeparateAndSend(793, player, 0);
					return;
				}
				else if(player.getLevel() >= 90 && player.getLevel() < 95)
				{
					MultiSellHolder.getInstance().SeparateAndSend(794, player, 0);
					return;
				}
				else if(player.getLevel() >= 95 && player.getLevel() < 99)
				{
					MultiSellHolder.getInstance().SeparateAndSend(795, player, 0);
					return;
				}
				else if(player.getLevel() >= 99)
				{
					MultiSellHolder.getInstance().SeparateAndSend(796, player, 0);
					return;
				}
			}
			else if(command.equalsIgnoreCase("exchangepassivetalisman"))
			{
//				if(!player.getClassId().isOfLevel(ClassLevel.AWAKED))
//				{
//					showChatWindow(player, "castle/CourtMagician/fortress_wizard009.htm", false);
//					return;
//				}

				if(player.getLevel() >= 85 && player.getLevel() < 90)
				{
					MultiSellHolder.getInstance().SeparateAndSend(789, player, 0);
					return;
				}
				else if(player.getLevel() >= 90 && player.getLevel() < 95)
				{
					MultiSellHolder.getInstance().SeparateAndSend(790, player, 0);
					return;
				}
				else if(player.getLevel() >= 95 && player.getLevel() < 99)
				{
					MultiSellHolder.getInstance().SeparateAndSend(791, player, 0);
					return;
				}
				else if(player.getLevel() >= 99)
				{
					MultiSellHolder.getInstance().SeparateAndSend(792, player, 0);
					return;
				}
			}
			else
			{
				super.onBypassFeedback(player, command);
			}
		}
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		player.sendActionFailed();
		String filename = "castle/CourtMagician/CourtMagician-no.htm";

		int condition = validateCondition(player);
		if(condition > COND_ALL_FALSE)
		{
			if(condition == COND_BUSY_BECAUSE_OF_SIEGE)
			{
				filename = "castle/CourtMagician/CourtMagician-busy.htm"; // Busy because of siege
			}
			else if(condition == COND_OWNER)
			{
				if(val == 0)
				{
					filename = "castle/CourtMagician/CourtMagician.htm";
				}
				else
				{
					filename = "castle/CourtMagician/CourtMagician-" + val + ".htm";
				}
			}
		}

		HtmlMessage html = new HtmlMessage(this).setPlayVoice(firstTalk);
		html.setFile(filename);

		int side = 0;
		if(getCastle() != null)
		{
			side = getCastle().getResidenceSide().ordinal();
		}

		html.replace("<?residnce_side?>", String.valueOf(side));
		player.sendPacket(html);
	}

	protected int validateCondition(Player player)
	{
		if(player.isGM())
		{
			return COND_OWNER;
		}
		if(getCastle() != null && getCastle().getId() > 0)
		{
			if(player.getClan() != null)
			{
				if(getCastle().getSiegeEvent().isInProgress())
				{
					return COND_BUSY_BECAUSE_OF_SIEGE; // Busy because of siege
				}
				else if(getCastle().getOwnerId() == player.getClanId()) // Clan owns castle
				{
					return COND_OWNER;
				}
			}
		}
		return COND_ALL_FALSE;
	}
}