/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (abubusoft@gmail.com).
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
package com.abubusoft.kripton.android.sqlite;

/**
 * Allow to manage SQL result pages with LIMIT clause.
 * 
 * See <a href="https://www.sqlite.org/lang_select.html">here</a> for more info.
 *  
 * @author xcesco
 *
 */
public class ResultPager {

	/**
	 * Build a result pager specifing only page size. Use it to obtains first page.
	 * 
	 * @param pageSize
	 * @return
	 * 		pager
	 */
	public static ResultPager build(int pageSize) {
		return new ResultPager(pageSize);
	}

	/**
	 * Build a result pager specifing page size and offset (last read rowid). Use it to obtains first page.
	 * 
	 * @param pageSize
	 * 		page size
	 * @param offset
	 * 		offset
	 * @return
	 * 		pager
	 */
	public static ResultPager build(int pageSize, int offset) {
		return new ResultPager(pageSize);
	}

	/**
	 * 
	 */
	int offset;

	int pageSize;

	private ResultPager(int pageSize) {
		this.pageSize = pageSize;
	}

	private ResultPager(int pageSize, int offset) {
		this.pageSize = pageSize;
		this.offset = offset;
	}
	
	/**
	 * Fluid interface for offset.
	 * 
	 * @param offset
	 * @return
	 */
	public ResultPager offset(int offset)
	{
		this.offset=offset;
		return this;
	}

	@Override
	public String toString() {
		return " LIMIT " + pageSize + (offset == 0 ? "" : " OFFSET " + offset);
	}

}
