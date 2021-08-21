package com.alessiodp.lastloginapi.api.events.bungee;

import com.alessiodp.lastloginapi.api.events.common.IPostUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

public class BungeeLastLoginPostUpdateLogoutTimestampEvent extends BungeeLastLoginEvent implements IPostUpdateTimestamp {
	private final LastLoginPlayer player;
	private final long timestamp;
	
	public BungeeLastLoginPostUpdateLogoutTimestampEvent(LastLoginPlayer player, long timestamp) {
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
}
