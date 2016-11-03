package quests;

import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _107_MercilessPunishment extends Quest
{
	int HATOSS_ORDER1 = 1553;
	int HATOSS_ORDER2 = 1554;
	int HATOSS_ORDER3 = 1555;
	int LETTER_TO_HUMAN = 1557;
	int LETTER_TO_DARKELF = 1556;
	int LETTER_TO_ELF = 1558;
	int BUTCHER = 49052;
	private static final int NEWBIE_SOULSHOT = 5789; // Заряд Души: Без Ранга для Новичков

	public _107_MercilessPunishment()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30568);

		addTalkId(30580);

		addKillId(27041);

		addQuestItem(LETTER_TO_DARKELF, LETTER_TO_HUMAN, LETTER_TO_ELF, HATOSS_ORDER1, HATOSS_ORDER2, HATOSS_ORDER3);

		addLevelCheck(10, 16);
		addRaceCheck(false, false, false, true, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("urutu_chief_hatos_q0107_03.htm"))
		{
			st.giveItems(HATOSS_ORDER1, 1);
			st.setCond(1);
		}
		else if(event.equalsIgnoreCase("urutu_chief_hatos_q0107_06.htm"))
		{
			st.takeItems(HATOSS_ORDER2, 1);
			st.takeItems(LETTER_TO_DARKELF, 1);
			st.takeItems(LETTER_TO_HUMAN, 1);
			st.takeItems(LETTER_TO_ELF, 1);
			st.takeItems(HATOSS_ORDER1, 1);
			st.takeItems(HATOSS_ORDER2, 1);
			st.takeItems(HATOSS_ORDER3, 1);
			if(st.getCond() == 3)
				st.giveItems(ADENA_ID, 300);
			else if(st.getCond() == 5)
				st.giveItems(ADENA_ID, 600);
			st.giveItems(BUTCHER, 1);
			st.finishQuest();
		}
		else if(event.equalsIgnoreCase("urutu_chief_hatos_q0107_07.htm"))
		{
			st.takeItems(HATOSS_ORDER1, 1);
			if(st.getQuestItemsCount(HATOSS_ORDER2) == 0)
				st.giveItems(HATOSS_ORDER2, 1);
		}
		else if(event.equalsIgnoreCase("urutu_chief_hatos_q0107_09.htm"))
		{
			st.takeItems(HATOSS_ORDER2, 1);
			if(st.getQuestItemsCount(HATOSS_ORDER3) == 0)
				st.giveItems(HATOSS_ORDER3, 1);
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		String htmltext = "noquest";
		int cond = st.getCond();
		if(npcId == 30568)
		{
			if(cond == 0)
			{
				if(checkStartCondition(st.getPlayer()))
					htmltext = "urutu_chief_hatos_q0107_02.htm";
				else if(st.getPlayer().getRace() != Race.ORC)
					htmltext = "urutu_chief_hatos_q0107_00.htm";
				else
					htmltext = "urutu_chief_hatos_q0107_01.htm";
			}
			else if(cond == 1)
				htmltext = "urutu_chief_hatos_q0107_04.htm";
			else if(cond == 2)
				htmltext = "urutu_chief_hatos_q0107_04.htm";
			else if(cond == 3)
			{
				htmltext = "urutu_chief_hatos_q0107_05.htm";
				st.setCond(4);
			}
			else if(cond == 4)
				htmltext = "urutu_chief_hatos_q0107_05.htm";
			else if(cond == 5)
			{
				htmltext = "urutu_chief_hatos_q0107_08.htm";
				st.setCond(6);
			}
			else if(cond == 6)
				htmltext = "urutu_chief_hatos_q0107_08.htm";
			else if(cond == 7)
			{
				htmltext = "urutu_chief_hatos_q0107_10.htm";
				st.takeItems(LETTER_TO_DARKELF, -1);
				st.takeItems(LETTER_TO_HUMAN, -1);
				st.takeItems(LETTER_TO_ELF, -1);
				st.takeItems(HATOSS_ORDER3, -1);
				st.giveItems(NEWBIE_SOULSHOT, 3000);
				st.giveItems(BUTCHER, 1);
				st.finishQuest();
			}
		}
		else if(npcId == 30580)
		{
			if(cond == 1)
				st.setCond(2);
			htmltext = "centurion_parugon_q0107_01.htm";
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		int cond = st.getCond();
		if(npcId == 27041)
		{
			if(cond == 2 && st.getQuestItemsCount(HATOSS_ORDER1) > 0 && st.getQuestItemsCount(LETTER_TO_HUMAN) == 0)
			{
				st.giveItems(LETTER_TO_HUMAN, 1);
				st.setCond(3);
			}
			else if(cond == 4 && st.getQuestItemsCount(HATOSS_ORDER2) > 0 && st.getQuestItemsCount(LETTER_TO_DARKELF) == 0)
			{
				st.giveItems(LETTER_TO_DARKELF, 1);
				st.setCond(5);
			}
			else if(cond == 6 && st.getQuestItemsCount(HATOSS_ORDER3) > 0 && st.getQuestItemsCount(LETTER_TO_ELF) == 0)
			{
				st.giveItems(LETTER_TO_ELF, 1);
				st.setCond(7);
			}
		}
		return null;
	}
}