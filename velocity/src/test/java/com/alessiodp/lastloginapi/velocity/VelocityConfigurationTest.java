package com.alessiodp.lastloginapi.velocity;

import com.alessiodp.core.common.addons.external.simpleyaml.configuration.file.YamlFile;
import com.alessiodp.core.common.configuration.ConfigOption;
import com.alessiodp.core.common.configuration.ConfigurationFile;
import com.alessiodp.core.common.logging.logger.ADPLogger;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.velocity.configuration.data.VelocityConfigMain;
import com.alessiodp.lastloginapi.velocity.configuration.data.VelocityMessages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VelocityConfigurationTest {
	private static final LastLoginPlugin mockPlugin = mock(LastLoginPlugin.class);
	
	@BeforeAll
	public static void setUp() {
		ADPLogger testLogger = mock(ADPLogger.class);
		doAnswer((params) -> {
			//System.out.println((String) params.getArgument(0));
			return null;
		}).when(testLogger).info(anyString());
		doAnswer((params) -> {
			System.out.println((String) params.getArgument(0));
			return null;
		}).when(testLogger).error(anyString());
		when(mockPlugin.getLogger()).thenReturn(testLogger);
		
		when(mockPlugin.getResource(anyString())).thenAnswer((a) -> ClassLoader.getSystemResourceAsStream(a.getArgument(0)));
	}
	
	@Test
	public void testConfigMain(@TempDir Path tempDir) throws IllegalAccessException, IOException {
		VelocityConfigMain configMain = new VelocityConfigMain(mockPlugin);
		
		List<String> skipPaths = Collections.emptyList();
		
		testConfiguration(configMain, skipPaths, tempDir);
		testAutomaticUpgrade(configMain);
	}
	
	@Test
	public void testMessages(@TempDir Path tempDir) throws IllegalAccessException, IOException {
		VelocityMessages messages = new VelocityMessages(mockPlugin);
		
		List<String> skipPaths = Collections.emptyList();
		
		testConfiguration(messages, skipPaths, tempDir);
		testAutomaticUpgrade(messages);
	}
	
	private void testConfiguration(ConfigurationFile configurationFile, List<String> skipPaths, Path tempDir) throws IllegalAccessException {
		Field[] fields = configurationFile.getClass().getFields();
		
		// Initialize YAML
		configurationFile.initializeConfiguration(tempDir);
		YamlFile yf = configurationFile.getConfiguration();
		
		// Check fields
		for (Field f : fields) {
			ConfigOption co = f.getAnnotation(ConfigOption.class);
			if (co != null && !skippablePath(co.path(), skipPaths)) {
				Object value = yf.get(co.path());
				assertNotNull(value, "The " + configurationFile.getClass().getSimpleName() + " path '" + co.path() + "' is null.");
				f.set(null, value);
			}
		}
	}
	
	private void testAutomaticUpgrade(ConfigurationFile configurationFile) throws IOException {
		configurationFile.getConfiguration().set("dont-edit-this.version", -1);
		configurationFile.getConfiguration().save();
		
		configurationFile.checkVersion(true);
	}
	
	private boolean skippablePath(String path, List<String> skipPaths) {
		for (String sp : skipPaths) {
			if (path.startsWith(sp))
				return true;
		}
		return false;
	}
}