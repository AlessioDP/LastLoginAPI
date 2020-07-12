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
import com.alessiodp.lastloginapi.common.storage.interfaces.ILLDatabase;

import java.util.Set;
import java.util.UUID;

public class LLDatabaseManager extends DatabaseManager implements ILLDatabase {
	public LLDatabaseManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected IDatabaseDispatcher initializeDispatcher(StorageType storageType) {
		IDatabaseDispatcher ret = null;
		switch (storageType) {
			case MYSQL:
			case SQLITE:
			case H2:
				ret = new LLSQLDispatcher(plugin, storageType);
				break;
			default:
				// Unsupported storage type
				plugin.getLoggerManager().printError(Constants.DEBUG_DB_INIT_FAILED_UNSUPPORTED
						.replace("{type}", ConfigMain.STORAGE_TYPE_DATABASE));
				break;
		}
		return ret;
	}
	
	@Override
	public void updatePlayer(LLPlayerImpl player) {
		plugin.getScheduler().runAsync(() -> {
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_DB_UPDATEPLAYER
					.replace("{player}", player.getName())
					.replace("{uuid}", player.getPlayerUUID().toString()), true);
			
			((ILLDatabase) database).updatePlayer(player);
		});
	}
	
	@Override
	public LLPlayerImpl getPlayer(UUID playerUuid) {
		return plugin.getScheduler().runSupplyAsync(() -> {
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_DB_GETPLAYER
					.replace("{uuid}", playerUuid.toString()), true);
			
			return ((ILLDatabase) database).getPlayer(playerUuid);
		}).join();
	}
	
	@Override
	public Set<LLPlayerImpl> getPlayerByName(String playerName) {
		return plugin.getScheduler().runSupplyAsync(() -> {
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_DB_GETPLAYER_BYNAME
					.replace("{player}", playerName), true);
			
			return ((ILLDatabase) database).getPlayerByName(playerName);
		}).join();
	}
}
