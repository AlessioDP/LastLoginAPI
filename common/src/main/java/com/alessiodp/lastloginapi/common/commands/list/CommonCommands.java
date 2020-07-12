package com.alessiodp.lastloginapi.common.commands.list;

import com.alessiodp.core.common.commands.list.ADPCommand;

public enum CommonCommands implements ADPCommand {
	LLAPI,
	
	HELP,
	INFO,
	RELOAD,
	VERSION;
	
	@Override
	public String getOriginalName() {
		return this.name();
	}
}
