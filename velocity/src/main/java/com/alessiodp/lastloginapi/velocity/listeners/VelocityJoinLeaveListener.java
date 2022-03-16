package com.alessiodp.lastloginapi.velocity.listeners;

import com.alessiodp.core.velocity.user.VelocityUser;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.listeners.JoinLeaveListener;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;

public class VelocityJoinLeaveListener extends JoinLeaveListener {
	
	public VelocityJoinLeaveListener(LastLoginPlugin plugin) {
		super(plugin);
	}
	
	@Subscribe(order = PostOrder.LATE)
	public void onPlayerJoinLow(LoginEvent event) {
		VelocityUser user = new VelocityUser(plugin, event.getPlayer());
		super.onPlayerJoinLow(user);
		super.onPlayerJoinMonitor(user, true);
	}
	
	@Subscribe
	public void onPlayerQuit(DisconnectEvent event) {
		super.onPlayerQuit(new VelocityUser(plugin, event.getPlayer()));
	}
}
