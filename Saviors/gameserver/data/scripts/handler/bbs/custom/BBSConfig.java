package handler.bbs.custom;

import l2s.commons.configuration.ExProperties;
import l2s.commons.string.StringArrayUtils;
import l2s.gameserver.Config;
import l2s.gameserver.listener.script.OnLoadScriptListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BBSConfig implements OnLoadScriptListener
{
	private static final Logger _log = LoggerFactory.getLogger(BBSConfig.class);

	private static final String PROPERTIES_FILE = "config/community/bbs.properties";

	public static boolean GLOBAL_USE_FUNCTIONS_CONFIGS;
	public static boolean CAN_USE_FUNCTIONS_WHEN_DEAD;
	public static boolean CAN_USE_FUNCTIONS_IN_A_BATTLE;
	public static boolean CAN_USE_FUNCTIONS_IN_PVP;
	public static boolean CAN_USE_FUNCTIONS_IF_FLIGHT;
	public static boolean CAN_USE_FUNCTIONS_IF_IN_VEHICLE;
	public static boolean CAN_USE_FUNCTIONS_IF_MOUNTED;
	public static boolean CAN_USE_FUNCTIONS_IF_CANNOT_MOVE;
	public static boolean CAN_USE_FUNCTIONS_WHEN_IN_TRADE;
	public static boolean CAN_USE_FUNCTIONS_IF_TELEPORTING;
	public static boolean CAN_USE_FUNCTIONS_WHEN_IS_PK;
	public static boolean CAN_USE_FUNCTIONS_CLAN_LEADERS_ONLY;
	public static boolean CAN_USE_FUNCTIONS_ON_SIEGE;
	public static boolean CAN_USE_FUNCTIONS_IN_PEACE_ZONE_ONLY;
	public static boolean CAN_USE_FUNCTIONS_IN_EVENTS;

	public static boolean BUFF_SERVICE_ALLOW_RESTORE;
	public static boolean BUFF_SERVICE_ALLOW_CANCEL_BUFFS;
	public static int BUFF_SERVICE_COST_ITEM_ID;
	public static long BUFF_SERVICE_COST_ITEM_COUNT;
	public static int BUFF_SERVICE_MAX_BUFFS_IN_SET;
	public static int BUFF_SERVICE_MAX_BUFF_SETS_PER_CHAR;
	public static int[] BUFF_SERVICE_AVAILABLE_SKILLS_FOR_BUFF;
	public static int BUFF_SERVICE_MAX_LEVEL_FOR_FREE_BUFF;
	public static int BUFF_SERVICE_ASSIGN_BUFF_TIME;
	public static int BUFF_SERVICE_ASSIGN_BUFF_TIME_MUSIC;
	public static int BUFF_SERVICE_ASSIGN_BUFF_TIME_SPECIAL;
	public static double BUFF_SERVICE_BUFF_TIME_MODIFIER;
	public static double BUFF_SERVICE_BUFF_TIME_MODIFIER_MUSIC;
	public static double BUFF_SERVICE_BUFF_TIME_MODIFIER_SPECIAL;

	public static int TELEPORT_SERVICE_COST_ITEM_ID;
	public static long TELEPORT_SERVICE_COST_ITEM_COUNT;
	public static int TELEPORT_SERVICE_BM_SAVE_COST_ITEM_ID;
	public static long TELEPORT_SERVICE_BM_SAVE_COST_ITEM_COUNT;
	public static int TELEPORT_SERVICE_BM_SAVE_LIMIT;
	public static int TELEPORT_SERVICE_BM_COST_ITEM_ID;
	public static long TELEPORT_SERVICE_BM_COST_ITEM_COUNT;
	public static boolean TELEPORT_SERVICE_TELEPORT_IF_PK;

	public static int OCCUPATION_SERVICE_COST_ITEM_ID_1;
	public static long OCCUPATION_SERVICE_COST_ITEM_COUNT_1;
	public static int OCCUPATION_SERVICE_COST_ITEM_ID_2;
	public static long OCCUPATION_SERVICE_COST_ITEM_COUNT_2;

	public static int CHANGE_PLAYER_NAME_SERVICE_COST_ITEM_ID;
	public static long CHANGE_PLAYER_NAME_SERVICE_COST_ITEM_COUNT;

	public static int CHANGE_PET_NAME_SERVICE_COST_ITEM_ID;
	public static long CHANGE_PET_NAME_SERVICE_COST_ITEM_COUNT;

	public static int CHANGE_CLAN_NAME_SERVICE_COST_ITEM_ID;
	public static long CHANGE_CLAN_NAME_SERVICE_COST_ITEM_COUNT;

	public static int COLOR_NAME_SERVICE_COST_ITEM_ID;
	public static long COLOR_NAME_SERVICE_COST_ITEM_COUNT;
	public static String[] COLOR_NAME_SERVICE_COLORS;

	public static int COLOR_TITLE_SERVICE_COST_ITEM_ID;
	public static long COLOR_TITLE_SERVICE_COST_ITEM_COUNT;
	public static String[] COLOR_TITLE_SERVICE_COLORS;

	@Override
	public void onLoad()
	{
		ExProperties properties = Config.load(PROPERTIES_FILE);

		// Global
		GLOBAL_USE_FUNCTIONS_CONFIGS = properties.getProperty("GLOBAL_USE_FUNCTIONS_CONFIGS", false);
		CAN_USE_FUNCTIONS_WHEN_DEAD = properties.getProperty("CAN_USE_FUNCTIONS_WHEN_DEAD", true);
		CAN_USE_FUNCTIONS_IN_A_BATTLE = properties.getProperty("CAN_USE_FUNCTIONS_IN_A_BATTLE", true);
		CAN_USE_FUNCTIONS_IN_PVP = properties.getProperty("CAN_USE_FUNCTIONS_IN_PVP", true);
		CAN_USE_FUNCTIONS_IF_FLIGHT = properties.getProperty("CAN_USE_FUNCTIONS_IF_FLIGHT", true);
		CAN_USE_FUNCTIONS_IF_IN_VEHICLE = properties.getProperty("CAN_USE_FUNCTIONS_IF_IN_VEHICLE", true);
		CAN_USE_FUNCTIONS_IF_MOUNTED = properties.getProperty("CAN_USE_FUNCTIONS_IF_MOUNTED", true);
		CAN_USE_FUNCTIONS_IF_CANNOT_MOVE = properties.getProperty("CAN_USE_FUNCTIONS_IF_CANNOT_MOVE", true);
		CAN_USE_FUNCTIONS_WHEN_IN_TRADE = properties.getProperty("CAN_USE_FUNCTIONS_WHEN_IN_TRADE", true);
		CAN_USE_FUNCTIONS_IF_TELEPORTING = properties.getProperty("CAN_USE_FUNCTIONS_IF_TELEPORTING", true);
		CAN_USE_FUNCTIONS_WHEN_IS_PK = properties.getProperty("CAN_USE_FUNCTIONS_WHEN_IS_PK", true);
		CAN_USE_FUNCTIONS_CLAN_LEADERS_ONLY = properties.getProperty("CAN_USE_FUNCTIONS_CLAN_LEADERS_ONLY", false);
		CAN_USE_FUNCTIONS_ON_SIEGE = properties.getProperty("CAN_USE_FUNCTIONS_ON_SIEGE", true);
		CAN_USE_FUNCTIONS_IN_PEACE_ZONE_ONLY = properties.getProperty("CAN_USE_FUNCTIONS_IN_PEACE_ZONE_ONLY", false);
		CAN_USE_FUNCTIONS_IN_EVENTS = properties.getProperty("CAN_USE_FUNCTIONS_IN_EVENTS", false);

		// Buff service
		BUFF_SERVICE_ALLOW_RESTORE = properties.getProperty("BUFF_SERVICE_ALLOW_RESTORE", true);
		BUFF_SERVICE_ALLOW_CANCEL_BUFFS = properties.getProperty("BUFF_SERVICE_ALLOW_CANCEL_BUFFS", true);
		BUFF_SERVICE_COST_ITEM_ID = properties.getProperty("BUFF_SERVICE_COST_ITEM_ID", 57);
		BUFF_SERVICE_COST_ITEM_COUNT = properties.getProperty("BUFF_SERVICE_COST_ITEM_COUNT", 1000L);
		BUFF_SERVICE_MAX_BUFFS_IN_SET = properties.getProperty("BUFF_SERVICE_MAX_BUFFS_IN_SET", 20);
		BUFF_SERVICE_MAX_BUFF_SETS_PER_CHAR = properties.getProperty("BUFF_SERVICE_MAX_BUFF_SETS_PER_CHAR", 8);
		BUFF_SERVICE_AVAILABLE_SKILLS_FOR_BUFF = StringArrayUtils.stringToIntArray(properties.getProperty("BUFF_SERVICE_AVAILABLE_SKILLS_FOR_BUFF", ""), ";");
		BUFF_SERVICE_MAX_LEVEL_FOR_FREE_BUFF = properties.getProperty("BUFF_SERVICE_MAX_LEVEL_FOR_FREE_BUFF", 84);
		BUFF_SERVICE_ASSIGN_BUFF_TIME = properties.getProperty("BUFF_SERVICE_ASSIGN_BUFF_TIME", 0);
		BUFF_SERVICE_ASSIGN_BUFF_TIME_MUSIC = properties.getProperty("BUFF_SERVICE_ASSIGN_BUFF_TIME_MUSIC", 0);
		BUFF_SERVICE_ASSIGN_BUFF_TIME_SPECIAL = properties.getProperty("BUFF_SERVICE_ASSIGN_BUFF_TIME_SPECIAL", 0);
		BUFF_SERVICE_BUFF_TIME_MODIFIER = properties.getProperty("BUFF_SERVICE_BUFF_TIME_MODIFIER", 1.0);
		BUFF_SERVICE_BUFF_TIME_MODIFIER_MUSIC = properties.getProperty("BUFF_SERVICE_BUFF_TIME_MODIFIER_MUSIC", 1.0);
		BUFF_SERVICE_BUFF_TIME_MODIFIER_SPECIAL = properties.getProperty("BUFF_SERVICE_BUFF_TIME_MODIFIER_SPECIAL", 1.0);

		// Teleport service
		TELEPORT_SERVICE_COST_ITEM_ID = properties.getProperty("TELEPORT_SERVICE_COST_ITEM_ID", 57);
		TELEPORT_SERVICE_COST_ITEM_COUNT = properties.getProperty("TELEPORT_SERVICE_COST_ITEM_COUNT", 10000);
		TELEPORT_SERVICE_BM_SAVE_COST_ITEM_ID = properties.getProperty("TELEPORT_SERVICE_BM_SAVE_COST_ITEM_ID", 57);
		TELEPORT_SERVICE_BM_SAVE_COST_ITEM_COUNT = properties.getProperty("TELEPORT_SERVICE_BM_SAVE_COST_ITEM_COUNT", 1000000);
		TELEPORT_SERVICE_BM_SAVE_LIMIT = properties.getProperty("TELEPORT_SERVICE_BM_SAVE_LIMIT", 10);
		TELEPORT_SERVICE_BM_COST_ITEM_ID = properties.getProperty("TELEPORT_SERVICE_BM_COST_ITEM_ID", 57);
		TELEPORT_SERVICE_BM_COST_ITEM_COUNT = properties.getProperty("TELEPORT_SERVICE_BM_COST_ITEM_COUNT", 100000);
		TELEPORT_SERVICE_TELEPORT_IF_PK = properties.getProperty("TELEPORT_SERVICE_TELEPORT_IF_PK", false);

		// Occupation purchase service
		OCCUPATION_SERVICE_COST_ITEM_ID_1 = properties.getProperty("OCCUPATION_SERVICE_COST_ITEM_ID_1", 57);
		OCCUPATION_SERVICE_COST_ITEM_COUNT_1 = properties.getProperty("OCCUPATION_SERVICE_COST_ITEM_COUNT_1", 10000L);
		OCCUPATION_SERVICE_COST_ITEM_ID_2 = properties.getProperty("OCCUPATION_SERVICE_COST_ITEM_ID_2", 57);
		OCCUPATION_SERVICE_COST_ITEM_COUNT_2 = properties.getProperty("OCCUPATION_SERVICE_COST_ITEM_COUNT_2", 1000000L);

		// Change player name service
		CHANGE_PLAYER_NAME_SERVICE_COST_ITEM_ID = properties.getProperty("CHANGE_PLAYER_NAME_SERVICE_COST_ITEM_ID", 57);
		CHANGE_PLAYER_NAME_SERVICE_COST_ITEM_COUNT = properties.getProperty("CHANGE_PLAYER_NAME_SERVICE_COST_ITEM_COUNT", 100000000L);

		// Change per name service
		CHANGE_PET_NAME_SERVICE_COST_ITEM_ID = properties.getProperty("CHANGE_PET_NAME_SERVICE_COST_ITEM_ID", 57);
		CHANGE_PET_NAME_SERVICE_COST_ITEM_COUNT = properties.getProperty("CHANGE_PET_NAME_SERVICE_COST_ITEM_COUNT", 100000000L);

		// Change clan name service
		CHANGE_CLAN_NAME_SERVICE_COST_ITEM_ID = properties.getProperty("CHANGE_CLAN_NAME_SERVICE_COST_ITEM_ID", 57);
		CHANGE_CLAN_NAME_SERVICE_COST_ITEM_COUNT = properties.getProperty("CHANGE_CLAN_NAME_SERVICE_COST_ITEM_COUNT", 100000000L);

		// Color name service
		COLOR_NAME_SERVICE_COST_ITEM_ID = properties.getProperty("COLOR_NAME_SERVICE_COST_ITEM_ID", 57);
		COLOR_NAME_SERVICE_COST_ITEM_COUNT = properties.getProperty("COLOR_NAME_SERVICE_COST_ITEM_COUNT", 100000000L);
		COLOR_NAME_SERVICE_COLORS = properties.getProperty("COLOR_NAME_SERVICE_COLORS", new String[0], ";");

		// Color title service
		COLOR_TITLE_SERVICE_COST_ITEM_ID = properties.getProperty("COLOR_TITLE_SERVICE_COST_ITEM_ID", 57);
		COLOR_TITLE_SERVICE_COST_ITEM_COUNT = properties.getProperty("COLOR_TITLE_SERVICE_COST_ITEM_COUNT", 100000000L);
		COLOR_TITLE_SERVICE_COLORS = properties.getProperty("COLOR_TITLE_SERVICE_COLORS", new String[0], ";");
	}
}