package quests;

import studio.lineage2.gameserver.model.base.Race;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _168_DeliverSupplies extends Quest
{
	int JENNIES_LETTER_ID = 1153;
	int SENTRY_BLADE1_ID = 1154;
	int SENTRY_BLADE2_ID = 1155;
	int SENTRY_BLADE3_ID = 1156;
	int OLD_BRONZE_SWORD_ID = 1157;

	public _168_DeliverSupplies()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30349);

		addTalkId(30349);
		addTalkId(30355);
		addTalkId(30357);
		addTalkId(30360);

		addQuestItem(new int[]
		{ SENTRY_BLADE1_ID, OLD_BRONZE_SWORD_ID, JENNIES_LETTER_ID, SENTRY_BLADE2_ID, SENTRY_BLADE3_ID });

		addLevelCheck(3, 6);
		addRaceCheck(false, false, true, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("1"))
		{
			st.set("id", "0");
			st.setCond(1);
			htmltext = "30349-03.htm";
			st.giveItems(JENNIES_LETTER_ID, 1);
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		String htmltext = "noquest";
		int cond = st.getCond();
		switch(npcId)
		{
			case 30349:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30349-02.htm";
					else if(st.getPlayer().getRace() != Race.DARKELF)
						htmltext = "30349-00.htm";
					else
						htmltext = "30349-01.htm";
				}
				else if(cond == 1)
					htmltext = "30349-04.htm";
				else if(cond == 2)
				{
					htmltext = "30349-05.htm";
					st.takeItems(SENTRY_BLADE1_ID, 1);
					st.setCond(3);
				}
				else if(cond == 3)
					htmltext = "30349-07.htm";
				else if(cond == 4)
				{
					htmltext = "30349-06.htm";
					st.takeItems(OLD_BRONZE_SWORD_ID, 2);
					st.giveItems(ADENA_ID, 100);
					st.finishQuest();
				}
				break;

			case 30360:
				if(cond == 1 && st.getQuestItemsCount(JENNIES_LETTER_ID) >= 1)
				{
					htmltext = "30360-01.htm";
					st.takeItems(JENNIES_LETTER_ID, 1);
					st.giveItems(SENTRY_BLADE1_ID, 1);
					st.giveItems(SENTRY_BLADE2_ID, 1);
					st.giveItems(SENTRY_BLADE3_ID, 1);
					st.setCond(2);
				}
				else if(cond == 2 || cond == 3)
					htmltext = "30360-02.htm";
				break;

			case 30355:
				if(cond == 3 && st.getQuestItemsCount(SENTRY_BLADE2_ID) != 0)
				{
					htmltext = "30355-01.htm";
					st.takeItems(SENTRY_BLADE2_ID, 1);
					st.giveItems(OLD_BRONZE_SWORD_ID, 1);
					if(st.getQuestItemsCount(SENTRY_BLADE2_ID) == 0 && st.getQuestItemsCount(SENTRY_BLADE3_ID) == 0)
						st.setCond(4);
				}
				else if((cond == 4 || cond == 3) && st.getQuestItemsCount(SENTRY_BLADE2_ID) == 0)
					htmltext = "30355-02.htm";
				break;

			case 30357:
				if(cond == 3 && st.getQuestItemsCount(SENTRY_BLADE3_ID) != 0)
				{
					htmltext = "30357-01.htm";
					st.takeItems(SENTRY_BLADE3_ID, 1);
					st.giveItems(OLD_BRONZE_SWORD_ID, 1);
					if(st.getQuestItemsCount(SENTRY_BLADE2_ID) == 0 && st.getQuestItemsCount(SENTRY_BLADE3_ID) == 0)
						st.setCond(4);
				}
				else if((cond == 4 || cond == 3) && st.getQuestItemsCount(SENTRY_BLADE3_ID) == 0)
					htmltext = "30355-02.htm";
				break;
		}
		return htmltext;
	}
}