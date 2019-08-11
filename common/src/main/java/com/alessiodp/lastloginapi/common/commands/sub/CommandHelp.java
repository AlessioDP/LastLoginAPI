package com.alessiodp.lastloginapi.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import com.alessiodp.lastloginapi.common.players.LastLoginPermission;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CommandHelp extends ADPSubCommand {
	@Getter
	private final boolean executableByConsole = false;
	
	public CommandHelp(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(plugin, mainCommand);
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		// Checks for command prerequisites
		if (!sender.hasPermission(LastLoginPermission.ADMIN_HELP.toString())) {
			sender.sendMessage(Messages.LLAPI_NO_PERMISSION
					.replace("%permission%", LastLoginPermission.ADMIN_HELP.toString()), true);
			return false;
		}
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User sender = commandData.getSender();
		
		plugin.getLoggerManager().logDebug(LLConstants.DEBUG_CMD_HELP
				.replace("{player}", sender.getName()), true);
		
		// Command starts
		// Get all allowed commands
		List<String> list = new ArrayList<>();
		for (ADPCommand cmd : plugin.getPlayerUtils().getAllowedCommands(sender)) {
			if (mainCommand.getEnabledSubCommands().contains(cmd))
				list.add(cmd.getHelp());
		}
		
		
		// Start printing
		sender.sendMessage(Messages.HELP_HEADER, true);
		for (String string : list) {
			sender.sendMessage(string, true);
		}
		sender.sendMessage(Messages.HELP_FOOTER, true);
	}
}