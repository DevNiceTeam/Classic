package npc.model;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.commons.util.Rnd;
import studio.lineage2.gameserver.ai.CtrlIntention;
import studio.lineage2.gameserver.model.Creature;
import studio.lineage2.gameserver.model.Skill;
import studio.lineage2.gameserver.model.instances.BossInstance;
import studio.lineage2.gameserver.network.l2.s2c.PlaySoundPacket;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.Location;

public class OrfenInstance extends BossInstance
{
	private static final long serialVersionUID = 1L;

	public static final Location nest = new Location(43728, 17220, -4342);

	public static final Location[] locs = new Location[] {
			new Location(55024, 17368, -5412),
			new Location(53504, 21248, -5496),
			new Location(53248, 24576, -5272)
	};

	public OrfenInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	public void setTeleported(boolean flag)
	{
		super.setTeleported(flag);
		Location loc = flag ? nest : locs[Rnd.get(locs.length)];
		setSpawnedLoc(loc);
		getAggroList().clear(true);
		getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE, null, null);
		teleToLocation(loc);
	}

	@Override
	protected void onSpawn()
	{
		super.onSpawn();
		setTeleported(false);
		broadcastPacketToOthers(new PlaySoundPacket(PlaySoundPacket.Type.MUSIC, "BS01_A", 1, 0, getLoc()));
	}

	@Override
	protected void onDeath(Creature killer)
	{
		broadcastPacketToOthers(new PlaySoundPacket(PlaySoundPacket.Type.MUSIC, "BS02_D", 1, 0, getLoc()));
		super.onDeath(killer);
	}

	@Override
	public void reduceCurrentHp(double damage, Creature attacker, Skill skill, boolean awake, boolean standUp, boolean directHp, boolean canReflectAndAbsorb, boolean transferDamage, boolean isDot, boolean sendReceiveMessage, boolean sendGiveMessage, boolean crit, boolean miss, boolean shld, boolean magic)
	{
		super.reduceCurrentHp(damage, attacker, skill, awake, standUp, directHp, canReflectAndAbsorb, transferDamage, isDot, sendReceiveMessage, sendGiveMessage, crit, miss, shld, magic);
		if(!isTeleported() && getCurrentHpPercents() <= 50)
		{
			setTeleported(true);
		}
	}
}