package com.alessiodp.lastloginapi.api.events.common;

import com.alessiodp.lastloginapi.api.events.Cancellable;
import com.alessiodp.lastloginapi.api.events.LastLoginEvent;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated Use {@link IPreUpdateTimestamp} instead
 */
@Deprecated
public interface IUpdateTimestamp extends LastLoginEvent, Cancellable {
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
	
	/**
	 * Set a new timestamp
	 *
	 * @param timestamp The timestamp to set
	 */
	void setTimestamp(long timestamp);
}
