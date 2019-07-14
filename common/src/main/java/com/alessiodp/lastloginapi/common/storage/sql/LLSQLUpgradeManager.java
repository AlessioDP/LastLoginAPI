package com.alessiodp.lastloginapi.common.storage.sql;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.core.common.storage.dispatchers.SQLDispatcher;
import com.alessiodp.core.common.storage.sql.ISQLTable;
import com.alessiodp.core.common.storage.sql.mysql.SQLUpgradeManager;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LLSQLUpgradeManager extends SQLUpgradeManager {
	public LLSQLUpgradeManager(@NonNull ADPPlugin plugin, @NonNull SQLDispatcher dispatcher, @NonNull StorageType storageType) {
		super(plugin, dispatcher, storageType, ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_SAVEOLD, ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_UPGRADE_OLDSUFFIX);
	}
	
	@Override
	protected void upgradeTable(Connection connection, ResultSet rs, ISQLTable table, int currentVersion) throws SQLException {
		// Not necessary
	}
	
	@Override
	protected boolean isVersionTable(ISQLTable table) {
		return table.equals(SQLTable.VERSIONS);
	}
	
	@Override
	protected String formatQuery(String query) {
		return SQLTable.formatGenericQuery(query);
	}
}
