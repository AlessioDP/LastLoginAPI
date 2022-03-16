package com.alessiodp.lastloginapi.bukkit.addons.external;

import com.alessiodp.core.bukkit.addons.external.bstats.bukkit.Metrics;
import com.alessiodp.core.bukkit.addons.external.bstats.charts.SimplePie;
import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.external.MetricsHandler;
import com.alessiodp.lastloginapi.bukkit.configuration.data.BukkitConfigMain;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class BukkitMetricsHandler extends MetricsHandler {
	public BukkitMetricsHandler(@NotNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void registerMetrics() {
		Metrics metrics = new Metrics((JavaPlugin) plugin.getBootstrap(), plugin.getBstatsId());
		
		metrics.addCustomChart(new SimplePie("login_support", () -> {
			if (BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_AUTHME
					|| BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_LOGINSECURITY
					|| BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_NLOGIN
					|| BukkitConfigMain.LASTLOGINAPI_LOGINPLUGINS_OPENLOGIN)
				return "Enabled";
			return "Disabled";
		}));
	}
}
