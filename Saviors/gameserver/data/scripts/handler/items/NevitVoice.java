package handler.items;

import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.items.ItemInstance;

public class NevitVoice extends SimpleItemHandler
{
	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		int itemId = item.getItemId();

		if(!reduceItem(player, item))
		{
			return false;
		}

		sendUseMessage(player, item);

		switch(itemId)
		{
			case 17094:
				player.addRecomHave(10);
				break;
			default:
				return false;
		}

		return true;
	}
}
