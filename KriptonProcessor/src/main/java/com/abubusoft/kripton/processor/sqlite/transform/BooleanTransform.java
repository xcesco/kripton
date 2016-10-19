package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java Boolean object
 * 
 * @author bulldog
 *
 */
class BooleanTransform extends AbstractCompileTimeTransform {

	protected String defaultValue;
	
	public BooleanTransform(boolean nullable)
	{
		defaultValue="false";
		if (nullable)
		{
			defaultValue="null";
		}
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "INTEGER";
	}

	@Override
	public void generateDefaultValue(Builder methodBuilder)
	{
		methodBuilder.addCode(defaultValue);		
	}
	
	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getInt($L)==0?false:true", cursorName, indexName);		
	}
	
	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(beanClass, property, "$L.getInt($L)==0?false:true"), beanName,cursorName, indexName);
	}
	
	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		
		methodBuilder.addCode("$L."+setter(beanClass, property, defaultValue), beanName);
	}

}
