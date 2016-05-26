package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.Currency;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a java.util.Currency object
 * 
 * @author bulldog
 *
 */
class CurrencyTransform  extends AbstractCompileTimeTransform {
/*
	public Currency read(String value) throws Exception {
		return Currency.getInstance(value);
	}

	public String write(Currency value) throws Exception {
		return value.toString();
	} 
*/
	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(beanClass, property, "$T.getInstance($L.getString($L))"), beanName,Currency.class, cursorName, indexName);		
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
		methodBuilder.addCode("$L."+setter(beanClass, property, "null"), beanName);
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "TEXT";
	}

}
