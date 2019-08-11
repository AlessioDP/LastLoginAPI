package com.alessiodp.lastloginapi.common.utils;

import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.user.User;
import com.alessiodp.core.common.utils.IPlayerUtils;
import com.alessiodp.lastloginapi.common.commands.list.CommonCommands;
import com.alessiodp.lastloginapi.common.players.LastLoginPermission;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class LLPlayerUtils implements IPlayerUtils {
	@Override
	public List<ADPCommand> getAllowedCommands(@NonNull User user) {
		List<ADPCommand> ret = new ArrayList<>();
		if (user.isPlayer()) {
			if (user.hasPermission(LastLoginPermission.ADMIN_HELP.toString()))
				ret.add(CommonCommands.HELP);
			if (user.hasPermission(LastLoginPermission.ADMIN_RELOAD.toString()))
				ret.add(CommonCommands.RELOAD);
			if (user.hasPermission(LastLoginPermission.ADMIN_VERSION.toString()))
				ret.add(CommonCommands.VERSION);
		}
		return ret;
	}
}