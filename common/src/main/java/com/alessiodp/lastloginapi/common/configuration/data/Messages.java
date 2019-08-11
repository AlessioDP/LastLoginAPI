package com.alessiodp.lastloginapi.common.configuration.data;

import com.alessiodp.core.common.configuration.ConfigurationFile;
import com.alessiodp.core.common.configuration.adapter.ConfigurationAdapter;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class Messages extends ConfigurationFile {
	// LastLoginAPI messages
	public static String LLAPI_UPDATEAVAILABLE;
	public static String LLAPI_INVALID_COMMAND;
	public static String LLAPI_CONFIGURATION_OUTDATED;
	public static String LLAPI_NO_PERMISSION;
	
	
	// Commands messages
	public static String CMD_RELOAD_RELOADED;
	public static String CMD_VERSION_UPDATED;
	public static String CMD_VERSION_OUTDATED;
	
	
	// Help messages
	public static String HELP_HEADER;
	public static String HELP_FOOTER;
	public static List<String> HELP_CONSOLEHELP;
	
	public static String HELP_CMD_HELP;
	public static String HELP_CMD_RELOAD;
	public static String HELP_CMD_VERSION;
	
	protected Messages(LastLoginPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void loadDefaults() {
		// LastLoginAPI messages
		LLAPI_UPDATEAVAILABLE = "&9New version of LastLoginAPI found: %version% (Current: %thisversion%)";
		LLAPI_INVALID_COMMAND = "&cInvalid command";
		LLAPI_CONFIGURATION_OUTDATED = "&cThe configuration file '%config%' of LastLoginAPI is outdated!";
		LLAPI_NO_PERMISSION = "&cYou do not have access to that command";
		
		
		// Command messages
		CMD_RELOAD_RELOADED = "&aConfiguration reloaded";
		
		CMD_VERSION_UPDATED = "&6&lLastLoginAPI &6%version% &7(%platform%) - Developed by &6AlessioDP";
		CMD_VERSION_OUTDATED = "&6&lLastLoginAPI &6%version% &7(%platform%) - Developed by &6AlessioDP\n&aNew version found: &2%newversion%";
		
		
		// Help messages
		HELP_HEADER = "&6============ &lLastLoginAPI Help Page &r&6============";
		HELP_FOOTER = "";
		HELP_CONSOLEHELP = new ArrayList<>();
		HELP_CONSOLEHELP.add("You can only make these commands:");
		HELP_CONSOLEHELP.add(" > llapi reload - Reload the configuration");
		HELP_CONSOLEHELP.add(" > llapi version - Show LastLoginAPI information");
		
		HELP_CMD_HELP = "&6/oa help&7 - Show help pages";
		HELP_CMD_RELOAD = "&6/oa reload&7 - Reload the configuration";
		HELP_CMD_VERSION = "&6/oa version&7 - Show LastLoginAPI information";
	}
	
	@Override
	public void loadConfiguration(ConfigurationAdapter confAdapter) {
		// LastLoginAPI messages
		LLAPI_UPDATEAVAILABLE = confAdapter.getString("lastloginapi.update-available", LLAPI_UPDATEAVAILABLE);
		LLAPI_INVALID_COMMAND = confAdapter.getString("lastloginapi.invalid-command", LLAPI_INVALID_COMMAND);
		LLAPI_CONFIGURATION_OUTDATED = confAdapter.getString("lastloginapi.configuration-outdated", LLAPI_CONFIGURATION_OUTDATED);
		LLAPI_NO_PERMISSION = confAdapter.getString("lastloginapi.no-permission", LLAPI_NO_PERMISSION);
		
		
		// Command messages
		CMD_RELOAD_RELOADED = confAdapter.getString("commands.reload.reloaded", CMD_RELOAD_RELOADED);
		
		CMD_VERSION_UPDATED = confAdapter.getString("commands.version.updated", CMD_VERSION_UPDATED);
		CMD_VERSION_OUTDATED = confAdapter.getString("commands.version.outdated", CMD_VERSION_OUTDATED);
		
		
		// Help messages
		HELP_HEADER = confAdapter.getString("help.header", HELP_HEADER);
		HELP_FOOTER = confAdapter.getString("help.footer", HELP_FOOTER);
		HELP_CONSOLEHELP = confAdapter.getStringList("help.console-help", HELP_CONSOLEHELP);
		
		HELP_CMD_HELP = confAdapter.getString("help.commands.help", HELP_CMD_HELP);
		HELP_CMD_RELOAD = confAdapter.getString("help.commands.reload", HELP_CMD_RELOAD);
		HELP_CMD_VERSION = confAdapter.getString("help.commands.version", HELP_CMD_VERSION);
	}
}
