package com.alessiodp.lastloginapi.bukkit.addons;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.AddonManager;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bukkit.addons.external.AuthMeHandler;
import com.alessiodp.lastloginapi.bukkit.addons.external.EssentialsChatHandler;
import com.alessiodp.lastloginapi.bukkit.addons.external.LoginSecurityHandler;
import com.alessiodp.lastloginapi.bukkit.addons.external.NLoginHandler;
import com.alessiodp.lastloginapi.bukkit.addons.external.OpeNLoginHandler;
import com.alessiodp.lastloginapi.bukkit.addons.external.PlaceholderAPIHandler;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import org.jetbrains.annotations.NotNull;

public class BukkitLLAddonManager extends AddonManager {
	private final AuthMeHandler authMeHandler;
	private final EssentialsChatHandler essentialsChat;
	private final LoginSecurityHandler loginSecurity;
	private final NLoginHandler nLoginHandler;
	private final OpeNLoginHandler opeNLoginHandler;
	private final PlaceholderAPIHandler placeholderAPI;
	
	public BukkitLLAddonManager(@NotNull ADPPlugin plugin) {
		super(plugin);
		
		authMeHandler = new AuthMeHandler((LastLoginPlugin) plugin);
		essentialsChat = new EssentialsChatHandler((LastLoginPlugin) plugin);
		loginSecurity = new LoginSecurityHandler((LastLoginPlugin) plugin);
		nLoginHandler = new NLoginHandler((LastLoginPlugin) plugin);
		opeNLoginHandler = new OpeNLoginHandler((LastLoginPlugin) plugin);
		placeholderAPI = new PlaceholderAPIHandler((LastLoginPlugin) plugin);
	}
	
	@Override
	public void loadAddons() {
		plugin.getLoggerManager().logDebug(Constants.DEBUG_ADDON_INIT, true);
		
		placeholderAPI.init();
		
		// Schedule sync later (post load plugins)
		plugin.getScheduler().getSyncExecutor().execute(this::postLoadAddons);
	}
	
	public void postLoadAddons() {
		authMeHandler.init();
		essentialsChat.init();
		loginSecurity.init();
		nLoginHandler.init();
		opeNLoginHandler.init();
	}
}
