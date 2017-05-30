package quests;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.model.base.Race;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

/**
 * Квест Proof Of Valor
 *
 * @author Sergey Ibryaev aka Artful
 */

public final class _271_ProofOfValor extends Quest
{
	//NPC
	private static final int RUKAIN = 30577;
	//Quest Item
	private static final int KASHA_WOLF_FANG_ID = 1473;
	private static final int NECKLACE_OF_NEWBIE = 49039;
	private static final int HEALING_POTION = 1061;

	public _271_ProofOfValor()
	{
		super(PARTY_NONE, REPEATABLE);

		addStartNpc(RUKAIN);
		addTalkId(RUKAIN);

		//Mob Drop
		addKillId(20475);

		addQuestItem(KASHA_WOLF_FANG_ID);

		addLevelCheck(4, 8);
		addRaceCheck(false, false, false, true, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("praetorian_rukain_q0271_03.htm"))
		{
			htmltext = "praetorian_rukain_q0271_07.htm";
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
			case RUKAIN:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
					{
						if(st.getCond() == -1)
							htmltext = "praetorian_rukain_q0271_06.htm";
						else
							htmltext = "praetorian_rukain_q0271_02.htm";
					}
					else if(st.getPlayer().getRace() != Race.ORC)
						htmltext = "praetorian_rukain_q0271_00.htm";
					else
						htmltext = "praetorian_rukain_q0271_01.htm";
				}
				else if(cond == 1)
					htmltext = "praetorian_rukain_q0271_04.htm";
				else if(cond == 2)
				{
					st.takeItems(KASHA_WOLF_FANG_ID, -1);
					if(Rnd.chance(14))
					{
						st.giveItems(NECKLACE_OF_NEWBIE, 1, false);
						st.giveItems(HEALING_POTION, 1, true);
					}
					else
						st.giveItems(NECKLACE_OF_NEWBIE, 1);
					htmltext = "praetorian_rukain_q0271_05.htm";
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
			st.rollAndGive(KASHA_WOLF_FANG_ID, 1, 1, 50, 90);
			if(st.getQuestItemsCount(KASHA_WOLF_FANG_ID) >= 50)
				st.setCond(2);
		}
		return null;
	}
}
