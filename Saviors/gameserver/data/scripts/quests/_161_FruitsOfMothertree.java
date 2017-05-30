package quests;

import studio.lineage2.gameserver.model.base.Race;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _161_FruitsOfMothertree extends Quest
{
	private static final int ANDELLRIAS_LETTER_ID = 1036;
	private static final int MOTHERTREE_FRUIT_ID = 1037;

	public _161_FruitsOfMothertree()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30362);
		addTalkId(30371);

		addQuestItem(new int[]
		{ MOTHERTREE_FRUIT_ID, ANDELLRIAS_LETTER_ID });

		addLevelCheck(3, 7);
		addRaceCheck(false, true, false, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("1"))
		{
			htmltext = "30362-04.htm";
			st.giveItems(ANDELLRIAS_LETTER_ID, 1);
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
			case 30362:
				if(st.getCond() == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30362-03.htm";
					else if(st.getPlayer().getRace() != Race.ELF)
						htmltext = "30362-00.htm";
					else
						htmltext = "30362-02.htm";
				}
				else if(st.getCond() == 1)
					htmltext = "30362-05.htm";
				else if(st.getCond() == 2)
				{
					htmltext = "30362-06.htm";
					st.giveItems(ADENA_ID, 100);
					st.takeItems(MOTHERTREE_FRUIT_ID, 1);
					st.finishQuest();
				}
				break;

			case 30371:
				if(st.getCond() == 1)
				{
					htmltext = "30371-01.htm";
					st.giveItems(MOTHERTREE_FRUIT_ID, 1);
					st.takeItems(ANDELLRIAS_LETTER_ID, 1);
					st.setCond(2);
				}
				else if(cond == 2)
					htmltext = "30371-02.htm";
				break;
		}
		return htmltext;
	}
}