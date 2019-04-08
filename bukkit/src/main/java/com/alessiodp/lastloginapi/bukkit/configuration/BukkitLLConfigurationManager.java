package com.alessiodp.lastloginapi.bukkit.configuration;

import com.alessiodp.core.bukkit.configuration.adapter.BukkitConfigurationAdapter;
import com.alessiodp.core.common.configuration.adapter.ConfigurationAdapter;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.addons.internal.LLPlaceholder;
import com.alessiodp.lastloginapi.common.configuration.LLConfigurationManager;

import java.nio.file.Path;

public class BukkitLLConfigurationManager extends LLConfigurationManager {
	
	public BukkitLLConfigurationManager(LastLoginPlugin plugin) {
		super(plugin);
		
		getConfigs().add(new BukkitConfigMain(plugin));
	}
	
	@Override
	protected ConfigurationAdapter initializeConfigurationAdapter(Path configurationFile) {
		return new BukkitConfigurationAdapter(configurationFile);
	}
	
	@Override
	protected void performChanges() {
		super.performChanges();
		LLPlaceholder.setupFormats((LastLoginPlugin) plugin);
	}
}
