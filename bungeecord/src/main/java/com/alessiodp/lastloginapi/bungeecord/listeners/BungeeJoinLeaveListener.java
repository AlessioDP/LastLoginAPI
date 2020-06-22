package com.alessiodp.lastloginapi.bungeecord.listeners;

import com.alessiodp.core.bungeecord.user.BungeeUser;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.listeners.JoinLeaveListener;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class BungeeJoinLeaveListener extends JoinLeaveListener implements Listener {
	
	public BungeeJoinLeaveListener(LastLoginPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerJoinLow(PostLoginEvent event) {
		BungeeUser user = new BungeeUser(plugin, event.getPlayer());
		super.onPlayerJoinLow(user);
		super.onPlayerJoinMonitor(user, true);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerDisconnectEvent event) {
		super.onPlayerQuit(new BungeeUser(plugin, event.getPlayer()));
	}
}
