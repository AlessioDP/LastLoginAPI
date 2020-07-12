package com.alessiodp.lastloginapi.api.events.bungee;

import com.alessiodp.lastloginapi.api.events.common.IPostUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.checkerframework.checker.nullness.qual.NonNull;

public class BungeeLastLoginPostUpdateLogoutTimestampEvent extends BungeeLastLoginEvent implements IPostUpdateTimestamp {
	private final LastLoginPlayer player;
	private long timestamp;
	
	public BungeeLastLoginPostUpdateLogoutTimestampEvent(LastLoginPlayer player, long timestamp) {
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
}
