package quests;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _162_CurseOfUndergroundFortress extends Quest
{
	int BONE_FRAGMENT3 = 1158;
	int ELF_SKULL = 1159;
	int BUCKLER = 20;

	public _162_CurseOfUndergroundFortress()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30147);

		addTalkId(30147);

		addKillId(20033);
		addKillId(20345);
		addKillId(20371);
		addKillId(20463);
		addKillId(20464);
		addKillId(20504);

		addQuestItem(new int[]
		{ ELF_SKULL, BONE_FRAGMENT3 });

		addLevelCheck(12, 21);
		addRaceCheck(true, true, false, true, true);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("30147-04.htm"))
		{
			st.setCond(1);
			htmltext = "30147-04.htm";
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
			case 30147:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "30147-02.htm";
					else if(st.getPlayer().getRace() == Race.DARKELF)
						htmltext = "30147-00.htm";
					else
						htmltext = "30147-01.htm";
				}
				else if(cond == 1)
					htmltext = "30147-05.htm";
				else if(cond == 2)
				{
					htmltext = "30147-06.htm";
					st.giveItems(BUCKLER, 1);
					st.takeItems(ELF_SKULL, -1);
					st.takeItems(BONE_FRAGMENT3, -1);
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
		if((npcId == 20463 || npcId == 20464 || npcId == 20504) && cond == 1 && Rnd.chance(25) && st.getQuestItemsCount(BONE_FRAGMENT3) < 10)
		{
			st.giveItems(BONE_FRAGMENT3, 1, true);
			if(st.getQuestItemsCount(BONE_FRAGMENT3) >= 10)
				st.playSound(SOUND_MIDDLE);
			else
				st.playSound(SOUND_ITEMGET);
		}
		else if((npcId == 20033 || npcId == 20345 || npcId == 20371) && cond == 1 && Rnd.chance(10) && st.getQuestItemsCount(ELF_SKULL) < 3)
		{
			st.giveItems(ELF_SKULL, 1, true);
			if(st.getQuestItemsCount(ELF_SKULL) >= 3)
				st.playSound(SOUND_MIDDLE);
			else
				st.playSound(SOUND_ITEMGET);
		}
		if(st.getQuestItemsCount(BONE_FRAGMENT3) >= 10 && st.getQuestItemsCount(ELF_SKULL) >= 3)
			st.setCond(2);
		return null;
	}
}