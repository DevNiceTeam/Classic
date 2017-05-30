package quests;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.model.base.Race;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _159_ProtectHeadsprings extends Quest
{
	int PLAGUE_DUST_ID = 1035;
	int HYACINTH_CHARM1_ID = 1071;
	int HYACINTH_CHARM2_ID = 1072;
	int NEWBIE_RING = 49041;
	int SCROLL_OF_TELEPORT = 736;

	public _159_ProtectHeadsprings()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30154);

		addKillId(27017);

		addQuestItem(new int[]
		{ PLAGUE_DUST_ID, HYACINTH_CHARM1_ID, HYACINTH_CHARM2_ID });

		addLevelCheck(12, 18);
		addRaceCheck(false, true, false, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("1"))
		{
			st.setCond(1);
			if(st.getQuestItemsCount(HYACINTH_CHARM1_ID) == 0)
			{
				st.giveItems(HYACINTH_CHARM1_ID, 1);
				htmltext = "30154-04.htm";
			}
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
			case 30154:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30154-03.htm";
					else if(st.getPlayer().getRace() != Race.ELF)
						htmltext = "30154-00.htm";
					else
						htmltext = "30154-02.htm";
				}
				else if(cond == 1)
					htmltext = "30154-05.htm";
				else if(cond == 2)
				{
					st.takeItems(PLAGUE_DUST_ID, -1);
					st.takeItems(HYACINTH_CHARM1_ID, -1);
					st.giveItems(HYACINTH_CHARM2_ID, 1);
					st.setCond(3);
					htmltext = "30154-06.htm";
				}
				else if(cond == 3)
					htmltext = "30154-07.htm";
				else if(cond == 4)
				{
					st.takeItems(PLAGUE_DUST_ID, -1);
					st.takeItems(HYACINTH_CHARM2_ID, -1);
					st.giveItems(SCROLL_OF_TELEPORT, 1, true);
					st.giveItems(NEWBIE_RING, 1, false);
					htmltext = "30154-08.htm";
					st.finishQuest();
				}
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int cond = st.getCond();

		if(cond == 1 && Rnd.chance(60))
		{
			st.giveItems(PLAGUE_DUST_ID, 1, true);
			st.setCond(2);
		}
		else if(cond == 3 && Rnd.chance(60))
		{
			if(st.getQuestItemsCount(PLAGUE_DUST_ID) >= 4)
			{
				st.giveItems(PLAGUE_DUST_ID, 1);
				st.setCond(4);
			}
			else
			{
				st.giveItems(PLAGUE_DUST_ID, 1, true);
				st.playSound(SOUND_ITEMGET);
			}
		}
		return null;
	}
}