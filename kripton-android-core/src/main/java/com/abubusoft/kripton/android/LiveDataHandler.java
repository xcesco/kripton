package com.abubusoft.kripton.android;

public interface LiveDataHandler {

	/**
	 * Invalidates the LiveData.
	 * <p>
	 * When there are active observers, this will trigger a call to
	 * {@link #compute()}.
	 */
	void invalidate();

}