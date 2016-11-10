package quests;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _106_ForgottenTruth extends Quest
{
	int ONYX_TALISMAN1 = 984;
	int ONYX_TALISMAN2 = 985;
	int ANCIENT_SCROLL = 986;
	int ANCIENT_CLAY_TABLET = 987;
	int KARTAS_TRANSLATION = 988;
	int DAGGER = 49049;
	private static final int NEWBIE_SPIRITSHOT = 5790; // Заряд Духа: Без Ранга для Новичков

	public _106_ForgottenTruth()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30358);
		addTalkId(30133);

		addKillId(27070);

		addQuestItem(KARTAS_TRANSLATION, ONYX_TALISMAN1, ONYX_TALISMAN2, ANCIENT_SCROLL, ANCIENT_CLAY_TABLET);

		addLevelCheck(10, 15);
		addRaceCheck(false, false, true, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("tetrarch_thifiell_q0106_05.htm"))
		{
			st.giveItems(ONYX_TALISMAN1, 1);
			st.setCond(1);
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		String htmltext = "noquest";
		int cond = st.getCond();
		if(npcId == 30358)
		{
			if(cond == 0)
			{
				if(checkStartCondition(st.getPlayer()))
					htmltext = "tetrarch_thifiell_q0106_03.htm";
				else if(st.getPlayer().getRace() != Race.DARKELF)
					htmltext = "tetrarch_thifiell_q0106_00.htm";
				else
					htmltext = "tetrarch_thifiell_q0106_02.htm";
			}
			else if(cond == 4)
			{
				htmltext = "tetrarch_thifiell_q0106_07.htm";
				st.takeItems(KARTAS_TRANSLATION, -1);
				st.giveItems(NEWBIE_SPIRITSHOT, 1500);
				st.giveItems(DAGGER, 1);
				st.finishQuest();
			}
			else if(cond > 0)
				htmltext = "tetrarch_thifiell_q0106_06.htm";
		}
		else if(npcId == 30133)
		{
			if(cond == 1)
			{
				htmltext = "karta_q0106_01.htm";
				st.takeItems(ONYX_TALISMAN1, -1);
				st.giveItems(ONYX_TALISMAN2, 1);
				st.setCond(2);
			}
			else if(cond == 2)
				htmltext = "karta_q0106_02.htm";
			else if(cond == 3)
			{
				htmltext = "karta_q0106_03.htm";
				st.takeItems(ONYX_TALISMAN2, -1);
				st.takeItems(ANCIENT_SCROLL, -1);
				st.takeItems(ANCIENT_CLAY_TABLET, -1);
				st.giveItems(KARTAS_TRANSLATION, 1);
				st.setCond(4);
			}
			else if(cond == 4)
				htmltext = "karta_q0106_04.htm";
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		if(npcId == 27070)
		{
			if(st.getCond() == 2 && st.getQuestItemsCount(ONYX_TALISMAN2) > 0)
			{
				if(Rnd.chance(20) && st.getQuestItemsCount(ANCIENT_SCROLL) == 0)
				{
					st.giveItems(ANCIENT_SCROLL, 1);
					st.playSound(SOUND_ITEMGET);
				}
				else if(Rnd.chance(10) && st.getQuestItemsCount(ANCIENT_CLAY_TABLET) == 0)
				{
					st.giveItems(ANCIENT_CLAY_TABLET, 1);
					st.playSound(SOUND_ITEMGET);
				}
			}
		}
		if(st.getQuestItemsCount(ANCIENT_SCROLL) > 0 && st.getQuestItemsCount(ANCIENT_CLAY_TABLET) > 0)
			st.setCond(3);
		return null;
	}
}