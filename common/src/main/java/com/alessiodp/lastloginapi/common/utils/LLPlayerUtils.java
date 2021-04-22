package com.alessiodp.lastloginapi.common.utils;

import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.user.User;
import com.alessiodp.core.common.utils.IPlayerUtils;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class LLPlayerUtils implements IPlayerUtils {
	private final LastLoginPlugin plugin;
	
	@Override
	public Set<ADPCommand> getAllowedCommands(@NonNull User user) {
		Set<ADPCommand> ret = new HashSet<>();
		LLPlayerImpl player = plugin.getPlayerManager().getPlayer(user.getUUID());
		if (player != null) {
			ret = player.getAllowedCommands();
		}
		return ret;
	}
}