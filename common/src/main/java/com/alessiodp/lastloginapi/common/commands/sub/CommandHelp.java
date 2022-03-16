package com.alessiodp.lastloginapi.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.commands.utils.ADPExecutableCommand;
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
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class CommandHelp extends LLSubCommand {
	public CommandHelp(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.HELP,
				LastLoginPermission.ADMIN_HELP,
				ConfigMain.COMMANDS_CMD_HELP,
				false
		);
		
		syntax = baseSyntax();
		
		description = Messages.HELP_CMD_DESCRIPTIONS_HELP;
		help = Messages.HELP_CMD_HELP;
	}
	
	@Override
	public boolean preRequisites(@NotNull CommandData commandData) {
		User sender = commandData.getSender();
		LLPlayerImpl player = ((LastLoginPlugin) plugin).getPlayerManager().getPlayer(sender.getUUID());
		
		// Checks for command prerequisites
		if (!sender.hasPermission(permission)) {
			player.sendNoPermission(permission);
			return false;
		}
		
		((LLCommandData) commandData).setPlayer(player);
		return true;
	}
	
	@Override
	public void onCommand(@NotNull CommandData commandData) {
		LLPlayerImpl player = ((LLCommandData) commandData).getPlayer();
		
		// Command starts
		player.sendMessage(Messages.HELP_HEADER);
		
		Set<ADPCommand> allowedCommands = player.getAllowedCommands();
		for(Map.Entry<ADPCommand, ADPExecutableCommand> e : plugin.getCommandManager().getOrderedCommands().entrySet()) {
			if (allowedCommands.contains(e.getKey()) && e.getValue().isListedInHelp()) {
				player.sendMessage(e.getValue().getHelp()
						.replace("%syntax%", e.getValue().getSyntaxForUser(commandData.getSender()))
						.replace("%description%", e.getValue().getDescription() != null ? e.getValue().getDescription() : "")
						.replace("%run_command%", e.getValue().getRunCommand())
						.replace("%perform_command%", Messages.HELP_PERFORM_COMMAND));
			}
		}
		
		player.sendMessage(Messages.HELP_FOOTER);
	}
}