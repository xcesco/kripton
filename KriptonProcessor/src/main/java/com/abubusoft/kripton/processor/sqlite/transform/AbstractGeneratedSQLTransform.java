package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

public class AbstractGeneratedSQLTransform extends AbstractSQLTransform {

	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		methodBuilder.addCode("$TTable.serialize$L($L)", beanClass, formatter.convert(property.getName()), getter(beanName, beanClass, property));
	}

	@Override
	public void generateWriteQueryParameter(Builder methodBuilder, String objectName, String serializerName) {
		methodBuilder.addCode("$L($L)", serializerName, objectName);
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "$TTable.parse$L($L.getBlob($L))"), beanClass, formatter.convert(property.getName()), cursorName, indexName);
	}

	@Override
	public boolean isJava2ContentSerializerNeeded() {
		return true;
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "null"));
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "BLOB";
	}
}
