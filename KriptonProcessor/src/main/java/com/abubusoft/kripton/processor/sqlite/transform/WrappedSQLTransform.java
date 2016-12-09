/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 * @param <U>
 *
 */
public class WrappedSQLTransform<U> extends AbstractSQLTransform {

	private Class<U> utilClazz;

	public WrappedSQLTransform(Class<U> utilClazz) {
		this.utilClazz = utilClazz;
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,
			String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "$T.read($L.getString($L))"), utilClazz,
				cursorName, indexName);

	}

	/*
	 * @Override public void generateRead(Builder methodBuilder, String
	 * cursorName, String indexName) {
	 * methodBuilder.addCode("$T.read($L.getString($L))", utilClazz, cursorName,
	 * indexName); }
	 */

	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property) {
		methodBuilder.addCode("$T.write($L)", utilClazz, getter(beanName, beanClass, property));
	}

	@Override
	public void generateWriteQueryParameter(Builder methodBuilder, String objectName, String serializerName) {
		methodBuilder.addCode("$T.write($L)", utilClazz, objectName);
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "null"));
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "TEXT";
	}

	/*
	 * @Override public void generateDefaultValue(Builder methodBuilder) {
	 * methodBuilder.addCode("null"); }
	 */

}
