package com.alessiodp.lastloginapi.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bukkit.addons.external.hooks.PAPIHook;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;

@RequiredArgsConstructor
public class PlaceholderAPIHandler {
	@NonNull private final LastLoginPlugin plugin;
	private static final String ADDON_NAME = "PlaceholderAPI";
	private static PAPIHook hook;
	
	public void init() {
		if (Bukkit.getPluginManager().isPluginEnabled(ADDON_NAME)) {
			hook = new PAPIHook(plugin);
			if (hook.register()) {
				
				plugin.getLoggerManager().log(Constants.DEBUG_ADDON_HOOKED
						.replace("{addon}", ADDON_NAME), true);
			}
		}
	}
}