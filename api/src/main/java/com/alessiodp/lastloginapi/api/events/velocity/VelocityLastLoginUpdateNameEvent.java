package com.alessiodp.lastloginapi.api.events.velocity;

import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

public class VelocityLastLoginUpdateNameEvent extends VelocityLastLoginEvent implements IUpdateName {
	private final LastLoginPlayer player;
	private final String newName;
	private final String oldName;
	
	public VelocityLastLoginUpdateNameEvent(LastLoginPlayer player, String newName, String oldName) {
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
