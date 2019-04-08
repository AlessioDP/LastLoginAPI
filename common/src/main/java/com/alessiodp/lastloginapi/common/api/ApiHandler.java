package com.alessiodp.lastloginapi.common.api;

import com.alessiodp.lastloginapi.api.interfaces.LastLoginAPI;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class ApiHandler implements LastLoginAPI {
	@NonNull
	private final LastLoginPlugin plugin;
	
	@Override
	public void reloadPlugin() {
		plugin.reloadConfiguration();
	}
	
	@Override
	public LastLoginPlayer getPlayer(UUID playerUuid) {
		return plugin.getPlayerManager().getPlayer(playerUuid);
	}
	
	@Override
	public Set<? extends LastLoginPlayer> getPlayerByName(String name) {
		return plugin.getDatabaseManager().getPlayerByName(name);
	}
}
