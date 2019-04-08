package com.alessiodp.lastloginapi.bukkit.addons;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.AddonManager;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bukkit.addons.external.EssentialsChatHandler;
import com.alessiodp.lastloginapi.bukkit.addons.external.PlaceholderAPIHandler;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import lombok.NonNull;

public class BukkitAddonManager extends AddonManager {
	private final EssentialsChatHandler essentialsChat;
	private final PlaceholderAPIHandler placeholderAPI;
	
	public BukkitAddonManager(@NonNull ADPPlugin plugin) {
		super(plugin);
		
		essentialsChat = new EssentialsChatHandler((LastLoginPlugin) plugin);
		placeholderAPI = new PlaceholderAPIHandler((LastLoginPlugin) plugin);
	}
	
	@Override
	public void loadAddons() {
		plugin.getLoggerManager().logDebug(Constants.DEBUG_ADDON_INIT, true);
		
		essentialsChat.init();
		placeholderAPI.init();
	}
}
