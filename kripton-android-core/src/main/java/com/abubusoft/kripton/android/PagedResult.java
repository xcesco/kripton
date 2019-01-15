package com.abubusoft.kripton.android;

public interface PagedResult extends PageRequest {

	/**
	 * Total elements.
	 * 
	 * @return
	 * 	total elements
	 */
	int getTotalCount();
}
