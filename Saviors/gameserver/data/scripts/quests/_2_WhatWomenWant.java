package quests;

import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

/**
 * @reworked by Bonux
**/
public final class _2_WhatWomenWant extends Quest
{
	// NPC's
	private static final int ARUJIEN = 30223; // Аруен
	private static final int MIRABEL_GATEKEEPER = 30146; // Мирабель - Хранитель Портала
	private static final int HERBIEL_GROCER = 30150; // Гербиэль - Бакалейщик
	private static final int GREENIS_MAGISTER = 30157; // Гринис - Магистр

	// Item's
	private static final int ARUJIENS_LETTER1 = 1092; // Письмо Аруена
	private static final int ARUJIENS_LETTER2 = 1093; // Письмо Аруена
	private static final int ARUJIENS_LETTER3 = 1094; // Письмо Аруена
	private static final int POETRY_BOOK = 689; // Сборник Стихов
	private static final int GREENIS_LETTER = 693; // Письмо Гринис
	private static final int NEWBIE_EARRING = 49040; // Серьга Новичка

	public _2_WhatWomenWant()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(ARUJIEN);
		addTalkId(MIRABEL_GATEKEEPER);
		addTalkId(HERBIEL_GROCER);
		addTalkId(GREENIS_MAGISTER);
		addQuestItem(GREENIS_LETTER, ARUJIENS_LETTER3, ARUJIENS_LETTER1, ARUJIENS_LETTER2, POETRY_BOOK);
		addLevelCheck(2, 5);
		addRaceCheck(true, true, false, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("arujien_q0002_04.htm"))
		{
			st.setCond(1);
			st.giveItems(ARUJIENS_LETTER1, 1);
		}
		else if(event.equalsIgnoreCase("arujien_q0002_08.htm"))
		{
			st.setCond(4);
			st.takeItems(ARUJIENS_LETTER3, -1);
			st.giveItems(POETRY_BOOK, 1);
		}
		else if(event.equalsIgnoreCase("arujien_q0002_09.htm"))
		{
			st.takeItems(ARUJIENS_LETTER3, -1);
			st.giveItems(NEWBIE_EARRING, 1);
			st.finishQuest();
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
			case ARUJIEN:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "arujien_q0002_02.htm";
					else if(st.getPlayer().getRace() != Race.ELF && st.getPlayer().getRace() != Race.HUMAN)
						htmltext = "arujien_q0002_00.htm";
					else
						htmltext = "arujien_q0002_01.htm";
				}
				else if(cond == 1)
					htmltext = "arujien_q0002_05.htm";
				else if(cond == 2)
					htmltext = "arujien_q0002_06.htm";
				else if(cond == 3)
					htmltext = "arujien_q0002_07.htm";
				else if(cond == 4)
					htmltext = "arujien_q0002_11.htm";
				else if(cond == 5)
				{
					htmltext = "arujien_q0002_10.htm";
					st.takeItems(GREENIS_LETTER, -1);
					st.giveItems(NEWBIE_EARRING, 1);
					st.finishQuest();
				}
				break;
			case MIRABEL_GATEKEEPER:
				if(cond == 1)
				{
					htmltext = "mint_q0002_01.htm";
					st.setCond(2);
					st.takeItems(ARUJIENS_LETTER1, -1);
					st.giveItems(ARUJIENS_LETTER2, 1);
				}
				else if(cond == 2)
					htmltext = "mint_q0002_02.htm";
				break;
			case HERBIEL_GROCER:
				if(cond == 2)
				{
					htmltext = "green_q0002_01.htm";
					st.setCond(3);
					st.takeItems(ARUJIENS_LETTER2, -1);
					st.giveItems(ARUJIENS_LETTER3, 1);
				}
				else if(cond == 3)
					htmltext = "green_q0002_02.htm";
				break;
			case GREENIS_MAGISTER:
				if(cond == 4 && st.haveQuestItem(POETRY_BOOK))
				{
					htmltext = "grain_q0002_02.htm";
					st.setCond(5);
					st.takeItems(POETRY_BOOK, -1);
					st.giveItems(GREENIS_LETTER, 1);
				}
				else if(cond == 5 && st.haveQuestItem(GREENIS_LETTER))
					htmltext = "grain_q0002_03.htm";
				else
					htmltext = "grain_q0002_01.htm";
				break;
		}
		return htmltext;
	}
}
