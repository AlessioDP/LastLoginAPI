package com.alessiodp.lastloginapi.api.events.velocity;

import com.alessiodp.lastloginapi.api.events.LastLoginEvent;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginAPI;
import org.jetbrains.annotations.NotNull;

public class VelocityLastLoginEvent implements LastLoginEvent {
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