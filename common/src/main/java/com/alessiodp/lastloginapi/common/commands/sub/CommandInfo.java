package com.alessiodp.lastloginapi.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.core.common.utils.Color;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.commands.list.CommonCommands;
import com.alessiodp.lastloginapi.common.commands.utils.LLCommandData;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.utils.LastLoginPermission;

import java.util.Set;

public class CommandInfo extends ADPSubCommand {
	private final String syntaxConsole;
	
	public CommandInfo(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.INFO,
				LastLoginPermission.ADMIN_INFO,
				ConfigMain.COMMANDS_CMD_INFO,
				true
		);
		
		syntax = String.format("%s [%s]",
				baseSyntax(),
				Messages.LLAPI_SYNTAX_PLAYER
		);
		
		syntaxConsole = String.format("%s <%s>",
				baseSyntax(),
				Messages.LLAPI_SYNTAX_PLAYER
		);
		
		description = Messages.HELP_CMD_DESCRIPTIONS_INFO;
		help = Messages.HELP_CMD_INFO;
	}
	
	@Override
	public String getRunCommand() {
		return baseSyntax();
	}
	
	@Override
	public String getConsoleSyntax() {
		return syntaxConsole;
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		if (sender.isPlayer()) {
			LLPlayerImpl player = ((LastLoginPlugin) plugin).getPlayerManager().getPlayer(sender.getUUID());
			
			// Checks for command prerequisites
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
		
		if (player != null)
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_CMD_INFO
					.replace("{player}", player.getName())
					.replace("{victim}", commandData.getArgs().length > 1 ? commandData.getArgs()[1] : ""), true);
		else
			plugin.getLoggerManager().logDebug(LLConstants.DEBUG_CMD_INFO_CONSOLE
					.replace("{victim}", commandData.getArgs().length > 1 ? commandData.getArgs()[1] : ""), true);
		
		// Command handling
		LLPlayerImpl targetPlayer = null;
		if (commandData.getArgs().length > 1) {
			Set<LLPlayerImpl> players = ((LastLoginPlugin) plugin).getPlayerManager().getPlayerByName(commandData.getArgs()[1]);
			if (players.size() > 0) {
				targetPlayer = players.iterator().next();
			} else {
				sendMessage(player, Messages.CMD_INFO_PLAYERNOTFOUND.replace("%player%", commandData.getArgs()[1]));
				return;
			}
		} else if (player == null) {
			sendMessage(player, Messages.LLAPI_SYNTAX_WRONGMESSAGE.replace("%syntax%", getConsoleSyntax()));
			return;
		} else {
			targetPlayer = player;
		}
		
		// Command starts
		sendMessage(player, ((LastLoginPlugin) plugin).getMessageUtils().convertPlaceholders(Messages.CMD_INFO_HEADER, targetPlayer));
		
		for (String string : Messages.CMD_INFO_TEXT) {
			sendMessage(player, ((LastLoginPlugin) plugin).getMessageUtils().convertPlaceholders(string, targetPlayer));
		}
		
		sendMessage(player, ((LastLoginPlugin) plugin).getMessageUtils().convertPlaceholders(Messages.CMD_INFO_FOOTER, targetPlayer));
	}
	
	private void sendMessage(LLPlayerImpl player, String message) {
		if (player != null)
			player.sendMessage(message);
		else
			plugin.logConsole(Color.translateAndStripColor(message), false);
	}
}
