package services.petevolve;

import studio.lineage2.commons.dao.JdbcEntityState;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.xml.holder.ItemHolder;
import studio.lineage2.gameserver.data.xml.holder.PetDataHolder;
import studio.lineage2.gameserver.handler.bypass.Bypass;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.instances.PetInstance;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.network.l2.s2c.InventoryUpdatePacket;
import studio.lineage2.gameserver.templates.item.ItemTemplate;
import studio.lineage2.gameserver.utils.Functions;
import studio.lineage2.gameserver.utils.ItemFunctions;
import studio.lineage2.gameserver.utils.Util;

public class exchange
{
	/** Билеты для обмена **/
	private static final int PEticketB = 7583;
	private static final int PEticketC = 7584;
	private static final int PEticketK = 7585;

	/** Дудки для вызова петов **/
	private static final int BbuffaloP = 6648;
	private static final int BcougarC = 6649;
	private static final int BkookaburraO = 6650;

	private static final int IMPROVED_BABY_COUGAR_ITEM_ID = 10312;
	private static final int IMPROVED_BABY_BUFFALO_ITEM_ID = 10311;
	private static final int IMPROVED_BABY_KOOKABURRA_ITEM_ID = 10313;

	@Bypass("services.petevolve.exchange:exch_1")
	public void exch_1(Player player, NpcInstance npc, String[] param)
	{
		if(ItemFunctions.getItemCount(player, PEticketB) >= 1)
		{
			ItemFunctions.deleteItem(player, PEticketB, 1);
			ItemFunctions.addItem(player, BbuffaloP, 1);
			return;
		}
		Functions.show("scripts/services/petevolve/exchange_no.htm", player);
	}

	@Bypass("services.petevolve.exchange:exch_2")
	public void exch_2(Player player, NpcInstance npc, String[] param)
	{
		if(ItemFunctions.getItemCount(player, PEticketC) >= 1)
		{
			ItemFunctions.deleteItem(player, PEticketC, 1);
			ItemFunctions.addItem(player, BcougarC, 1);
			return;
		}
		Functions.show("scripts/services/petevolve/exchange_no.htm", player);
	}

	@Bypass("services.petevolve.exchange:exch_3")
	public void exch_3(Player player, NpcInstance npc, String[] param)
	{
		if(ItemFunctions.getItemCount(player, PEticketK) >= 1)
		{
			ItemFunctions.deleteItem(player, PEticketK, 1);
			ItemFunctions.addItem(player, BkookaburraO, 1);
			return;
		}
		Functions.show("scripts/services/petevolve/exchange_no.htm", player);
	}

	@Bypass("services.petevolve.exchange:showBabyPetExchange")
	public void showBabyPetExchange(Player player, NpcInstance npc, String[] param)
	{
		if(!Config.SERVICES_EXCHANGE_BABY_PET_ENABLED)
		{
			Functions.show("Сервис отключен.", player);
			return;
		}
		ItemTemplate item = ItemHolder.getInstance().getTemplate(Config.SERVICES_EXCHANGE_BABY_PET_ITEM);
		String out = "";
		out += "<html><body>Вы можете в любое время обменять вашего Improved Baby пета на другой вид, без потери опыта. Пет при этом должен быть вызван.";
		out += "<br>Стоимость обмена: " + Util.formatAdena(Config.SERVICES_EXCHANGE_BABY_PET_PRICE) + " " + item.getName();
		out += "<br><button width=250 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h htmbypass_services.petevolve.exchange:exToCougar\" value=\"Обменять на Improved Cougar\">";
		out += "<br1><button width=250 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h htmbypass_services.petevolve.exchange:exToBuffalo\" value=\"Обменять на Improved Buffalo\">";
		out += "<br1><button width=250 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h htmbypass_services.petevolve.exchange:exToKookaburra\" value=\"Обменять на Improved Kookaburra\">";
		out += "</body></html>";
		Functions.show(out, player);
	}

	@Bypass("services.petevolve.exchange:showErasePetName")
	public void showErasePetName(Player player, NpcInstance npc, String[] param)
	{
		if(!Config.SERVICES_CHANGE_PET_NAME_ENABLED)
		{
			Functions.show("Сервис отключен.", player);
			return;
		}
		ItemTemplate item = ItemHolder.getInstance().getTemplate(Config.SERVICES_CHANGE_PET_NAME_ITEM);
		String out = "";
		out += "<html><body>Вы можете обнулить имя у пета, для того чтобы назначить новое. Пет при этом должен быть вызван.";
		out += "<br>Стоимость обнуления: " + Util.formatAdena(Config.SERVICES_CHANGE_PET_NAME_PRICE) + " " + item.getName();
		out += "<br><button width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" action=\"bypass -h htmbypass_services.petevolve.exchange:erasePetName\" value=\"Обнулить имя\">";
		out += "</body></html>";
		Functions.show(out, player);
	}

	@Bypass("services.petevolve.exchange:erasePetName")
	public void erasePetName(Player player, NpcInstance npc, String[] param)
	{
		if(!Config.SERVICES_CHANGE_PET_NAME_ENABLED)
		{
			Functions.show("Сервис отключен.", player);
			return;
		}

		PetInstance pl_pet = player.getPet();
		if(pl_pet == null)
		{
			Functions.show("Питомец должен быть вызван.", player);
			return;
		}
		if(player.getInventory().destroyItemByItemId(Config.SERVICES_CHANGE_PET_NAME_ITEM, Config.SERVICES_CHANGE_PET_NAME_PRICE))
		{
			pl_pet.setName(pl_pet.getTemplate().name);
			pl_pet.broadcastCharInfo();

			PetInstance _pet = pl_pet;
			ItemInstance control = _pet.getControlItem();
			if(control != null)
			{
				control.setCustomType2(1);
				control.setJdbcState(JdbcEntityState.UPDATED);
				control.update();
				player.sendPacket(new InventoryUpdatePacket().addModifiedItem(player, control));
			}
			Functions.show("Имя стерто.", player);
		}
		else if(Config.SERVICES_CHANGE_PET_NAME_ITEM == 57)
		{
			player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
		}
		else
		{
			player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
		}
	}

	@Bypass("services.petevolve.exchange:exToCougar")
	public void exToCougar(Player player, NpcInstance npc, String[] param)
	{
		if(!Config.SERVICES_EXCHANGE_BABY_PET_ENABLED)
		{
			Functions.show("Сервис отключен.", player);
			return;
		}

		PetInstance pl_pet = player.getPet();
		if(pl_pet == null || pl_pet.isDead() || !(pl_pet.getNpcId() == PetDataHolder.IMPROVED_BABY_BUFFALO_ID || pl_pet.getNpcId() == PetDataHolder.IMPROVED_BABY_KOOKABURRA_ID))
		{
			Functions.show("Пет должен быть вызван.", player);
			return;
		}
		if(player.getInventory().destroyItemByItemId(Config.SERVICES_EXCHANGE_BABY_PET_ITEM, Config.SERVICES_EXCHANGE_BABY_PET_PRICE))
		{
			ItemInstance control = player.getInventory().getItemByObjectId(pl_pet.getControlItemObjId());
			control.setItemId(IMPROVED_BABY_COUGAR_ITEM_ID);
			control.setJdbcState(JdbcEntityState.UPDATED);
			control.update();
			player.sendPacket(new InventoryUpdatePacket().addModifiedItem(player, control));
			pl_pet.unSummon(false);
			Functions.show("Пет изменен.", player);
		}
		else if(Config.SERVICES_EXCHANGE_BABY_PET_ITEM == 57)
		{
			player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
		}
		else
		{
			player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
		}
	}

	@Bypass("services.petevolve.exchange:exToBuffalo")
	public void exToBuffalo(Player player, NpcInstance npc, String[] param)
	{
		if(!Config.SERVICES_EXCHANGE_BABY_PET_ENABLED)
		{
			Functions.show("Сервис отключен.", player);
			return;
		}

		PetInstance pl_pet = player.getPet();
		if(pl_pet == null || pl_pet.isDead() || !(pl_pet.getNpcId() == PetDataHolder.IMPROVED_BABY_COUGAR_ID || pl_pet.getNpcId() == PetDataHolder.IMPROVED_BABY_KOOKABURRA_ID))
		{
			Functions.show("Пет должен быть вызван.", player);
			return;
		}
		if(Config.ALT_IMPROVED_PETS_LIMITED_USE && player.isMageClass())
		{
			Functions.show("Этот пет только для воинов.", player);
			return;
		}
		if(player.getInventory().destroyItemByItemId(Config.SERVICES_EXCHANGE_BABY_PET_ITEM, Config.SERVICES_EXCHANGE_BABY_PET_PRICE))
		{
			ItemInstance control = player.getInventory().getItemByObjectId(pl_pet.getControlItemObjId());
			control.setItemId(IMPROVED_BABY_BUFFALO_ITEM_ID);
			control.setJdbcState(JdbcEntityState.UPDATED);
			control.update();
			player.sendPacket(new InventoryUpdatePacket().addModifiedItem(player, control));
			pl_pet.unSummon(false);
			Functions.show("Пет изменен.", player);
		}
		else if(Config.SERVICES_EXCHANGE_BABY_PET_ITEM == 57)
		{
			player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
		}
		else
		{
			player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
		}
	}

	@Bypass("services.petevolve.exchange:exToKookaburra")
	public void exToKookaburra(Player player, NpcInstance npc, String[] param)
	{
		if(!Config.SERVICES_EXCHANGE_BABY_PET_ENABLED)
		{
			Functions.show("Сервис отключен.", player);
			return;
		}

		PetInstance pl_pet = player.getPet();
		if(pl_pet == null || pl_pet.isDead() || !(pl_pet.getNpcId() == PetDataHolder.IMPROVED_BABY_BUFFALO_ID || pl_pet.getNpcId() == PetDataHolder.IMPROVED_BABY_COUGAR_ID))
		{
			Functions.show("Пет должен быть вызван.", player);
			return;
		}
		if(Config.ALT_IMPROVED_PETS_LIMITED_USE && !player.isMageClass())
		{
			Functions.show("Этот пет только для магов.", player);
			return;
		}
		if(player.getInventory().destroyItemByItemId(Config.SERVICES_EXCHANGE_BABY_PET_ITEM, Config.SERVICES_EXCHANGE_BABY_PET_PRICE))
		{
			ItemInstance control = player.getInventory().getItemByObjectId(pl_pet.getControlItemObjId());
			control.setItemId(IMPROVED_BABY_KOOKABURRA_ITEM_ID);
			control.setJdbcState(JdbcEntityState.UPDATED);
			control.update();
			player.sendPacket(new InventoryUpdatePacket().addModifiedItem(player, control));
			pl_pet.unSummon(false);
			Functions.show("Пет изменен.", player);
		}
		else if(Config.SERVICES_EXCHANGE_BABY_PET_ITEM == 57)
		{
			player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
		}
		else
		{
			player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
		}
	}
}