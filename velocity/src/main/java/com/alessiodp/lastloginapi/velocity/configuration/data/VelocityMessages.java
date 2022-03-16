package com.alessiodp.lastloginapi.velocity.configuration.data;

import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import lombok.Getter;

public class VelocityMessages extends Messages {
	@Getter private final String fileName = "messages.yml";
	@Getter private final String resourceName = "velocity/messages.yml";
	@Getter private final int latestVersion = LLConstants.VERSION_VELOCITY_MESSAGES;
	
	public VelocityMessages(LastLoginPlugin plugin) {
		super(plugin);
	}
}