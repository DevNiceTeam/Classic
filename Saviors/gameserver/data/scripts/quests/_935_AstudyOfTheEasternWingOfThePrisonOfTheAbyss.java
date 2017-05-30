package quests;

import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

/*Done by DXVSI*/
//:TODO Все диалоги этого квеста

public final class _935_AstudyOfTheEasternWingOfThePrisonOfTheAbyss extends Quest
{
	//npc
	private static final int Айрис = 31776;
	private static final int Розамми = 31777;
	//mobs
  	private static final int[] Мобы = {21644,21645,21646,21647};
	//item
	private static final int Образ_Зла = 90009;
	//reward item
	private static final int Подарок_Измерения = 90136;


	public _935_AstudyOfTheEasternWingOfThePrisonOfTheAbyss()
	{
		super(PARTY_ONE, REPEATABLE);

		addStartNpc(Айрис,Розамми);

		addKillId(Мобы);

		addQuestItem(Образ_Зла);

		addLevelCheck(45, 51);
	}

	@Override
	public String onEvent(String event, QuestState qs, NpcInstance npc)
	{		
		String htmltext = event;		

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
			case Айрис:
				if(cond == 0)
				{
					if(checkStartCondition(qs.getPlayer()))
					{
						htmltext = "iris_ft.htm";
					}
					else
					{
						htmltext = "cond_quest_level.htm";
					}
				}
				
				if(cond == 1)
				{
					htmltext = "iris_ft.htm";
				}				
				
				if(cond == 2)
				{
					htmltext = "iris_finish.htm";
					qs.takeAllItems(Образ_Зла);
					qs.addExpAndSp(300000,9000);
					qs.giveItems(Подарок_Измерения,1);
					qs.finishQuest();
				}
				break;

			case Розамми:
				if(cond == 2)
				{
					htmltext = "rosamy_finish.htm";
					qs.takeAllItems(Образ_Зла);
					qs.addExpAndSp(300000,9000);
					qs.giveItems(Подарок_Измерения,1);
					qs.finishQuest();
				}
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
					qs.rollAndGive(Образ_Зла, 1, 1, 50, 66);
				}
			}
			
			if(qs.getQuestItemsCount(Образ_Зла) >= 50)
			{
				qs.setCond(2);
			}
		}		
		return null;
	}
}