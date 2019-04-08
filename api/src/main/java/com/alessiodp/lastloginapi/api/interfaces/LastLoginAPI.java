package com.alessiodp.lastloginapi.api.interfaces;

import java.util.Set;
import java.util.UUID;

public interface LastLoginAPI {
	/**
	 * Reload plugin configuration files
	 */
	void reloadPlugin();
	
	/**
	 * Get the player by his {@link UUID}
	 *
	 * @param playerUuid The {@link UUID} of the player
	 * @return Returns the {@link LastLoginPlayer} instance of the relative player
	 */
	LastLoginPlayer getPlayer(UUID playerUuid);
	
	/**
	 * Get the player by his name.
	 * It can be possible to have multiple players with the same name.
	 *
	 * @param name The name of the player
	 * @return Returns a list of {@link LastLoginPlayer} that have the given name
	 */
	Set<? extends LastLoginPlayer> getPlayerByName(String name);
}
