package com.alessiodp.lastloginapi.common.commands;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.CommandManager;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.lastloginapi.common.commands.utils.LLCommandData;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public abstract class LLCommandManager extends CommandManager {
	protected LLCommandManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void prepareCommands() {
		commandOrder = new LinkedList<>();
		commandOrder.addAll(ConfigMain.COMMANDS_ORDER);
	}
	
	@Override
	public @NotNull CommandData initializeCommandData() {
		return new LLCommandData();
	}
}
