package com.alessiodp.lastloginapi.common.storage.interfaces;

import com.alessiodp.core.common.storage.interfaces.IDatabaseDispatcher;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;

import java.util.Set;
import java.util.UUID;

public interface ILLDatabaseDispatcher extends IDatabaseDispatcher {
	void updatePlayer(LLPlayerImpl player);
	LLPlayerImpl getPlayer(UUID playerUuid);
	Set<LLPlayerImpl> getPlayerByName(String playerName);
}