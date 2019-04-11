package com.alessiodp.lastloginapi.bungeecord.addons;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.AddonManager;
import lombok.NonNull;

public class BungeeAddonManager extends AddonManager {
	public BungeeAddonManager(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void loadAddons() {
		// Nothing to load
	}
}
