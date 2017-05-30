package quests;

import studio.lineage2.gameserver.model.base.ClassId;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.QuestState;

import java.util.ArrayList;

/**
 * Created by Averen on 22.02.2017.
 */
public class _70_SagaOfThePhoenixKnight extends SagaThirdClassChange
{
    //TODO [Averen] переписать все диалоги
    public _70_SagaOfThePhoenixKnight()
    {
        NPC = new int[]{
                30849,
                31277,
                34268,
                31277,
                31646,
                31647,
                31651,
                31654,
                31631
        };

        Items = new int[]{
                7534,
                49804,
                7268,
                7299,
                7330,
                7361,
                7485,
        };

        Mobs = new int[]{
                27316, // same
                27317, // same
                27318, // same
                27319, // same
                27320, // same
                27321, // same
                27214, // same
                27286,
                21650, // same
                21651, // same
                21652, // same
                21653, // same
                27219,
                27282  // same
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
        // TODO: 06.03.2017 [Averen] переписать основной диалог нпц
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
                "2-1.htm",
                "2-2.htm",
                "2-3.htm"
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

        addQuestParams(ClassId.PALADIN, ClassId.PHOENIX_KNIGHT, 90038);

    }

    @Override
    String DifferentTalk(NpcInstance npc, QuestState qs)
    {
        if(npc.getNpcId() == NPC[1])
        {
            if(qs.getCond() == 1)
                return getDialog(1, 1);
            else if(qs.getCond() == 2)
                return getDialog(1, 2);
            else if(qs.getCond() == 5)
                return getDialog(1, 3);
            else if(qs.getCond() == 6)
                return getDialog(1, 4);
            else if(qs.getCond() == 11)
                return getDialog(3, 1);
            else if(qs.getCond() == 12)
                return getDialog(3, 3);
        }
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
