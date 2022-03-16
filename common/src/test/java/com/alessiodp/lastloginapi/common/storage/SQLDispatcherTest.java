package com.alessiodp.lastloginapi.common.storage;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.bootstrap.ADPBootstrap;
import com.alessiodp.core.common.logging.LoggerManager;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.core.common.storage.sql.connection.ConnectionFactory;
import com.alessiodp.core.common.user.OfflineUser;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.PlayerManager;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.storage.dispatchers.LLSQLDispatcher;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.H2PlayersDao;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.PlayersDao;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.PostgreSQLPlayersDao;
import com.alessiodp.lastloginapi.common.storage.sql.dao.players.SQLitePlayersDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SQLDispatcherTest {
	private static final LastLoginPlugin mockPlugin = mock(LastLoginPlugin.class);
	private static MockedStatic<ADPPlugin> staticPlugin;
	
	@BeforeAll
	public static void setUp() {
		ADPBootstrap mockBootstrap = mock(ADPBootstrap.class);
		LoggerManager mockLoggerManager = mock(LoggerManager.class);
		when(mockPlugin.getPluginFallbackName()).thenReturn("lastloginapi");
		when(mockPlugin.getFolder()).thenReturn(Paths.get("../testing/"));
		when(mockPlugin.getBootstrap()).thenReturn(mockBootstrap);
		when(mockPlugin.getLoggerManager()).thenReturn(mockLoggerManager);
		when(mockPlugin.getVersion()).thenReturn("1.0.0");
		
		// Mock debug methods
		when(mockPlugin.getResource(anyString())).thenAnswer((mock) -> ClassLoader.getSystemResourceAsStream(mock.getArgument(0)));
		when(mockLoggerManager.isDebugEnabled()).thenReturn(true);
		Mockito.doAnswer((args) -> {
			System.out.println((String) args.getArgument(0));
			return null;
		}).when(mockLoggerManager).logDebug(anyString(), anyBoolean());
		Mockito.doAnswer((args) -> {
			((Exception) args.getArgument(1)).printStackTrace();
			return null;
		}).when(mockLoggerManager).logError(any(), any());
		
		// Mock names
		OfflineUser mockOfflineUser = mock(OfflineUser.class);
		when(mockPlugin.getOfflinePlayer(any())).thenReturn(mockOfflineUser);
		when(mockOfflineUser.getName()).thenReturn("Dummy");
		
		ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX = "test_";
		
		staticPlugin = Mockito.mockStatic(ADPPlugin.class);
		when(ADPPlugin.getInstance()).thenReturn(mockPlugin);
	}
	
	@AfterAll
	public static void tearDown() {
		staticPlugin.close();
	}
	
	private LLSQLDispatcher getSQLDispatcherH2() {
		ConfigMain.STORAGE_SETTINGS_H2_DBFILE = "";
		LLSQLDispatcher ret = new LLSQLDispatcher(mockPlugin, StorageType.H2) {
			@Override
			public ConnectionFactory initConnectionFactory() {
				ConnectionFactory ret = super.initConnectionFactory();
				ret.setDatabaseUrl("jdbc:h2:mem:" + UUID.randomUUID() + ";DB_CLOSE_DELAY=-1;IGNORECASE=TRUE");
				return ret;
			}
		};
		ret.init();
		return ret;
	}
	
	private LLSQLDispatcher getSQLDispatcherSQLite(Path temporaryDirectory) {
		ConfigMain.STORAGE_SETTINGS_SQLITE_DBFILE = "";
		LLSQLDispatcher ret = new LLSQLDispatcher(mockPlugin, StorageType.SQLITE) {
			@Override
			public ConnectionFactory initConnectionFactory() {
				ConnectionFactory ret = super.initConnectionFactory();
				ret.setDatabaseUrl("jdbc:sqlite:" + temporaryDirectory.resolve("database.db"));
				return ret;
			}
		};
		ret.init();
		return ret;
	}
	
	public static LLSQLDispatcher getSQLDispatcherMySQL(LastLoginPlugin plugin) {
		// Manual test only
		/*ignored
		ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX = "test_";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CHARSET = "utf8";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_ADDRESS = "localhost";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PORT = "3306";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_DATABASE = "lastloginapi";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USERNAME = "root";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PASSWORD = "";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_POOLSIZE = 10;
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CONNLIFETIME = 1800000;
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USESSL = false;
		LLSQLDispatcher ret = new LLSQLDispatcher(plugin, StorageType.MYSQL);
		ret.init();
		
		ret.getConnectionFactory().getJdbi().onDemand(PlayersDao.class).deleteAll();
		return ret;*/
		return null;
	}
	
	public static LLSQLDispatcher getSQLDispatcherMariaDB(LastLoginPlugin plugin) {
		// Manual test only
		/*ignored
		ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX = "test_";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CHARSET = "utf8";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_ADDRESS = "localhost";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PORT = "3306";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_DATABASE = "lastloginapi";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USERNAME = "root";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PASSWORD = "";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_POOLSIZE = 10;
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CONNLIFETIME = 1800000;
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USESSL = false;
		LLSQLDispatcher ret = new LLSQLDispatcher(plugin, StorageType.MARIADB);
		ret.init();
		
		ret.getConnectionFactory().getJdbi().onDemand(PlayersDao.class).deleteAll();
		return ret;*/
		return null;
	}
	
	public static LLSQLDispatcher getSQLDispatcherPostgreSQL(LastLoginPlugin plugin) {
		// Manual test only
		/*ignored
		ConfigMain.STORAGE_SETTINGS_GENERAL_SQL_PREFIX = "test_";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CHARSET = "utf8";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_ADDRESS = "localhost";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PORT = "5432";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_DATABASE = "lastloginapi";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USERNAME = "postgres";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_PASSWORD = "";
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_POOLSIZE = 10;
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_CONNLIFETIME = 1800000;
		ConfigMain.STORAGE_SETTINGS_REMOTE_SQL_USESSL = false;
		LLSQLDispatcher ret = new LLSQLDispatcher(plugin, StorageType.POSTGRESQL);
		ret.init();
		
		ret.getConnectionFactory().getJdbi().onDemand(PostgreSQLPlayersDao.class).deleteAll();
		return ret;*/
		return null;
	}
	
	@Test
	public void testPlayer(@TempDir Path tempDir) {
		LLSQLDispatcher dispatcher = getSQLDispatcherH2();
		player(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(H2PlayersDao.class));
		dispatcher.stop();
		
		dispatcher = getSQLDispatcherSQLite(tempDir);
		player(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(SQLitePlayersDao.class));
		dispatcher.stop();
		
		dispatcher = getSQLDispatcherMySQL(mockPlugin);
		if (dispatcher != null) {
			player(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(PlayersDao.class));
			dispatcher.stop();
		}
		
		dispatcher = getSQLDispatcherMariaDB(mockPlugin);
		if (dispatcher != null) {
			player(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(PlayersDao.class));
			dispatcher.stop();
		}
		
		dispatcher = getSQLDispatcherPostgreSQL(mockPlugin);
		if (dispatcher != null) {
			player(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(PostgreSQLPlayersDao.class));
			dispatcher.stop();
		}
	}
	
	
	private void player(LLSQLDispatcher dispatcher, PlayersDao dao) {
		LLPlayerImpl player = new LLPlayerImpl(mockPlugin, UUID.randomUUID()) {};
		LLPlayerImpl mockPlayer = mock(player.getClass());
		doNothing().when(mockPlayer).updatePlayer();
		
		PlayerManager mockPlayerManager = mock(PlayerManager.class);
		when(mockPlugin.getPlayerManager()).thenReturn(mockPlayerManager);
		when(mockPlayerManager.initializePlayer(any())).thenAnswer((mock) ->
				new LLPlayerImpl(mockPlugin, mock.getArgument(0)) {}
		);
		
		
		player.setAccessible(true);
		player.setName("Test");
		player.setLastLogin(System.currentTimeMillis() / 1000L);
		player.setLastLogout(System.currentTimeMillis() / 1000L);
		player.setAccessible(false);
		assertEquals(dao.countAll(), 0);
		dispatcher.updatePlayer(player);
		assertEquals(dao.countAll(), 1);
		
		LLPlayerImpl newPlayer = dispatcher.getPlayer(player.getPlayerUUID());
		
		assertEquals(player, newPlayer);
	}
	
	@Test
	public void testPlayerName(@TempDir Path tempDir) {
		LLSQLDispatcher dispatcher = getSQLDispatcherH2();
		playerName(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(H2PlayersDao.class));
		dispatcher.stop();
		
		dispatcher = getSQLDispatcherSQLite(tempDir);
		playerName(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(SQLitePlayersDao.class));
		dispatcher.stop();
		
		dispatcher = getSQLDispatcherMySQL(mockPlugin);
		if (dispatcher != null) {
			playerName(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(PlayersDao.class));
			dispatcher.stop();
		}
		
		dispatcher = getSQLDispatcherMariaDB(mockPlugin);
		if (dispatcher != null) {
			playerName(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(PlayersDao.class));
			dispatcher.stop();
		}
		
		dispatcher = getSQLDispatcherPostgreSQL(mockPlugin);
		if (dispatcher != null) {
			playerName(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(PostgreSQLPlayersDao.class));
			dispatcher.stop();
		}
	}
	
	
	private void playerName(LLSQLDispatcher dispatcher, PlayersDao dao) {
		player(dispatcher, dao);
		
		assertFalse(dispatcher.getPlayerByName("Test").isEmpty());
	}
	
	@Test
	public void testPlayerNameInsensitive(@TempDir Path tempDir) {
		LLSQLDispatcher dispatcher = getSQLDispatcherH2();
		playerNameInsensitive(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(H2PlayersDao.class));
		dispatcher.stop();
		
		dispatcher = getSQLDispatcherSQLite(tempDir);
		playerNameInsensitive(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(SQLitePlayersDao.class));
		dispatcher.stop();
		
		dispatcher = getSQLDispatcherMySQL(mockPlugin);
		if (dispatcher != null) {
			playerNameInsensitive(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(PlayersDao.class));
			dispatcher.stop();
		}
		
		dispatcher = getSQLDispatcherMariaDB(mockPlugin);
		if (dispatcher != null) {
			playerNameInsensitive(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(PlayersDao.class));
			dispatcher.stop();
		}
		
		dispatcher = getSQLDispatcherPostgreSQL(mockPlugin);
		if (dispatcher != null) {
			playerNameInsensitive(dispatcher, dispatcher.getConnectionFactory().getJdbi().onDemand(PostgreSQLPlayersDao.class));
			dispatcher.stop();
		}
	}
	
	
	private void playerNameInsensitive(LLSQLDispatcher dispatcher, PlayersDao dao) {
		player(dispatcher, dao);
		
		assertFalse(dispatcher.getPlayerByName("test").isEmpty());
		assertFalse(dispatcher.getPlayerByName("TEST").isEmpty());
	}
}
