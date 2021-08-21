package com.alessiodp.lastloginapi.common.players.objects;

import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.commands.utils.ADPPermission;
import com.alessiodp.core.common.user.User;
import com.alessiodp.core.common.utils.Color;
import com.alessiodp.lastloginapi.api.events.common.IPostUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IPreUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.commands.list.CommonCommands;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import com.alessiodp.lastloginapi.common.utils.LastLoginPermission;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

@EqualsAndHashCode(doNotUseGetters = true)
public class LLPlayerImpl implements LastLoginPlayer {
	@EqualsAndHashCode.Exclude private final LastLoginPlugin plugin;
	
	@Getter private final UUID playerUUID;
	@Getter private String name;
	@Getter private long lastLogin;
	@Getter private long lastLogout;
	
	@EqualsAndHashCode.Exclude @Getter @Setter private boolean isLoggedIn;
	
	@EqualsAndHashCode.Exclude @Getter private final ReentrantLock lock = new ReentrantLock();
	@EqualsAndHashCode.Exclude @ToString.Exclude private boolean accessible = false;
	
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
	
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	
	public void updatePlayer() {
		plugin.getDatabaseManager().updatePlayer(this);
	}
	
	private void updateValue(Runnable runnable) {
		if (accessible) {
			runnable.run();
		} else {
			lock.lock();
			runnable.run();
			updatePlayer();
			lock.unlock();
		}
	}
	
	@Override
	public void setName(@NotNull String name) {
		updateValue(() -> this.name = name);
	}
	
	@Override
	public void setLastLogin(long lastLogin) {
		updateValue(() -> this.lastLogin = lastLogin);
	}
	
	@Override
	public void setLastLogout(long lastLogout) {
		updateValue(() -> this.lastLogout = lastLogout);
	}
	
	@Override
	public boolean isOnline() {
		return plugin.getOfflinePlayer(playerUUID).isOnline();
	}
	
	public void updateName() {
		String serverName = plugin.getOfflinePlayer(getPlayerUUID()).getName();
		if (serverName != null && !serverName.isEmpty() && !serverName.equals(getName())) {
			String oldName = getName();
			setName(serverName);
			
			plugin.getScheduler().runAsync(() -> {
				plugin.getLoggerManager().logDebug(String.format(LLConstants.DEBUG_PLAYER_UPDATENAME,
						getPlayerUUID().toString(),
						oldName,
						serverName
				), true);
				
				plugin.getEventManager().callEvent(plugin.getEventManager().prepareUpdateName(this, serverName, oldName));
			});
		}
	}
	
	public void updateLastLogin() {
		// Deprecated event
		IUpdateTimestamp eventDeprecated = plugin.getEventManager().prepareDeprecatedUpdateLoginTimestamp(this, System.currentTimeMillis() / 1000L);
		plugin.getEventManager().callEvent(eventDeprecated);
		
		IPreUpdateTimestamp eventPre = plugin.getEventManager().preparePreUpdateLoginTimestamp(this, eventDeprecated.getTimestamp());
		eventPre.setCancelled(eventDeprecated.isCancelled());
		plugin.getEventManager().callEvent(eventPre);
		
		if (!eventPre.isCancelled()) {
			setLastLogin(eventPre.getTimestamp());
			
			plugin.getScheduler().runAsync(() -> {
				IPostUpdateTimestamp eventPost = plugin.getEventManager().preparePostUpdateLoginTimestamp(this, eventPre.getTimestamp());
				plugin.getEventManager().callEvent(eventPost);
			});
		} else {
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_API_CANCELLED_UPDATE_LAST_LOGIN, true);
		}
	}
	
	public void updateLastLogout() {
		// Deprecated event
		IUpdateTimestamp eventDeprecated = plugin.getEventManager().prepareDeprecatedUpdateLogoutTimestamp(this, System.currentTimeMillis() / 1000L);
		plugin.getEventManager().callEvent(eventDeprecated);
		
		IPreUpdateTimestamp eventPre = plugin.getEventManager().preparePreUpdateLogoutTimestamp(this, eventDeprecated.getTimestamp());
		eventPre.setCancelled(eventDeprecated.isCancelled());
		plugin.getEventManager().callEvent(eventPre);
		
		if (!eventPre.isCancelled()) {
			setLastLogout(eventPre.getTimestamp());
			
			plugin.getScheduler().runAsync(() -> {
				IPostUpdateTimestamp eventPost = plugin.getEventManager().preparePostUpdateLogoutTimestamp(this, eventPre.getTimestamp());
				plugin.getEventManager().callEvent(eventPost);
			});
		} else {
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_API_CANCELLED_UPDATE_LAST_LOGOUT, true);
		}
	}
	
	public Set<ADPCommand> getAllowedCommands() {
		Set<ADPCommand> ret = new HashSet<>();
		User player = plugin.getPlayer(getPlayerUUID());
		
		if (player.hasPermission(LastLoginPermission.ADMIN_HELP))
			ret.add(CommonCommands.HELP);
		if (player.hasPermission(LastLoginPermission.ADMIN_INFO))
			ret.add(CommonCommands.INFO);
		if (player.hasPermission(LastLoginPermission.ADMIN_RELOAD))
			ret.add(CommonCommands.RELOAD);
		if (player.hasPermission(LastLoginPermission.ADMIN_VERSION))
			ret.add(CommonCommands.VERSION);
		
		return ret;
	}
	
	public void sendNoPermission(ADPPermission perm) {
		sendMessage(Messages.LLAPI_NOPERMISSION
				.replace("%permission%", perm.toString()));
	}
	
	public void sendMessage(String message) {
		sendMessage(message, this);
	}
	
	public void sendMessage(String message, LLPlayerImpl victim) {
		if (message == null || message.isEmpty())
			return;
		
		String formattedMessage = plugin.getMessageUtils().convertPlaceholders(message, victim);
		formattedMessage = Color.translateAlternateColorCodes(formattedMessage);
		sendDirect(formattedMessage);
	}
	
	public void sendDirect(String message) {
		User player = plugin.getPlayer(getPlayerUUID());
		if (player != null) {
			player.sendMessage(message, false);
		}
	}
}
