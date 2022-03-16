package com.alessiodp.lastloginapi.api.interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface LastLoginPlayer {
	/**
	 * Get the player {@link UUID}
	 *
	 * @return the {@link UUID} of the player
	 */
	@NotNull UUID getPlayerUUID();
	
	/**
	 * Get the name
	 *
	 * @return the name of the player
	 */
	@NotNull String getName();
	
	/**
	 * Set the name
	 *
	 * @param name the name to set
	 */
	void setName(@NotNull String name);
	
	/**
	 * Get the last login timestamp
	 *
	 * @return the last login timestamp, 0 if not exists
	 */
	long getLastLogin();
	
	/**
	 * Set the last login timestamp
	 *
	 * @param timestamp the timestamp to set
	 */
	void setLastLogin(long timestamp);
	
	/**
	 * Get the last logout timestamp
	 *
	 * @return the last login timestamp, 0 if not exists
	 */
	long getLastLogout();
	
	/**
	 * Set the last logout timestamp
	 *
	 * @param timestamp the timestamp to set
	 */
	void setLastLogout(long timestamp);
	
	/**
	 * Is the player online?
	 *
	 * @return true if the player is online
	 */
	boolean isOnline();
}
