package com.alessiodp.lastloginapi.bukkit.addons.external;

import com.alessiodp.core.bukkit.addons.external.bstats.bukkit.Metrics;
import com.alessiodp.core.bukkit.addons.external.bstats.charts.SimplePie;
import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.external.MetricsHandler;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import lombok.NonNull;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMetricsHandler extends MetricsHandler {
	public BukkitMetricsHandler(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void registerMetrics() {
		Metrics metrics = new Metrics((JavaPlugin) plugin.getBootstrap(), plugin.getBstatsId());
		
		metrics.addCustomChart(new SimplePie("authme_support", () -> {
			if (BukkitConfigMain.LASTLOGINAPI_AUTHME_ENABLE)
				return "Enabled";
			return "Disabled";
		}));
	}
}
