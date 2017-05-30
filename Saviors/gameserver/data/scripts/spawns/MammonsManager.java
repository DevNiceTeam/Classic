package spawns;

import studio.lineage2.commons.threading.RunnableImpl;
import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ThreadPoolManager;
import studio.lineage2.gameserver.instancemanager.SpawnManager;
import studio.lineage2.gameserver.listener.script.OnInitScriptListener;

import java.util.concurrent.Future;

/**
 * @author Bonux
 * Дополнил MaJr
 */
public class MammonsManager implements OnInitScriptListener
{
	private static final long RESPAWN_DELAY = 3600000L; // Через каждый час меняет локацию только по городам: Аден, Руна и Гиран.
	private static final String[] SPAWN_GROUPS = {
			"giran_mammons",
			"aden_mammons",
			"rune_mammons"
	};

	private int _currentSpawnedGroup = -1;
	private Future<?> _respawnTask;

	@Override
	public void onInit()
	{
		_currentSpawnedGroup = Rnd.get(SPAWN_GROUPS.length);

		String groupName = SPAWN_GROUPS[_currentSpawnedGroup];
		SpawnManager.getInstance().spawn(groupName);
		SpawnManager.getInstance().spawn(groupName + "_priest_" + Rnd.get(1, 2));
		_respawnTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new RespawnTask(), RESPAWN_DELAY, RESPAWN_DELAY);
	}

	private void stopRespawnTask()
	{
		if(_respawnTask != null)
		{
			_respawnTask.cancel(false);
			_respawnTask = null;
		}
	}

	private class RespawnTask extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			int newSpawnGroup = 0;
			if(SPAWN_GROUPS.length > 1) // Чтобы группы спавна не повторялись.
			{
				newSpawnGroup = Rnd.get(SPAWN_GROUPS.length);
				while(newSpawnGroup == _currentSpawnedGroup)
				{
					newSpawnGroup = Rnd.get(SPAWN_GROUPS.length);
				}
			}

			String groupName = SPAWN_GROUPS[_currentSpawnedGroup];
			SpawnManager.getInstance().despawn(groupName);
			SpawnManager.getInstance().despawn(groupName + "_priest_1");
			SpawnManager.getInstance().despawn(groupName + "_priest_2");

			groupName = SPAWN_GROUPS[newSpawnGroup];
			SpawnManager.getInstance().spawn(groupName);
			SpawnManager.getInstance().spawn(groupName + "_priest_" + Rnd.get(1, 2));

			_currentSpawnedGroup = newSpawnGroup;
		}
	}
}