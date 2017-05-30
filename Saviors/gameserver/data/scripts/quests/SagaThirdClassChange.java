package quests;

import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ThreadPoolManager;
import studio.lineage2.gameserver.ai.CtrlEvent;
import studio.lineage2.gameserver.model.GameObjectsStorage;
import studio.lineage2.gameserver.model.base.ClassId;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;
import studio.lineage2.gameserver.network.l2.s2c.MagicSkillUse;

import java.util.ArrayList;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

/**
 * Created by Averen on 22.02.2017
 */
public abstract class SagaThirdClassChange extends Quest
{
    //NPC
    private NpcInstance Angel;
    private NpcInstance Leona;

    private int classId;
    private int QuestRewardBook;
    private int secondClass;


    protected int[] NPC = new int[]{
            0,
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8
    };

    protected int[] Items = new int[]{
            0,
            1,
            2,
            3,
            4,
            5,
            6
    };

    protected int[] Mobs = new int[]{
            0,
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13
    };

    ArrayList<String[]> dialogs;

    abstract String DifferentTalk(NpcInstance npc, QuestState qs);

    abstract String DifferentEvent(String event, QuestState qs, NpcInstance npc);

    abstract String DifferentKill(NpcInstance npc, QuestState qs);

    protected String getDialog(int npcId, int dialogId)
    {
        return NPC[npcId] + "/" + dialogs.get(npcId)[dialogId-1];
    }

    private boolean isNPCSpawned(NpcInstance npc)
    {
        return GameObjectsStorage.findObject(npc.getObjectId()) != null;
    }

    //LISTS
    private static final String A_LIST = "A_LIST";
    private static final String B_LIST = "B_LIST";

    void addQuestParams(ClassId secondClass, ClassId thirdClass, int questRewardBook)
    {
        addStartNpc(NPC[0]);
        addTalkId(NPC);
        addQuestItem(Items);
        addLevelCheck(76, 80);
        this.secondClass = secondClass.getId();
        addClassIdCheck(secondClass);
        addKillId(Mobs);
        addKillNpcWithLog(7, A_LIST, 20, Mobs[6]);
        addKillNpcWithLog(9, B_LIST, 1, Mobs[7]);
        classId = thirdClass.getId();
        QuestRewardBook = questRewardBook;
    }

    SagaThirdClassChange()
    {
        super(PARTY_NONE, ONETIME);
    }

    @Override public String onTalk(NpcInstance npc, QuestState qs)
    {
        if(DifferentTalk(npc, qs) != null)
             return DifferentTalk(npc, qs);

        if(npc.getNpcId() == NPC[0])
        {
            if(qs.getPlayer().getClassId().getId() != secondClass)
            {
                qs.abortQuest();
                return "noquest";
            }

            if(qs.getCond() == 0)
                return getDialog(0, 1);
            else if(qs.getCond() == 1)
                return getDialog(0, 4);
            else if(qs.getCond() == 18)
                return getDialog(0, 6);
            else if (qs.getCond() == 19)
                if(qs.getPlayer().getLevel() < 76)
                {
                    return getDialog(0, 10);
                }
                else
                {
                    qs.setCond(20);
                    return getDialog(0, 9);
                }
        }

        else if(npc.getNpcId() == NPC[1])
        {
            if(qs.getCond() == 1)
                return getDialog(1, 1);
            else if(qs.getCond() == 2)
                return getDialog(1, 2);
            else if(qs.getCond() == 5)
                return getDialog(1, 3);
            else if(qs.getCond() == 6)
                return getDialog(1, 4);
        }
        else if(npc.getNpcId() == NPC[2])
        {
            if(qs.getCond() == 2)
                return getDialog(2, 1);
            else if(qs.getCond() == 3)
                return getDialog(2, 2);
            else if(qs.getCond() == 4)
                return getDialog(2, 3);
            else if(qs.getCond() == 5)
                return getDialog(2, 4);
        }
        else if(npc.getNpcId() == NPC[3])
        {
            if(qs.getCond() == 11)
                return getDialog(3, 1);
            else if(qs.getCond() == 12)
                return getDialog(3, 3);
        }
        else if(npc.getNpcId() == NPC[4])
        {

            if(qs.getCond() == 6)
                return getDialog(4, 1);
            else if(qs.getCond() == 7)
                return getDialog(4, 2);
        }
        else if(npc.getNpcId() == NPC[5])
        {
            if (qs.getCond() == 8)
                return getDialog(5, 1);
            else if (qs.getCond() == 9)
                return getDialog(5, 2);
            else if (qs.getCond() == 10)
                return getDialog(5, 3);
            else if (qs.getCond() == 11)
                return getDialog(5, 4);
            else
                return getDialog(5, 5);
        }
        else if(npc.getNpcId() == NPC[6])
        {
            if(qs.getCond() == 14)
                return getDialog(6, 1);
            else if(qs.getCond() == 15)
                return getDialog(6, 2);
        }
        else if(npc.getNpcId() == NPC[7])
        {
            if(qs.getQuestItemsCount(Items[5]) != 1 && qs.getCond() == 16)
                qs.setCond(15);
            if(qs.getCond() == 15)
                return getDialog(7, 2);
            else if(qs.getCond() == 16)
                return getDialog(7, 3);
            else if(qs.getCond() == 17)
                return getDialog(7, 1);
        }
        else if(npc.getNpcId() == NPC[8])
        {
            if(qs.getCond() == 15 && (!isNPCSpawned(Angel) || Angel.isDead()))
                return getDialog(8, 5);
            else if(qs.getCond() == 15)
                return getDialog(8, 1);
            else if(qs.getCond() == 16)
                return getDialog(8, 2);
            else if(qs.getCond() == 17)
                return getDialog(8, 4);
        }
        return null;
    }
    @Override public String onEvent(String event, QuestState qs, NpcInstance npc)
    {
        if(DifferentEvent(event, qs, npc) != null)
            return DifferentEvent(event, qs, npc);

        String command = event;
        if(npc.getNpcId() == NPC[0])
        {
            switch (event)
            {
                case "accept":
                    command = getDialog(0, 3);
                    qs.setCond(1);
                    break;
                case "0-1":
                    if(qs.getPlayer().getLevel() < 76)
                    {
                        command = getDialog(0, 2);
                        qs.abortQuest();
                    }
                    else
                    {
                        command = getDialog(0, 5);
                    }
                    break;
                case "0-2":
                    if(qs.getPlayer().getLevel() >= 76)
                    {
                        if(qs.getCond() == 18)
                        {
                            command = getDialog(0, 7);
                            // TODO: 26.02.2017 Абнормал получения профессии
                            //qs.getPlayer().broadcastPacket(new MagicSkillUse(qs.getPlayer(), qs.getPlayer(), 4339, 1, 6000, 1));

                            qs.getPlayer().setClassId(classId, true);
                            qs.getPlayer().addExpAndSp(3100000, 103000);
                            qs.giveItems(QuestRewardBook,1);
                            qs.finishQuest();
                        }
                    }
                    else
                    {
                        qs.setCond(19);
                        command = getDialog(0, 8);
                    }
                    break;
            }
        }
        if (npc.getNpcId() == NPC[1])
        {
            switch (event)
            {
                case "2-1":
                    command = getDialog(1, 5);
                    qs.setCond(2);
                    break;
                case "2-2":
                    qs.takeItems(Items[0], 1);
                    qs.giveItems(Items[2], 1);
                    qs.setCond(6);
                    command = getDialog(1, 6);
                    break;
            }
        }

        if (npc.getNpcId() == NPC[2])
        {
            switch(event)
            {
                case "1-3":
                    command = getDialog(2, 5);
                    qs.setCond(3);
                    break;

                case "1-4":
                    command = getDialog(2, 6);
                    qs.takeAllItems(Items[1]);
                    qs.giveItems(Items[0], 1);
                    qs.setCond(5);
                    break;
            }
        }

        if(npc.getNpcId() == NPC[3])
        {
            switch (event)
            {
                case "11-9":
                    command = getDialog(3, 2);
                    qs.setCond(12);
                    break;
            }
        }

        if (npc.getNpcId() == NPC[4])
        {
            switch(event)
            {
                case "4-1":
                    if(qs.getCond() == 6)
                    {
                        if(qs.getQuestItemsCount(Items[2]) < 1)
                        {
                            command = "no-amulet.htm";
                        }
                        else
                        {
                            command = getDialog(4, 2);
                            qs.takeItems(Items[2], 1);
                            qs.setCond(7);
                        }
                    }
                    break;
            }
        }
        if (npc.getNpcId() == NPC[5])
        {
            switch(event)
            {
                case "5-1":
                    if(qs.getCond() == 8)
                    {
                        if(qs.getQuestItemsCount(Items[3]) < 1)
                        {
                            command = "no-amulet.htm";
                        }
                        else
                        {
                            command = getDialog(5, 2);
                            qs.takeItems(Items[3], 1);
                            qs.addSpawn(Mobs[7], npc.getX() + 100, npc.getY() + 100, npc.getZ(), 600000).getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, new Object[]{qs.getPlayer(), 100000});
                            qs.setCond(9);
                        }
                    }
                    break;
                case "5-2":
                    if(qs.getCond() == 10)
                    {
                        if(qs.getQuestItemsCount(Items[3]) < 1)
                        {
                            command = "no-amulet.htm";
                        }
                        else
                        {
                            command = getDialog(5, 4);
                            qs.takeItems(Items[3], 1);
                            qs.setCond(11);
                        }

                    }
                    break;
            }
        }

        if (npc.getNpcId() == NPC[6])
        {
            switch(event)
            {
                case "6-1":
                    if(qs.getCond() == 14)
                    {
                        if(qs.getQuestItemsCount(Items[4]) < 1)
                        {
                            command = "no-amulet.htm";
                        }
                        else
                        {
                            command = getDialog(6, 2);
                            qs.takeAllItems(Items[4]);
                            qs.setCond(15);
                            break;
                        }
                    }
            }
        }

        if(npc.getNpcId() == NPC[7])
        {
            switch(event)
            {
                case "7-1":
                    if(qs.getCond() == 15 || qs.getCond() == 16)
                    {
                        command = getDialog(7, 3);
                            if (Leona != null && isNPCSpawned(Leona))
                            {
                                Leona.deleteMe();
                                Angel.deleteMe();
                            }

                            Leona = qs.addSpawn(NPC[8], npc.getX() + 100, npc.getY() + 100, npc.getZ(), 5 * 60 * 1000);
                            Angel = qs.addSpawn(Mobs[13], npc.getX() + 150, npc.getY() + 150, npc.getZ(), 5 * 60 * 1000);

                            // TODO: [Averen] костыль, переделать, кто-то сломал сражения между нпс
                            ThreadPoolManager.getInstance().execute(() ->
                            {
                                try
                                {
                                    while (isNPCSpawned(Angel) && !Angel.isDead())
                                    {
                                        Thread.sleep(150);
                                        Angel.doAttack(Leona);
                                        Leona.doAttack(Angel);
                                        if(Angel.getCurrentHp() <= Angel.getMaxHp() * 0.15)
                                        {
                                            if(Angel.getAggroList().get(qs.getPlayer()) == null)
                                                Angel.deleteMe();
                                        }
                                    }
                                    if (Angel.getAggroList().get(qs.getPlayer()) != null && Angel.getAggroList().get(qs.getPlayer()).damage > 0)
                                    {
                                        qs.setCond(16);
                                    }

                                }
                                catch (Exception ignored)
                                {
                                    ignored.printStackTrace();
                                }
                            });
                    }
                    break;
                case "7-2":
                    if(qs.getCond() == 17)
                    {
                        if(qs.getQuestItemsCount(Items[5]) < 1)
                        {
                            command = "no-amulet.htm";
                        }
                        else
                        {
                            if(Leona != null)
                                Leona.deleteMe();
                            qs.takeAllItems(Items[5]);
                            command = getDialog(7, 4);
                            qs.setCond(18);
                        }
                    }
                    break;
            }
        }
        if(npc.getNpcId() == NPC[8])
        {
            switch (event)
            {
                case "8-1":
                    if(qs.getCond() == 16)
                    {
                        command = getDialog(8, 3);
                        qs.giveItems(Items[5], 1);
                        qs.setCond(17);
                    }
                    break;
            }
        }


        return command;
    }
    @Override public String onKill(NpcInstance npc, QuestState qs)
    {
        if(DifferentKill(npc, qs) != null)
            return DifferentKill(npc, qs);

        if(qs.getCond() == 3)
        {
            if(qs.getQuestItemsCount(Items[1]) < 50)
            {
                if(Rnd.chance(50))
                {
                    qs.giveItems(Items[1], 1);

                    if(qs.getQuestItemsCount(Items[1]) >= 50)
                        qs.setCond(4);
                }
            }
        }
        switch(qs.getCond())
        {
            case 7:
                if (updateKill(npc, qs))
                {
                    qs.unset(A_LIST);
                    qs.giveItems(Items[3], 1);
                    qs.setCond(8);

                }
                break;
            case 9:
                if (updateKill(npc, qs))
                {
                    qs.unset(B_LIST);
                    qs.giveItems(Items[3],1);
                    qs.setCond(10);
                }
                break;

            case 12:
                if(qs.getQuestItemsCount(Items[6]) < 700)
                {
                    if(Rnd.chance(90))
                    {
                        qs.giveItems(Items[6], 1);
                    }
                }
                else
                {
                    qs.setCond(13);
                    qs.takeAllItems(Items[6]);
                    qs.addSpawn(Mobs[12], npc.getX()+100, npc.getY()+100, npc.getZ()).getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, new Object[]{
                            qs.getPlayer(),
                            100000
                    });
                }
                break;
            case 13:
                if(npc.getNpcId() == Mobs[12])
                {
                    qs.giveItems(Items[4], 1);
                    qs.setCond(14);
                }
                break;
            case 15:
                if(npc.getNpcId() == Mobs[13])
                {
                    qs.setCond(16);
                }
                break;
            case 16:
                //
                break;
        }
        return null;
    }
}



