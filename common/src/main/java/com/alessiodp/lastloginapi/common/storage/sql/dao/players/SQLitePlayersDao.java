package com.alessiodp.lastloginapi.common.storage.sql.dao.players;

import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Set;

public interface SQLitePlayersDao extends PlayersDao {
	@Override
	@SqlUpdate("INSERT OR REPLACE INTO `<prefix>players` (`uuid`, `name`, `lastLogin`, `lastLogout`) VALUES (?, ?, ?, ?)")
	void insert(String uuid, String name, Long lastLogin, Long lastLogout);
	
	@Override
	@SqlQuery("SELECT * FROM `<prefix>players` WHERE name = ? COLLATE NOCASE")
	@RegisterRowMapper(LLPlayerRowMapper.class)
	Set<LLPlayerImpl> getPlayerByName(String name);
}
