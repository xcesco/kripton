/**
 * 
 */
package com.abubusoft.kripton.binder.transform;

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
