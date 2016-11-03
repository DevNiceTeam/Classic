package quests;

import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

/*Done by Start-Dev*/

public final class _360_PlunderTheSupplies extends Quest
{
	//npc
	private static final int Рувард = 30942;	
	//mobs
  private static final int[] Орки = {20666,20669};			
	//item
	private static final int Припасы = 5872;	

	public _360_PlunderTheSupplies()
	{
		super(PARTY_NONE, REPEATABLE);

		addStartNpc(Рувард);	

		addKillId(Орки);

		addQuestItem(Припасы);

		addLevelCheck(52, 59);
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
					qs.takeAllItems(Припасы);
					qs.giveItems(57, 14000);
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
			for(int i = 0; i < Орки.length; i++)
			{
				if(npcId == Орки[i])
				{
					qs.rollAndGive(Припасы, 1, 1, 500, 66);
				}
			}
			
			if(qs.getQuestItemsCount(Припасы) >= 500)
			{
				qs.setCond(2);
			}
		}		
		return null;
	}
}
