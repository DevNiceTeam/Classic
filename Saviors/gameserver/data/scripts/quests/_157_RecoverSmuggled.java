package quests;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _157_RecoverSmuggled extends Quest
{
	int ADAMANTITE_ORE_ID = 1024;
	int BUCKLER = 49042;

	public _157_RecoverSmuggled()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30005);

		addTalkId(30005);

		addKillId(20121);

		addQuestItem(ADAMANTITE_ORE_ID);

		addLevelCheck(5, 9);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("1"))
		{
			st.set("id", "0");
			st.setCond(1);
			htmltext = "30005-05.htm";
		}
		else if(event.equals("157_1"))
		{
			htmltext = "30005-04.htm";
			return htmltext;
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
			case 30005:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30005-03.htm";
					else
						htmltext = "30005-02.htm";
				}
				else if(cond == 1)
					htmltext = "30005-06.htm";
				else if(cond == 2)
				{
					st.takeItems(ADAMANTITE_ORE_ID, st.getQuestItemsCount(ADAMANTITE_ORE_ID));
					st.giveItems(BUCKLER, 1);
					htmltext = "30005-07.htm";
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
		if(npcId == 20121)
		{
			st.set("id", "0");
			if(st.getCond() != 0 && st.getQuestItemsCount(ADAMANTITE_ORE_ID) < 20 && Rnd.chance(14))
			{
				st.giveItems(ADAMANTITE_ORE_ID, 1, true);
				if(st.getQuestItemsCount(ADAMANTITE_ORE_ID) >= 20)
				{
					st.setCond(2);
					st.playSound(SOUND_MIDDLE);
				}
				else
					st.playSound(SOUND_ITEMGET);
			}
		}
		return null;
	}
}