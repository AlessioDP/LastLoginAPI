package com.alessiodp.lastloginapi.bungeecord.configuration.data;

import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import lombok.Getter;

public class BungeeMessages extends Messages {
	@Getter private final String fileName = "messages.yml";
	@Getter private final String resourceName = "bungee/messages.yml";
	@Getter private final int latestVersion = LLConstants.VERSION_BUNGEE_MESSAGES;
	
	public BungeeMessages(LastLoginPlugin plugin) {
		super(plugin);
	}
}