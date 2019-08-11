package com.alessiodp.lastloginapi.common.commands;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.CommandManager;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.lastloginapi.common.commands.list.CommonCommands;
import com.alessiodp.lastloginapi.common.commands.utils.LLCommandData;

public abstract class LLCommandManager extends CommandManager {
	protected LLCommandManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void prepareCommands() {
		CommonCommands.setup();
	}
	
	@Override
	protected void registerCommands() {
	
	}
	
	@Override
	public CommandData initializeCommandData() {
		return new LLCommandData();
	}
}
