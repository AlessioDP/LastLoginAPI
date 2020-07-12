package com.alessiodp.lastloginapi.common.configuration.data;

import com.alessiodp.core.common.configuration.ConfigOption;
import com.alessiodp.core.common.configuration.ConfigurationFile;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;

import java.util.List;

public abstract class ConfigMain extends ConfigurationFile {
	// LastLoginAPI settings
	@ConfigOption(path = "lastloginapi.updates.check")
	public static boolean		LASTLOGINAPI_UPDATES_CHECK;
	@ConfigOption(path = "lastloginapi.updates.warn")
	public static boolean		LASTLOGINAPI_UPDATES_WARN;
	@ConfigOption(path = "lastloginapi.logging.debug")
	public static boolean		LASTLOGINAPI_LOGGING_DEBUG;
	@ConfigOption(path = "lastloginapi.logging.save-file.enable")
	public static boolean		LASTLOGINAPI_LOGGING_SAVE_ENABLE;
	@ConfigOption(path = "lastloginapi.logging.save-file.format")
	public static String		LASTLOGINAPI_LOGGING_SAVE_FORMAT;
	@ConfigOption(path = "lastloginapi.logging.save-file.file")
	public static String		LASTLOGINAPI_LOGGING_SAVE_FILE;
	
	
	// Storage settings
	@ConfigOption(path = "storage.database-storage-type")
	public static String		STORAGE_TYPE_DATABASE;
	@ConfigOption(path = "storage.storage-settings.general-sql-settings.prefix")
	public static String		STORAGE_SETTINGS_GENERAL_SQL_PREFIX;
	@ConfigOption(path = "storage.storage-settings.sqlite.database-file")
	public static String		STORAGE_SETTINGS_SQLITE_DBFILE;
	@ConfigOption(path = "storage.storage-settings.h2.database-file")
	public static String		STORAGE_SETTINGS_H2_DBFILE;
	@ConfigOption(path = "storage.storage-settings.mysql.address")
	public static String		STORAGE_SETTINGS_MYSQL_ADDRESS;
	@ConfigOption(path = "storage.storage-settings.mysql.port")
	public static String		STORAGE_SETTINGS_MYSQL_PORT;
	@ConfigOption(path = "storage.storage-settings.mysql.database")
	public static String		STORAGE_SETTINGS_MYSQL_DATABASE;
	@ConfigOption(path = "storage.storage-settings.mysql.username")
	public static String		STORAGE_SETTINGS_MYSQL_USERNAME;
	@ConfigOption(path = "storage.storage-settings.mysql.password")
	public static String		STORAGE_SETTINGS_MYSQL_PASSWORD;
	@ConfigOption(path = "storage.storage-settings.mysql.pool-size")
	public static int			STORAGE_SETTINGS_MYSQL_POOLSIZE;
	@ConfigOption(path = "storage.storage-settings.mysql.connection-lifetime")
	public static int			STORAGE_SETTINGS_MYSQL_CONNLIFETIME;
	@ConfigOption(path = "storage.storage-settings.mysql.use-ssl")
	public static boolean		STORAGE_SETTINGS_MYSQL_USESSL;
	@ConfigOption(path = "storage.storage-settings.mysql.charset")
	public static String		STORAGE_SETTINGS_MYSQL_CHARSET;
	
	
	// Placeholders
	@ConfigOption(path = "placeholders.name.format")
	public static String		PLACEHOLDERS_NAME_FORMAT;
	@ConfigOption(path = "placeholders.name.format-unknown")
	public static String		PLACEHOLDERS_NAME_FORMAT_UNKNOWN;
	@ConfigOption(path = "placeholders.last-login-date.format")
	public static String		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT;
	@ConfigOption(path = "placeholders.last-login-date.format-online")
	public static String		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE;
	@ConfigOption(path = "placeholders.last-login-date.format-unknown")
	public static String		PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN;
	@ConfigOption(path = "placeholders.last-login-elapsed.format.large")
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_LARGE;
	@ConfigOption(path = "placeholders.last-login-elapsed.format.medium")
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_MEDIUM;
	@ConfigOption(path = "placeholders.last-login-elapsed.format.small")
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_SMALL;
	@ConfigOption(path = "placeholders.last-login-elapsed.format.smallest")
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_SMALLEST;
	@ConfigOption(path = "placeholders.last-login-elapsed.format-online.large")
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_LARGE;
	@ConfigOption(path = "placeholders.last-login-elapsed.format-online.medium")
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_MEDIUM;
	@ConfigOption(path = "placeholders.last-login-elapsed.format-online.small")
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_SMALL;
	@ConfigOption(path = "placeholders.last-login-elapsed.format-online.smallest")
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_SMALLEST;
	@ConfigOption(path = "placeholders.last-login-elapsed.format-unknown")
	public static String		PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN;
	@ConfigOption(path = "placeholders.last-logout-date.format")
	public static String		PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT;
	@ConfigOption(path = "placeholders.last-logout-date.format-unknown")
	public static String		PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN;
	@ConfigOption(path = "placeholders.last-logout-elapsed.format.large")
	public static String		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_LARGE;
	@ConfigOption(path = "placeholders.last-logout-elapsed.format.medium")
	public static String		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_MEDIUM;
	@ConfigOption(path = "placeholders.last-logout-elapsed.format.small")
	public static String		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_SMALL;
	@ConfigOption(path = "placeholders.last-logout-elapsed.format.smallest")
	public static String		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_SMALLEST;
	@ConfigOption(path = "placeholders.last-logout-elapsed.format-unknown")
	public static String		PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN;
	
	
	// Commands settings
	@ConfigOption(path = "commands.tab-support")
	public static boolean		COMMANDS_TABSUPPORT;
	@ConfigOption(path = "commands.llapi-description")
	public static String		COMMANDS_DESCRIPTION_LLAPI;
	
	@ConfigOption(path = "commands.main-commands.help")
	public static String		COMMANDS_CMD_HELP;
	@ConfigOption(path = "commands.main-commands.llapi")
	public static String		COMMANDS_CMD_LLAPI;
	@ConfigOption(path = "commands.main-commands.info")
	public static String		COMMANDS_CMD_INFO;
	@ConfigOption(path = "commands.main-commands.reload")
	public static String		COMMANDS_CMD_RELOAD;
	@ConfigOption(path = "commands.main-commands.version")
	public static String		COMMANDS_CMD_VERSION;
	
	@ConfigOption(path = "commands.order")
	public static List<String> COMMANDS_ORDER;
	
	
	protected ConfigMain(LastLoginPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void loadDefaults() {
		loadDefaultConfigOptions();
	}
	
	@Override
	public void loadConfiguration() {
		loadConfigOptions();
	}
}