package bosses;

import studio.lineage2.commons.dbutils.DbUtils;
import studio.lineage2.gameserver.database.DatabaseFactory;
import studio.lineage2.gameserver.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class EpicBossState
{
	private int _bossId;
	private long _respawnDate;
	private State state;

	public EpicBossState(int bossId)
	{
		this(bossId, true);
	}

	public EpicBossState(int bossId, boolean isDoLoad)
	{
		_bossId = bossId;
		_respawnDate = 0;
		state = State.NOTSPAWN;
		if(isDoLoad)
		{
			load();
		}
	}

	public State getState()
	{
		return state;
	}

	public void setState(State newState)
	{
		state = newState;
	}

	public long getRespawnDate()
	{
		return _respawnDate;
	}

	public void setRespawnDate(long interval)
	{
		_respawnDate = interval;
	}

	public void load()
	{
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rset = null;

		try
		{
			con = DatabaseFactory.getInstance().getConnection();

			statement = con.prepareStatement("SELECT * FROM epic_boss_spawn WHERE bossId = ? LIMIT 1");
			statement.setInt(1, _bossId);
			rset = statement.executeQuery();

			if(rset.next())
			{
				_respawnDate = rset.getLong("respawnDate") * 1000L;

				if(_respawnDate - System.currentTimeMillis() <= 0)
				{
					state = State.NOTSPAWN;
				}
				else
				{
					int tempState = rset.getInt("state");
					if(tempState == State.NOTSPAWN.ordinal())
					{
						state = State.NOTSPAWN;
					}
					else if(tempState == State.INTERVAL.ordinal())
					{
						state = State.INTERVAL;
					}
					else if(tempState == State.ALIVE.ordinal())
					{
						state = State.ALIVE;
					}
					else if(tempState == State.DEAD.ordinal())
					{
						state = State.DEAD;
					}
					else
					{
						state = State.NOTSPAWN;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rset);
		}
	}

	public void save()
	{
		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("REPLACE INTO epic_boss_spawn (bossId,respawnDate,state) VALUES(?,?,?)");
			statement.setInt(1, _bossId);
			statement.setInt(2, (int) (_respawnDate / 1000));
			statement.setInt(3, state.ordinal());
			statement.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
	}

	public void update()
	{
		Connection con = null;
		Statement statement = null;

		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.createStatement();
			statement.executeUpdate("UPDATE epic_boss_spawn SET respawnDate=" + _respawnDate / 1000 + ", state=" + state.ordinal() + " WHERE bossId=" + _bossId);
			final Date dt = new Date(_respawnDate);
			Log.add("EPIC", "update EpicBossState: ID:" + _bossId + ", RespawnDate:" + dt + ", State:" + state.toString());
		}
		catch(Exception e)
		{
			Log.add("EPIC", "Exception on update EpicBossState: ID " + _bossId + ", RespawnDate:" + _respawnDate / 1000 + ", State:" + state.toString());
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
	}

	public void setNextRespawnDate(long newRespawnDate)
	{
		_respawnDate = newRespawnDate;
	}

	public long getInterval()
	{
		long interval = _respawnDate - System.currentTimeMillis();
		return interval > 0 ? interval : 0;
	}

	public enum State
	{
		NOTSPAWN,
		ALIVE,
		DEAD,
		INTERVAL
	}
}