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

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java Byte object
 * 
 * @author bulldog
 *
 */
abstract class PrimitiveSPTransform extends AbstractSPTransform {
	
	public PrimitiveSPTransform(boolean nullable)
	{
		this.nullable=nullable;
	}
	
	protected boolean nullable;
	
	protected String SIMPLE_TYPE;
	protected String PREFS_CONVERT;
	protected String PREFS_TYPE;
	protected String PREFS_DEFAULT_VALUE;


	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean readAll) {
		if (readAll) {						
			methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicOrPackageField()?"(":"=")+"", beanName);
		} else {
			methodBuilder.addCode("return ");
		}

		if (nullable){
			methodBuilder.addCode(SIMPLE_TYPE+"$L.get"+PREFS_TYPE+"($S, "+SIMPLE_TYPE+"($L==null?"+PREFS_DEFAULT_VALUE+":$L))", preferenceName, property.getName(), getter(beanName, beanClass, property), getter(beanName, beanClass, property));
		} else {
			methodBuilder.addCode(SIMPLE_TYPE+"$L.get"+PREFS_TYPE+"($S, "+SIMPLE_TYPE+"$L)", preferenceName, property.getName(), getter(beanName, beanClass, property));
		}

		if (readAll) {
			methodBuilder.addCode((!property.isPublicOrPackageField()?")":""));
		} 
		
		methodBuilder.addCode(";");
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanClass!=null)
		{
			if (nullable)
			{
				methodBuilder.addCode("if ($L!=null) ", getter(beanName, beanClass, property));
			}
			methodBuilder.addCode("$L.put"+PREFS_TYPE+"($S,"+PREFS_CONVERT+"$L)", editorName, property.getName(), getter(beanName, beanClass, property));
		} else {
			if (nullable)
			{
				methodBuilder.addCode("if ($L!=null) ", beanName);
			}
			methodBuilder.addCode("$L.put"+PREFS_TYPE+"($S,"+PREFS_CONVERT+"$L)", editorName, property.getName(), beanName);
		}
			
	}

}
