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
package com.abubusoft.kripton.android.orm;

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
 * @author Francesco Benincasa (info@abubusoft.com)
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
