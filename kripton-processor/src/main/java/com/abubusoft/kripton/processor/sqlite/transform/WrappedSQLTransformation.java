/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;


/**
 * The Class WrappedSQLTransformation.
 */
public abstract class WrappedSQLTransformation extends TypeAdapterAwareSQLTransform {
	
	/** The read from cursor. */
	protected String READ_FROM_CURSOR=null;
	
	/** The nullable. */
	protected boolean nullable;
	
	/** The default value. */
	protected String defaultValue=null;

	/**
	 * Instantiates a new wrapped SQL transformation.
	 *
	 * @param nullable the nullable
	 */
	protected WrappedSQLTransformation(boolean nullable) {
		this.nullable=nullable;
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.SQLTransform#generateReadPropertyFromCursor(com.squareup.javapoet.MethodSpec.Builder, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.core.ModelProperty, java.lang.String, java.lang.String)
	 */
	@Override
	public void generateReadPropertyFromCursor(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {		
		if (property.hasTypeAdapter()) {			
			methodBuilder.addCode(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+READ_FROM_CURSOR+POST_TYPE_ADAPTER),property.getName(), cursorName, indexName);
		} else {			
			methodBuilder.addCode(setter(beanClass, beanName, property, READ_FROM_CURSOR), cursorName, indexName);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.AbstractSQLTransform#generateReadValueFromCursor(com.squareup.javapoet.MethodSpec.Builder, com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition, com.squareup.javapoet.TypeName, java.lang.String, java.lang.String)
	 */
	@Override
	public void generateReadValueFromCursor(Builder methodBuilder, SQLiteDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName) {
		methodBuilder.addCode(READ_FROM_CURSOR, cursorName, indexName);
	}
		
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.SQLTransform#generateResetProperty(com.squareup.javapoet.MethodSpec.Builder, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.core.ModelProperty, java.lang.String, java.lang.String)
	 */
	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property, String cursorName, String indexName) {
		if (property.hasTypeAdapter()) {			
			methodBuilder.addCode(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+defaultValue+POST_TYPE_ADAPTER),property.getName());
		} else {			
			methodBuilder.addCode(setter(beanClass, beanName, property, defaultValue));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.AbstractSQLTransform#isTypeAdapterAware()
	 */
	@Override
	public boolean isTypeAdapterAware() {		
		return nullable;
	}

}
