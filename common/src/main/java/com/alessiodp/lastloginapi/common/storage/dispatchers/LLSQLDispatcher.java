package com.alessiodp.lastloginapi.common.storage.dispatchers;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.core.common.storage.dispatchers.SQLDispatcher;
import com.alessiodp.core.common.storage.interfaces.IDatabaseDispatcher;
import com.alessiodp.core.common.storage.sql.connection.ConnectionFactory;
import com.alessiodp.core.common.storage.sql.connection.H2ConnectionFactory;
import com.alessiodp.core.common.storage.sql.connection.MySQLConnectionFactory;
import com.alessiodp.core.common.storage.sql.connection.SQLiteConnectionFactory;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.storage.interfaces.ILLDatabase;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.H2PlayersDao;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.PlayersDao;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.SQLitePlayersDao;

import java.util.Set;
import java.util.UUID;


public class LLSQLDispatcher extends SQLDispatcher implements ILLDatabase, IDatabaseDispatcher {
	
	protected Class<? extends PlayersDao> playersDao = PlayersDao.class;
	
	public LLSQLDispatcher(ADPPlugin plugin, StorageType storageType) {
		super(plugin, storageType);
	}
	
	@Override
	public ConnectionFactory initConnectionFactory() {
		ConnectionFactory ret = null;
		switch (storageType) {
			case MYSQL:
				ret = new MySQLConnectionFactory();
				((MySQLConnectionFactory) ret).setTablePrefix(ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX);
				((MySQLConnectionFactory) ret).setCharset(ConfigMain.STORAGE_SETTINGS_MYSQL_CHARSET);
				((MySQLConnectionFactory) ret).setServerName(ConfigMain.STORAGE_SETTINGS_MYSQL_ADDRESS);
				((MySQLConnectionFactory) ret).setPort(ConfigMain.STORAGE_SETTINGS_MYSQL_PORT);
				((MySQLConnectionFactory) ret).setDatabaseName(ConfigMain.STORAGE_SETTINGS_MYSQL_DATABASE);
				((MySQLConnectionFactory) ret).setUsername(ConfigMain.STORAGE_SETTINGS_MYSQL_USERNAME);
				((MySQLConnectionFactory) ret).setPassword(ConfigMain.STORAGE_SETTINGS_MYSQL_PASSWORD);
				((MySQLConnectionFactory) ret).setMaximumPoolSize(ConfigMain.STORAGE_SETTINGS_MYSQL_POOLSIZE);
				((MySQLConnectionFactory) ret).setMaxLifetime(ConfigMain.STORAGE_SETTINGS_MYSQL_CONNLIFETIME);
				((MySQLConnectionFactory) ret).setUseSSL(ConfigMain.STORAGE_SETTINGS_MYSQL_USESSL);
				break;
			case SQLITE:
				ret = new SQLiteConnectionFactory(plugin, plugin.getFolder().resolve(ConfigMain.STORAGE_SETTINGS_SQLITE_DBFILE));
				((SQLiteConnectionFactory) ret).setTablePrefix(ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX);
				playersDao = SQLitePlayersDao.class;
				break;
			case H2:
				ret = new H2ConnectionFactory(plugin, plugin.getFolder().resolve(ConfigMain.STORAGE_SETTINGS_H2_DBFILE));
				((H2ConnectionFactory) ret).setTablePrefix(ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX);
				playersDao = H2PlayersDao.class;
				break;
			default:
				// Unsupported storage type
		}
		return ret;
	}
	
	@Override
	public void updatePlayer(LLPlayerImpl player) {
		this.connectionFactory.getJdbi().useHandle(handle -> handle.attach(playersDao).insert(
				player.getPlayerUUID().toString(),
				player.getName(),
				player.getLastLogin(),
				player.getLastLogout()
		));
	}
	
	@Override
	public LLPlayerImpl getPlayer(UUID playerUuid) {
		return this.connectionFactory.getJdbi().withHandle(handle -> handle.attach(playersDao).getPlayer(playerUuid.toString())).orElse(null);
	}
	
	@Override
	public Set<LLPlayerImpl> getPlayerByName(String playerName) {
		return this.connectionFactory.getJdbi().withHandle(handle -> handle.attach(playersDao).getPlayerByName(playerName));
	}
	
	@Override
	protected int getBackwardMigration() {
		return storageType == StorageType.H2 ? -1 : 0;
	}
}