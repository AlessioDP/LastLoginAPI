package com.alessiodp.lastloginapi.common.players;

import com.alessiodp.core.common.commands.utils.ADPPermission;

public enum LastLoginPermission implements ADPPermission {
	ADMIN_ALERTS		("lastloginapi.alerts"),
	ADMIN_HELP			("lastloginapi.help"),
	ADMIN_RELOAD		("lastloginapi.reload"),
	ADMIN_VERSION		("lastloginapi.version");
	
	private final String perm;
	LastLoginPermission(String t) {
		perm = t;
	}
	
	@Override
	public String toString() {
		return perm;
	}
}
