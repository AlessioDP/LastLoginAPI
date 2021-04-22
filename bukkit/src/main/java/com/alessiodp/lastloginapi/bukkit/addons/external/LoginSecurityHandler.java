package com.alessiodp.lastloginapi.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.bukkit.bootstrap.BukkitLastLoginBootstrap;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.listeners.JoinLeaveListener;
import com.lenis0012.bukkit.loginsecurity.events.AuthActionEvent;
import com.lenis0012.bukkit.loginsecurity.session.AuthActionType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class LoginSecurityHandler implements Listener {
	@NonNull private final LastLoginPlugin plugin;
	private static final String ADDON_NAME = "LoginSecurity";
	private static boolean active;
	
	public void init() {
		active = false;
		if (BukkitConfigMain.LASTLOGINAPI_LOGINSECURITY_ENABLE) {
			if (Bukkit.getPluginManager().isPluginEnabled(ADDON_NAME)) {
				active = true;
				
				((BukkitLastLoginBootstrap) plugin.getBootstrap()).getServer().getPluginManager().registerEvents(this, (BukkitLastLoginBootstrap) plugin.getBootstrap());
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_HOOKED, ADDON_NAME), true);
			} else {
				BukkitConfigMain.LASTLOGINAPI_LOGINSECURITY_ENABLE = false;
				active = false;
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_FAILED, ADDON_NAME), true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLogin(AuthActionEvent event) {
		if (active
				&& BukkitConfigMain.LASTLOGINAPI_LOGINSECURITY_ENABLE
				&& (event.getType() == AuthActionType.LOGIN || event.getType() == AuthActionType.REGISTER)) {
			JoinLeaveListener.updateLastLogin(plugin, event.getPlayer().getUniqueId());
		}
	}
}