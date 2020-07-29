package com.alessiodp.lastloginapi.bukkit.utils;

import com.alessiodp.lastloginapi.bukkit.addons.external.PlaceholderAPIHandler;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import com.alessiodp.lastloginapi.common.utils.MessageUtils;

public class BukkitMessageUtils extends MessageUtils {
	
	@Override
	public String convertPlaceholders(String message, LLPlayerImpl player) {
		String ret = super.convertPlaceholders(message, player);
		if (player != null)
			ret = PlaceholderAPIHandler.getPlaceholders(player.getPlayerUUID(), ret);
		return ret;
	}
}