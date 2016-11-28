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

import java.util.ArrayList;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between an array field to base64 encoded string and viceversa
 * 
 * @author xcesco
 *
 */
public class ArrayTransform extends AbstractSPTransform {

	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	protected Class<?> helperClazz;
	
	private TypeName clazz;

	private boolean primitive;

	public ArrayTransform(TypeName clazz, boolean primitive) {
		this.helperClazz = ProcessorHelper.class;

		this.clazz = clazz;
		this.primitive = primitive;
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean add) {
		String name = nc.convert(clazz.toString().substring(clazz.toString().lastIndexOf(".") + 1));
		String primitiveName=primitiveType(name);
		
		methodBuilder.beginControlFlow("");
		
		// temp variable
		String tempPreferenceName="temp"+nc.convert(property.getName());
		
		methodBuilder.addCode("// read $L\n",property.getName());
		methodBuilder.addStatement("String $L=$L.getString($S, null)", tempPreferenceName, preferenceName, property.getName());
		methodBuilder.addStatement("$T<$T> collection=$T.asCollection(new $T<$T>(), $T.class, $L)",ArrayList.class, clazz.box(), helperClazz, ArrayList.class, clazz.box(), clazz.box(), tempPreferenceName);

		if (add) {
			methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicOrPackageField() ? "(" : "=") + "", beanName);
		} else {
			methodBuilder.addCode("return ");
		}

		methodBuilder.addCode("($T.hasText($L)) ? ", StringUtility.class, tempPreferenceName);
		
		if (TypeUtility.isTypePrimitive(clazz) || TypeUtility.isTypeWrappedPrimitive(clazz)){
			methodBuilder.addCode("$T.as$L$LArray(collection)", CollectionUtility.class, primitiveName, (primitive ? "Type" :""));
		} else {			
			methodBuilder.addCode("$T.asArray(collection, new $L[collection.size()])", CollectionUtility.class, clazz);
		}
		methodBuilder.addCode(": null");

		if (add) {
			methodBuilder.addCode((!property.isPublicOrPackageField() ? ")" : ""));
		} 
		
		methodBuilder.addCode(";\n");		
		methodBuilder.endControlFlow();
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanClass != null) {
			methodBuilder.addCode("if ($L!=null) ", getter(beanName, beanClass, property));

			methodBuilder.addCode("$L.putString($S,$T.asString($T.asList($L, $T.class)))", editorName, property.getName(), helperClazz, CollectionUtility.class,getter(beanName, beanClass, property), ArrayList.class);

			methodBuilder.addCode(";");
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
		} else {
			methodBuilder.addCode("if ($L!=null) ", beanName);

			methodBuilder.addCode("$L.putString($S,$T.asString($T.asList($L, $T.class)))", editorName, property.getName(), helperClazz, CollectionUtility.class, beanName, ArrayList.class);

			methodBuilder.addCode(";");
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
		}

	}

	/**
	 * Convert to primitive type name. 
	 * @return
	 * 		primitive type name
	 */
	public String primitiveType(String name) {
		String value=nc.convert(name);
		
		if ("Char".equals(value)) value="Character";
		if ("Int".equals(value)) value="Integer";
		
		return value;
	}
}
