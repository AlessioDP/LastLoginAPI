package com.alessiodp.lastloginapi.common.commands.main;

import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.commands.utils.ADPExecutableCommand;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.user.User;
import com.alessiodp.core.common.utils.Color;
import com.alessiodp.core.common.utils.CommonUtils;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.commands.list.CommonCommands;
import com.alessiodp.lastloginapi.common.commands.sub.CommandHelp;
import com.alessiodp.lastloginapi.common.commands.sub.CommandInfo;
import com.alessiodp.lastloginapi.common.commands.sub.CommandReload;
import com.alessiodp.lastloginapi.common.commands.sub.CommandVersion;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;

import java.util.HashMap;
import java.util.Map;

public class CommandLLAPI extends ADPMainCommand {
	public CommandLLAPI(LastLoginPlugin plugin) {
		super(plugin, CommonCommands.LLAPI, ConfigMain.COMMANDS_CMD_LLAPI, true);
		
		description = ConfigMain.COMMANDS_DESCRIPTION_LLAPI;
		subCommands = new HashMap<>();
		subCommandsByEnum = new HashMap<>();
		tabSupport = ConfigMain.COMMANDS_TABSUPPORT;
		
		register(new CommandHelp(plugin, this));
		register(new CommandInfo(plugin, this));
		register(new CommandReload(plugin, this));
		register(new CommandVersion(plugin, this));
	}
	
	@Override
	public boolean onCommand(User sender, String command, String[] args) {
		String subCommand;
		if (sender.isPlayer()) {
			if (args.length == 0) {
				// Set /llapi to /llapi help
				subCommand = CommonUtils.toLowerCase(ConfigMain.COMMANDS_CMD_HELP);
			} else {
				subCommand = CommonUtils.toLowerCase(args[0]);
			}
			
			if (exists(subCommand)) {
				plugin.getCommandManager().getCommandUtils().executeCommand(sender, getCommandName(), getSubCommand(subCommand), args);
			} else {
				sender.sendMessage(Messages.LLAPI_COMMON_INVALIDCMD, true);
			}
		} else {
			// Console
			if (args.length > 0) {
				subCommand = CommonUtils.toLowerCase(args[0]);
				if (exists(subCommand) && getSubCommand(subCommand).isExecutableByConsole()) {
					plugin.getCommandManager().getCommandUtils().executeCommand(sender, getCommandName(), getSubCommand(subCommand), args);
				} else {
					plugin.logConsole(Color.translateAndStripColor(Messages.LLAPI_COMMON_INVALIDCMD), false);
				}
			} else {
				// Print help
				plugin.logConsole(Messages.HELP_CONSOLEHELP_HEADER, false);
				for(Map.Entry<ADPCommand, ADPExecutableCommand> e : plugin.getCommandManager().getOrderedCommands().entrySet()) {
					if (e.getValue().isExecutableByConsole()  && e.getValue().isListedInHelp()) {
						plugin.logConsole(Messages.HELP_CONSOLEHELP_COMMAND
								.replace("%command%", e.getValue().getConsoleSyntax())
								.replace("%description%", e.getValue().getDescription()), false);
					}
				}
			}
		}
		return true;
	}
}
