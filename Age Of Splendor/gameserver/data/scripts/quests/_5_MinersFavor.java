package quests;

import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

/**
 * @reworked by Bonux
**/
public final class _5_MinersFavor extends Quest
{
	// NPC's
	private static final int BOLTER = 30554;
	private static final int SHARI = 30517;
	private static final int GARITA = 30518;
	private static final int REED = 30520;
	private static final int BRUNON = 30526;

	// Item's
	private static final int BOLTERS_LIST = 1547;
	private static final int MINING_BOOTS = 1548;
	private static final int MINERS_PICK = 1549;
	private static final int BOOMBOOM_POWDER = 1550;
	private static final int REDSTONE_BEER = 1551;
	private static final int BOLTERS_SMELLY_SOCKS = 1552;
	private static final int NECKLACE = 49039; // Ожерелье Новичка

	public _5_MinersFavor()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(BOLTER);
		addTalkId(SHARI, GARITA, REED, BRUNON);
		addQuestItem(BOLTERS_LIST, BOLTERS_SMELLY_SOCKS, MINING_BOOTS, MINERS_PICK, BOOMBOOM_POWDER, REDSTONE_BEER);
		addLevelCheck(2, 5);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("miner_bolter_q0005_03.htm"))
		{
			st.setCond(1);
			st.giveItems(BOLTERS_LIST, 1);
			st.giveItems(BOLTERS_SMELLY_SOCKS, 1);
		}
		else if(event.equalsIgnoreCase("blacksmith_bronp_q0005_02.htm"))
		{
			st.takeItems(BOLTERS_SMELLY_SOCKS, -1);
			st.giveItems(MINERS_PICK, 1);
			if(st.haveQuestItem(BOLTERS_LIST) && st.haveQuestItem(MINING_BOOTS) && st.haveQuestItem(MINERS_PICK) && st.haveQuestItem(BOOMBOOM_POWDER) && st.haveQuestItem(REDSTONE_BEER))
				st.setCond(2);
			else
				st.playSound(SOUND_ITEMGET);
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int npcId = npc.getNpcId();
		int cond = st.getCond();
		switch(npcId)
		{
			case BOLTER:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "miner_bolter_q0005_02.htm";
					else
						htmltext = "miner_bolter_q0005_01.htm";
				}
				else if(cond == 1)
					htmltext = "miner_bolter_q0005_04.htm";
				else if(cond == 2)
				{
					htmltext = "miner_bolter_q0005_06.htm";
					st.takeItems(MINING_BOOTS, -1);
					st.takeItems(MINERS_PICK, -1);
					st.takeItems(BOOMBOOM_POWDER, -1);
					st.takeItems(REDSTONE_BEER, -1);
					st.takeItems(BOLTERS_LIST, -1);
					st.giveItems(NECKLACE, 1);
					st.finishQuest();
				}
				break;
			case SHARI:
				if(cond == 1 && st.haveQuestItem(BOLTERS_LIST))
				{
					if(!st.haveQuestItem(BOOMBOOM_POWDER))
					{
						htmltext = "trader_chali_q0005_01.htm";
						st.giveItems(BOOMBOOM_POWDER, 1);
						if(st.haveQuestItem(BOLTERS_LIST) && st.haveQuestItem(MINING_BOOTS) && st.haveQuestItem(MINERS_PICK) && st.haveQuestItem(BOOMBOOM_POWDER) && st.haveQuestItem(REDSTONE_BEER))
							st.setCond(2);
						else
							st.playSound(SOUND_ITEMGET);
					}
					else
						htmltext = "trader_chali_q0005_02.htm";
				}
				break;
			case GARITA:
				if(cond == 1 && st.haveQuestItem(BOLTERS_LIST))
				{
					if(!st.haveQuestItem(MINING_BOOTS))
					{
						htmltext = "trader_garita_q0005_01.htm";
						st.giveItems(MINING_BOOTS, 1);
						if(st.haveQuestItem(BOLTERS_LIST) && st.haveQuestItem(MINING_BOOTS) && st.haveQuestItem(MINERS_PICK) && st.haveQuestItem(BOOMBOOM_POWDER) && st.haveQuestItem(REDSTONE_BEER))
							st.setCond(2);
						else
							st.playSound(SOUND_ITEMGET);
					}
					else
						htmltext = "trader_garita_q0005_02.htm";
				}
				break;
			case REED:
				if(cond == 1 && st.haveQuestItem(BOLTERS_LIST))
				{
					if(!st.haveQuestItem(REDSTONE_BEER))
					{
						htmltext = "warehouse_chief_reed_q0005_01.htm";
						st.giveItems(REDSTONE_BEER, 1);
						if(st.haveQuestItem(BOLTERS_LIST) && st.haveQuestItem(MINING_BOOTS) && st.haveQuestItem(MINERS_PICK) && st.haveQuestItem(BOOMBOOM_POWDER) && st.haveQuestItem(REDSTONE_BEER))
							st.setCond(2);
						else
							st.playSound(SOUND_ITEMGET);
					}
					else
						htmltext = "warehouse_chief_reed_q0005_02.htm";
				}
				break;
			case BRUNON:
				if(cond == 1 && st.haveQuestItem(BOLTERS_LIST) && st.haveQuestItem(BOLTERS_SMELLY_SOCKS))
				{
					if(!st.haveQuestItem(MINERS_PICK))
						htmltext = "blacksmith_bronp_q0005_01.htm";
					else
						htmltext = "blacksmith_bronp_q0005_03.htm";
				}
				break;
		}
		return htmltext;
	}
}
