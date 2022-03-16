package com.alessiodp.lastloginapi.velocity.commands;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.velocity.commands.utils.VelocityCommandUtils;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.commands.LLCommandManager;
import com.alessiodp.lastloginapi.common.commands.main.CommandLLAPI;

import java.util.ArrayList;

public class VelocityLLCommandManager extends LLCommandManager {
	public VelocityLLCommandManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void prepareCommands() {
		commandUtils = new VelocityCommandUtils(plugin, "on", "off");
		super.prepareCommands();
	}
	
	@Override
	public void registerCommands() {
		mainCommands = new ArrayList<>();
		mainCommands.add(new CommandLLAPI((LastLoginPlugin) plugin));
	}
}