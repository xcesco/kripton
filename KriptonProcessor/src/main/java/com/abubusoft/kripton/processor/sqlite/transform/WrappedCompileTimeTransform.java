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
public class WrappedCompileTimeTransform<U> extends AbstractCompileTimeTransform {

	private Class<U> utilClazz;

	public WrappedCompileTimeTransform(Class<U> utilClazz)
	{
		this.utilClazz=utilClazz;
	}
	
	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("$L." + setter(beanClass, property, "$T.read($L.getString($L))"), beanName, utilClazz, cursorName, indexName);

	}
	
	/*@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$T.read($L.getString($L))", utilClazz, cursorName, indexName);
	}*/
	
	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanName!=null)
		{
			methodBuilder.addCode("$T.write($L."+getter(beanClass, property)+")", utilClazz, beanName);
		} else {
			generateWriteProperty(methodBuilder, property.getName());
		}
	}
	
	@Override
	public void generateWriteProperty(Builder methodBuilder, String objectName) {
		methodBuilder.addCode("$T.write($L)", utilClazz, objectName);		
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		methodBuilder.addCode("$L." + setter(beanClass, property, "null"), beanName);
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "TEXT";
	}

	/*@Override
	public void generateDefaultValue(Builder methodBuilder) {
		methodBuilder.addCode("null");
	}*/

}
