package com.alessiodp.lastloginapi.common.players.objects;

import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.api.events.common.IUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import lombok.Getter;
import org.checkerframework.checker.nullness.qual.NonNull;

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
			String oldName = getName();
			setName(serverName);
			
			plugin.getScheduler().runAsync(() -> {
				plugin.getLoggerManager().logDebug(LLConstants.DEBUG_PLAYER_UPDATENAME
						.replace("{uuid}", getPlayerUUID().toString())
						.replace("{old}", oldName)
						.replace("{new}", serverName), true);
				
				plugin.getEventManager().callEvent(plugin.getEventManager().prepareUpdateName(this, serverName, oldName));
			});
		}
	}
	
	public void updateLastLogin() {
		IUpdateTimestamp event = plugin.getEventManager().prepareUpdateLoginTimestamp(this, System.currentTimeMillis() / 1000L);
		plugin.getEventManager().callEvent(event);
		setLastLogin(event.getTimestamp());
	}
	
	public void updateLastLogout() {
		IUpdateTimestamp event = plugin.getEventManager().prepareUpdateLogoutTimestamp(this, System.currentTimeMillis() / 1000L);
		plugin.getEventManager().callEvent(event);
		setLastLogout(event.getTimestamp());
	}
	
	@Override
	public void setName(@NonNull String name) {
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
