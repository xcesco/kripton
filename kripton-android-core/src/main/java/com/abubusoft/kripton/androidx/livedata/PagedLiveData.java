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

	private KriptonXPagedLiveDataHandlerImpl<T> backed;

	public PagedLiveData(PageRequest pageRequest, KriptonXPagedLiveDataHandlerImpl<T> backed) {
		this.pageRequest = pageRequest;
		this.backed = backed;
	}

	public void setPage(Integer currentPage) {
		if (pageRequest.getPage() != currentPage) {
			pageRequest.setPage(currentPage);
			backed.invalidate();
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
		backed.invalidate();
	}

	public void moveToPreviousPage() {
		pageRequest.setPage(pageRequest.getPage() - 1);
		backed.invalidate();
	}

	public void moveToFirstPage() {
		if (pageRequest.getPage() != 0) {
			pageRequest.setPage(0);
			backed.invalidate();
		}
	}

}
