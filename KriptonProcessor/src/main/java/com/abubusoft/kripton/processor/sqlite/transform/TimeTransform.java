package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.sql.Time;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a custom Time object
 * 
 * @author bulldog
 *
 */
class TimeTransform implements Transform {

	public Time read(String value) throws Exception {
		return Time.valueOf(value);
	}

	public String write(Time value) throws Exception {
		return value.toString();
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(property, "$T.valueOf($L.getString($L))")+";", beanName,Time.class, cursorName, indexName);	
	}

	@Override
	public String generateWriteProperty(ModelProperty property) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void generateResetProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(property, "null")+";", beanName);
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "TEXT";
	}

}
