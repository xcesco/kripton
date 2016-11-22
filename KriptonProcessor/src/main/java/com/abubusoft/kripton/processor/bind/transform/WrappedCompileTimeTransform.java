/**
 * 
 */
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;

import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 *
 */
public class WrappedCompileTimeTransform extends AbstractBindTransform {
	
	protected Class<?> utilClazz;

	public WrappedCompileTimeTransform(Class<?> utilClazz)
	{
		this.nullable=true;
		this.utilClazz=utilClazz;
	}
	
	protected boolean nullable;
	
	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		/*
		if (add) {
						
			methodBuilder.addCode("$L." + setter(beanClass, property) + (property.isFieldWithSetter()?"(":"=")+"", beanName);
		}
		
		methodBuilder.addCode("($L.getString($S, null)!=null) ? ", preferenceName, property.getName());
		methodBuilder.addCode("$T.read($L.getString($S, null))",  utilClazz, preferenceName, property.getName());
		methodBuilder.addCode(": null");
		
		if (add) {
			methodBuilder.addCode((property.isFieldWithSetter()?")":""));
		}*/
	}


	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String editorName, TypeName beanClass, String beanName, BindProperty property) {
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


	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}

}