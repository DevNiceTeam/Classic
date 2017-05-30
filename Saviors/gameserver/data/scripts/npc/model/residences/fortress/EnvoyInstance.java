package npc.model.residences.fortress;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.commons.dao.JdbcEntityState;
import studio.lineage2.gameserver.data.xml.holder.ResidenceHolder;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import studio.lineage2.gameserver.model.entity.residence.Castle;
import studio.lineage2.gameserver.model.entity.residence.Fortress;
import studio.lineage2.gameserver.model.entity.residence.Residence;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;

/**
 * @author VISTALL
 * @date 13:21/05.04.2011
 *
 * 001 - first
 * 002 - success independent
 * 003 - not leader
 * 004 - success contract
 * 005 - fail contract
 */
public class EnvoyInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	public static final int COND_LEADER = 0;
	public static final int COND_FAIL = 1;

	private int _castleId;
	private String _mainDialog;
	private String _failDialog;
	private String _successContractDialog;
	private String _successIndependentDialog;
	private String _failContractDialog;

	public EnvoyInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
		_castleId = template.getAIParams().getInteger("castle_id");
		_mainDialog = template.getAIParams().getString("main_dialog");
		_failDialog = template.getAIParams().getString("fail_dialog");
		_successContractDialog = template.getAIParams().getString("success_contract_dialog");
		_successIndependentDialog = template.getAIParams().getString("success_independent_dialog");
		_failContractDialog = template.getAIParams().getString("fail_contract_dialog");
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}
		int cond = getCond(player);
		switch(cond)
		{
			case COND_LEADER:
				final int castleId, state;
				final String fileName;
				if(command.equalsIgnoreCase("yes"))
				{
					Residence castle = ResidenceHolder.getInstance().getResidence(Castle.class, _castleId);
					if(castle.getOwnerId() == 0)
					{
						castleId = -1;
						state = Fortress.NOT_DECIDED;
						fileName = _failContractDialog;
					}
					else
					{
						castleId = castle.getId();
						state = Fortress.CONTRACT_WITH_CASTLE;
						fileName = _successContractDialog;
					}
				}
				else
				//else if(command.equalsIgnoreCase("no"))
				{
					castleId = 0;
					state = Fortress.INDEPENDENT;
					fileName = _successIndependentDialog;
				}

				if(state != Fortress.NOT_DECIDED)
				{
					Fortress fortress = getFortress();
					fortress.setFortState(state, castleId);
					fortress.setJdbcState(JdbcEntityState.UPDATED);
					fortress.update();

					FortressSiegeEvent event = fortress.getSiegeEvent();

					event.despawnEnvoy();
				}
				player.sendPacket(new HtmlMessage(this, fileName));
				break;
			case COND_FAIL:
				player.sendPacket(new HtmlMessage(this, _failDialog));
				break;
		}
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		String filename = null;
		int cond = getCond(player);
		switch(cond)
		{
			case COND_LEADER:
				filename = _mainDialog;
				break;
			case COND_FAIL:
				filename = _failDialog;
				break;
		}
		player.sendPacket(new HtmlMessage(this, filename).setPlayVoice(firstTalk));
	}

	protected int getCond(Player player)
	{
		Residence residence = getFortress();
		if(residence == null)
		{
			throw new IllegalArgumentException("Not find fortress: " + getNpcId() + "; loc: " + getLoc());
		}
		Clan residenceOwner = residence.getOwner();
		if(residenceOwner != null && player.getClan() == residenceOwner && residenceOwner.getLeaderId() == player.getObjectId())
		{
			return COND_LEADER;
		}
		else
		{
			return COND_FAIL;
		}
	}
}
