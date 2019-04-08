package com.alessiodp.lastloginapi.common.players;

import com.alessiodp.core.common.commands.utils.ADPPermission;

public enum LastLoginPermission implements ADPPermission {
	ADMIN_UPDATES		("lastloginapi.updates");
	
	private final String perm;
	LastLoginPermission(String t) {
		perm = t;
	}
	
	@Override
	public String toString() {
		return perm;
	}
}
