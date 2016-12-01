/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.math.BigInteger;

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a java.math.BigInteger object
 * 
 * @author bulldog
 *
 */
abstract class AbstractNumberTransform extends AbstractSPTransform {
	
	protected String METHOD_CONVERSION;
	protected Class<?> clazz;

	public AbstractNumberTransform()
	{
		defaultValue="0";
		clazz=BigInteger.class;
		METHOD_CONVERSION="toString";
	}
	
	protected String defaultValue;
	
	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean readAll) {
		if (readAll) {
			methodBuilder.beginControlFlow("");
		}
		
		methodBuilder.addStatement("String temp=$L.getString($S, $S)", preferenceName, property.getName(), defaultValue);
		if (readAll) {					
			methodBuilder.addCode("$L.$L" + (!property.isPublicOrPackageField()?"(":"=")+"", beanName, setter(beanClass, property));
		} else {
			methodBuilder.addCode("return ");
		}
		
		methodBuilder.addCode("($T.hasText(temp)) ? ", StringUtils.class);
		methodBuilder.addCode("new $T(temp)",  clazz);
		methodBuilder.addCode(": null");
		
		if (readAll) {
			methodBuilder.addCode((!property.isPublicOrPackageField()?")":""));
		}
		
		methodBuilder.addCode(";\n");
		
		if (readAll) {
			methodBuilder.endControlFlow();
		}
	}


	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanClass!=null)
		{
			methodBuilder.addCode("if ($L!=null) ", getter(beanName, beanClass, property));
			methodBuilder.addCode("$L.putString($S,$L.$L() );", editorName, property.getName(), getter(beanName, beanClass, property), METHOD_CONVERSION);			
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, null);", editorName, property.getName());

		} else {
			methodBuilder.addCode("if ($L!=null) ", beanName);
			methodBuilder.addCode("$L.putString($S,$L.$L());", editorName, property.getName(), beanName, METHOD_CONVERSION);			
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, null);", editorName, property.getName());
		}
			
	}

}
