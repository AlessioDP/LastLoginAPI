package com.alessiodp.lastloginapi.api.events.common;

import com.alessiodp.lastloginapi.api.events.LastLoginEvent;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

public interface IUpdateName extends LastLoginEvent {
	/**
	 * Get the player
	 *
	 * @return Returns the {@link LastLoginPlayer}
	 */
	@NotNull
	LastLoginPlayer getPlayer();
	
	/**
	 * Get the new name that is gonna be set
	 *
	 * @return The new name
	 */
	String getNewName();
	
	/**
	 * Get the old name
	 *
	 * @return The old name
	 */
	String getOldName();
}
