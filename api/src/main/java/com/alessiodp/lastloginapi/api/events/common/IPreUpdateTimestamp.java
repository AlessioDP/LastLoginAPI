package com.alessiodp.lastloginapi.api.events.common;

import com.alessiodp.lastloginapi.api.events.Cancellable;
import com.alessiodp.lastloginapi.api.events.LastLoginEvent;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

public interface IPreUpdateTimestamp extends LastLoginEvent, Cancellable {
	/**
	 * Get the player
	 *
	 * @return the {@link LastLoginPlayer}
	 */
	@NotNull
	LastLoginPlayer getPlayer();
	
	/**
	 * The timestamp that is gonna to be set
	 *
	 * @return the timestamp
	 */
	long getTimestamp();
	
	/**
	 * Set a new timestamp
	 *
	 * @param timestamp the timestamp to set
	 */
	void setTimestamp(long timestamp);
}
