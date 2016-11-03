package quests;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public final class _103_SpiritOfCraftsman extends Quest
{
	public final int KAROYDS_LETTER_ID = 968;
	public final int CECKTINONS_VOUCHER1_ID = 969;
	public final int CECKTINONS_VOUCHER2_ID = 970;
	public final int BONE_FRAGMENT1_ID = 1107;
	public final int SOUL_CATCHER_ID = 971;
	public final int PRESERVE_OIL_ID = 972;
	public final int ZOMBIE_HEAD_ID = 973;
	public final int STEELBENDERS_HEAD_ID = 974;
	public final int BLOODSABER_ID = 49050;
	private static final int NEWBIE_SOULSHOT = 5789; // Заряд Души: Без Ранга для Новичков

	public _103_SpiritOfCraftsman()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(30307);

		addTalkId(30132);
		addTalkId(30144);

		addKillId(20015);
		addKillId(20455);
		addKillId(20517);
		addKillId(20518);

		addQuestItem(KAROYDS_LETTER_ID, CECKTINONS_VOUCHER1_ID, CECKTINONS_VOUCHER2_ID, BONE_FRAGMENT1_ID, SOUL_CATCHER_ID, PRESERVE_OIL_ID, ZOMBIE_HEAD_ID, STEELBENDERS_HEAD_ID);

		addLevelCheck(10, 17);
		addRaceCheck(false, false, true, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("blacksmith_karoyd_q0103_05.htm"))
		{
			st.giveItems(KAROYDS_LETTER_ID, 1);
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
			case 30307:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "blacksmith_karoyd_q0103_03.htm";
					else if(st.getPlayer().getRace() != Race.DARKELF)
						htmltext = "blacksmith_karoyd_q0103_00.htm";
					else
						htmltext = "blacksmith_karoyd_q0103_02.htm";
				}
				else if(cond >= 1 && cond < 8)
					htmltext = "blacksmith_karoyd_q0103_06.htm";
				else if(cond == 8)
				{
					htmltext = "blacksmith_karoyd_q0103_07.htm";
					st.takeItems(STEELBENDERS_HEAD_ID, 1);
					st.giveItems(NEWBIE_SOULSHOT, 3000);
					st.giveItems(BLOODSABER_ID, 1);
					st.finishQuest();
				}
				break;
			case 30132:
				if(cond == 1)
				{
					htmltext = "cecon_q0103_01.htm";
					st.setCond(2);
					st.takeItems(KAROYDS_LETTER_ID, 1);
					st.giveItems(CECKTINONS_VOUCHER1_ID, 1);
				}
				else if(cond == 5)
				{
					htmltext = "cecon_q0103_03.htm";
					st.setCond(6);
					st.takeItems(SOUL_CATCHER_ID, 1);
					st.giveItems(PRESERVE_OIL_ID, 1);
				}
				else if(cond == 6)
					htmltext = "cecon_q0103_04.htm";
				else if(cond == 7)
				{
					htmltext = "cecon_q0103_05.htm";
					st.setCond(8);
					st.takeItems(ZOMBIE_HEAD_ID, 1);
					st.giveItems(STEELBENDERS_HEAD_ID, 1);
				}
				else if(cond == 8)
					htmltext = "cecon_q0103_06.htm";
				else if(cond >= 2)
					htmltext = "cecon_q0103_02.htm";
				break;
			case 30144:
				if(cond == 2)
				{
					htmltext = "harne_q0103_01.htm";
					st.setCond(3);
					st.takeItems(CECKTINONS_VOUCHER1_ID, 1);
					st.giveItems(CECKTINONS_VOUCHER2_ID, 1);
				}
				else if(cond == 3)
					htmltext = "harne_q0103_02.htm";
				else if(cond == 4)
				{
					htmltext = "harne_q0103_03.htm";
					st.setCond(5);
					st.takeItems(CECKTINONS_VOUCHER2_ID, 1);
					st.takeItems(BONE_FRAGMENT1_ID, 10);
					st.giveItems(SOUL_CATCHER_ID, 1);
				}
				else if(cond == 5)
					htmltext = "harne_q0103_04.htm";
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		if((npcId == 20517 || npcId == 20518 || npcId == 20455) && st.getCond() == 3)
		{
			if(st.getQuestItemsCount(CECKTINONS_VOUCHER2_ID) == 1 && st.getQuestItemsCount(BONE_FRAGMENT1_ID) < 10)
			{
				if(Rnd.chance(33))
				{
					st.giveItems(BONE_FRAGMENT1_ID, 1, true);
					if(st.getQuestItemsCount(BONE_FRAGMENT1_ID) >= 10)
						st.setCond(4);
					else
						st.playSound(SOUND_ITEMGET);
				}
			}
		}
		else if(npcId == 20015 && st.getCond() == 6)
		{
			if(st.getQuestItemsCount(PRESERVE_OIL_ID) >= 1)
			{
				if(Rnd.chance(33))
				{
					st.giveItems(ZOMBIE_HEAD_ID, 1);
					st.takeItems(PRESERVE_OIL_ID, 1);
					st.setCond(7);
				}
			}
		}
		return null;
	}
}
