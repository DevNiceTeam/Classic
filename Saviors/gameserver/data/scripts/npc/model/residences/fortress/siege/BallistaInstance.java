package npc.model.residences.fortress.siege;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.network.l2.s2c.SystemMessagePacket;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;

/**
 * Данный инстанс используется NPC Ballista на осадах фортов
 * @author SYS
 */
public class BallistaInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	public BallistaInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	protected void onDeath(Creature killer)
	{
		super.onDeath(killer);

		if(killer == null || !killer.isPlayer())
		{
			return;
		}

		Player player = killer.getPlayer();
		if(player.getClan() == null)
		{
			return;
		}

		player.getClan().incReputation(30, false, "Ballista " + getTitle());
		player.sendPacket(new SystemMessagePacket(SystemMsg.THE_BALLISTA_HAS_BEEN_SUCCESSFULLY_DESTROYED));
	}

	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		return true;
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{}

	@Override
	public boolean isPeaceNpc()
	{
		return false;
	}

	@Override
	public boolean isFearImmune()
	{
		return true;
	}
}