package com.alessiodp.lastloginapi.common.utils;

import com.alessiodp.core.common.commands.utils.ADPPermission;

public enum LastLoginPermission implements ADPPermission {
	ADMIN_HELP			("lastloginapi.help"),
	ADMIN_INFO			("lastloginapi.info"),
	ADMIN_RELOAD		("lastloginapi.reload"),
	ADMIN_VERSION		("lastloginapi.version"),
	ADMIN_WARNINGS		("lastloginapi.warnings");
	
	private final String perm;
	LastLoginPermission(String t) {
		perm = t;
	}
	
	@Override
	public String toString() {
		return perm;
	}
}
