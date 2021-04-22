package com.alessiodp.lastloginapi.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.commands.list.CommonCommands;
import com.alessiodp.lastloginapi.common.commands.utils.LLCommandData;
import com.alessiodp.lastloginapi.common.commands.utils.LLSubCommand;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.utils.LastLoginPermission;

public class CommandReload extends LLSubCommand {
	
	public CommandReload(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.RELOAD,
				LastLoginPermission.ADMIN_RELOAD,
				ConfigMain.COMMANDS_CMD_RELOAD,
				true
		);
		
		syntax = baseSyntax();
		
		description = Messages.HELP_CMD_DESCRIPTIONS_RELOAD;
		help = Messages.HELP_CMD_RELOAD;
	}
	
	@Override
	public String getRunCommand() {
		return baseSyntax();
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		if (sender.isPlayer()) {
			// If the sender is a player
			LLPlayerImpl player = ((LastLoginPlugin) plugin).getPlayerManager().getPlayer(sender.getUUID());
			
			if (player != null && !sender.hasPermission(permission)) {
				player.sendNoPermission(permission);
				return false;
			}
			
			((LLCommandData) commandData).setPlayer(player);
		}
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		LLPlayerImpl player = ((LLCommandData) commandData).getPlayer();
		
		plugin.reloadConfiguration();
		
		sendMessage(commandData.getSender(), player, Messages.LLAPI_COMMON_CONFIGRELOAD);
	}
}
