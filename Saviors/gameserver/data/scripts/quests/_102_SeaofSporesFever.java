package quests;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _102_SeaofSporesFever extends Quest
{
	int ALBERRYUS_LETTER = 964;
	int EVERGREEN_AMULET = 965;
	int DRYAD_TEARS = 966;
	int LBERRYUS_LIST = 746;
	int COBS_MEDICINE1 = 1130;
	int COBS_MEDICINE2 = 1131;
	int COBS_MEDICINE3 = 1132;
	int COBS_MEDICINE4 = 1133;
	int COBS_MEDICINE5 = 1134;
	int SWORD_OF_SENTINEL = 49047;
	int STAFF_OF_SENTINEL = 49048;
	int ALBERRYUS_LIST = 746;

	public _102_SeaofSporesFever()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30284);

		addTalkId(30156);
		addTalkId(30217);
		addTalkId(30219);
		addTalkId(30221);
		addTalkId(30284);
		addTalkId(30285);

		addKillId(20013);
		addKillId(20019);

		addQuestItem(ALBERRYUS_LETTER, EVERGREEN_AMULET, DRYAD_TEARS, COBS_MEDICINE1, COBS_MEDICINE2, COBS_MEDICINE3, COBS_MEDICINE4, COBS_MEDICINE5, ALBERRYUS_LIST);

		addLevelCheck(12, 18);
		addRaceCheck(false, true, false, false, false);
	}

	private void check(QuestState st)
	{
		if(st.getQuestItemsCount(COBS_MEDICINE2) == 0 && st.getQuestItemsCount(COBS_MEDICINE3) == 0 && st.getQuestItemsCount(COBS_MEDICINE4) == 0 && st.getQuestItemsCount(COBS_MEDICINE5) == 0)
			st.setCond(6);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("alberryus_q0102_02.htm"))
		{
			st.setCond(1);
			st.giveItems(ALBERRYUS_LETTER, 1);
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int npcId = npc.getNpcId();
		int cond = st.getCond();
		if(npcId == 30284)
		{
			if(cond == 0)
			{
				if(checkStartCondition(st.getPlayer()))
					htmltext = "alberryus_q0102_07.htm";
				else if(st.getPlayer().getRace() != Race.ELF)
					htmltext = "alberryus_q0102_00.htm";
				else
					htmltext = "alberryus_q0102_08.htm";
			}
			else if(cond == 1)
				htmltext = "alberryus_q0102_03.htm";
			else if(cond == 2)
				htmltext = "alberryus_q0102_09.htm";
			else if(cond == 4)
			{
				st.setCond(5);
				st.takeItems(COBS_MEDICINE1, 1);
				st.giveItems(ALBERRYUS_LIST, 1);
				htmltext = "alberryus_q0102_04.htm";
			}
			else if(cond == 5)
				htmltext = "alberryus_q0102_05.htm";
			else if(cond == 6)
			{
				st.takeItems(ALBERRYUS_LIST, 1);

				if(st.getPlayer().getClassId().isMage())
					st.giveItems(STAFF_OF_SENTINEL, 1);
				else
					st.giveItems(SWORD_OF_SENTINEL, 1);

				htmltext = "alberryus_q0102_06.htm";
				st.finishQuest();
			}
		}
		else if(npcId == 30156)
		{
			if(cond == 1 && st.getQuestItemsCount(ALBERRYUS_LETTER) >= 1)
			{
				st.takeItems(ALBERRYUS_LETTER, 1);
				st.giveItems(EVERGREEN_AMULET, 1);
				st.setCond(2);
				htmltext = "cob_q0102_03.htm";
			}
			else if(cond == 2)
				htmltext = "cob_q0102_04.htm";
			else if(cond == 3)
			{
				st.takeItems(EVERGREEN_AMULET, 1);
				st.takeItems(DRYAD_TEARS, -1);
				st.giveItems(COBS_MEDICINE1, 1);
				st.giveItems(COBS_MEDICINE2, 1);
				st.giveItems(COBS_MEDICINE3, 1);
				st.giveItems(COBS_MEDICINE4, 1);
				st.giveItems(COBS_MEDICINE5, 1);
				st.setCond(4);
				htmltext = "cob_q0102_05.htm";
			}
			else if(cond == 4)
				htmltext = "cob_q0102_06.htm";
			else if(cond > 3)
				htmltext = "cob_q0102_07.htm";
		}
		else if(npcId == 30217 && cond == 5 && st.getQuestItemsCount(COBS_MEDICINE2) >= 1)
		{
			st.takeItems(COBS_MEDICINE2, 1);
			htmltext = "sentinel_berryos_q0102_01.htm";
			check(st);
		}
		else if(npcId == 30219 && cond == 5 && st.getQuestItemsCount(COBS_MEDICINE3) >= 1)
		{
			st.takeItems(COBS_MEDICINE3, 1);
			htmltext = "sentinel_veltress_q0102_01.htm";
			check(st);
		}
		else if(npcId == 30221 && cond == 5 && st.getQuestItemsCount(COBS_MEDICINE4) >= 1)
		{
			st.takeItems(COBS_MEDICINE4, 1);
			htmltext = "sentinel_rayjien_q0102_01.htm";
			check(st);
		}
		else if(npcId == 30285 && cond == 5 && st.getQuestItemsCount(COBS_MEDICINE5) >= 1)
		{
			st.takeItems(COBS_MEDICINE5, 1);
			htmltext = "sentinel_gartrandell_q0102_01.htm";
			check(st);
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		if((npcId == 20013 || npcId == 20019) && Rnd.chance(33))
		{
			if(st.getQuestItemsCount(EVERGREEN_AMULET) > 0 && st.getQuestItemsCount(DRYAD_TEARS) < 10)
			{
				st.giveItems(DRYAD_TEARS, 1, true);
				if(st.getQuestItemsCount(DRYAD_TEARS) >= 10)
					st.setCond(3);
				else
					st.playSound(SOUND_ITEMGET);
			}
		}
		return null;
	}
}
