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
	
	protected PaginatedResult()
	{
		initialized=false;
	}
	
	protected boolean initialized;

	protected int firstRow;

	protected List<E> list;

	protected int pageSize;

	public abstract List<E> execute();

	public int firstRow() {
		return firstRow;
	}

	public boolean nextPage() {
		if (initialized)
		{
			firstRow += pageSize;
		} else {
			initialized=true;	
		}
		
		return execute().size()>0;
	}

	public boolean gotoPage(int page) {
		firstRow = pageSize * page ;
		
		if (firstRow<0) {
			firstRow=0;
			return false;
		}
		
		return execute().size()>0;
	}

	public boolean previousPage() {
		firstRow -= pageSize;

		if (firstRow < -1)
			firstRow = -1;
		
		return execute().size()>0;
	}
	
	public boolean hasNext()
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
