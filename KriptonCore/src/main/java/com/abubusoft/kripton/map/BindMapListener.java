package com.abubusoft.kripton.map;

import com.abubusoft.kripton.map.BindMapVisitor.VisitorStatusType;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface BindMapListener {	

	/**
	 * 
	 * @param name
	 * @param value
	 */
	void onField(String name, String value, VisitorStatusType status);

}
