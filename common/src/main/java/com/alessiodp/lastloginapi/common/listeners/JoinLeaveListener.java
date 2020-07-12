package com.alessiodp.lastloginapi.common.listeners;

import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class JoinLeaveListener {
	protected final LastLoginPlugin plugin;
	
	protected void onPlayerJoinLow(User user) {
		plugin.getLoginAlertsManager().sendAlerts(user);
	}
	
	protected void onPlayerJoinMonitor(User user, boolean updateLastLogin) {
		if (updateLastLogin) {
			updateLastLogin(plugin, user.getUUID());
		}
	}
	
	protected void onPlayerQuit(User user) {
		if (!plugin.isBungeeCordEnabled()) {
			UUID uuid = user.getUUID();
			plugin.getScheduler().runAsync(() -> {
				LLPlayerImpl player = plugin.getPlayerManager().getPlayer(uuid);
				plugin.getPlayerManager().unloadPlayer(user.getUUID());
				
				if (player.isLoggedIn()) {
					// Update logout timestamp
					player.updateLastLogout();
				}
			});
		}
	}
	
	public static void updateLastLogin(LastLoginPlugin plugin, UUID uuid) {
		if (!plugin.isBungeeCordEnabled()) {
			plugin.getScheduler().runAsync(() -> {
				LLPlayerImpl player = plugin.getPlayerManager().loadPlayer(uuid);
				
				player.setLoggedIn(true);
				// Update login timestamp
				player.updateLastLogin();
			});
		}
	}
}
