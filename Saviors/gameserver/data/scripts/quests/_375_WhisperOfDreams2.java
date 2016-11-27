package quests;

import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public class _375_WhisperOfDreams2 extends Quest
{

	private static final int Вануту = 30938;
	private static final int Лимал_Каринесс = 20628;
	private static final int Карик = 20629;

	private static final int Рог_Карика = 5888;
	private static final int Кровь_Лимал_Каринесс = 5889;
	private static final int Таинственный_Камень = 5887;


	public _375_WhisperOfDreams2()
	{
		super(PARTY_NONE, REPEATABLE);
		addStartNpc(Вануту);
		addKillId(Карик,Лимал_Каринесс);
		addQuestItem(Рог_Карика,Кровь_Лимал_Каринесс,Таинственный_Камень);
		addLevelCheck(60, 74);
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
		
		return event;
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
					if(checkStartCondition(qs.getPlayer()) && qs.getQuestItemsCount(Таинственный_Камень) >= 1)
					{
						htmltext = "vanutu_ft.htm";
					}
					else
					{
						htmltext = "cond_quest_level.htm";
					}
				}
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
			if(npcId == Карик)
			{
				qs.rollAndGive(Рог_Карика, 1, 1, 360, 66);
			}

			if(npcId == Лимал_Каринесс)
			{
				qs.rollAndGive(Кровь_Лимал_Каринесс, 1, 1, 360, 66);
			}
		}

		return null;
	}

}