package handler.items;

import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.items.ItemInstance;

//TODO: [Bonux] Сделать через скиллы.
public class Kamaloka extends SimpleItemHandler
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
			case 13010:
			case 13297:
			case 20026:
				player.removeInstanceReusesByGroupId(1);
				break;
			case 13011:
			case 13298:
			case 20027:
				player.removeInstanceReusesByGroupId(2);
				break;
			case 13012:
			case 13299:
			case 20028:
				player.removeInstanceReusesByGroupId(3);
				break;
			default:
				return false;
		}
		return true;
	}
}
