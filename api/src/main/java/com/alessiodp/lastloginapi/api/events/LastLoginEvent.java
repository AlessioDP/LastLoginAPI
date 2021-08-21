package com.alessiodp.lastloginapi.api.events;

import com.alessiodp.lastloginapi.api.interfaces.LastLoginAPI;
import org.jetbrains.annotations.NotNull;

public interface LastLoginEvent {
	/**
	 * Get the LastLogin API instance
	 *
	 * @return Returns the {@link LastLoginAPI}
	 */
	@NotNull
	LastLoginAPI getApi();
	
	/**
	 * Set the LastLogin API instance. Used by LastLoginAPI instance to let you hook directly to the main API.
	 *
	 * @param instance {@link LastLoginAPI} instance to set
	 */
	void setApi(LastLoginAPI instance);
}
