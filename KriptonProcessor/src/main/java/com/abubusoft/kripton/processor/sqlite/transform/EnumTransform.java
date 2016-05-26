package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a Java5 Enum object
 * 
 * @author bulldog
 * 
 */
class EnumTransform extends AbstractCompileTimeTransform {

	public EnumTransform(TypeName typeName) {
		
	}
	
	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {		
		methodBuilder.addCode("$L."+getter(beanClass, property)+".toString()", beanName);
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {			
		methodBuilder.addCode("$L."+setter(beanClass, property, "$T.valueOf($L.getString($L))"), beanName, property.getModelType().getName(),cursorName, indexName);
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