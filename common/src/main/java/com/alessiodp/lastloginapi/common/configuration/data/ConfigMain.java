package com.alessiodp.lastloginapi.common.configuration.data;

import com.alessiodp.core.common.configuration.ConfigurationFile;
import com.alessiodp.core.common.configuration.adapter.ConfigurationAdapter;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;

public abstract class ConfigMain extends ConfigurationFile {
	// LastLoginAPI settings
	public static boolean		LASTLOGINAPI_UPDATES_CHECK;
	public static boolean		LASTLOGINAPI_UPDATES_WARN;
	public static String		LASTLOGINAPI_UPDATES_WARNMESSAGE;
	public static boolean		LASTLOGINAPI_LOGGING_DEBUG;
	public static boolean		LASTLOGINAPI_LOGGING_SAVE_ENABLE;
	public static String		LASTLOGINAPI_LOGGING_SAVE_FORMAT;
	public static String		LASTLOGINAPI_LOGGING_SAVE_FILE;
	
	// Storage settings
	public static String		STORAGE_TYPE_DATABASE;
	public static int			STORAGE_SETTINGS_GENERAL_SQL_VARCHARSIZE;
	public static boolean		STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_SAVEOLD;
	public static String		STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_OLDSUFFIX;
	public static String		STORAGE_SETTINGS_GENERAL_SQL_TABLES_PLAYERS;
	public static String		STORAGE_SETTINGS_GENERAL_SQL_TABLES_VERSIONS;
	public static String		STORAGE_SETTINGS_SQLITE_DBFILE;
	public static String		STORAGE_SETTINGS_MYSQL_ADDRESS;
	public static String		STORAGE_SETTINGS_MYSQL_PORT;
	public static String		STORAGE_SETTINGS_MYSQL_DATABASE;
	public static String		STORAGE_SETTINGS_MYSQL_USERNAME;
	public static String		STORAGE_SETTINGS_MYSQL_PASSWORD;
	public static int			STORAGE_SETTINGS_MYSQL_POOLSIZE;
	public static int			STORAGE_SETTINGS_MYSQL_CONNLIFETIME;
	public static boolean		STORAGE_SETTINGS_MYSQL_USESSL;
	public static String		STORAGE_SETTINGS_MYSQL_CHARSET;
	
	
	// Placeholders
	public static String		PLACEHOLDERS_NAME_FORMAT;
	public static String		PLACEHOLDERS_NAME_FORMAT_UNKNOWN;
	public static String		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT;
	public static String		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE;
	public static String		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN;
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT;
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE;
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN;
	public static String		PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT;
	public static String		PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN;
	public static String		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT;
	public static String		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN;
	
	protected ConfigMain(LastLoginPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void loadDefaults() {
		// General settings
		LASTLOGINAPI_UPDATES_CHECK = true;
		LASTLOGINAPI_UPDATES_WARN = true;
		LASTLOGINAPI_UPDATES_WARNMESSAGE = "&aNew version of LastLoginAPI found: %version% (Current: %thisversion%)";
		LASTLOGINAPI_LOGGING_DEBUG = false;
		LASTLOGINAPI_LOGGING_SAVE_ENABLE = false;
		LASTLOGINAPI_LOGGING_SAVE_FORMAT = "%date% [%time%] %message%\n";
		LASTLOGINAPI_LOGGING_SAVE_FILE = "log.txt";
		
		
		// Storage settings
		STORAGE_TYPE_DATABASE = "sqlite";
		STORAGE_SETTINGS_GENERAL_SQL_VARCHARSIZE = 255;
		STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_SAVEOLD = true;
		STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_OLDSUFFIX = "_backup";
		STORAGE_SETTINGS_GENERAL_SQL_TABLES_PLAYERS = "lastloginapi_players";
		STORAGE_SETTINGS_GENERAL_SQL_TABLES_VERSIONS = "lastloginapi_versions";
		STORAGE_SETTINGS_SQLITE_DBFILE = "database.db";
		STORAGE_SETTINGS_MYSQL_ADDRESS = "localhost";
		STORAGE_SETTINGS_MYSQL_PORT = "3306";
		STORAGE_SETTINGS_MYSQL_DATABASE = "database";
		STORAGE_SETTINGS_MYSQL_USERNAME = "username";
		STORAGE_SETTINGS_MYSQL_PASSWORD = "password";
		STORAGE_SETTINGS_MYSQL_POOLSIZE = 10;
		STORAGE_SETTINGS_MYSQL_CONNLIFETIME = 1800000;
		STORAGE_SETTINGS_MYSQL_USESSL = false;
		STORAGE_SETTINGS_MYSQL_CHARSET = "utf8";
		
		
		// Placeholders
		PLACEHOLDERS_NAME_FORMAT = "%name%";
		PLACEHOLDERS_NAME_FORMAT_UNKNOWN = "Unknown";
		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT = "yyyy-MM-dd";
		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE = "Online";
		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN = "Unknown";
		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT = "d'd' H'h' m'm'";
		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE = "H'h' m'm'";
		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN = "Unknown";
		PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT = "yyyy-MM-dd";
		PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN = "Unknown";
		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT = "d'd' H'h' m'm'";
		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN = "Unknown";
	}
	
	@Override
	public void loadConfiguration(ConfigurationAdapter confAdapter) {
		// LastLoginAPI settings
		LASTLOGINAPI_UPDATES_CHECK = confAdapter.getBoolean("lastloginapi.updates.check", LASTLOGINAPI_UPDATES_CHECK);
		LASTLOGINAPI_UPDATES_WARN = confAdapter.getBoolean("lastloginapi.updates.warn", LASTLOGINAPI_UPDATES_WARN);
		LASTLOGINAPI_UPDATES_WARNMESSAGE = confAdapter.getString("lastloginapi.updates.warn-message", LASTLOGINAPI_UPDATES_WARNMESSAGE);
		LASTLOGINAPI_LOGGING_DEBUG = confAdapter.getBoolean("lastloginapi.logging.debug", LASTLOGINAPI_LOGGING_DEBUG);
		LASTLOGINAPI_LOGGING_SAVE_ENABLE = confAdapter.getBoolean("lastloginapi.logging.save-file.enable", LASTLOGINAPI_LOGGING_SAVE_ENABLE);
		LASTLOGINAPI_LOGGING_SAVE_FORMAT = confAdapter.getString("lastloginapi.logging.save-file.format", LASTLOGINAPI_LOGGING_SAVE_FORMAT);
		LASTLOGINAPI_LOGGING_SAVE_FILE = confAdapter.getString("lastloginapi.logging.save-file.file", LASTLOGINAPI_LOGGING_SAVE_FILE);
		
		
		// Storage settings
		STORAGE_TYPE_DATABASE = confAdapter.getString("storage.database-storage-type", STORAGE_TYPE_DATABASE);
		STORAGE_SETTINGS_GENERAL_SQL_VARCHARSIZE = confAdapter.getInt("storage.storage-settings.general-sql-settings.varchar-size", STORAGE_SETTINGS_GENERAL_SQL_VARCHARSIZE);
		STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_SAVEOLD = confAdapter.getBoolean("storage.storage-settings.general-sql-settings.upgrade.save-old-table", STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_SAVEOLD);
		STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_OLDSUFFIX = confAdapter.getString("storage.storage-settings.general-sql-settings.upgrade.old-table-suffix", STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_OLDSUFFIX);
		STORAGE_SETTINGS_GENERAL_SQL_TABLES_PLAYERS = confAdapter.getString("storage.storage-settings.general-sql-settings.tables.players", STORAGE_SETTINGS_GENERAL_SQL_TABLES_PLAYERS);
		STORAGE_SETTINGS_GENERAL_SQL_TABLES_VERSIONS = confAdapter.getString("storage.storage-settings.general-sql-settings.tables.versions", STORAGE_SETTINGS_GENERAL_SQL_TABLES_VERSIONS);
		STORAGE_SETTINGS_SQLITE_DBFILE = confAdapter.getString("storage.storage-settings.sqlite.database-file", STORAGE_SETTINGS_SQLITE_DBFILE);
		STORAGE_SETTINGS_MYSQL_ADDRESS = confAdapter.getString("storage.storage-settings.mysql.address", STORAGE_SETTINGS_MYSQL_ADDRESS);
		STORAGE_SETTINGS_MYSQL_PORT = confAdapter.getString("storage.storage-settings.mysql.port", STORAGE_SETTINGS_MYSQL_PORT);
		STORAGE_SETTINGS_MYSQL_DATABASE = confAdapter.getString("storage.storage-settings.mysql.database", STORAGE_SETTINGS_MYSQL_DATABASE);
		STORAGE_SETTINGS_MYSQL_USERNAME = confAdapter.getString("storage.storage-settings.mysql.username", STORAGE_SETTINGS_MYSQL_USERNAME);
		STORAGE_SETTINGS_MYSQL_PASSWORD = confAdapter.getString("storage.storage-settings.mysql.password", STORAGE_SETTINGS_MYSQL_PASSWORD);
		STORAGE_SETTINGS_MYSQL_POOLSIZE = confAdapter.getInt("storage.storage-settings.mysql.pool-size", STORAGE_SETTINGS_MYSQL_POOLSIZE);
		STORAGE_SETTINGS_MYSQL_CONNLIFETIME = confAdapter.getInt("storage.storage-settings.mysql.connection-lifetime", STORAGE_SETTINGS_MYSQL_CONNLIFETIME);
		STORAGE_SETTINGS_MYSQL_USESSL = confAdapter.getBoolean("storage.storage-settings.mysql.use-ssl", STORAGE_SETTINGS_MYSQL_USESSL);
		STORAGE_SETTINGS_MYSQL_CHARSET = confAdapter.getString("storage.storage-settings.mysql.charset", STORAGE_SETTINGS_MYSQL_CHARSET);
		
		
		// Placeholders
		PLACEHOLDERS_NAME_FORMAT = confAdapter.getString("placeholders.name.format", PLACEHOLDERS_NAME_FORMAT);
		PLACEHOLDERS_NAME_FORMAT_UNKNOWN = confAdapter.getString("placeholders.name.format-unknown", PLACEHOLDERS_NAME_FORMAT_UNKNOWN);
		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT = confAdapter.getString("placeholders.last-login-date.format", PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT);
		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE = confAdapter.getString("placeholders.last-login-date.format-online", PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE);
		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN = confAdapter.getString("placeholders.last-login-date.format-unknown", PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN);
		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT = confAdapter.getString("placeholders.last-login-elapsed.format", PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT);
		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE = confAdapter.getString("placeholders.last-login-elapsed.format-online", PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE);
		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN = confAdapter.getString("placeholders.last-login-elapsed.format-unknown", PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN);
		PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT = confAdapter.getString("placeholders.last-logout-date.format", PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT);
		PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN = confAdapter.getString("placeholders.last-logout-date.format-unknown", PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN);
		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT = confAdapter.getString("placeholders.last-logout-elapsed.format", PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT);
		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN = confAdapter.getString("placeholders.last-logout-elapsed.format-unknown", PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN);
	}
}