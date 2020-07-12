package com.alessiodp.lastloginapi.common.commands.utils;

import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.lastloginapi.common.players.objects.LLPlayerImpl;
import lombok.Getter;
import lombok.Setter;

public class LLCommandData extends CommandData {
	@Getter @Setter private LLPlayerImpl player;
}