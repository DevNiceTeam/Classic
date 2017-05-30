package quests;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _151_CureforFeverDisease extends Quest
{
	int POISON_SAC = 703;
	int FEVER_MEDICINE = 704;
	int HAST_POTION = 735;

	public _151_CureforFeverDisease()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30050);

		addTalkId(30032);

		addKillId(20103, 20106, 20108);

		addQuestItem(FEVER_MEDICINE, POISON_SAC);

		addLevelCheck(15, 21);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("30050-03.htm"))
		{
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
			case 30050:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30050-02.htm";
					else
						htmltext = "30050-01.htm";
				}
				else if(cond == 1 && st.getQuestItemsCount(POISON_SAC) == 0 && st.getQuestItemsCount(FEVER_MEDICINE) == 0)
					htmltext = "30050-04.htm";
				else if(cond == 1 && st.getQuestItemsCount(POISON_SAC) >= 1)
					htmltext = "30050-05.htm";
				else if(cond == 3)
				{
					st.takeItems(FEVER_MEDICINE, -1);
					st.giveItems(HAST_POTION, 1, true);
					htmltext = "30050-06.htm";
					st.finishQuest();
				}
				break;
			case 30032:
				if(cond == 2)
				{
					st.giveItems(FEVER_MEDICINE, 1);
					st.takeItems(POISON_SAC, -1);
					st.setCond(3);
					htmltext = "30032-01.htm";
				}
				else if(cond == 3)
					htmltext = "30032-02.htm";
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		if((npcId == 20103 || npcId == 20106 || npcId == 20108) && st.getQuestItemsCount(POISON_SAC) == 0 && st.getCond() == 1 && Rnd.chance(50))
		{
			st.setCond(2);
			st.giveItems(POISON_SAC, 1, true);
		}
		return null;
	}
}