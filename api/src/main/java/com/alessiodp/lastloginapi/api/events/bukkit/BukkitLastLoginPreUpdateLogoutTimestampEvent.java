package com.alessiodp.lastloginapi.api.events.bukkit;

import com.alessiodp.lastloginapi.api.events.common.IPreUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

public class BukkitLastLoginPreUpdateLogoutTimestampEvent extends BukkitLastLoginEvent implements IPreUpdateTimestamp {
	private boolean cancelled;
	private final LastLoginPlayer player;
	private long timestamp;
	
	public BukkitLastLoginPreUpdateLogoutTimestampEvent(LastLoginPlayer player, long timestamp) {
		super(false);
		cancelled = false;
		this.player = player;
		this.timestamp = timestamp;
	}
	
	@NotNull
	@Override
	public LastLoginPlayer getPlayer() {
		return player;
	}
	
	@Override
	public long getTimestamp() {
		return timestamp;
	}
	
	@Override
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
}
