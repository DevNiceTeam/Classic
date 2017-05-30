package npc.model.residences.castle;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.residence.Castle;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.Functions;

import java.util.StringTokenizer;

/**
 * @author Bonux
 **/
public class RevolutionaryInstance extends NpcInstance
{
	public RevolutionaryInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	public String getHtmlPath(int npcId, int val, Player player)
	{
		String pom;
		if(val == 0)
		{
			Castle castle = getCastle(player);
			if(castle != null && castle.getOwnerId() > 0)
			{
				pom = String.valueOf(getNpcId());
			}
			else
			{
				pom = "no_clan";
			}
		}
		else
		{
			pom = getNpcId() + "-" + val;
		}

		return "castle/revolutionary/" + pom + ".htm";
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		StringTokenizer st = new StringTokenizer(command);
		String cmd = st.nextToken();
		if(cmd.equals("take_bundle")) // TODO [Bonux]
		{
			showChatWindow(player, "castle/revolutionary/no_bundle.htm", false);
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		Castle castle = getCastle(player);
		Clan clan = (castle == null ? null : castle.getOwner());
		if(clan != null)
		{
			if(val == 0)
			{
				Functions.npcSayToPlayer(this, player, NpcString.WE_WILL_EXECUTE_OUR_PLAN_ON_SUNDAY_NIGHT_IF_YOU_JOIN_US_I_WILL_GIVE_YOU_A_SUBSTANTIAL_REWARD);
			}
		}

		super.showChatWindow(player, val, firstTalk, arg);
	}
}