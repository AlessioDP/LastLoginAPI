package com.alessiodp.lastloginapi.bukkit;

import com.alessiodp.core.bukkit.scheduling.ADPBukkitScheduler;
import com.alessiodp.core.bukkit.utils.BukkitColorUtils;
import com.alessiodp.core.common.bootstrap.ADPBootstrap;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bukkit.addons.BukkitAddonManager;
import com.alessiodp.lastloginapi.bukkit.addons.external.BukkitMetricsHandler;
import com.alessiodp.lastloginapi.bukkit.configuration.BukkitLLConfigurationManager;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.lastloginapi.bukkit.listeners.BukkitJoinLeaveListener;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class BukkitLastLoginPlugin extends LastLoginPlugin {
	
	public BukkitLastLoginPlugin(ADPBootstrap bootstrap) {
		super(bootstrap);
	}
	
	@Override
	protected void initializeCore() {
		scheduler = new ADPBukkitScheduler(this);
		configurationManager = new BukkitLLConfigurationManager(this);
		
		super.initializeCore();
	}
	
	@Override
	protected void postHandle() {
		colorUtils = new BukkitColorUtils();
		addonManager = new BukkitAddonManager(this);
		
		super.postHandle();
		
		new BukkitMetricsHandler(this);
	}
	
	@Override
	protected void registerListeners() {
		getLoggerManager().logDebug(Constants.DEBUG_PLUGIN_REGISTERING, true);
		PluginManager pm = ((Plugin) getBootstrap()).getServer().getPluginManager();
		pm.registerEvents(new BukkitJoinLeaveListener(this), ((Plugin) getBootstrap()));
	}
	
	@Override
	public boolean isBungeeCordEnabled() {
		return BukkitConfigMain.LASTLOGINAPI_BUNGEECORD_ENABLE;
	}
}
