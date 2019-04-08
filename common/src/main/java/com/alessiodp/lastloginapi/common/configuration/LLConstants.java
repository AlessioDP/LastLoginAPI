package com.alessiodp.lastloginapi.common.configuration;

import com.alessiodp.core.common.logging.ConsoleColor;

public class LLConstants {
	// Plugin settings
	public static final String PLUGIN_NAME = "LastLoginAPI";
	public static final String PLUGIN_FALLBACK = "lastloginapi";
	public static final ConsoleColor PLUGIN_CONSOLECOLOR = ConsoleColor.YELLOW;
	public static final String PLUGIN_SPIGOTCODE = "";
	
	
	// Versions
	public static final int VERSION_BUKKIT_CONFIG = 1;
	public static final int VERSION_DATABASE_MYSQL = 1;
	public static final int VERSION_DATABASE_SQLITE = 1;
	
	
	// Placeholders
	public static final String PLACEHOLDER_NAME = "%name%";
	public static final String PLACEHOLDER_LAST_LOGIN_DATE = "%last_login_date%";
	public static final String PLACEHOLDER_LAST_LOGIN_ELAPSED = "%last_login_elapsed%";
	public static final String PLACEHOLDER_LAST_LOGOUT_DATE = "%last_logout_date%";
	public static final String PLACEHOLDER_LAST_LOGOUT_ELAPSED = "%last_logout_elapsed%";
	
	
	// SQL queries
	public static final String QUERY_PLAYER_INSERT_MYSQL = "INSERT INTO {table_players} (`uuid`, `name`, `lastLogin`, `lastLogout`) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE `name`=VALUES(`name`), `lastLogin`=VALUES(`lastLogin`), `lastLogout`=VALUES(`lastLogout`);";
	public static final String QUERY_PLAYER_INSERT_SQLITE = "INSERT OR REPLACE INTO {table_players} (`uuid`, `name`, `lastLogin`, `lastLogout`) VALUES (?,?,?,?);";
	public static final String QUERY_PLAYER_DELETE = "DELETE FROM {table_players} WHERE `uuid`=?;";
	public static final String QUERY_PLAYER_GET = "SELECT * FROM {table_players} WHERE `uuid`=?;";
	public static final String QUERY_PLAYER_GET_BYNAME = "SELECT * FROM {table_players} WHERE `name`=?;";
	
	
	// Debug messages
	public static final String DEBUG_DB_UPDATEPLAYER = "Update player for {player} [{uuid}]";
	public static final String DEBUG_DB_GETPLAYER = "Get player request for '{uuid}'";
	public static final String DEBUG_DB_GETPLAYER_BYNAME = "Get player request by name for '{player}'";
	public static final String DEBUG_PLAYER_UPDATENAME = "Changed name of '{uuid}' from '{old}' to '{new}'";
}
