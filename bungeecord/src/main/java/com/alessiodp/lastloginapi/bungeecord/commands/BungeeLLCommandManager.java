package com.alessiodp.lastloginapi.bungeecord.commands;

import com.alessiodp.core.bungeecord.commands.utils.BungeeCommandUtils;
import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.commands.LLCommandManager;
import com.alessiodp.lastloginapi.common.commands.main.CommandLLAPI;

import java.util.ArrayList;

public class BungeeLLCommandManager extends LLCommandManager {
	public BungeeLLCommandManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void prepareCommands() {
		commandOrder = null; // Command order disabled
		commandUtils = new BungeeCommandUtils(plugin, "on", "off");
		super.prepareCommands();
	}
	
	@Override
	public void registerCommands() {
		mainCommands = new ArrayList<>();
		mainCommands.add(new CommandLLAPI((LastLoginPlugin) plugin));
	}
}