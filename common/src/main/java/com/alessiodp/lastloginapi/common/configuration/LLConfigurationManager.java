package com.alessiodp.lastloginapi.common.configuration;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.configuration.ConfigurationManager;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.lastloginapi.common.configuration.data.ConfigMain;
import com.alessiodp.lastloginapi.common.configuration.data.Messages;
import com.alessiodp.lastloginapi.common.utils.LastLoginPermission;

public abstract class LLConfigurationManager extends ConfigurationManager {
	
	public LLConfigurationManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void performChanges() {
		plugin.getDatabaseManager().setDatabaseType(StorageType.getEnum(ConfigMain.STORAGE_TYPE_DATABASE));
		plugin.getLoginAlertsManager().setPermission(LastLoginPermission.ADMIN_WARNINGS);
		checkOutdatedConfigs(Messages.LLAPI_CONFIGURATION_OUTDATED);
	}
}
