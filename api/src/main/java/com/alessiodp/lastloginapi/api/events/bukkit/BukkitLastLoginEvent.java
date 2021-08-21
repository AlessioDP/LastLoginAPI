package com.alessiodp.lastloginapi.api.events.bukkit;

import com.alessiodp.lastloginapi.api.events.LastLoginEvent;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginAPI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BukkitLastLoginEvent extends Event implements LastLoginEvent {
	private LastLoginAPI api;
	private static final HandlerList HANDLERS = new HandlerList();
	
	public BukkitLastLoginEvent(boolean async) {
		super(async);
	}
	
	@Override
	@NotNull
	public LastLoginAPI getApi() {
		return api;
	}
	
	@Override
	public void setApi(LastLoginAPI instance) {
		api = instance;
	}
	
	@Override
	@NotNull
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}