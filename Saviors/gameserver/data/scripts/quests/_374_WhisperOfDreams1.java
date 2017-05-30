package quests;

import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

/*Done by Start-Dev*/

public class _374_WhisperOfDreams1 extends Quest
{
	//Шансы
	private static final double Шанс_Дропа_Запечатанного_камня = 0.4; 
	private static final int Шанс_Дропа = 20;
	//Награда	
	private static final int Фрагмент_Свитка_Модифицировать_Доспех = 49475;
	private static final int Фрагмент_Улучшенного_Свитка_Модифицировать_Доспех = 49478; 
	private static final int Свиток_Модифицировать_Доспех_B = 948;
	private static final int Улучшенный_Свиток_Модифицировать_Доспех_B = 33814;
	private static final int Адена = 57;
	private static final int Количество_Адены = 9000;
	private static final int[][] mReward =
		{
		{8, Улучшенный_Свиток_Модифицировать_Доспех_B, 1 },
		{20, Свиток_Модифицировать_Доспех_B, 1 },
		{41, Фрагмент_Улучшенного_Свитка_Модифицировать_Доспех, 1 },
		{59, Фрагмент_Свитка_Модифицировать_Доспех, 1 },};
	// Квест предметы
	private static final int Зуб_Пещерного_Зверя = 5884; 
	private static final int Свет_Волны_Смерти = 5885; 
	private static final int Запечатанный_Камень = 5886; 
	private static final int Распечатанный_Камень = 5887; 
	// НПС
	private static final int Вануту = 30938;
	private static final int Гальман = 31044;
	// Мобы
	private static final int Пещерный_Зверь = 20620;
	private static final int Волна_Смерти = 20621;

	public _374_WhisperOfDreams1()
	{
		super(PARTY_NONE, REPEATABLE);
		addStartNpc(Вануту);
		addTalkId(Гальман);
		addKillId(Пещерный_Зверь,Волна_Смерти);
		addQuestItem(Зуб_Пещерного_Зверя,Свет_Волны_Смерти,Запечатанный_Камень,Распечатанный_Камень);
		addLevelCheck(55, 66);
	}

	@Override
	public String onEvent(String event, QuestState qs, NpcInstance npc)
	{
		String htmltext = event;		
		
		if(event.equalsIgnoreCase("1"))
		{
			htmltext = "vanutu_kill_wait.htm";
			qs.setCond(1);
		}
		if(event.equalsIgnoreCase("2"))
		{
			htmltext = "galman_finish.htm";
			qs.takeAllItems(Запечатанный_Камень);
			qs.giveItems(Распечатанный_Камень, 1);
			qs.setCond(4);
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState qs)
	{
		int npcId = npc.getNpcId();
		int cond = qs.getCond();
		String htmltext = "noquest";		
		
		switch(npcId)
		{
			case Вануту:
			{
				if(cond == 0)
				{
					if(checkStartCondition(qs.getPlayer()))
					{					
						htmltext = "vanutu_ft.htm";
					}
					else
					{
						htmltext = "cond_quest_level.htm";
					}
				}				
				else if(cond == 1)
				{
					if(qs.getQuestItemsCount(Зуб_Пещерного_Зверя) == 360 && qs.getQuestItemsCount(Свет_Волны_Смерти) == 360 && qs.getQuestItemsCount(Запечатанный_Камень) == 0)
					{
						htmltext = "vanutu_finish_2.htm";
						qs.giveItems(Адена, Количество_Адены);
						qs.takeAllItems(Зуб_Пещерного_Зверя);
						qs.takeAllItems(Свет_Волны_Смерти);						
						qs.randomReward(mReward);						
						qs.finishQuest();
					}
					else if(qs.getQuestItemsCount(Зуб_Пещерного_Зверя) == 360 && qs.getQuestItemsCount(Свет_Волны_Смерти) == 360 && qs.getQuestItemsCount(Запечатанный_Камень) == 1)
					{
						htmltext = "vanutu_finish_1.htm";
						qs.setCond(3);
					}
					else
					{
						htmltext = "vanutu_kill_wait.htm";
					}
				}
				else if(cond == 4)
				{
					htmltext = "vanutu_finish_2.htm";
					qs.giveItems(Адена, Количество_Адены);
					qs.takeAllItems(Зуб_Пещерного_Зверя);
					qs.takeAllItems(Свет_Волны_Смерти);
					qs.randomReward(mReward);						
					qs.finishQuestWithoutRemovingItems();					
				}
			}
			break;
			
			case Гальман:
			{
				if(cond == 3 && qs.getQuestItemsCount(Запечатанный_Камень) == 1)
				{
					htmltext = "galman_ft.htm";
				}
			}
			break;
		}		
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState qs)
	{
		int npcId = npc.getNpcId();
		int cond = qs.getCond();
		
		if(cond == 1)
		{
			if(npcId == Пещерный_Зверь)
			{
				qs.rollAndGive(Зуб_Пещерного_Зверя, 1, 1, 360, Шанс_Дропа);
			}
			
			if(npcId == Волна_Смерти)
			{
				qs.rollAndGive(Свет_Волны_Смерти, 1, 1, 360, Шанс_Дропа);				
			}
			
			if(npcId == Пещерный_Зверь && npcId == Волна_Смерти)
			{
				qs.rollAndGive(Запечатанный_Камень, 1, 1, 1, Шанс_Дропа_Запечатанного_камня);
			}
		}
		return null;
	}	
}