package com.alessiodp.lastloginapi.bukkit.configuration.data;

import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import lombok.Getter;

public class BukkitConfigMain extends ConfigMain {
	@Getter private final String fileName = "config.yml";
	@Getter private final String resourceName = "bukkit/config.yml";
	@Getter private final int latestVersion = LLConstants.VERSION_BUKKIT_CONFIG;
	
	public BukkitConfigMain(LastLoginPlugin plugin) {
		super(plugin);
	}
}