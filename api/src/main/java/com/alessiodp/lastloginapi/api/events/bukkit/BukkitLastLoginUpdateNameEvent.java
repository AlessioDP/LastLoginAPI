package com.alessiodp.lastloginapi.api.events.bukkit;

import com.alessiodp.lastloginapi.api.events.common.IUpdateName;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.checkerframework.checker.nullness.qual.NonNull;

public class BukkitLastLoginUpdateNameEvent extends BukkitLastLoginEvent implements IUpdateName {
	private final LastLoginPlayer player;
	private final String newName;
	private final String oldName;
	
	public BukkitLastLoginUpdateNameEvent(LastLoginPlayer player, String newName, String oldName) {
		super(true);
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
