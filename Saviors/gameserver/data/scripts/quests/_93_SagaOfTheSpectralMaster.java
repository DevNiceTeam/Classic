package quests;

import studio.lineage2.gameserver.model.base.ClassId;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.QuestState;

import java.util.ArrayList;

/**
 * Created by Averen on 22.02.2017.
 */
public class _93_SagaOfTheSpectralMaster extends SagaThirdClassChange
{
    //TODO [Averen] переписать все диалоги
    public _93_SagaOfTheSpectralMaster()
    {

        NPC = new int[]{
                30175,
                31631,
                30476,
                31631,
                31646,
                31647,
                31652,
                31657,
                31632
        };

        Items = new int[]{
                7606,
                49827,
                7291,
                7322,
                7353,
                7384,
                7508,
        };

        Mobs = new int[]{
                27316, // same
                27317, // same
                27318, // same
                27319, // same
                27320, // same
                27321, // same
                27214, // same
                27315,
                21650, // same
                21651, // same
                21652, // same
                21653, // same
                27242,
                27312  // same
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

        addQuestParams(ClassId.PHANTOM_SUMMONER, ClassId.SPECTRAL_MASTER, 90038);

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
