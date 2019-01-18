package com.abubusoft.kripton.android;

/**
 * <p>
 * The paged version of KriptonLiveData. A paged live data allows to move in the dataset with three parameters:
 * </p>
 * <ul>
 * <li><strong>page</strong>: current page.</li>
 * <li><strong>pageSize</strong>: is the number of elements retrieved from datasource. It is also used to define iteraction with</li>
 * <li><strong>offset</strong>: it is an alternative to page as navigation system. It rapresents the distance from the first row. If you mix <code>offset</code> and
 * <code>nextPage</code>, the behaviour will be strange (but not wrong). Just remember that <code>nextPage</code> and <code>previousPage</code> work with pages and not with
 * <code>offset</code>.</li>
 * </ul>
 * 
 * @author xcesco
 *
 */
public interface PageRequest {
	/**
	 * Navigate to the first page.
	 */
	void firstPage();
	
	/**
	 * Navigate to last page
	 */
	void lastPage();

	/**
	 * Set the current offset from start of dataset.
	 * 
	 * @return
	 */
	int getOffset();

	/**
	 * Current page
	 * 
	 * @return Current page
	 */
	int getPageNumber();

	/**
	 * Page size used to navigate
	 * 
	 * @return Page size used to navigate
	 */
	int getPageSize();

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
