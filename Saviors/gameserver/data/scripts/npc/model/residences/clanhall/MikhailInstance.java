package npc.model.residences.clanhall;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;

/**
 * @author VISTALL
 * @date 20:05/07.05.2011
 * 35409
 */
public class MikhailInstance extends _34BossMinionInstance
{
	private static final long serialVersionUID = 1L;

	public MikhailInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	public NpcString spawnChatSay()
	{
		return NpcString.GLORY_TO_ADEN_THE_KINGDOM_OF_THE_LION_GLORY_TO_SIR_GUSTAV_OUR_IMMORTAL_LORD;
	}

	@Override
	public NpcString teleChatSay()
	{
		return NpcString.COULD_IT_BE_THAT_I_HAVE_REACHED_MY_END_I_CANNOT_DIE_WITHOUT_HONOR_WITHOUT_THE_PERMISSION_OF_SIR_GUSTAV;
	}
}
