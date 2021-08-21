package com.alessiodp.lastloginapi.api.events.bungee;

import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

public class BungeeLastLoginUpdateNameEvent extends BungeeLastLoginEvent implements IUpdateName {
	private final LastLoginPlayer player;
	private final String newName;
	private final String oldName;
	
	public BungeeLastLoginUpdateNameEvent(LastLoginPlayer player, String newName, String oldName) {
		this.player = player;
		this.newName = newName;
		this.oldName = oldName;
	}
	
	@NotNull
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
