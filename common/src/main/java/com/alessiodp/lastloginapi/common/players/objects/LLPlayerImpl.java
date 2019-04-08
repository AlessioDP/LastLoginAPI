package com.alessiodp.lastloginapi.common.players.objects;

import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class LLPlayerImpl implements LastLoginPlayer {
	private final LastLoginPlugin plugin;
	
	@Getter private UUID playerUUID;
	@Getter private String name;
	@Getter private long lastLogin;
	@Getter private long lastLogout;
	
	private final ReentrantLock lock = new ReentrantLock();
	
	public LLPlayerImpl(LastLoginPlugin plugin, UUID uuid) {
		this.plugin = plugin;
		
		playerUUID = uuid;
		name = plugin.getOfflinePlayer(uuid).getName();
		if (name == null)
			name = "";
		lastLogin = 0;
		lastLogout = 0;
	}
	
	public LLPlayerImpl(LastLoginPlugin plugin, UUID uuid, String name, long lastLogin, long lastLogout) {
		this.plugin = plugin;
		
		playerUUID = uuid;
		this.name = name;
		this.lastLogin = lastLogin;
		this.lastLogout = lastLogout;
	}
	
	private void updatePlayer() {
		plugin.getDatabaseManager().updatePlayer(this);
	}
	
	public void updateName() {
		String serverName = plugin.getOfflinePlayer(getPlayerUUID()).getName();
		if (!serverName.isEmpty() && !serverName.equals(getName())) {
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_PLAYER_UPDATENAME
					.replace("{uuid}", getPlayerUUID().toString())
					.replace("{old}", getName())
					.replace("{new}", serverName), true);
			
			setName(serverName);
		}
	}
	
	public void updateLastLogin() {
		setLastLogin(System.currentTimeMillis() / 1000L);
	}
	
	public void updateLastLogout() {
		setLastLogout(System.currentTimeMillis() / 1000L);
	}
	
	@Override
	public void setName(String name) {
		lock.lock();
		this.name = name;
		updatePlayer();
		lock.unlock();
	}
	
	@Override
	public void setLastLogin(long lastLogin) {
		lock.lock();
		this.lastLogin = lastLogin;
		updatePlayer();
		lock.unlock();
	}
	
	@Override
	public void setLastLogout(long lastLogout) {
		lock.lock();
		this.lastLogout = lastLogout;
		updatePlayer();
		lock.unlock();
	}
	
	@Override
	public boolean isOnline() {
		return plugin.getOfflinePlayer(playerUUID).isOnline();
	}
	
	public void sendMessage(String message) {
		User player = plugin.getPlayer(getPlayerUUID());
		if (player != null) {
			player.sendMessage(message, false);
		}
	}
}
