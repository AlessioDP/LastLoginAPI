package com.alessiodp.lastloginapi.bukkit.bootstrap;

import com.alessiodp.core.bukkit.bootstrap.ADPBukkitBootstrap;
import com.alessiodp.lastloginapi.bukkit.BukkitLastLoginPlugin;

public class BukkitLastLoginBootstrap  extends ADPBukkitBootstrap {
	public BukkitLastLoginBootstrap() {
		plugin = new BukkitLastLoginPlugin(this);
	}
}