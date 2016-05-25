package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a Java Float object
 * 
 * @author bulldog
 *
 */
public class FloatTransform  extends AbstractCompileTimeTransform {

	@Override
	public void generateReadProperty(MethodSpec.Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName)  {
		methodBuilder.addCode("$L."+setter(property, "$L.getFloat($L)"), beanName,cursorName, indexName);
	}
	
	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getFloat($L)", cursorName, indexName);		
	}

	public FloatTransform(boolean nullable)
	{
		defaultValue="0f";
		if (nullable)
		{
			defaultValue="null";
		}
	}
	
	@Override
	public void generateDefaultValue(Builder methodBuilder)
	{
		methodBuilder.addCode(defaultValue);		
	}
	
	protected String defaultValue;
	
	@Override
	public void generateResetProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName) {
		
		methodBuilder.addCode("$L."+setter(property, defaultValue), beanName);
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "REAL";
	}

}
