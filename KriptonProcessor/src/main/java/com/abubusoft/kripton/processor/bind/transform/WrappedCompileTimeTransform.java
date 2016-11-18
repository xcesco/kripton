/**
 * 
 */
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 *
 */
public class WrappedCompileTimeTransform extends AbstractSPTransform {
	
	protected Class<?> utilClazz;

	public WrappedCompileTimeTransform(Class<?> utilClazz)
	{
		this.nullable=true;
		this.utilClazz=utilClazz;
	}
	
	protected boolean nullable;
	
	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean add) {
		if (add) {
						
			methodBuilder.addCode("$L." + setter(beanClass, property) + (property.isFieldWithSetter()?"(":"=")+"", beanName);
		}
		
		methodBuilder.addCode("($L.getString($S, null)!=null) ? ", preferenceName, property.getName());
		methodBuilder.addCode("$T.read($L.getString($S, null))",  utilClazz, preferenceName, property.getName());
		methodBuilder.addCode(": null");
		
		if (add) {
			methodBuilder.addCode((property.isFieldWithSetter()?")":""));
		}
	}


	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanClass!=null)
		{			
			if (nullable)
			{
				methodBuilder.addCode("if ($L." + getter(beanClass, property)+"!=null) ", beanName);
			}
			methodBuilder.addCode("$L.putString($S,$T.write($L." + getter(beanClass, property) + "))", editorName, property.getName(), utilClazz, beanName);
			if (nullable)
			{
				methodBuilder.addCode(";");
				methodBuilder.addCode(" else ");
				methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
			}
		} else {
			if (nullable)
			{
				methodBuilder.addCode("if ($L!=null) ", beanName);
			}
			methodBuilder.addCode("$L.putString($S,$T.write($L))", editorName, property.getName(), utilClazz, beanName);
			if (nullable)
			{
				methodBuilder.addCode(";");
				methodBuilder.addCode(" else ");
				methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
			}
		}
			
	}

}