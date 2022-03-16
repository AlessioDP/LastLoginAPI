package com.alessiodp.lastloginapi.velocity.addons;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.AddonManager;
import org.jetbrains.annotations.NotNull;

public class VelocityLLAddonManager extends AddonManager {
	public VelocityLLAddonManager(@NotNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void loadAddons() {
		// Nothing to load
	}
}
