package quests;

import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

/*Done by Start-Dev*/
//by DXVSI

public final class _11000_MoonKnight extends Quest
{
	//npc
	private static final int Джонс = 30939;
	private static final int Дамион = 30208;
	private static final int Амора = 30940;
	private static final int Нети = 30425;
	private static final int Роленто = 30437;
	private static final int Гудз = 30941;
	//mobs
	private static final int Командир_Орков_Турек = 27202;
	private static final int Вор_Гноллов = 27201;
	private static final int Захватчик_Орков_Турек = 27203;
	//item
	private static final int Заготовка = 49555;
	private static final int Расписка_Аморы = 49556;
	private static final int Контракт_о_Торговле_Доспехами = 49557;
	private static final int Приказ_Орков_Турек = 49558;
	private static final int Голова_Захватчика_Орков_Турек = 49561;
	private static final int Сумка_Роленто = 49559;
	private static final int Сертификат_Гильдии_Железных_Весов = 49560;

	public _11000_MoonKnight()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(Джонс);

		addTalkId(Дамион, Амора, Роленто, Нети, Гудз);

		addKillId(Командир_Орков_Турек, Вор_Гноллов, Захватчик_Орков_Турек);

		addQuestItem(Заготовка, Расписка_Аморы, Контракт_о_Торговле_Доспехами, Голова_Захватчика_Орков_Турек, Приказ_Орков_Турек, Сумка_Роленто, Сертификат_Гильдии_Железных_Весов);

		addLevelCheck(25, 40);
	}

	@Override
	public String onEvent(String event, QuestState qs, NpcInstance npc)
	{		
		String htmltext = event;
		int cond = qs.getCond();
		if(event.equalsIgnoreCase("1"))
		{
			qs.setCond(1);
			htmltext = "jons_start.htm";
		}		
		else if (event.equalsIgnoreCase("2"))
		{
			qs.setCond(5);		
			htmltext = "jons_kill_wait.htm";
		}
		else if(event.equalsIgnoreCase("3"))
		{
			qs.setCond(6);
			htmltext = "jons_to_neti.htm";
		}
		else if(event.equalsIgnoreCase("4"))
		{
			htmltext = "rolento_st2.htm";
		}
		else if(event.equalsIgnoreCase("5"))
		{
			htmltext = "rolento_st3.htm";
			qs.takeAllItems(Контракт_о_Торговле_Доспехами);
			qs.giveItems(Сумка_Роленто, 1);
			qs.giveItems(Сертификат_Гильдии_Железных_Весов, 1);
			qs.setCond(8);	
		}
		else if(event.equalsIgnoreCase("6"))
		{
			htmltext = "gudz_st2.htm";
		}
		else if(event.equalsIgnoreCase("7"))
		{
			htmltext = "gudz_st3.htm";
		}
		else if(event.equalsIgnoreCase("8"))
		{
			htmltext = "gudz_st4.htm";
			qs.takeAllItems(Приказ_Орков_Турек);
			qs.takeAllItems(Сумка_Роленто);
			qs.takeAllItems(Сертификат_Гильдии_Железных_Весов);
			qs.setCond(9);
		}
		else if(event.equalsIgnoreCase("damion_1")) 
		{
			htmltext = "damion_ft.htm";
		}
		else if(event.equalsIgnoreCase("damion_return")) 
		{			
			htmltext = "damion_st2.htm";
		}		
		else if(event.equalsIgnoreCase("heavy"))
		{
			if(cond == 10)
			{
				qs.giveItems(7850, 1);
				qs.giveItems(7851, 1);
				qs.giveItems(7852, 1);
				qs.giveItems(7853, 1);		
				qs.finishQuest();
			}
		}
		else if(event.equalsIgnoreCase("light"))
		{
			if(cond == 10)
			{
				qs.giveItems(7850, 1);
				qs.giveItems(7854, 1);
				qs.giveItems(7855, 1);
				qs.giveItems(7856, 1);
				qs.finishQuest();
			}
		}
		else if(event.equalsIgnoreCase("mag"))
		{
			if(cond == 10)
			{
				qs.giveItems(7850, 1);
				qs.giveItems(7857, 1);
				qs.giveItems(7858, 1);
				qs.giveItems(7859, 1);
				qs.finishQuest();
			}
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState qs)
	{
		int npcId = npc.getNpcId();
		String htmltext = "noquest";
		int cond = qs.getCond();

		switch(npcId)
		{
			case Джонс:
				if(cond == 0)
				{
					if(checkStartCondition(qs.getPlayer()))
					{
						htmltext = "jons_ft.htm";
					}
					else
					{
						htmltext = "cond_quest_level.htm";
					}
				}
				else if(cond == 1)
				{
					htmltext = "jons_wait.htm";
				}
				else if(cond == 4)
				{
					htmltext = "jons_to_kill_orc.htm";					
				}
				else if(cond == 5)
				{
					if(qs.getQuestItemsCount(Контракт_о_Торговле_Доспехами) == 1 && qs.getQuestItemsCount(Приказ_Орков_Турек) == 1)
					{
						htmltext = "jons_finish_kill_orc.htm";
					}
					else
					{
						htmltext = "jons_kill_wait.htm";
					}
				}
				else if(cond == 6)
				{
					htmltext = "jons_to_neti.htm";
				}
				else if(cond == 10)
				{
					htmltext = "jons_finish.htm";
				}
				break;

			case Дамион:
				if(cond == 1)
				{
					htmltext = "damion_ft.htm";
					qs.setCond(2);
				}
				else if(cond == 2)
				{
					htmltext = "damion_ft.htm";
				}
				else if(cond == 3)
				{
					htmltext = "damion_finish.htm";
					qs.takeAllItems(Расписка_Аморы);
					qs.setCond(4);
				}
				else if(cond == 4)
				{
					htmltext = "damion_wait.htm";
				}
				break;

			case Амора:
				if(qs.getQuestItemsCount(Заготовка) == 10)
				{
					qs.takeAllItems(Заготовка);
					htmltext = "amora_ft.htm";
					qs.setCond(3);
					qs.giveItems(Расписка_Аморы, 1);
				}
				else
				{
					htmltext = "amora_item_wait.htm";
				}
				if(cond == 3)
				{
					htmltext = "amora_to_damion_wait.htm";
				}
				break;
				
			case Нети:
				if(cond == 6)
				{
					htmltext = "neti_ft.htm";
					qs.setCond(7);
				}
				else if(cond == 7)
				{
					htmltext = "neti_wait.htm";
				}
				break;
				
			case Роленто:
				if(cond == 7)
				{						
					if (qs.getQuestItemsCount(Контракт_о_Торговле_Доспехами) == 1)
					{
						htmltext = "rolento_ft.htm";												
					}
					else
					{
						htmltext = "rolento_miss.htm";	
					}						
				}		
				else if (cond == 8) 
				{
					htmltext = "rolento_st3.htm";	
				}
				break;
				
			case Гудз:
				if(cond == 8)
				{
					if(qs.getQuestItemsCount(Приказ_Орков_Турек) == 1)
					{
						htmltext = "gudz_ft.htm";						
					}
					else
					{
						htmltext = "gudz_miss.htm";
					}
				}				
				else if(cond == 9)
				{
					if(qs.getQuestItemsCount(Голова_Захватчика_Орков_Турек) == 10)
					{
						htmltext = "gudz_finish.htm";
						qs.takeAllItems(Голова_Захватчика_Орков_Турек);
						qs.setCond(10);
					}
					else
					{
						htmltext = "gudz_st4.htm";
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

		if(cond == 2)
		{
			if(npcId == Вор_Гноллов)
			{
				qs.rollAndGive(Заготовка, 1, 1, 10, 66);
			}
		}
		else if(cond == 5)
		{
			if(npcId == Командир_Орков_Турек)
			{
				qs.rollAndGive(Контракт_о_Торговле_Доспехами, 1, 1, 1, 40);
				qs.rollAndGive(Приказ_Орков_Турек, 1, 1, 1, 40);
			}
		}
		else if(cond == 9)
		{
			if(npcId == Захватчик_Орков_Турек)
			{
				qs.rollAndGive(Голова_Захватчика_Орков_Турек, 1, 1, 10, 66);
			}
		}
		return null;
	}
}
