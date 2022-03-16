package com.alessiodp.lastloginapi.velocity.events;

import com.alessiodp.core.velocity.events.VelocityEventDispatcher;
import com.alessiodp.lastloginapi.api.events.common.IPostUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IPreUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.events.velocity.VelocityLastLoginPostUpdateLoginTimestampEvent;
import com.alessiodp.lastloginapi.api.events.velocity.VelocityLastLoginPostUpdateLogoutTimestampEvent;
import com.alessiodp.lastloginapi.api.events.velocity.VelocityLastLoginPreUpdateLoginTimestampEvent;
import com.alessiodp.lastloginapi.api.events.velocity.VelocityLastLoginPreUpdateLogoutTimestampEvent;
import com.alessiodp.lastloginapi.api.events.velocity.VelocityLastLoginUpdateNameEvent;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.events.EventManager;
import org.jetbrains.annotations.NotNull;

public class VelocityEventManager extends EventManager {
	public VelocityEventManager(@NotNull LastLoginPlugin plugin) {
		super(plugin, new VelocityEventDispatcher(plugin));
	}
	
	@Override
	public IPreUpdateTimestamp preparePreUpdateLoginTimestamp(LastLoginPlayer player, long timestamp) {
		return new VelocityLastLoginPreUpdateLoginTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPostUpdateTimestamp preparePostUpdateLoginTimestamp(LastLoginPlayer player, long timestamp) {
		return new VelocityLastLoginPostUpdateLoginTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPreUpdateTimestamp preparePreUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp) {
		return new VelocityLastLoginPreUpdateLogoutTimestampEvent(player, timestamp);
	}
	
	@Override
	public IPostUpdateTimestamp preparePostUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp) {
		return new VelocityLastLoginPostUpdateLogoutTimestampEvent(player, timestamp);
	}
	
	@Override
	public IUpdateName prepareUpdateName(LastLoginPlayer player, String newName, String oldName) {
		return new VelocityLastLoginUpdateNameEvent(player, newName, oldName);
	}
}
