package com.alessiodp.lastloginapi.common.storage.sql.dao.players;

import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;
import java.util.Set;

public interface PlayersDao {
	@SqlUpdate("INSERT INTO `<prefix>players` (`uuid`, `name`, `lastLogin`, `lastLogout`) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE name=VALUES(name), lastLogin=VALUES(lastLogin), lastLogout=VALUES(lastLogout)")
	void insert(String uuid, String name, Long lastLogin, Long lastLogout); // MySQL syntax
	
	@SqlUpdate("DELETE FROM `<prefix>players` WHERE uuid = ?")
	void remove(String uuid);
	
	@SqlQuery("SELECT * FROM `<prefix>players` WHERE uuid = ?")
	@RegisterRowMapper(LLPlayerRowMapper.class)
	Optional<LLPlayerImpl> getPlayer(String uuid);
	
	@SqlQuery("SELECT * FROM `<prefix>players` WHERE name = ?")
	@RegisterRowMapper(LLPlayerRowMapper.class)
	Set<LLPlayerImpl> getPlayerByName(String name);
	
	@SqlQuery("SELECT count(*) FROM `<prefix>players`")
	int countAll();
}
