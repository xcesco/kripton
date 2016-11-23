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

		if (add) {
			methodBuilder.addCode("$L." + setter(beanClass, property) + (property.isFieldWithSetter() ? "(" : "=") + "", beanName);
		}

		methodBuilder.addCode("($L.getString($S, null)!=null) ? ", preferenceName, property.getName());
		
		if (primitive)
		{		
			methodBuilder.addCode("$T.as$LTypeArray($T.asCollection(new $T<$L>(), $L.class, $L.getString($S, null)))", CollectionUtility.class, primitiveName, helperClazz, ArrayList.class, primitiveName, primitiveName, preferenceName, property.getName());
		} else if (TypeUtility.isTypeWrappedPrimitive(clazz)){
			methodBuilder.addCode("$T.as$LArray($T.asCollection(new $T<$L>(), $L.class, $L.getString($S, null)))", CollectionUtility.class, primitiveName, helperClazz, ArrayList.class, name, name, preferenceName, property.getName());
		} else {
			methodBuilder.addCode("$T.asArray($T.asCollection(new $T<$L>(), $L.class, $L.getString($S, null)))", CollectionUtility.class, helperClazz, ArrayList.class, name, name, preferenceName, property.getName());
		}
		methodBuilder.addCode(": null");

		if (add) {
			methodBuilder.addCode((property.isFieldWithSetter() ? ")" : ""));
		}
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
