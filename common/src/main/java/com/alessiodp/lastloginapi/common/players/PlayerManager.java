package com.alessiodp.lastloginapi.common.players;

import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class PlayerManager {
	private final LastLoginPlugin plugin;
	
	@Getter private HashMap<UUID, LLPlayerImpl> listPlayers;
	
	public PlayerManager(@NotNull LastLoginPlugin plugin) {
		this.plugin = plugin;
		listPlayers = new HashMap<>();
	}
	
	public void reload() {
		listPlayers = new HashMap<>();
		
		for (User user : plugin.getOnlinePlayers()) {
			LLPlayerImpl player = loadPlayer(user.getUUID());
			player.setLoggedIn(true); // Mark current players as logged so the logout timestamp will be saved
		}
	}
	
	public LLPlayerImpl initializePlayer(UUID playerUUID) {
		return new LLPlayerImpl(plugin, playerUUID);
	}
	
	public LLPlayerImpl initializePlayer(UUID playerUUID, String name, long lastLogin, long lastLogout) {
		return new LLPlayerImpl(plugin, playerUUID, name, lastLogin, lastLogout);
	}
	
	public LLPlayerImpl loadPlayer(UUID uuid) {
		LLPlayerImpl ret = getPlayer(uuid);
		getListPlayers().put(uuid, ret);
		ret.updateName(); // Check for name updates
		return ret;
	}
	
	public void unloadPlayer(UUID uuid) {
		getListPlayers().remove(uuid);
	}
	
	public LLPlayerImpl getPlayer(UUID uuid) {
		LLPlayerImpl ret;
		if (getListPlayers().containsKey(uuid)) {
			// Get player from online list
			ret = getListPlayers().get(uuid);
		} else {
			// Get player from database
			ret = plugin.getDatabaseManager().getPlayer(uuid);
			
			if (ret != null && plugin.isBungeeCordEnabled()) {
				// Check for username changes
				ret.updateName();
			}
			
			// Load new player
			if (ret == null)
				ret = initializePlayer(uuid);
		}
		return ret;
	}
	
	public Set<LLPlayerImpl> getPlayerByName(String name) {
		LLPlayerImpl player = listPlayers.values().stream()
				.filter((p) -> p.getName().equalsIgnoreCase(name))
				.findAny().orElse(null);
		
		if (player == null) {
			return plugin.getDatabaseManager().getPlayerByName(name);
		}
		return Collections.singleton(player);
	}
}
