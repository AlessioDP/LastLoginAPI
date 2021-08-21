package com.alessiodp.lastloginapi.bungeecord.events;

import com.alessiodp.core.bungeecord.events.BungeeEventDispatcher;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginPostUpdateLoginTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginPostUpdateLogoutTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginPreUpdateLoginTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginPreUpdateLogoutTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginUpdateLoginTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginUpdateLogoutTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bungee.BungeeLastLoginUpdateNameEvent;
import com.alessiodp.lastloginapi.api.events.common.IPostUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IPreUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.events.common.IUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.events.EventManager;
import org.jetbrains.annotations.NotNull;

public class BungeeEventManager extends EventManager {
	public BungeeEventManager(@NotNull LastLoginPlugin plugin) {
		super(plugin, new BungeeEventDispatcher(plugin));
	}
	
	@Override
	public IUpdateTimestamp prepareDeprecatedUpdateLoginTimestamp(LastLoginPlayer player, long timestamp) {
		return new BungeeLastLoginUpdateLoginTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPreUpdateTimestamp preparePreUpdateLoginTimestamp(LastLoginPlayer player, long timestamp) {
		return new BungeeLastLoginPreUpdateLoginTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPostUpdateTimestamp preparePostUpdateLoginTimestamp(LastLoginPlayer player, long timestamp) {
		return new BungeeLastLoginPostUpdateLoginTimestampEvent(player, timestamp);
	}
	
	@Override
	public IUpdateTimestamp prepareDeprecatedUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp) {
		return new BungeeLastLoginUpdateLogoutTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPreUpdateTimestamp preparePreUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp) {
		return new BungeeLastLoginPreUpdateLogoutTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPostUpdateTimestamp preparePostUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp) {
		return new BungeeLastLoginPostUpdateLogoutTimestampEvent(player, timestamp);
	}
	
	@Override
	public IUpdateName prepareUpdateName(LastLoginPlayer player, String newName, String oldName) {
		return new BungeeLastLoginUpdateNameEvent(player, newName, oldName);
	}
}
