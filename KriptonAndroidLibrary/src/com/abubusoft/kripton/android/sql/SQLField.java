/**
 * 
 */
package com.abubusoft.kripton.android.sql;

import java.io.Serializable;

import com.abubusoft.kripton.binder.annotation.BindDefault;

/**
 * @author xcesco
 * 
 *
 */
@BindDefault
public class SQLField implements Serializable {

	private static final long serialVersionUID = 6881484888866900392L;

	protected String column;
	
	protected String field;

	public String getField() {
		return field;
	}

	public void setField(String fieldName) {
		this.field = fieldName;
	}

	public String getColumnName() {
		return column;
	}

	public void setColumnName(String columnName) {
		this.column = columnName;
	}
}
