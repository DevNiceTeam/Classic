package npc.model;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.network.l2.components.SceneMovie;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;

/**
 * @author Bonux
 */
public class LeonaInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	public LeonaInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		if(command.startsWith("listen"))
		{
			player.startScenePlayer(SceneMovie.SCENE_HELLBOUND);
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}
}