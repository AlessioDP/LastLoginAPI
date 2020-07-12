package com.alessiodp.lastloginapi.common.addons;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.user.OfflineUser;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.addons.internal.LLPlaceholder;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.utils.MessageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.MockRepository;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
		ConfigMain.class,
		LastLoginPlugin.class,
		LLPlayerImpl.class,
		LLPlaceholder.class
})
public class LLPlaceholderTest {
	private LastLoginPlugin mockPlugin;
	private LLPlayerImpl player;
	
	private static final String PLAYER_NAME = "Test";
	private static final Long PLAYER_LOGIN = 10000000L;
	private static final Long PLAYER_LOGOUT = 10000000L;
	
	@Before
	public void setUp() {
		MockRepository.clear();
		mockPlugin = mock(LastLoginPlugin.class);
		
		// Mock getInstance
		mockStatic(ADPPlugin.class);
		when(ADPPlugin.getInstance()).thenReturn(mockPlugin);
		
		// Setup player
		player = new LLPlayerImpl(mockPlugin, UUID.randomUUID(), PLAYER_NAME, PLAYER_LOGIN, PLAYER_LOGOUT);
		
		// Setup MessageUtils
		when(mockPlugin.getMessageUtils()).thenReturn(new MessageUtils());
	}
	
	@Test
	public void testNullness() {
		String identifier = "impossible_placeholder";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNull(placeholder);
	}
	
	@Test
	public void testName() {
		String identifier = "name";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		
		// Setup config
		ConfigMain.PLACEHOLDERS_NAME_FORMAT = "%name%";
		ConfigMain.PLACEHOLDERS_NAME_FORMAT_UNKNOWN = "UNKNOWN";
		
		assertEquals(PLAYER_NAME, placeholder.formatPlaceholder(player, identifier));
		
		player.setAccessible(true);
		player.setName("");
		player.setAccessible(false);
		assertEquals(ConfigMain.PLACEHOLDERS_NAME_FORMAT_UNKNOWN, placeholder.formatPlaceholder(player, identifier));
	}
	
	@Test
	public void testLastLoginDate() {
		String identifier = "last_login_date";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		
		// Mock offline player
		OfflineUser mockOfflineUser = mock(OfflineUser.class);
		when(mockPlugin.getOfflinePlayer(any())).thenReturn(mockOfflineUser);
		
		// Setup config
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT = "yyyy-MM-dd";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE = "ONLINE";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN = "UNKNOWN";
		
		when(mockOfflineUser.isOnline()).thenReturn(true);
		assertEquals(ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE, placeholder.formatPlaceholder(player, identifier));
		
		when(mockOfflineUser.isOnline()).thenReturn(false);
		assertEquals("1970-04-26", placeholder.formatPlaceholder(player, identifier));
		
		player.setAccessible(true);
		player.setLastLogin(0);
		player.setAccessible(false);
		assertEquals(ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN, placeholder.formatPlaceholder(player, identifier));
	}
	
	@Test
	public void testLastLoginElapsed() {
		String identifier = "last_login_elapsed";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		
		// Mock offline player
		OfflineUser mockOfflineUser = mock(OfflineUser.class);
		when(mockPlugin.getOfflinePlayer(any())).thenReturn(mockOfflineUser);
		
		// Setup config
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_LARGE = "d'd' H'h'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_MEDIUM = "H'h' m'm'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_SMALL = "m'm' s's'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_SMALLEST = "s's'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_LARGE = "d'd' H'h'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_MEDIUM = "H'h' m'm'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_SMALL = "m'm' s's'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_SMALLEST = "s's'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN = "UNKNOWN";
		
		// Results
		final String large = "5d 10h";
		final String medium = "10h 30m";
		final String small = "30m 30s";
		final String smallest = "30s";
		
		player.setAccessible(true);
		// Large
		player.setLastLogin((System.currentTimeMillis() / 1000L) - (5 * 24 * 60 * 60L) - (10 * 60 * 60L) - (30 * 60L) - 30L);
		when(mockOfflineUser.isOnline()).thenReturn(true);
		assertEquals(large, placeholder.formatPlaceholder(player, identifier));
		when(mockOfflineUser.isOnline()).thenReturn(false);
		assertEquals(large, placeholder.formatPlaceholder(player, identifier));
		
		// Medium
		player.setLastLogin((System.currentTimeMillis() / 1000L) - (10 * 60 * 60L) - (30 * 60L) - 30L);
		when(mockOfflineUser.isOnline()).thenReturn(true);
		assertEquals(medium, placeholder.formatPlaceholder(player, identifier));
		when(mockOfflineUser.isOnline()).thenReturn(false);
		assertEquals(medium, placeholder.formatPlaceholder(player, identifier));
		
		// Small
		player.setLastLogin((System.currentTimeMillis() / 1000L) - (30 * 60L) - 30L);
		when(mockOfflineUser.isOnline()).thenReturn(true);
		assertEquals(small, placeholder.formatPlaceholder(player, identifier));
		when(mockOfflineUser.isOnline()).thenReturn(false);
		assertEquals(small, placeholder.formatPlaceholder(player, identifier));
		
		// Smallest
		player.setLastLogin((System.currentTimeMillis() / 1000L) - 30L);
		when(mockOfflineUser.isOnline()).thenReturn(true);
		assertEquals(smallest, placeholder.formatPlaceholder(player, identifier));
		when(mockOfflineUser.isOnline()).thenReturn(false);
		assertEquals(smallest, placeholder.formatPlaceholder(player, identifier));
		
		
		player.setLastLogin(0);
		assertEquals(ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN, placeholder.formatPlaceholder(player, identifier));
		
		player.setAccessible(false);
	}
	
	@Test
	public void testLastLoginElapsedTimes() {
		player.setAccessible(true);
		player.setLastLogin((System.currentTimeMillis() / 1000L) - (5 * 24 * 60 * 60L) - (10 * 60 * 60L) - (30 * 60L) - 30L);
		player.setAccessible(false);
		
		String identifier = "last_login_elapsed_seconds";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		assertEquals(Long.toString((5 * 24 * 60 * 60L) + (10 * 60 * 60L) + (30 * 60L) + 30L), placeholder.formatPlaceholder(player, identifier));
		
		identifier = "last_login_elapsed_minutes";
		placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		assertEquals(Long.toString((5 * 24 * 60L) + (10 * 60L) + (30L)), placeholder.formatPlaceholder(player, identifier));
		
		identifier = "last_login_elapsed_hours";
		placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		assertEquals(Long.toString((5 * 24L) + (10L)), placeholder.formatPlaceholder(player, identifier));
		
		identifier = "last_login_elapsed_days";
		placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		assertEquals(Long.toString(5L), placeholder.formatPlaceholder(player, identifier));
		
		player.setAccessible(true);
		player.setLastLogin(0);
		player.setAccessible(false);
		assertEquals("0", placeholder.formatPlaceholder(player, identifier));
	}
	
	@Test
	public void testLastLoginTimestamp() {
		String identifier = "last_login_timestamp";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		
		assertEquals(Long.toString(PLAYER_LOGIN), placeholder.formatPlaceholder(player, identifier));
	}
	
	@Test
	public void testLastLogoutDate() {
		String identifier = "last_logout_date";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		
		// Setup config
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT = "yyyy-MM-dd";
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN = "UNKNOWN";
		
		assertEquals("1970-04-26", placeholder.formatPlaceholder(player, identifier));
		
		player.setAccessible(true);
		player.setLastLogout(0);
		player.setAccessible(false);
		assertEquals(ConfigMain.PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN, placeholder.formatPlaceholder(player, identifier));
	}
	
	@Test
	public void testLastLogoutElapsed() {
		String identifier = "last_logout_elapsed";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		
		// Setup config
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_LARGE = "d'd' H'h'";
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_MEDIUM = "H'h' m'm'";
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_SMALL = "m'm' s's'";
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_SMALLEST = "s's'";
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN = "UNKNOWN";
		
		// Results
		final String large = "5d 10h";
		final String medium = "10h 30m";
		final String small = "30m 30s";
		final String smallest = "30s";
		
		player.setAccessible(true);
		// Large
		player.setLastLogout((System.currentTimeMillis() / 1000L) - (5 * 24 * 60 * 60L) - (10 * 60 * 60L) - (30 * 60L) - 30L);
		assertEquals(large, placeholder.formatPlaceholder(player, identifier));
		
		// Medium
		player.setLastLogout((System.currentTimeMillis() / 1000L) - (10 * 60 * 60L) - (30 * 60L) - 30L);
		assertEquals(medium, placeholder.formatPlaceholder(player, identifier));
		
		// Small
		player.setLastLogout((System.currentTimeMillis() / 1000L) - (30 * 60L) - 30L);
		assertEquals(small, placeholder.formatPlaceholder(player, identifier));
		
		// Smallest
		player.setLastLogout((System.currentTimeMillis() / 1000L) - 30L);
		assertEquals(smallest, placeholder.formatPlaceholder(player, identifier));
		
		
		player.setLastLogout(0);
		assertEquals(ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN, placeholder.formatPlaceholder(player, identifier));
		
		player.setAccessible(false);
	}
	
	@Test
	public void testLastLogoutElapsedTimes() {
		player.setAccessible(true);
		player.setLastLogout((System.currentTimeMillis() / 1000L) - (5 * 24 * 60 * 60L) - (10 * 60 * 60L) - (30 * 60L) - 30L);
		player.setAccessible(false);
		
		String identifier = "last_logout_elapsed_seconds";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		assertEquals(Long.toString((5 * 24 * 60 * 60L) + (10 * 60 * 60L) + (30 * 60L) + 30L), placeholder.formatPlaceholder(player, identifier));
		
		identifier = "last_logout_elapsed_minutes";
		placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		assertEquals(Long.toString((5 * 24 * 60L) + (10 * 60L) + (30L)), placeholder.formatPlaceholder(player, identifier));
		
		identifier = "last_logout_elapsed_hours";
		placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		assertEquals(Long.toString((5 * 24L) + (10L)), placeholder.formatPlaceholder(player, identifier));
		
		identifier = "last_logout_elapsed_days";
		placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		assertEquals(Long.toString(5L), placeholder.formatPlaceholder(player, identifier));
		
		player.setAccessible(true);
		player.setLastLogout(0);
		player.setAccessible(false);
		assertEquals("0", placeholder.formatPlaceholder(player, identifier));
	}
	
	@Test
	public void testLastLogoutTimestamp() {
		String identifier = "last_logout_timestamp";
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		assertNotNull(placeholder);
		
		assertEquals(Long.toString(PLAYER_LOGOUT), placeholder.formatPlaceholder(player, identifier));
	}
}
