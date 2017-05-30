package services.community;

import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.handler.bbs.CommunityBoardHolder;
import studio.lineage2.gameserver.handler.bbs.CommunityBoard;
import studio.lineage2.gameserver.listener.script.OnInitScriptListener;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.Zone;
import studio.lineage2.gameserver.network.l2.s2c.ShowBoardPacket;

/**
 * @author Bonux
 **/
public abstract class ScriptsCommunityHandler implements CommunityBoard, OnInitScriptListener
{
    @Override
    public void onInit()
    {
        if(Config.COMMUNITYBOARD_ENABLED)
            CommunityBoardHolder.getInstance().registerHandler(this);
    }

    boolean CheckCondition(Player player)
    {
        if(player == null)
            return false;

        if(player.isDead())
            return false;

        if(!Config.ALLOW_COMMUNITYBOARD_IN_COMBAT && player.isInCombat())
        {
            if (player.isLangRus())
                player.sendMessage("В режиме боя использовать запрещено.");
            else
                player.sendMessage("Can not use in battle.");
            return false;
        }

        if(!Config.ALLOW_COMMUNITYBOARD_IS_IN_SIEGE && player.isInZone(Zone.ZoneType.SIEGE))
        {
            if (player.isLangRus())
                player.sendMessage("В зоне, находящейся в осаде, использовать запрещено.");
            else
                player.sendMessage("In the zone, located in the siege, use prohibited.");
            return false;
        }

        if(!Config.ALLOW_COMMUNITYBOARD_IN_COMBAT && (player.isInDuel() || player.isInCombat() || player.isAttackingNow()))
        {
            if (player.isLangRus())
                player.sendMessage("Во время боя нельзя использовать данную функцию.");
            else
                player.sendMessage("During combat, you can not use this feature.");
            return false;
        }

        if(player.isInOlympiadMode())
        {
            player.sendMessage(player.isLangRus() ? "Во время олимпийского боя нельзя использовать данную функцию." : "During the Olympic battle you can not use this feature.");
            return false;
        }
        return true;
    }

    protected static String GetStringCount(long count)
    {
        String scount = Long.toString(count);
        if (count < 1000)
            return scount;
        if ((count > 999) && (count < 1000000))
            return scount.substring(0, scount.length() - 3) + "к";
        if ((count > 999999) && (count < 1000000000))
            return scount.substring(0, scount.length() - 6) + "кк";
        if (count > 999999999)
            return scount.substring(0, scount.length() - 9) + "ккк";
        if (count == 0)
            return "00.00";
        return "ERROR";
    }

    protected String getProffesionNameById(int id)
    {
        String names[] =
                {
                        "Воин",
                        "Воитель",
                        "Гладиатор",
                        "Копейщик",
                        "Рыцарь",
                        "Паладин",
                        "Мститель",
                        "Разбойник",
                        "Искатель Сокровищь",
                        "Стрелок",
                        "Мистик",
                        "Маг",
                        "Волшебник",
                        "Некромант",
                        "Колдун",
                        "Клерик",
                        "Епископ",
                        "Проповедник",
                        "Светлый Воин",
                        "Светлый Рыцарь",
                        "Рыцарь Евы",
                        "Менестрель",
                        "Разведчик",
                        "Следопыт",
                        "Серебрянный Рейнджер",
                        "Светлый Мистик",
                        "Светлый Маг",
                        "Певец Заклинаний",
                        "Последователь Стихий",
                        "Оракул Евы",
                        "Мудрец Евы",
                        "Темный Воин",
                        "Темный Рыцарь",
                        "Рыцарь Шилен",
                        "Танцор Смерти",
                        "Ассасин",
                        "Странник Бездны",
                        "Призрачный Рейнджер",
                        "Темный Мистик",
                        "Темный Маг",
                        "Заклинатель Ветра",
                        "Последователь Тьмы",
                        "Оракл Шилен",
                        "Мудрец Шилен",
                        "Орк Боец",
                        "Налетчик",
                        "Разрушитель",
                        "Монах",
                        "Отшельник",
                        "Адепт",
                        "Шаман",
                        "Верховный Шаман",
                        "Вестник Войны",
                        "Подмастерье",
                        "Собиратель",
                        "Охотник За Наградой",
                        "Ремесленник",
                        "Кузнец",
                        "Мультибафер",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        "Дуелист",
                        "Полководец",
                        "Рыцарь Феникса",
                        "Рыцарь Фда",
                        "Снайпер",
                        "Авантюрист",
                        "Архимаг",
                        "Пожиратель Душ",
                        "Еретик",
                        "Кардинал",
                        "Апостол",
                        "Храмовник Евы",
                        "Виртуоз",
                        "Странник Ветра",
                        "Страж Лун Света",
                        "Магистр Магии",
                        "Мастер Стихий",
                        "Жрец Евы",
                        "Храмовник Шилен",
                        "Призрачный Танцор",
                        "Призрачный Охотник",
                        "Страж Теней",
                        "Повелитель Бури",
                        "Владыка Теней",
                        "Жрец Шилен",
                        "Титан",
                        "Аватар",
                        "Деспот",
                        "Пророк",
                        "Кладоискатель",
                        "Мастер",
                };
        return names[id];
    }

    void onWrongCondition(Player player)
    {
        player.sendMessage(player.isLangRus() ? "Не соблюдены условия для использование данной функции." : "You are not allowed to use this action in you current stance.");
        player.sendPacket(ShowBoardPacket.CLOSE);
    }

    @Override
    public void onBypassCommand(Player player, String bypass)
    {
        if(!CheckCondition(player))
        {
            onWrongCondition(player);
            return;
        }

        doBypassCommand(player, bypass);
    }

    @Override
    public void onWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5)
    {
        if(!CheckCondition(player))
        {
            onWrongCondition(player);
            return;
        }

        doWriteCommand(player, bypass, arg1, arg2, arg3, arg4, arg5);
    }

    protected void doBypassCommand(Player player, String bypass)
    {
        //
    }

    protected void doWriteCommand(Player player, String bypass, String arg1, String arg2, String arg3, String arg4, String arg5)
    {
        //
    }

}

