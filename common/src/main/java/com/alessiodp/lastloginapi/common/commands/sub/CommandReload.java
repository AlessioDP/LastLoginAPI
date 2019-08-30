package com.alessiodp.lastloginapi.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import com.alessiodp.lastloginapi.common.players.LastLoginPermission;
import lombok.Getter;

public class CommandReload extends ADPSubCommand {
	@Getter private final boolean executableByConsole = true;
	
	public CommandReload(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(plugin, mainCommand);
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		if (sender.isPlayer()
				&& !sender.hasPermission(LastLoginPermission.ADMIN_RELOAD.toString())) {
			sender.sendMessage(Messages.LLAPI_NO_PERMISSION
					.replace("%permission%", LastLoginPermission.ADMIN_RELOAD.toString()), true);
			return false;
		}
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (sender.isPlayer())
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_CMD_RELOAD
					.replace("{player}", sender.getName()), true);
		else
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_CMD_RELOAD_CONSOLE, true);
		
		plugin.reloadConfiguration();
		
		if (sender.isPlayer()) {
			sender.sendMessage(Messages.CMD_RELOAD_RELOADED, true);
			
			plugin.getLoggerManager().log(LLConstants.DEBUG_CMD_RELOADED
					.replace("{player}", sender.getName()), true);
		} else {
			plugin.getLoggerManager().log(LLConstants.DEBUG_CMD_RELOADED_CONSOLE, true);
		}
	}
}
