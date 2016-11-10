package npc.model.residences.castle;

import java.util.StringTokenizer;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.entity.residence.Castle;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.pledge.Clan;
import l2s.gameserver.network.l2.components.NpcString;
import l2s.gameserver.templates.npc.NpcTemplate;
import l2s.gameserver.utils.Functions;

/**
 * @author Bonux
**/
public class RevolutionaryInstance extends NpcInstance
{
	public RevolutionaryInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public String getHtmlPath(int npcId, int val, Player player)
	{
		String pom;
		if(val == 0)
		{
			Castle castle = getCastle(player);
			if(castle != null && castle.getOwnerId() > 0)
				pom = String.valueOf(getNpcId());
			else
				pom = "no_clan";
		}
		else
			pom = getNpcId() + "-" + val;

		return "castle/revolutionary/" + pom + ".htm";
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
			return;

		StringTokenizer st = new StringTokenizer(command);
		String cmd = st.nextToken();
		if(cmd.equals("take_bundle")) // TODO [Bonux]
		{
			showChatWindow(player, "castle/revolutionary/no_bundle.htm", false);
		}
		else
			super.onBypassFeedback(player, command);
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		Castle castle = getCastle(player);
		Clan clan = (castle == null ? null : castle.getOwner());
		if(clan != null)
		{
			if(val == 0)
				Functions.npcSayToPlayer(this, player, NpcString.WE_WILL_EXECUTE_OUR_PLAN_ON_SUNDAY_NIGHT_IF_YOU_JOIN_US_I_WILL_GIVE_YOU_A_SUBSTANTIAL_REWARD);
		}

		super.showChatWindow(player, val, firstTalk, arg);
	}
}