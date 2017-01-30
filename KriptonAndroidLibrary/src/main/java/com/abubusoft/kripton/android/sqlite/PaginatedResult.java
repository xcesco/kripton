package com.abubusoft.kripton.android.sqlite;

import java.util.List;

/**
 * <p>
 * Allow to manage SQL result pages with LIMIT clause.
 * </p>
 * 
 * <p>
 * See <a href="https://www.sqlite.org/lang_select.html">here</a> for more info.
 * </p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 * @param <E>
 */
public abstract class PaginatedResult<E> {

	protected int firtRow;

	protected List<E> list;

	protected int pageSize;

	public abstract List<E> execute();

	public int firstRow() {
		return firtRow;
	}

	public void nextPage() {
		firtRow += pageSize;
	}

	public void gotoPage(int page) {
		firtRow = pageSize * page - 1;
	}

	public void previousPage() {
		firtRow -= pageSize;

		if (firtRow < -1)
			firtRow = -1;
	}
	
	public boolean hasRows()
	{
		return list.size()>0;
	}

	public List<E> list() {
		return list;
	}

	public int pageSize() {
		return pageSize;
	}
}
