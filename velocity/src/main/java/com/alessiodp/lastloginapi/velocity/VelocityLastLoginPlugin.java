package com.alessiodp.lastloginapi.velocity;

import com.alessiodp.core.common.bootstrap.ADPBootstrap;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.core.velocity.addons.internal.json.VelocityJsonHandler;
import com.alessiodp.core.velocity.addons.internal.title.VelocityTitleHandler;
import com.alessiodp.core.velocity.bootstrap.ADPVelocityBootstrap;
import com.alessiodp.core.velocity.scheduling.ADPVelocityScheduler;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.utils.MessageUtils;
import com.alessiodp.lastloginapi.velocity.addons.VelocityLLAddonManager;
import com.alessiodp.lastloginapi.velocity.addons.external.VelocityMetricsHandler;
import com.alessiodp.lastloginapi.velocity.commands.VelocityLLCommandManager;
import com.alessiodp.lastloginapi.velocity.configuration.VelocityLLConfigurationManager;
import com.alessiodp.lastloginapi.velocity.events.VelocityEventManager;
import com.alessiodp.lastloginapi.velocity.listeners.VelocityJoinLeaveListener;
import com.velocitypowered.api.event.EventManager;
import lombok.Getter;

public class VelocityLastLoginPlugin extends LastLoginPlugin {
	@Getter private final int bstatsId = LLConstants.PLUGIN_BSTATS_VELOCITY_ID;
	
	public VelocityLastLoginPlugin(ADPBootstrap bootstrap) {
		super(bootstrap);
	}
	
	@Override
	protected void initializeCore() {
		scheduler = new ADPVelocityScheduler(this);
		configurationManager = new VelocityLLConfigurationManager(this);
		messageUtils = new MessageUtils(this);
		
		super.initializeCore();
	}
	
	@Override
	protected void loadCore() {
		commandManager = new VelocityLLCommandManager(this);
		
		super.loadCore();
	}
	
	@Override
	protected void postHandle() {
		addonManager = new VelocityLLAddonManager(this);
		eventManager = new VelocityEventManager(this);
		
		super.postHandle();
		
		new VelocityMetricsHandler(this);
	}
	
	@Override
	protected  void initializeJsonHandler() {
		jsonHandler = new VelocityJsonHandler(this);
	}
	
	@Override
	protected  void initializeTitleHandler() {
		titleHandler = new VelocityTitleHandler(this);
	}
	
	@Override
	protected void registerListeners() {
		getLoggerManager().logDebug(Constants.DEBUG_PLUGIN_REGISTERING, true);
		ADPVelocityBootstrap plugin = (ADPVelocityBootstrap) getBootstrap();
		EventManager em = plugin.getServer().getEventManager();
		em.register(plugin, new VelocityJoinLeaveListener(this));
	}
	
	@Override
	public boolean isBungeeCordEnabled() {
		return false;
	}
}
