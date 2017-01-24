/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class WrappedPrefsTransform extends AbstractPrefsTransform {

	protected Class<?> utilClazz;

	public WrappedPrefsTransform(Class<?> utilClazz) {
		this.nullable = true;
		this.utilClazz = utilClazz;
	}

	protected boolean nullable;

	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean readAll) {
		if (readAll) {
			methodBuilder.beginControlFlow("");
		}

		methodBuilder.addStatement("String temp=$L.getString($S, null)", preferenceName, property.getName());

		if (readAll) {
			methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicField() ? "(" : "=") + "", beanName);
		} else {
			methodBuilder.addCode("return ");
		}

		methodBuilder.addCode("($T.hasText(temp)) ? ", StringUtils.class);
		methodBuilder.addCode("$T.read(temp)", utilClazz);
		methodBuilder.addCode(": null");

		if (readAll) {
			methodBuilder.addCode((!property.isPublicField() ? ")" : ""));
		}

		methodBuilder.addCode(";");

		if (readAll) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		if (nullable) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		methodBuilder.addStatement("$L.putString($S,$T.write($L))", editorName, property.getName(), utilClazz, getter(beanName, beanClass, property));
		if (nullable) {
			methodBuilder.nextControlFlow("else");
			methodBuilder.addStatement("$L.remove($S)", editorName, property.getName());
			methodBuilder.endControlFlow();
		}

	}

}