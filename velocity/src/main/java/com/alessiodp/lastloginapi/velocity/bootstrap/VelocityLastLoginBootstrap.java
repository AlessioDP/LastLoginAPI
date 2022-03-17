package com.alessiodp.lastloginapi.velocity.bootstrap;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.velocity.addons.external.bstats.velocity.Metrics;
import com.alessiodp.core.velocity.bootstrap.ADPVelocityBootstrap;
import com.alessiodp.lastloginapi.common.configuration.LLConstants;
import com.alessiodp.lastloginapi.velocity.VelocityConstants;
import com.alessiodp.lastloginapi.velocity.VelocityLastLoginPlugin;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.nio.file.Path;

@Plugin(
		id = LLConstants.PLUGIN_FALLBACK,
		name = LLConstants.PLUGIN_NAME,
		description = VelocityConstants.DESCRIPTION,
		authors = {VelocityConstants.AUTHORS},
		url = VelocityConstants.URL,
		version = VelocityConstants.VERSION
)
public class VelocityLastLoginBootstrap extends ADPVelocityBootstrap {
	@Inject
	public VelocityLastLoginBootstrap(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory, Metrics.Factory metricsFactory) {
		super(server, logger, dataDirectory, metricsFactory);
	}
	
	@Override
	protected ADPPlugin initializePlugin() {
		return new VelocityLastLoginPlugin(this);
	}
	
	@Override
	public @NotNull String getAuthor() {
		return VelocityConstants.AUTHORS;
	}
	
	@Override
	public @NotNull String getVersion() {
		return VelocityConstants.VERSION;
	}
}
