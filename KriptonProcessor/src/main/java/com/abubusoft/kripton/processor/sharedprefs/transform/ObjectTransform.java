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

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java String object
 * 
 * @author bulldog
 *
 */
public class ObjectTransform extends AbstractSPTransform {

	protected Class<?> utilClazz;

	public ObjectTransform() {
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean add) {
		if (add) {

			methodBuilder.addCode("$L." + setter(beanClass, property) + (property.isFieldWithSetter() ? "(" : "=") + "", beanName);
		} else {
			methodBuilder.addCode("return ");
		}

		methodBuilder.addCode("($L.getString($S, null)!=null) ? ", preferenceName, property.getName());
		methodBuilder.addCode("($T)readObj($L.getString($S, null), $T.class)", property.getPropertyType().getName(), preferenceName, property.getName(), property.getPropertyType().getName());
		methodBuilder.addCode(": null");

		if (add) {
			methodBuilder.addCode((property.isFieldWithSetter() ? ")" : ""));
		}
		
		methodBuilder.addCode(";");
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanClass != null) {
			methodBuilder.addCode("if ($L!=null) ", getter(beanName, beanClass, property));

			methodBuilder.addCode("$L.putString($S,writeObj($L))", editorName, property.getName(), getter(beanName, beanClass, property));

			methodBuilder.addCode(";");
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
		} else {
			methodBuilder.addCode("if ($L!=null) ", beanName);

			methodBuilder.addCode("$L.putString($S,writeObj($L))", editorName, property.getName(), beanName);

			methodBuilder.addCode(";");
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
		}

	}
}
