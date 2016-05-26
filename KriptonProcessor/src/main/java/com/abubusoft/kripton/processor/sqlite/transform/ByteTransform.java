package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a Java Byte object
 * 
 * @author bulldog
 *
 */
class ByteTransform  extends AbstractCompileTimeTransform {
	
	public ByteTransform(boolean nullable)
	{
		defaultValue="0";
		if (nullable)
		{
			defaultValue="null";
		}
	}
	 
	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {			
		methodBuilder.addCode("$L."+setter(beanClass, property, "$L.getInt($L)"), beanName,cursorName, indexName);
	}
	
	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getInt($L)", cursorName, indexName);		
	}
	
	@Override
	public void generateDefaultValue(Builder methodBuilder)
	{
		methodBuilder.addCode(defaultValue);		
	}

	protected String defaultValue;
	
	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		
		methodBuilder.addCode("$L."+setter(beanClass, property, "0"), beanName);
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "INTEGER";
	}

}
