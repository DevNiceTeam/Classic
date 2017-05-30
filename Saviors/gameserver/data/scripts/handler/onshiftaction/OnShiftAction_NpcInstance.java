package handler.onshiftaction;

import handler.onshiftaction.commons.RewardListInfo;
import org.apache.commons.lang3.text.StrBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import studio.lineage2.gameserver.Config;
import studio.lineage2.gameserver.data.xml.parser.NpcParser;
//import studio.lineage2.gameserver.extensions.impl.сommunity.CommunityManager;
import studio.lineage2.gameserver.handler.bypass.Bypass;
import studio.lineage2.gameserver.model.AggroList;
import studio.lineage2.gameserver.model.GameObjectsStorage;
import studio.lineage2.gameserver.model.Player;
import studio.lineage2.gameserver.model.SimpleSpawner;
import studio.lineage2.gameserver.model.Spawner;
import studio.lineage2.gameserver.model.actor.instances.creature.Effect;
import studio.lineage2.gameserver.model.entity.events.Event;
import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestEventType;
import studio.lineage2.gameserver.network.l2.components.CustomMessage;
import studio.lineage2.gameserver.network.l2.components.HtmlMessage;
import studio.lineage2.gameserver.network.l2.components.NpcString;
import studio.lineage2.gameserver.skills.SkillEntry;
import studio.lineage2.gameserver.stats.Stats;
import studio.lineage2.gameserver.utils.HtmlUtils;
import studio.lineage2.gameserver.utils.Location;
import studio.lineage2.gameserver.utils.PositionUtils;
import studio.lineage2.gameserver.utils.Util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class OnShiftAction_NpcInstance extends ScriptOnShiftActionHandler<NpcInstance>
{
	private static boolean addResist(StrBuilder dialog, String name, double val)
	{
		if(val == 0)
		{
			return false;
		}

		dialog.append("<tr><td>").append(name).append("</td><td>");
		if(val == Double.POSITIVE_INFINITY)
		{
			dialog.append("MAX");
		}
		else if(val == Double.NEGATIVE_INFINITY)
		{
			dialog.append("MIN");
		}
		else
		{
			dialog.append(String.valueOf((int) val));
			dialog.append("</td></tr>");
			return true;
		}

		dialog.append("</td></tr>");
		return true;
	}

	private static String getNpcRaceById(int raceId)
	{
		switch(raceId)
		{
			case 1:
				return "Undead";
			case 2:
				return "Magic Creatures";
			case 3:
				return "Beasts";
			case 4:
				return "Animals";
			case 5:
				return "Plants";
			case 6:
				return "Humanoids";
			case 7:
				return "Spirits";
			case 8:
				return "Angels";
			case 9:
				return "Demons";
			case 10:
				return "Dragons";
			case 11:
				return "Giants";
			case 12:
				return "Bugs";
			case 13:
				return "Fairies";
			case 14:
				return "Humans";
			case 15:
				return "Elves";
			case 16:
				return "Dark Elves";
			case 17:
				return "Orcs";
			case 18:
				return "Dwarves";
			case 19:
				return "Others";
			case 20:
				return "Non-living Beings";
			case 21:
				return "Siege Weapons";
			case 22:
				return "Defending Army";
			case 23:
				return "Mercenaries";
			case 24:
				return "Unknown Creature";
			case 25:
				return "Kamael";
			default:
				return "Not defined";
		}
	}

	private static String nameNpc(NpcInstance npc)
	{
		if(npc.getNameNpcString() == NpcString.NONE)
		{
			return HtmlUtils.htmlNpcName(npc.getNpcId());
		}
		else
		{
			return HtmlUtils.htmlNpcString(npc.getNameNpcString().getId(), npc.getName());
		}
	}

	@Override public Class<NpcInstance> getClazz()
	{
		return NpcInstance.class;
	}

	@Override public boolean call(NpcInstance npc, Player player)
	{
		return showMain(player, npc, player.isGM());
	}

	@Bypass("actions.OnActionShift:showShort") public void showShort(Player player, NpcInstance npc, String[] par)
	{
		showMain(player, npc, false);
	}

	private boolean showMain(Player player, NpcInstance npc, boolean full)
	{
		if(npc == null)
		{
			return false;
		}

		// Для мертвых мобов не показываем табличку, иначе спойлеры плачут
		if((npc.noShiftClick() || npc.isDead()) && !player.isGM())
		{
			return false;
		}

		if(!Config.ALLOW_NPC_SHIFTCLICK && !player.isGM())
		{
			if(Config.ALT_GAME_SHOW_DROPLIST)
			{
				droplist(player, npc, null);
				return true;
			}
			return false;
		}

		HtmlMessage msg = new HtmlMessage(0);
		msg.setFile("scripts/actions/player.L2NpcInstance.onActionShift." + (full ? "full.htm" : "htm"));

		if(full)
		{
			msg.replace("%class%", String.valueOf(npc.getClass().getSimpleName()));
			msg.replace("%id%", String.valueOf(npc.getNpcId()));
			msg.replace("%respawn%", String.valueOf(npc.getSpawn() != null ? Util.formatTime(npc.getSpawn().getRespawnDelay()) : "0"));
			msg.replace("%walkSpeed%", String.valueOf(npc.getWalkSpeed()));
			msg.replace("%pevs%", String.valueOf(npc.getPEvasionRate(null)));
			msg.replace("%pacc%", String.valueOf(npc.getPAccuracy()));
			msg.replace("%mevs%", String.valueOf(npc.getMEvasionRate(null)));
			msg.replace("%macc%", String.valueOf(npc.getMAccuracy()));
			msg.replace("%pcrt%", String.valueOf(npc.getPCriticalHit(null)));
			msg.replace("%mcrt%", String.valueOf(npc.getMCriticalHit(null, null)));
			msg.replace("%aspd%", String.valueOf(npc.getPAtkSpd()));
			msg.replace("%cspd%", String.valueOf(npc.getMAtkSpd()));
			msg.replace("%currentMP%", String.valueOf(npc.getCurrentMp()));
			msg.replace("%currentHP%", String.valueOf(npc.getCurrentHp()));
			msg.replace("%loc%", npc.getSpawn() == null ? "" : npc.getSpawn().getName());
			msg.replace("%dist%", String.valueOf((int) npc.getDistance3D(player)));
			msg.replace("%killed%", String.valueOf(0));//TODO [G1ta0] убрать
			msg.replace("%spReward%", String.valueOf(npc.getSpReward()));
			msg.replace("%xyz%", npc.getLoc().x + " " + npc.getLoc().y + " " + npc.getLoc().z);
			msg.replace("%xyzOriginal%", npc.getSpawnedLoc().x + " " + npc.getSpawnedLoc().y + " " + npc.getSpawnedLoc().z);
			msg.replace("%ai_type%", npc.getAI().getClass().getSimpleName());
			msg.replace("%direction%", PositionUtils.getDirectionTo(npc, player).toString().toLowerCase());
			msg.replace("%respawn%", String.valueOf(npc.getSpawn() != null ? Util.formatTime(npc.getSpawn().getRespawnDelay()) : "0"));
			msg.replace("%factionId%", String.valueOf(npc.getFaction()));
			msg.replace("%aggro%", String.valueOf(npc.getAggroRange()));
			msg.replace("%pDef%", String.valueOf(npc.getPDef(null)));
			msg.replace("%mDef%", String.valueOf(npc.getMDef(null, null)));
			msg.replace("%pAtk%", String.valueOf(npc.getPAtk(null)));
			msg.replace("%mAtk%", String.valueOf(npc.getMAtk(null, null)));
			msg.replace("%runSpeed%", String.valueOf(npc.getRunSpeed()));

			// Дополнительная инфа для ГМов
			if(player.isGM())
			{
				msg.replace("%AI%", String.valueOf(npc.getAI()) + ",<br1>active: " + npc.getAI().isActive() + ",<br1>intention: " + npc.getAI().getIntention());
			}
			else
			{
				msg.replace("%AI%", "");
			}

			StrBuilder b = new StrBuilder("");
			for(Event e : npc.getEvents())
			{
				b.append(e.toString()).append(";");
			}

			msg.replace("%event%", b.toString());
		}

		msg.replace("<?npc_name?>", nameNpc(npc));
		msg.replace("<?id?>", String.valueOf(npc.getNpcId()));
		msg.replace("<?level?>", String.valueOf(npc.getLevel()));
		msg.replace("<?max_hp?>", String.valueOf(npc.getMaxHp()));
		msg.replace("<?max_mp?>", String.valueOf(npc.getMaxMp()));
		msg.replace("<?xp_reward?>", String.valueOf(npc.getExpReward()));
		msg.replace("<?sp_reward?>", String.valueOf(npc.getSpReward()));
		msg.replace("<?aggresive?>", new CustomMessage(npc.getAggroRange() > 0 ? "YES" : "NO").toString(player));

		player.sendPacket(msg);
		return true;
	}

	@Bypass("actions.OnActionShift:droplist") public void droplist(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null || !npc.isMonster())
		{
			return;
		}

		if(Config.ALT_GAME_SHOW_DROPLIST || player.isGM())
		{
			RewardListInfo.SendRewardInfo(player, npc, par);
		}
	}

	@Bypass("actions.OnActionShift:reload") public void reload(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		NpcParser.getInstance().reload();

		int npcId = npc.getNpcId();
		Location location = npc.getLoc();
		int respawnDelay = npc.getSpawn().getRespawnDelay();

		npc.deleteMe();

		SimpleSpawner simpleSpawner = new SimpleSpawner(npcId);
		simpleSpawner.setLoc(location);
		simpleSpawner.setRespawnDelay(respawnDelay);
		simpleSpawner.doSpawn(true);
	}

	@Bypass("actions.OnActionShift:reloadAll") public void reloadAll(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		NpcParser.getInstance().reload();

		int npcId = npc.getNpcId();

		List<NpcInstance> npcInstances = new ArrayList<>();
		for(NpcInstance npcInstance : GameObjectsStorage.getAllByNpcId(npcId, false))
		{
			npcInstances.add(npcInstance);
		}

		for(NpcInstance npcInstance : npcInstances)
		{
			if(npcInstance.getSpawn() == null)
			{
				continue;
			}

			Location location = npcInstance.getLoc();
			int respawnDelay = npcInstance.getSpawn().getRespawnDelay();

			npcInstance.deleteMe();

			SimpleSpawner simpleSpawner = new SimpleSpawner(npcId);
			simpleSpawner.setLoc(location);
			simpleSpawner.setRespawnDelay(respawnDelay);
			simpleSpawner.doSpawn(true);
		}
	}

	public void respawnDir(String dir, Player player, NpcInstance npc, int x, int y, int z)
	{
		for(File file : new File(dir).listFiles())
		{
			if(file.isDirectory())
			{
				continue;
			}

			boolean save = false;

			try
			{
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				Document document = dbf.newDocumentBuilder().parse(file);

				XPathFactory xpf = XPathFactory.newInstance();
				XPath xpath = xpf.newXPath();
				XPathExpression expression = xpath.compile("/list/spawn/npc[contains(@id,'" + npc.getNpcId() + "')]");

				NodeList nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

				if(nodes == null)
				{
					continue;
				}

				for(int i = 0; i < nodes.getLength(); i++)
				{
					Node spawn = nodes.item(i).getParentNode();
					NodeList nodeList = spawn.getChildNodes();
					for(int j = 0; j < nodeList.getLength(); j++)
					{
						Node node = nodeList.item(j);
						if(node.getNodeName().equals("point"))
						{
							int curx = Integer.parseInt(node.getAttributes().getNamedItem("x").getNodeValue());
							int cury = Integer.parseInt(node.getAttributes().getNamedItem("y").getNodeValue());
							int curz = Integer.parseInt(node.getAttributes().getNamedItem("z").getNodeValue());

							if(curx == x && cury == y && curz == z)
							{
								node.getAttributes().getNamedItem("x").setNodeValue(String.valueOf(player.getX()));
								node.getAttributes().getNamedItem("y").setNodeValue(String.valueOf(player.getY()));
								node.getAttributes().getNamedItem("z").setNodeValue(String.valueOf(player.getZ()));
								node.getAttributes().getNamedItem("h").setNodeValue(String.valueOf(player.getHeading()));

								int respawnDelay = npc.getSpawn().getRespawnDelay();

								npc.deleteMe();

								SimpleSpawner simpleSpawner = new SimpleSpawner(npc.getNpcId());
								simpleSpawner.setLoc(player.getLoc());
								simpleSpawner.setHeading(player.getHeading());
								simpleSpawner.setRespawnDelay(respawnDelay);
								simpleSpawner.doSpawn(true);

								save = true;
							}
						}
					}
				}

				if(save)
				{
					TransformerFactory tf = TransformerFactory.newInstance();
					Transformer t = tf.newTransformer();
					t.setOutputProperty(OutputKeys.INDENT, "yes");
					t.setOutputProperty(OutputKeys.METHOD, "xml");
					t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
					t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
					t.transform(new DOMSource(document), new StreamResult(file));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Bypass("actions.OnActionShift:respawn") public void respawn(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		int x = npc.getSpawnedLoc().x;
		int y = npc.getSpawnedLoc().y;
		int z = npc.getSpawnedLoc().z;

		respawnDir("./data/zone_and_spawn/spawn", player, npc, x, y, z);
	}

	public void deleteDir(String dir, NpcInstance npc, int x, int y, int z)
	{
		for(File file : new File(dir).listFiles())
		{
			if(file.isDirectory())
			{
				continue;
			}

			boolean save = false;

			try
			{
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				Document document = dbf.newDocumentBuilder().parse(file);

				XPathFactory xpf = XPathFactory.newInstance();
				XPath xpath = xpf.newXPath();
				XPathExpression expression = xpath.compile("/list/spawn/npc[contains(@id,'" + npc.getNpcId() + "')]");

				NodeList nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

				if(nodes == null)
				{
					continue;
				}

				for(int i = 0; i < nodes.getLength(); i++)
				{
					Node spawn = nodes.item(i).getParentNode();
					NodeList nodeList = spawn.getChildNodes();
					for(int j = 0; j < nodeList.getLength(); j++)
					{
						Node node = nodeList.item(j);
						if(node.getNodeName().equals("point"))
						{
							int curx = Integer.parseInt(node.getAttributes().getNamedItem("x").getNodeValue());
							int cury = Integer.parseInt(node.getAttributes().getNamedItem("y").getNodeValue());
							int curz = Integer.parseInt(node.getAttributes().getNamedItem("z").getNodeValue());

							if(curx == x && cury == y && curz == z)
							{
								spawn.getParentNode().removeChild(spawn);
								npc.deleteMe();
								Spawner s = npc.getSpawn();
								if(s != null)
								{
									s.stopRespawn();
								}
								save = true;
							}
						}
					}
				}

				expression = xpath.compile("//text()[normalize-space(.) = '']");
				nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
				if(nodes != null)
				{
					for(int iSpace = 0; iSpace < nodes.getLength(); iSpace++)
					{
						nodes.item(iSpace).getParentNode().removeChild(nodes.item(iSpace));
					}
				}

				if(save)
				{
					TransformerFactory tf = TransformerFactory.newInstance();
					Transformer t = tf.newTransformer();
					t.setOutputProperty(OutputKeys.INDENT, "yes");
					t.setOutputProperty(OutputKeys.METHOD, "xml");
					t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
					t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
					t.transform(new DOMSource(document), new StreamResult(file));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Bypass("actions.OnActionShift:delete") public void delete(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		int x = npc.getSpawnedLoc().x;
		int y = npc.getSpawnedLoc().y;
		int z = npc.getSpawnedLoc().z;

		deleteDir("./data/zone_and_spawn/spawn", npc, x, y, z);
	}

	@Bypass("actions.OnActionShift:kill") public void kill(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		npc.getAggroList().addDamageHate(player, (int) npc.getCurrentHp(), 0);
		npc.doDie(player);
	}

	@Bypass("actions.OnActionShift:stats") public void stats(Player player, NpcInstance npc, String[] par)
	{
		if(npc == null)
		{
			return;
		}

		HtmlMessage msg = new HtmlMessage(npc);
		msg.setFile("scripts/actions/player.L2NpcInstance.stats.htm");

		msg.replace("%name%", nameNpc(npc));
		msg.replace("%level%", String.valueOf(npc.getLevel()));
		msg.replace("%factionId%", String.valueOf(npc.getFaction()));
		msg.replace("%aggro%", String.valueOf(npc.getAggroRange()));
		msg.replace("%race%", getNpcRaceById(npc.getTemplate().getRace()));
		msg.replace("%maxHp%", String.valueOf(npc.getMaxHp()));
		msg.replace("%maxMp%", String.valueOf(npc.getMaxMp()));
		msg.replace("%pDef%", String.valueOf(npc.getPDef(null)));
		msg.replace("%mDef%", String.valueOf(npc.getMDef(null, null)));
		msg.replace("%pAtk%", String.valueOf(npc.getPAtk(null)));
		msg.replace("%mAtk%", String.valueOf(npc.getMAtk(null, null)));
		msg.replace("%paccuracy%", String.valueOf(npc.getPAccuracy()));
		msg.replace("%pevasionRate%", String.valueOf(npc.getPEvasionRate(null)));
		msg.replace("%pcriticalHit%", String.valueOf(npc.getPCriticalHit(null)));
		msg.replace("%maccuracy%", String.valueOf(npc.getMAccuracy()));
		msg.replace("%mevasionRate%", String.valueOf(npc.getMEvasionRate(null)));
		msg.replace("%mcriticalHit%", String.valueOf(npc.getMCriticalHit(null, null)));
		msg.replace("%runSpeed%", String.valueOf(npc.getRunSpeed()));
		msg.replace("%walkSpeed%", String.valueOf(npc.getWalkSpeed()));
		msg.replace("%pAtkSpd%", String.valueOf(npc.getPAtkSpd()));
		msg.replace("%mAtkSpd%", String.valueOf(npc.getMAtkSpd()));

		player.sendPacket(msg);
	}

	@Bypass("actions.OnActionShift:quests") public void quests(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		StrBuilder dialog = new StrBuilder("<html><body><center><font color=\"LEVEL\">");
		dialog.append(nameNpc(npc)).append("<br></font></center><br>");

		Map<QuestEventType, Set<Quest>> list = npc.getTemplate().getQuestEvents();
		for(Map.Entry<QuestEventType, Set<Quest>> entry : list.entrySet())
		{
			for(Quest q : entry.getValue())
			{
				dialog.append(entry.getKey()).append(" ").append(q.getClass().getSimpleName()).append("<br1>");
			}
		}

		dialog.append("</body></html>");

		HtmlMessage msg = new HtmlMessage(npc);
		msg.setHtml(dialog.toString());
		player.sendPacket(msg);
	}

	@Bypass("actions.OnActionShift:skills") public void skills(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		StrBuilder dialog = new StrBuilder("<html><body><center><font color=\"LEVEL\">");
		dialog.append(nameNpc(npc)).append("<br></font></center>");

		Collection<SkillEntry> list = npc.getAllSkills();
		if(list != null && !list.isEmpty())
		{
			dialog.append("<br><font color=\"LEVEL\">Active:</font><br>");
			for(SkillEntry s : list)
			{
				if(s.getTemplate().isActive())
				{
					dialog.append(s.getName()).append(" <font color=\"LEVEL\">Id: ").append(s.getId()).append(" Level: ").append(s.getLevel()).append("</font><br1>");
				}
			}

			dialog.append("<br><font color=\"LEVEL\">Passive:</font><br>");
			for(SkillEntry s : list)
			{
				if(!s.getTemplate().isActive())
				{
					dialog.append(s.getName()).append(" <font color=\"LEVEL\">Id: ").append(s.getId()).append(" Level: ").append(s.getLevel()).append("</font><br1>");
				}
			}
		}

		dialog.append("</body></html>");

		HtmlMessage msg = new HtmlMessage(npc);
		msg.setHtml(dialog.toString());
		player.sendPacket(msg);
	}

	@Bypass("actions.OnActionShift:effects") public void effects(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		StrBuilder dialog = new StrBuilder("<html><body><center><font color=\"LEVEL\">");
		dialog.append(nameNpc(npc)).append("<br></font></center><br>");

		for(Effect e : npc.getEffectList().getEffects())
		{
			dialog.append(e.getSkill().getName()).append("<br1>");
		}

		dialog.append("<br><center><button value=\"");
		dialog.append(player.isLangRus() ? "Обновить" : "Refresh");
		dialog.append("\" action=\"bypass -h htmbypass_actions.OnActionShift:effects\" width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" /></center></body></html>");

		HtmlMessage msg = new HtmlMessage(npc);
		msg.setHtml(dialog.toString());
		player.sendPacket(msg);
	}

	@Bypass("actions.OnActionShift:resists") public void resists(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		StrBuilder dialog = new StrBuilder("<html><body><center><font color=\"LEVEL\">");
		dialog.append(nameNpc(npc)).append("<br></font></center><table width=\"80%\">");

		boolean hasResist;

		hasResist = addResist(dialog, "Bleed", npc.calcStat(Stats.BLEED_RESIST, 0, null, null));
		hasResist |= addResist(dialog, "Poison", npc.calcStat(Stats.POISON_RESIST, 0, null, null));
		hasResist |= addResist(dialog, "Stun", npc.calcStat(Stats.STUN_RESIST, 0, null, null));
		hasResist |= addResist(dialog, "Root", npc.calcStat(Stats.ROOT_RESIST, 0, null, null));
		hasResist |= addResist(dialog, "Sleep", npc.calcStat(Stats.SLEEP_RESIST, 0, null, null));
		hasResist |= addResist(dialog, "Paralyze", npc.calcStat(Stats.PARALYZE_RESIST, 0, null, null));
		hasResist |= addResist(dialog, "Mental", npc.calcStat(Stats.MENTAL_RESIST, 0, null, null));
		hasResist |= addResist(dialog, "Debuff", npc.calcStat(Stats.DEBUFF_RESIST, 0, null, null));
		hasResist |= addResist(dialog, "Cancel", npc.calcStat(Stats.CANCEL_RESIST, 0, null, null));
		hasResist |= addResist(dialog, "Sword", 100 - npc.calcStat(Stats.swordWpnVuln, null, null));
		hasResist |= addResist(dialog, "Dual Sword", 100 - npc.calcStat(Stats.dualWpnVuln, null, null));
		hasResist |= addResist(dialog, "Blunt", 100 - npc.calcStat(Stats.bluntWpnVuln, null, null));
		hasResist |= addResist(dialog, "Dagger", 100 - npc.calcStat(Stats.daggerWpnVuln, null, null));
		hasResist |= addResist(dialog, "Bow", 100 - npc.calcStat(Stats.bowWpnVuln, null, null));
		hasResist |= addResist(dialog, "Polearm", 100 - npc.calcStat(Stats.poleWpnVuln, null, null));
		hasResist |= addResist(dialog, "Fist", 100 - npc.calcStat(Stats.fistWpnVuln, null, null));

		if(!hasResist)
		{
			dialog.append("</table>No resists</body></html>");
		}
		else
		{
			dialog.append("</table></body></html>");
		}

		HtmlMessage msg = new HtmlMessage(npc);
		msg.setHtml(dialog.toString());
		player.sendPacket(msg);
	}

	@Bypass("actions.OnActionShift:aggro") public void aggro(Player player, NpcInstance npc, String[] par)
	{
		if(player == null || npc == null)
		{
			return;
		}

		StrBuilder dialog = new StrBuilder("<html><body><table width=\"80%\"><tr><td>Attacker</td><td>Damage</td><td>Hate</td></tr>");

		Set<AggroList.HateInfo> set = new TreeSet<AggroList.HateInfo>(AggroList.HateComparator.getInstance());
		set.addAll(npc.getAggroList().getCharMap().values());
		for(AggroList.HateInfo aggroInfo : set)
		{
			dialog.append("<tr><td>").append(aggroInfo.attacker.getName()).append("</td><td>").append(aggroInfo.damage).append("</td><td>").append(aggroInfo.hate).append("</td></tr>");
		}

		dialog.append("</table><br><center><button value=\"");
		dialog.append(player.isLangRus() ? "Обновить" : "Refresh");
		dialog.append("\" action=\"bypass -h htmbypass_actions.OnActionShift:aggro\" width=100 height=15 back=\"L2UI_CT1.Button_DF_Down\" fore=\"L2UI_CT1.Button_DF\" /></center></body></html>");

		HtmlMessage msg = new HtmlMessage(npc);
		msg.setHtml(dialog.toString());
		player.sendPacket(msg);
	}
}