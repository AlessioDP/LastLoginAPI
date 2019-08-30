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

public class CommandVersion extends ADPSubCommand {
	@Getter private final boolean executableByConsole = true;
	
	public CommandVersion(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(plugin, mainCommand);
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		if (sender.isPlayer()
				&& !sender.hasPermission(LastLoginPermission.ADMIN_VERSION.toString())) {
			sender.sendMessage(Messages.LLAPI_NO_PERMISSION
					.replace("%permission%", LastLoginPermission.ADMIN_VERSION.toString()), true);
			return false;
		}
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User sender = commandData.getSender();
		
		// Command handling
		if (sender.isPlayer())
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_CMD_VERSION
					.replace("{player}", sender.getName()), true);
		else
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_CMD_VERSION_CONSOLE, true);
		
		// Command starts
		String version = plugin.getVersion();
		String newVersion = plugin.getAdpUpdater().getFoundVersion().isEmpty() ? version : plugin.getAdpUpdater().getFoundVersion();
		String message = version.equals(newVersion) ? Messages.CMD_VERSION_UPDATED : Messages.CMD_VERSION_OUTDATED;
		message = message.replace("%version%", version)
				.replace("%newversion%", newVersion)
				.replace("%platform%", plugin.getPlatform());
		
		if (sender.isPlayer()) {
			sender.sendMessage(message, true);
		} else {
			plugin.logConsole(plugin.getColorUtils().removeColors(message), false);
		}
	}
}
