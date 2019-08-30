package com.alessiodp.lastloginapi.bungeecord.configuration;

import com.alessiodp.core.bungeecord.configuration.adapter.BungeeConfigurationAdapter;
import com.alessiodp.core.common.configuration.adapter.ConfigurationAdapter;
import com.alessiodp.lastloginapi.bungeecord.configuration.data.BungeeConfigMain;
import com.alessiodp.lastloginapi.bungeecord.configuration.data.BungeeMessages;
import com.alessiodp.lastloginapi.common.configuration.LLConfigurationManager;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.addons.internal.LLPlaceholder;

import java.nio.file.Path;

public class BungeeLLConfigurationManager extends LLConfigurationManager {
	
	public BungeeLLConfigurationManager(LastLoginPlugin plugin) {
		super(plugin);
		
		getConfigs().add(new BungeeMessages(plugin));
		getConfigs().add(new BungeeConfigMain(plugin));
	}
	
	@Override
	protected ConfigurationAdapter initializeConfigurationAdapter(Path configurationFile) {
		return new BungeeConfigurationAdapter(configurationFile);
	}
	
	@Override
	protected void performChanges() {
		super.performChanges();
		LLPlaceholder.setupFormats((LastLoginPlugin) plugin);
	}
}
