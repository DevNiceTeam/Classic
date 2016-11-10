package quests;

import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

/**
 * @reworked by Bonux
**/
public final class _3_WilltheSealbeBroken extends Quest
{
	// NPC's
	private static final int TALOS_HIERARCH = 30141; // Таллот - Тетрарх

	// Monster's
	private static final int ONYX_BEAST = 20031; // Ониксовый Зверь
	private static final int CONTORTION_OF_LUNACY = 20041; // Гнилой Зомби
	private static final int STINK_ZOMBIE = 20046; // Зловонный Зомби
	private static final int LESSER_SUCCUBUS = 20048; // Младший Суккуб
	private static final int LESSER_SUCCUBUS_TUREN = 20052; // Младший Суккуб Турен
	private static final int LESSER_SUCCUBUS_TILFO = 20057; // Младший Суккуб Тилфо

	// Item's
	private static final int ONYX_BEASTS_EYE = 1081; // Глаз Зверя Знамения
	private static final int TAINT_STONE = 1082; // Камень Порока
	private static final int SUCCUBUS_BLOOD = 1083; // Кровь Суккуба

	public _3_WilltheSealbeBroken()
	{
		super(PARTY_NONE, ONETIME);

		addStartNpc(TALOS_HIERARCH);
		addKillId(ONYX_BEAST, CONTORTION_OF_LUNACY, STINK_ZOMBIE, LESSER_SUCCUBUS, LESSER_SUCCUBUS_TUREN, LESSER_SUCCUBUS_TILFO);
		addQuestItem(ONYX_BEASTS_EYE, TAINT_STONE, SUCCUBUS_BLOOD);
		addLevelCheck(16, 26);
		addRaceCheck(false, false, true, false, false);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("redry_q0003_03.htm"))
			st.setCond(1);
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int npcId = npc.getNpcId();
		int cond = st.getCond();
		switch(npcId)
		{
			case TALOS_HIERARCH:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "redry_q0003_02.htm";
					else if(st.getPlayer().getRace() != Race.DARKELF)
						htmltext = "redry_q0003_00.htm";
					else
						htmltext = "redry_q0003_01.htm";
				}
				else if(cond == 1)
					htmltext = "redry_q0003_04.htm";
				else if(cond == 2)
				{
					htmltext = "redry_q0003_06.htm";
					st.takeItems(ONYX_BEASTS_EYE, -1);
					st.takeItems(TAINT_STONE, -1);
					st.takeItems(SUCCUBUS_BLOOD, -1);
					st.giveItems(ADENA_ID, 3800);
					st.finishQuest();
				}
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		int cond = st.getCond();
		if(cond == 1)
		{
			boolean itemReceived = false;
			switch(npcId)
			{
				case ONYX_BEAST:
					if(!st.haveQuestItem(ONYX_BEASTS_EYE))
					{
						st.giveItems(ONYX_BEASTS_EYE, 1);
						itemReceived = true;
					}
					break;
				case CONTORTION_OF_LUNACY:
				case STINK_ZOMBIE:
					if(!st.haveQuestItem(TAINT_STONE))
					{
						st.giveItems(TAINT_STONE, 1);
						itemReceived = true;
					}
					break;
				case LESSER_SUCCUBUS:
				case LESSER_SUCCUBUS_TUREN:
				case LESSER_SUCCUBUS_TILFO:
					if(!st.haveQuestItem(SUCCUBUS_BLOOD))
					{
						st.giveItems(SUCCUBUS_BLOOD, 1);
						itemReceived = true;
					}
					break;
			}

			if(st.haveQuestItem(ONYX_BEASTS_EYE) && st.haveQuestItem(TAINT_STONE) && st.haveQuestItem(SUCCUBUS_BLOOD))
				st.setCond(2);
			else if(itemReceived)
				st.playSound(SOUND_ITEMGET);
		}
		return null;
	}
}