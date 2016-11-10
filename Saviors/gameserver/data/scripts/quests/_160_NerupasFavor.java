package quests;

import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _160_NerupasFavor extends Quest
{
	private static int SILVERY_SPIDERSILK = 1026;
	private static int UNOS_RECEIPT = 1027;
	private static int CELS_TICKET = 1028;
	private static int NIGHTSHADE_LEAF = 1029;
	private static int LESSER_HEALING_POTION = 1060;

	private final static int NERUPA = 30370;
	private final static int UNOREN = 30147;
	private final static int CREAMEES = 30149;
	private final static int JULIA = 30152;

	public _160_NerupasFavor()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(NERUPA);

		addTalkId(UNOREN, CREAMEES, JULIA);

		addQuestItem(SILVERY_SPIDERSILK, UNOS_RECEIPT, CELS_TICKET, NIGHTSHADE_LEAF);

		addLevelCheck(3, 7);
		addRaceCheck(false, true, false, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("30370-04.htm"))
		{
			st.setCond(1);
			st.giveItems(SILVERY_SPIDERSILK, 1);
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
			case NERUPA:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30370-03.htm";
					else if(st.getPlayer().getRace() != Race.ELF)
						htmltext = "30370-00.htm";
					else
						htmltext = "30370-02.htm";
				}
				else if(cond == 1)
					htmltext = "30370-04.htm";
				else if(cond == 4)
				{
					st.takeItems(NIGHTSHADE_LEAF, -1);
					st.giveItems(LESSER_HEALING_POTION, 1, true);
					htmltext = "30370-06.htm";
					st.finishQuest();
				}
				else
					htmltext = "30370-05.htm";
				break;

			case UNOREN:
				if(cond == 1)
				{
					st.takeItems(SILVERY_SPIDERSILK, -1);
					st.giveItems(UNOS_RECEIPT, 1);
					st.setCond(2);
					htmltext = "30147-01.htm";
				}
				else if(cond == 2 || cond == 3)
					htmltext = "30147-02.htm";
				else if(cond == 4)
					htmltext = "30147-03.htm";
				break;

			case CREAMEES:
				if(cond == 2)
				{
					st.takeItems(UNOS_RECEIPT, -1);
					st.giveItems(CELS_TICKET, 1);
					st.setCond(3);
					htmltext = "30149-01.htm";
				}
				else if(cond == 3)
					htmltext = "30149-02.htm";
				else if(cond == 4)
					htmltext = "30149-03.htm";
				break;

			case JULIA:
				if(cond == 3)
				{
					st.takeItems(CELS_TICKET, -1);
					st.giveItems(NIGHTSHADE_LEAF, 1);
					htmltext = "30152-01.htm";
					st.setCond(4);
				}
				else if(cond == 4)
					htmltext = "30152-02.htm";
				break;
		}
		return htmltext;
	}
}