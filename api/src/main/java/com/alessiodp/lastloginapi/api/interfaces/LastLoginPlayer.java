package com.alessiodp.lastloginapi.api.interfaces;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.UUID;

public interface LastLoginPlayer {
	/**
	 * Get the player {@link UUID}
	 *
	 * @return Returns the {@link UUID} of the player
	 */
	@NonNull UUID getPlayerUUID();
	
	/**
	 * Get the name
	 *
	 * @return Returns the name of the player
	 */
	@NonNull String getName();
	
	/**
	 * Set the name
	 *
	 * @param name The name to set
	 */
	void setName(@NonNull String name);
	
	/**
	 * Get the last login timestamp
	 *
	 * @return Returns the last login timestamp, 0 if not exists
	 */
	long getLastLogin();
	
	/**
	 * Set the last login timestamp
	 *
	 * @param timestamp The timestamp to set
	 */
	void setLastLogin(long timestamp);
	
	/**
	 * Get the last logout timestamp
	 *
	 * @return Returns the last login timestamp, 0 if not exists
	 */
	long getLastLogout();
	
	/**
	 * Set the last logout timestamp
	 *
	 * @param timestamp The timestamp to set
	 */
	void setLastLogout(long timestamp);
	
	/**
	 * Is the player online?
	 *
	 * @return Returns true if the player is online
	 */
	boolean isOnline();
}
