package com.alessiodp.lastloginapi.api.events.bungee;

import com.alessiodp.lastloginapi.api.events.LastLoginEvent;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginAPI;
import net.md_5.bungee.api.plugin.Event;
import org.jetbrains.annotations.NotNull;

public class BungeeLastLoginEvent extends Event implements LastLoginEvent {
	private LastLoginAPI api;
	
	@Override
	@NotNull
	public LastLoginAPI getApi() {
		return api;
	}
	
	@Override
	public void setApi(LastLoginAPI instance) {
		api = instance;
	}
}