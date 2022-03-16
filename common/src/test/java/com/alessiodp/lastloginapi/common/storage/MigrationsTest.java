package com.alessiodp.lastloginapi.common.storage;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.logging.LoggerManager;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.core.common.user.OfflineUser;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.storage.dispatchers.LLSQLDispatcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MigrationsTest {
	private static final LastLoginPlugin mockPlugin = mock(LastLoginPlugin.class);
	private final Path testingPath = Paths.get("../testing/");
	private static MockedStatic<ADPPlugin> staticPlugin;
	
	@BeforeAll
	public static void setUp(@TempDir Path tempDir) {
		LoggerManager mockLoggerManager = mock(LoggerManager.class);
		when(mockPlugin.getLoggerManager()).thenReturn(mockLoggerManager);
		when(mockPlugin.getPluginFallbackName()).thenReturn("lastloginapi");
		when(mockPlugin.getFolder()).thenReturn(tempDir);
		when(mockPlugin.getResource(anyString())).thenAnswer((mock) -> ClassLoader.getSystemResourceAsStream(mock.getArgument(0)));
		when(mockLoggerManager.isDebugEnabled()).thenReturn(true);
		
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
	
	private void prepareDatabase(String database) throws IOException {
		Files.copy(
				testingPath.resolve(database),
				mockPlugin.getFolder().resolve(ConfigMain.STORAGE_SETTINGS_SQLITE_DBFILE),
				StandardCopyOption.REPLACE_EXISTING
		);
	}
	
	
	@Test
	public void testDatabase1_3_X() throws IOException {
		ConfigMain.STORAGE_SETTINGS_SQLITE_DBFILE = "database_1_3_X.db";
		prepareDatabase(ConfigMain.STORAGE_SETTINGS_SQLITE_DBFILE);
		
		LLSQLDispatcher dispatcher = new LLSQLDispatcher(mockPlugin, StorageType.SQLITE);
		dispatcher.init();
		
		dispatcher.getConnectionFactory().getJdbi().useHandle(handle -> {
			handle.execute("SELECT 1 FROM <prefix>players");
		});
		
		dispatcher.stop();
	}
	
	@Test
	public void testDatabase1_4_X() throws IOException {
		ConfigMain.STORAGE_SETTINGS_SQLITE_DBFILE = "database_1_4_X.db";
		prepareDatabase(ConfigMain.STORAGE_SETTINGS_SQLITE_DBFILE);
		
		LLSQLDispatcher dispatcher = new LLSQLDispatcher(mockPlugin, StorageType.SQLITE);
		dispatcher.init();
		
		dispatcher.getConnectionFactory().getJdbi().useHandle(handle -> {
			handle.execute("SELECT 1 FROM <prefix>players");
		});
		
		dispatcher.stop();
	}
	
	@Test
	public void testDatabaseFreshSQLite() {
		ConfigMain.STORAGE_SETTINGS_SQLITE_DBFILE = "database_sqlite.db";
		
		LLSQLDispatcher dispatcher = new LLSQLDispatcher(mockPlugin, StorageType.SQLITE);
		dispatcher.init();
		
		dispatcher.getConnectionFactory().getJdbi().useHandle(handle -> {
			handle.execute("SELECT 1 FROM <prefix>players");
		});
		
		dispatcher.stop();
	}
	
	@Test
	public void testDatabaseFreshH2() {
		ConfigMain.STORAGE_SETTINGS_H2_DBFILE = "database_h2.db";
		
		LLSQLDispatcher dispatcher = new LLSQLDispatcher(mockPlugin, StorageType.H2);
		dispatcher.init();
		
		dispatcher.getConnectionFactory().getJdbi().useHandle(handle -> {
			handle.execute("SELECT 1 FROM <prefix>players");
		});
		
		dispatcher.stop();
	}
	
	@Test
	public void testDatabaseFreshMySQL() {
		LLSQLDispatcher dispatcher = SQLDispatcherTest.getSQLDispatcherMySQL(mockPlugin);
		if (dispatcher != null) {
			dispatcher.init();
			
			dispatcher.getConnectionFactory().getJdbi().useHandle(handle -> {
				handle.execute("SELECT 1 FROM <prefix>players");
			});
			
			dispatcher.stop();
		}
	}
	
	@Test
	public void testDatabaseFreshMariaDB() {
		LLSQLDispatcher dispatcher = SQLDispatcherTest.getSQLDispatcherMariaDB(mockPlugin);
		if (dispatcher != null) {
			dispatcher.init();
			
			dispatcher.getConnectionFactory().getJdbi().useHandle(handle -> {
				handle.execute("SELECT 1 FROM <prefix>players");
			});
			
			dispatcher.stop();
		}
	}
	
	@Test
	public void testDatabaseFreshPostgreSQL() {
		LLSQLDispatcher dispatcher = SQLDispatcherTest.getSQLDispatcherPostgreSQL(mockPlugin);
		if (dispatcher != null) {
			dispatcher.init();
			
			dispatcher.getConnectionFactory().getJdbi().useHandle(handle -> {
				handle.execute("SELECT 1 FROM <prefix>players");
			});
			
			dispatcher.stop();
		}
	}
}