package com.alessiodp.lastloginapi.bungeecord.events;

import com.alessiodp.core.bungeecord.events.BungeeEventDispatcher;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginUpdateLoginTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginUpdateLogoutTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginUpdateNameEvent;
import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.events.common.IUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.events.EventManager;
import org.checkerframework.checker.nullness.qual.NonNull;

public class BungeeEventManager extends EventManager {
	public BungeeEventManager(@NonNull LastLoginPlugin plugin) {
		super(plugin, new BungeeEventDispatcher(plugin));
	}
	
	@Override
	public IUpdateTimestamp prepareUpdateLoginTimestamp(LastLoginPlayer player, long timestamp) {
		return new BungeeLastLoginUpdateLoginTimestampEvent(player, timestamp);
	}
	
	@Override
	public IUpdateTimestamp prepareUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp) {
		return new BungeeLastLoginUpdateLogoutTimestampEvent(player, timestamp);
	}
	
	@Override
	public IUpdateName prepareUpdateName(LastLoginPlayer player, String newName, String oldName) {
		return new BungeeLastLoginUpdateNameEvent(player, newName, oldName);
	}
}
