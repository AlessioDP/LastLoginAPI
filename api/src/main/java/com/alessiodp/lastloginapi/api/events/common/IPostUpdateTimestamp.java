package com.alessiodp.lastloginapi.api.events.common;

import com.alessiodp.lastloginapi.api.events.LastLoginEvent;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

public interface IPostUpdateTimestamp extends LastLoginEvent {
	/**
	 * Get the player
	 *
	 * @return Returns the {@link LastLoginPlayer}
	 */
	@NotNull
	LastLoginPlayer getPlayer();
	
	/**
	 * The timestamp that is gonna to be set
	 *
	 * @return The timestamp
	 */
	long getTimestamp();
}
