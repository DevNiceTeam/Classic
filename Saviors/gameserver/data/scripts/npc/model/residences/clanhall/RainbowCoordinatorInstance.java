package npc.model.residences.clanhall;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.Party;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.events.impl.ClanHallMiniGameEvent;
import studio.lineage2.gameserver.model.entity.events.objects.CMGSiegeClanObject;
import studio.lineage2.gameserver.model.entity.events.objects.SpawnExObject;
import studio.lineage2.gameserver.model.entity.residence.ClanHall;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.Location;

import java.util.List;

/**
 * @author VISTALL
 * @date 8:22/06.05.2011
 * 35603
 */
public class RainbowCoordinatorInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	public RainbowCoordinatorInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
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
		if(miniGameEvent == null)
		{
			return;
		}

		if(miniGameEvent.isArenaClosed())
		{
			showChatWindow(player, "residence2/clanhall/game_manager003.htm", false);
			return;
		}

		List<CMGSiegeClanObject> siegeClans = miniGameEvent.getObjects(ClanHallMiniGameEvent.ATTACKERS);

		CMGSiegeClanObject siegeClan = miniGameEvent.getSiegeClan(ClanHallMiniGameEvent.ATTACKERS, player.getClan());
		if(siegeClan == null)
		{
			showChatWindow(player, "residence2/clanhall/game_manager014.htm", false);
			return;
		}

		if(siegeClan.getPlayers().isEmpty())
		{
			Party party = player.getParty();
			if(party == null)
			{
				showChatWindow(player, player.isClanLeader() ? "residence2/clanhall/game_manager005.htm" : "residence2/clanhall/game_manager002.htm", false);
				return;
			}

			if(!player.isClanLeader())
			{
				showChatWindow(player, "residence2/clanhall/game_manager004.htm", false);
				return;
			}

			if(party.getMemberCount() < 5)
			{
				showChatWindow(player, "residence2/clanhall/game_manager003.htm", false);
				return;
			}

			if(party.getPartyLeader() != player)
			{
				showChatWindow(player, "residence2/clanhall/game_manager006.htm", false);
				return;
			}

			for(Player member : party.getPartyMembers())
			{
				if(member.getClan() != player.getClan())
				{
					showChatWindow(player, "residence2/clanhall/game_manager007.htm", false);
					return;
				}
			}

			int index = siegeClans.indexOf(siegeClan);

			SpawnExObject spawnEx = miniGameEvent.getFirstObject("arena_" + index);

			Location loc = (Location) spawnEx.getSpawns().get(0).getRandomSpawnRange();

			for(Player member : party.getPartyMembers())
			{
				siegeClan.addPlayer(member.getObjectId());
				member.teleToLocation(Location.coordsRandomize(loc, 100, 200));
			}
		}
		else
		{
			showChatWindow(player, "residence2/clanhall/game_manager013.htm", false);
		}
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		showChatWindow(player, "residence2/clanhall/game_manager001.htm", firstTalk);
	}
}
