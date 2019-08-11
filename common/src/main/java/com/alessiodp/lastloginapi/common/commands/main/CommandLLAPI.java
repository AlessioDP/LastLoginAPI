package com.alessiodp.lastloginapi.common.commands.main;

import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.commands.list.CommonCommands;
import com.alessiodp.lastloginapi.common.commands.sub.CommandHelp;
import com.alessiodp.lastloginapi.common.commands.sub.CommandReload;
import com.alessiodp.lastloginapi.common.commands.sub.CommandVersion;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandLLAPI extends ADPMainCommand {
	public CommandLLAPI(LastLoginPlugin plugin) {
		super(plugin);
		
		commandName = "llapi";
		description = "LastLoginAPI main command";
		subCommands = new HashMap<>();
		enabledSubCommands = new ArrayList<>();
		tabSupport = true;
		
		register(CommonCommands.HELP, new CommandHelp(plugin, this));
		register(CommonCommands.RELOAD, new CommandReload(plugin, this));
		register(CommonCommands.VERSION, new CommandVersion(plugin, this));
	}
	
	@Override
	public boolean onCommand(User sender, String command, String[] args) {
		String subCommand;
		if (sender.isPlayer()) {
			if (args.length == 0) {
				// Set /llapi to /llapi help
				subCommand = CommonCommands.HELP.getCommand().toLowerCase();
			} else {
				subCommand = args[0].toLowerCase();
			}
			
			if (exists(subCommand)) {
				plugin.getCommandManager().getCommandUtils().executeCommand(sender, getCommandName(), getSubCommand(subCommand), args);
			} else {
				sender.sendMessage(Messages.LLAPI_INVALID_COMMAND, true);
			}
		} else {
			// Console
			if (args.length > 0) {
				subCommand = args[0].toLowerCase();
				if (exists(subCommand) && getSubCommand(subCommand).isExecutableByConsole()) {
					plugin.getCommandManager().getCommandUtils().executeCommand(sender, getCommandName(), getSubCommand(subCommand), args);
				} else {
					plugin.logConsole(plugin.getColorUtils().removeColors(Messages.LLAPI_INVALID_COMMAND), false);
				}
			} else {
				// Print help
				for (String str : Messages.HELP_CONSOLEHELP) {
					plugin.logConsole(str, false);
				}
			}
		}
		return true;
	}
}
