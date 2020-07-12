package com.alessiodp.lastloginapi.api.events.bukkit;

import com.alessiodp.lastloginapi.api.events.common.IPostUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.checkerframework.checker.nullness.qual.NonNull;

public class BukkitLastLoginPostUpdateLogoutTimestampEvent extends BukkitLastLoginEvent implements IPostUpdateTimestamp {
	private final LastLoginPlayer player;
	private long timestamp;
	
	public BukkitLastLoginPostUpdateLogoutTimestampEvent(LastLoginPlayer player, long timestamp) {
		super(true);
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
