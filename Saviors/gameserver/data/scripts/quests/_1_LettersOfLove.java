package quests;

import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

/**
 * @author Bonux
**/
public final class _1_LettersOfLove extends Quest
{
	// NPC's
	private static final int DARIN = 30048; // Дарин
	private static final int ROXXY_GATEKEEPER = 30006; // Рокси - Хранитель Портала
	private static final int BAULRO_MAGISTER = 30033; // Баулро - Магистр

	// Item's
	private static final int DARINS_LETTER = 687; // Письмо Дарина
	private static final int ROXXYS_KERCHIEF = 688; // Платок Рокси
	private static final int DARINS_RECEIPT = 1079; // Расписка Дарина
	private static final int BAULROS_POTION = 1080; // Зелье Баулро
	private static final int NEWBIE_NECKLACE = 49039; // Ожерелье Новичка

	public _1_LettersOfLove()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(DARIN);
		addTalkId(ROXXY_GATEKEEPER, BAULRO_MAGISTER);
		addQuestItem(DARINS_LETTER, ROXXYS_KERCHIEF, DARINS_RECEIPT, BAULROS_POTION);
		addLevelCheck(2, 5);
	}

	@Override
	public String onEvent(String event, QuestState qs, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("daring_q0001_06.htm"))
		{
			qs.setCond(1);
			qs.giveItems(DARINS_LETTER, 1);
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
			case DARIN:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "daring_q0001_02.htm";
					else
						htmltext = "daring_q0001_01.htm";
				}
				else if(cond == 1)
					htmltext = "daring_q0001_07.htm";
				else if(cond == 2)
				{
					htmltext = "daring_q0001_08.htm";
					st.takeItems(ROXXYS_KERCHIEF, -1);
					st.giveItems(DARINS_RECEIPT, 1);
					st.setCond(3);
				}
				else if(cond == 3)
					htmltext = "daring_q0001_09.htm";
				else if(cond == 4)
				{
					htmltext = "daring_q0001_10.htm";
					st.takeItems(BAULROS_POTION, -1);
					st.giveItems(NEWBIE_NECKLACE, 1);
					st.finishQuest();
				}
				break;
			case ROXXY_GATEKEEPER:
				if(cond == 1)
				{
					htmltext = "rapunzel_q0001_01.htm";
					st.takeItems(DARINS_LETTER, -1);
					st.giveItems(ROXXYS_KERCHIEF, 1);
					st.setCond(2);
				}
				else if(cond == 2)
					htmltext = "rapunzel_q0001_02.htm";
				else if(cond > 2)
					htmltext = "rapunzel_q0001_03.htm";
				break;
			case BAULRO_MAGISTER:
				if(cond == 3)
				{
					htmltext = "baul_q0001_01.htm";
					st.takeItems(DARINS_RECEIPT, -1);
					st.giveItems(BAULROS_POTION, 1);
					st.setCond(4);
				}
				else if(cond == 4)
					htmltext = "baul_q0001_02.htm";
				break;
		}
		return htmltext;
	}
}