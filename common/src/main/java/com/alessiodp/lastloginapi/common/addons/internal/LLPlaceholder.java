package com.alessiodp.lastloginapi.common.addons.internal;

import com.alessiodp.core.common.utils.CommonUtils;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;

public enum LLPlaceholder {
	NAME,
	
	LAST_LOGIN_DATE,
	LAST_LOGIN_ELAPSED,
	LAST_LOGIN_ELAPSED_SECONDS,
	LAST_LOGIN_ELAPSED_MINUTES,
	LAST_LOGIN_ELAPSED_HOURS,
	LAST_LOGIN_ELAPSED_DAYS,
	LAST_LOGIN_TIMESTAMP,
	
	LAST_LOGOUT_DATE,
	LAST_LOGOUT_ELAPSED,
	LAST_LOGOUT_ELAPSED_SECONDS,
	LAST_LOGOUT_ELAPSED_MINUTES,
	LAST_LOGOUT_ELAPSED_HOURS,
	LAST_LOGOUT_ELAPSED_DAYS,
	LAST_LOGOUT_TIMESTAMP;
	
	private final LastLoginPlugin plugin;
	
	LLPlaceholder() {
		plugin = ((LastLoginPlugin) LastLoginPlugin.getInstance());
	}
	
	public static LLPlaceholder getPlaceholder(String identifier) {
		String identifierLower = CommonUtils.toLowerCase(identifier);
		for (LLPlaceholder en : LLPlaceholder.values()) {
			if (CommonUtils.toLowerCase(en.name()).equals(identifierLower)) {
				return en;
			}
		}
		return null;
	}
	
	public String formatPlaceholder(LLPlayerImpl player, String identifier) {
		if (player != null) {
			switch (this) {
				case NAME:
					if (player.getName() != null && !player.getName().isEmpty())
						return ConfigMain.PLACEHOLDERS_NAME_FORMAT.replace("%name%", player.getName());
					return ConfigMain.PLACEHOLDERS_NAME_FORMAT_UNKNOWN;
				case LAST_LOGIN_DATE:
					if (player.isOnline()) {
						return plugin.getMessageUtils().formatDate(player.getLastLogin(), ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE);
					} else if (player.getLastLogin() > 0) {
						return plugin.getMessageUtils().formatDate(player.getLastLogin(), ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT);
					}
					return ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN;
				case LAST_LOGIN_ELAPSED:
					if (player.isOnline()) {
						return plugin.getMessageUtils().formatElapsed(
								player.getLastLogin(),
								ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_LARGE,
								ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_MEDIUM,
								ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_SMALL,
								ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE_SMALLEST
						);
					} else if (player.getLastLogin() > 0) {
						return plugin.getMessageUtils().formatElapsed(
								player.getLastLogin(),
								ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_LARGE,
								ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_MEDIUM,
								ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_SMALL,
								ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_SMALLEST
						);
					}
					return ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN;
				case LAST_LOGIN_ELAPSED_SECONDS:
					return player.getLastLogin() == 0 ? "0" : Long.toString(((System.currentTimeMillis() / 1000L) - player.getLastLogin()));
				case LAST_LOGIN_ELAPSED_MINUTES:
					return player.getLastLogin() == 0 ? "0" : Long.toString(((System.currentTimeMillis() / 1000L) - player.getLastLogin()) / 60L);
				case LAST_LOGIN_ELAPSED_HOURS:
					return player.getLastLogin() == 0 ? "0" : Long.toString(((System.currentTimeMillis() / 1000L) - player.getLastLogin()) / (60 * 60L));
				case LAST_LOGIN_ELAPSED_DAYS:
					return player.getLastLogin() == 0 ? "0" : Long.toString(((System.currentTimeMillis() / 1000L) - player.getLastLogin()) / (60 * 60 * 24L));
				case LAST_LOGIN_TIMESTAMP:
					return Long.toString(player.getLastLogin());
				case LAST_LOGOUT_DATE:
					if (player.getLastLogout() > 0) {
						return plugin.getMessageUtils().formatDate(player.getLastLogout(), ConfigMain.PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT);
					}
					return ConfigMain.PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN;
				
				case LAST_LOGOUT_ELAPSED:
					if (player.getLastLogout() > 0) {
						return plugin.getMessageUtils().formatElapsed(
								player.getLastLogout(),
								ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_LARGE,
								ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_MEDIUM,
								ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_SMALL,
								ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_SMALLEST
						);
					}
					return ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN;
				case LAST_LOGOUT_ELAPSED_SECONDS:
					return player.getLastLogout() == 0 ? "0" : Long.toString(((System.currentTimeMillis() / 1000L) - player.getLastLogout()));
				case LAST_LOGOUT_ELAPSED_MINUTES:
					return player.getLastLogout() == 0 ? "0" : Long.toString(((System.currentTimeMillis() / 1000L) - player.getLastLogout()) / 60L);
				case LAST_LOGOUT_ELAPSED_HOURS:
					return player.getLastLogout() == 0 ? "0" : Long.toString(((System.currentTimeMillis() / 1000L) - player.getLastLogout()) / (60 * 60L));
				case LAST_LOGOUT_ELAPSED_DAYS:
					return player.getLastLogout() == 0 ? "0" : Long.toString(((System.currentTimeMillis() / 1000L) - player.getLastLogout()) / (60 * 60 * 24L));
				case LAST_LOGOUT_TIMESTAMP:
					return player.getLastLogout() == 0 ? "0" : Long.toString(player.getLastLogout());
				default:
					return null;
			}
		}
		return null;
	}
}
