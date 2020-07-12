package com.alessiodp.lastloginapi.bungeecord.configuration;

import com.alessiodp.lastloginapi.bungeecord.configuration.data.BungeeConfigMain;
import com.alessiodp.lastloginapi.bungeecord.configuration.data.BungeeMessages;
import com.alessiodp.lastloginapi.common.configuration.LLConfigurationManager;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;

public class BungeeLLConfigurationManager extends LLConfigurationManager {
	
	public BungeeLLConfigurationManager(LastLoginPlugin plugin) {
		super(plugin);
		
		getConfigs().add(new BungeeMessages(plugin));
		getConfigs().add(new BungeeConfigMain(plugin));
	}
}
