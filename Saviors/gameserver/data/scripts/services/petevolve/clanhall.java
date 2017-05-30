package services.petevolve;

import studio.lineage2.commons.dao.JdbcEntityState;
import studio.lineage2.gameserver.data.xml.holder.NpcHolder;
import studio.lineage2.gameserver.data.xml.holder.PetDataHolder;
import studio.lineage2.gameserver.handler.bypass.Bypass;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.instances.PetInstance;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.network.l2.s2c.SystemMessagePacket;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.templates.pet.PetData;
import studio.lineage2.gameserver.utils.Functions;

public class clanhall
{
	// -- Pet ID --
	private static final int GREAT_WOLF = PetDataHolder.GREAT_WOLF_ID;
	private static final int WHITE_WOLF = PetDataHolder.WGREAT_WOLF_ID;
	private static final int FENRIR = PetDataHolder.FENRIR_WOLF_ID;
	private static final int WHITE_FENRIR = PetDataHolder.WFENRIR_WOLF_ID;
	private static final int WIND_STRIDER = PetDataHolder.STRIDER_WIND_ID;
	private static final int RED_WIND_STRIDER = PetDataHolder.RED_STRIDER_WIND_ID;
	private static final int STAR_STRIDER = PetDataHolder.STRIDER_STAR_ID;
	private static final int RED_STAR_STRIDER = PetDataHolder.RED_STRIDER_STAR_ID;
	private static final int TWILING_STRIDER = PetDataHolder.STRIDER_TWILIGHT_ID;
	private static final int RED_TWILING_STRIDER = PetDataHolder.RED_STRIDER_TWILIGHT_ID;

	// -- First Item ID --
	private static final int GREAT_WOLF_NECKLACE = 9882;
	private static final int FENRIR_NECKLACE = 10426;
	private static final int WIND_STRIDER_ITEM = 4422;
	private static final int STAR_STRIDER_ITEM = 4423;
	private static final int TWILING_STRIDER_ITEM = 4424;

	// -- Second Item ID --
	private static final int WHITE_WOLF_NECKLACE = 10307;
	private static final int WHITE_FENRIR_NECKLACE = 10611;
	private static final int RED_WS_ITEM = 10308;
	private static final int RED_SS_ITEM = 10309;
	private static final int RED_TW_ITEM = 10310;

	@Bypass("services.petevolve.clanhall:greatsw")
	public void greatsw(Player player, NpcInstance npc, String[] param)
	{
		boolean fwd = Integer.parseInt(param[0]) == 1;

		if(player.getInventory().getCountOf(fwd ? GREAT_WOLF_NECKLACE : WHITE_WOLF_NECKLACE) > 1)
		{
			Functions.show("scripts/services/petevolve/error_3.htm", player, npc);
			return;
		}
		if(player.getServitors().length > 0)
		{
			Functions.show("scripts/services/petevolve/error_4.htm", player, npc);
			return;
		}
		ItemInstance collar = player.getInventory().getItemByItemId(fwd ? GREAT_WOLF_NECKLACE : WHITE_WOLF_NECKLACE);
		if(collar == null)
		{
			Functions.show("scripts/services/petevolve/no_item.htm", player, npc);
			return;
		}

		PetData petDataTemplate = PetDataHolder.getInstance().getTemplateByItemId(collar.getItemId());
		if(petDataTemplate == null)
		{
			return;
		}

		NpcTemplate petTemplate = NpcHolder.getInstance().getTemplate(petDataTemplate.getNpcId());
		if(petTemplate == null)
		{
			return;
		}

		PetInstance pet = PetInstance.restore(collar, petTemplate, player);

		if(petDataTemplate.getNpcId() != (fwd ? GREAT_WOLF : WHITE_WOLF))
		{
			Functions.show("scripts/services/petevolve/error_2.htm", player, npc);
			return;
		}
		if(pet.getLevel() < 55)
		{
			Functions.show("scripts/services/petevolve/error_lvl_greatw.htm", player, npc);
			return;
		}

		collar.setItemId(fwd ? WHITE_WOLF_NECKLACE : GREAT_WOLF_NECKLACE);
		collar.setJdbcState(JdbcEntityState.UPDATED);
		collar.update();
		player.sendItemList(false);
		player.sendPacket(SystemMessagePacket.obtainItems((fwd ? WHITE_WOLF_NECKLACE : GREAT_WOLF_NECKLACE), 1, 0));
		Functions.show("scripts/services/petevolve/end_msg3_gwolf.htm", player, npc);
	}

	@Bypass("services.petevolve.clanhall:fenrir")
	public void fenrir(Player player, NpcInstance npc, String[] param)
	{
		boolean fwd = Integer.parseInt(param[0]) == 1;

		if(player.getInventory().getCountOf(fwd ? FENRIR_NECKLACE : WHITE_FENRIR_NECKLACE) > 1)
		{
			Functions.show("scripts/services/petevolve/error_3.htm", player, npc);
			return;
		}
		if(player.getServitors().length > 0)
		{
			Functions.show("scripts/services/petevolve/error_4.htm", player, npc);
			return;
		}
		ItemInstance collar = player.getInventory().getItemByItemId(fwd ? FENRIR_NECKLACE : WHITE_FENRIR_NECKLACE);
		if(collar == null)
		{
			Functions.show("scripts/services/petevolve/no_item.htm", player, npc);
			return;
		}

		PetData petDataTemplate = PetDataHolder.getInstance().getTemplateByItemId(collar.getItemId());
		if(petDataTemplate == null)
		{
			return;
		}

		NpcTemplate petTemplate = NpcHolder.getInstance().getTemplate(petDataTemplate.getNpcId());
		if(petTemplate == null)
		{
			return;
		}

		PetInstance pet = PetInstance.restore(collar, petTemplate, player);

		if(petDataTemplate.getNpcId() != (fwd ? FENRIR : WHITE_FENRIR))
		{
			Functions.show("scripts/services/petevolve/error_2.htm", player, npc);
			return;
		}
		if(pet.getLevel() < 55)
		{
			Functions.show("scripts/services/petevolve/error_lvl_fenrir.htm", player, npc);
			return;
		}

		collar.setItemId(fwd ? WHITE_FENRIR_NECKLACE : FENRIR_NECKLACE);
		collar.setJdbcState(JdbcEntityState.UPDATED);
		collar.update();
		player.sendItemList(false);
		player.sendPacket(SystemMessagePacket.obtainItems((fwd ? WHITE_FENRIR_NECKLACE : FENRIR_NECKLACE), 1, 0));
		Functions.show("scripts/services/petevolve/end_msg2_fenrir.htm", player, npc);
	}

	@Bypass("services.petevolve.clanhall:wstrider")
	public void wstrider(Player player, NpcInstance npc, String[] param)
	{
		boolean fwd = Integer.parseInt(param[0]) == 1;

		if(player.getInventory().getCountOf(fwd ? WIND_STRIDER_ITEM : RED_WS_ITEM) > 1)
		{
			Functions.show("scripts/services/petevolve/error_3.htm", player, npc);
			return;
		}
		if(player.getServitors().length > 0)
		{
			Functions.show("scripts/services/petevolve/error_4.htm", player, npc);
			return;
		}
		ItemInstance collar = player.getInventory().getItemByItemId(fwd ? WIND_STRIDER_ITEM : RED_WS_ITEM);
		if(collar == null)
		{
			Functions.show("scripts/services/petevolve/no_item.htm", player, npc);
			return;
		}

		PetData petDataTemplate = PetDataHolder.getInstance().getTemplateByItemId(collar.getItemId());
		if(petDataTemplate == null)
		{
			return;
		}

		NpcTemplate petTemplate = NpcHolder.getInstance().getTemplate(petDataTemplate.getNpcId());
		if(petTemplate == null)
		{
			return;
		}

		PetInstance pet = PetInstance.restore(collar, petTemplate, player);

		if(petDataTemplate.getNpcId() != (fwd ? WIND_STRIDER : RED_WIND_STRIDER))
		{
			Functions.show("scripts/services/petevolve/error_2.htm", player, npc);
			return;
		}
		if(pet.getLevel() < 55)
		{
			Functions.show("scripts/services/petevolve/error_lvl_strider.htm", player, npc);
			return;
		}

		collar.setItemId(fwd ? RED_WS_ITEM : WIND_STRIDER_ITEM);
		collar.setJdbcState(JdbcEntityState.UPDATED);
		collar.update();
		player.sendItemList(false);
		player.sendPacket(SystemMessagePacket.obtainItems((fwd ? RED_WS_ITEM : WIND_STRIDER_ITEM), 1, 0));
		Functions.show("scripts/services/petevolve/end_msg_strider.htm", player, npc);
	}

	@Bypass("services.petevolve.clanhall:sstrider")
	public void sstrider(Player player, NpcInstance npc, String[] param)
	{
		boolean fwd = Integer.parseInt(param[0]) == 1;

		if(player.getInventory().getCountOf(fwd ? STAR_STRIDER_ITEM : RED_SS_ITEM) > 1)
		{
			Functions.show("scripts/services/petevolve/error_3.htm", player, npc);
			return;
		}
		if(player.getServitors().length > 0)
		{
			Functions.show("scripts/services/petevolve/error_4.htm", player, npc);
			return;
		}
		ItemInstance collar = player.getInventory().getItemByItemId(fwd ? STAR_STRIDER_ITEM : RED_SS_ITEM);
		if(collar == null)
		{
			Functions.show("scripts/services/petevolve/no_item.htm", player, npc);
			return;
		}

		PetData petDataTemplate = PetDataHolder.getInstance().getTemplateByItemId(collar.getItemId());
		if(petDataTemplate == null)
		{
			return;
		}

		NpcTemplate petTemplate = NpcHolder.getInstance().getTemplate(petDataTemplate.getNpcId());
		if(petTemplate == null)
		{
			return;
		}

		PetInstance pet = PetInstance.restore(collar, petTemplate, player);

		if(petDataTemplate.getNpcId() != (fwd ? STAR_STRIDER : RED_STAR_STRIDER))
		{
			Functions.show("scripts/services/petevolve/error_2.htm", player, npc);
			return;
		}
		if(pet.getLevel() < 55)
		{
			Functions.show("scripts/services/petevolve/error_lvl_strider.htm", player, npc);
			return;
		}

		collar.setItemId(fwd ? RED_SS_ITEM : STAR_STRIDER_ITEM);
		collar.setJdbcState(JdbcEntityState.UPDATED);
		collar.update();
		player.sendItemList(false);
		player.sendPacket(SystemMessagePacket.obtainItems((fwd ? RED_SS_ITEM : STAR_STRIDER_ITEM), 1, 0));
		Functions.show("scripts/services/petevolve/end_msg_strider.htm", player, npc);
	}

	@Bypass("services.petevolve.clanhall:tstrider")
	public void tstrider(Player player, NpcInstance npc, String[] param)
	{
		boolean fwd = Integer.parseInt(param[0]) == 1;

		if(player.getInventory().getCountOf(fwd ? TWILING_STRIDER_ITEM : RED_TW_ITEM) > 1)
		{
			Functions.show("scripts/services/petevolve/error_3.htm", player, npc);
			return;
		}
		if(player.getServitors().length > 0)
		{
			Functions.show("scripts/services/petevolve/error_4.htm", player, npc);
			return;
		}
		ItemInstance collar = player.getInventory().getItemByItemId(fwd ? TWILING_STRIDER_ITEM : RED_TW_ITEM);
		if(collar == null)
		{
			Functions.show("scripts/services/petevolve/no_item.htm", player, npc);
			return;
		}

		PetData petDataTemplate = PetDataHolder.getInstance().getTemplateByItemId(collar.getItemId());
		if(petDataTemplate == null)
		{
			return;
		}

		NpcTemplate petTemplate = NpcHolder.getInstance().getTemplate(petDataTemplate.getNpcId());
		if(petTemplate == null)
		{
			return;
		}

		PetInstance pet = PetInstance.restore(collar, petTemplate, player);

		if(petDataTemplate.getNpcId() != (fwd ? TWILING_STRIDER : RED_TWILING_STRIDER))
		{
			Functions.show("scripts/services/petevolve/error_2.htm", player, npc);
			return;
		}
		if(pet.getLevel() < 55)
		{
			Functions.show("scripts/services/petevolve/error_lvl_strider.htm", player, npc);
			return;
		}

		collar.setItemId(fwd ? RED_TW_ITEM : TWILING_STRIDER_ITEM);
		collar.setJdbcState(JdbcEntityState.UPDATED);
		collar.update();
		player.sendItemList(false);
		player.sendPacket(SystemMessagePacket.obtainItems((fwd ? RED_TW_ITEM : TWILING_STRIDER_ITEM), 1, 0));
		Functions.show("scripts/services/petevolve/end_msg_strider.htm", player, npc);
	}
}