package com.alessiodp.lastloginapi.api;

import com.alessiodp.lastloginapi.api.interfaces.LastLoginAPI;

public class LastLogin {
	private static LastLoginAPI api = null;
	private static boolean flagHook = false;
	
	private LastLogin() {
	}
	
	/**
	 * Get the {@link LastLoginAPI} instance
	 *
	 * @return Returns the {@link LastLoginAPI} interface
	 * @throws IllegalStateException if LastLoginAPI has not been initialized, in other words,
	 *                               LastLoginAPI has not been loaded
	 */
	public static LastLoginAPI getApi() throws IllegalStateException {
		flagHook = true;
		if (api == null)
			throw new IllegalStateException("LastLoginAPI has not been initialized");
		return api;
	}
	
	/**
	 * Set the LastLoginAPI instance. This should not be used.
	 *
	 * @param instance The LastLoginAPI instance.
	 */
	public static void setApi(LastLoginAPI instance) {
		api = instance;
	}
	
	/**
	 * Flag to know if LastLoginAPI has been hooked
	 *
	 * @return Returns true if the API has been hooked at least one time
	 */
	public static boolean isFlagHook() {
		return flagHook;
	}
}
