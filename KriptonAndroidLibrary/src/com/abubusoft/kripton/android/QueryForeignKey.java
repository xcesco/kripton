/**
 * 
 */
package com.abubusoft.kripton.android;

import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.common.Triple;

/**
 * @author xcesco
 *
 */
public class QueryForeignKey extends Triple<Integer, DatabaseColumn, DatabaseColumn> {

	public QueryForeignKey() {
		super();
	}

	public QueryForeignKey(Integer v1, DatabaseColumn v2, DatabaseColumn v3) {
		super(v1, v2, v3);
	}

}
