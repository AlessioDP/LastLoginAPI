package com.alessiodp.lastloginapi.api.events.bungee;

import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.checkerframework.checker.nullness.qual.NonNull;

public class BungeeLastLoginUpdateNameEvent extends BungeeLastLoginEvent implements IUpdateName {
	private final LastLoginPlayer player;
	private String newName;
	private String oldName;
	
	public BungeeLastLoginUpdateNameEvent(LastLoginPlayer player, String newName, String oldName) {
		this.player = player;
		this.newName = newName;
		this.oldName = oldName;
	}
	
	@NonNull
	@Override
	public LastLoginPlayer getPlayer() {
		return player;
	}
	
	@Override
	public String getNewName() {
		return newName;
	}
	
	@Override
	public String getOldName() {
		return oldName;
	}
}
