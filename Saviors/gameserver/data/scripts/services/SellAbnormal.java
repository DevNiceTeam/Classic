package services;

import l2s.gameserver.Config;
import l2s.gameserver.model.Player;
import l2s.gameserver.network.l2.components.SystemMsg;
import l2s.gameserver.network.l2.s2c.ShowBoardPacket;
import l2s.gameserver.skills.AbnormalEffect;
import l2s.gameserver.utils.Functions;

public class SellAbnormal extends Functions
{
	public void getAbnormal1()
	{
		Player player = getSelf();

		if(player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.SANTA_SUIT) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.BASEBALL) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_KREST) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ARCHI_MAGE) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ELEGANT_CAPE))
		{
			player.sendMessage(player.isLangRus() ? "Вы уже находитесь под эффектом Dolce & Gabbana." : "You are already under the effect of Dolce & Gabbana.");
			player.sendMessage(player.isLangRus() ? "Сбросьте эффект кнопкой Cброс." : "Reset effect button reset.");
			//packet();
		}
		else
		{
			if(player.getInventory().destroyItemByItemId(Config.ABNORMAL_ITEM_ID, Config.ABNORMAL_ITEM_COUNT))
			{
				player.sendMessage(player.isLangRus() ? "- " + Config.ABNORMAL_ITEM_COUNT + "Аден " : "- " + Config.ABNORMAL_ITEM_COUNT + "Aden ");
				player.startAbnormalEffect(AbnormalEffect.SANTA_SUIT);
				//packet();
			}
			else
			{
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			//	packet();
			}
		}

	}

	public void getAbnormal2()
	{
		Player player = getSelf();

		if(player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.SANTA_SUIT) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.BASEBALL) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_KREST) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ARCHI_MAGE) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ELEGANT_CAPE))
		{
			player.sendMessage(player.isLangRus() ? "Вы уже находитесь под эффектом Dolce & Gabbana." : "You are already under the effect of Dolce & Gabbana.");
			player.sendMessage(player.isLangRus() ? "Сбросьте эффект кнопкой Cброс." : "Reset effect button reset.");
			packet();
		}
		else
		{
			if(player.getInventory().destroyItemByItemId(Config.ABNORMAL_ITEM_ID, Config.ABNORMAL_ITEM_COUNT))
			{
				player.sendMessage(player.isLangRus() ? "- " + Config.ABNORMAL_ITEM_COUNT + "Аден " : "- " + Config.ABNORMAL_ITEM_COUNT + "Aden ");
				player.startAbnormalEffect(AbnormalEffect.BASEBALL);
				packet();
				return;
			}
			else
			{
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				packet();
			}
		}
	}

	public void getAbnormal3()
	{
		Player player = getSelf();

		if(player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.SANTA_SUIT) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.BASEBALL) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_KREST) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ARCHI_MAGE) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ELEGANT_CAPE))
		{
			player.sendMessage(player.isLangRus() ? "Вы уже находитесь под эффектом Dolce & Gabbana." : "You are already under the effect of Dolce & Gabbana.");
			player.sendMessage(player.isLangRus() ? "Сбросьте эффект кнопкой Cброс." : "Reset effect button reset.");
			packet();
		}
		else
		{
			if(player.getInventory().destroyItemByItemId(Config.ABNORMAL_ITEM_ID, Config.ABNORMAL_ITEM_COUNT))
			{
				player.sendMessage(player.isLangRus() ? "- " + Config.ABNORMAL_ITEM_COUNT + "Аден " : "- " + Config.ABNORMAL_ITEM_COUNT + "Aden ");
				player.startAbnormalEffect(AbnormalEffect.TRANSFORM_KREST);
				packet();
			}
			else
			{
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				packet();
			}
		}
	}

	public void getAbnormal4()
	{
		Player player = getSelf();

		if(player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.SANTA_SUIT) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.BASEBALL) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_KREST) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ARCHI_MAGE) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ELEGANT_CAPE))
		{
			player.sendMessage(player.isLangRus() ? "Вы уже находитесь под эффектом Dolce & Gabbana." : "You are already under the effect of Dolce & Gabbana.");
			player.sendMessage(player.isLangRus() ? "Сбросьте эффект кнопкой Cброс." : "Reset effect button reset.");
			packet();
		}
		else
		{
			if(player.getInventory().destroyItemByItemId(Config.ABNORMAL_ITEM_ID, Config.ABNORMAL_ITEM_COUNT))
			{
				player.sendMessage(player.isLangRus() ? "- " + Config.ABNORMAL_ITEM_COUNT + "Аден " : "- " + Config.ABNORMAL_ITEM_COUNT + "Aden ");
				player.startAbnormalEffect(AbnormalEffect.TRANSFORM_ARCHI_MAGE);
				packet();
			}
			else
			{
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				packet();
			}
		}
	}

	public void getAbnormal5()
	{
		Player player = getSelf();

		if(player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.SANTA_SUIT) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.BASEBALL) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_KREST) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ARCHI_MAGE) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.TRANSFORM_ELEGANT_CAPE))
		{
			player.sendMessage(player.isLangRus() ? "Вы уже находитесь под эффектом Dolce & Gabbana." : "You are already under the effect of Dolce & Gabbana.");
			player.sendMessage(player.isLangRus() ? "Сбросьте эффект кнопкой Cброс." : "Reset effect button reset.");
			packet();
		}
		else
		{
			if(player.getInventory().destroyItemByItemId(Config.ABNORMAL_ITEM_ID, Config.ABNORMAL_ITEM_COUNT))
			{
				player.sendMessage(player.isLangRus() ? "- " + Config.ABNORMAL_ITEM_COUNT + "Аден " : "- " + Config.ABNORMAL_ITEM_COUNT + "Aden ");
				player.startAbnormalEffect(AbnormalEffect.TRANSFORM_ELEGANT_CAPE);
				packet();
			}
			else
			{
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				packet();
			}
		}
	}

	public void getAbnormal6()
	{
		Player player = getSelf();

		if(player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.BIG_CHAR) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.SMALL_CHAR))
		{
			player.sendMessage(player.isLangRus() ? "Вы уже находитесь под эффектом Dolce & Gabbana." : "You are already under the effect of Dolce & Gabbana.");
			player.sendMessage(player.isLangRus() ? "Сбросьте эффект кнопкой Cброс." : "Reset effect button reset.");
			packet();
		}
		else
		{
			if(player.getInventory().destroyItemByItemId(Config.ABNORMAL_ITEM_ID, Config.ABNORMAL_ITEM_COUNT))
			{
				player.sendMessage(player.isLangRus() ? "- " + Config.ABNORMAL_ITEM_COUNT + "Аден " : "- " + Config.ABNORMAL_ITEM_COUNT + "Aden ");
				player.startAbnormalEffect(AbnormalEffect.BIG_CHAR);
				packet();
			}
			else
			{
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				packet();
			}
		}
	}

	public void getAbnormal7()
	{
		Player player = getSelf();

		if(player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.BIG_CHAR) || player.getPlayer().getAbnormalEffects().contains(AbnormalEffect.SMALL_CHAR))
		{
			player.sendMessage(player.isLangRus() ? "Вы уже находитесь под эффектом Dolce & Gabbana." : "You are already under the effect of Dolce & Gabbana.");
			player.sendMessage(player.isLangRus() ? "Сбросьте эффект кнопкой Cброс." : "Reset effect button reset.");
			packet();
		}
		else
		{
			if(player.getInventory().destroyItemByItemId(Config.ABNORMAL_ITEM_ID, Config.ABNORMAL_ITEM_COUNT))
			{
				player.sendMessage(player.isLangRus() ? "- " + Config.ABNORMAL_ITEM_COUNT + "Аден " : "- " + Config.ABNORMAL_ITEM_COUNT + "Aden ");
				player.startAbnormalEffect(AbnormalEffect.SMALL_CHAR);
				packet();
			}
			else
			{
				player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				packet();
			}
		}
	}

	public void reset()
	{
		Player player = getSelf();

		player.getPlayer().stopAbnormalEffect(AbnormalEffect.SANTA_SUIT);
		player.getPlayer().stopAbnormalEffect(AbnormalEffect.BASEBALL);
		player.getPlayer().stopAbnormalEffect(AbnormalEffect.TRANSFORM_KREST);
		player.getPlayer().stopAbnormalEffect(AbnormalEffect.TRANSFORM_ARCHI_MAGE);
		player.getPlayer().stopAbnormalEffect(AbnormalEffect.TRANSFORM_ELEGANT_CAPE);
		player.getPlayer().stopAbnormalEffect(AbnormalEffect.BIG_CHAR);
		player.getPlayer().stopAbnormalEffect(AbnormalEffect.SMALL_CHAR);
		packet();

	}
	
	public void packet()
	{
		Player player = getSelf();
		player.broadcastUserInfo(true);
		player.sendPacket(ShowBoardPacket.CLOSE);
	}
}
