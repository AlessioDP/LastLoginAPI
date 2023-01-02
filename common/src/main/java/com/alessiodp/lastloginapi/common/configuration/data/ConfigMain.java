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
	
	@ConfigOption(path = "lastloginapi.automatic-upgrade-configs")
	public static boolean		LASTLOGINAPI_AUTOMATIC_UPGRADE_CONFIGS;
	
	
	// Storage settings
	@ConfigOption(path = "storage.database-storage-type")
	public static String		STORAGE_TYPE_DATABASE;
	@ConfigOption(path = "storage.storage-settings.general-sql-settings.prefix")
	public static String		STORAGE_SETTINGS_GENERAL_SQL_PREFIX;
	@ConfigOption(path = "storage.storage-settings.sqlite.database-file")
	public static String		STORAGE_SETTINGS_SQLITE_DBFILE;
	@ConfigOption(path = "storage.storage-settings.h2.database-file")
	public static String		STORAGE_SETTINGS_H2_DBFILE;
	@ConfigOption(path = "storage.storage-settings.remote-sql.address")
	public static String		STORAGE_SETTINGS_REMOTE_SQL_ADDRESS;
	@ConfigOption(path = "storage.storage-settings.remote-sql.port")
	public static String		STORAGE_SETTINGS_REMOTE_SQL_PORT;
	@ConfigOption(path = "storage.storage-settings.remote-sql.database")
	public static String		STORAGE_SETTINGS_REMOTE_SQL_DATABASE;
	@ConfigOption(path = "storage.storage-settings.remote-sql.username")
	public static String		STORAGE_SETTINGS_REMOTE_SQL_USERNAME;
	@ConfigOption(path = "storage.storage-settings.remote-sql.password")
	public static String		STORAGE_SETTINGS_REMOTE_SQL_PASSWORD;
	@ConfigOption(path = "storage.storage-settings.remote-sql.pool-size")
	public static int			STORAGE_SETTINGS_REMOTE_SQL_POOLSIZE;
	@ConfigOption(path = "storage.storage-settings.remote-sql.connection-lifetime")
	public static int			STORAGE_SETTINGS_REMOTE_SQL_CONNLIFETIME;
	@ConfigOption(path = "storage.storage-settings.remote-sql.use-ssl")
	public static boolean		STORAGE_SETTINGS_REMOTE_SQL_USESSL;
	@ConfigOption(path = "storage.storage-settings.remote-sql.charset")
	public static String		STORAGE_SETTINGS_REMOTE_SQL_CHARSET;
	@ConfigOption(path = "storage.storage-settings.remote-sql.additional-parameters")
	public static String		STORAGE_SETTINGS_REMOTE_SQL_ADDITIONAL_PARAMETERS;
	
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
	@ConfigOption(path = "placeholders.status.format-online")
	public static String		PLACEHOLDERS_STATUS_FORMAT_ONLINE;
	@ConfigOption(path = "placeholders.status.format-offline")
	public static String		PLACEHOLDERS_STATUS_FORMAT_OFFLINE;
	
	// Commands settings
	@ConfigOption(path = "commands.tab-support")
	public static boolean		COMMANDS_TABSUPPORT;
	
	@ConfigOption(path = "commands.main-commands.lastloginapi.command")
	public static String		COMMANDS_MAIN_LLAPI_COMMAND;
	@ConfigOption(path = "commands.main-commands.lastloginapi.aliases")
	public static List<String>	COMMANDS_MAIN_LLAPI_ALIASES;
	
	@ConfigOption(path = "commands.sub-commands.help")
	public static String		COMMANDS_CMD_HELP;
	@ConfigOption(path = "commands.sub-commands.info")
	public static String		COMMANDS_CMD_INFO;
	@ConfigOption(path = "commands.sub-commands.reload")
	public static String		COMMANDS_CMD_RELOAD;
	@ConfigOption(path = "commands.sub-commands.version")
	public static String		COMMANDS_CMD_VERSION;
	
	@ConfigOption(path = "commands.order")
	public static List<String> COMMANDS_ORDER;
	
	
	protected ConfigMain(LastLoginPlugin plugin) {
		super(plugin);
	}
}