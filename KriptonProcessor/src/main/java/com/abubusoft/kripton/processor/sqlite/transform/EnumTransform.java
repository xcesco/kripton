package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java5 Enum object
 * 
 * @author bulldog
 * 
 */
class EnumTransform implements Transform {

	public EnumTransform(TypeName typeName) {
		
	}

	@Override
	public void generateReadProperty(MethodSpec.Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName)  {		
		//methodBuilder.addCode("$L."+setter(property, "T$.valueOf($L.getString($L))")+";", beanName,property.getModelType().getName(), cursorName, indexName);
		methodBuilder.addCode("$L."+setter(property, "$T.valueOf($L.getString($L))")+";", beanName,property.getModelType().getName(), cursorName, indexName);
		
	}

	@Override
	public String generateWriteProperty(ModelProperty property) {
		// TODO Auto-generated method stub
		return null;
	}
}