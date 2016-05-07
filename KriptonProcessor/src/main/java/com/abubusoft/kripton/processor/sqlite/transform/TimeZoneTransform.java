package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.TimeZone;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a java.util.TimeZone object
 * 
 * @author bulldog
 *
 */
public class TimeZoneTransform implements Transform {

	public TimeZone read(String value) throws Exception {
	    return TimeZone.getTimeZone(value);
	}

	public String write(TimeZone value) throws Exception {
	    return value.getID();
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(property, "$T.getTimeZone($L.getString($L))")+";", beanName,TimeZone.class, cursorName, indexName);	
		
	}

	@Override
	public String generateWriteProperty(ModelProperty property) {
		// TODO Auto-generated method stub
		return null;
	}

}
