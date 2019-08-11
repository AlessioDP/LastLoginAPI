package com.alessiodp.lastloginapi.common.api;

import com.alessiodp.lastloginapi.api.interfaces.LastLoginAPI;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class ApiHandler implements LastLoginAPI {
	@lombok.NonNull private final LastLoginPlugin plugin;
	
	@Override
	public void reloadPlugin() {
		plugin.reloadConfiguration();
	}
	
	@Override
	@NonNull
	public LastLoginPlayer getPlayer(@NonNull UUID playerUuid) {
		return plugin.getPlayerManager().getPlayer(playerUuid);
	}
	
	@Override
	public Set<? extends LastLoginPlayer> getPlayerByName(@NonNull String name) {
		return plugin.getDatabaseManager().getPlayerByName(name);
	}
}
