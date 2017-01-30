package com.abubusoft.kripton.android.sqlite;

import java.util.List;

/**
 * Allow to manage SQL result pages with LIMIT clause.
 * 
 * See <a href="https://www.sqlite.org/lang_select.html">here</a> for more info.
 *  
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 * @param <E>
 */
public abstract class PagedResult<E> {
	
	public PagedResult()
	{
		configured=false;
	}
	
	protected boolean configured;
	
	protected List<E> list;

	public List<E> list()
	{
		return list;
	}

	//abstract void execute();

	public boolean nextPage()
	{
		return false;
	}
		

	public boolean previousPage()
	{
		return false;
	}

	boolean gotoPage(int page) {
		return false;
	}
}
