package com.abubusoft.kripton.android;

/**
 * <p>
 * The paged version of KriptonLiveData. A paged live data allows to move in the
 * dataset with three parameters:
 * </p>
 * <ul>
 * <li><strong>page</strong>: current page.</li>
 * <li><strong>pageSize</strong>: is the number of elements retrieved from
 * datasource. It is also used to define iteraction with</li>
 * <li><strong>offset</strong>: it is an alternative to page as navigation
 * system. It rapresents the distance from the first row. If you mix
 * <code>offset</code> and <code>nextPage</code>, the behaviour will be strange
 * (but not wrong). Just remember that <code>nextPage</code> and
 * <code>previousPage</code> work with pages and not with
 * <code>offset</code>.</li>
 * </ul>
 * 
 * @author xcesco
 *
 */
public interface PageRequest {

	public static PageRequest build(int pageNumber, int pageSize) {
		return new PageRequestImpl(pageNumber, pageSize);
	}

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

}
