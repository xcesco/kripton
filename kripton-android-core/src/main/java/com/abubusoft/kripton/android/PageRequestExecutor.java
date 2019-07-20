package com.abubusoft.kripton.android;

public interface PageRequestExecutor<E> {

	/**
	 * Execute a page request
	 * 
	 * @param pageRequest
	 * @return
	 */
	E execute(PageRequest pageRequest);

	/**
	 * Execute a page request
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	E execute(int pageNumber, int pageSize);
}
