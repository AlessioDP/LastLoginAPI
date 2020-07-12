package com.alessiodp.lastloginapi.common.storage.sql.dao.players;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SQLitePlayersDao extends PlayersDao {
	@Override
	@SqlUpdate("INSERT OR REPLACE INTO `<prefix>players` (`uuid`, `name`, `lastLogin`, `lastLogout`) VALUES (?, ?, ?, ?)")
	void insert(String uuid, String name, Long lastLogin, Long lastLogout);
}
