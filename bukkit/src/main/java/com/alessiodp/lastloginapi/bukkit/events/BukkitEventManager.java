package com.alessiodp.lastloginapi.bukkit.events;

import com.alessiodp.core.bukkit.events.BukkitEventDispatcher;
import com.alessiodp.lastloginapi.api.events.bukkit.BukkitLastLoginPostUpdateLoginTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bukkit.BukkitLastLoginPostUpdateLogoutTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bukkit.BukkitLastLoginPreUpdateLoginTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bukkit.BukkitLastLoginUpdateNameEvent;
import com.alessiodp.lastloginapi.api.events.bukkit.BukkitLastLoginPreUpdateLogoutTimestampEvent;
import com.alessiodp.lastloginapi.api.events.common.IPostUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IPreUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.events.EventManager;
import org.jetbrains.annotations.NotNull;

public class BukkitEventManager extends EventManager {
	public BukkitEventManager(@NotNull LastLoginPlugin plugin) {
		super(plugin, new BukkitEventDispatcher(plugin));
	}
	
	@Override
	public IPreUpdateTimestamp preparePreUpdateLoginTimestamp(LastLoginPlayer player, long timestamp) {
		return new BukkitLastLoginPreUpdateLoginTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPostUpdateTimestamp preparePostUpdateLoginTimestamp(LastLoginPlayer player, long timestamp) {
		return new BukkitLastLoginPostUpdateLoginTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPreUpdateTimestamp preparePreUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp) {
		return new BukkitLastLoginPreUpdateLogoutTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPostUpdateTimestamp preparePostUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp) {
		return new BukkitLastLoginPostUpdateLogoutTimestampEvent(player, timestamp);
	}
	
	@Override
	public IUpdateName prepareUpdateName(LastLoginPlayer player, String newName, String oldName) {
		return new BukkitLastLoginUpdateNameEvent(player, newName, oldName);
	}
}
