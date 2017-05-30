package handler.items;

import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.items.ItemInstance;

public class BookMark extends SimpleItemHandler
{
	private static final int ADD_CAPACITY = 3;

	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		if(player == null)
		{
			return false;
		}

		if(!reduceItem(player, item))
		{
			return false;
		}

		sendUseMessage(player, item);

		player.getBookMarkList().incCapacity(ADD_CAPACITY);
		return true;
	}
}