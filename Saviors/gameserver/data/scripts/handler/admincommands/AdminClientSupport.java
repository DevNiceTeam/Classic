package handler.admincommands;

import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.xml.holder.ItemHolder;
import studio.lineage2.gameserver.data.xml.holder.NpcHolder;
import studio.lineage2.gameserver.data.xml.holder.SkillHolder;
import studio.lineage2.gameserver.model.GameObject;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.items.ItemInstance;
import studio.lineage2.gameserver.network.l2.components.SystemMsg;
import studio.lineage2.gameserver.network.l2.s2c.SystemMessagePacket;
import studio.lineage2.gameserver.skills.SkillEntry;
import studio.lineage2.gameserver.templates.item.ItemTemplate;
import studio.lineage2.gameserver.templates.npc.NpcTemplate;
import studio.lineage2.gameserver.utils.ItemFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminClientSupport extends ScriptAdminCommand
{
	private static final Logger _log = LoggerFactory.getLogger(AdminClientSupport.class);

	public enum Commands
	{
		admin_setskill,
		admin_summon
	}

	@Override public boolean useAdminCommand(Enum<?> comm, String[] wordList, String fullString, Player player)
	{
		Commands c = (Commands) comm;
		GameObject target = player.getTarget();
		switch(c)
		{
			case admin_setskill:
				if(wordList.length != 4)
				{
					return false;
				}

				if(!player.getPlayerAccess().CanEditChar)
				{
					return false;
				}

				if(target == null || !target.isPlayer())
				{
					target = player;
				}

				try
				{
					SkillEntry skillEntry = SkillHolder.getInstance().getSkillEntry(Integer.parseInt(wordList[1]), Integer.parseInt(wordList[2]));
					target.getPlayer().addSkill(skillEntry, true);
					target.getPlayer().sendSkillList();
					target.getPlayer().sendPacket(new SystemMessagePacket(SystemMsg.YOU_HAVE_EARNED_S1_SKILL).addSkillName(skillEntry.getId(), skillEntry.getLevel()));
				}
				catch(NumberFormatException e)
				{
					_log.info("AdminClientSupport:useAdminCommand(Enum,String[],String,L2Player): " + e, e);
					return false;
				}
				break;
			case admin_summon:
				if(wordList.length != 3)
				{
					return false;
				}

				if(!player.getPlayerAccess().CanEditChar)
				{
					return false;
				}
				try
				{
					int id = Integer.parseInt(wordList[1]);
					long count = Long.parseLong(wordList[2]);

					if(id >= 1000000)
					{
						if(target == null)
						{
							target = player;
						}

						NpcTemplate template = NpcHolder.getInstance().getTemplate(id - 1000000);

						for(int i = 0; i < count; i++)
						{
							NpcInstance npc = template.getNewInstance();
							npc.setSpawnedLoc(target.getLoc());
							npc.setCurrentHpMp(npc.getMaxHp(), npc.getMaxMp(), true);

							npc.spawnMe(npc.getSpawnedLoc());
						}
					}
					else
					{
						if(target == null || !target.isPlayer())
						{
							target = player;
						}

						ItemTemplate template = ItemHolder.getInstance().getTemplate(id);
						if(template == null)
						{
							return false;
						}

						if(Config.DEBUG_SERVER)
						{
							System.out.println("<item><ingredient id=\"57\" count=\"1\"/><production id=\"" + template.getItemId() + "\" count=\"1\"/></item><!-- " + ItemHolder.getInstance().getName(template.getItemId()) + " -->");
						}
						else
						{
							if(template.isStackable())
							{
								ItemInstance item = ItemFunctions.createItem(id);
								item.setCount(count);

								target.getPlayer().getInventory().addItem(item);
								target.getPlayer().sendPacket(SystemMessagePacket.obtainItems(item));
							}
							else
							{
								for(int i = 0; i < count; i++)
								{
									ItemInstance item = ItemFunctions.createItem(id);

									target.getPlayer().getInventory().addItem(item);
									target.getPlayer().sendPacket(SystemMessagePacket.obtainItems(item));
								}
							}
						}
					}
				}
				catch(NumberFormatException e)
				{
					_log.info("AdminClientSupport:useAdminCommand(Enum,String[],String,L2Player): " + e, e);
					return false;
				}

				break;
		}
		return true;
	}

	@Override public Enum[] getAdminCommandEnum()
	{
		return Commands.values();
	}
}