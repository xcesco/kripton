/**
 * 
 */
package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.utils.StringUtility;
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
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean readAll) {
		if (readAll) {
			methodBuilder.beginControlFlow("");
		}
		
		methodBuilder.addStatement("String temp=$L.getString($S, null)", preferenceName, property.getName());
		
		if (readAll) {					
			methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicOrPackageField()?"(":"=")+"", beanName);
		} else {
			methodBuilder.addCode("return ");
		}
				
		methodBuilder.addCode("($L.hasText(temp)) ? ", StringUtility.class);
		methodBuilder.addCode("$T.read(temp)",  utilClazz);
		methodBuilder.addCode(": null");
		
		if (readAll) {
			methodBuilder.addCode((!property.isPublicOrPackageField()?")":""));
		}
		
		methodBuilder.addCode(";");
		
		if (readAll) {
			methodBuilder.endControlFlow();
		}
	}


	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanClass!=null)
		{			
			if (nullable)
			{
				methodBuilder.addCode("if ($L!=null) ", getter(beanName, beanClass, property));
			}
			methodBuilder.addCode("$L.putString($S,$T.write($L))", editorName, property.getName(), utilClazz, getter(beanName, beanClass, property));
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