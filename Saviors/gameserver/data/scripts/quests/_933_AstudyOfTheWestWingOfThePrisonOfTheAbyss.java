package quests;

import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

/*Done by DXVSI*/

public final class _933_AstudyOfTheWestWingOfThePrisonOfTheAbyss extends Quest
{
	//npc
	private static final int Магрит = 31774;
	private static final int Ингрит = 31775;
	//mobs
  	private static final int[] Мобы = {21638,21639,21640,21641};
	//item
	private static final int Осколки = 90008;
	//reward item
	private static final int Подарок_Измерения = 90136;


	public _933_AstudyOfTheWestWingOfThePrisonOfTheAbyss()
	{
		super(PARTY_ONE, REPEATABLE);

		addStartNpc(Магрит,Ингрит);

		addKillId(Мобы);

		addQuestItem(Осколки);

		addLevelCheck(40, 46);
	}

	@Override
	public String onEvent(String event, QuestState qs, NpcInstance npc)
	{		
		String htmltext = event;		
		if(event.equalsIgnoreCase("1"))
		{
			htmltext = "margarit_ft_st2.htm";
		}
		else if(event.equalsIgnoreCase("2"))
		{
			htmltext = "margarit_ft_st3.htm";
		}
		else if(event.equalsIgnoreCase("3"))
		{
			qs.setCond(1);
			htmltext = "margarit_ft_st4.htm";
		}

		if(event.equalsIgnoreCase("4"))
		{
			htmltext = "ingrit_finish.htm";
			qs.takeAllItems(Осколки);
			qs.addExpAndSp(250000,7700);
			qs.giveItems(Подарок_Измерения,1);
			qs.finishQuest();
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
			case Магрит:
				if(cond == 0)
				{
					if(checkStartCondition(qs.getPlayer()))
					{
						htmltext = "margarit_ft.htm";
					}
					else
					{
						htmltext = "cond_quest_level.htm";
					}
				}
				
				if(cond == 1)
				{
					htmltext = "margarit_ft_st4.htm";
				}				
				
				if(cond == 2)
				{
					htmltext = "margarit_finish.htm";// :TODO Диалог в конце квеста
					qs.takeAllItems(Осколки);
					qs.addExpAndSp(250000,7700);
					qs.giveItems(Подарок_Измерения,1);
					qs.finishQuest();
				}
				break;

			case Ингрит:
				if(cond == 2)
				{
					htmltext = "ingrit_pre_finish.htm";
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
			for(int i = 0; i < Мобы.length; i++)
			{
				if(npcId == Мобы[i])
				{
					qs.rollAndGive(Осколки, 1, 1, 50, 66);
				}
			}
			
			if(qs.getQuestItemsCount(Осколки) >= 50)
			{
				qs.setCond(2);
			}
		}		
		return null;
	}
}