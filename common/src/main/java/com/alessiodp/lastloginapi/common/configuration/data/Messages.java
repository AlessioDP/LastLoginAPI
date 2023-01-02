package com.alessiodp.lastloginapi.common.configuration.data;

import com.alessiodp.core.common.configuration.ConfigOption;
import com.alessiodp.core.common.configuration.ConfigurationFile;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;

import java.util.List;

public abstract class Messages extends ConfigurationFile {
	// LastLoginAPI messages
	@ConfigOption(path = "lastloginapi.update-available")
	public static String LLAPI_UPDATEAVAILABLE;
	@ConfigOption(path = "lastloginapi.configuration-outdated")
	public static String LLAPI_CONFIGURATION_OUTDATED;
	@ConfigOption(path = "lastloginapi.no-permission")
	public static String LLAPI_NOPERMISSION;
	
	@ConfigOption(path = "lastloginapi.common-messages.invalid-command")
	public static String LLAPI_COMMON_INVALIDCMD;
	@ConfigOption(path = "lastloginapi.common-messages.configuration-reloaded")
	public static String LLAPI_COMMON_CONFIGRELOAD;
	
	@ConfigOption(path = "lastloginapi.syntax.wrong-message")
	public static String LLAPI_SYNTAX_WRONGMESSAGE;
	@ConfigOption(path = "lastloginapi.syntax.player")
	public static String LLAPI_SYNTAX_PLAYER;
	@ConfigOption(path = "lastloginapi.syntax.unknown")
	public static String LLAPI_SYNTAX_UNKNOWN;
	
	
	// Commands messages
	@ConfigOption(path = "commands.info.header")
	public static String CMD_INFO_HEADER;
	@ConfigOption(path = "commands.info.text")
	public static List<String> CMD_INFO_TEXT;
	@ConfigOption(path = "commands.info.footer")
	public static String CMD_INFO_FOOTER;
	@ConfigOption(path = "commands.info.player-not-found")
	public static String CMD_INFO_PLAYERNOTFOUND;
	
	@ConfigOption(path = "commands.version.updated")
	public static String CMD_VERSION_UPDATED;
	@ConfigOption(path = "commands.version.outdated")
	public static String CMD_VERSION_OUTDATED;
	
	
	// Help messages
	@ConfigOption(path = "help.header")
	public static String HELP_HEADER;
	@ConfigOption(path = "help.footer")
	public static String HELP_FOOTER;
	@ConfigOption(path = "help.perform-command")
	public static String HELP_PERFORM_COMMAND;
	@ConfigOption(path = "help.console-help.header")
	public static String HELP_CONSOLEHELP_HEADER;
	@ConfigOption(path = "help.console-help.command")
	public static String HELP_CONSOLEHELP_COMMAND;
	
	@ConfigOption(path = "help.commands.help")
	public static String HELP_CMD_HELP;
	@ConfigOption(path = "help.commands.info")
	public static String HELP_CMD_INFO;
	@ConfigOption(path = "help.commands.reload")
	public static String HELP_CMD_RELOAD;
	@ConfigOption(path = "help.commands.version")
	public static String HELP_CMD_VERSION;
	
	@ConfigOption(path = "help.command-descriptions.help")
	public static String HELP_CMD_DESCRIPTIONS_HELP;
	@ConfigOption(path = "help.command-descriptions.info")
	public static String HELP_CMD_DESCRIPTIONS_INFO;
	@ConfigOption(path = "help.command-descriptions.reload")
	public static String HELP_CMD_DESCRIPTIONS_RELOAD;
	@ConfigOption(path = "help.command-descriptions.version")
	public static String HELP_CMD_DESCRIPTIONS_VERSION;
	
	
	protected Messages(LastLoginPlugin plugin) {
		super(plugin);
	}
}
