package com.alessiodp.lastloginapi.bukkit.listeners;

import com.alessiodp.core.bukkit.user.BukkitUser;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.listeners.JoinLeaveListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitJoinLeaveListener extends JoinLeaveListener implements Listener {
	
	public BukkitJoinLeaveListener(LastLoginPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	public void onPlayerJoinLow(PlayerJoinEvent event) {
		super.onPlayerJoinLow(new BukkitUser(plugin, event.getPlayer()));
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerJoinMonitor(PlayerJoinEvent event) {
		super.onPlayerJoinMonitor(
				new BukkitUser(plugin, event.getPlayer()),
				canUpdateLastLogin()
		);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		super.onPlayerQuit(new BukkitUser(plugin, event.getPlayer()));
	}
	
	private boolean canUpdateLastLogin() {
		return !BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_AUTHME
				&& !BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_LOGINSECURITY
				&& !BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_NLOGIN
				&& !BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_OPENLOGIN;
	}
}
