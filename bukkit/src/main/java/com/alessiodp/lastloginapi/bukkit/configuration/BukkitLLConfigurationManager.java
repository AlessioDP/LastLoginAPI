package com.alessiodp.lastloginapi.bukkit.configuration;

import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitMessages;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConfigurationManager;

public class BukkitLLConfigurationManager extends LLConfigurationManager {
	
	public BukkitLLConfigurationManager(LastLoginPlugin plugin) {
		super(plugin);
		
		getConfigs().add(new BukkitMessages(plugin));
		getConfigs().add(new BukkitConfigMain(plugin));
	}
}
