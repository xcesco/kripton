package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

public class AbstractGeneratedSQLTransform extends AbstractSQLTransform {

	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		methodBuilder.addCode("$T.serialize$L($L)", TypeUtility.mergeTypeName(beanClass, "Table"), formatter.convert(property.getName()), getter(beanName, beanClass, property));
	}

	@Override
	public void generateWriteParam(Builder methodBuilder, SQLDaoDefinition sqlDaoDefinition, String paramName, TypeName paramTypeName) {
		String methodName = sqlDaoDefinition.generateJava2ContentSerializer(paramTypeName);
		
		methodBuilder.addCode("$L($L)", methodName, paramName);
	}
	
	@Override
	public void generateReadParam(Builder methodBuilder, SQLDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName) {
		String methodName = daoDefinition.generateJava2ContentParser(paramTypeName);
		
		methodBuilder.addCode("$L($L.getBlob($L))", methodName, cursorName, indexName);
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "$T.parse$L($L.getBlob($L))"), TypeUtility.mergeTypeName(beanClass, "Table"), formatter.convert(property.getName()), cursorName, indexName);
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "null"));
	}

	@Override
	public SQLColumnType getColumnType() {
		return SQLColumnType.BLOB;
	}
}
