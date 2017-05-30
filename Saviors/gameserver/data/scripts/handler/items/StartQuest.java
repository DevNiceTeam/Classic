package handler.items;

import studio.lineage2.gameserver.data.QuestHolder;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;
import studio.lineage2.gameserver.utils.ItemFunctions;

/**
 LIO
 24.01.2016
 */
public class StartQuest extends SimpleItemHandler
{
	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		switch(item.getItemId())
		{
			case 35702:
			{
				Quest quest = QuestHolder.getInstance().getQuest(756);
				QuestState qs = player.getQuestState(quest.getId());
				if(qs == null || qs.isCompleted())
				{
					ItemFunctions.deleteItem(player, item, 1, true);
					qs = quest.newQuestState(player);
					qs.setCond(1);
				}
			}
			case 47513:
			{
				Quest quest = QuestHolder.getInstance().getQuest(800);
				QuestState qs = player.getQuestState(quest.getId());
				if(qs == null || qs.isCompleted())
				{
					ItemFunctions.deleteItem(player, item, 1, true);
					qs = quest.newQuestState(player);
					qs.setCond(1);
				}
			}
			case 47514:
			{
				Quest quest = QuestHolder.getInstance().getQuest(801);
				QuestState qs = player.getQuestState(quest.getId());
				if(qs == null || qs.isCompleted())
				{
					ItemFunctions.deleteItem(player, item, 1, true);
					qs = quest.newQuestState(player);
					qs.setCond(1);
				}
			}
			case 47515:
			{
				Quest quest = QuestHolder.getInstance().getQuest(802);
				QuestState qs = player.getQuestState(quest.getId());
				if(qs == null || qs.isCompleted())
				{
					ItemFunctions.deleteItem(player, item, 1, true);
					qs = quest.newQuestState(player);
					qs.setCond(1);
				}
			}
			break;
		}
		return true;
	}
}