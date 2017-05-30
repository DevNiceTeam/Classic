package npc.model.residences.castle;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.dao.CastleDamageZoneDAO;
import studio.lineage2.gameserver.dao.CastleDoorUpgradeDAO;
import studio.lineage2.gameserver.data.xml.holder.ResidenceHolder;
import studio.lineage2.gameserver.instancemanager.CastleManorManager;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.base.PledgeRank;
import studio.lineage2.gameserver.model.entity.events.impl.CastleSiegeEvent;
import studio.lineage2.gameserver.model.entity.events.impl.SiegeEvent;
import studio.lineage2.gameserver.model.entity.events.objects.CastleDamageZoneObject;
import studio.lineage2.gameserver.model.entity.events.objects.DoorObject;
import studio.lineage2.gameserver.model.entity.residence.Castle;
import studio.lineage2.gameserver.model.entity.residence.Fortress;
import studio.lineage2.gameserver.model.entity.residence.Residence;
import studio.lineage2.gameserver.model.entity.residence.ResidenceSide;
import studio.lineage2.gameserver.model.instances.DoorInstance;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.model.pledge.Privilege;
import studio.lineage2.gameserver.network.l2.c2s.L2GameClientPacket;
import studio.lineage2.gameserver.network.l2.c2s.RequestSetCrop;
import studio.lineage2.gameserver.network.l2.c2s.RequestSetSeed;
import studio.lineage2.gameserver.network.l2.components.CustomMessage;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.network.l2.s2c.CastleSiegeInfoPacket;
import studio.lineage2.gameserver.network.l2.s2c.ExShowCropInfoPacket;
import studio.lineage2.gameserver.network.l2.s2c.ExShowCropSettingPacket;
import studio.lineage2.gameserver.network.l2.s2c.ExShowManorDefaultInfoPacket;
import studio.lineage2.gameserver.network.l2.s2c.ExShowSeedInfoPacket;
import studio.lineage2.gameserver.network.l2.s2c.ExShowSeedSetting;
import studio.lineage2.gameserver.network.l2.s2c.L2GameServerPacket;
import studio.lineage2.gameserver.templates.item.ItemTemplate;
import studio.lineage2.gameserver.templates.manor.CropProcure;
import studio.lineage2.gameserver.templates.manor.SeedProduction;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.HtmlUtils;
import studio.lineage2.gameserver.utils.ItemFunctions;
import studio.lineage2.gameserver.utils.Log;
import studio.lineage2.gameserver.utils.ReflectionUtils;
import npc.model.residences.ResidenceManager;
import org.napile.pair.primitive.IntObjectPair;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class ChamberlainInstance extends ResidenceManager
{
	private static final long serialVersionUID = 1L;

	// Плащи
	private static final int CloakLight = 34925;
	private static final int CloakDark = 34926;
	private static final int CloakLightLeader = 34996;
	private static final int CloakDarkLeader = 34997;

	public ChamberlainInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	protected void setDialogs()
	{
		_mainDialog = "castle/chamberlain/chamberlain.htm";
		_failDialog = "castle/chamberlain/chamberlain-notlord.htm";
		_siegeDialog = _mainDialog;
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		int condition = getCond(player);
		if(condition != COND_OWNER)
		{
			return;
		}

		StringTokenizer st = new StringTokenizer(command, " ");
		String actualCommand = st.nextToken();
		String val = "";
		if(st.countTokens() >= 1)
		{
			val = st.nextToken();
		}

		Castle castle = getCastle();
		if(actualCommand.equalsIgnoreCase("viewSiegeInfo"))
		{
			if(!isHaveRigths(player, Clan.CP_CS_MANAGE_SIEGE))
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			player.sendPacket(new CastleSiegeInfoPacket(castle, player));
		}
		else if(actualCommand.equalsIgnoreCase("ManageTreasure"))
		{
			if(!player.isClanLeader())
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/chamberlain-castlevault.htm");
			html.replace("%Treasure%", String.valueOf(castle.getTreasury()));
			html.replace("%CollectedShops%", String.valueOf(castle.getCollectedShops()));
			html.replace("%CollectedSeed%", String.valueOf(castle.getCollectedSeed()));
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("TakeTreasure"))
		{
			if(!player.isClanLeader())
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			if(!val.equals(""))
			{
				long treasure = Long.parseLong(val);
				if(castle.getTreasury() < treasure)
				{
					HtmlMessage html = new HtmlMessage(this);
					html.setFile("castle/chamberlain/chamberlain-havenottreasure.htm");
					html.replace("%Treasure%", String.valueOf(castle.getTreasury()));
					html.replace("%Requested%", String.valueOf(treasure));
					player.sendPacket(html);
					return;
				}
				if(treasure > 0)
				{
					castle.addToTreasuryNoTax(-treasure, false, false);
					Log.add(castle.getName() + "|" + -treasure + "|CastleChamberlain", "treasury");
					player.addAdena(treasure);
				}
			}

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/chamberlain-castlevault.htm");
			html.replace("%Treasure%", String.valueOf(castle.getTreasury()));
			html.replace("%CollectedShops%", String.valueOf(castle.getCollectedShops()));
			html.replace("%CollectedSeed%", String.valueOf(castle.getCollectedSeed()));
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("PutTreasure"))
		{
			if(!val.equals(""))
			{
				long treasure = Long.parseLong(val);
				if(treasure > player.getAdena())
				{
					player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
					return;
				}
				if(treasure > 0)
				{
					castle.addToTreasuryNoTax(treasure, false, false);
					Log.add(castle.getName() + "|" + treasure + "|CastleChamberlain", "treasury");
					player.reduceAdena(treasure, true);
				}
			}

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/chamberlain-castlevault.htm");
			html.replace("%Treasure%", String.valueOf(castle.getTreasury()));
			html.replace("%CollectedShops%", String.valueOf(castle.getCollectedShops()));
			html.replace("%CollectedSeed%", String.valueOf(castle.getCollectedSeed()));
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("manor"))
		{
			if(!isHaveRigths(player, Clan.CP_CS_MANOR_ADMIN))
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			String filename = "";
			if(CastleManorManager.getInstance().isDisabled())
			{
				filename = "npcdefault.htm";
			}
			else
			{
				int cmd = Integer.parseInt(val);
				switch(cmd)
				{
					case 0:
						filename = "castle/chamberlain/manor/manor.htm";
						break;
					// TODO: correct in html's to 1
					case 4:
						filename = "castle/chamberlain/manor/manor_help00" + st.nextToken() + ".htm";
						break;
					default:
						filename = "castle/chamberlain/chamberlain-no.htm";
						break;
				}
			}

			if(filename.length() > 0)
			{
				HtmlMessage html = new HtmlMessage(this);
				html.setFile(filename);
				player.sendPacket(html);
			}
		}
		else if(actualCommand.startsWith("manor_menu_select"))
		{
			if(!isHaveRigths(player, Clan.CP_CS_MANOR_ADMIN))
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			// input string format:
			// manor_menu_select?ask=X&state=Y&time=X
			if(CastleManorManager.getInstance().isUnderMaintenance())
			{
				player.sendPacket(SystemMsg.THE_MANOR_SYSTEM_IS_CURRENTLY_UNDER_MAINTENANCE);
				player.sendActionFailed();
				return;
			}

			String params = actualCommand.substring(actualCommand.indexOf("?") + 1);
			StringTokenizer str = new StringTokenizer(params, "&");
			int ask = Integer.parseInt(str.nextToken().split("=")[1]);
			int state = Integer.parseInt(str.nextToken().split("=")[1]);
			int time = Integer.parseInt(str.nextToken().split("=")[1]);

			int castleId;
			if(state == -1) // info for current manor
			{
				castleId = castle.getId();
			}
			else
			// info for requested manor
			{
				castleId = state;
			}

			switch(ask)
			{ // Main action
				case 3: // Current seeds (Manor info)
					if(time == 1 && !ResidenceHolder.getInstance().getResidence(Castle.class, castleId).isNextPeriodApproved())
					{
						player.sendPacket(new ExShowSeedInfoPacket(castleId, Collections.<SeedProduction>emptyList()));
					}
					else
					{
						player.sendPacket(new ExShowSeedInfoPacket(castleId, ResidenceHolder.getInstance().getResidence(Castle.class, castleId).getSeedProduction(time)));
					}
					break;
				case 4: // Current crops (Manor info)
					if(time == 1 && !ResidenceHolder.getInstance().getResidence(Castle.class, castleId).isNextPeriodApproved())
					{
						player.sendPacket(new ExShowCropInfoPacket(castleId, Collections.<CropProcure>emptyList()));
					}
					else
					{
						player.sendPacket(new ExShowCropInfoPacket(castleId, ResidenceHolder.getInstance().getResidence(Castle.class, castleId).getCropProcure(time)));
					}
					break;
				case 5: // Basic info (Manor info)
					player.sendPacket(new ExShowManorDefaultInfoPacket());
					break;
				case 7: // Edit seed setup
					if(castle.isNextPeriodApproved())
					{
						player.sendPacket(SystemMsg.A_MANOR_CANNOT_BE_SET_UP_BETWEEN_430_AM_AND_8_PM);
					}
					else
					{
						player.sendPacket(new ExShowSeedSetting(castle.getId()));
					}
					break;
				case 8: // Edit crop setup
					if(castle.isNextPeriodApproved())
					{
						player.sendPacket(SystemMsg.A_MANOR_CANNOT_BE_SET_UP_BETWEEN_430_AM_AND_8_PM);
					}
					else
					{
						player.sendPacket(new ExShowCropSettingPacket(castle.getId()));
					}
					break;
			}
		}
		else if(actualCommand.equalsIgnoreCase("operate_door")) // door control
		{
			if(!isHaveRigths(player, Clan.CP_CS_ENTRY_EXIT))
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			if(castle.getSiegeEvent().isInProgress())
			{
				showChatWindow(player, "residence2/castle/chamberlain_saius021.htm", false);
				return;
			}
			if(!val.equals(""))
			{
				boolean open = Integer.parseInt(val) == 1;
				while(st.hasMoreTokens())
				{
					DoorInstance door = ReflectionUtils.getDoor(Integer.parseInt(st.nextToken()));
					if(open)
					{
						door.openMe(player, true);
					}
					else
					{
						door.closeMe(player, true);
					}
				}
			}

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/" + getNpcId() + "-d.htm");
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("tax_set")) // tax rates control
		{
			if(!isHaveRigths(player, Clan.CP_CS_TAXES))
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			if(!val.equals(""))
			{
				// По умолчанию налог не более 30%
				int maxTax = 30;

				int tax = Integer.parseInt(val);
				if(tax < 0 || tax > maxTax)
				{
					HtmlMessage html = new HtmlMessage(this);
					html.setFile("castle/chamberlain/chamberlain-hightax.htm");
					html.replace("%CurrentTax%", String.valueOf(castle.getTaxPercent()));
					player.sendPacket(html);
					return;
				}
				castle.setTaxPercent(player, tax);
			}

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/chamberlain-settax.htm");
			html.replace("%CurrentTax%", String.valueOf(castle.getTaxPercent()));
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("upgrade_castle"))
		{
			if(!checkSiegeFunctions(player))
			{
				return;
			}

			showChatWindow(player, "castle/chamberlain/chamberlain-upgrades.htm", false);
		}
		else if(actualCommand.equalsIgnoreCase("reinforce"))
		{
			if(!checkSiegeFunctions(player))
			{
				return;
			}

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/doorStrengthen-" + castle.getName() + ".htm");
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("trap_select"))
		{
			if(!checkSiegeFunctions(player))
			{
				return;
			}

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/trap_select-" + castle.getName() + ".htm");
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("buy_trap"))
		{
			if(!checkSiegeFunctions(player))
			{
				return;
			}

			if(castle.getSiegeEvent().getObjects(CastleSiegeEvent.BOUGHT_ZONES).contains(val))
			{
				HtmlMessage html = new HtmlMessage(this);
				html.setFile("castle/chamberlain/trapAlready.htm");
				player.sendPacket(html);
				return;
			}

			List<CastleDamageZoneObject> objects = castle.getSiegeEvent().getObjects(val);
			long price = 0;
			for(CastleDamageZoneObject o : objects)
			{
				price += o.getPrice();
			}

			if(player.getClan().getAdenaCount() < price)
			{
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				return;
			}

			player.getClan().getWarehouse().destroyItemByItemId(ItemTemplate.ITEM_ID_ADENA, price);
			castle.getSiegeEvent().addObject(CastleSiegeEvent.BOUGHT_ZONES, val);
			CastleDamageZoneDAO.getInstance().insert(castle, val);

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/trapSuccess.htm");
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("door_manage"))
		{
			if(!isHaveRigths(player, Clan.CP_CS_ENTRY_EXIT))
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			if(castle.getSiegeEvent().isInProgress())
			{
				showChatWindow(player, "residence2/castle/chamberlain_saius021.htm", false);
				return;
			}

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/doorManage.htm");
			html.replace("%id%", val);
			html.replace("%type%", st.nextToken());
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("upgrade_door_confirm"))
		{
			if(!isHaveRigths(player, Clan.CP_CS_MANAGE_SIEGE))
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			int id = Integer.parseInt(val);
			int type = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			long price = getDoorCost(type, level);

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/doorConfirm.htm");
			html.replace("%id%", String.valueOf(id));
			html.replace("%level%", String.valueOf(level));
			html.replace("%type%", String.valueOf(type));
			html.replace("%price%", String.valueOf(price));
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("upgrade_door"))
		{
			if(checkSiegeFunctions(player))
			{
				return;
			}

			int id = Integer.parseInt(val);
			int type = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			long price = getDoorCost(type, level);

			List<DoorObject> doorObjects = castle.getSiegeEvent().getObjects(SiegeEvent.DOORS);
			DoorObject targetDoorObject = null;
			for(DoorObject o : doorObjects)
			{
				if(o.getUId() == id)
				{
					targetDoorObject = o;
					break;
				}
			}

			DoorInstance door = targetDoorObject.getDoor();
			int upgradeHp = (door.getMaxHp() - door.getUpgradeHp()) * level - door.getMaxHp();

			if(price == 0 || upgradeHp < 0)
			{
				player.sendMessage(new CustomMessage("common.Error"));
				return;
			}

			if(door.getUpgradeHp() >= upgradeHp)
			{
				int oldLevel = door.getUpgradeHp() / (door.getMaxHp() - door.getUpgradeHp()) + 1;
				HtmlMessage html = new HtmlMessage(this);
				html.setFile("castle/chamberlain/doorAlready.htm");
				html.replace("%level%", String.valueOf(oldLevel));
				player.sendPacket(html);
				return;
			}

			if(player.getClan().getAdenaCount() < price)
			{
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				return;
			}

			player.getClan().getWarehouse().destroyItemByItemId(ItemTemplate.ITEM_ID_ADENA, price);

			targetDoorObject.setUpgradeValue(castle.<SiegeEvent>getSiegeEvent(), upgradeHp);
			CastleDoorUpgradeDAO.getInstance().insert(door.getDoorId(), upgradeHp);
		}
		else if(actualCommand.equalsIgnoreCase("report")) // Report page
		{
			if(!isHaveRigths(player, Clan.CP_CS_USE_FUNCTIONS))
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			NpcString ssq_period = NpcString.PREPARATION;

			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/chamberlain-report.htm");
			html.replace("%FeudName%", castle.getNpcStringName());
			html.replace("%CharClan%", player.getClan().getName());
			html.replace("%CharName%", player.getName());
			html.replace("%SSPeriod%", ssq_period);
			html.replace("%Avarice%", getSealOwner(1));
			html.replace("%Revelation%", getSealOwner(2));
			html.replace("%Strife%", getSealOwner(3));
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("fortressStatus"))
		{
			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/chamberlain-fortress-status.htm");
			StringBuilder b = new StringBuilder(100);

			for(IntObjectPair<List<Fortress>> entry : castle.getRelatedFortresses().entrySet())
			{
				NpcString type;
				switch(entry.getKey())
				{
					case Fortress.DOMAIN:
						type = NpcString.DOMAIN_FORTRESS;
						break;
					case Fortress.BOUNDARY:
						type = NpcString.BOUNDARY_FORTRESS;
						break;
					default:
						continue;
				}
				List<Fortress> fortresses = entry.getValue();
				for(Fortress fort : fortresses)
				{
					b.append(HtmlUtils.htmlResidenceName(fort.getId())).append(" (").append(HtmlUtils.htmlNpcString(type)).append(") : <font color=\"00FFFF\">");

					NpcString contractType;
					switch(fort.getContractState())
					{
						case Fortress.NOT_DECIDED:
							contractType = NpcString.NONPARTISAN;
							break;
						case Fortress.INDEPENDENT:
							contractType = NpcString.INDEPENDENT_STATE;
							break;
						case Fortress.CONTRACT_WITH_CASTLE:
							contractType = NpcString.CONTRACT_STATE;
							break;
						default:
							continue;
					}
					b.append(HtmlUtils.htmlNpcString(contractType)).append("</font> <br>");
				}
			}
			html.replace("%list%", b.toString());
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("Crown")) // Give Crown to Castle Owner
		{
			if(!player.isClanLeader())
			{
				player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}
			if(player.getInventory().getItemByItemId(6841) == null)
			{
				ItemFunctions.addItem(player, 6841, 1, true);

				HtmlMessage html = new HtmlMessage(this);
				html.setFile("castle/chamberlain/chamberlain-givecrown.htm");
				html.replace("%CharName%", player.getName());
				html.replace("%FeudName%", castle.getNpcStringName());
				player.sendPacket(html);
			}
			else
			{
				HtmlMessage html = new HtmlMessage(this);
				html.setFile("castle/chamberlain/alreadyhavecrown.htm");
				player.sendPacket(html);
			}
		}
		else if(actualCommand.equalsIgnoreCase("Cloak"))
		{
			if(player.isClanLeader())
			{
				int itemId = getCastle().getResidenceSide() == ResidenceSide.LIGHT ? CloakLightLeader : CloakDarkLeader;
				if(player.getInventory().getItemByItemId(itemId) != null)
				{
					showChatWindow(player, "castle/chamberlain/alreadyhavecloak.htm", false);
					return;
				}
				else
				{
					ItemFunctions.addItem(player, itemId, 1, true);
					showChatWindow(player, "castle/chamberlain/chamberlain-givecloak.htm", false);
					return;
				}
			}
			else
			{
				int itemId = getCastle().getResidenceSide() == ResidenceSide.LIGHT ? CloakLight : CloakDark;

				// Плащ может получить только маркиз и выше.
				if(player.getInventory().getItemByItemId(itemId) != null || player.getPledgeRank().ordinal() < PledgeRank.MARQUIS.ordinal())
				{
					showChatWindow(player, "castle/chamberlain/alreadyhavecloak.htm", false);
					return;
				}
				else
				{
					ItemFunctions.addItem(player, itemId, 1, true);
					showChatWindow(player, "castle/chamberlain/chamberlain-givecloak.htm", false);
					return;
				}
			}
		}
		else if(actualCommand.equalsIgnoreCase("manageFunctions"))
		{
			if(!player.hasPrivilege(Privilege.CS_FS_SET_FUNCTIONS))
			{
				showChatWindow(player, "residence2/castle/chamberlain_saius063.htm", false);
			}
			else
			{
				showChatWindow(player, "residence2/castle/chamberlain_saius065.htm", false);
			}
		}
		else if(actualCommand.equalsIgnoreCase("manageSiegeFunctions"))
		{
			if(!player.hasPrivilege(Privilege.CS_FS_SET_FUNCTIONS))
			{
				showChatWindow(player, "residence2/castle/chamberlain_saius063.htm", false);
			}
			else if(castle.getDomainFortressContract() == 0)
			{
				showChatWindow(player, "residence2/castle/chamberlain_saius069.htm", false);
			}
			else
			{
				showChatWindow(player, "residence2/castle/chamberlain_saius052.htm", false);
			}
		}
		else if(actualCommand.equalsIgnoreCase("items"))
		{
			HtmlMessage html = new HtmlMessage(this);
			html.setFile("residence2/castle/chamberlain_saius064.htm");
			html.replace("%npcId%", String.valueOf(getNpcId()));
			player.sendPacket(html);
		}
		else if(actualCommand.equalsIgnoreCase("default"))
		{
			HtmlMessage html = new HtmlMessage(this);
			html.setFile("castle/chamberlain/chamberlain.htm");
			player.sendPacket(html);
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}

	@Override
	protected int getCond(Player player)
	{
		if(player.isGM())
		{
			return COND_OWNER;
		}
		Residence castle = getCastle();
		if(castle != null && castle.getId() > 0)
		{
			if(player.getClan() != null)
			{
				if(castle.getSiegeEvent().isInProgress())
				{
					return COND_SIEGE; // Busy because of siege
				}
				else if(castle.getOwnerId() == player.getClanId())
				{
					if(player.isClanLeader()) // Leader of clan
					{
						return COND_OWNER;
					}
					if(isHaveRigths(player, Clan.CP_CS_ENTRY_EXIT) || // doors
							isHaveRigths(player, Clan.CP_CS_MANOR_ADMIN) || // manor
							isHaveRigths(player, Clan.CP_CS_MANAGE_SIEGE) || // siege
							isHaveRigths(player, Clan.CP_CS_USE_FUNCTIONS) || // funcs
							isHaveRigths(player, Clan.CP_CS_DISMISS) || // banish
							isHaveRigths(player, Clan.CP_CS_TAXES) || // tax
							isHaveRigths(player, Clan.CP_CS_MERCENARIES) || // merc
							isHaveRigths(player, Clan.CP_CS_SET_FUNCTIONS) //funcs
							)
					{
						return COND_OWNER; // Есть какие либо замковые привилегии
					}
				}
			}
		}

		return COND_FAIL;
	}

	private NpcString getSealOwner(int seal)
	{
		//TODO: [Bonux] пересмотреть
		return NpcString.NO_OWNER;
	}

	private long getDoorCost(int type, int level)
	{
		int price = 0;

		switch(type)
		{
			case 1: // Главные ворота
				switch(level)
				{
					case 2:
						price = 3000000;
						break;
					case 3:
						price = 4000000;
						break;
					case 5:
						price = 5000000;
						break;
				}
				break;
			case 2: // Внутренние ворота
				switch(level)
				{
					case 2:
						price = 750000;
						break;
					case 3:
						price = 900000;
						break;
					case 5:
						price = 1000000;
						break;
				}
				break;
			case 3: // Стены
				switch(level)
				{
					case 2:
						price = 1600000;
						break;
					case 3:
						price = 1800000;
						break;
					case 5:
						price = 2000000;
						break;
				}
				break;
		}

		return price;
	}

	@Override
	protected Residence getResidence()
	{
		return getCastle();
	}

	@Override
	public L2GameServerPacket decoPacket()
	{
		return null;
	}

	@Override
	protected int getPrivUseFunctions()
	{
		return Clan.CP_CS_USE_FUNCTIONS;
	}

	@Override
	protected int getPrivSetFunctions()
	{
		return Clan.CP_CS_SET_FUNCTIONS;
	}

	@Override
	protected int getPrivDismiss()
	{
		return Clan.CP_CS_DISMISS;
	}

	@Override
	protected int getPrivDoors()
	{
		return Clan.CP_CS_ENTRY_EXIT;
	}

	private boolean checkSiegeFunctions(Player player)
	{
		Castle castle = getCastle();
		if(!player.hasPrivilege(Privilege.CS_FS_SIEGE_WAR))
		{
			player.sendPacket(SystemMsg.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
			return false;
		}

		if(castle.getSiegeEvent().isInProgress())
		{
			showChatWindow(player, "residence2/castle/chamberlain_saius021.htm", false);
			return false;
		}
		return true;
	}

	@Override
	public boolean canPassPacket(Player player, Class<? extends L2GameClientPacket> packet, Object... arg)
	{
		return packet == RequestSetSeed.class || packet == RequestSetCrop.class || super.canPassPacket(player, packet, arg);
	}
}