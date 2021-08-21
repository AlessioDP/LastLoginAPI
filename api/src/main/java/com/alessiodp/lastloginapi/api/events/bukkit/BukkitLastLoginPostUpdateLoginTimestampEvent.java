package com.alessiodp.lastloginapi.api.events.bukkit;

import com.alessiodp.lastloginapi.api.events.common.IPostUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

public class BukkitLastLoginPostUpdateLoginTimestampEvent extends BukkitLastLoginEvent implements IPostUpdateTimestamp {
	private final LastLoginPlayer player;
	private final long timestamp;
	
	public BukkitLastLoginPostUpdateLoginTimestampEvent(LastLoginPlayer player, long timestamp) {
		super(true);
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
