package com.alessiodp.lastloginapi.velocity.configuration.data;

import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import lombok.Getter;

public class VelocityConfigMain extends ConfigMain {
	@Getter private final String fileName = "config.yml";
	@Getter private final String resourceName = "velocity/config.yml";
	@Getter private final int latestVersion = LLConstants.VERSION_VELOCITY_CONFIG;
	
	public VelocityConfigMain(LastLoginPlugin plugin) {
		super(plugin);
	}
}