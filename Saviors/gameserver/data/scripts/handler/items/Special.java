package handler.items;

import bosses.AntharasManager;
import bosses.ValakasManager;
import studio.lineage2.commons.lang.ArrayUtils;
import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.data.QuestHolder;
import studio.lineage2.gameserver.data.xml.holder.SkillHolder;
import studio.lineage2.gameserver.instancemanager.ReflectionManager;
import studio.lineage2.gameserver.model.GameObject;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.Zone.ZoneType;
import studio.lineage2.gameserver.model.base.ClassType2;
import studio.lineage2.gameserver.model.instances.DoorInstance;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;
import studio.lineage2.gameserver.network.l2.components.CustomMessage;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.network.l2.s2c.MagicSkillUse;
import studio.lineage2.gameserver.network.l2.s2c.SystemMessage;
import studio.lineage2.gameserver.utils.ItemFunctions;
import studio.lineage2.gameserver.utils.Location;
import studio.lineage2.gameserver.utils.NpcUtils;

public class Special extends SimpleItemHandler
{
	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		int itemId = item.getItemId();

		switch(itemId)
		{
			case 8060:
				return use8060(player, ctrl);
			case 13853:
				return use13853(player, ctrl);
			case 14835:
				return use14835(player, ctrl);
			case 15537:
				return use15537(player, ctrl);
			case 21899:
				return use21899(player, ctrl);
			case 21900:
				return use21900(player, ctrl);
			case 21901:
				return use21901(player, ctrl);
			case 21902:
				return use21902(player, ctrl);
			case 21903:
				return use21903(player, ctrl);
			case 21904:
				return use21904(player, ctrl);
			//Antharas Blood Crystal
			case 17268:
				return use17268(player, ctrl);
			case 17619: //cruma quest
				return use17619(player, ctrl);
			case 17604: //megameld quest
				return use17604(player, ctrl);
			case 34033:
				return use34033(player, ctrl);
			case 37314:
				return use37314(player, ctrl);
			case 39629:
				return use39629(player, ctrl);
			case 39630:
				return use39630(player, ctrl);
			case 39631:
				return use39631(player, ctrl);
			case 39632:
				return use39632(player, ctrl);
			case 39633:
				return use39633(player, ctrl);
			case 36065:
				return use36065(player, ctrl);
			default:
				return false;
		}
	}

	//Мешочек Кладоискателя - Ур. 1
	private boolean use39629(Player player, boolean ctrl)
	{
		if(player.getLevel() >= 85)
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(39629));
			return false;
		}

		else if(ItemFunctions.deleteItem(player, 39629, 1))
		{
			player.addExpAndSp(400000, 0);
			return true;
		}
		return false;
	}

	//Мешочек Кладоискателя - Ур. 2
	private boolean use39630(Player player, boolean ctrl)
	{
		if(player.getLevel() >= 85)
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(39630));
			return false;
		}

		else if(ItemFunctions.deleteItem(player, 39630, 1))
		{
			player.addExpAndSp(1600000, 0);
			return true;
		}
		return false;
	}

	//Мешочек Кладоискателя - Ур. 3
	private boolean use39631(Player player, boolean ctrl)
	{
		if(player.getLevel() >= 85)
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(39631));
			return false;
		}

		else if(ItemFunctions.deleteItem(player, 39631, 1))
		{
			if(Rnd.chance(1))
			{
				ItemFunctions.addItem(player, 39631, 10);
			}
			else if(Rnd.chance(5))
			{
				ItemFunctions.addItem(player, 39386, 1);
			}
			else if(Rnd.chance(3))
			{
				ItemFunctions.addItem(player, 39387, 1);
			}
			else if(Rnd.chance(1))
			{
				ItemFunctions.addItem(player, 39388, 1);
			}
			else if(Rnd.chance(2))
			{
				ItemFunctions.addItem(player, 39720, 1);
			}

			if(Rnd.get(1, 1000) == 5)
			{
				ItemFunctions.addItem(player, 57, 200000000);
			}
			else if(Rnd.get(1, 1000) < 6)
			{
				ItemFunctions.addItem(player, 57, 20000000);
			}
			else if(Rnd.chance(1))
			{
				ItemFunctions.addItem(player, 57, 2000000);
			}
			else if(Rnd.chance(15))
			{
				ItemFunctions.addItem(player, 57, 200000);
			}
			else
			{
				ItemFunctions.addItem(player, 57, 20000);
			}
			player.addExpAndSp(6400000, 0);
			return true;
		}
		return false;
	}

	//Мешочек Кладоискателя - Ур. 4
	private boolean use39632(Player player, boolean ctrl)
	{
		if(player.getLevel() < 85)
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(39632));
			return false;
		}

		else if(ItemFunctions.deleteItem(player, 39632, 1))
		{
			player.addExpAndSp(0, 80000);
			return true;
		}
		return false;
	}

	//Мешочек Кладоискателя - Ур. 5
	private boolean use39633(Player player, boolean ctrl)
	{
		if(player.getLevel() < 85)
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(39633));
			return false;
		}

		else if(ItemFunctions.deleteItem(player, 39633, 1))
		{
			if(Rnd.chance(4))
			{
				ItemFunctions.addItem(player, 39633, 10);
			}
			else if(Rnd.chance(2))
			{
				ItemFunctions.addItem(player, 39388, 1);
			}
			else if(Rnd.chance(1))
			{
				ItemFunctions.addItem(player, 39389, 1);
			}
			else if(Rnd.chance(0.5))
			{
				ItemFunctions.addItem(player, 39390, 1);
			}
			else if(Rnd.chance(15))
			{
				ItemFunctions.addItem(player, 39720, 1);
			}

			if(Rnd.chance(3))
			{
				ItemFunctions.addItem(player, 57, 100000000);
			}
			else if(Rnd.chance(5))
			{
				ItemFunctions.addItem(player, 57, 10000000);
			}
			else if(Rnd.chance(15))
			{
				ItemFunctions.addItem(player, 57, 1000000);
			}
			else if(Rnd.get(1, 1000) < 5)
			{
				ItemFunctions.addItem(player, 57, 1000000000);
			}
			else
			{
				ItemFunctions.addItem(player, 57, 100000);
			}

			player.addExpAndSp(0, 256000);
			return true;
		}
		return false;
	}

	//Key of Enigma
	private boolean use8060(Player player, boolean ctrl)
	{
		if(ItemFunctions.deleteItem(player, 8058, 1))
		{
			ItemFunctions.addItem(player, 8059, 1);
			return true;
		}
		return false;
	}

	//DestroyedDarknessFragmentPowder -> DestroyedLightFragmentPowde
	private boolean use13853(Player player, boolean ctrl)
	{
		if(!player.isInZone(ZoneType.mother_tree))
		{
			player.sendPacket(SystemMsg.THERE_WAS_NOTHING_FOUND_INSIDE);
			return false;
		}
		useItem(player, 13853, 1);
		ItemFunctions.addItem(player, 13854, 1);
		return true;
	}

	private boolean use14835(Player player, boolean ctrl)
	{
		//TODO [G1ta0] добавить кучу других проверок на возможность телепорта
		if(player.isActionsDisabled() || player.isInOlympiadMode() || player.isInZone(ZoneType.no_escape))
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(14835));
			return false;
		}

		useItem(player, 14835, 1);
		//Stakato nest entrance
		player.teleToLocation(89464, -44712, -2167, ReflectionManager.DEFAULT);
		return true;
	}

	//Strongbox of Promise
	private boolean use15537(Player player, boolean ctrl)
	{
		QuestState qs = player.getQuestState(464);
		if(player.getLevel() >= 82 && qs == null)
		{
			useItem(player, 15537, 1);
			ItemFunctions.addItem(player, 15538, 1);
			Quest q = QuestHolder.getInstance().getQuest(464);
			QuestState st = player.getQuestState(q.getId());
			st = q.newQuestState(player);
			st.setCond(1);
		}
		else
		{
			player.sendMessage(new CustomMessage("Quest._464_Oath.QuestCannotBeTaken"));
			return false;
		}
		return true;
	}

	//Totem
	private boolean use21899(Player player, boolean ctrl)
	{
		if(!player.isInZone(AntharasManager.getZone()) && !player.isInZone(ValakasManager.getZone()))
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(21899));
			return false;
		}
		NpcUtils.spawnSingle(143, Location.findPointToStay(player.getLoc(), 50, 100, player.getGeoIndex()));
		return true;
	}

	//Totem
	private boolean use21900(Player player, boolean ctrl)
	{
		if(!player.isInZone(AntharasManager.getZone()) && !player.isInZone(ValakasManager.getZone()))
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(21900));
			return false;
		}
		NpcUtils.spawnSingle(144, Location.findPointToStay(player.getLoc(), 50, 100, player.getGeoIndex()));
		return true;
	}

	//Totem
	private boolean use21901(Player player, boolean ctrl)
	{
		if(!player.isInZone(AntharasManager.getZone()) && !player.isInZone(ValakasManager.getZone()))
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(21901));
			return false;
		}
		NpcUtils.spawnSingle(145, Location.findPointToStay(player.getLoc(), 50, 100, player.getGeoIndex()));
		return true;
	}

	//Totem
	private boolean use21902(Player player, boolean ctrl)
	{
		if(!player.isInZone(AntharasManager.getZone()) && !player.isInZone(ValakasManager.getZone()))
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(21902));
			return false;
		}
		NpcUtils.spawnSingle(146, Location.findPointToStay(player.getLoc(), 50, 100, player.getGeoIndex()));
		return true;
	}

	// Refined Red Dragon Blood
	private boolean use21903(Player player, boolean ctrl)
	{
		if(!player.isInZone(AntharasManager.getZone()) && !player.isInZone(ValakasManager.getZone()))
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(21903));
			return false;
		}
		player.doCast(SkillHolder.getInstance().getSkillEntry(22298, 1), player, false);
		ItemFunctions.deleteItem(player, 21903, 1);
		return true;
	}

	// Refined Blue Dragon Blood
	private boolean use21904(Player player, boolean ctrl)
	{
		if(!player.isInZone(AntharasManager.getZone()) && !player.isInZone(ValakasManager.getZone()))
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(21904));
			return false;
		}
		player.doCast(SkillHolder.getInstance().getSkillEntry(22299, 1), player, false);
		ItemFunctions.deleteItem(player, 21904, 1);
		return true;
	}

	// Antharas Blood Crystal
	private boolean use17268(Player player, boolean ctrl)
	{
		if(!player.isInZone(AntharasManager.getZone()))
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(17268));
			return false;
		}
		player.doCast(SkillHolder.getInstance().getSkillEntry(9179, 1), player, false);
		ItemFunctions.deleteItem(player, 17268, 1);
		return true;
	}

	private boolean use17619(Player player, boolean ctrl)
	{
		//TODO[Iqman+Nosferatu] Define zone in cruma tower we can use this scroll only there!!
		QuestState qs = player.getQuestState(10352);
		QuestState qs2 = player.getQuestState(480);
		if(player.getVar("MechanismSpawn") != null || qs == null || qs.getCond() > 4)
		{
			if(qs2 == null || qs2.getCond() > 4 || player.getVar("MechanismSpawn") != null)
			{
				player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(17619));
				return false;
			}
		}

		ItemFunctions.deleteItem(player, 17619, 1);
		NpcInstance npc = NpcUtils.spawnSingle(17619, Location.findPointToStay(player.getLoc(), 50, 100, player.getGeoIndex()), 120000L);
		player.setVar("MechanismSpawn", "1", System.currentTimeMillis() + 120000L);
		if(qs != null && !qs.isCompleted())
		{
			Quest q = QuestHolder.getInstance().getQuest(10352);
			player.processQuestEvent(q.getId(), "advanceCond3", null);
		}
		if(qs2 != null && !qs2.isCompleted())
		{
			Quest q2 = QuestHolder.getInstance().getQuest(480);
			player.processQuestEvent(q2.getId(), "advanceCond3", null);
		}
		return true;
	}

	private boolean use17604(Player player, boolean ctrl)
	{
		//TODO[Iqman+Nosferatu] Define zone in cruma tower we can use this scroll only there!!
		QuestState qs = player.getQuestState(10301);
		GameObject target = player.getTarget();
		if(target == null || !target.isNpc())
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(17604));
			return false;
		}

		NpcInstance _target = (NpcInstance) target;
		if(_target.getNpcId() != 33489)
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(17604));
			return false;
		}

		if(qs == null || qs.getCond() != 2 || player.getVar("CrystalsSpawn") != null)
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(17604));
			return false;
		}

		ItemFunctions.deleteItem(player, 17604, 1);
		NpcInstance npc = NpcUtils.spawnSingle(32938, Location.findPointToStay(player.getLoc(), 50, 100, player.getGeoIndex()), 120000L);
		NpcInstance npc2 = NpcUtils.spawnSingle(32938, Location.findPointToStay(player.getLoc(), 50, 100, player.getGeoIndex()), 120000L);
		player.setVar("CrystalsSpawn", "1", System.currentTimeMillis() + 120000L);
		return true;
	}

	private boolean use34033(Player player, boolean ctrl)
	{
		QuestState qs = player.getQuestState(10304);
		HtmlMessage msg = new HtmlMessage(5);
		if(player.getLevel() >= 90 && qs == null)
		{
			Quest q = QuestHolder.getInstance().getQuest(10304);
			QuestState st = player.getQuestState(q.getId());
			st = q.newQuestState(player);
			st.setCond(1);
			useItem(player, 34033, 1);
			ItemFunctions.addItem(player, 17618, 1);
			msg.setFile("quests/_10304_ForTheForgottenHeroes/2.htm");
			player.sendPacket(msg);
		}
		else
		{
			msg.setFile("quests/_10304_ForTheForgottenHeroes/4.htm");
			player.sendPacket(msg);
			return false;
		}
		return true;
	}

	private boolean use37314(Player player, boolean ctrl)
	{
		useItem(player, 37314, 1);
		switch(player.getRace())
		{
			case ORC:
				if(player.isMageClass())
				{
					ItemFunctions.addItem(player, 37321, 1);
				}
				else
				{
					ItemFunctions.addItem(player, 37320, 1);
				}
				break;
			default:
				if(player.isMageClass())
				{
					ItemFunctions.addItem(player, 37316, 1);
				}
				else
				{
					if(player.getClassId().getType2() == ClassType2.WARRIOR)
					{
						ItemFunctions.addItem(player, 37315, 1);
					}
					else if(player.getClassId().getType2() == ClassType2.ROGUE || player.getClassId().getType2() == ClassType2.ARCHER)
					{
						ItemFunctions.addItem(player, 37318, 1);
					}
					else if(player.getClassId().getType2() == ClassType2.KNIGHT)
					{
						ItemFunctions.addItem(player, 37317, 1);
					}
				}
				break;
		}
		return true;
	}

	private boolean use36065(Player player, boolean ctrl)
	{
		QuestState qs = player.getQuestState(753);
		if(qs != null && qs.getCond() == 1)
		{
			if(player.getTarget() instanceof NpcInstance)
			{
				if(((NpcInstance) player.getTarget()).getNpcId() == 19296)
				{
					GameObject target = player.getTarget();
					((NpcInstance) player.getTarget()).doDie(player);
					NpcInstance npc = (NpcInstance) target;
					//player.broadcastPacket(new MagicSkillUse(player, npc, 9584, 1, 2000, 0));
					player.forceUseSkill(SkillHolder.getInstance().getSkill(9584, 1), npc);
					return true;
				}
			}
		}
		return false;
	}

	private static boolean useItem(Player player, int itemId, long count)
	{
		player.sendPacket(new SystemMessage(SystemMessage.YOU_USE_S1).addItemName(itemId));
		return ItemFunctions.deleteItem(player, itemId, count);
	}
}