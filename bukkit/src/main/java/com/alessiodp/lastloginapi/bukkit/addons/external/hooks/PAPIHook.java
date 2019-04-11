package com.alessiodp.lastloginapi.bukkit.addons.external.hooks;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.addons.internal.LLPlaceholder;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PAPIHook extends PlaceholderExpansion {
	@NonNull
	private final LastLoginPlugin plugin;
	
	@Override
	public String getName() {
		return plugin.getPluginName();
	}
	
	@Override
	public String getIdentifier() {
		return plugin.getPluginFallbackName();
	}
	
	@Override
	public String getAuthor() {
		return plugin.getAuthor();
	}
	
	@Override
	public String getVersion() {
		return plugin.getVersion();
	}
	
	@Override
	public List<String> getPlaceholders() {
		List<String> ret = new ArrayList<>();
		for (LLPlaceholder placeholder : LLPlaceholder.values()) {
			ret.add("%" + getIdentifier() + "_" + placeholder.name().toLowerCase() + "%");
		}
		return ret;
	}
	
	public boolean register() {
		boolean ret = false;
		try {
			Class.forName("me.clip.placeholderapi.PlaceholderHook").getMethod("onRequest", OfflinePlayer.class, String.class);
			
			if (PlaceholderAPI.isRegistered(getIdentifier())) {
				PlaceholderAPI.unregisterExpansion(this);
			}
			ret = PlaceholderAPI.registerExpansion(this);
		} catch (Exception ex) {
			plugin.getLoggerManager().printError(Constants.DEBUG_ADDON_OUTDATED
					.replace("{addon}", "PlaceholderAPI"));
		}
		return ret;
	}
	
	@Override
	public String onRequest(OfflinePlayer offlinePlayer, String identifier) {
		plugin.getLoggerManager().logDebug(LLConstants.DEBUG_PLACEHOLDER_RECEIVE
				.replace("{placeholder}", identifier), true);
		LLPlayerImpl player = plugin.getPlayerManager().getPlayer(offlinePlayer.getUniqueId());
		
		LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
		return placeholder != null ? placeholder.formatPlaceholder(player) : "";
	}
}