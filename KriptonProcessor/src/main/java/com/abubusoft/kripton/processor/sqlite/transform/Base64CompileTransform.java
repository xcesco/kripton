package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.common.Base64;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;


/**
 * Transformer between a base64 encoded string and a byte[]
 * 
 * @author bulldog
 *
 */
public class Base64CompileTransform extends AbstractCompileTimeTransform {

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(beanClass, property, "$T.decode($L.getBlob($L))"), beanName,Base64.class, cursorName, indexName);				
	}
	
	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getBlob($L)", cursorName, indexName);		
	}
	
	@Override
	public void generateDefaultValue(Builder methodBuilder)
	{
		methodBuilder.addCode("null");		
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("$L."+setter(beanClass, property, "null"), beanName);
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "BLOB";
	}

}
