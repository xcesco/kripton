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

	/**
	 * Allows to create a builder for a page request. This builder is usefully when you need to modify different parameter of page request and you want to make only a page request.
	 * 
	 * @return
	 */
	public PageRequestBuilder createPageRequestBuilder() {
		return new PageRequestBuilder();
	}

	/**
	 * This builder allows you to manipulate page request object, changing some its attributes and invoke an unique update to live date.
	 * 
	 * @author xcesco
	 *
	 */
	public class PageRequestBuilder {
		private int offset;
		private int page;
		private int pageSize;

		private PageRequestBuilder() {
			offset = pageRequest.getOffset();
			page = pageRequest.getPage();
			pageSize = pageRequest.getPageSize();
		}

		/**
		 * change offset
		 * 
		 * @param value
		 * @return
		 */
		public PageRequestBuilder offset(int value) {
			offset = value;
			return this;
		}

		/**
		 * change page
		 * 
		 * @param value
		 * @return
		 */
		public PageRequestBuilder page(int value) {
			page = value;
			return this;
		}

		/**
		 * change pageSize
		 * 
		 * @param value
		 * @return
		 */
		public PageRequestBuilder pageSize(int value) {
			pageSize = value;
			return this;
		}

		/**
		 * Applies all the change you defined with this builder. Backend livedata will be updated. If nothing changes, no livedata update will be performed.
		 */
		public void apply() {
			boolean changes = false;
			if (pageRequest.getOffset() != offset) {
				changes = true;
				pageRequest.setOffset(offset);
			}

			if (pageRequest.getPageSize() != pageSize) {
				changes = true;
				pageRequest.setPageSize(pageSize);
			}

			if (pageRequest.getPage() != page) {
				changes = true;
				pageRequest.setPage(page);
			}

			if (changes) {
				backed.invalidate();
			}
		}
	}

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
