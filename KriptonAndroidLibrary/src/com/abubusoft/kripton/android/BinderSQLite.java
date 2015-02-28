/**
 * 
 */
package com.abubusoft.kripton.android;

import com.abubusoft.kripton.binder.annotation.BindRoot;

/**
 * Tabella per la conversione degli oggetti tra tabella ed entity.
 * 
 * @author Cesco
 *
 */
public class BinderSQLite {

	public BinderSQLiteTable getBinder(Class<?> clazz)
	{
		BinderSQLiteTable table=new BinderSQLiteTable();
		
		BindRoot root = (BindRoot) clazz.getAnnotation(BindRoot.class);
		if (root!=null)
		{
			String name=root.name();
			if (name==null)
			{
				name=clazz.getSimpleName();
			}
			table.name=name;
		}
		
		return table;
	}
}
