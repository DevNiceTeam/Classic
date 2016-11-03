package services;

import l2s.gameserver.Config;
import l2s.gameserver.dao.AccountBonusDAO;
import l2s.gameserver.data.htm.HtmCache;
import l2s.gameserver.data.xml.holder.ItemHolder;
import l2s.gameserver.handler.bypass.Bypass;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.actor.instances.player.Bonus;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.network.authcomm.AuthServerCommunication;
import l2s.gameserver.network.authcomm.gs2as.BonusRequest;
import l2s.gameserver.network.l2.components.SystemMsg;
import l2s.gameserver.network.l2.s2c.ExBR_PremiumStatePacket;
import l2s.gameserver.utils.Functions;
import l2s.gameserver.utils.Log;

public class RateBonus
{
	@Bypass("services.RateBonus:list")
	public void list(Player player, NpcInstance npc, String[] param)
	{
		if(Config.SERVICES_RATE_TYPE == Bonus.NO_BONUS)
		{
			Functions.show(HtmCache.getInstance().getHtml("npcdefault.htm", player), player);
			return;
		}

		String html;
		if(player.getNetConnection().getBonusType() >= 1.)
		{
			if(player.getLevel() <= 39)
			{
				html = HtmCache.getInstance().getHtml("scripts/services/RateBonusAlready.htm", player);
			}
			else
			{
				html = HtmCache.getInstance().getHtml("scripts/services/RateBonus.htm", player);

				String add = "";
				for(int i = 0; i < Config.SERVICES_RATE_BONUS_DAYS.length; i++)
					add += "<Button ALIGN=LEFT ICON=\"NORMAL\" action=\"bypass -h htmbypass_services.RateBonus:get " + i + "\">" //
					+ (Config.SERVICES_RATE_BONUS_VALUE[i] * 100 - 100) + //
					"% for " + Config.SERVICES_RATE_BONUS_DAYS[i] + //
					" days - " + Config.SERVICES_RATE_BONUS_PRICE[i] + //
					" " + ItemHolder.getInstance().getTemplate(Config.SERVICES_RATE_BONUS_ITEM[i]).getName() + "</button>";

				html = html.replaceFirst("%toreplace%", add);
			}
		}
		else
			html = HtmCache.getInstance().getHtml("scripts/services/RateBonusNo.htm", player);

		Functions.show(html, player);
	}

	@Bypass("services.RateBonus:get")
	public void get(Player player, NpcInstance npc, String[] param)
	{
		if(Config.SERVICES_RATE_TYPE == Bonus.NO_BONUS)
		{
			Functions.show(HtmCache.getInstance().getHtml("npcdefault.htm", player), player);
			return;
		}

		int i = Integer.parseInt(param[0]);

		if(!player.getInventory().destroyItemByItemId(Config.SERVICES_RATE_BONUS_ITEM[i], Config.SERVICES_RATE_BONUS_PRICE[i]))
		{
			if(Config.SERVICES_RATE_BONUS_ITEM[i] == 57)
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			else
				player.sendPacket(SystemMsg.INCORRECT_ITEM_COUNT);
			return;
		}

		if(Config.SERVICES_RATE_TYPE == Bonus.BONUS_GLOBAL_ON_AUTHSERVER && AuthServerCommunication.getInstance().isShutdown())
		{
			list(player, npc, param);
			return;
		}

		Log.add(player.getName() + "|" + player.getObjectId() + "|rate bonus|" + Config.SERVICES_RATE_BONUS_VALUE[i] + "|" + Config.SERVICES_RATE_BONUS_DAYS[i] + "|", "services");

		int bonus = Config.SERVICES_RATE_BONUS_VALUE[i];
		int bonusExpire = (int) (System.currentTimeMillis() / 1000L) + Config.SERVICES_RATE_BONUS_DAYS[i] * 24 * 60 * 60;

		switch(Config.SERVICES_RATE_TYPE)
		{
			case Bonus.BONUS_GLOBAL_ON_AUTHSERVER:
				AuthServerCommunication.getInstance().sendPacket(new BonusRequest(player.getAccountName(), bonus, bonusExpire));
				break;
			case Bonus.BONUS_GLOBAL_ON_GAMESERVER:
				AccountBonusDAO.getInstance().insert(player.getAccountName(), bonus, bonusExpire);
				break;
		}

		player.getNetConnection().setBonus(bonus);
		player.getNetConnection().setBonusExpire(bonusExpire);

		player.stopBonusTask();
		player.startBonusTask();

		if(player.getParty() != null)
			player.getParty().recalculatePartyData();

		player.sendPacket(new ExBR_PremiumStatePacket(player, true));

		Functions.show(HtmCache.getInstance().getHtml("scripts/services/RateBonusGet.htm", player), player);
	}
}