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

public class CommandVersion extends LLSubCommand {
	
	public CommandVersion(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.VERSION,
				LastLoginPermission.ADMIN_VERSION,
				ConfigMain.COMMANDS_CMD_VERSION,
				true
		);
		
		syntax = baseSyntax();
		
		description = Messages.HELP_CMD_DESCRIPTIONS_VERSION;
		help = Messages.HELP_CMD_VERSION;
	}
	
	@Override
	public @NotNull String getRunCommand() {
		return baseSyntax();
	}
	
	@Override
	public boolean preRequisites(@NotNull CommandData commandData) {
		User sender = commandData.getSender();
		if (sender.isPlayer()) {
			LLPlayerImpl player = ((LastLoginPlugin) plugin).getPlayerManager().getPlayer(sender.getUUID());
			
			// Checks for command prerequisites
			if (!sender.hasPermission(permission)) {
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
		
		// Command starts
		String version = plugin.getVersion();
		String newVersion = plugin.getAdpUpdater().getFoundVersion().isEmpty() ? version : plugin.getAdpUpdater().getFoundVersion();
		String message = version.equals(newVersion) ? Messages.CMD_VERSION_UPDATED : Messages.CMD_VERSION_OUTDATED;
		
		sendMessage(sender, player, message
				.replace("%version%", version)
				.replace("%newversion%", newVersion)
				.replace("%platform%", plugin.getPlatform().getName()));
	}
}
