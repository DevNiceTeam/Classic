package npc.model.residences.fortress;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.residence.Residence;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.Location;
import studio.lineage2.gameserver.utils.ReflectionUtils;

/**
 * @author VISTALL
 * @date 13:47/02.04.2011
 */
public class DoormanInstance extends npc.model.residences.DoormanInstance
{
	private static final long serialVersionUID = 1L;

	private Location _loc;

	public DoormanInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
		String loc = template.getAIParams().getString("tele_loc", null);
		if(loc != null)
		{
			_loc = Location.parseLoc(loc);
		}
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
			case COND_OWNER:
				if(command.equalsIgnoreCase("openDoors"))
				{
					for(int i : _doors)
					{
						ReflectionUtils.getDoor(i).openMe(player, true);
					}
				}
				else if(command.equalsIgnoreCase("closeDoors"))
				{
					for(int i : _doors)
					{
						ReflectionUtils.getDoor(i).closeMe(player, true);
					}
				}
				break;
			case COND_SIEGE:
				if(command.equalsIgnoreCase("tele"))
				{
					player.teleToLocation(_loc);
				}
				break;
			case COND_FAIL:
				player.sendPacket(new HtmlMessage(this, _failDialog));
				break;
		}
	}

	@Override
	public void setDialogs()
	{
		_mainDialog = "residence2/fortress/fortress_doorkeeper001.htm";
		_failDialog = "residence2/fortress/fortress_doorkeeper002.htm";
		_siegeDialog = "residence2/fortress/fortress_doorkeeper003.htm";
	}

	@Override
	public int getOpenPriv()
	{
		return Clan.CP_CS_ENTRY_EXIT;
	}

	@Override
	public Residence getResidence()
	{
		return getFortress();
	}
}
