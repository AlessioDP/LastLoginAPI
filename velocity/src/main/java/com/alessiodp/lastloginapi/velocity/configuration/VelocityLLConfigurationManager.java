package com.alessiodp.lastloginapi.velocity.configuration;

import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.configuration.LLConfigurationManager;
import com.alessiodp.lastloginapi.velocity.configuration.data.VelocityConfigMain;
import com.alessiodp.lastloginapi.velocity.configuration.data.VelocityMessages;

public class VelocityLLConfigurationManager extends LLConfigurationManager {
	
	public VelocityLLConfigurationManager(LastLoginPlugin plugin) {
		super(plugin);
		
		getConfigs().add(new VelocityMessages(plugin));
		getConfigs().add(new VelocityConfigMain(plugin));
	}
}
