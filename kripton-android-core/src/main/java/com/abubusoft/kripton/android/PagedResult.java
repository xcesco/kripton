package com.abubusoft.kripton.android;

public interface PagedResult extends PageRequest {

	/**
	 * Returns the total amount of elements.
	 *
	 * @return the total amount of elements
	 */
	int getTotalElements();
	
	/**
	 * Returns the number of total pages.
	 *
	 * @return the number of total pages
	 */
	int getTotalPages();
	
	boolean isLast();
	
	public boolean isFirst();
	
	boolean hasNext();
	
	boolean hasPrevious();
		
}
