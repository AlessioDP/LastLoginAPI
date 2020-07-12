package com.alessiodp.lastloginapi.common.configuration;

import com.alessiodp.core.common.logging.ConsoleColor;

public class LLConstants {
	// Plugin settings
	public static final String PLUGIN_NAME = "LastLoginAPI";
	public static final String PLUGIN_FALLBACK = "lastloginapi";
	public static final ConsoleColor PLUGIN_CONSOLECOLOR = ConsoleColor.YELLOW;
	public static final String PLUGIN_PACKAGENAME = "com.alessiodp.lastloginapi";
	public static final String PLUGIN_SPIGOTCODE = "66348";
	public static final int PLUGIN_BSTATS_BUKKIT_ID = 4369;
	public static final int PLUGIN_BSTATS_BUNGEE_ID = 4447;
	
	
	// Versions
	public static final int VERSION_BUKKIT_CONFIG = 3;
	public static final int VERSION_BUKKIT_MESSAGES = 2;
	public static final int VERSION_BUNGEE_CONFIG = 2;
	public static final int VERSION_BUNGEE_MESSAGES = 2;
	
	// Debug messages
	public static final String DEBUG_API_CANCELLED_UPDATE_LAST_LOGIN = "Event update last login cancelled";
	public static final String DEBUG_API_CANCELLED_UPDATE_LAST_LOGOUT = "Event update last logout cancelled";
	
	public static final String DEBUG_DB_UPDATEPLAYER = "Update player for {player} [{uuid}]";
	public static final String DEBUG_DB_GETPLAYER = "Get player request for '{uuid}'";
	public static final String DEBUG_DB_GETPLAYER_BYNAME = "Get player request by name for '{player}'";
	public static final String DEBUG_PLACEHOLDER_RECEIVE = "Received PAPI placeholder request for '{placeholder}'";
	public static final String DEBUG_PLAYER_UPDATENAME = "Changed name of '{uuid}' from '{old}' to '{new}'";
	
	public static final String DEBUG_CMD_HELP = "{player} performed help command";
	public static final String DEBUG_CMD_INFO = "{player} performed info command on '{victim}'";
	public static final String DEBUG_CMD_INFO_CONSOLE = "Console performed info command on '{victim}'";
	public static final String DEBUG_CMD_RELOAD = "{player} performed reload command";
	public static final String DEBUG_CMD_RELOAD_CONSOLE = "Console performed reload command";
	public static final String DEBUG_CMD_RELOADED = "Configuration reloaded by {player}";
	public static final String DEBUG_CMD_RELOADED_CONSOLE = "Configuration reloaded";
	public static final String DEBUG_CMD_VERSION = "{player} performed version command";
	public static final String DEBUG_CMD_VERSION_CONSOLE = "Performed version command";
}
