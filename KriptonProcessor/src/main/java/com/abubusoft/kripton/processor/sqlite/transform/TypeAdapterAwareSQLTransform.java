/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;

import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class TypeAdapterAwareSQLTransform extends AbstractSQLTransform {

	@Override
	public void generateWriteProperty2ContentValues(Builder methodBuilder, String beanName, TypeName beanClass, ModelProperty property) {
		if (property!=null && property.hasTypeAdapter()) {			
			methodBuilder.addCode(PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER,SQLTypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
		} else {
			methodBuilder.addCode("$L", getter(beanName, beanClass, property));
		}
	}
	
	@Override
	public void generateWriteProperty2WhereCondition(Builder methodBuilder, String beanName, TypeName beanClass, ModelProperty property) {
		if (property!=null && property.hasTypeAdapter()) {			
			methodBuilder.addCode(PRE_TYPE_ADAPTER_TO_STRING + "$L" + POST_TYPE_ADAPTER,SQLTypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
		} else {
			methodBuilder.addCode("$L", getter(beanName, beanClass, property));
		}
	}
	
	@Override
	public void generateWriteParam2WhereCondition(Builder methodBuilder, SQLiteModelMethod method, String paramName, TypeName paramTypeName) {
		if (method.hasAdapterForParam(paramName)) {			
			methodBuilder.addCode(PRE_TYPE_ADAPTER_TO_STRING + "$L" + POST_TYPE_ADAPTER,SQLTypeAdapterUtils.class, method.getAdapterForParam(paramName), paramName);
		} else {
			methodBuilder.addCode("$L", paramName);
		}			
	}
	
	@Override
	public void generateWriteParam2ContentValues(Builder methodBuilder,  SQLiteModelMethod method, String paramName, TypeName paramTypeName, ModelProperty property) {
		if (method.hasAdapterForParam(paramName)) {					
			methodBuilder.addCode(PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER,SQLTypeAdapterUtils.class, method.getAdapterForParam(paramName) , paramName);		
		} else {
			methodBuilder.addCode("$L", paramName);
		}
	}

}
