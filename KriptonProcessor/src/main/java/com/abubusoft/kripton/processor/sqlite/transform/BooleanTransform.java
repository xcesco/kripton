package com.abubusoft.kripton.processor.sqlite.transform;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

/**
 * Transformer between a string and a Java Boolean object
 * 
 * @author bulldog
 *
 */
class BooleanTransform extends AbstractTransform {

	@Override
	public void generateReadProperty(MethodSpec.Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName)  {
		methodBuilder.addCode("$L."+setter(property, "$L.getInt($L)==0?false:true"), beanName,cursorName, indexName);
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

	public BooleanTransform(boolean nullable)
	{
		defaultValue="false";
		if (nullable)
		{
			defaultValue="null";
		}
	}
	
	protected String defaultValue;
	
	@Override
	public void generateResetProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName) {
		
		methodBuilder.addCode("$L."+setter(property, defaultValue), beanName);
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "INTEGER";
	}

}
