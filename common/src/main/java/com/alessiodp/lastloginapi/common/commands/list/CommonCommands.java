package com.alessiodp.lastloginapi.common.commands.list;

import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import com.alessiodp.lastloginapi.common.players.LastLoginPermission;
import lombok.Getter;

public enum CommonCommands implements ADPCommand {
	LLAPI,
	
	HELP,
	RELOAD,
	VERSION;
	
	@Getter private String command;
	@Getter private String help;
	@Getter private String permission;
	
	CommonCommands() {
		command = "";
		help = "";
		permission = "";
	}
	
	public static void setup() {
		CommonCommands.LLAPI.command = "llapi";
		
		CommonCommands.HELP.command = "help";
		CommonCommands.HELP.help = Messages.HELP_CMD_HELP;
		CommonCommands.HELP.permission = LastLoginPermission.ADMIN_HELP.toString();
		CommonCommands.RELOAD.command = "reload";
		CommonCommands.RELOAD.help = Messages.HELP_CMD_RELOAD;
		CommonCommands.RELOAD.permission = LastLoginPermission.ADMIN_RELOAD.toString();
		CommonCommands.VERSION.command = "version";
		CommonCommands.VERSION.help = Messages.HELP_CMD_VERSION;
		CommonCommands.VERSION.permission = LastLoginPermission.ADMIN_VERSION.toString();
	}
	
	@Override
	public String getOriginalName() {
		return this.name();
	}
}
