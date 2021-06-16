package com.alessiodp.lastloginapi.bukkit.commands.main;

import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.commands.main.CommandLLAPI;

public class BukkitCommandLLAPI extends CommandLLAPI {
	
	public BukkitCommandLLAPI(LastLoginPlugin plugin) {
		super(plugin);
		
		description = BukkitConfigMain.COMMANDS_MAIN_LLAPI_DESCRIPTION;
	}
}
