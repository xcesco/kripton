/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 * @param <U>
 *
 */
public class WrappedSQLTransform<U> extends AbstractSQLTransform {

	private Class<U> utilClazz;

	public WrappedSQLTransform(Class<U> utilClazz) {
		this.utilClazz = utilClazz;
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "$T.read($L.getString($L))"), utilClazz, cursorName, indexName);
	}

	@Override
	public void generateReadParam(Builder methodBuilder, SQLDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName) {
		methodBuilder.addCode("$T.read($L.getString($L))", utilClazz, cursorName, indexName);
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		methodBuilder.addCode("$T.write($L)", utilClazz, getter(beanName, beanClass, property));
	}

	@Override
	public void generateWriteParam(Builder methodBuilder, SQLDaoDefinition sqlDaoDefinition, String paramName, TypeName paramTypeName) {
		methodBuilder.addCode("$T.write($L)", utilClazz, paramName);
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "null"));
	}

	@Override
	public SQLColumnType getColumnType() {
		return SQLColumnType.TEXT;
	}

}
