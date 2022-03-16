package com.alessiodp.lastloginapi.bukkit.addons.external.hooks;

import com.alessiodp.core.common.utils.CommonUtils;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.addons.internal.LLPlaceholder;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PAPIHook extends PlaceholderExpansion {
	@NotNull private final LastLoginPlugin plugin;
	
	@Override
	public boolean canRegister() {
		return true;
	}
	
	@Override
	public @NotNull String getName() {
		return plugin.getPluginName();
	}
	
	@Override
	public @NotNull String getIdentifier() {
		return "lastloginapi";
	}
	
	@Override
	public @NotNull String getAuthor() {
		return "AlessioDP";
	}
	
	@Override
	public @NotNull String getVersion() {
		return plugin.getVersion();
	}
	
	@Override
	public boolean persist(){
		return true;
	}
	
	@Override
	public @NotNull List<String> getPlaceholders() {
		List<String> ret = new ArrayList<>();
		for (LLPlaceholder placeholder : LLPlaceholder.values()) {
			ret.add("%" + getIdentifier() + "_" + CommonUtils.toLowerCase(placeholder.name()) + "%");
		}
		return ret;
	}
	
	public String parsePlaceholders(OfflinePlayer player, String msg) {
		return PlaceholderAPI.setPlaceholders(player, msg);
	}
	
	@Override
	public String onRequest(OfflinePlayer offlinePlayer, @NotNull String identifier) {
		if (offlinePlayer != null) {
			plugin.getLoggerManager().logDebug(String.format(LLConstants.DEBUG_PLACEHOLDER_RECEIVE, identifier), true);
			
			LLPlayerImpl player = plugin.getPlayerManager().getPlayer(offlinePlayer.getUniqueId());
			
			LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
			
			return placeholder != null ? placeholder.formatPlaceholder(player, identifier) : "";
		}
		return identifier;
	}
}