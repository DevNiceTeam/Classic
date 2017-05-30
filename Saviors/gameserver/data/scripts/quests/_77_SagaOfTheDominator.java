package quests;

import studio.lineage2.gameserver.model.base.ClassId;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.QuestState;

import java.util.ArrayList;

/**
 * Created by Averen on 22.02.2017.
 */
public class _77_SagaOfTheDominator extends SagaThirdClassChange
{
    //TODO [Averen] переписать все диалоги
    public _77_SagaOfTheDominator()
    {

        NPC = new int[]{
                30867,
                34265,
                34268,
                30706,
                31646,
                31649,
                31651,
                31653,
                31636
        };

        Items = new int[]{
                49842,
                49811,
                7275,
                7306,
                7337,
                7368,
                7492,
        };

        Mobs = new int[]{
                27316, // same
                27317, // same
                27318, // same
                27319, // same
                27320, // same
                27321, // same
                27216, // same
                27294,
                21650, // same
                21651, // same
                21652, // same
                21653, // same
                27226,
                27262  // same 62
        };

        // Индекс массива с диалогами равен индексу НПЦ id
        dialogs = new ArrayList<>();
        dialogs.add(0, new String[]{
                "1.htm",
                "2.htm",
                "3.htm",
                "4.htm",
                "5.htm",
                "6.htm",
                "7.htm",
                "8.htm",
                "9.htm",
                "10.htm"
        });
        dialogs.add(1, new String[]{
                "1.htm",
                "2.htm",
                "3.htm",
                "4.htm",
                "5.htm",
                "6.htm"
        });
        dialogs.add(2, new String[]{
                "1.htm",
                "2.htm",
                "3.htm",
                "4.htm",
                "5.htm",
                "6.htm"
        });
        dialogs.add(3, new String[]{
                "1.htm",
                "2.htm",
                "3.htm"
        });

        dialogs.add(4, new String[]{
                "1.htm",
                "2.htm"
        });
        dialogs.add(5, new String[]{
                "1.htm",
                "2.htm",
                "3.htm",
                "4.htm",
                "5.htm"
        });
        dialogs.add(6, new String[]{
                "1.htm",
                "2.htm"
        });
        dialogs.add(7, new String[]{
                "1.htm",
                "2.htm",
                "3.htm",
                "4.htm"
        });
        dialogs.add(8, new String[]{
                "1.htm",
                "2.htm",
                "3.htm",
                "4.htm",
                "5.htm"
        });

        addQuestParams(ClassId.OVERLORD, ClassId.DOMINATOR, 90038);

    }
    @Override
    String DifferentTalk(NpcInstance npc, QuestState qs)
    {
        return null;
    }

    @Override
    String DifferentEvent(String event, QuestState qs, NpcInstance npc)
    {
        return null;
    }

    @Override
    String DifferentKill(NpcInstance npc, QuestState qs)
    {
        return null;
    }
}
