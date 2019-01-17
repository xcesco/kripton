/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.android.PagedResult;

/**
 * <p>
 * Allow to manage SQL result pages with LIMIT clause.
 * </p>
 * 
 * <p>
 * See <a href="https://www.sqlite.org/lang_select.html">here</a> for more info.
 * </p>
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <E>
 *            the element type
 */
/**
 * @author xcesco
 *
 * @param <E>
 */
public abstract class PagedResultImpl<E> implements PagedResult {

	/**
	 * if false, paged result does not contains valid paged result
	 */
	protected boolean paged;

	/** The first row. */
	protected int offset;

	/** The list. */
	protected volatile List<E> list;

	/** The page size. */
	protected int pageSize;

	/**
	 * number of total element extracted by the query
	 */
	protected volatile int totalElements = 0;

	/**
	 * Instantiates a new paginated result.
	 */
	protected PagedResultImpl() {
		reset();
	}

	/**
	 * execute method
	 * 
	 * @return result
	 */
	public abstract List<E> execute();

	@Override
	public void firstPage() {
		setPage(0);

		execute();
	}

	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<E> getList() {
		return list;
	}

	/**
	 * First row.
	 *
	 * @return the int
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Get current Page
	 * 
	 * @return 0-based number of current page
	 */
	@Override
	public int getPageNumber() {
		if (!paged)
			return 0;

		return offset / pageSize;
	}

	/**
	 * Page size.
	 *
	 * @return the int
	 */
	@Override
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Get Total count
	 */
	@Override
	public int getTotalElements() {
		return totalElements;
	}

	/**
	 * Has previous pages
	 * 
	 * @return
	 */
	public boolean hasPrevious() {
		return !paged || (offset > 0 && totalElements > 0);
	}

	public void setTotalElements(int value) {
		this.totalElements = value;
	}

	/**
	 * Next page.
	 *
	 * @return true, if successful
	 */
	@Override
	public void nextPage() {
		if (!paged) {			
			this.firstPage();
			paged = true;
		} else if (!isLast()) {
			offset = (getPageNumber() + 1) * pageSize;

			execute();
		}
	}

	/**
	 * Previous page.
	 *
	 * @return true, if successful
	 */
	@Override
	public void previousPage() {
		if (!isFirst()) {
			offset = (getPageNumber() - 1) * pageSize;

			execute();
		}
	}

	@Override
	public void setOffset(int offset) {
		if (this.offset != offset && offset >= 0) {
			this.offset = offset;
		}
	}

	/**
	 * Goto page.
	 *
	 * @param page
	 *            the page
	 * @return true, if successful
	 */
	@Override
	public void setPage(int page) {					
		offset = pageSize * page;

		// check to stay in the range
		/*if (offset < 0) {
			offset = 0;
		}*/
		
		/*if (paged && offset > pageSize * (getTotalPages() - 1)) {
			offset = pageSize * (getTotalPages() - 1);
		}*/
		
		paged=true;
	}

	@Override
	public int getTotalPages() {
		return (int) Math.ceil((double) getTotalElements() / (double) getPageSize());
	}

	@Override
	public boolean isLast() {
		return !hasNext();
	}

	@Override
	public boolean isFirst() {
		return getPageNumber() > 0;
	}

	@Override
	public void lastPage() {
		setPage(getTotalPages() - 1);

		execute();
	}

	@Override
	public boolean hasNext() {
		return !paged || (getPageNumber() < getTotalPages() - 1);
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0 && this.pageSize != pageSize) {
			this.pageSize = pageSize;
		}
	}

	/**
	 * Reset
	 */
	public void reset() {
		this.paged = false;
		this.totalElements = -1;
		this.list=new ArrayList<>();

	}
}
