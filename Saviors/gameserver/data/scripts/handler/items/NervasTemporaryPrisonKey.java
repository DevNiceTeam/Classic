package handler.items;

import studio.lineage2.gameserver.model.Playable;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;
import studio.lineage2.gameserver.utils.ItemFunctions;

/**
 Eanseen
 15.12.2015
 */
public class NervasTemporaryPrisonKey extends ScriptItemHandler
{
	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean ctrl)
	{
		if(!playable.isPlayer())
		{
			return false;
		}

		Player player = (Player) playable;

		QuestState state = player.getQuestState(10447);
		if(state == null || state.getCond() != 1)
		{
			return false;
		}

		if(player.getTarget() != null && player.getTarget().isNpc())
		{
			NpcInstance npc = (NpcInstance) player.getTarget();
			if(npc.getNpcId() == 19459)
			{
				state.playSound(Quest.SOUND_MIDDLE);
				state.setCond(2);
				ItemFunctions.deleteItem(player, 36665, 1, true);
			}
		}

		player.sendActionFailed();
		return true;
	}
}