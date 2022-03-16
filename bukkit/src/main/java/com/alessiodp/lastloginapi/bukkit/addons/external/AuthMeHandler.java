package com.alessiodp.lastloginapi.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bukkit.bootstrap.BukkitLastLoginBootstrap;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.listeners.JoinLeaveListener;
import fr.xephi.authme.events.LoginEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class AuthMeHandler implements Listener {
	@NotNull private final LastLoginPlugin plugin;
	private static final String ADDON_NAME = "AuthMe";
	private static boolean active;
	
	public void init() {
		active = false;
		if (BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_AUTHME) {
			if (Bukkit.getPluginManager().isPluginEnabled(ADDON_NAME)) {
				active = true;
				
				((BukkitLastLoginBootstrap) plugin.getBootstrap()).getServer().getPluginManager().registerEvents(this, (BukkitLastLoginBootstrap) plugin.getBootstrap());
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_HOOKED, ADDON_NAME), true);
			} else {
				BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_AUTHME = false;
				active = false;
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_FAILED, ADDON_NAME), true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLogin(LoginEvent event) {
		if (active && BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_AUTHME) {
			JoinLeaveListener.updateLastLogin(plugin, event.getPlayer().getUniqueId());
		}
	}
}