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
 * <li><strong>pageNumber</strong>: current page.</li>
 * <li><strong>pageSize</strong>: is the number of elements retrieved from datasource. It is also used to define iteraction with</li>
 * <li><strong>offset</strong>: it is an alternative to page as navigation system. It rapresents the distance from the first row. If you mix <code>offset</code> and
 * <li><strong>totalElements</strong>: total number of elements</li>
 * <code>nextPage</code>, the behaviour will be strange (but not wrong). Just remember that <code>nextPage</code> and <code>previousPage</code> work with pages and not with
 * <code>offset</code>.</li>
 * </ul>
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <T>
 *            the generic type
 */
public abstract class PagedLiveData<T> extends KriptonLiveData<T> implements PagedResult {

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
			originalPage = pagedResult.getPageNumber();
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
				handler.invalidate();
			}
		}
	}

	private final PagedResult pagedResult;

	private KriptonPagedLiveDataHandlerImpl<T> handler;

	public PagedLiveData(PagedResult pageRequest, KriptonPagedLiveDataHandlerImpl<T> handler) {
		this.pagedResult = pageRequest;
		this.handler = handler;
	}

	@Override
	public int getPageNumber() {
		return pagedResult.getPageNumber();
	}

	@Override
	public int getPageSize() {
		return pagedResult.getPageSize();
	}

	@Override
	public void setPage(int page) {
		if (pagedResult.getPageNumber() != page) {
			pagedResult.setPage(page);
			handler.invalidate();
		}
	}

	@Override
	public void nextPage() {		
		pagedResult.setPage(pagedResult.getPageNumber() + 1);
		handler.invalidate();		
	}

	@Override
	public void setOffset(int offset) {
		if (pagedResult.getOffset() != offset && offset >= 0) {
			this.pagedResult.setOffset(offset);

			handler.invalidate();
		}
	}

	@Override
	public void previousPage() {
		pagedResult.setPage(pagedResult.getPageNumber() - 1);
		handler.invalidate();
	}

	@Override
	public void firstPage() {
		if (pagedResult.getPageNumber() != 0) {
			pagedResult.setPage(0);
			handler.invalidate();
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

			handler.invalidate();
		}
	}

	@Override
	public int getTotalElements() {
		return pagedResult.getTotalElements();
	}

	@Override
	public void lastPage() {
		setPage(getTotalElements()/getPageSize());		
	}

	@Override
	public int getTotalPages() {
		return pagedResult.getTotalPages();
	}

	@Override
	public boolean isLast() {
		return pagedResult.isLast();
	}

	@Override
	public boolean isFirst() {
		return pagedResult.isFirst();
	}

	@Override
	public boolean hasNext() {
		return pagedResult.hasNext();
	}

	@Override
	public boolean hasPrevious() {
		return pagedResult.hasPrevious();
	}

}
