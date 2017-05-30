package npc.model.residences.clanhall;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.commons.threading.RunnableImpl;
import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ThreadPoolManager;
import studio.lineage2.gameserver.model.GameObject;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.World;
import studio.lineage2.gameserver.model.entity.events.impl.ClanHallMiniGameEvent;
import studio.lineage2.gameserver.model.entity.events.objects.CMGSiegeClanObject;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.network.l2.s2c.ExShowScreenMessage;
import studio.lineage2.gameserver.network.l2.s2c.SystemMessagePacket;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.ItemFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author VISTALL
 * @date 13:49/11.05.2011
 * 750
 */
public class RainbowYetiInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	private static final int ItemA = 8035;
	private static final int ItemB = 8036;
	private static final int ItemC = 8037;
	private static final int ItemD = 8038;
	private static final int ItemE = 8039;
	private static final int ItemF = 8040;
	private static final int ItemG = 8041;
	private static final int ItemH = 8042;
	private static final int ItemI = 8043;
	private static final int ItemK = 8045;
	private static final int ItemL = 8046;
	private static final int ItemN = 8047;
	private static final int ItemO = 8048;
	private static final int ItemP = 8049;
	private static final int ItemR = 8050;
	private static final int ItemS = 8051;
	private static final int ItemT = 8052;
	private static final int ItemU = 8053;
	private static final int ItemW = 8054;
	private static final int ItemY = 8055;

	private static class Word
	{
		private final String _name;
		private final int[][] _items;

		public Word(String name, int[]... items)
		{
			_name = name;
			_items = items;
		}

		public String getName()
		{
			return _name;
		}

		public int[][] getItems()
		{
			return _items;
		}
	}

	private class GenerateTask extends RunnableImpl
	{
		@Override
		public void runImpl() throws Exception
		{
			_generated = Rnd.get(WORLD_LIST.length);
			Word word = WORLD_LIST[_generated];

			List<Player> around = World.getAroundPlayers(RainbowYetiInstance.this, 750, 100);
			ExShowScreenMessage msg = new ExShowScreenMessage(NpcString.NONE, 5000, ExShowScreenMessage.ScreenMessageAlign.TOP_CENTER, word.getName());
			for(Player player : around)
			{
				player.sendPacket(msg);
			}
		}
	}

	private static final Word[] WORLD_LIST = new Word[8];

	static
	{
		WORLD_LIST[0] = new Word("BABYDUCK", new int[] {
				ItemB,
				2
		}, new int[] {
				ItemA,
				1
		}, new int[] {
				ItemY,
				1
		}, new int[] {
				ItemD,
				1
		}, new int[] {
				ItemU,
				1
		}, new int[] {
				ItemC,
				1
		}, new int[] {
				ItemK,
				1
		});
		WORLD_LIST[1] = new Word("ALBATROS", new int[] {
				ItemA,
				2
		}, new int[] {
				ItemL,
				1
		}, new int[] {
				ItemB,
				1
		}, new int[] {
				ItemT,
				1
		}, new int[] {
				ItemR,
				1
		}, new int[] {
				ItemO,
				1
		}, new int[] {
				ItemS,
				1
		});
		WORLD_LIST[2] = new Word("PELICAN", new int[] {
				ItemP,
				1
		}, new int[] {
				ItemE,
				1
		}, new int[] {
				ItemL,
				1
		}, new int[] {
				ItemI,
				1
		}, new int[] {
				ItemC,
				1
		}, new int[] {
				ItemA,
				1
		}, new int[] {
				ItemN,
				1
		});
		WORLD_LIST[3] = new Word("KINGFISHER", new int[] {
				ItemK,
				1
		}, new int[] {
				ItemI,
				1
		}, new int[] {
				ItemN,
				1
		}, new int[] {
				ItemG,
				1
		}, new int[] {
				ItemF,
				1
		}, new int[] {
				ItemI,
				1
		}, new int[] {
				ItemS,
				1
		}, new int[] {
				ItemH,
				1
		}, new int[] {
				ItemE,
				1
		}, new int[] {
				ItemR,
				1
		});
		WORLD_LIST[4] = new Word("CYGNUS", new int[] {
				ItemC,
				1
		}, new int[] {
				ItemY,
				1
		}, new int[] {
				ItemG,
				1
		}, new int[] {
				ItemN,
				1
		}, new int[] {
				ItemU,
				1
		}, new int[] {
				ItemS,
				1
		});
		WORLD_LIST[5] = new Word("TRITON", new int[] {
				ItemT,
				2
		}, new int[] {
				ItemR,
				1
		}, new int[] {
				ItemI,
				1
		}, new int[] {
				ItemN,
				1
		});
		WORLD_LIST[6] = new Word("RAINBOW", new int[] {
				ItemR,
				1
		}, new int[] {
				ItemA,
				1
		}, new int[] {
				ItemI,
				1
		}, new int[] {
				ItemN,
				1
		}, new int[] {
				ItemB,
				1
		}, new int[] {
				ItemO,
				1
		}, new int[] {
				ItemW,
				1
		});
		WORLD_LIST[7] = new Word("SPRING", new int[] {
				ItemS,
				1
		}, new int[] {
				ItemP,
				1
		}, new int[] {
				ItemR,
				1
		}, new int[] {
				ItemI,
				1
		}, new int[] {
				ItemN,
				1
		}, new int[] {
				ItemG,
				1
		});
	}

	private List<GameObject> _mobs = new ArrayList<GameObject>();
	private int _generated = -1;
	private Future<?> _task = null;

	public RainbowYetiInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
		_hasRandomWalk = false;
	}

	@Override
	public void onSpawn()
	{
		super.onSpawn();
		ClanHallMiniGameEvent event = getEvent(ClanHallMiniGameEvent.class);
		if(event == null)
		{
			return;
		}

		List<Player> around = World.getAroundPlayers(this, 750, 100);
		for(Player player : around)
		{
			CMGSiegeClanObject siegeClanObject = event.getSiegeClan(ClanHallMiniGameEvent.ATTACKERS, player.getClan());

			if(siegeClanObject == null || !siegeClanObject.getPlayers().contains(player.getObjectId()))
			{
				player.teleToLocation(event.getResidence().getOtherRestartPoint());
			}
		}

		_task = ThreadPoolManager.getInstance().scheduleAtFixedRate(new GenerateTask(), 10000L, 300000L);
	}

	@Override
	public void onDelete()
	{
		super.onDelete();
		if(_task != null)
		{
			_task.cancel(false);
			_task = null;
		}

		for(GameObject object : _mobs)
		{
			object.deleteMe();
		}

		_mobs.clear();
	}

	public void teleportFromArena()
	{
		ClanHallMiniGameEvent event = getEvent(ClanHallMiniGameEvent.class);
		if(event == null)
		{
			return;
		}
		List<Player> around = World.getAroundPlayers(this, 750, 100);
		for(Player player : around)
		{
			player.teleToLocation(event.getResidence().getOtherRestartPoint());
		}
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		if(command.equalsIgnoreCase("get"))
		{
			HtmlMessage msg = new HtmlMessage(this);
			boolean has = true;
			if(_generated == -1)
			{
				has = false;
			}
			else
			{
				Word word = WORLD_LIST[_generated];

				for(int[] itemInfo : word.getItems())
				{
					if(player.getInventory().getCountOf(itemInfo[0]) < itemInfo[1])
					{
						has = false;
					}
				}

				if(has)
				{
					for(int[] itemInfo : word.getItems())
					{
						if(!player.consumeItem(itemInfo[0], itemInfo[1], true))
						{
							return;
						}
					}

					int rnd = Rnd.get(100);
					if(_generated >= 0 && _generated <= 5)
					{
						if(rnd < 70)
						{
							addItem(player, 8030);
						}
						else if(rnd < 80)
						{
							addItem(player, 8031);
						}
						else if(rnd < 90)
						{
							addItem(player, 8032);
						}
						else
						{
							addItem(player, 8033);
						}
					}
					else
					{
						if(rnd < 10)
						{
							addItem(player, 8030);
						}
						else if(rnd < 40)
						{
							addItem(player, 8031);
						}
						else if(rnd < 70)
						{
							addItem(player, 8032);
						}
						else
						{
							addItem(player, 8033);
						}
					}
				}
			}
			if(!has)
			{
				msg.setFile("residence2/clanhall/watering_manager002.htm");
			}
			else
			{
				msg.setFile("residence2/clanhall/watering_manager004.htm");
			}

			player.sendPacket(msg);
		}
		else if(command.equalsIgnoreCase("see"))
		{
			HtmlMessage msg = new HtmlMessage(this);
			msg.setFile("residence2/clanhall/watering_manager005.htm");
			if(_generated == -1)
			{
				msg.replace("%word%", NpcString.UNDECIDED);
			}
			else
			{
				msg.replace("%word%", WORLD_LIST[_generated].getName());
			}
			player.sendPacket(msg);
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}

	private void addItem(Player player, int itemId)
	{
		ClanHallMiniGameEvent event = getEvent(ClanHallMiniGameEvent.class);
		if(event == null)
		{
			return;
		}

		ItemInstance item = ItemFunctions.createItem(itemId);
		item.addEvent(event);

		player.getInventory().addItem(item);
		player.sendPacket(SystemMessagePacket.obtainItems(item));
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		showChatWindow(player, "residence2/clanhall/watering_manager001.htm", firstTalk);
	}

	public void addMob(GameObject object)
	{
		_mobs.add(object);
	}
}
