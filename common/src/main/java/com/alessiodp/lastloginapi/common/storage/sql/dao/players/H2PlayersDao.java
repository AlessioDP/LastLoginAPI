package com.alessiodp.lastloginapi.common.storage.sql.dao.players;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface H2PlayersDao extends PlayersDao {
	@Override
	@SqlUpdate("MERGE INTO `<prefix>players` (`uuid`, `name`, `lastLogin`, `lastLogout`) VALUES (?, ?, ?, ?)")
	void insert(String uuid, String name, Long lastLogin, Long lastLogout);
}
