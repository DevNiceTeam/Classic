package handler.bbs.custom;

import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

import l2s.commons.map.hash.TIntStringHashMap;
import l2s.gameserver.Config;
import l2s.gameserver.dao.AccountBonusDAO;
import l2s.gameserver.dao.CharacterDAO;
import l2s.gameserver.dao.PremiumAccountRatesHolder;
import l2s.gameserver.dao.PremiumAccountRatesHolder.PremiumInfo;
import l2s.gameserver.data.htm.HtmCache;
import l2s.gameserver.data.xml.holder.FakePlayersHolder;
import l2s.gameserver.handler.bbs.BbsHandlerHolder;
import l2s.gameserver.handler.bbs.IBbsHandler;
import l2s.gameserver.handler.voicecommands.IVoicedCommandHandler;
import l2s.gameserver.handler.voicecommands.VoicedCommandHandler;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.instances.PetInstance;
import l2s.gameserver.model.actor.instances.player.Bonus;
import l2s.gameserver.model.pledge.Clan;
import l2s.gameserver.network.authcomm.AuthServerCommunication;
import l2s.gameserver.network.authcomm.gs2as.BonusRequest;
import l2s.gameserver.network.l2.components.SystemMsg;
import l2s.gameserver.network.l2.s2c.ExBR_PremiumStatePacket;
import l2s.gameserver.network.l2.s2c.ExBuySellListPacket;
import l2s.gameserver.network.l2.s2c.MagicSkillUse;
import l2s.gameserver.network.l2.s2c.ShowBoardPacket;
import l2s.gameserver.tables.ClanTable;
import l2s.gameserver.utils.HtmlUtils;
import l2s.gameserver.utils.ItemFunctions;
import l2s.gameserver.utils.Log;
import l2s.gameserver.utils.WarehouseFunctions;
import l2s.gameserver.utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import handler.bbs.ScriptsCommunityHandler;

/**
 * @author Bonux
**/
public class CommunityServices extends ScriptsCommunityHandler
{
	private static final Logger _log = LoggerFactory.getLogger(CommunityServices.class);

	@Override
	public String[] getBypassCommands()
	{
		return new String[]
		{ "_cbbsservices" };
	}

	@Override
	protected void doBypassCommand(Player player, String bypass)
	{
		StringTokenizer st = new StringTokenizer(bypass, "_");
		String cmd = st.nextToken();
		String html = "";

		if("cbbsservices".equals(cmd))
		{
			if(!st.hasMoreTokens())
			{
				player.sendMessage(player.isLangRus() ? "Данная функция еще не реализована." : "This feature is not yet implemented.");
				player.sendPacket(ShowBoardPacket.CLOSE);
				return;
			}

			String cmd2 = st.nextToken();
			if("sell".equals(cmd2))
			{
				if(!Config.BBS_SELL_ITEMS_ENABLED)
				{
					player.sendMessage(player.isLangRus() ? "Данный сервис отключен." : "This service disallowed.");
					player.sendPacket(ShowBoardPacket.CLOSE);
					return;
				}

				if(!BBSConfig.GLOBAL_USE_FUNCTIONS_CONFIGS && !checkUseCondition(player))
				{
					onWrongCondition(player);
					return;
				}

				IBbsHandler handler = BbsHandlerHolder.getInstance().getCommunityHandler("_bbspage:shop");
				if(handler != null)
					handler.onBypassCommand(player, "_bbspage:shop");

				player.sendPacket(new ExBuySellListPacket.BuyList(null, player, 0), new ExBuySellListPacket.SellRefundList(player, false));
				return;
			}
			else if("warehouse".equals(cmd2))
			{
				if(!st.hasMoreTokens())
					return;

				if(!Config.BBS_WAREHOUSE_ENABLED)
				{
					player.sendMessage(player.isLangRus() ? "Данный сервис отключен." : "This service disallowed.");
					player.sendPacket(ShowBoardPacket.CLOSE);
					return;
				}

				if(!BBSConfig.GLOBAL_USE_FUNCTIONS_CONFIGS && !checkUseCondition(player))
				{
					onWrongCondition(player);
					return;
				}

				IBbsHandler handler = BbsHandlerHolder.getInstance().getCommunityHandler("_bbspage:warehouse");
				if(handler != null)
					handler.onBypassCommand(player, "_bbspage:warehouse");

				String cmd3 = st.nextToken();
				if("withdraw".equals(cmd3))
				{
					if(!st.hasMoreTokens())
						return;

					String cmd4 = st.nextToken();
					if("personal".equals(cmd4))
						WarehouseFunctions.showRetrieveWindow(player);
					else if("clan".equals(cmd4))
						WarehouseFunctions.showWithdrawWindowClan(player);
				}
				else if("deposit".equals(cmd3))
				{
					if(!st.hasMoreTokens())
						return;

					String cmd4 = st.nextToken();
					if("personal".equals(cmd4))
						WarehouseFunctions.showDepositWindow(player);
					else if("clan".equals(cmd4))
						WarehouseFunctions.showDepositWindowClan(player);
				}
				return;
			}
			else if("cabinet".equals(cmd2))
			{
				if(st.hasMoreTokens())
				{
					onBypassCommand(player, "_cbbsservices_cabinet");

					String cmd3 = st.nextToken();
					if("repair".equals(cmd3))
					{
						player.sendMessage(player.isLangRus() ? "Для восстановления персонажа используйте команду .repair [имя персонажа]" : "To restore the character, use the command .repair [character name]");
						return;
					}
					else if("config".equals(cmd3))
					{
						IVoicedCommandHandler vch = VoicedCommandHandler.getInstance().getVoicedCommandHandler("cfg");
						if(vch != null)
							vch.useVoicedCommand("cfg", player, "");
						return;
					}
					else if("stats".equals(cmd3))
					{
						IVoicedCommandHandler vch = VoicedCommandHandler.getInstance().getVoicedCommandHandler("whoiam");
						if(vch != null)
							vch.useVoicedCommand("whoiam", player, "");
						return;
					}
				}
				else
				{
					html = HtmCache.getInstance().getHtml("scripts/handler/bbs/pages/cabinet.htm", player);
					html = html.replace("<?account?>", player.getAccountName());
					html = html.replace("<?ip?>", player.getIP());
					html = html.replace("<?type?>", player.hasBonus() ? (player.isLangRus() ? "премиум" : "premium") : (player.isLangRus() ? "обычный" : "common"));
					html = html.replace("<?char_name?>", player.getName());
					html = html.replace("<?clan_name?>", player.getClan() != null ? player.getClan().getName() : (player.isLangRus() ? "нету" : "none"));
					html = html.replace("<?ally_name?>", player.getAlliance() != null ? player.getAlliance().getAllyName() : (player.isLangRus() ? "нету" : "none"));

					int playedTime = (int) player.getOnlineTime() / 60;
					int minutes = playedTime % 60;
					int hours = ((playedTime - minutes) / 60) % 24;
					int days = (((playedTime - minutes) / 60) - hours) / 24;
					html = html.replace("<?played_day?>", String.valueOf(days));
					html = html.replace("<?played_hour?>", String.valueOf(hours));
					html = html.replace("<?played_minute?>", String.valueOf(minutes));

					html = html.replace("<?rate_xp?>", doubleToString(player.getRateExp()));
					html = html.replace("<?rate_sp?>", doubleToString(player.getRateSp()));
					html = html.replace("<?rate_adena?>", doubleToString(player.getRateAdena()));
					html = html.replace("<?rate_drop?>", doubleToString(player.getRateItems()));
					html = html.replace("<?rate_spoil?>", doubleToString(player.getRateSpoil()));
					html = html.replace("<?rate_quest_reward?>", doubleToString(player.getRateQuestsReward()));
					html = html.replace("<?rate_quest_drop?>", doubleToString(player.getRateQuestsDrop()));
				}
			}
			else if("pa".equals(cmd2))
			{
				TIntStringHashMap tpls = HtmCache.getInstance().getTemplates("scripts/handler/bbs/pages/pa.htm", player);
				html = tpls.get(0);

				StringBuilder content = new StringBuilder();

				final CopyOnWriteArrayList<PremiumInfo> allAquisions = PremiumAccountRatesHolder.getAllAquisions();
				if(!allAquisions.isEmpty())
				{
					if(st.hasMoreTokens())
					{
						String cmd3 = st.nextToken();
						if("info".equals(cmd3))
						{
							if(!st.hasMoreTokens())
								return;

							final int schemeId = Integer.parseInt(st.nextToken());
							final PremiumInfo info = PremiumAccountRatesHolder.getPremiumByGroupId(schemeId);
							if(info == null)
							{
								_log.warn(getClass().getSimpleName() + ": Error while open info about premium account scheme ID[" + schemeId + "]! Scheme is null.");
								return;
							}

							String infoBlock = tpls.get(6);
							infoBlock = infoBlock.replace("<?scheme_id?>", String.valueOf(info.getGroupNumber()));
							infoBlock = infoBlock.replace("<?scheme_name?>", player.isLangRus() ? info.getGroupNameRu() : info.getGroupNameEn());
							infoBlock = infoBlock.replace("<?period?>", info.getDays() + " " + (info.isHours() ? player.isLangRus() ? "часа(ов)" : "hour(s)" : player.isLangRus() ? "дня(ей)" : "day(s)"));
							infoBlock = infoBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(info.getItemId()));
							infoBlock = infoBlock.replace("<?fee_item_count?>", Util.formatAdena(info.getItemCount()));
							infoBlock = infoBlock.replace("<?exp_rate?>", doubleToString(info.getExp()));
							infoBlock = infoBlock.replace("<?sp_rate?>", doubleToString(info.getSp()));
							infoBlock = infoBlock.replace("<?adena_drop_rate?>", doubleToString(info.getAdena()));
							infoBlock = infoBlock.replace("<?items_drop_rate?>", doubleToString(info.getDrop()));
							infoBlock = infoBlock.replace("<?spoil_rate?>", doubleToString(info.getSpoil()));
							infoBlock = infoBlock.replace("<?quest_drop_rate?>", doubleToString(info.getQDrop()));
							infoBlock = infoBlock.replace("<?quest_reward_rate?>", doubleToString(info.getQReward()));
							infoBlock = infoBlock.replace("<?enchant_chance?>", doubleToString(info.getEnchantAdd()));

							content.append(infoBlock);
						}
						else if("buy".equals(cmd3))
						{
							if(!BBSConfig.GLOBAL_USE_FUNCTIONS_CONFIGS && !checkUseCondition(player))
							{
								onWrongCondition(player);
								return;
							}

							if(Config.SERVICES_RATE_TYPE == Bonus.NO_BONUS)
								content.append(tpls.get(1));
							else if(Config.SERVICES_RATE_TYPE == Bonus.BONUS_GLOBAL_ON_AUTHSERVER && AuthServerCommunication.getInstance().isShutdown())
								content.append(tpls.get(4));
							else if(player.getBonus().getBonusExpire() > 0)
							{
								String expireBlock = tpls.get(5);
								expireBlock = expireBlock.replace("<?date_expire?>", String.valueOf(new Date(player.getBonus().getBonusExpire() * 1000L)));
								content.append(expireBlock);
							}
							else
							{
								if(!st.hasMoreTokens())
									return;

								final int schemeId = Integer.parseInt(st.nextToken());
								final PremiumInfo info = PremiumAccountRatesHolder.getPremiumByGroupId(schemeId);
								if(info == null)
								{
									_log.warn(getClass().getSimpleName() + ": Error while open info about premium account scheme ID[" + schemeId + "]! Scheme is null.");
									return;
								}

								if(ItemFunctions.deleteItem(player, info.getItemId(), info.getItemCount(), true))
								{
									int startTime = (int) (System.currentTimeMillis() / 1000);
									if(player.getNetConnection().getBonusType() >= 1.)
									{
										int endTime = player.getNetConnection().getBonusExpire();
										if(endTime >= System.currentTimeMillis() / 1000L)
											startTime = endTime;
									}

									int bonusExpire;
									if(info.isHours())
										bonusExpire = startTime + info.getDays() * 60 * 60;
									else
										bonusExpire = startTime + info.getDays() * 24 * 60 * 60;

									switch(Config.SERVICES_RATE_TYPE)
									{
										case Bonus.BONUS_GLOBAL_ON_AUTHSERVER:
											AuthServerCommunication.getInstance().sendPacket(new BonusRequest(player.getAccountName(), info.getGroupNumber(), bonusExpire));
											break;
										case Bonus.BONUS_GLOBAL_ON_GAMESERVER:
											AccountBonusDAO.getInstance().insert(player.getAccountName(), info.getGroupNumber(), bonusExpire);
											break;
									}

									player.getNetConnection().setBonus(info.getGroupNumber());
									player.getNetConnection().setBonusExpire(bonusExpire);

									player.stopBonusTask();
									player.startBonusTask();

									if(player.getParty() != null)
										player.getParty().recalculatePartyData();

									player.broadcastPacket(new MagicSkillUse(player, player, 23128, 1, 1, 0));
									player.sendPacket(new ExBR_PremiumStatePacket(player, true));

									IBbsHandler handler = BbsHandlerHolder.getInstance().getCommunityHandler("_cbbsservices_pa");
									if(handler != null)
										onBypassCommand(player, "_cbbsservices_pa");
									return;
								}
								else
								{
									String noItemsMsg = tpls.get(7);
									noItemsMsg = noItemsMsg.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(info.getItemId()));
									noItemsMsg = noItemsMsg.replace("<?fee_item_count?>", Util.formatAdena(info.getItemCount()));
									content.append(noItemsMsg);
								}
							}
						}
					}
					else
					{
						content.append(tpls.get(2));

						final String schemeButton = tpls.get(3);
						for(PremiumInfo premiumInfo : allAquisions)
						{
							int id = premiumInfo.getGroupNumber();
							String name = player.isLangRus() ? premiumInfo.getGroupNameRu() : premiumInfo.getGroupNameEn();

							String tempButton = schemeButton.replace("<?scheme_name?>", name);
							tempButton = tempButton.replace("<?scheme_id?>", String.valueOf(id));
							content.append(tempButton);
						}
					}
				}
				else
					content.append(tpls.get(1));

				html = html.replace("<?content?>", content.toString());
			}
			else if("changename".equals(cmd2))
			{
				if(!st.hasMoreTokens())
					return;

				String cmd3 = st.nextToken();
				if("player".equals(cmd3))
				{
					final int feeItemId = BBSConfig.CHANGE_PLAYER_NAME_SERVICE_COST_ITEM_ID;
					final long feeItemCount = BBSConfig.CHANGE_PLAYER_NAME_SERVICE_COST_ITEM_COUNT;
					if(feeItemId == 0)
					{
						player.sendMessage(player.isLangRus() ? "Данный сервис отключен." : "This service disallowed.");
						player.sendPacket(ShowBoardPacket.CLOSE);
						return;
					}

					TIntStringHashMap tpls = HtmCache.getInstance().getTemplates("scripts/handler/bbs/pages/change_player_name.htm", player);
					html = tpls.get(0);

					StringBuilder content = new StringBuilder();
					if(!st.hasMoreTokens())
					{
						if(feeItemCount > 0)
						{
							String feeBlock = tpls.get(1);
							feeBlock = feeBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							feeBlock = feeBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(feeBlock);
						}
						else
							content.append(tpls.get(2));

						content.append(tpls.get(3));
					}
					else
					{
						if(!BBSConfig.GLOBAL_USE_FUNCTIONS_CONFIGS && !checkUseCondition(player))
						{
							onWrongCondition(player);
							return;
						}

						String newPlayerName = st.nextToken();
						if(newPlayerName.charAt(0) == ' ')
							newPlayerName = newPlayerName.substring(1);

						if(player.getName().equals(newPlayerName))
							content.append(tpls.get(7));
						if(!Util.isMatchingRegexp(newPlayerName, Config.CNAME_TEMPLATE))
							content.append(tpls.get(5));
						else if(CharacterDAO.getInstance().getObjectIdByName(newPlayerName) > 0 || FakePlayersHolder.getInstance().getTemplate(newPlayerName) != null)
							content.append(tpls.get(6));
						else if(feeItemCount > 0 && !ItemFunctions.deleteItem(player, feeItemId, feeItemCount, true))
						{
							String noHaveItemBlock = tpls.get(4);
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(noHaveItemBlock);
						}
						else
						{
							content.append(tpls.get(8).replace("<?player_name?>", newPlayerName));

							String oldName = player.getName();

							player.reName(newPlayerName, true);
							Log.add("Character " + oldName + " renamed to " + newPlayerName, "renames");
						}
					}
					html = html.replace("<?content?>", content.toString());
				}
				else if("pet".equals(cmd3))
				{
					final int feeItemId = BBSConfig.CHANGE_PET_NAME_SERVICE_COST_ITEM_ID;
					final long feeItemCount = BBSConfig.CHANGE_PET_NAME_SERVICE_COST_ITEM_COUNT;
					if(feeItemId == 0)
					{
						player.sendMessage(player.isLangRus() ? "Данный сервис отключен." : "This service disallowed.");
						player.sendPacket(ShowBoardPacket.CLOSE);
						return;
					}

					TIntStringHashMap tpls = HtmCache.getInstance().getTemplates("scripts/handler/bbs/pages/change_pet_name.htm", player);
					html = tpls.get(0);

					StringBuilder content = new StringBuilder();
					if(!st.hasMoreTokens())
					{
						if(feeItemCount > 0)
						{
							String feeBlock = tpls.get(1);
							feeBlock = feeBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							feeBlock = feeBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(feeBlock);
						}
						else
							content.append(tpls.get(2));

						content.append(tpls.get(3));
					}
					else
					{
						if(!BBSConfig.GLOBAL_USE_FUNCTIONS_CONFIGS && !checkUseCondition(player))
						{
							onWrongCondition(player);
							return;
						}

						String newPetName = st.nextToken();
						if(newPetName.charAt(0) == ' ')
							newPetName = newPetName.substring(1);

						PetInstance pet = player.getPet();
						if(pet == null)
							content.append(tpls.get(8));
						else if(feeItemCount > 0 && pet.isDefaultName())
							content.append(tpls.get(7));
						else if(pet.getName().equals(newPetName))
							content.append(tpls.get(6));
						else if(newPetName.length() < 1 || newPetName.length() > 8)
							content.append(tpls.get(5));
						else if(feeItemCount > 0 && !ItemFunctions.deleteItem(player, feeItemId, feeItemCount, true))
						{
							String noHaveItemBlock = tpls.get(4);
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(noHaveItemBlock);
						}
						else
						{
							content.append(tpls.get(9).replace("<?pet_name?>", newPetName));

							String oldName = pet.getName();

							pet.setName(newPetName);
							pet.broadcastCharInfo();
							pet.updateControlItem();
							Log.add("Pet " + oldName + " renamed to " + newPetName, "renames");
						}
					}
					html = html.replace("<?content?>", content.toString());
				}
				else if("clan".equals(cmd3))
				{
					final int feeItemId = BBSConfig.CHANGE_CLAN_NAME_SERVICE_COST_ITEM_ID;
					final long feeItemCount = BBSConfig.CHANGE_CLAN_NAME_SERVICE_COST_ITEM_COUNT;
					if(feeItemId == 0)
					{
						player.sendMessage(player.isLangRus() ? "Данный сервис отключен." : "This service disallowed.");
						player.sendPacket(ShowBoardPacket.CLOSE);
						return;
					}

					TIntStringHashMap tpls = HtmCache.getInstance().getTemplates("scripts/handler/bbs/pages/change_clan_name.htm", player);
					html = tpls.get(0);

					StringBuilder content = new StringBuilder();
					if(!st.hasMoreTokens())
					{
						if(feeItemCount > 0)
						{
							String feeBlock = tpls.get(1);
							feeBlock = feeBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							feeBlock = feeBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(feeBlock);
						}
						else
							content.append(tpls.get(2));

						content.append(tpls.get(3));
					}
					else
					{
						if(!BBSConfig.GLOBAL_USE_FUNCTIONS_CONFIGS && !checkUseCondition(player))
						{
							onWrongCondition(player);
							return;
						}

						String newClanName = st.nextToken();
						if(newClanName.charAt(0) == ' ')
							newClanName = newClanName.substring(1);

						final Clan clan = player.getClan();
						if(clan == null)
							content.append(tpls.get(8));
						else if(!player.isClanLeader())
							content.append(tpls.get(9));
						else if(clan.getSubUnit(Clan.SUBUNIT_MAIN_CLAN).getName().equals(newClanName))
							content.append(tpls.get(6));
						else if(!Util.isMatchingRegexp(newClanName, Config.CLAN_NAME_TEMPLATE))
							content.append(tpls.get(5));
						else if(ClanTable.getInstance().getClanByName(newClanName) != null)
							content.append(tpls.get(7));
						else if(feeItemCount > 0 && !ItemFunctions.deleteItem(player, feeItemId, feeItemCount, true))
						{
							String noHaveItemBlock = tpls.get(4);
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(noHaveItemBlock);
						}
						else
						{
							content.append(tpls.get(10).replace("<?clan_name?>", newClanName));

							String oldName = clan.getSubUnit(Clan.SUBUNIT_MAIN_CLAN).getName();

							clan.getSubUnit(Clan.SUBUNIT_MAIN_CLAN).setName(newClanName, true);
							clan.updateClanInDB();
							clan.broadcastClanStatus(true, true, true);
							player.broadcastUserInfo(true);
							Log.add("Clan " + oldName + " renamed to " + newClanName, "renames");
						}
					}
					html = html.replace("<?content?>", content.toString());
				}
			}
			else if("color".equals(cmd2))
			{
				if(!st.hasMoreTokens())
					return;

				String cmd3 = st.nextToken();
				if("name".equals(cmd3))
				{
					final int feeItemId = BBSConfig.COLOR_NAME_SERVICE_COST_ITEM_ID;
					final long feeItemCount = BBSConfig.COLOR_NAME_SERVICE_COST_ITEM_COUNT;
					final String[] availableColors = BBSConfig.COLOR_NAME_SERVICE_COLORS;
					if(feeItemId == 0 || availableColors.length == 0)
					{
						player.sendMessage(player.isLangRus() ? "Данный сервис отключен." : "This service disallowed.");
						player.sendPacket(ShowBoardPacket.CLOSE);
						return;
					}

					TIntStringHashMap tpls = HtmCache.getInstance().getTemplates("scripts/handler/bbs/pages/color_name_change.htm", player);
					html = tpls.get(0);

					StringBuilder content = new StringBuilder();
					if(!st.hasMoreTokens())
					{
						if(feeItemCount > 0)
						{
							String feeBlock = tpls.get(1);
							feeBlock = feeBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							feeBlock = feeBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(feeBlock);
						}
						else
							content.append(tpls.get(2));

						final String colorBlock = tpls.get(3).replace("<?player_name?>", player.getName());

						if(player.getNameColor() != Integer.decode("0xFFFFFF"))
							content.append(colorBlock.replace("<?color?>", "FFFFFF"));

						for(String color : availableColors)
						{
							String bgrColor = color.substring(4, 6) + color.substring(2, 4) + color.substring(0, 2);
							if(player.getNameColor() != Integer.decode("0x" + bgrColor))
								content.append(colorBlock.replace("<?color?>", color));
						}
					}
					else
					{
						if(!BBSConfig.GLOBAL_USE_FUNCTIONS_CONFIGS && !checkUseCondition(player))
						{
							onWrongCondition(player);
							return;
						}

						final String newColor = st.nextToken().replace(" ", "");

						if(!newColor.equalsIgnoreCase("FFFFFF"))
						{
							boolean available = false;
							for(String color : availableColors)
							{
								if(color.equalsIgnoreCase(newColor))
								{
									available = true;
									break;
								}
							}

							if(!available)
							{
								player.sendPacket(ShowBoardPacket.CLOSE);
								return;
							}
						}

						final String bgrNewColor = newColor.substring(4, 6) + newColor.substring(2, 4) + newColor.substring(0, 2);
						final int newColorInt = Integer.decode("0x" + bgrNewColor);
						if(player.getNameColor() == newColorInt)
						{
							player.sendPacket(ShowBoardPacket.CLOSE);
							return;
						}

						if(feeItemCount > 0 && !ItemFunctions.deleteItem(player, feeItemId, feeItemCount, true))
						{
							String noHaveItemBlock = tpls.get(4);
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(noHaveItemBlock);
						}
						else
						{
							content.append(tpls.get(5).replace("<?color?>", newColor).replace("<?player_name?>", player.getName()));

							player.setNameColor(newColorInt);
							player.broadcastUserInfo(true);
							Log.add("Player " + player.getName() + " changed name color to " + newColor, "changecolor");
						}
					}
					html = html.replace("<?content?>", content.toString());
				}
				else if("title".equals(cmd3))
				{
					final int feeItemId = BBSConfig.COLOR_TITLE_SERVICE_COST_ITEM_ID;
					final long feeItemCount = BBSConfig.COLOR_TITLE_SERVICE_COST_ITEM_COUNT;
					final String[] availableColors = BBSConfig.COLOR_TITLE_SERVICE_COLORS;
					if(feeItemId == 0 || availableColors.length == 0)
					{
						player.sendMessage(player.isLangRus() ? "Данный сервис отключен." : "This service disallowed.");
						player.sendPacket(ShowBoardPacket.CLOSE);
						return;
					}

					TIntStringHashMap tpls = HtmCache.getInstance().getTemplates("scripts/handler/bbs/pages/color_title_change.htm", player);
					html = tpls.get(0);

					StringBuilder content = new StringBuilder();
					if(!st.hasMoreTokens())
					{
						if(feeItemCount > 0)
						{
							String feeBlock = tpls.get(1);
							feeBlock = feeBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							feeBlock = feeBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(feeBlock);
						}
						else
							content.append(tpls.get(2));

						final String colorBlock = tpls.get(3).replace("<?player_name?>", player.getName());

						if(player.getTitleColor() != Integer.decode("0xFFFF77"))
							content.append(colorBlock.replace("<?color?>", "77FFFF"));

						for(String color : availableColors)
						{
							String bgrColor = color.substring(4, 6) + color.substring(2, 4) + color.substring(0, 2);
							if(player.getTitleColor() != Integer.decode("0x" + bgrColor))
								content.append(colorBlock.replace("<?color?>", color));
						}
					}
					else
					{
						if(!BBSConfig.GLOBAL_USE_FUNCTIONS_CONFIGS && !checkUseCondition(player))
						{
							onWrongCondition(player);
							return;
						}

						final String newColor = st.nextToken().replace(" ", "");

						if(!newColor.equalsIgnoreCase("77FFFF"))
						{
							boolean available = false;
							for(String color : availableColors)
							{
								if(color.equalsIgnoreCase(newColor))
								{
									available = true;
									break;
								}
							}

							if(!available)
							{
								player.sendPacket(ShowBoardPacket.CLOSE);
								return;
							}
						}

						final String bgrNewColor = newColor.substring(4, 6) + newColor.substring(2, 4) + newColor.substring(0, 2);
						final int newColorInt = Integer.decode("0x" + bgrNewColor);
						if(player.getTitleColor() == newColorInt)
						{
							player.sendPacket(ShowBoardPacket.CLOSE);
							return;
						}

						if(feeItemCount > 0 && !ItemFunctions.deleteItem(player, feeItemId, feeItemCount, true))
						{
							String noHaveItemBlock = tpls.get(4);
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_count?>", Util.formatAdena(feeItemCount));
							noHaveItemBlock = noHaveItemBlock.replace("<?fee_item_name?>", HtmlUtils.htmlItemName(feeItemId));

							content.append(noHaveItemBlock);
						}
						else
						{
							content.append(tpls.get(5).replace("<?color?>", newColor).replace("<?player_name?>", player.getName()));

							player.setTitleColor(newColorInt);
							player.broadcastUserInfo(true);
							Log.add("Player " + player.getName() + " changed title color to " + newColor, "changecolor");
						}
					}
					html = html.replace("<?content?>", content.toString());
				}
			}
		}
		ShowBoardPacket.separateAndSend(html, player);
	}

	@Override
	protected void doWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5)
	{
		//
	}

	private static String doubleToString(double value)
	{
		int intValue = (int) value;
		if(intValue == value)
			return String.valueOf(intValue);
		return String.valueOf(value);
	}
}