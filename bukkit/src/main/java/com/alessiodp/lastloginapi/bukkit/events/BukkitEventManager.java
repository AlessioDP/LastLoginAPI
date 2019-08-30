package com.alessiodp.lastloginapi.bukkit.events;

import com.alessiodp.core.bukkit.events.BukkitEventDispatcher;
import com.alessiodp.lastloginapi.api.events.bukkit.BukkitLastLoginUpdateLoginTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bukkit.BukkitLastLoginUpdateLogoutTimestampEvent;
import com.alessiodp.lastloginapi.api.events.bukkit.BukkitLastLoginUpdateNameEvent;
import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.events.common.IUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.events.EventManager;
import org.checkerframework.checker.nullness.qual.NonNull;

public class BukkitEventManager extends EventManager {
	public BukkitEventManager(@NonNull LastLoginPlugin plugin) {
		super(plugin, new BukkitEventDispatcher(plugin));
	}
	
	@Override
	public IUpdateTimestamp prepareUpdateLoginTimestamp(LastLoginPlayer player, long timestamp) {
		return new BukkitLastLoginUpdateLoginTimestampEvent(player, timestamp);
	}
	
	@Override
	public IUpdateTimestamp prepareUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp) {
		return new BukkitLastLoginUpdateLogoutTimestampEvent(player, timestamp);
	}
	
	@Override
	public IUpdateName prepareUpdateName(LastLoginPlayer player, String newName, String oldName) {
		return new BukkitLastLoginUpdateNameEvent(player, newName, oldName);
	}
}
