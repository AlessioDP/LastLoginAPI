package com.alessiodp.lastloginapi.bukkit;

import com.alessiodp.core.bukkit.addons.internal.json.BukkitJsonHandler;
import com.alessiodp.core.bukkit.addons.internal.json.SpigotJsonHandler;
import com.alessiodp.core.bukkit.scheduling.ADPBukkitScheduler;
import com.alessiodp.core.common.bootstrap.ADPBootstrap;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bukkit.addons.BukkitAddonManager;
import com.alessiodp.lastloginapi.bukkit.addons.external.BukkitMetricsHandler;
import com.alessiodp.lastloginapi.bukkit.bootstrap.BukkitLastLoginBootstrap;
import com.alessiodp.lastloginapi.bukkit.commands.BukkitLLCommandManager;
import com.alessiodp.lastloginapi.bukkit.configuration.BukkitLLConfigurationManager;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.lastloginapi.bukkit.events.BukkitEventManager;
import com.alessiodp.lastloginapi.bukkit.listeners.BukkitJoinLeaveListener;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class BukkitLastLoginPlugin extends LastLoginPlugin {
	@Getter private final int bstatsId = LLConstants.PLUGIN_BSTATS_BUKKIT_ID;
	
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
	protected void loadCore() {
		commandManager = new BukkitLLCommandManager(this);
		
		super.loadCore();
	}
	
	@Override
	protected void postHandle() {
		addonManager = new BukkitAddonManager(this);
		eventManager = new BukkitEventManager(this);
		
		super.postHandle();
		
		new BukkitMetricsHandler(this);
	}
	
	@Override
	protected void initializeJsonHandler() {
		if (((BukkitLastLoginBootstrap) getBootstrap()).isSpigot())
			jsonHandler = new SpigotJsonHandler();
		else
			jsonHandler = new BukkitJsonHandler();
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
