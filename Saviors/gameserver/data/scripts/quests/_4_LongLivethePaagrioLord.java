package quests;

import studio.lineage2.gameserver.model.base.Race;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

/**
 * @reworked by Bonux
**/
public final class _4_LongLivethePaagrioLord extends Quest
{
	// NPC's
	private static final int NAKUSIN_CENTURION = 30578; // Накушин - Центурион
	private static final int KUNAI_ARMOR_MERCHANT = 30559; // Кунай - Торговец Доспехами
	private static final int USKA_ACCESSORY_MERCHANT = 30560; // Аска - Ювелир
	private static final int GROOKIN_WAREHOUSE_KEEPER = 30562; // Грукин - Рабочий Склада
	private static final int VARKEES_ATUBA_CHIEF = 30566; // Варкис - Вождь Атуба
	private static final int TATARU_ZU_HESTUI = 30585; // Татару Зу Хестуи
	private static final int GANTAKI_ZU_URUTU = 30587; // Гентаки Зу Уруту

	// Item's
	private static final int HONEY_KHANDAR = 1541;
	private static final int BEAR_FUR_CLOAK = 1542;
	private static final int BLOODY_AXE = 1543;
	private static final int ANCESTOR_SKULL = 1544;
	private static final int SPIDER_DUST = 1545;
	private static final int DEEP_SEA_ORB = 1546;
	private static final int NEWBIE_BLUNT = 49051;

	public _4_LongLivethePaagrioLord()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(NAKUSIN_CENTURION);
		addTalkId(KUNAI_ARMOR_MERCHANT, USKA_ACCESSORY_MERCHANT, GROOKIN_WAREHOUSE_KEEPER, VARKEES_ATUBA_CHIEF, TATARU_ZU_HESTUI, GANTAKI_ZU_URUTU);
		addQuestItem(SPIDER_DUST, ANCESTOR_SKULL, BLOODY_AXE, HONEY_KHANDAR, BEAR_FUR_CLOAK, DEEP_SEA_ORB);
		addLevelCheck(2, 5);
		addRaceCheck(false, false, false, true, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("centurion_nakusin_q0004_03.htm"))
			st.setCond(1);
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
			case NAKUSIN_CENTURION:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "centurion_nakusin_q0004_02.htm";
					else if(st.getPlayer().getRace() != Race.ORC)
						htmltext = "centurion_nakusin_q0004_00.htm";
					else
						htmltext = "centurion_nakusin_q0004_01.htm";
				}
				else if(cond == 1)
					htmltext = "centurion_nakusin_q0004_04.htm";
				else if(cond == 2)
				{
					htmltext = "centurion_nakusin_q0004_06.htm";
					st.takeItems(SPIDER_DUST, -1);
					st.takeItems(BEAR_FUR_CLOAK, -1);
					st.takeItems(HONEY_KHANDAR, -1);
					st.takeItems(BLOODY_AXE, -1);
					st.takeItems(ANCESTOR_SKULL, -1);
					st.takeItems(DEEP_SEA_ORB, -1);
					st.giveItems(NEWBIE_BLUNT, 1);
					st.finishQuest();
				}
				break;
			case KUNAI_ARMOR_MERCHANT:
				if(cond == 1)
				{
					if(st.getQuestItemsCount(SPIDER_DUST) > 0)
						htmltext = "trader_kunai_q0004_02.htm";
					else
					{
						htmltext = "trader_kunai_q0004_01.htm";
						st.giveItems(SPIDER_DUST, 1);

						if(st.haveQuestItem(SPIDER_DUST) && st.haveQuestItem(BEAR_FUR_CLOAK) && st.haveQuestItem(HONEY_KHANDAR) && st.haveQuestItem(BLOODY_AXE) && st.haveQuestItem(ANCESTOR_SKULL) && st.haveQuestItem(DEEP_SEA_ORB))
							st.setCond(2);
						else
							st.playSound(SOUND_ITEMGET);
					}
				}
				break;
			case USKA_ACCESSORY_MERCHANT:
				if(cond == 1)
				{
					if(st.getQuestItemsCount(ANCESTOR_SKULL) > 0)
						htmltext = "trader_uska_q0004_02.htm";
					else
					{
						htmltext = "trader_uska_q0004_01.htm";
						st.giveItems(ANCESTOR_SKULL, 1);

						if(st.haveQuestItem(SPIDER_DUST) && st.haveQuestItem(BEAR_FUR_CLOAK) && st.haveQuestItem(HONEY_KHANDAR) && st.haveQuestItem(BLOODY_AXE) && st.haveQuestItem(ANCESTOR_SKULL) && st.haveQuestItem(DEEP_SEA_ORB))
							st.setCond(2);
						else
							st.playSound(SOUND_ITEMGET);
					}
				}
				break;
			case GROOKIN_WAREHOUSE_KEEPER:
				if(cond == 1)
				{
					if(st.getQuestItemsCount(BLOODY_AXE) > 0)
						htmltext = "warehouse_grookin_q0004_02.htm";
					else
					{
						htmltext = "warehouse_grookin_q0004_01.htm";
						st.giveItems(BLOODY_AXE, 1);

						if(st.haveQuestItem(SPIDER_DUST) && st.haveQuestItem(BEAR_FUR_CLOAK) && st.haveQuestItem(HONEY_KHANDAR) && st.haveQuestItem(BLOODY_AXE) && st.haveQuestItem(ANCESTOR_SKULL) && st.haveQuestItem(DEEP_SEA_ORB))
							st.setCond(2);
						else
							st.playSound(SOUND_ITEMGET);
					}
				}
				break;
			case VARKEES_ATUBA_CHIEF:
				if(cond == 1)
				{
					if(st.getQuestItemsCount(HONEY_KHANDAR) > 0)
						htmltext = "atuba_chief_varkees_q0004_02.htm";
					else
					{
						htmltext = "atuba_chief_varkees_q0004_01.htm";
						st.giveItems(HONEY_KHANDAR, 1);

						if(st.haveQuestItem(SPIDER_DUST) && st.haveQuestItem(BEAR_FUR_CLOAK) && st.haveQuestItem(HONEY_KHANDAR) && st.haveQuestItem(BLOODY_AXE) && st.haveQuestItem(ANCESTOR_SKULL) && st.haveQuestItem(DEEP_SEA_ORB))
							st.setCond(2);
						else
							st.playSound(SOUND_ITEMGET);
					}
				}
				break;
			case TATARU_ZU_HESTUI:
				if(cond == 1)
				{
					if(st.getQuestItemsCount(BEAR_FUR_CLOAK) > 0)
						htmltext = "tataru_zu_hestui_q0004_02.htm";
					else
					{
						htmltext = "tataru_zu_hestui_q0004_01.htm";
						st.giveItems(BEAR_FUR_CLOAK, 1);

						if(st.haveQuestItem(SPIDER_DUST) && st.haveQuestItem(BEAR_FUR_CLOAK) && st.haveQuestItem(HONEY_KHANDAR) && st.haveQuestItem(BLOODY_AXE) && st.haveQuestItem(ANCESTOR_SKULL) && st.haveQuestItem(DEEP_SEA_ORB))
							st.setCond(2);
						else
							st.playSound(SOUND_ITEMGET);
					}
				}
				break;
			case GANTAKI_ZU_URUTU:
				if(cond == 1)
				{
					if(st.getQuestItemsCount(DEEP_SEA_ORB) > 0)
						htmltext = "gantaki_zu_urutu_q0004_02.htm";
					else
					{
						htmltext = "gantaki_zu_urutu_q0004_01.htm";
						st.giveItems(DEEP_SEA_ORB, 1);

						if(st.haveQuestItem(SPIDER_DUST) && st.haveQuestItem(BEAR_FUR_CLOAK) && st.haveQuestItem(HONEY_KHANDAR) && st.haveQuestItem(BLOODY_AXE) && st.haveQuestItem(ANCESTOR_SKULL) && st.haveQuestItem(DEEP_SEA_ORB))
							st.setCond(2);
						else
							st.playSound(SOUND_ITEMGET);
					}
				}
				break;
		}
		return htmltext;
	}
}