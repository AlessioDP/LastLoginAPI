package com.alessiodp.lastloginapi.bungeecord.configuration.data;

import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import lombok.Getter;

public class BungeeConfigMain extends ConfigMain {
	@Getter private final String fileName = "config.yml";
	@Getter private final String resourceName = "bungee/config.yml";
	@Getter private final int latestVersion = LLConstants.VERSION_BUNGEE_CONFIG;
	
	public BungeeConfigMain(LastLoginPlugin plugin) {
		super(plugin);
	}
}