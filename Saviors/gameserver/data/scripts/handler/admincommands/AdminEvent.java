package handler.admincommands;

import studio.lineage2.commons.map.hash.TIntStringHashMap;
import studio.lineage2.gameserver.data.htm.HtmCache;
import studio.lineage2.gameserver.data.xml.holder.EventHolder;
import studio.lineage2.gameserver.instancemanager.ServerVariables;
import studio.lineage2.gameserver.model.GameObject;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.entity.events.Event;
import studio.lineage2.gameserver.model.entity.events.EventType;
import studio.lineage2.gameserver.model.entity.events.impl.PvPEvent;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;

import java.util.List;

public class AdminEvent extends ScriptAdminCommand
{
	enum Commands
	{
		admin_events, admin_list_events, admin_start_event, admin_stop_event, admin_start_event_pvp
	}

	@Override public boolean useAdminCommand(Enum<?> comm, String[] wordList, String fullString, Player activeChar)
	{
		Commands c = (Commands) comm;
		switch(c)
		{
			case admin_events:
				if(wordList.length == 1)
				{
					final TIntStringHashMap tpls = HtmCache.getInstance().getTemplates("admin/events/events.htm", activeChar);

					String html = tpls.get(0);

					StringBuilder eventsList = new StringBuilder();

					final String eventBlock = tpls.get(1);
					final String startButton = tpls.get(2);
					final String stopButton = tpls.get(3);

					List<PvPEvent> eventsPvP = EventHolder.getInstance().getEvents(PvPEvent.class);
					for(PvPEvent event : eventsPvP)
					{
						String block = eventBlock;
						block = block.replace("<?event_name?>", event.getName());

						String button = event.isInProgress() ? "" : startButton;
						button = event.isInProgress() ? "" : button.replace("<?command?>", "admin_start_event_pvp");
						button = button.replace("<?event_id?>", String.valueOf(event.getId()));

						block = block.replace("<?button?>", button);

						eventsList.append(block);
					}

					List<Event> events = EventHolder.getInstance().getEvents(EventType.CUSTOM_EVENT);
					for(Event event : events)
					{
						String block = eventBlock;
						block = block.replace("<?event_name?>", event.getName());

						String button = event.isInProgress() ? stopButton : startButton;
						button = event.isInProgress() ? button.replace("<?command?>", "admin_stop_event") : button.replace("<?command?>", "admin_start_event");
						button = button.replace("<?event_id?>", String.valueOf(event.getId()));

						block = block.replace("<?button?>", button);

						eventsList.append(block);
					}

					html = html.replace("<?events_list?>", eventsList.toString());

					activeChar.sendPacket(new HtmlMessage(5).setHtml(html));
				}
				else
				{
					activeChar.sendPacket(new HtmlMessage(5).setFile("admin/events/" + wordList[1].trim()));
				}
				break;
			case admin_start_event_pvp:
				if(wordList.length < 2)
				{
					activeChar.sendMessage("USAGE://" + wordList[0] + " ID");
					return false;
				}
				try
				{
					int id = Integer.parseInt(wordList[1]);

					PvPEvent event = EventHolder.getInstance().getEvent(EventType.PVP_EVENT, id);
					if(comm == Commands.admin_start_event_pvp)
					{
						if(!event.isInProgress())
						{
							event.startEvent();
							activeChar.sendMessage("Started event: " + event.getName());
						}
					}
				}
				catch(NumberFormatException e)
				{
					activeChar.sendMessage("USAGE://" + wordList[0] + " ID");
					return false;
				}
				break;
			case admin_start_event:
			case admin_stop_event:
				if(wordList.length < 2)
				{
					activeChar.sendMessage("USAGE://" + wordList[0] + " ID");
					return false;
				}
				try
				{
					int id = Integer.parseInt(wordList[1]);

					Event e = EventHolder.getInstance().getEvent(EventType.CUSTOM_EVENT, id);
					if(comm == Commands.admin_start_event)
					{
						if(!e.isInProgress())
						{
							ServerVariables.set(e.getName(), (int) (System.currentTimeMillis() / 1000));
							e.startEvent();
							activeChar.sendMessage("Started event: " + e.getName());
						}
					}
					else
					{
						if(e.isInProgress())
						{
							ServerVariables.unset(e.getName());
							e.stopEvent();
							activeChar.sendMessage("Finished event: " + e.getName());
						}
					}
				}
				catch(NumberFormatException e)
				{
					activeChar.sendMessage("USAGE://" + wordList[0] + " ID");
					return false;
				}
				break;
			case admin_list_events:
				GameObject object = activeChar.getTarget();
				if(object == null)
				{
					activeChar.sendPacket(SystemMsg.INVALID_TARGET);
				}
				else
				{
					for(Event e : object.getEvents())
					{
						activeChar.sendMessage("- " + e.toString());
					}
				}
				break;
		}
		return false;
	}

	@Override public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}