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
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class CommandInfo extends LLSubCommand {
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
	public @NotNull String getRunCommand() {
		return baseSyntax();
	}
	
	@Override
	public @NotNull String getConsoleSyntax() {
		return syntaxConsole;
	}
	
	@Override
	public boolean preRequisites(@NotNull CommandData commandData) {
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
	public void onCommand(@NotNull CommandData commandData) {
		User sender = commandData.getSender();
		LLPlayerImpl player = ((LLCommandData) commandData).getPlayer();
		
		// Command handling
		LLPlayerImpl targetPlayer;
		if (commandData.getArgs().length > 1) {
			Set<LLPlayerImpl> players = ((LastLoginPlugin) plugin).getPlayerManager().getPlayerByName(commandData.getArgs()[1]);
			if (players.size() > 0) {
				targetPlayer = players.iterator().next();
			} else {
				sendMessage(sender, player, Messages.CMD_INFO_PLAYERNOTFOUND
						.replace("%player%", commandData.getArgs()[1]));
				return;
			}
		} else if (player == null) {
			sendMessage(sender, null, Messages.LLAPI_SYNTAX_WRONGMESSAGE
					.replace("%syntax%", getConsoleSyntax()));
			return;
		} else {
			targetPlayer = player;
		}
		
		// Command starts
		sendMessage(sender, player, ((LastLoginPlugin) plugin).getMessageUtils().convertPlaceholders(Messages.CMD_INFO_HEADER, targetPlayer));
		
		for (String string : Messages.CMD_INFO_TEXT) {
			sendMessage(sender, player, ((LastLoginPlugin) plugin).getMessageUtils().convertPlaceholders(string, targetPlayer));
		}
		
		sendMessage(sender, player, ((LastLoginPlugin) plugin).getMessageUtils().convertPlaceholders(Messages.CMD_INFO_FOOTER, targetPlayer));
	}
	
	@Override
	public List<String> onTabComplete(@NotNull User sender, String[] args) {
		return plugin.getCommandManager().getCommandUtils().tabCompletePlayerList(args, 1);
	}
}
