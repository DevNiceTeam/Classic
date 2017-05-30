package npc.model;

import studio.lineage2.commons.collections.MultiValueSet;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.Location;

import java.util.StringTokenizer;

/**
 * @author Bonux
 */
public class HerbCollectorInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	private static final String TELEPORT_LOC_VAR = "teleport_loc";

	private Location _teleportLoc = null;

	public HerbCollectorInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	protected void onSpawn()
	{
		String locPar = getParameter(TELEPORT_LOC_VAR, null);
		if(locPar != null)
		{
			_teleportLoc = Location.parseLoc(locPar);
		}
	}

	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if(!canBypassCheck(player, this))
		{
			return;
		}

		StringTokenizer st = new StringTokenizer(command, "_");
		String cmd = st.nextToken();
		if(cmd.equalsIgnoreCase("liftmeup"))
		{
			if(_teleportLoc != null)
			{
				player.teleToLocation(_teleportLoc);
			}
			else
			{
				player.sendMessage("Teleport var is null. Inform about this to administration!");
			}
		}
		else
		{
			super.onBypassFeedback(player, command);
		}
	}
}
