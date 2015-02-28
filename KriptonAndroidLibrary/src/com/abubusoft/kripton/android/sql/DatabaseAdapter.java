/**
 * 
 */
package com.abubusoft.kripton.android.sql;

import com.abubusoft.kripton.binder.exception.MappingException;

/**
 * @author Francesco Benincasa
 *
 */
public interface DatabaseAdapter  {

	public String generateCreateTable(Object bean) throws MappingException;
	
	public String generateDropTable(Object bean) throws MappingException;
	
	public SQLSelect generateQuery(String name, Object bean, String fields) throws MappingException;
	
	public void setDatabase(AbstractDatabase database);
}