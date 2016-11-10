package quests;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _163_LegacyOfPoet extends Quest
{
	int RUMIELS_POEM_1_ID = 1038;
	int RUMIELS_POEM_3_ID = 1039;
	int RUMIELS_POEM_4_ID = 1040;
	int RUMIELS_POEM_5_ID = 1041;
	int LESSER_SHIRT = 22;

	public _163_LegacyOfPoet()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30220);

		addTalkId(30220);

		addTalkId(30220);

		addKillId(20372);
		addKillId(20373);

		addQuestItem(new int[]
		{ RUMIELS_POEM_1_ID, RUMIELS_POEM_3_ID, RUMIELS_POEM_4_ID, RUMIELS_POEM_5_ID });

		addLevelCheck(10, 15);
		addRaceCheck(true, true, false, true, true);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("1"))
		{
			htmltext = "30220-07.htm";
			st.setCond(1);
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int cond = st.getCond();
		int npcId = npc.getNpcId();

		switch(npcId)
		{
			case 30220:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30220-03.htm";
					else if(st.getPlayer().getRace() == Race.DARKELF)
						htmltext = "30220-00.htm";
					else
						htmltext = "30220-02.htm";
				}
				else if(cond == 1)
					htmltext = "30220-08.htm";
				else if(cond == 2)
				{
					htmltext = "30220-09.htm";
					st.takeItems(RUMIELS_POEM_1_ID, 1);
					st.takeItems(RUMIELS_POEM_3_ID, 1);
					st.takeItems(RUMIELS_POEM_4_ID, 1);
					st.takeItems(RUMIELS_POEM_5_ID, 1);
					st.giveItems(LESSER_SHIRT, 1);
					st.finishQuest();
				}
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		if(npcId == 20372 || npcId == 20373)
		{
			if(st.getCond() == 1)
			{
				if(Rnd.chance(10) && st.getQuestItemsCount(RUMIELS_POEM_1_ID) == 0)
				{
					st.giveItems(RUMIELS_POEM_1_ID, 1, true);
					st.playSound(SOUND_ITEMGET);
				}
				if(Rnd.chance(70) && st.getQuestItemsCount(RUMIELS_POEM_3_ID) == 0)
				{
					st.giveItems(RUMIELS_POEM_3_ID, 1, true);
					st.playSound(SOUND_ITEMGET);
				}
				if(Rnd.chance(70) && st.getQuestItemsCount(RUMIELS_POEM_4_ID) == 0)
				{
					st.giveItems(RUMIELS_POEM_4_ID, 1, true);
					st.playSound(SOUND_ITEMGET);
				}
				if(Rnd.chance(50) && st.getQuestItemsCount(RUMIELS_POEM_5_ID) == 0)
				{
					st.giveItems(RUMIELS_POEM_5_ID, 1, true);
					st.playSound(SOUND_ITEMGET);
				}
				if(st.haveQuestItem(RUMIELS_POEM_1_ID) && st.haveQuestItem(RUMIELS_POEM_3_ID) && st.haveQuestItem(RUMIELS_POEM_4_ID) && st.haveQuestItem(RUMIELS_POEM_5_ID))
					st.setCond(2);
			}
		}
		return null;
	}

}