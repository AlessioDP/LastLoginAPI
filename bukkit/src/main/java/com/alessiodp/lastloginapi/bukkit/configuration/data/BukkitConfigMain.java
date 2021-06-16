package com.alessiodp.lastloginapi.bukkit.configuration.data;

import com.alessiodp.core.common.configuration.ConfigOption;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import lombok.Getter;

public class BukkitConfigMain extends ConfigMain {
	@Getter private final String fileName = "config.yml";
	@Getter private final String resourceName = "bukkit/config.yml";
	@Getter private final int latestVersion = LLConstants.VERSION_BUKKIT_CONFIG;
	
	// LastLoginAPI settings
	@ConfigOption(path = "lastloginapi.bungeecord.enable")
	public static boolean		LASTLOGINAPI_BUNGEECORD_ENABLE;
	@ConfigOption(path = "lastloginapi.authme.enable")
	public static boolean		LASTLOGINAPI_AUTHME_ENABLE;
	@ConfigOption(path = "lastloginapi.login-security.enable")
	public static boolean		LASTLOGINAPI_LOGINSECURITY_ENABLE;
	
	// Commands settings
	@ConfigOption(path = "commands.main-commands.lastloginapi.description")
	public static String		COMMANDS_MAIN_LLAPI_DESCRIPTION;
	
	public BukkitConfigMain(LastLoginPlugin plugin) {
		super(plugin);
	}
}