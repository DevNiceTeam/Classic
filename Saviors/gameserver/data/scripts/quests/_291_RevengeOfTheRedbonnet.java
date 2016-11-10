package quests;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _291_RevengeOfTheRedbonnet extends Quest
{
	//NPC
	final int MaryseRedbonnet = 30553;
	//Quest Items
	int BlackWolfPelt = 1482;
	//Item
	int GrandmasPearl = 1502;
	int GrandmasMirror = 1503;
	int GrandmasNecklace = 1504;
	int GrandmasHairpin = 1505;
	//Mobs
	int BlackWolf = 20317;

	public _291_RevengeOfTheRedbonnet()
	{
		super(PARTY_NONE, REPEATABLE);

		addStartNpc(MaryseRedbonnet);
		addTalkId(MaryseRedbonnet);

		addKillId(BlackWolf);

		addQuestItem(BlackWolfPelt);

		addLevelCheck(4, 8);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("marife_redbonnet_q0291_03.htm"))
		{
			st.setCond(1);
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int npcId = npc.getNpcId();
		int cond = st.getCond();

		switch(npcId)
		{
			case MaryseRedbonnet:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "marife_redbonnet_q0291_02.htm";
					else
						htmltext = "marife_redbonnet_q0291_01.htm";
				}
				else if(cond == 1)
					htmltext = "marife_redbonnet_q0291_04.htm";
				else if(cond == 2)
				{
					st.takeItems(BlackWolfPelt, -1);
					if(Rnd.chance(3))
						st.giveItems(GrandmasPearl, 1);
					else if(Rnd.chance(17))
						st.giveItems(GrandmasMirror, 1);
					else if(Rnd.chance(29))
						st.giveItems(GrandmasNecklace, 1);
					else
						st.giveItems(GrandmasHairpin, 1);
					htmltext = "marife_redbonnet_q0291_05.htm";
					st.finishQuest();
				}
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		if(st.getCond() == 1)
		{
			st.giveItems(BlackWolfPelt, 1, true);
			if(st.getQuestItemsCount(BlackWolfPelt) < 40)
				st.playSound(SOUND_ITEMGET);
			else
				st.setCond(2);
		}
		return null;
	}
}
