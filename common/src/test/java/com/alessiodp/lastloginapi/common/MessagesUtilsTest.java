package com.alessiodp.lastloginapi.common;

import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.utils.MessageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
		ConfigMain.class,
		LastLoginPlugin.class,
		LLPlayerImpl.class,
		MessageUtils.class,
		DateTimeFormatter.class
})
public class MessagesUtilsTest {
	private LLPlayerImpl mockPlayer;
	private MessageUtils messageUtils;
	
	@Before
	public void setUp() {
		mockPlayer = mock(LLPlayerImpl.class);
		
		messageUtils = new MessageUtils();
	}
	
	@Test
	public void testPlaceholderName() {
		final String NAME = "AlessioDP";
		final String UNKNOWN = "Unknown";
		ConfigMain.PLACEHOLDERS_NAME_FORMAT = "%name%";
		ConfigMain.PLACEHOLDERS_NAME_FORMAT_UNKNOWN = UNKNOWN;
		
		when(mockPlayer.getName()).thenReturn(NAME);
		assertEquals(NAME, messageUtils.convertPlaceholders("%name%", mockPlayer));
		
		when(mockPlayer.getName()).thenReturn("");
		assertEquals(UNKNOWN, messageUtils.convertPlaceholders("%name%", mockPlayer));
	}
	
	@Test
	public void testPlaceholderLastLoginDate() {
		final String RESULT = "1970-01-01";
		final String ONLINE = "Online";
		final String UNKNOWN = "Unknown";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT = "yyyy-MM-dd";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE = ONLINE;
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN = UNKNOWN;
		
		when(mockPlayer.isOnline()).thenReturn(false);
		when(mockPlayer.getLastLogin()).thenReturn(1L);
		assertEquals(RESULT, messageUtils.convertPlaceholders("%last_login_date%", mockPlayer));
		
		when(mockPlayer.isOnline()).thenReturn(true);
		when(mockPlayer.getLastLogin()).thenReturn(1L);
		assertEquals(ONLINE, messageUtils.convertPlaceholders("%last_login_date%", mockPlayer));
		
		when(mockPlayer.isOnline()).thenReturn(false);
		when(mockPlayer.getLastLogin()).thenReturn(0L);
		assertEquals(UNKNOWN, messageUtils.convertPlaceholders("%last_login_date%", mockPlayer));
	}
	
	@Test
	public void testPlaceholderLastLoginElapsed() {
		final String RESULT = "0d 1h 30m";
		final String ONLINE = "1h 30m";
		final String UNKNOWN = "Unknown";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT = "d'd' H'h' m'm'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE = "H'h' m'm'";
		ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN = UNKNOWN;
		
		when(mockPlayer.isOnline()).thenReturn(false);
		when(mockPlayer.getLastLogin()).thenReturn((System.currentTimeMillis() / 1000L) - (60 * 90));
		assertEquals(RESULT, messageUtils.convertPlaceholders("%last_login_elapsed%", mockPlayer));
		
		when(mockPlayer.isOnline()).thenReturn(true);
		when(mockPlayer.getLastLogin()).thenReturn((System.currentTimeMillis() / 1000L) - (60 * 90));
		assertEquals(ONLINE, messageUtils.convertPlaceholders("%last_login_elapsed%", mockPlayer));
		
		when(mockPlayer.isOnline()).thenReturn(false);
		when(mockPlayer.getLastLogin()).thenReturn(0L);
		assertEquals(UNKNOWN, messageUtils.convertPlaceholders("%last_login_elapsed%", mockPlayer));
	}
	
	@Test
	public void testPlaceholderLastLogoutDate() {
		final String RESULT = "1970-01-01";
		final String UNKNOWN = "Unknown";
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT = "yyyy-MM-dd";
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN = UNKNOWN;
		
		when(mockPlayer.getLastLogout()).thenReturn(1L);
		assertEquals(RESULT, messageUtils.convertPlaceholders("%last_logout_date%", mockPlayer));
		
		when(mockPlayer.getLastLogout()).thenReturn(0L);
		assertEquals(UNKNOWN, messageUtils.convertPlaceholders("%last_logout_date%", mockPlayer));
	}
	
	@Test
	public void testPlaceholderLastLogoutElapsed() {
		final String RESULT = "0d 1h 30m";
		final String UNKNOWN = "Unknown";
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT = "d'd' H'h' m'm'";
		ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN = UNKNOWN;
		
		when(mockPlayer.getLastLogout()).thenReturn((System.currentTimeMillis() / 1000L) - (60 * 90));
		assertEquals(RESULT, messageUtils.convertPlaceholders("%last_logout_elapsed%", mockPlayer));
		
		when(mockPlayer.getLastLogout()).thenReturn(0L);
		assertEquals(UNKNOWN, messageUtils.convertPlaceholders("%last_logout_elapsed%", mockPlayer));
	}
}