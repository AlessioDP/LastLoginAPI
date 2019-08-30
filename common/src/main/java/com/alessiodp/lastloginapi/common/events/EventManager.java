package com.alessiodp.lastloginapi.common.events;

import com.alessiodp.core.common.events.EventDispatcher;
import com.alessiodp.lastloginapi.api.events.LastLoginEvent;
import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.events.common.IUpdateTimestamp;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class EventManager {
	@NonNull protected final LastLoginPlugin plugin;
	@NonNull protected final EventDispatcher eventDispatcher;
	
	public final void callEvent(LastLoginEvent event) {
		event.setApi(plugin.getApi());
		eventDispatcher.callEvent(event);
	}
	
	public abstract IUpdateTimestamp prepareUpdateLoginTimestamp(LastLoginPlayer player, long timestamp);
	public abstract IUpdateTimestamp prepareUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp);
	public abstract IUpdateName prepareUpdateName(LastLoginPlayer player, String newName, String oldName);
}
