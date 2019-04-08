package com.alessiodp.lastloginapi.common.addons.internal;

import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.Getter;

public enum LLPlaceholder {
	NAME,
	LAST_LOGIN_DATE,
	LAST_LOGIN_ELAPSED,
	LAST_LOGOUT_DATE,
	LAST_LOGOUT_ELAPSED;
	
	private static LastLoginPlugin plugin;
	@Getter private String format;
	
	LLPlaceholder() {
		format = "";
	}
	
	public static void setupFormats(LastLoginPlugin plugin) {
		LLPlaceholder.plugin = plugin;
		
		NAME.format = LLConstants.PLACEHOLDER_NAME;
		LAST_LOGIN_DATE.format = LLConstants.PLACEHOLDER_LAST_LOGIN_DATE;
		LAST_LOGIN_ELAPSED.format = LLConstants.PLACEHOLDER_LAST_LOGIN_ELAPSED;
		LAST_LOGOUT_DATE.format = LLConstants.PLACEHOLDER_LAST_LOGOUT_DATE;
		LAST_LOGOUT_ELAPSED.format = LLConstants.PLACEHOLDER_LAST_LOGOUT_ELAPSED;
	}
	
	public static LLPlaceholder getPlaceholder(String identifier) {
		LLPlaceholder ret = null;
		for (LLPlaceholder en : LLPlaceholder.values()) {
			if (en.name().equalsIgnoreCase(identifier)) {
				ret = en;
				break;
			}
		}
		return ret;
	}
	
	public String formatPlaceholder(LLPlayerImpl player) {
		String ret = "";
		if (player != null) {
			String pFormat = format;
			ret = plugin.getMessageUtils().convertPlaceholders(pFormat, player);
		}
		return ret;
	}
}
