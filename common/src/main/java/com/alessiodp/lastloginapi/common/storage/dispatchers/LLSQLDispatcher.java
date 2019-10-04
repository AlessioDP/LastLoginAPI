package com.alessiodp.lastloginapi.common.storage.dispatchers;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.core.common.storage.dispatchers.SQLDispatcher;
import com.alessiodp.core.common.storage.sql.ISQLTable;
import com.alessiodp.core.common.storage.sql.mysql.MySQLDao;
import com.alessiodp.core.common.storage.sql.mysql.MySQLHikariConfiguration;
import com.alessiodp.core.common.storage.sql.sqlite.SQLiteDao;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.storage.interfaces.ILLDatabaseDispatcher;
import com.alessiodp.lastloginapi.common.storage.sql.LLSQLUpgradeManager;
import com.alessiodp.lastloginapi.common.storage.sql.SQLTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LLSQLDispatcher extends SQLDispatcher implements ILLDatabaseDispatcher {
	
	public LLSQLDispatcher(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void init(StorageType type) {
		upgradeManager = new LLSQLUpgradeManager(plugin, this, type);
		
		switch (type) {
			case MYSQL:
				SQLTable.setupTables(
						LLConstants.VERSION_DATABASE_MYSQL,
						plugin.getResource("schemas/" + type.name().toLowerCase(Locale.ENGLISH) + ".sql")
				);
				MySQLHikariConfiguration hikari = new MySQLHikariConfiguration(
						plugin.getPluginFallbackName(),
						ConfigMain.STORAGE_SETTINGS_MYSQL_ADDRESS,
						ConfigMain.STORAGE_SETTINGS_MYSQL_PORT,
						ConfigMain.STORAGE_SETTINGS_MYSQL_DATABASE,
						ConfigMain.STORAGE_SETTINGS_MYSQL_USERNAME,
						ConfigMain.STORAGE_SETTINGS_MYSQL_PASSWORD
				);
				hikari.setMaximumPoolSize(ConfigMain.STORAGE_SETTINGS_MYSQL_POOLSIZE);
				hikari.setMaxLifetime(ConfigMain.STORAGE_SETTINGS_MYSQL_CONNLIFETIME);
				hikari.setCharacterEncoding(ConfigMain.STORAGE_SETTINGS_MYSQL_CHARSET);
				hikari.setUseSSL(ConfigMain.STORAGE_SETTINGS_MYSQL_USESSL);
				database = new MySQLDao(plugin, hikari);
				database.initSQL();
				break;
			case SQLITE:
				SQLTable.setupTables(
						LLConstants.VERSION_DATABASE_SQLITE,
						plugin.getResource("schemas/" + type.name().toLowerCase(Locale.ENGLISH) + ".sql")
				);
				database = new SQLiteDao(plugin, ConfigMain.STORAGE_SETTINGS_SQLITE_DBFILE);
				database.initSQL();
				break;
			default:
				// Unsupported storage type
		}
		
		if (database != null && !database.isFailed()) {
			databaseType = type;
			
			// Prepare tables list
			LinkedList<ISQLTable> tables = new LinkedList<>();
			tables.add(SQLTable.VERSIONS); // Version must be first
			tables.add(SQLTable.PLAYERS);
			
			try (Connection connection = getConnection()) {
				initTables(connection, tables);
			} catch (Exception ex) {
				plugin.getLoggerManager().printErrorStacktrace(Constants.DEBUG_SQL_ERROR, ex);
			}
		}
	}
	
	@Override
	public void updatePlayer(LLPlayerImpl player) {
		try {
			boolean existData = false;
			if (!player.getName().isEmpty())
				existData = true;
			
			try (Connection connection = getConnection()) {
				if (connection != null) {
					if (existData) {
						// Save data
						String query = LLConstants.QUERY_PLAYER_INSERT_SQLITE;
						if (databaseType == StorageType.MYSQL)
							query = LLConstants.QUERY_PLAYER_INSERT_MYSQL;
						try (PreparedStatement preStatement = connection.prepareStatement(SQLTable.formatGenericQuery(query))) {
							preStatement.setString(1, player.getPlayerUUID().toString());
							preStatement.setString(2, player.getName());
							preStatement.setLong(3, player.getLastLogin());
							preStatement.setLong(4, player.getLastLogout());
							
							preStatement.executeUpdate();
						}
					} else {
						// Remove data
						String query = LLConstants.QUERY_PLAYER_DELETE;
						
						try (PreparedStatement preStatement = connection.prepareStatement(SQLTable.formatGenericQuery(query))) {
							preStatement.setString(1, player.getPlayerUUID().toString());
							
							preStatement.executeUpdate();
						}
					}
				}
			}
		} catch (SQLException ex) {
			plugin.getLoggerManager().printErrorStacktrace(Constants.DEBUG_SQL_ERROR, ex);
		}
	}
	
	@Override
	public LLPlayerImpl getPlayer(UUID playerUuid) {
		LLPlayerImpl ret = null;
		try (Connection connection = getConnection()) {
			if (connection != null) {
				ret = getPlayer(connection, playerUuid);
			}
		} catch (SQLException ex) {
			plugin.getLoggerManager().printErrorStacktrace(Constants.DEBUG_SQL_ERROR, ex);
		}
		return ret;
	}
	
	@Override
	public Set<LLPlayerImpl> getPlayerByName(String playerName) {
		Set<LLPlayerImpl> ret = new HashSet<>();
		try (Connection connection = getConnection()) {
			if (connection != null) {
				String query = LLConstants.QUERY_PLAYER_GET_BYNAME;
				
				try (PreparedStatement preStatement = connection.prepareStatement(SQLTable.formatGenericQuery(query))) {
					preStatement.setString(1, playerName);
					
					try (ResultSet rs = preStatement.executeQuery()) {
						while (rs.next()) {
							LLPlayerImpl player = getPlayerFromResultSet(rs);
							if (player != null)
								ret.add(player);
						}
					}
				}
			}
		} catch (SQLException ex) {
			plugin.getLoggerManager().printErrorStacktrace(Constants.DEBUG_SQL_ERROR, ex);
		}
		return ret;
	}
	
	private LLPlayerImpl getPlayer(Connection connection, UUID playerUuid) {
		LLPlayerImpl ret = null;
		String query = LLConstants.QUERY_PLAYER_GET;
		
		try (PreparedStatement preStatement = connection.prepareStatement(SQLTable.formatGenericQuery(query))) {
			preStatement.setString(1, playerUuid.toString());
			
			try (ResultSet rs = preStatement.executeQuery()) {
				if (rs.next()) {
					ret = getPlayerFromResultSet(rs);
				}
			}
		} catch (SQLException ex) {
			plugin.getLoggerManager().printErrorStacktrace(Constants.DEBUG_SQL_ERROR, ex);
		}
		return ret;
	}
	
	private LLPlayerImpl getPlayerFromResultSet(ResultSet rs) {
		LLPlayerImpl ret = null;
		String uuid = "";
		try {
			uuid = rs.getString("uuid");
			String name = rs.getString("name");
			long lastLogin = rs.getLong("lastLogin");
			long lastLogout = rs.getLong("lastLogout");
			ret = ((LastLoginPlugin) plugin).getPlayerManager().initializePlayer(
					UUID.fromString(uuid),
					name,
					lastLogin,
					lastLogout);
		} catch (IllegalArgumentException ex) {
			plugin.getLoggerManager().printErrorStacktrace(Constants.DEBUG_SQL_ERROR_UUID
					.replace("{uuid}", uuid), ex);
		} catch (Exception ex) {
			plugin.getLoggerManager().printErrorStacktrace(Constants.DEBUG_SQL_ERROR, ex);
		}
		return ret;
	}
}