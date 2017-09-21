/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

public abstract class WrappedSQLTransformation extends TypeAdapterAwareSQLTransform {
	
	protected String READ_FROM_CURSOR=null;
	
	protected boolean nullable;
	
	protected String defaultValue=null;

	protected WrappedSQLTransformation(boolean nullable) {
		this.nullable=nullable;
	}
	
	@Override
	public void generateReadPropertyFromCursor(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {		
		if (property.hasTypeAdapter()) {			
			methodBuilder.addCode(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+READ_FROM_CURSOR+POST_TYPE_ADAPTER),SQLTypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), cursorName, indexName);
		} else {			
			methodBuilder.addCode(setter(beanClass, beanName, property, READ_FROM_CURSOR), cursorName, indexName);
		}
	}

	@Override
	public void generateReadValueFromCursor(Builder methodBuilder, SQLDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName) {
		methodBuilder.addCode(READ_FROM_CURSOR, cursorName, indexName);
	}
		
	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property, String cursorName, String indexName) {
		if (property.hasTypeAdapter()) {			
			methodBuilder.addCode(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+defaultValue+POST_TYPE_ADAPTER),SQLTypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz));
		} else {			
			methodBuilder.addCode(setter(beanClass, beanName, property, defaultValue));
		}
	}
	
	@Override
	public boolean isTypeAdapterAware() {		
		return nullable;
	}

}
