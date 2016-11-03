package handler.onshiftaction.commons;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import l2s.commons.map.hash.TIntStringHashMap;
import l2s.gameserver.Config;
import l2s.gameserver.data.htm.HtmCache;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.Experience;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.instances.RaidBossInstance;
import l2s.gameserver.model.reward.RewardData;
import l2s.gameserver.model.reward.RewardGroup;
import l2s.gameserver.model.reward.RewardList;
import l2s.gameserver.model.reward.RewardType;
import l2s.gameserver.stats.Stats;
import l2s.gameserver.utils.HtmlUtils;

public abstract class RewardListInfo
{
	private static final int MAX_ITEMS_ON_PAGE = 40; // 40 Максимум клиента

	private static final NumberFormat pf = NumberFormat.getPercentInstance(Locale.ENGLISH);
	static
	{
		pf.setMaximumFractionDigits(5);
		pf.setMinimumFractionDigits(0);
	}

	public static void showInfo(Player player, NpcInstance npc, RewardType showType, int showGroupId, int page)
	{
		final int diff = npc.calculateLevelDiffForDrop(player.isInParty() ? player.getParty().getLevel() : player.getLevel());

		final double dropRateMul = npc.calcStat(Stats.DROP_RATE_MULTIPLIER, 1., player, null);
		final double spoilRateMul = npc.calcStat(Stats.SPOIL_RATE_MULTIPLIER, 1., player, null);

		double mod = Experience.penaltyModifier(diff, 9);

		final TIntStringHashMap tpls = HtmCache.getInstance().getTemplates("actions/rewardlist_info.htm", player);

		String html = tpls.get(0);
		html = html.replace("<?npc_name?>", HtmlUtils.htmlNpcName(npc.getNpcId()));
		html = html.replace("<?id?>", String.valueOf(npc.getNpcId()));
		html = html.replace("<?level?>", String.valueOf(npc.getLevel()));

		StringBuilder content = new StringBuilder();
		if(mod <= 0)
			content.append(tpls.get(1));
		else if(npc.getTemplate().getRewards().isEmpty())
			content.append(tpls.get(2));
		else
		{
			StringBuilder ratedGroupedBuilder = new StringBuilder();
			StringBuilder notRatedGroupedBuilder = new StringBuilder();
			StringBuilder notGroupedBuilder = new StringBuilder();
			StringBuilder spoilBuilder = new StringBuilder();
			for(Map.Entry<RewardType, RewardList> entry : npc.getTemplate().getRewards().entrySet())
			{
				RewardList rewardList = entry.getValue();

				final RewardType type = entry.getKey();
				switch(type)
				{
					case RATED_GROUPED:
						ratedGroupedRewardList(player, tpls, ratedGroupedBuilder, npc, rewardList, mod * dropRateMul, type == showType ? showGroupId : 0, page);
						break;
					case NOT_RATED_GROUPED:
						notRatedGroupedRewardList(player, tpls, notRatedGroupedBuilder, rewardList, type == showType ? showGroupId : 0, page);
						break;
					case NOT_RATED_NOT_GROUPED:
						notGroupedRewardList(player, tpls, notGroupedBuilder, rewardList, 1.0, mod * dropRateMul, type == showType, page);
						break;
					case SWEEP:
						notGroupedRewardList(player, tpls, spoilBuilder, rewardList, player.getRateSpoil(), mod * spoilRateMul, type == showType, page);
						break;
				}
			}

			content.append(ratedGroupedBuilder.toString());
			content.append(notRatedGroupedBuilder.toString());
			content.append(notGroupedBuilder.toString());
			content.append(spoilBuilder.toString());
		}

		html = html.replace("<?content?>", content.toString());

		npc.showChatWindow(player, html, false);
	}

	public static void ratedGroupedRewardList(Player player, TIntStringHashMap tpls, StringBuilder builder, NpcInstance npc, RewardList list, double mod, int showGroupId, int page)
	{
		if(list.isEmpty())
			return;

		String mainBlock = tpls.get(8);
		mainBlock = mainBlock.replace("<?group_type_name?>", tpls.get(3));

		StringBuilder groupsList = new StringBuilder();

		int groupId = 0;
		for(RewardGroup g : list)
		{
			groupId++;

			List<RewardData> items = new ArrayList<RewardData>();
			for(RewardData d : g.getItems())
			{
				if(!d.getItem().isHerb())
					items.add(d);
			}

			if(items.isEmpty())
				continue;

			double gchance = g.getChance();
			double gmod = mod;
			double grate;
			double gmult;

			double rateDrop = (npc instanceof RaidBossInstance ? Config.RATE_DROP_RAIDBOSS : npc.isSiegeGuard() ? Config.RATE_DROP_SIEGE_GUARD : player.getRateItems());
			double rateAdena = player.getRateAdena();

			if(g.isAdena())
			{
				if(rateAdena == 0)
					continue;

				grate = rateAdena;
			}
			else
			{
				if(rateDrop == 0)
					continue;

				grate = rateDrop;
			}

			if(g.notRate())
				grate = Math.min(gmod, 1.0);
			else
				grate *= gmod;

			gmult = Math.ceil(grate);

			String groupName = tpls.get(7);
			groupName = groupName.replace("<?group_id?>", String.valueOf(groupId));
			groupName = groupName.replace("<?group_chance?>", pf.format(gchance / RewardList.MAX_CHANCE));

			if(groupId == showGroupId)
			{
				final int minPage = 1;
				final int maxPage = (int) Math.ceil((double) items.size() / MAX_ITEMS_ON_PAGE);
				final int currentPage = Math.max(Math.min(maxPage, page), minPage);

				String groupBlock = tpls.get(10);

				String prevButton;
				if(currentPage == minPage || maxPage == 1)
					prevButton = tpls.get(12);
				else
				{
					prevButton = tpls.get(13);
					prevButton = prevButton.replace("<?page?>", String.valueOf(currentPage - 1));
				}

				prevButton = prevButton.replace("<?button_name?>", "<");
				groupBlock = groupBlock.replace("<?prev_button?>", prevButton);

				String nextButton;
				if(currentPage == maxPage || maxPage == 1)
					nextButton = tpls.get(12);
				else
				{
					nextButton = tpls.get(13);
					nextButton = nextButton.replace("<?page?>", String.valueOf(currentPage + 1));
				}

				nextButton = nextButton.replace("<?button_name?>", ">");
				groupBlock = groupBlock.replace("<?next_button?>", nextButton);

				groupBlock = groupBlock.replace("<?group_name?>", groupName);
				groupBlock = groupBlock.replace("<?group_type?>", String.valueOf(list.getType()));
				groupBlock = groupBlock.replace("<?group_id?>", String.valueOf(groupId));

				StringBuilder dropList = new StringBuilder();

				for(int i = ((currentPage - 1) * MAX_ITEMS_ON_PAGE); i < Math.min(currentPage * MAX_ITEMS_ON_PAGE, items.size()); i++)
				{
					RewardData d = items.get(i);
					double imult = d.notRate() ? 1.0 : gmult;
					String icon = d.getItem().getIcon();
					if(icon == null || icon.equals(StringUtils.EMPTY))
						icon = "icon.etc_question_mark_i00";

					String dropBlock = tpls.get(11);
					dropBlock = dropBlock.replace("<?line_color?>", (((i % 2) == 0) ? "2D2D2D" : "000000"));
					dropBlock = dropBlock.replace("<?drop_icon?>", icon);
					dropBlock = dropBlock.replace("<?drop_item_name?>", HtmlUtils.htmlItemName(d.getItemId()));
					dropBlock = dropBlock.replace("<?drop_min_count?>", String.valueOf(Math.round(d.getMinDrop())));
					dropBlock = dropBlock.replace("<?drop_max_count?>", String.valueOf(Math.round(d.getMaxDrop() * imult)));
					dropBlock = dropBlock.replace("<?drop_chance?>", pf.format(d.getChance() / RewardList.MAX_CHANCE));

					dropList.append(dropBlock);
				}

				groupBlock = groupBlock.replace("<?drop_list?>", dropList.toString());
				groupsList.append(groupBlock);
			}
			else
			{
				String groupBlock = tpls.get(9);
				groupBlock = groupBlock.replace("<?group_name?>", groupName);
				groupBlock = groupBlock.replace("<?group_type?>", String.valueOf(list.getType()));
				groupBlock = groupBlock.replace("<?group_id?>", String.valueOf(groupId));

				groupsList.append(groupBlock);
			}
		}

		if(groupsList.length() > 0)
		{
			mainBlock = mainBlock.replace("<?groups_list?>", groupsList.toString());
			builder.append(mainBlock);
		}
	}

	public static void notRatedGroupedRewardList(Player player, TIntStringHashMap tpls, StringBuilder builder, RewardList list, int showGroupId, int page)
	{
		if(list.isEmpty())
			return;

		String mainBlock = tpls.get(8);
		mainBlock = mainBlock.replace("<?group_type_name?>", tpls.get(4));

		StringBuilder groupsList = new StringBuilder();

		int groupId = 0;
		for(RewardGroup g : list)
		{
			groupId++;

			List<RewardData> items = new ArrayList<RewardData>();
			for(RewardData d : g.getItems())
			{
				if(!d.getItem().isHerb())
					items.add(d);
			}

			if(items.isEmpty())
				continue;

			double gchance = g.getChance();

			String groupName = tpls.get(7);
			groupName = groupName.replace("<?group_id?>", String.valueOf(groupId));
			groupName = groupName.replace("<?group_chance?>", pf.format(gchance / RewardList.MAX_CHANCE));

			if(groupId == showGroupId)
			{
				final int minPage = 1;
				final int maxPage = (int) Math.ceil((double) items.size() / MAX_ITEMS_ON_PAGE);
				final int currentPage = Math.max(Math.min(maxPage, page), minPage);

				String groupBlock = tpls.get(10);

				String prevButton;
				if(currentPage == minPage || maxPage == 1)
					prevButton = tpls.get(12);
				else
				{
					prevButton = tpls.get(13);
					prevButton = prevButton.replace("<?page?>", String.valueOf(currentPage - 1));
				}

				prevButton = prevButton.replace("<?button_name?>", "<");
				groupBlock = groupBlock.replace("<?prev_button?>", prevButton);

				String nextButton;
				if(currentPage == maxPage || maxPage == 1)
					nextButton = tpls.get(12);
				else
				{
					nextButton = tpls.get(13);
					nextButton = nextButton.replace("<?page?>", String.valueOf(currentPage + 1));
				}

				nextButton = nextButton.replace("<?button_name?>", ">");
				groupBlock = groupBlock.replace("<?next_button?>", nextButton);

				groupBlock = groupBlock.replace("<?group_name?>", groupName);
				groupBlock = groupBlock.replace("<?group_type?>", String.valueOf(list.getType()));
				groupBlock = groupBlock.replace("<?group_id?>", String.valueOf(groupId));

				StringBuilder dropList = new StringBuilder();

				for(int i = ((currentPage - 1) * MAX_ITEMS_ON_PAGE); i < Math.min(currentPage * MAX_ITEMS_ON_PAGE, items.size()); i++)
				{
					RewardData d = items.get(i);
					String icon = d.getItem().getIcon();
					if(icon == null || icon.equals(StringUtils.EMPTY))
						icon = "icon.etc_question_mark_i00";

					String dropBlock = tpls.get(11);
					dropBlock = dropBlock.replace("<?line_color?>", (((i % 2) == 0) ? "2D2D2D" : "000000"));
					dropBlock = dropBlock.replace("<?drop_icon?>", icon);
					dropBlock = dropBlock.replace("<?drop_item_name?>", HtmlUtils.htmlItemName(d.getItemId()));
					dropBlock = dropBlock.replace("<?drop_min_count?>", String.valueOf(Math.round(d.getMinDrop())));
					dropBlock = dropBlock.replace("<?drop_max_count?>", String.valueOf(Math.round(d.getMaxDrop())));
					dropBlock = dropBlock.replace("<?drop_chance?>", pf.format(d.getChance() / RewardList.MAX_CHANCE));

					dropList.append(dropBlock);
				}

				groupBlock = groupBlock.replace("<?drop_list?>", dropList.toString());
				groupsList.append(groupBlock);
			}
			else
			{
				String groupBlock = tpls.get(9);
				groupBlock = groupBlock.replace("<?group_name?>", groupName);
				groupBlock = groupBlock.replace("<?group_type?>", String.valueOf(list.getType()));
				groupBlock = groupBlock.replace("<?group_id?>", String.valueOf(groupId));

				groupsList.append(groupBlock);
			}
		}

		if(groupsList.length() > 0)
		{
			mainBlock = mainBlock.replace("<?groups_list?>", groupsList.toString());
			builder.append(mainBlock);
		}
	}

	private static class DropInfo
	{
		public final String name;
		public final String icon;
		public final long minCount;
		public final long maxCount;
		public final double chance;

		public DropInfo(String name, String icon, long minCount, long maxCount, double chance)
		{
			this.name = name;
			this.icon = icon;
			this.minCount = minCount;
			this.maxCount = maxCount;
			this.chance = chance;
		}
	}

	public static void notGroupedRewardList(Player player, TIntStringHashMap tpls, StringBuilder builder, RewardList list, double rate, double mod, boolean show, int page)
	{
		if(rate == 0)
			return;

		if(list.isEmpty())
			return;

		String mainBlock = tpls.get(8);
		mainBlock = mainBlock.replace("<?group_type_name?>", "");

		if(show)
		{
			List<DropInfo> infos = new ArrayList<DropInfo>();
			for(RewardGroup g : list)
			{
				List<RewardData> items = new ArrayList<RewardData>();
				for(RewardData d : g.getItems())
				{
					if(!d.getItem().isHerb())
						items.add(d);
				}

				if(items.isEmpty())
					continue;

				double gmod = mod;
				double grate;
				double gmult;

				grate = rate;

				if(g.notRate())
					grate = Math.min(gmod, 1.0);
				else
					grate *= gmod;

				gmult = Math.ceil(grate);

				int i = 0;
				for(RewardData d : items)
				{
					double imult = d.notRate() ? 1.0 : gmult;
					String icon = d.getItem().getIcon();
					if(icon == null || icon.equals(StringUtils.EMPTY))
						icon = "icon.etc_question_mark_i00";

					infos.add(new DropInfo(HtmlUtils.htmlItemName(d.getItemId()), icon, d.getMinDrop(), Math.round(d.getMaxDrop() * imult), d.getChance() / RewardList.MAX_CHANCE));
				}
			}

			if(infos.isEmpty())
				return;

			final int minPage = 1;
			final int maxPage = (int) Math.ceil((double) infos.size() / MAX_ITEMS_ON_PAGE);
			final int currentPage = Math.max(Math.min(maxPage, page), minPage);

			String groupBlock = tpls.get(10);

			String prevButton;
			if(currentPage == minPage || maxPage == 1)
				prevButton = tpls.get(12);
			else
			{
				prevButton = tpls.get(13);
				prevButton = prevButton.replace("<?page?>", String.valueOf(currentPage - 1));
			}

			prevButton = prevButton.replace("<?button_name?>", "<");
			groupBlock = groupBlock.replace("<?prev_button?>", prevButton);

			String nextButton;
			if(currentPage == maxPage || maxPage == 1)
				nextButton = tpls.get(12);
			else
			{
				nextButton = tpls.get(13);
				nextButton = nextButton.replace("<?page?>", String.valueOf(currentPage + 1));
			}

			nextButton = nextButton.replace("<?button_name?>", ">");
			groupBlock = groupBlock.replace("<?next_button?>", nextButton);

			groupBlock = groupBlock.replace("<?group_name?>", list.getType() == RewardType.SWEEP ? tpls.get(5) : tpls.get(6));
			groupBlock = groupBlock.replace("<?group_type?>", String.valueOf(list.getType()));
			groupBlock = groupBlock.replace("<?group_id?>", "0");

			StringBuilder dropList = new StringBuilder();

			for(int i = ((currentPage - 1) * MAX_ITEMS_ON_PAGE); i < Math.min(currentPage * MAX_ITEMS_ON_PAGE, infos.size()); i++)
			{
				DropInfo info = infos.get(i);
				String dropBlock = tpls.get(11);
				dropBlock = dropBlock.replace("<?line_color?>", (((i % 2) == 0) ? "2D2D2D" : "000000"));
				dropBlock = dropBlock.replace("<?drop_icon?>", info.icon);
				dropBlock = dropBlock.replace("<?drop_item_name?>", info.name);
				dropBlock = dropBlock.replace("<?drop_min_count?>", String.valueOf(info.minCount));
				dropBlock = dropBlock.replace("<?drop_max_count?>", String.valueOf(info.maxCount));
				dropBlock = dropBlock.replace("<?drop_chance?>", pf.format(info.chance));

				dropList.append(dropBlock);
			}

			groupBlock = groupBlock.replace("<?drop_list?>", dropList.toString());
			mainBlock = mainBlock.replace("<?groups_list?>", groupBlock);
			builder.append(mainBlock);
		}
		else
		{
			boolean haveDrop = false;
			for(RewardGroup g : list)
			{
				for(RewardData d : g.getItems())
				{
					if(!d.getItem().isHerb())
					{
						haveDrop = true;
						break;
					}
				}
			}

			if(haveDrop)
			{
				String groupBlock = tpls.get(9);
				groupBlock = groupBlock.replace("<?group_name?>", list.getType() == RewardType.SWEEP ? tpls.get(5) : tpls.get(6));
				groupBlock = groupBlock.replace("<?group_type?>", String.valueOf(list.getType()));
				groupBlock = groupBlock.replace("<?group_id?>", "0");

				mainBlock = mainBlock.replace("<?groups_list?>", groupBlock);

				builder.append(mainBlock);
			}
		}
	}
}