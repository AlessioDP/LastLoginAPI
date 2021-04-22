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
	public static final int VERSION_BUKKIT_CONFIG = 4;
	public static final int VERSION_BUKKIT_MESSAGES = 2;
	public static final int VERSION_BUNGEE_CONFIG = 3;
	public static final int VERSION_BUNGEE_MESSAGES = 2;
	
	// Debug messages
	public static final String DEBUG_API_CANCELLED_UPDATE_LAST_LOGIN = "Event update last login cancelled";
	public static final String DEBUG_API_CANCELLED_UPDATE_LAST_LOGOUT = "Event update last logout cancelled";
	
	public static final String DEBUG_FAILED_PARSE_DATE = "Failed to parse format date";
	
	public static final String DEBUG_DB_UPDATEPLAYER = "Update player request for %s (uuid: %s)";
	public static final String DEBUG_DB_GETPLAYER = "Get player request for %s";
	public static final String DEBUG_DB_GETPLAYER_BYNAME = "Get player by name request for %s";
	
	public static final String DEBUG_PLACEHOLDER_RECEIVE = "Received PAPI placeholder request for '%s'";
	
	public static final String DEBUG_PLAYER_UPDATENAME = "Changed name of %s from '%s' to '%s'";
}
