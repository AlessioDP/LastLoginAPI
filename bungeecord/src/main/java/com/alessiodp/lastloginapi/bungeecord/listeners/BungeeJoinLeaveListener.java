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
	public void onPlayerJoin(PostLoginEvent event) {
		super.onPlayerJoin(new BungeeUser(plugin, event.getPlayer()), true);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerDisconnectEvent event) {
		super.onPlayerQuit(new BungeeUser(plugin, event.getPlayer()));
	}
}
