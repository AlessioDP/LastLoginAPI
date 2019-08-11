package com.alessiodp.lastloginapi.common.listeners;

import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.LastLoginPermission;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class JoinLeaveListener {
	protected final LastLoginPlugin plugin;
	
	protected void onPlayerJoin(User user) {
		// Make it async
		plugin.getScheduler().runAsync(() -> {
			LLPlayerImpl player;
			if (!plugin.isBungeeCordEnabled()) {
				player = plugin.getPlayerManager().loadPlayer(user.getUUID());
			
				// Update login timestamp
				player.updateLastLogin();
			}
			
			plugin.getLoginAlertsManager().sendAlerts(user);
		});
	}
	
	protected void onPlayerQuit(User user) {
		// Make it async
		plugin.getScheduler().runAsync(() -> {
			if (!plugin.isBungeeCordEnabled()) {
				LLPlayerImpl player = plugin.getPlayerManager().getPlayer(user.getUUID());
				plugin.getPlayerManager().unloadPlayer(user.getUUID());
				
				// Update logout timestamp
				player.updateLastLogout();
			}
		});
	}
}
