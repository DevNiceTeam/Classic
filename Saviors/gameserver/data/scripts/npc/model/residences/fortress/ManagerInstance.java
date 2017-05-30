package npc.model.residences.fortress;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.residence.Fortress;
import studio.lineage2.gameserver.model.entity.residence.Residence;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.network.l2.s2c.L2GameServerPacket;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.HtmlUtils;
import npc.model.residences.ResidenceManager;

public class ManagerInstance extends ResidenceManager
{
	private static final long serialVersionUID = 1L;

	private static final long REWARD_CYCLE = 6 * 60 * 60; // каждых 6 часов

	public ManagerInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	protected void setDialogs()
	{
		_mainDialog = "residence2/fortress/fortress_steward001.htm";
		_failDialog = "residence2/fortress/fortress_steward002.htm";
		_siegeDialog = "residence2/fortress/fortress_steward018.htm";
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		if(command.equalsIgnoreCase("receive_report"))
		{
			int ownedTime = (int) ((System.currentTimeMillis() - getFortress().getOwnDate().getTimeInMillis()) / 60000L);

			HtmlMessage html = new HtmlMessage(this);

			Fortress fortress = getFortress();
			if(fortress.getContractState() == Fortress.CONTRACT_WITH_CASTLE)
			{
				html.setFile("residence2/fortress/fortress_steward022.htm");
				html.replace("%castle_name%", HtmlUtils.htmlResidenceName(getFortress().getCastleId()));
				html.replace("%contract%", NpcString.CONTRACT_STATE);

				long leftTime = (REWARD_CYCLE - (3600 - fortress.getCycleDelay()) - fortress.getPaidCycle() * 3600) / 60;

				html.replace("%rent_cost%", String.valueOf(Fortress.CASTLE_FEE));
				html.replace("%next_hour%", String.valueOf(leftTime / 60));
				html.replace("%next_min%", String.valueOf(leftTime % 60));
			}
			else
			{
				html.setFile("residence2/fortress/fortress_steward023.htm");
			}

			html.replace("%time_remained%", NpcString.S1HOUR_S2MINUTE, ownedTime / 60, ownedTime % 60);

			player.sendPacket(html);
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}

	@Override
	protected int getCond(Player player)
	{
		Residence residence = getResidence();
		Clan residenceOwner = residence.getOwner();
		if(residenceOwner != null && player.getClan() == residenceOwner)
		{
			if(residence.getSiegeEvent().isInProgress())
			{
				return COND_SIEGE;
			}
			else
			{
				return COND_OWNER;
			}
		}
		else
		{
			return COND_FAIL;
		}
	}

	@Override
	protected Residence getResidence()
	{
		return getFortress();
	}

	@Override
	public L2GameServerPacket decoPacket()
	{
		return null;
	}

	@Override
	protected int getPrivUseFunctions()
	{
		return Clan.CP_CS_USE_FUNCTIONS;
	}

	@Override
	protected int getPrivSetFunctions()
	{
		return Clan.CP_CS_SET_FUNCTIONS;
	}

	@Override
	protected int getPrivDismiss()
	{
		return Clan.CP_CS_DISMISS;
	}

	@Override
	protected int getPrivDoors()
	{
		return Clan.CP_CS_ENTRY_EXIT;
	}
}