package handler.onshiftaction;

import studio.lineage2.gameserver.model.GameObjectsStorage;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;

public class OnShiftAction_ItemInstance extends ScriptOnShiftActionHandler<ItemInstance>
{
	@Override
	public Class<ItemInstance> getClazz()
	{
		return ItemInstance.class;
	}

	@Override
	public boolean call(ItemInstance item, Player player)
	{
		if(!player.getPlayerAccess().CanViewChar)
		{
			return false;
		}

		HtmlMessage msg = new HtmlMessage(0);
		msg.setFile("scripts/actions/admin.L2ItemInstance.onActionShift.htm");
		msg.replace("%name%", String.valueOf(item.getTemplate().getName()));
		msg.replace("%objId%", String.valueOf(item.getObjectId()));
		msg.replace("%itemId%", String.valueOf(item.getItemId()));
		msg.replace("%grade%", String.valueOf(item.getGrade()));
		msg.replace("%count%", String.valueOf(item.getCount()));

		Player owner = GameObjectsStorage.getPlayer(item.getOwnerId()); //FIXME [VISTALL] несовсем верно, может быть CCE при условии если овнер не игрок
		msg.replace("%owner%", String.valueOf(owner == null ? "none" : owner.getName()));
		msg.replace("%ownerId%", String.valueOf(item.getOwnerId()));

		msg.replace("%enchLevel%", String.valueOf(item.getEnchantLevel()));
		msg.replace("%type%", String.valueOf(item.getItemType()));

		msg.replace("%dropTime%", String.valueOf(item.getDropTimeOwner()));
		//dialog = msg.addVar("dropOwner", String.valueOf(item.getDropOwnerId()));
		//dialog = msg.addVar("dropOwnerId", String.valueOf(item.getDropOwnerId()));

		player.sendPacket(msg);
		return true;
	}
}