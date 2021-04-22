package com.alessiodp.lastloginapi.common.storage.dispatchers;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.core.common.storage.dispatchers.SQLDispatcher;
import com.alessiodp.core.common.storage.sql.connection.ConnectionFactory;
import com.alessiodp.core.common.storage.sql.connection.H2ConnectionFactory;
import com.alessiodp.core.common.storage.sql.connection.MariaDBConnectionFactory;
import com.alessiodp.core.common.storage.sql.connection.MySQLConnectionFactory;
import com.alessiodp.core.common.storage.sql.connection.PostgreSQLConnectionFactory;
import com.alessiodp.core.common.storage.sql.connection.SQLiteConnectionFactory;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.storage.interfaces.ILLDatabase;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.H2PlayersDao;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.PlayersDao;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.PostgreSQLPlayersDao;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.SQLitePlayersDao;

import java.util.Set;
import java.util.UUID;


public class LLSQLDispatcher extends SQLDispatcher implements ILLDatabase {
	
	protected Class<? extends PlayersDao> playersDao = PlayersDao.class;
	
	public LLSQLDispatcher(ADPPlugin plugin, StorageType storageType) {
		super(plugin, storageType);
	}
	
	@Override
	public ConnectionFactory initConnectionFactory() {
		ConnectionFactory ret = null;
		switch (storageType) {
			case MARIADB:
				ret = new MariaDBConnectionFactory();
				((MariaDBConnectionFactory) ret).setTablePrefix(ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX);
				((MariaDBConnectionFactory) ret).setCharset(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CHARSET);
				((MariaDBConnectionFactory) ret).setServerName(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_ADDRESS);
				((MariaDBConnectionFactory) ret).setPort(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PORT);
				((MariaDBConnectionFactory) ret).setDatabaseName(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_DATABASE);
				((MariaDBConnectionFactory) ret).setUsername(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USERNAME);
				((MariaDBConnectionFactory) ret).setPassword(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PASSWORD);
				((MariaDBConnectionFactory) ret).setMaximumPoolSize(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_POOLSIZE);
				((MariaDBConnectionFactory) ret).setMaxLifetime(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CONNLIFETIME);
				break;
			case MYSQL:
				ret = new MySQLConnectionFactory();
				((MySQLConnectionFactory) ret).setTablePrefix(ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX);
				((MySQLConnectionFactory) ret).setCharset(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CHARSET);
				((MySQLConnectionFactory) ret).setServerName(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_ADDRESS);
				((MySQLConnectionFactory) ret).setPort(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PORT);
				((MySQLConnectionFactory) ret).setDatabaseName(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_DATABASE);
				((MySQLConnectionFactory) ret).setUsername(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USERNAME);
				((MySQLConnectionFactory) ret).setPassword(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PASSWORD);
				((MySQLConnectionFactory) ret).setMaximumPoolSize(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_POOLSIZE);
				((MySQLConnectionFactory) ret).setMaxLifetime(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CONNLIFETIME);
				((MySQLConnectionFactory) ret).setUseSSL(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USESSL);
				break;
			case POSTGRESQL:
				ret = new PostgreSQLConnectionFactory();
				((PostgreSQLConnectionFactory) ret).setTablePrefix(ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX);
				((PostgreSQLConnectionFactory) ret).setCharset(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CHARSET);
				((PostgreSQLConnectionFactory) ret).setServerName(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_ADDRESS);
				((PostgreSQLConnectionFactory) ret).setPort(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PORT);
				((PostgreSQLConnectionFactory) ret).setDatabaseName(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_DATABASE);
				((PostgreSQLConnectionFactory) ret).setUsername(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USERNAME);
				((PostgreSQLConnectionFactory) ret).setPassword(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PASSWORD);
				((PostgreSQLConnectionFactory) ret).setMaximumPoolSize(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_POOLSIZE);
				((PostgreSQLConnectionFactory) ret).setMaxLifetime(ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CONNLIFETIME);
				playersDao = PostgreSQLPlayersDao.class;
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
		switch (storageType) {
			case H2:
			case MARIADB:
			case POSTGRESQL:
				return -1;
			case MYSQL:
			case SQLITE:
			default:
				return 0;
		}
	}
}