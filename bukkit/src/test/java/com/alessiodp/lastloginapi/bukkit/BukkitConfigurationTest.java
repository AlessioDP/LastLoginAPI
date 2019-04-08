package com.alessiodp.lastloginapi.bukkit;

import com.alessiodp.core.bukkit.configuration.adapter.BukkitConfigurationAdapter;
import com.alessiodp.core.common.configuration.adapter.ConfigurationAdapter;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
		BukkitConfigMain.class,
		BukkitConfigurationAdapter.class,
		LastLoginPlugin.class
})
public class BukkitConfigurationTest {
	private LastLoginPlugin mockPlugin;
	private final Pattern pattern = Pattern.compile("[A-Z_]+");
	
	@Before
	public void setUp() {
		mockPlugin = mock(LastLoginPlugin.class);
	}
	
	@Test
	public void testConfigMain() throws URISyntaxException {
		BukkitConfigMain configMain = new BukkitConfigMain(mockPlugin);
		Field[] fields = PowerMockito.fields(configMain.getClass());
		
		// Load defaults
		configMain.loadDefaults();
		
		// Save default values
		HashMap<String, Object> savedMap = populateMap(fields, configMain);
		
		// Get config file
		Path path = Paths.get(getClass().getResource("/" + configMain.getResourceName()).toURI());
		Assert.assertNotNull(path);
		
		// Initialize configuration
		ConfigurationAdapter configurationAdapter = new BukkitConfigurationAdapter(path);
		
		// Load configuration
		configMain.loadConfiguration(configurationAdapter);
		
		// Match configuration
		match(fields, savedMap, configMain);
	}
	
	private HashMap<String, Object> populateMap(Field[] fields, Object configMainInstance) {
		HashMap<String, Object> ret = new HashMap<>();
		for (Field f : fields) {
			if (pattern.matcher(f.getName()).matches()) {
				try {
					ret.put(f.getName(), f.get(configMainInstance));
				} catch (Exception ex) {
					ex.printStackTrace();
					fail(ex.getMessage());
				}
			}
		}
		return ret;
	}
	
	private void match(Field[] fields, HashMap<String, Object> savedMap, Object configMainInstance) {
		for (Field f : fields) {
			try {
				if (savedMap.containsKey(f.getName()) && !savedMap.get(f.getName()).equals(f.get(configMainInstance))) {
					fail("Fields are mismatched: " + f.getName() + "\n" + savedMap.get(f.getName()) + " != " + f.get(configMainInstance));
				}
			} catch (Exception ex) {
				fail("Error at field " + f.getName());
				ex.printStackTrace();
			}
		}
	}
}
