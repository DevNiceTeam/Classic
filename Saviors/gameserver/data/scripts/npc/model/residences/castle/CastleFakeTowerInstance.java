package npc.model.residences.castle;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;

/**
 * @Author: Death
 * @Date: 17/9/2007
 * @Time: 19:11:50
 *
 * Этот инстанс просто для отрисовки умершей вышки на месте оригинальной на осаде
 * Фэйковый инстанс неуязвим.
 */
public class CastleFakeTowerInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	public CastleFakeTowerInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	/**
	 * Фэйковые вышки нельзя атаковать
	 */
	@Override
	public boolean isAutoAttackable(Creature player)
	{
		return false;
	}

	/**
	 * Вышки не умеют говорить
	 */
	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{}

	/**
	 * Вышки не умеют говорить
	 */
	@Override
	public void showChatWindow(Player player, String filename, boolean firstTalk, Object... replace)
	{}

	@Override
	public boolean hasRandomAnimation()
	{
		return false;
	}
}