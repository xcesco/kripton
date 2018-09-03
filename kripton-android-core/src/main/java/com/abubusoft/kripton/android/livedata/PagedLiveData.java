/**
 * 
 */
package com.abubusoft.kripton.android.livedata;

import com.abubusoft.kripton.android.PageRequest;

/**
 * The paged version of KriptonLiveData.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <T>
 *            the generic type
 */
public class PagedLiveData<T> extends KriptonLiveData<T> implements PageRequest {

	private final PageRequest pageRequest;

	private KriptonPagedLiveDataHandlerImpl<T> backed;

	public PagedLiveData(PageRequest pageRequest, KriptonPagedLiveDataHandlerImpl<T> backed) {
		this.pageRequest = pageRequest;
		this.backed = backed;
	}

	@Override
	public int getPage() {
		return pageRequest.getPage();
	}

	@Override
	public int getPageSize() {
		return pageRequest.getPageSize();
	}

	@Override
	public void setPage(int page) {
		if (pageRequest.getPage() != page) {
			pageRequest.setPage(page);
			backed.invalidate();
		}
	}

	@Override
	public void nextPage() {
		pageRequest.setPage(pageRequest.getPage() + 1);
		backed.invalidate();
	}

	@Override
	public void setOffset(int offset) {
		if (pageRequest.getOffset() != offset && offset >= 0) {
			this.pageRequest.setOffset(offset);

			backed.invalidate();
		}
	}

	@Override
	public void previousPage() {
		pageRequest.setPage(pageRequest.getPage() - 1);
		backed.invalidate();
	}

	@Override
	public void firstPage() {
		if (pageRequest.getPage() != 0) {
			pageRequest.setPage(0);
			backed.invalidate();
		}
	}

	@Override
	public int getOffset() {
		return pageRequest.getOffset();
	}

	@Override
	public void setPageSize(int pageSize) {
		if (pageRequest.getPageSize() != pageSize && pageSize > 0) {
			this.pageRequest.setPageSize(pageSize);

			backed.invalidate();
		}

	}

}
