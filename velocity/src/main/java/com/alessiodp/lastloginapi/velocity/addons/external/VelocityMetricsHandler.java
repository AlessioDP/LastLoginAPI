package com.alessiodp.lastloginapi.velocity.addons.external;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.external.MetricsHandler;
import com.alessiodp.core.velocity.bootstrap.ADPVelocityBootstrap;
import org.jetbrains.annotations.NotNull;

public class VelocityMetricsHandler extends MetricsHandler {
	public VelocityMetricsHandler(@NotNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void registerMetrics() {
		ADPVelocityBootstrap velocityPlugin = ((ADPVelocityBootstrap) plugin.getBootstrap());
		velocityPlugin.getMetricsFactory().make(velocityPlugin, plugin.getBstatsId());
	}
}
