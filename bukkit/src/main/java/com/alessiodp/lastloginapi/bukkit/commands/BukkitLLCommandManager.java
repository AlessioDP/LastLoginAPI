package com.alessiodp.lastloginapi.bukkit.commands;

import com.alessiodp.core.bukkit.commands.utils.BukkitCommandUtils;
import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.lastloginapi.bukkit.commands.main.BukkitCommandLLAPI;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.commands.LLCommandManager;

import java.util.ArrayList;

public class BukkitLLCommandManager extends LLCommandManager {
	public BukkitLLCommandManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void prepareCommands() {
		commandOrder = null; // Command order disabled
		commandUtils = new BukkitCommandUtils(plugin, "on", "off");
		super.prepareCommands();
	}
	
	@Override
	public void registerCommands() {
		mainCommands = new ArrayList<>();
		mainCommands.add(new BukkitCommandLLAPI((LastLoginPlugin) plugin));
	}
}