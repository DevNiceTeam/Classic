package quests;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

/**
 * @reworked by Bonux
**/
public final class _101_SwordOfSolidarity extends Quest
{
	private static final int ROIENS_LETTER = 796; // 
	private static final int HOWTOGO_RUINS = 937; // 
	private static final int BROKEN_SWORD_HANDLE = 739; // 
	private static final int BROKEN_BLADE_BOTTOM = 740; // 
	private static final int BROKEN_BLADE_TOP = 741; // 
	private static final int ALLTRANS_NOTE = 742; // 
	private static final int SWORD_OF_SOLIDARITY = 49043; // 
	private static final int NEWBIE_SOULSHOT = 34603; // Заряд Души: Без Ранга для Новичков
	private static final int NEWBIE_SPIRITSHOT = 34610; // Заряд Души: Без Ранга для Новичков

	public _101_SwordOfSolidarity()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30008);
		addTalkId(30283);
		addKillId(20361, 20362);
		addQuestItem(ALLTRANS_NOTE, HOWTOGO_RUINS, BROKEN_BLADE_TOP, BROKEN_BLADE_BOTTOM, ROIENS_LETTER, BROKEN_SWORD_HANDLE);
		addLevelCheck(10, 16);
		addRaceCheck(true, false, false, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("roien_q0101_04.htm"))
		{
			st.setCond(1);
			st.giveItems(ROIENS_LETTER, 1);
		}
		else if(event.equalsIgnoreCase("blacksmith_alltran_q0101_02.htm"))
		{
			st.setCond(2);
			st.takeItems(ROIENS_LETTER, -1);
			st.giveItems(HOWTOGO_RUINS, 1);
		}
		else if(event.equalsIgnoreCase("blacksmith_alltran_q0101_07.htm"))
		{
			st.takeItems(BROKEN_SWORD_HANDLE, -1);
			st.giveItems(NEWBIE_SOULSHOT, 3000);
			st.giveItems(NEWBIE_SPIRITSHOT, 1000);
			st.giveItems(SWORD_OF_SOLIDARITY, 1);
			st.finishQuest();
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int npcId = npc.getNpcId();
		int cond = st.getCond();

		if(npcId == 30008)
		{
			if(cond == 0)
			{
				if(checkStartCondition(st.getPlayer()))
					htmltext = "roien_q0101_02.htm";
				else if(st.getPlayer().getRace() != Race.HUMAN)
					htmltext = "roien_q0101_00.htm";
				else
					htmltext = "roien_q0101_08.htm";
			}
			else if(cond == 1)
				htmltext = "roien_q0101_05.htm";
			else if(cond == 2 || cond == 3)
			{
				if(st.getQuestItemsCount(BROKEN_BLADE_TOP) > 0 && st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) > 0)
					htmltext = "roien_q0101_12.htm";
				if(st.getQuestItemsCount(BROKEN_BLADE_TOP) + st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) <= 1)
					htmltext = "roien_q0101_11.htm";
				if(st.getQuestItemsCount(BROKEN_SWORD_HANDLE) > 0)
					htmltext = "roien_q0101_07.htm";
				if(st.getQuestItemsCount(HOWTOGO_RUINS) >= 1)
					htmltext = "roien_q0101_10.htm";
			}
			else if(cond == 4)
			{
				htmltext = "roien_q0101_06.htm";
				st.setCond(5);
				st.takeItems(ALLTRANS_NOTE, -1);
				st.giveItems(BROKEN_SWORD_HANDLE, 1);
			}
		}
		else if(npcId == 30283)
		{
			if(cond == 1)
				htmltext = "blacksmith_alltran_q0101_01.htm";
			else if(cond == 2 || cond == 3)
			{
				if(st.getQuestItemsCount(BROKEN_BLADE_TOP) + st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) == 1)
					htmltext = "blacksmith_alltran_q0101_08.htm";
				else if(st.getQuestItemsCount(BROKEN_BLADE_TOP) + st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) == 0)
					htmltext = "blacksmith_alltran_q0101_03.htm";
				else if(st.getQuestItemsCount(BROKEN_BLADE_TOP) > 0 && st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) > 0)
				{
					htmltext = "blacksmith_alltran_q0101_04.htm";
					st.setCond(4);
					st.takeItems(HOWTOGO_RUINS, -1);
					st.takeItems(BROKEN_BLADE_TOP, -1);
					st.takeItems(BROKEN_BLADE_BOTTOM, -1);
					st.giveItems(ALLTRANS_NOTE, 1);
				}
			}
			else if(cond == 4)
				htmltext = "blacksmith_alltran_q0101_05.htm";
			else if(cond == 5)
				htmltext = "blacksmith_alltran_q0101_06.htm";
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		if((npcId == 20361 || npcId == 20362) && st.getQuestItemsCount(HOWTOGO_RUINS) > 0)
		{
			if(st.getQuestItemsCount(BROKEN_BLADE_TOP) == 0 && Rnd.chance(60))
			{
				st.giveItems(BROKEN_BLADE_TOP, 1);
				st.playSound(SOUND_ITEMGET);
			}
			else if(st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) == 0 && Rnd.chance(60))
			{
				st.giveItems(BROKEN_BLADE_BOTTOM, 1);
				st.playSound(SOUND_ITEMGET);
			}
			if(st.getQuestItemsCount(BROKEN_BLADE_TOP) > 0 && st.getQuestItemsCount(BROKEN_BLADE_BOTTOM) > 0)
				st.setCond(3);
		}
		return null;
	}
}