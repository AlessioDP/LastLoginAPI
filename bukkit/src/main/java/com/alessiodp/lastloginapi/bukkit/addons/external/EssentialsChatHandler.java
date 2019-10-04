package com.alessiodp.lastloginapi.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.addons.internal.LLPlaceholder;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class EssentialsChatHandler implements Listener {
	@NonNull private final LastLoginPlugin plugin;
	private static final String ADDON_NAME = "EssentialsChat";
	private final Pattern PATTERN = Pattern.compile("\\{lastloginapi_([a-z_]+)}", Pattern.CASE_INSENSITIVE);
	
	public void init() {
		if (Bukkit.getPluginManager().getPlugin(ADDON_NAME) != null) {
			((Plugin) plugin.getBootstrap()).getServer().getPluginManager().registerEvents(this, ((Plugin) plugin.getBootstrap()));
			
			plugin.getLoggerManager().log(Constants.DEBUG_ADDON_HOOKED
					.replace("{addon}", ADDON_NAME), true);
		}
	}
	
	
	@EventHandler
	public void onChatPlayer(AsyncPlayerChatEvent event) {
		String old = event.getFormat();
		if (old.toLowerCase(Locale.ENGLISH).contains("{lastloginapi_")) {
			// Bypass useless checks if this isn't a LastLoginAPI placeholder
			boolean somethingChanged = false;
			LLPlayerImpl player = plugin.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
			
			Matcher mat = PATTERN.matcher(old);
			while (mat.find()) {
				String base = mat.group(0);
				String identifier = mat.group(1);
				if (identifier != null) {
					LLPlaceholder placeholder = LLPlaceholder.getPlaceholder(identifier);
					if (placeholder != null) {
						old = old.replace(base, plugin.getColorUtils().convertColors(placeholder.formatPlaceholder(player)));
						somethingChanged = true;
					}
				}
			}
			
			if(somethingChanged)
				event.setFormat(old);
		}
	}
}