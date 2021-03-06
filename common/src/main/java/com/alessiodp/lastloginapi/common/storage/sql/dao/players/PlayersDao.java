package com.alessiodp.lastloginapi.common.storage.sql.dao.players;

import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;
import java.util.Set;

public interface PlayersDao {
	String QUERY_INSERT = "INSERT INTO `<prefix>players` (`uuid`, `name`, `lastLogin`, `lastLogout`)" +
			" VALUES (?, ?, ?, ?)" +
			" ON DUPLICATE KEY UPDATE `name`=VALUES(name), `lastLogin`=VALUES(lastLogin), `lastLogout`=VALUES(lastLogout)";
	String QUERY_REMOVE = "DELETE FROM `<prefix>players` WHERE uuid = ?";
	String QUERY_GET = "SELECT * FROM `<prefix>players` WHERE uuid = ?";
	String QUERY_GET_BY_NAME = "SELECT * FROM `<prefix>players` WHERE name = ?";
	String QUERY_COUNT_ALL = "SELECT count(*) FROM `<prefix>players`";
	String QUERY_DELETE_ALL = "DELETE FROM `<prefix>players`";
	
	@SqlUpdate(QUERY_INSERT)
	void insert(String uuid, String name, Long lastLogin, Long lastLogout);
	
	@SqlUpdate(QUERY_REMOVE)
	void remove(String uuid);
	
	@SqlQuery(QUERY_GET)
	@RegisterRowMapper(LLPlayerRowMapper.class)
	Optional<LLPlayerImpl> getPlayer(String uuid);
	
	@SqlQuery(QUERY_GET_BY_NAME)
	@RegisterRowMapper(LLPlayerRowMapper.class)
	Set<LLPlayerImpl> getPlayerByName(String name);
	
	@SqlQuery(QUERY_COUNT_ALL)
	int countAll();
	
	@SqlUpdate(QUERY_DELETE_ALL)
	void deleteAll();
}
