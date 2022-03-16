package com.alessiodp.lastloginapi.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bukkit.addons.external.hooks.PAPIHook;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@RequiredArgsConstructor
public class PlaceholderAPIHandler {
	@NotNull private final LastLoginPlugin plugin;
	private static final String ADDON_NAME = "PlaceholderAPI";
	private static boolean active;
	private static PAPIHook hook;
	
	public void init() {
		if (!active && Bukkit.getPluginManager().isPluginEnabled(ADDON_NAME)) {
			hook = new PAPIHook(plugin);
			
			// This is executed only 1 time, must be sync
			if (hook.register()) {
				active = true;
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_HOOKED, ADDON_NAME), true);
			}
		}
	}
	
	public static String getPlaceholders(UUID uuid, String message) {
		String ret = message;
		if (active && hook != null)
			ret = hook.parsePlaceholders(Bukkit.getOfflinePlayer(uuid), message);
		return ret;
	}
}