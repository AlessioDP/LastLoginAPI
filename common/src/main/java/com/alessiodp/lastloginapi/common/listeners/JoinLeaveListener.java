package com.alessiodp.lastloginapi.common.listeners;

import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class JoinLeaveListener {
	protected final LastLoginPlugin plugin;
	
	protected void onPlayerJoin(User user, boolean updateLastLogin) {
		if (updateLastLogin)
			updateLastLogin(plugin, user.getUUID());
		plugin.getLoginAlertsManager().sendAlerts(user);
	}
	
	protected void onPlayerQuit(User user) {
		if (!plugin.isBungeeCordEnabled()) {
			LLPlayerImpl player = plugin.getPlayerManager().getPlayer(user.getUUID());
			plugin.getPlayerManager().unloadPlayer(user.getUUID());
			
			// Update logout timestamp
			player.updateLastLogout();
		}
	}
	
	public static void updateLastLogin(LastLoginPlugin plugin, UUID uuid) {
		LLPlayerImpl player;
		if (!plugin.isBungeeCordEnabled()) {
			player = plugin.getPlayerManager().loadPlayer(uuid);
			
			// Update login timestamp
			player.updateLastLogin();
		}
	}
}
