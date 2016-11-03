package quests;

import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _155_FindSirWindawood extends Quest
{
	int OFFICIAL_LETTER = 1019;
	int HASTE_POTION = 49036;

	public _155_FindSirWindawood()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30042);

		addTalkId(30042);
		addTalkId(30311);

		addQuestItem(OFFICIAL_LETTER);

		addLevelCheck(3, 6);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("30042-04.htm"))
		{
			st.giveItems(OFFICIAL_LETTER, 1);
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
			case 30042:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30042-03.htm";
					else
						htmltext = "30042-02.htm";
				}
				else if(cond == 1)
					htmltext = "30042-05.htm";
				break;

			case 30311:
				if(cond == 1)
				{
					htmltext = "30311-01.htm";
					st.takeItems(OFFICIAL_LETTER, -1);
					st.giveItems(HASTE_POTION, 2);
					st.finishQuest();
				}
				break;
		}
		return htmltext;
	}
}