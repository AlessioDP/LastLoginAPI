package com.alessiodp.lastloginapi.common;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.bootstrap.ADPBootstrap;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.core.common.libraries.LibraryUsage;
import com.alessiodp.core.common.logging.ConsoleColor;
import com.alessiodp.lastloginapi.api.LastLogin;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginAPI;
import com.alessiodp.lastloginapi.common.api.ApiHandler;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import com.alessiodp.lastloginapi.common.events.EventManager;
import com.alessiodp.lastloginapi.common.utils.LastLoginPermission;
import com.alessiodp.lastloginapi.common.players.PlayerManager;
import com.alessiodp.lastloginapi.common.storage.LLDatabaseManager;
import com.alessiodp.lastloginapi.common.utils.LLPlayerUtils;
import com.alessiodp.lastloginapi.common.utils.MessageUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public abstract class LastLoginPlugin extends ADPPlugin {
	// Plugin fields
	@Getter private final String pluginName = LLConstants.PLUGIN_NAME;
	@Getter private final String pluginFallbackName = LLConstants.PLUGIN_FALLBACK;
	@Getter private final ConsoleColor consoleColor = LLConstants.PLUGIN_CONSOLECOLOR;
	@Getter private final String packageName = LLConstants.PLUGIN_PACKAGENAME;
	
	// LastLoginPlugin fields
	@Getter protected LastLoginAPI api;
	@Getter protected EventManager eventManager;
	@Getter protected MessageUtils messageUtils;
	@Getter protected PlayerManager playerManager;
	
	public LastLoginPlugin(ADPBootstrap bootstrap) {
		super(bootstrap);
	}
	
	@Override
	public void onDisabling() {
		// Nothing to disable
	}
	
	@Override
	protected void initializeCore() {
		databaseManager = new LLDatabaseManager(this);
	}
	
	@Override
	protected void loadCore() {
		playerManager = new PlayerManager(this);
		
		getConfigurationManager().reload();
		reloadLoggerManager();
		getDatabaseManager().reload();
	}
	
	@Override
	protected void postHandle() {
		api = new ApiHandler(this);
		playerUtils = new LLPlayerUtils(this);
		
		getPlayerManager().reload();
		getCommandManager().setup();
		registerListeners();
		
		reloadAdpUpdater();
		getAddonManager().loadAddons();
		LastLogin.setApi(api);
	}
	
	protected abstract void registerListeners();
	
	@Override
	public void reloadConfiguration() {
		getLoggerManager().logDebug(Constants.DEBUG_PLUGIN_RELOADING, true);
		getLoginAlertsManager().reload();
		getConfigurationManager().reload();
		reloadLoggerManager();
		getDatabaseManager().reload();
		
		getPlayerManager().reload();
		getAddonManager().loadAddons();
		getCommandManager().setup();
		
		reloadAdpUpdater();
	}
	
	@Override
	public LLDatabaseManager getDatabaseManager() {
		return (LLDatabaseManager) databaseManager;
	}
	
	private void reloadLoggerManager() {
		getLoggerManager().reload(
				ConfigMain.LASTLOGINAPI_LOGGING_DEBUG,
				ConfigMain.LASTLOGINAPI_LOGGING_SAVE_ENABLE,
				ConfigMain.LASTLOGINAPI_LOGGING_SAVE_FILE,
				ConfigMain.LASTLOGINAPI_LOGGING_SAVE_FORMAT
		);
	}
	
	private void reloadAdpUpdater() {
		getAdpUpdater().reload(
				getPluginFallbackName(),
				LLConstants.PLUGIN_SPIGOTCODE,
				ConfigMain.LASTLOGINAPI_UPDATES_CHECK,
				ConfigMain.LASTLOGINAPI_UPDATES_WARN,
				LastLoginPermission.ADMIN_WARNINGS,
				Messages.LLAPI_UPDATEAVAILABLE
		);
		getAdpUpdater().asyncTaskCheckUpdates();
	}
	
	public abstract boolean isBungeeCordEnabled();
	
	@Override
	public List<LibraryUsage> getLibrariesUsages() {
		return Arrays.asList(
				LibraryUsage.H2,
				LibraryUsage.MYSQL,
				LibraryUsage.MARIADB,
				LibraryUsage.POSTGRESQL,
				LibraryUsage.SQLITE
		);
	}
}
