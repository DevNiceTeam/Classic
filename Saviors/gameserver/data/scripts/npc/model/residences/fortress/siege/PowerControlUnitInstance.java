package npc.model.residences.fortress.siege;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.Spawner;
import studio.lineage2.gameserver.model.base.ClassId;
import studio.lineage2.gameserver.model.entity.events.impl.FortressSiegeEvent;
import studio.lineage2.gameserver.model.entity.events.objects.SpawnExObject;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import org.apache.commons.lang3.ArrayUtils;

import java.util.StringTokenizer;

/**
 * @author VISTALL
 * @date 19:35/19.04.2011
 */
public class PowerControlUnitInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	public static final int LIMIT = 3;

	public static final int COND_NO_ENTERED = 0;
	public static final int COND_ENTERED = 1;
	public static final int COND_ALL_OK = 2;
	public static final int COND_FAIL = 3;
	public static final int COND_TIMEOUT = 4;

	private int[] _generated = new int[LIMIT];
	private int _index;
	private int _tryCount;
	private long _invalidatePeriod;

	public PowerControlUnitInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		StringTokenizer token = new StringTokenizer(command);
		token.nextToken();    // step
		int val = Integer.parseInt(token.nextToken());

		if(player.getClassId() == ClassId.WARSMITH || player.getClassId() == ClassId.MAESTRO)
		{
			if(_tryCount == 0)
			{
				_tryCount++;
			}
			else
			{
				_index++;
			}
		}
		else
		{
			if(_generated[_index] == val)
			{
				_index++;
			}
			else
			{
				_tryCount++;
			}
		}

		showChatWindow(player, 0, false);
	}


	@Override
	public void onSpawn()
	{
		super.onSpawn();

		generate();
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		HtmlMessage message = new HtmlMessage(this).setPlayVoice(firstTalk);

		if(_invalidatePeriod > 0 && _invalidatePeriod < System.currentTimeMillis())
		{
			generate();
		}

		int cond = getCond();
		switch(cond)
		{
			case COND_ALL_OK:
				message.setFile("residence2/fortress/fortress_inner_controller002.htm");

				FortressSiegeEvent event = getEvent(FortressSiegeEvent.class);
				if(event != null)
				{
					SpawnExObject exObject = event.getFirstObject(FortressSiegeEvent.SIEGE_COMMANDERS);
					Spawner spawn = exObject.getSpawns().get(3); // spawn of Main Machine

					MainMachineInstance machineInstance = (MainMachineInstance) spawn.getFirstSpawned();
					machineInstance.powerOff(this);

					onDecay();
				}
				break;
			case COND_TIMEOUT:
				message.setFile("residence2/fortress/fortress_inner_controller003.htm");
				break;
			case COND_FAIL:
				message.setFile("residence2/fortress/fortress_inner_controller003.htm");
				_invalidatePeriod = System.currentTimeMillis() + 30000L;
				break;
			case COND_ENTERED:
				message.setFile("residence2/fortress/fortress_inner_controller004.htm");
				message.replace("%password%", _index == 0 ? NpcString.PASSWORD_HAS_NOT_BEEN_ENTERED : _index == 1 ? NpcString.FIRST_PASSWORD_HAS_BEEN_ENTERED : NpcString.SECOND_PASSWORD_HAS_BEEN_ENTERED);
				message.replace("%try_count%", NpcString.ATTEMPT_S1__3_IS_IN_PROGRESS, _tryCount);
				break;
			case COND_NO_ENTERED:
				message.setFile("residence2/fortress/fortress_inner_controller001.htm");
				break;
		}
		player.sendPacket(message);
	}

	private void generate()
	{
		_invalidatePeriod = 0;
		_tryCount = 0;
		_index = 0;

		for(int i = 0; i < _generated.length; i++)
		{
			_generated[i] = -1;
		}

		int j = 0;
		while(j != LIMIT)
		{
			int val = Rnd.get(0, 9);
			if(ArrayUtils.contains(_generated, val))
			{
				continue;
			}
			_generated[j++] = val;
		}
	}

	private int getCond()
	{
		if(_invalidatePeriod > System.currentTimeMillis())
		{
			return COND_TIMEOUT;
		}
		else if(_tryCount >= LIMIT)  // максимум лимит
		{
			return COND_FAIL;
		}
		else if(_index == 0 && _tryCount == 0)  // ищо ничего никто не клацал
		{
			return COND_NO_ENTERED;
		}
		else if(_index == LIMIT)   // все верно
		{
			return COND_ALL_OK;
		}
		else // не все удал
		{
			return COND_ENTERED;
		}
	}

	public int[] getGenerated()
	{
		return _generated;
	}
}
