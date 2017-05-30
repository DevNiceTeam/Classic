package quests;

import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _154_SacrificeToSea extends Quest
{
	private static final int FOX_FUR_ID = 1032;
	private static final int FOX_FUR_YARN_ID = 1033;
	private static final int MAIDEN_DOLL_ID = 1034;
	private static final int MYSTICS_EARRING_ID = 49040;

	public _154_SacrificeToSea()
	{
		super(PARTY_ONE, ONETIME);

		addStartNpc(30312);

		addTalkId(30051);
		addTalkId(30055);

		addKillId(20481);
		addKillId(20544);
		addKillId(20545);

		addQuestItem(new int[]
		{ FOX_FUR_ID, FOX_FUR_YARN_ID, MAIDEN_DOLL_ID });

		addLevelCheck(2, 7);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("1"))
		{
			htmltext = "30312-04.htm";
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

		switch(npcId)
		{
			case 30312:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30312-03.htm";
					else
						htmltext = "30312-02.htm";
				}
				else if(cond == 1)
					htmltext = "30312-05.htm";
				else if(cond == 2)
					htmltext = "30312-08.htm";
				else if(cond == 3)
					htmltext = "30312-06.htm";
				if(cond == 4)
				{
					htmltext = "30312-07.htm";
					st.takeItems(MAIDEN_DOLL_ID, st.getQuestItemsCount(MAIDEN_DOLL_ID));
					st.giveItems(MYSTICS_EARRING_ID, 1);
					st.finishQuest();
				}
				break;

			case 30051:
				if(cond == 1)
					htmltext = "30051-01.htm";
				else if(cond == 2)
				{
					htmltext = "30051-02.htm";
					st.giveItems(FOX_FUR_YARN_ID, 1);
					st.takeItems(FOX_FUR_ID, st.getQuestItemsCount(FOX_FUR_ID));
					st.setCond(3);
				}
				else if(cond == 3)
					htmltext = "30051-03.htm";
				else if(cond == 4)
					htmltext = "30051-04.htm";
				break;

			case 30055:
				if(cond == 3)
				{
					htmltext = "30055-01.htm";
					st.giveItems(MAIDEN_DOLL_ID, 1);
					st.takeItems(FOX_FUR_YARN_ID, st.getQuestItemsCount(FOX_FUR_YARN_ID));
					st.setCond(4);
				}
				else if(cond == 4)
					htmltext = "30055-02.htm";
				else
					htmltext = "30055-03.htm";
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		if(st.getCond() == 1 && st.getQuestItemsCount(FOX_FUR_YARN_ID) == 0)
			st.rollAndGive(FOX_FUR_ID, 1, 1, 10, 14);
		if(st.getQuestItemsCount(FOX_FUR_ID) > 9)
			st.setCond(2);
		return null;
	}
}