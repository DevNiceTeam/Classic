package quests;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.model.base.Race;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _105_SkirmishWithOrcs extends Quest
{
	//NPC
	private static final int Kendell = 30218;
	//QuestItem
	private static final int Kendells1stOrder = 1836;
	private static final int Kendells2stOrder = 1837;
	private static final int Kendells3stOrder = 1838;
	private static final int Kendells4stOrder = 1839;
	private static final int Kendells5stOrder = 1840;
	private static final int Kendells6stOrder = 1841;
	private static final int Kendells7stOrder = 1842;
	private static final int Kendells8stOrder = 1843;
	private static final int KabooChiefs1stTorque = 1844;
	private static final int KabooChiefs2stTorque = 1845;
	private static final int RED_SUNSET_SWORD = 49046;
	private static final int RED_SUNSET_STAFF = 49045;
	//Item
	private static final int NEWBIE_SOULSHOT = 5789; // Заряд Души: Без Ранга для Новичков
	private static final int NEWBIE_SPIRITSHOT = 5790; // Заряд Духа: Без Ранга для Новичков
	//NPC
	private static final int KabooChiefUoph = 27059;
	private static final int KabooChiefKracha = 27060;
	private static final int KabooChiefBatoh = 27061;
	private static final int KabooChiefTanukia = 27062;
	private static final int KabooChiefTurel = 27064;
	private static final int KabooChiefRoko = 27065;
	private static final int KabooChiefKamut = 27067;
	private static final int KabooChiefMurtika = 27068;

	public _105_SkirmishWithOrcs()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(Kendell);

		addKillId(KabooChiefUoph);
		addKillId(KabooChiefKracha);
		addKillId(KabooChiefBatoh);
		addKillId(KabooChiefTanukia);
		addKillId(KabooChiefTurel);
		addKillId(KabooChiefRoko);
		addKillId(KabooChiefKamut);
		addKillId(KabooChiefMurtika);

		addQuestItem(new int[]
		{
				Kendells1stOrder,
				Kendells2stOrder,
				Kendells3stOrder,
				Kendells4stOrder,
				Kendells5stOrder,
				Kendells6stOrder,
				Kendells7stOrder,
				Kendells8stOrder,
				KabooChiefs1stTorque,
				KabooChiefs2stTorque });

		addLevelCheck(10, 15);
		addRaceCheck(false, true, false, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		if(event.equalsIgnoreCase("sentinel_kendnell_q0105_03.htm"))
		{
			st.setCond(1);
			if(st.getQuestItemsCount(Kendells1stOrder) + st.getQuestItemsCount(Kendells2stOrder) + st.getQuestItemsCount(Kendells3stOrder) + st.getQuestItemsCount(Kendells4stOrder) == 0)
			{
				int n = Rnd.get(4);
				if(n == 0)
					st.giveItems(Kendells1stOrder, 1);
				else if(n == 1)
					st.giveItems(Kendells2stOrder, 1);
				else if(n == 2)
					st.giveItems(Kendells3stOrder, 1);
				else
					st.giveItems(Kendells4stOrder, 1);
			}
		}
		return event;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int cond = st.getCond();
		if(cond == 0)
		{
			if(checkStartCondition(st.getPlayer()))
				htmltext = "sentinel_kendnell_q0105_02.htm";
			else if(st.getPlayer().getRace() != Race.ELF)
				htmltext = "sentinel_kendnell_q0105_00.htm";
			else
				htmltext = "sentinel_kendnell_q0105_10.htm";
		}
		else if(cond == 1)
			htmltext = "sentinel_kendnell_q0105_05.htm";
		else if(cond == 2)
		{
			htmltext = "sentinel_kendnell_q0105_06.htm";
			st.takeItems(Kendells1stOrder, -1);
			st.takeItems(Kendells2stOrder, -1);
			st.takeItems(Kendells3stOrder, -1);
			st.takeItems(Kendells4stOrder, -1);
			st.takeItems(KabooChiefs1stTorque, 1);
			int n = Rnd.get(4);
			if(n == 0)
				st.giveItems(Kendells5stOrder, 1);
			else if(n == 1)
				st.giveItems(Kendells6stOrder, 1);
			else if(n == 2)
				st.giveItems(Kendells7stOrder, 1);
			else
				st.giveItems(Kendells8stOrder, 1);

			st.setCond(3);
		}
		else if(cond == 3)
			htmltext = "sentinel_kendnell_q0105_07.htm";
		else if(cond == 4)
		{
			htmltext = "sentinel_kendnell_q0105_08.htm";
			st.takeItems(Kendells5stOrder, -1);
			st.takeItems(Kendells6stOrder, -1);
			st.takeItems(Kendells7stOrder, -1);
			st.takeItems(Kendells8stOrder, -1);
			st.takeItems(KabooChiefs2stTorque, -1);

			if(st.getPlayer().getClassId().isMage())
			{
				st.giveItems(NEWBIE_SPIRITSHOT, 1500);
				st.giveItems(RED_SUNSET_STAFF, 1);
			}
			else
			{
				st.giveItems(NEWBIE_SOULSHOT, 3000);
				st.giveItems(RED_SUNSET_SWORD, 1);
			}
			st.finishQuest();
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		int cond = st.getCond();
		if(cond == 1 && st.getQuestItemsCount(KabooChiefs1stTorque) == 0)
		{
			if(npcId == KabooChiefUoph && st.getQuestItemsCount(Kendells1stOrder) > 0)
				st.giveItems(KabooChiefs1stTorque, 1);
			else if(npcId == KabooChiefKracha && st.getQuestItemsCount(Kendells2stOrder) > 0)
				st.giveItems(KabooChiefs1stTorque, 1);
			else if(npcId == KabooChiefBatoh && st.getQuestItemsCount(Kendells3stOrder) > 0)
				st.giveItems(KabooChiefs1stTorque, 1);
			else if(npcId == KabooChiefTanukia && st.getQuestItemsCount(Kendells4stOrder) > 0)
				st.giveItems(KabooChiefs1stTorque, 1);
			if(st.getQuestItemsCount(KabooChiefs1stTorque) > 0)
				st.setCond(2);
		}
		else if(cond == 3 && st.getQuestItemsCount(KabooChiefs2stTorque) == 0)
		{
			if(npcId == KabooChiefTurel && st.getQuestItemsCount(Kendells5stOrder) > 0)
				st.giveItems(KabooChiefs2stTorque, 1);
			else if(npcId == KabooChiefRoko && st.getQuestItemsCount(Kendells6stOrder) > 0)
				st.giveItems(KabooChiefs2stTorque, 1);
			else if(npcId == KabooChiefKamut && st.getQuestItemsCount(Kendells7stOrder) > 0)
				st.giveItems(KabooChiefs2stTorque, 1);
			else if(npcId == KabooChiefMurtika && st.getQuestItemsCount(Kendells8stOrder) > 0)
				st.giveItems(KabooChiefs2stTorque, 1);
			if(st.getQuestItemsCount(KabooChiefs2stTorque) > 0)
				st.setCond(4);
		}
		return null;
	}
}