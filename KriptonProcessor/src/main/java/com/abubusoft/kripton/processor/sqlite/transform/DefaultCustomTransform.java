/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.transform;

import com.abubusoft.kripton.binder.transform.CustomTransform;

/**
 * @author xcesco
 *
 */
public class DefaultCustomTransform implements CustomTransform<Object> {

	@Override
	public Object read(String value) throws Exception {
		return value;
	}

	@Override
	public String write(Object value) throws Exception {
		if (value!=null)
			return value.toString();
		
		return null;
	}
	
	

}
