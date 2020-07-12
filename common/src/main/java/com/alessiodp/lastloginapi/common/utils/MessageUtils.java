package com.alessiodp.lastloginapi.common.utils;

import com.alessiodp.core.common.utils.CommonUtils;
import com.alessiodp.core.common.utils.DurationUtils;
import com.alessiodp.lastloginapi.common.addons.internal.LLPlaceholder;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {
	private final Pattern PLACEHOLDER_PATTERN = Pattern.compile("([%][^%]+[%])");
	
	public String convertPlaceholders(String message, LLPlayerImpl player) {
		String ret = message;
		if (player != null) {
			String replacement;
			Matcher matcher = PLACEHOLDER_PATTERN.matcher(ret);
			while (matcher.find()) {
				String identifier = matcher.group(1);
				
				// Match basic placeholders
				switch (CommonUtils.toLowerCase(identifier)) {
					case "%player%":
						replacement = CommonUtils.getNoEmptyOr(player.getName(), Messages.LLAPI_SYNTAX_UNKNOWN);
						ret = ret.replace(identifier, replacement);
						break;
					default: // Nothing to do
				}
				
				LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(stripPlaceholder(identifier));
				if (placeholder != null) {
					replacement = placeholder.formatPlaceholder(player, stripPlaceholder(identifier));
					if (replacement != null)
						ret = ret.replace(identifier, replacement);
				}
			}
		}
		return ret;
	}
	
	public String formatDate(long timestamp, String format) {
		Instant instant = Instant.ofEpochSecond(timestamp);
		LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		
		String ret = format;
		try {
			ret = DateTimeFormatter.ofPattern(format).format(date);
		} catch (IllegalArgumentException ignored) {}
		return ret;
	}
	
	public String formatElapsed(long timestamp, String large, String medium, String small, String smallest) {
		return DurationUtils.formatWith(
				(System.currentTimeMillis() / 1000L) - timestamp,
				large,
				medium,
				small,
				smallest
		);
	}
	
	public String stripPlaceholder(String placeholder) {
		return placeholder.substring(1, placeholder.length() - 1);
	}
}