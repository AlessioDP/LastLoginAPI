package com.alessiodp.lastloginapi.api.interfaces;

import org.jetbrains.annotations.NotNull;

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
	 * @param playerUuid the {@link UUID} of the player
	 * @return the {@link LastLoginPlayer} instance of the relative player
	 */
	@NotNull LastLoginPlayer getPlayer(@NotNull UUID playerUuid);
	
	/**
	 * Get the player by his name.
	 * It can be possible to have multiple players with the same name.
	 *
	 * @param name the name of the player
	 * @return a list of {@link LastLoginPlayer} that have the given name
	 */
	Set<? extends LastLoginPlayer> getPlayerByName(@NotNull String name);
}
