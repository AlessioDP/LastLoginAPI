package com.alessiodp.lastloginapi.bungeecord.addons;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.AddonManager;
import org.jetbrains.annotations.NotNull;

public class BungeeLLAddonManager extends AddonManager {
	public BungeeLLAddonManager(@NotNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void loadAddons() {
		// Nothing to load
	}
}
