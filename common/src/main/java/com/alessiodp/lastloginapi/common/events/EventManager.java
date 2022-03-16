package com.alessiodp.lastloginapi.common.events;

import com.alessiodp.core.common.events.EventDispatcher;
import com.alessiodp.lastloginapi.api.events.LastLoginEvent;
import com.alessiodp.lastloginapi.api.events.common.IPostUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IPreUpdateTimestamp;
import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public abstract class EventManager {
	@NotNull protected final LastLoginPlugin plugin;
	@NotNull protected final EventDispatcher eventDispatcher;
	
	public final void callEvent(LastLoginEvent event) {
		event.setApi(plugin.getApi());
		eventDispatcher.callEvent(event);
	}
	
	public abstract IPreUpdateTimestamp preparePreUpdateLoginTimestamp(LastLoginPlayer player, long timestamp);
	public abstract IPostUpdateTimestamp preparePostUpdateLoginTimestamp(LastLoginPlayer player, long timestamp);
	
	public abstract IPreUpdateTimestamp preparePreUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp);
	public abstract IPostUpdateTimestamp preparePostUpdateLogoutTimestamp(LastLoginPlayer player, long timestamp);
	
	public abstract IUpdateName prepareUpdateName(LastLoginPlayer player, String newName, String oldName);
}
