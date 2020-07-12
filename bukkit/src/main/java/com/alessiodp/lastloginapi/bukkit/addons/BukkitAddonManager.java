package com.alessiodp.lastloginapi.bukkit.addons;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.AddonManager;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bukkit.addons.external.AuthMeHandler;
import com.alessiodp.lastloginapi.bukkit.addons.external.EssentialsChatHandler;
import com.alessiodp.lastloginapi.bukkit.addons.external.LoginSecurityHandler;
import com.alessiodp.lastloginapi.bukkit.addons.external.PlaceholderAPIHandler;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import lombok.NonNull;

public class BukkitAddonManager extends AddonManager {
	private final AuthMeHandler authMeHandler;
	private final EssentialsChatHandler essentialsChat;
	private final LoginSecurityHandler loginSecurity;
	private final PlaceholderAPIHandler placeholderAPI;
	
	public BukkitAddonManager(@NonNull ADPPlugin plugin) {
		super(plugin);
		
		authMeHandler = new AuthMeHandler((LastLoginPlugin) plugin);
		essentialsChat = new EssentialsChatHandler((LastLoginPlugin) plugin);
		loginSecurity = new LoginSecurityHandler((LastLoginPlugin) plugin);
		placeholderAPI = new PlaceholderAPIHandler((LastLoginPlugin) plugin);
	}
	
	@Override
	public void loadAddons() {
		plugin.getLoggerManager().logDebug(Constants.DEBUG_ADDON_INIT, true);
		
		authMeHandler.init();
		essentialsChat.init();
		loginSecurity.init();
		placeholderAPI.init();
	}
}
