package quests;

import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

/*Done by Start-Dev*/

public final class _358_IllegitimateChildOfAGoddess extends Quest
{
	//npc
	private static final int Рувард = 30942;	
	//mobs
  private static final int[] Змеи = {20666,20669};			
	//item
	private static final int Чешуя_Змеи = 5872;	
	//награда
	private static final int[][] randReward =
		{
		{8, 4937, 1 },
		{20, 4938, 1 },
		{41, 4939, 1 },
		{59, 4973, 1 },
		{59, 4974, 1 },
		{59, 4975, 1 },
		{59, 4126, 1 },
		{59, 4127, 1 },
		{59, 4128, 1 },
		{59, 4129, 1 },
		{59, 4130, 1 },
		{59, 4131, 1 },};

	public _358_IllegitimateChildOfAGoddess()
	{
		super(PARTY_NONE, REPEATABLE);

		addStartNpc(Рувард);	

		addKillId(Змеи);

		addQuestItem(Чешуя_Змеи);

		addLevelCheck(63, 67);
	}

	@Override
	public String onEvent(String event, QuestState qs, NpcInstance npc)
	{		
		String htmltext = event;		
		if(event.equalsIgnoreCase("1"))
		{		
			qs.setCond(1);
			htmltext = "ruvard_start.htm";
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
			case Рувард:
				if(cond == 0)
				{
					if(checkStartCondition(qs.getPlayer()))
					{
						htmltext = "ruvard_ft.htm";
					}
					else
					{
						htmltext = "cond_quest_level.htm";
					}
				}
				
				if(cond == 1)
				{					
					htmltext = "ruvard_wait.htm";									
				}				
				
				if(cond == 2)
				{
					htmltext = "ruvard_finish.htm";
					qs.takeAllItems(Чешуя_Змеи);
					qs.randomReward(randReward);		
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
			for(int i = 0; i < Змеи.length; i++)
			{
				if(npcId == Змеи[i])
				{
					qs.rollAndGive(Чешуя_Змеи, 1, 1, 520, 66);
				}
			}
			
			if(qs.getQuestItemsCount(Чешуя_Змеи) >= 520)
			{
				qs.setCond(2);
			}
		}		
		return null;
	}
}
