package com.alessiodp.lastloginapi.bukkit.configuration.data;

import com.alessiodp.core.common.configuration.adapter.ConfigurationAdapter;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import lombok.Getter;

public class BukkitConfigMain extends ConfigMain {
	@Getter private final String fileName = "config.yml";
	@Getter private final String resourceName = "bukkit/config.yml";
	@Getter private final int latestVersion = LLConstants.VERSION_BUKKIT_CONFIG;
	
	// LastLoginAPI settings
	public static boolean		LASTLOGINAPI_BUNGEECORD_ENABLE;
	public static boolean		LASTLOGINAPI_AUTHME_ENABLE;
	
	public BukkitConfigMain(LastLoginPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void loadDefaults() {
		super.loadDefaults();
		
		// LastLoginAPI settings
		LASTLOGINAPI_BUNGEECORD_ENABLE = false;
		LASTLOGINAPI_AUTHME_ENABLE = false;
	}
	
	@Override
	public void loadConfiguration(ConfigurationAdapter confAdapter) {
		super.loadConfiguration(confAdapter);
		
		// LastLoginAPI settings
		LASTLOGINAPI_BUNGEECORD_ENABLE = confAdapter.getBoolean("lastloginapi.bungeecord.enable", LASTLOGINAPI_BUNGEECORD_ENABLE);
		LASTLOGINAPI_AUTHME_ENABLE = confAdapter.getBoolean("lastloginapi.authme.enable", LASTLOGINAPI_AUTHME_ENABLE);
	}
}