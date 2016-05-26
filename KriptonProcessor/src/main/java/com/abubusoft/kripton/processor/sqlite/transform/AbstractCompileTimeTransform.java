/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * @author xcesco
 *
 */
public abstract class AbstractCompileTimeTransform implements Transform {
	
	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {		
		methodBuilder.addCode("$L."+getter(beanClass, property), beanName);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.Transform#generateWriteProperty(com.squareup.javapoet.MethodSpec.Builder, java.lang.String)
	 */
	@Override
	public void generateWriteProperty(Builder methodBuilder, String objectName) {
		methodBuilder.addCode("$L", objectName);		
	}
	

}
