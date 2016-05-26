package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a Java Character object
 * 
 * @author bulldog
 *
 */
class CharacterTransform  extends AbstractCompileTimeTransform {

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {	
		methodBuilder.addCode("$L."+setter(beanClass, property, "$T.toChars($L.getInt($L))"), beanName,Character.class, cursorName, indexName);
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
	
	public CharacterTransform(boolean nullable)
	{
		defaultValue="0";
		if (nullable)
		{
			defaultValue="null";
		}
	}
	
	protected String defaultValue;
	
	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		
		methodBuilder.addCode("$L."+setter(beanClass, property, defaultValue), beanName);
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "INTEGER";
	}

}
