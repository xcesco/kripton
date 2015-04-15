/**
 * 
 */
package com.abubusoft.kripton.binder.database;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;

/**
 * @author xcesco
 *
 */
public enum NameConverterType {
	UPPER_UNDERSCORE(CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE)),
	LOWER_UNDERSCORE(CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE));
	
	private Converter<String, String> converter;

	private NameConverterType(Converter<String, String> converter)
	{
		this.converter=converter;
	}
	
	public String convertName(String input)
	{
		return converter.convert(input);
	}
	
}
