package com.abubusoft.kripton.android;

public interface PageNavigator extends PageRequest {
	/**
	 * Navigate to the first page.
	 */
	void firstPage();

	/**
	 * Navigate to last page
	 */
	void lastPage();

	/**
	 * Navigates to next page.
	 */
	void nextPage();

	/**
	 * Navigates to previous page.
	 */
	void previousPage();

	/**
	 * Navigates to the offset. This method is an alternative to page.
	 * 
	 * @param value
	 */
	void setOffset(int value);

	/**
	 * Navigates to the page. This method is an alternative to offset.
	 * 
	 * @param page
	 */
	void setPage(int page);

	/**
	 * Set page size
	 */
	void setPageSize(int pageSize);
}
