package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.net.URL;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a java.net.URL object
 * 
 * @author bulldog
 *
 */
public class UrlTransform  extends AbstractCompileTimeTransform {

	public URL read(String value) throws Exception {
	    return new URL(value);
	}

	public String write(URL value) throws Exception {
	    return value.toString();
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(property, "new $T($L.getString($L))"), beanName,URL.class, cursorName, indexName);
		
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
	public void generateResetProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(property, "null"), beanName);
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "TEXT";
	}

}
