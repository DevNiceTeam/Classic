package quests;

import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

/**
 * @author Bonux
**/
public final class _153_DeliverGoods extends Quest
{
	// NPC's
	private static final int ARNOLD_GUARD = 30041; // Арнольд - Страж
	private static final int JACKSON_ARMOR_MERCHANT = 30002; // Джексон - Торговец Доспехами
	private static final int SILVIA_ACCESSORY_MERCHANT = 30003; // Сильвия - Ювелир
	private static final int RANT_WAREHOUSE_KEEPER = 30054; // Рант - Рабочий Склада

	// Item's
	private static final int DELIVERY_LIST = 1012; // Список Доставки
	private static final int HEAVY_WOOD_BOX = 1013; // Тяжелый Деревянный Ящик
	private static final int CLOTH_BUNDLE = 1014; // Моток Ткани
	private static final int CLAY_POT = 1015; // Глиняный Горшок
	private static final int JACKSONS_RECEIPT = 1016; // Расписка Джексона
	private static final int SILVIAS_RECEIPT = 1017; // Расписка Сильвии
	private static final int RANTS_RECEIPT = 1018; // Расписка Ранта
	private static final int NEWBIE_RING = 49041; // Кольцо Новичка

	public _153_DeliverGoods()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(ARNOLD_GUARD);
		addTalkId(JACKSON_ARMOR_MERCHANT, SILVIA_ACCESSORY_MERCHANT, RANT_WAREHOUSE_KEEPER);
		addQuestItem(DELIVERY_LIST, HEAVY_WOOD_BOX, CLOTH_BUNDLE, CLAY_POT, JACKSONS_RECEIPT, SILVIAS_RECEIPT, RANTS_RECEIPT);
		addLevelCheck(2, 5);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("arnold_q0302_04.htm"))
		{
			st.setCond(1);
			st.giveItems(DELIVERY_LIST, 1);
			st.giveItems(HEAVY_WOOD_BOX, 1);
			st.giveItems(CLOTH_BUNDLE, 1);
			st.giveItems(CLAY_POT, 1);
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
			case ARNOLD_GUARD:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "arnold_q0302_03.htm";
					else
						htmltext = "arnold_q0302_02.htm";
				}
				else if(cond == 1)
				{
					htmltext = "arnold_q0302_05.htm";
				}
				else if(cond == 2)
				{
					htmltext = "arnold_q0302_06.htm";
					st.takeItems(DELIVERY_LIST, -1);
					st.takeItems(JACKSONS_RECEIPT, -1);
					st.takeItems(SILVIAS_RECEIPT, -1);
					st.takeItems(RANTS_RECEIPT, -1);
					st.giveItems(NEWBIE_RING, 1);
					st.finishQuest();
				}
				break;
			case JACKSON_ARMOR_MERCHANT:
				if(cond == 1)
				{
					if(st.haveQuestItem(HEAVY_WOOD_BOX))
					{
						htmltext = "jackson_q0302_01.htm";
						st.takeItems(HEAVY_WOOD_BOX, -1);
						st.giveItems(JACKSONS_RECEIPT, 1);
						chekDelivery(st);
					}
					else if(st.haveQuestItem(JACKSONS_RECEIPT))
						htmltext = "jackson_q0302_02.htm";
				}
				break;
			case SILVIA_ACCESSORY_MERCHANT:
				if(cond == 1)
				{
					if(st.haveQuestItem(CLOTH_BUNDLE))
					{
						htmltext = "silvia_q0302_01.htm";
						st.takeItems(CLOTH_BUNDLE, -1);
						st.giveItems(SILVIAS_RECEIPT, 1);
						chekDelivery(st);
					}
					else if(st.haveQuestItem(SILVIAS_RECEIPT))
						htmltext = "silvia_q0302_02.htm";
				}
				break;
			case RANT_WAREHOUSE_KEEPER:
				if(cond == 1)
				{
					if(st.haveQuestItem(CLAY_POT))
					{
						htmltext = "rant_q0302_01.htm";
						st.takeItems(CLAY_POT, -1);
						st.giveItems(RANTS_RECEIPT, 1);
						chekDelivery(st);
					}
					else if(st.haveQuestItem(RANTS_RECEIPT))
						htmltext = "rant_q0302_02.htm";
				}
				break;
		}
		return htmltext;
	}

	public void chekDelivery(QuestState st)
	{
		if(st.haveQuestItem(RANTS_RECEIPT) && st.haveQuestItem(SILVIAS_RECEIPT) && st.haveQuestItem(JACKSONS_RECEIPT))
			st.setCond(2);
	}
}