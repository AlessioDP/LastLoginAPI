package com.alessiodp.lastloginapi.common.storage.sql.dao.players;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.lastloginapi.common.LastLoginPlugin;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LLPlayerRowMapper implements RowMapper<LLPlayerImpl> {
	@Override
	public LLPlayerImpl map(ResultSet rs, StatementContext ctx) throws SQLException {
		LLPlayerImpl ret = ((LastLoginPlugin) ADPPlugin.getInstance()).getPlayerManager().initializePlayer(UUID.fromString(rs.getString("uuid")));
		ret.setAccessible(true);
		ret.setName(rs.getString("name"));
		ret.setLastLogin(rs.getLong("lastLogin"));
		ret.setLastLogout(rs.getLong("lastLogout"));
		ret.setAccessible(false);
		return ret;
	}
}
