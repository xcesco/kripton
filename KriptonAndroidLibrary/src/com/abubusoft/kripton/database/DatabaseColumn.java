/**
 * 
 */
package com.abubusoft.kripton.database;

import com.abubusoft.kripton.binder.schema.ElementSchema;

/**
 * @author xcesco
 *
 */
public class DatabaseColumn {

	public String name;
	
	public ColumnType feature;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatabaseColumn [name=" + name + ", type=" + type + "]";
	}

	public ElementSchema schema;
	
	public String type;
}
