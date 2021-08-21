package com.alessiodp.lastloginapi.api.events.bungee;

import com.alessiodp.lastloginapi.api.events.common.IUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated Use {@link BungeeLastLoginPreUpdateLoginTimestampEvent} instead
 */
@Deprecated
public class BungeeLastLoginUpdateLogoutTimestampEvent extends BungeeLastLoginEvent implements IUpdateTimestamp {
	private boolean cancelled;
	private final LastLoginPlayer player;
	private long timestamp;
	
	public BungeeLastLoginUpdateLogoutTimestampEvent(LastLoginPlayer player, long timestamp) {
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
