package com.alessiodp.lastloginapi.common.storage;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.core.common.storage.DatabaseManager;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.core.common.storage.interfaces.IDatabaseDispatcher;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.storage.dispatchers.LLSQLDispatcher;
import com.alessiodp.lastloginapi.common.storage.interfaces.ILLDatabaseDispatcher;

import java.util.Set;
import java.util.UUID;

public class LLDatabaseManager extends DatabaseManager {
	public LLDatabaseManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	public IDatabaseDispatcher initializeDispatcher(StorageType storageType) {
		IDatabaseDispatcher ret = null;
		switch (storageType) {
			case MYSQL:
			case SQLITE:
				ret = new LLSQLDispatcher(plugin);
				break;
			default:
				// Unsupported storage type
				plugin.getLoggerManager().printError(Constants.DEBUG_DB_INIT_FAILED_UNSUPPORTED
						.replace("{type}", ConfigMain.STORAGE_TYPE_DATABASE));
				break;
		}
		
		if (ret != null) {
			ret.init(storageType);
			if (ret.isFailed())
				return null;
		}
		return ret;
	}
	
	public void updatePlayer(LLPlayerImpl player) {
		plugin.getScheduler().runAsync(() -> {
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_DB_UPDATEPLAYER
					.replace("{player}", player.getName())
					.replace("{uuid}", player.getPlayerUUID().toString()), true);
			
			((ILLDatabaseDispatcher) database).updatePlayer(player);
		});
	}
	
	public LLPlayerImpl getPlayer(UUID playerUuid) {
		return plugin.getScheduler().runSupplyAsync(() -> {
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_DB_GETPLAYER
					.replace("{uuid}", playerUuid.toString()), true);
			
			return ((ILLDatabaseDispatcher) database).getPlayer(playerUuid);
		}).join();
	}
	
	public Set<LLPlayerImpl> getPlayerByName(String playerName) {
		return plugin.getScheduler().runSupplyAsync(() -> {
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_DB_GETPLAYER_BYNAME
					.replace("{player}", playerName), true);
			
			return ((ILLDatabaseDispatcher) database).getPlayerByName(playerName);
		}).join();
	}
}
