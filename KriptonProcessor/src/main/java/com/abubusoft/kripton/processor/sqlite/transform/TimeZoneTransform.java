package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.TimeZone;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a java.util.TimeZone object
 * 
 * @author bulldog
 *
 */
public class TimeZoneTransform  extends AbstractCompileTimeTransform {

	public TimeZone read(String value) throws Exception {
		return TimeZone.getTimeZone(value);
	}

	public String write(TimeZone value) throws Exception {
		return value.getID();
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("$L." + setter(beanClass, property, "$T.getTimeZone($L.getString($L))") , beanName, TimeZone.class, cursorName, indexName);

	}

	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getString($L)", cursorName, indexName);
	}

	@Override
	public void generateDefaultValue(Builder methodBuilder)
	{
		methodBuilder.addCode("null");		
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		methodBuilder.addCode("$L." + setter(beanClass, property, "null") , beanName);
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "TEXT";
	}

}
