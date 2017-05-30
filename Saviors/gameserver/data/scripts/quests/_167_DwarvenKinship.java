package quests;

import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _167_DwarvenKinship extends Quest
{
	//NPC
	private static final int Carlon = 30350;
	private static final int Haprock = 30255;
	private static final int Norman = 30210;
	//Quest Items
	private static final int CarlonsLetter = 1076;
	private static final int NormansLetter = 1106;

	public _167_DwarvenKinship()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(Carlon);

		addTalkId(Haprock);
		addTalkId(Norman);

		addQuestItem(CarlonsLetter, NormansLetter);

		addLevelCheck(15);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("30350-04.htm"))
		{
			st.giveItems(CarlonsLetter, 1);
			st.setCond(1);
		}
		else if(event.equalsIgnoreCase("30255-03.htm"))
		{
			st.takeItems(CarlonsLetter, -1);
			st.giveItems(NormansLetter, 1);
			st.setCond(2);
		}
		else if(event.equalsIgnoreCase("30255-04.htm"))
		{
			st.takeItems(CarlonsLetter, -1);
			st.giveItems(ADENA_ID, 1000);
			st.playSound(SOUND_GIVEUP);
			st.finishQuest();
		}
		else if(event.equalsIgnoreCase("30210-02.htm"))
		{
			st.takeItems(NormansLetter, -1);
			st.giveItems(ADENA_ID, 3000);
			st.finishQuest();
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
			case Carlon:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30350-03.htm";
					else
						htmltext = "30350-02.htm";
				}
				else if(cond > 0)
					htmltext = "30350-05.htm";
				break;

			case Haprock:
				if(cond == 1)
					htmltext = "30255-01.htm";
				else if(cond > 1)
					htmltext = "30255-05.htm";
				break;

			case Norman:
				if(cond == 2)
					htmltext = "30210-01.htm";
				break;
		}
		return htmltext;
	}
}