package com.alessiodp.lastloginapi.bukkit.configuration.data;

import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import lombok.Getter;

public class BukkitMessages extends Messages {
	@Getter private final String fileName = "messages.yml";
	@Getter private final String resourceName = "bukkit/messages.yml";
	@Getter private final int latestVersion = LLConstants.VERSION_BUKKIT_MESSAGES;
	
	public BukkitMessages(LastLoginPlugin plugin) {
		super(plugin);
	}
}