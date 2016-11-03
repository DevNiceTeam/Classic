package quests;

import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _164_BloodFiend extends Quest
{
	//NPC
	private static final int Creamees = 30149;
	//Quest Items
	private static final int KirunakSkull = 1044;
	//MOB
	private static final int Kirunak = 27021;

	public _164_BloodFiend()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(Creamees);
		addTalkId(Creamees);
		addKillId(Kirunak);
		addQuestItem(KirunakSkull);

		addLevelCheck(21, 26);
		addRaceCheck(true, true, false, true, true);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("30149-04.htm"))
			st.setCond(1);
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
			case Creamees:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30149-03.htm";
					else if(st.getPlayer().getRace() == Race.DARKELF)
						htmltext = "30149-00.htm";
					else
						htmltext = "30149-02.htm";
				}
				else if(cond == 1)
					htmltext = "30149-05.htm";
				else if(cond == 2 || cond == 3)
				{
					st.takeItems(KirunakSkull, -1);
					st.giveItems(ADENA_ID, 12000);
					htmltext = "30149-06.htm";
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
		int cond = st.getCond();
		if(cond == 1 && npcId == Kirunak)
		{
			if(st.getQuestItemsCount(KirunakSkull) == 0)
				st.giveItems(KirunakSkull, 1);
			st.setCond(2);
		}
		return null;
	}
}