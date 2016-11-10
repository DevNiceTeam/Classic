package quests;

import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

/*Done by Start-Dev*/

public final class _354_ConquestofAlligatorIsland extends Quest
{
	//npc
	private static final int Морита = 30937;	
	//mobs
  private static final int[] Крокодилы = {20804,20805,20806,20807,20808,20793};			
	//item
	private static final int Зуб_Аллигатора = 5863;	

	public _354_ConquestofAlligatorIsland()
	{
		super(PARTY_ONE, REPEATABLE);

		addStartNpc(Морита);	

		addKillId(Крокодилы);

		addQuestItem(Зуб_Аллигатора);

		addLevelCheck(38, 49);
	}

	@Override
	public String onEvent(String event, QuestState qs, NpcInstance npc)
	{		
		String htmltext = event;		
		if(event.equalsIgnoreCase("1"))
		{		
			qs.setCond(1);
			htmltext = "morita_start.htm";
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
			case Морита:
				if(cond == 0)
				{
					if(checkStartCondition(qs.getPlayer()))
					{
						htmltext = "morita_ft.htm";
					}
					else
					{
						htmltext = "cond_quest_level.htm";
					}
				}
				
				if(cond == 1)
				{					
					htmltext = "morita_wait.htm";									
				}				
				
				if(cond == 2)
				{
					htmltext = "morita_finish.htm";
					qs.takeAllItems(Зуб_Аллигатора);
					qs.giveItems(57, 2000);
					qs.finishQuest();
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
			for(int i = 0; i < Крокодилы.length; i++)
			{
				if(npcId == Крокодилы[i])
				{
					qs.rollAndGive(Зуб_Аллигатора, 1, 1, 400, 66);
				}
			}
			
			if(qs.getQuestItemsCount(Зуб_Аллигатора) >= 400)
			{
				qs.setCond(2);
			}
		}		
		return null;
	}
}
