package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.common.DateUtil;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a java.util.Date object
 * 
 * @author bulldog
 *
 */
class DateTransform extends AbstractCompileTimeTransform {

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("$L." + setter(beanClass, property, "$T.read($L.getString($L))"), beanName, DateUtil.class, cursorName, indexName);

	}
	
	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {	
		methodBuilder.addCode("$T.write($L."+getter(beanClass, property)+")", DateUtil.class, beanName);
	}
	
	@Override
	public void generateWriteProperty(Builder methodBuilder, String objectName) {
		methodBuilder.addCode("$T.write($L)", DateUtil.class, objectName);		
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		methodBuilder.addCode("$L." + setter(beanClass, property, "null"), beanName);
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "TEXT";
	}

	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getString($L)", cursorName, indexName);
	}

	@Override
	public void generateDefaultValue(Builder methodBuilder) {
		methodBuilder.addCode("null");
	}

}
