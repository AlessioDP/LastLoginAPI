package com.alessiodp.lastloginapi.bungeecord.bootstrap;

import com.alessiodp.core.bungeecord.bootstrap.ADPBungeeBootstrap;
import com.alessiodp.lastloginapi.bungeecord.BungeeLastLoginPlugin;

public class BungeeLastLoginBootstrap extends ADPBungeeBootstrap {
	public BungeeLastLoginBootstrap() {
		plugin = new BungeeLastLoginPlugin(this);
	}
}
