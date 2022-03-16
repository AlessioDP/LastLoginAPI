package com.alessiodp.lastloginapi.bungeecord.addons.external;

import com.alessiodp.core.bungeecord.addons.external.bstats.bungeecord.Metrics;
import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.external.MetricsHandler;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BungeeMetricsHandler extends MetricsHandler {
	public BungeeMetricsHandler(@NotNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void registerMetrics() {
		new Metrics((Plugin) plugin.getBootstrap(), plugin.getBstatsId());
	}
}
