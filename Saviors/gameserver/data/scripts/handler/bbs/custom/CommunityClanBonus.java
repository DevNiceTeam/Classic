package handler.bbs.custom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import handler.bbs.ScriptsCommunityHandler;
import l2s.commons.dbutils.DbUtils;
import l2s.gameserver.database.DatabaseFactory;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.pledge.Clan;

public class CommunityClanBonus extends ScriptsCommunityHandler
{
	@Override
	public String[] getBypassCommands()
	{
		return new String[]
		{ "_bbsclanbonus" };
	}

	@Override
	public void onBypassCommand(Player player, String bypass)
	{
		if(bypass.startsWith("_bbsclanbonus"))
		{
			if(checkCondition(player))
				if(addReward(player.getClan()))
				{
					deletRecord(player.getClan());
					player.sendMessage(player.isLangRus() ? "Ваш клан получил бонус." : "Your clan received a bonus.");
				}
		}
	}

	private boolean checkCondition(Player player)
	{
		if(player == null)
			return false;

		Clan clan = player.getClan();

		if(clan == null)
		{
			player.sendMessage(player.isLangRus() ? "Вы не состоите в клане." : "You are not in a clan.");
			return false;
		}

		if(!player.isClanLeader())
		{
			player.sendMessage(player.isLangRus() ? "Вы не являетесь главой клана." : "You are not the clan leader.");
			return false;
		}

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;
		List<String> names = new ArrayList<String>();
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("SELECT name FROM bbs_clanbonus");
			rset = statement.executeQuery();
			while(rset.next())
				names.add(rset.getString("name"));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rset);
		}

		if(!names.contains(clan.getName()))
		{
			player.sendMessage(player.isLangRus() ? "Вашего клана нет в списке." : "Your clan is not in the list.");
			return false;
		}

		if(!checkPlayersCount(clan))
		{
			player.sendMessage(player.isLangRus() ? "Условия не соблюдены." : "Conditions are not met.");
			return false;
		}

		return true;

	}

	private boolean checkPlayersCount(Clan clan)
	{
		Map<String, String> ips = new HashMap<String, String>();
		int realOnline = clan.getOnlineMembers(0).size();
		int need = 0;
		//проверка на твинков
		for(Player p : clan.getOnlineMembers(0))
			ips.put(p.getIP(), p.getIP());
		if(realOnline != ips.size())
			return false;

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("SELECT players_count FROM bbs_clanbonus WHERE name=? LIMIT 1");
			statement.setString(1, clan.getName());
			rset = statement.executeQuery();
			if(rset.next())
			{
				need = rset.getInt("players_count");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rset);
		}

		if(need > realOnline)
			return false;

		return true;

	}

	private boolean addReward(Clan clan)
	{
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;

		int lvl = 0, rep = 0;
		//Clan clan = player.getClan();

		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("SELECT reward_lvl, reward_rep FROM bbs_clanbonus WHERE name=? LIMIT 1");
			statement.setString(1, clan.getName());
			rset = statement.executeQuery();
			if(rset.next())
			{
				lvl = rset.getInt("reward_lvl");
				rep = rset.getInt("reward_rep");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rset);
		}

		if(lvl > clan.getLevel())
			clan.setLevel(lvl);
		clan.incReputation(rep, false, null);
		clan.broadcastClanStatus(true, true, false);
		return true;
	}

	private void deletRecord(Clan clan)
	{
		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("DELETE FROM bbs_clanbonus WHERE name = ?");
			statement.setString(1, clan.getName());
			statement.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
	}

	@Override
	public void onWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5)
	{

	}

}
