package com.alessiodp.lastloginapi.bungeecord;

import com.alessiodp.core.bungeecord.addons.internal.json.BungeeJsonHandler;
import com.alessiodp.core.bungeecord.scheduling.ADPBungeeScheduler;
import com.alessiodp.core.bungeecord.utils.BungeeColorUtils;
import com.alessiodp.core.common.bootstrap.ADPBootstrap;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bungeecord.addons.BungeeAddonManager;
import com.alessiodp.lastloginapi.bungeecord.addons.external.BungeeMetricsHandler;
import com.alessiodp.lastloginapi.bungeecord.commands.BungeeLLCommandManager;
import com.alessiodp.lastloginapi.bungeecord.configuration.BungeeLLConfigurationManager;
import com.alessiodp.lastloginapi.bungeecord.events.BungeeEventManager;
import com.alessiodp.lastloginapi.bungeecord.listeners.BungeeJoinLeaveListener;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeLastLoginPlugin extends LastLoginPlugin {
	
	public BungeeLastLoginPlugin(ADPBootstrap bootstrap) {
		super(bootstrap);
	}
	
	@Override
	protected void initializeCore() {
		scheduler = new ADPBungeeScheduler(this);
		configurationManager = new BungeeLLConfigurationManager(this);
		
		super.initializeCore();
	}
	
	@Override
	protected void loadCore() {
		commandManager = new BungeeLLCommandManager(this);
		
		super.loadCore();
	}
	
	@Override
	protected void postHandle() {
		colorUtils = new BungeeColorUtils();
		addonManager = new BungeeAddonManager(this);
		eventManager = new BungeeEventManager(this);
		
		super.postHandle();
		
		new BungeeMetricsHandler(this);
		
		eventManager.callEvent(eventManager.prepareUpdateLoginTimestamp(null, 0));
	}
	
	@Override
	protected void initializeJsonHandler() {
		jsonHandler = new BungeeJsonHandler();
	}
	
	@Override
	protected void registerListeners() {
		getLoggerManager().logDebug(Constants.DEBUG_PLUGIN_REGISTERING, true);
		Plugin plugin = (Plugin) getBootstrap();
		PluginManager pm = plugin.getProxy().getPluginManager();
		pm.registerListener(plugin, new BungeeJoinLeaveListener(this));
	}
	
	@Override
	public boolean isBungeeCordEnabled() {
		return false;
	}
}
