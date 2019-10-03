package com.alessiodp.lastloginapi.common.utils;

import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import org.apache.commons.lang.time.DurationFormatUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {
	private final Pattern PLACEHOLDER_PATTERN = Pattern.compile("([%][^%]+[%])");
	
	public String convertPlaceholders(String message, LLPlayerImpl player) {
		String ret = message;
		if (player != null) {
			Matcher matcher = PLACEHOLDER_PATTERN.matcher(ret);
			while (matcher.find()) {
				String replacement;
				String identifier = matcher.group(1);
				switch (identifier.toLowerCase(Locale.ENGLISH)) {
					case LLConstants.PLACEHOLDER_NAME:
						if (!player.getName().isEmpty()) {
							// Known
							replacement = ConfigMain.PLACEHOLDERS_NAME_FORMAT
									.replace("%name%", player.getName());
						} else {
							// Unknown
							replacement = ConfigMain.PLACEHOLDERS_NAME_FORMAT_UNKNOWN;
						}
						break;
					case LLConstants.PLACEHOLDER_LAST_LOGIN_DATE:
						if (player.isOnline()) {
							// Online
							replacement = formatDate(player.getLastLogin(), ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_ONLINE);
						} else if (player.getLastLogin() > 0) {
							// Offline
							replacement = formatDate(player.getLastLogin(), ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT);
						} else {
							// Unknown
							replacement = ConfigMain.PLACEHOLDERS_LAST_LOGIN_DATE_FORMAT_UNKNOWN;
						}
						break;
					case LLConstants.PLACEHOLDER_LAST_LOGIN_ELAPSED:
						if (player.isOnline()) {
							// Online
							replacement = formatElapsed(player.getLastLogin(), ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_ONLINE);
						} else if (player.getLastLogin() > 0) {
							// Offline
							replacement = formatElapsed(player.getLastLogin(), ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT);
						} else {
							// Unknown
							replacement = ConfigMain.PLACEHOLDERS_LAST_LOGIN_ELAPSED_FORMAT_UNKNOWN;
						}
						break;
					case LLConstants.PLACEHOLDER_LAST_LOGOUT_DATE:
						if (player.getLastLogout() > 0) {
							// Offline
							replacement = formatDate(player.getLastLogout(), ConfigMain.PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT);
						} else {
							// Unknown
							replacement = ConfigMain.PLACEHOLDERS_LAST_LOGOUT_DATE_FORMAT_UNKNOWN;
						}
						break;
					case LLConstants.PLACEHOLDER_LAST_LOGOUT_ELAPSED:
						if (player.getLastLogout() > 0) {
							// Offline
							replacement = formatElapsed(player.getLastLogout(), ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT);
						} else {
							// Unknown
							replacement = ConfigMain.PLACEHOLDERS_LAST_LOGOUT_ELAPSED_FORMAT_UNKNOWN;
						}
						break;
					default:
						replacement = null;
						break;
				}
				
				if (replacement != null)
					ret = ret.replace(identifier, replacement);
			}
		}
		return ret;
	}
	
	private String formatDate(long timestamp, String format) {
		Instant instant = Instant.ofEpochSecond(timestamp);
		LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		
		String ret = format;
		try {
			ret = DateTimeFormatter.ofPattern(format).format(date);
		} catch (IllegalArgumentException ignored) {}
		return ret;
	}
	
	private String formatElapsed(long timestamp, String format) {
		return DurationFormatUtils.formatDuration(System.currentTimeMillis() - (timestamp * 1000L), format);
	}
}