/**
 * 
 */
package com.abubusoft.kripton.android.livedata;

import com.abubusoft.kripton.android.PagedResult;

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
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <T>
 *            the generic type
 */
public class PagedLiveData<T> extends KriptonLiveData<T> implements PagedResult {

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
		private int originalOffset;
		private int originalPage;
		private int originalPageSize;

		private int offset;
		private int page;
		private int pageSize;

		private PageRequestBuilder() {
			originalOffset = pagedResult.getOffset();
			originalPage = pagedResult.getPage();
			originalPageSize = pagedResult.getPageSize();

			offset = originalOffset;
			page = originalPage;
			pageSize = originalPageSize;
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
			if (originalOffset != offset) {
				changes = true;
				pagedResult.setOffset(offset);
			}

			if (originalPageSize != pageSize) {
				changes = true;
				pagedResult.setPageSize(pageSize);
			}

			if (originalPage != page) {
				changes = true;
				pagedResult.setPage(page);
			}

			if (changes) {
				backed.invalidate();
			}
		}
	}

	private final PagedResult pagedResult;

	private KriptonPagedLiveDataHandlerImpl<T> backed;

	public PagedLiveData(PagedResult pageRequest, KriptonPagedLiveDataHandlerImpl<T> backed) {
		this.pagedResult = pageRequest;
		this.backed = backed;
	}

	@Override
	public int getPage() {
		return pagedResult.getPage();
	}

	@Override
	public int getPageSize() {
		return pagedResult.getPageSize();
	}

	@Override
	public void setPage(int page) {
		if (pagedResult.getPage() != page) {
			pagedResult.setPage(page);
			backed.invalidate();
		}
	}

	@Override
	public void nextPage() {
		pagedResult.setPage(pagedResult.getPage() + 1);
		backed.invalidate();
	}

	@Override
	public void setOffset(int offset) {
		if (pagedResult.getOffset() != offset && offset >= 0) {
			this.pagedResult.setOffset(offset);

			backed.invalidate();
		}
	}

	@Override
	public void previousPage() {
		pagedResult.setPage(pagedResult.getPage() - 1);
		backed.invalidate();
	}

	@Override
	public void firstPage() {
		if (pagedResult.getPage() != 0) {
			pagedResult.setPage(0);
			backed.invalidate();
		}
	}

	@Override
	public int getOffset() {
		return pagedResult.getOffset();
	}

	@Override
	public void setPageSize(int pageSize) {
		if (pagedResult.getPageSize() != pageSize && pageSize > 0) {
			this.pagedResult.setPageSize(pageSize);

			backed.invalidate();
		}

	}

	@Override
	public int getTotalCount() {
		return pagedResult.getTotalCount();
	}

}
