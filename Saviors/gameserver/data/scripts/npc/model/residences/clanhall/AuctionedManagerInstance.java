package npc.model.residences.clanhall;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.residence.Residence;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;

/**
 * @author VISTALL
 * @date 13:29/20.06.2011
 */
public class AuctionedManagerInstance extends ManagerInstance
{
	private static final long serialVersionUID = 1L;

	public AuctionedManagerInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	protected void setDialogs()
	{
		_mainDialog = getTemplate().getAIParams().getString("main_dialog", "residence2/clanhall/black001.htm");
		_failDialog = getTemplate().getAIParams().getString("fail_dialog", "residence2/clanhall/black002.htm");
	}

	@Override
	protected int getCond(Player player)
	{
		Residence residence = getResidence();
		Clan residenceOwner = residence.getOwner();
		if(residenceOwner != null && player.getClan() == residenceOwner)
		{
			return COND_OWNER;
		}
		else
		{
			return COND_FAIL;
		}
	}
}
