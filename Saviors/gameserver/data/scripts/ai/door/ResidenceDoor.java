package ai.door;

import studio.lineage2.gameserver.ai.DoorAI;
import studio.lineage2.gameserver.data.xml.holder.ResidenceHolder;
import studio.lineage2.gameserver.listener.actor.player.OnAnswerListener;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.residence.Residence;
import studio.lineage2.gameserver.model.instances.DoorInstance;
import studio.lineage2.gameserver.model.pledge.Clan;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.network.l2.s2c.ConfirmDlgPacket;

/**
 * @author VISTALL
 * @date 15:32/11.07.2011
 */
public class ResidenceDoor extends DoorAI
{
	public ResidenceDoor(DoorInstance actor)
	{
		super(actor);
	}

	@Override
	public void onEvtTwiceClick(final Player player)
	{
		final DoorInstance door = getActor();

		Residence residence = ResidenceHolder.getInstance().getResidence(door.getTemplate().getAIParams().getInteger("residence_id"));
		if(residence.getOwner() != null && player.getClan() != null && player.getClan() == residence.getOwner() && (player.getClanPrivileges() & Clan.CP_CS_ENTRY_EXIT) == Clan.CP_CS_ENTRY_EXIT)
		{
			SystemMsg msg = door.isOpen() ? SystemMsg.WOULD_YOU_LIKE_TO_CLOSE_THE_GATE : SystemMsg.WOULD_YOU_LIKE_TO_OPEN_THE_GATE;
			player.ask(new ConfirmDlgPacket(msg, 0), new OnAnswerListener()
			{
				@Override
				public void sayYes()
				{
					if(door.isOpen())
					{
						door.closeMe(player, true);
					}
					else
					{
						door.openMe(player, true);
					}
				}

				@Override
				public void sayNo()
				{
					//
				}
			});
		}
	}
}
