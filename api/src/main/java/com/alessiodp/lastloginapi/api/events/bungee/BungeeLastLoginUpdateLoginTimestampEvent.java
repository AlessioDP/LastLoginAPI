package com.alessiodp.lastloginapi.api.events.bungee;

import com.alessiodp.lastloginapi.api.events.common.IUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @deprecated Use {@link BungeeLastLoginPreUpdateLoginTimestampEvent} instead
 */
@Deprecated
public class BungeeLastLoginUpdateLoginTimestampEvent extends BungeeLastLoginEvent implements IUpdateTimestamp {
	private boolean cancelled;
	private final LastLoginPlayer player;
	private long timestamp;
	
	public BungeeLastLoginUpdateLoginTimestampEvent(LastLoginPlayer player, long timestamp) {
		cancelled = false;
		this.player = player;
		this.timestamp = timestamp;
	}
	
	@NonNull
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
