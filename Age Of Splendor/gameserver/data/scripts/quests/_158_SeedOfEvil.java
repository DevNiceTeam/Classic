package quests;

import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _158_SeedOfEvil extends Quest
{
	int CLAY_TABLET_ID = 1025;
	int SERP = 153;

	public _158_SeedOfEvil()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30031);

		addKillId(27016);

		addQuestItem(CLAY_TABLET_ID);

		addLevelCheck(21, 26);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("1"))
		{
			st.set("id", "0");
			st.setCond(1);
			htmltext = "30031-04.htm";
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
			case 30031:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30031-03.htm";
					else
						htmltext = "30031-02.htm";
				}
				else if(cond == 1)
					htmltext = "30031-05.htm";
				else if(cond == 2)
				{
					st.takeItems(CLAY_TABLET_ID, st.getQuestItemsCount(CLAY_TABLET_ID));
					st.giveItems(ADENA_ID, 5000);
					st.giveItems(SERP, 1);
					htmltext = "30031-06.htm";
					st.finishQuest();
				}
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		if(st.getQuestItemsCount(CLAY_TABLET_ID) == 0)
		{
			st.giveItems(CLAY_TABLET_ID, 1);
			st.setCond(2);
		}
		return null;
	}
}