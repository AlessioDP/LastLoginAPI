package com.alessiodp.lastloginapi.common.storage.sql.dao.players;

import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Set;

public interface SQLitePlayersDao extends PlayersDao {
	String QUERY_UPDATE = "INSERT OR REPLACE INTO `<prefix>players` (`uuid`, `name`, `lastLogin`, `lastLogout`)" +
			" VALUES (?, ?, ?, ?)";
	String QUERY_GET_BY_NAME = "SELECT * FROM `<prefix>players` WHERE name = ? COLLATE NOCASE";
	
	@Override
	@SqlUpdate(QUERY_UPDATE)
	void insert(String uuid, String name, Long lastLogin, Long lastLogout);
	
	@Override
	@SqlQuery(QUERY_GET_BY_NAME)
	@RegisterRowMapper(LLPlayerRowMapper.class)
	Set<LLPlayerImpl> getPlayerByName(String name);
}
