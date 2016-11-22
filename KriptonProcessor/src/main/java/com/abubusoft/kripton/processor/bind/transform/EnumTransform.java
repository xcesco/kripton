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
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java5 Enum object
 * 
 * @author bulldog
 * 
 */
public class EnumTransform extends AbstractBindTransform {

	private TypeName typeName;

	public EnumTransform(TypeName typeName) {
		this.typeName = typeName;
		defaultValue = "null";
	}

	protected String defaultValue;

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		/*
		if (add) {

			methodBuilder.addCode("$L." + setter(beanClass, property) + (property.isFieldWithSetter() ? "(" : "=") + "", beanName);
		}

		methodBuilder.addCode("($L.getString($S, null)!=null) ? ", preferenceName, property.getName());
		methodBuilder.addCode("$T.valueOf($L.getString($S, $L))", typeName, preferenceName, property.getName(), defaultValue);
		methodBuilder.addCode(": null");

		if (add) {
			methodBuilder.addCode((property.isFieldWithSetter() ? ")" : ""));
		}*/
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String editorName, TypeName beanClass, String beanName, BindProperty property) {
		if (beanClass != null) {
			methodBuilder.addCode("if ($L." + getter(beanClass, property) + "!=null) ", beanName);
			methodBuilder.addCode("$L.putString($S,$L." + getter(beanClass, property) + ".toString() )", editorName, property.getName(), beanName);
			methodBuilder.addCode(";");
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, $L)", editorName, property.getName(), defaultValue);
		} else {
			methodBuilder.addCode("if ($L!=null) ", beanName);
			methodBuilder.addCode("$L.putString($S,$L.toString())", editorName, property.getName(), beanName);
			methodBuilder.addCode(";");
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, $L)", editorName, property.getName(), defaultValue);
		}

	}

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method
	}
	
	@Override
	public void generateParseOnJackson(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	} 
		
}