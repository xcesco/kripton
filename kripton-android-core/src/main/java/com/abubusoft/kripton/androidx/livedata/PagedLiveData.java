/**
 * 
 */
package com.abubusoft.kripton.androidx.livedata;

import com.abubusoft.kripton.android.PageRequest;

/**
 * The paged version of KriptonLiveData.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <T>
 *            the generic type
 */
public abstract class PagedLiveData<T> extends KriptonXLiveData<T> {

	private final PageRequest pageRequest;

	private KriptonXPagedLiveDataHandlerImpl<T> handler;

	public PagedLiveData(PageRequest pageRequest, KriptonXPagedLiveDataHandlerImpl<T> handler) {
		this.pageRequest = pageRequest;
		this.handler = handler;
	}

	public void setPage(Integer currentPage) {
		if (pageRequest.getPage() != currentPage) {
			pageRequest.setPage(currentPage);
			handler.invalidate();
		}
	}

	/**
	 * Move to a specific offset with page size.
	 * 
	 * @param offset
	 * @param limit
	 */
	public void moveTo(int offset, int limit) {
		boolean change = false;
		if (pageRequest.getOffset() != offset) {
			pageRequest.setOffset(offset);
			change = true;
		}

		if (pageRequest.getPageSize() != limit) {
			pageRequest.setPageSize(limit);
			change = true;
		}

		if (change) {
			handler.invalidate();
		}

	}

	public int getPage() {
		return pageRequest.getPage();
	}

	public int getPageSize() {
		return pageRequest.getPageSize();
	}

	public void moveToNextPage() {
		pageRequest.setPage(pageRequest.getPage() + 1);
		handler.invalidate();
	}

	public void moveToPreviousPage() {
		pageRequest.setPage(pageRequest.getPage() - 1);
		handler.invalidate();
	}

	public void moveToFirstPage() {
		if (pageRequest.getPage() != 0) {
			pageRequest.setPage(0);
			handler.invalidate();
		}
	}

}
