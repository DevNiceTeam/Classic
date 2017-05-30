package handler.items;

import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.network.l2.s2c.SystemMessage;
import studio.lineage2.gameserver.utils.ItemFunctions;

//By Evil_dnk

public class PkDecrease extends SimpleItemHandler
{
	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		int itemId = item.getItemId();

		switch(itemId)
		{
			//Scroll: PK
			case 35741:
			case 23474:
				DecreasPk(player, 1, itemId);
				break;
			case 35752:
			case 23475:
				DecreasPk(player, 3, itemId);
				break;
			case 35753:
			case 23476:
				DecreasPk(player, 5, itemId);
				break;
			case 35754:
			case 23477:
				DecreasPk(player, 10, itemId);
				break;

			//Scroll: Reputation
			case 35742:
			case 23478:
				DecreasRep(player, 1000, itemId);
				break;
			case 35755:
			case 23479:
				DecreasRep(player, 5000, itemId);
				break;
			case 35756:
			case 23480:
				DecreasRep(player, 30000, itemId);
				break;

			//Scroll: Reputation set 0
			case 35757:
			case 23481:
				if(player.getKarma() < 0 && !player.isCursedWeaponEquipped())
				{
					player.setKarma(0);
					ItemFunctions.deleteItem(player, itemId, 1);
				}
				else
				{
					player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(itemId));
				}
				break;

			default:
				return false;
		}
		return true;
	}

	private void DecreasPk(Player player, int count, int item)
	{
		if(player.getPkKills() > 0 && !player.isCursedWeaponEquipped())
		{
			if(player.getPkKills() - count < 0)
			{
				player.setPkKills(0);
			}
			else
			{
				player.setPkKills(player.getPkKills() - count);
			}
			ItemFunctions.deleteItem(player, item, 1);
			player.broadcastCharInfo();
		}
		else
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(item));
		}
	}

	private void DecreasRep(Player player, int count, int item)
	{
		if(player.getKarma() < 0 && !player.isCursedWeaponEquipped())
		{
			if(player.getKarma() + count > 0)
			{
				player.setKarma(0);
			}
			else
			{
				player.setKarma(player.getKarma() + count);
			}
			ItemFunctions.deleteItem(player, item, 1);
			player.broadcastCharInfo();
		}
		else
		{
			player.sendPacket(new SystemMessage(SystemMessage.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS).addItemName(item));
		}
	}
}
