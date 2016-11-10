package quests;

import l2s.gameserver.Config;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;
import l2s.commons.util.Rnd;

public class _375_WhisperOfDreams2 extends Quest
{
	//NPCs
	private static final int MANAKIA = 30515;
	//Quest items
	private static final int MSTONE = 5887;
	private static final int K_HORN = 5888;
	private static final int CH_SKULL = 5889;
	// Items chance drop
	private static final int DROP_CHANCE_ITEMS = 20;
	//Quest collections
	private static final int[] REWARDS =
	{ 5348, 5352, 5350 };
	private static final int[] REWARDS2 =
	{ 5349, 5353, 5351 };
	//Mobs & Drop
	private static final int CAVEHOWLER = 20624;
	private static final int KARIK = 20629;
	//Messages
	private static final String _default = "noquest";

	public _375_WhisperOfDreams2()
	{
		super(PARTY_NONE, REPEATABLE);
		addStartNpc(MANAKIA);
		addKillId(new int[]
		{ CAVEHOWLER, KARIK });
		addQuestItem(new int[]
		{ K_HORN, CH_SKULL });
		addLevelCheck(60, 74);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		if(event.equalsIgnoreCase("30515-6.htm"))
		{
			st.set("cond", "1");
			st.playSound(SOUND_ACCEPT);
		}
		else if(event.equalsIgnoreCase("30515-7.htm"))
		{
			st.finishQuest();
		}
		else if(event.equalsIgnoreCase("30515-8.htm"))
		{}
		return event;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = _default;
		if(st.getCond() == 0)
		{
			htmltext = "30515-1.htm";
			if(st.getPlayer().getLevel() < 60)
			{
				htmltext = "30515-2.htm";
			}
			else if(st.getQuestItemsCount(MSTONE) < 1)
			{
				htmltext = "30515-3.htm";
			}
		}
		else if(st.getCond() == 1)
		{
			if(st.getQuestItemsCount(CH_SKULL) == 100 && st.getQuestItemsCount(K_HORN) == 100)
			{
				st.takeItems(CH_SKULL, -1);
				st.takeItems(K_HORN, -1);
				//TODO: [Alergy] ALT_100_RECIPES_A найти аналог илил добавить.
				//int item = Config.ALT_100_RECIPES_A ? REWARDS2[Rnd.get(REWARDS2.length)] : REWARDS[Rnd.get(REWARDS.length)];
				int item = REWARDS[Rnd.get(REWARDS.length)];
				st.giveItems(item, 1);
				htmltext = "30515-4.htm";
			}
			else
			{
				htmltext = "30515-5.htm";
			}
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcid = npc.getNpcId();
		if(npcid == KARIK)
		{
			st.rollAndGive(K_HORN, 1, 1, 100, DROP_CHANCE_ITEMS);
		}
		else if(npcid == CAVEHOWLER)
		{
			st.rollAndGive(CH_SKULL, 1, 1, 100, DROP_CHANCE_ITEMS);
		}
		return null;
	}

}