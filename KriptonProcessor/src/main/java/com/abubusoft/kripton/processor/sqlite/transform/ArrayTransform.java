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
package com.abubusoft.kripton.processor.sqlite.transform;

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
public class ArrayTransform extends AbstractCompileTimeTransform {

	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	private TypeName clazz;

	private boolean primitive;

	public ArrayTransform(TypeName clazz) {
		this.clazz = clazz;
	}

	public ArrayTransform(TypeName componentTypeName, boolean primitive) {
		this.primitive = primitive;
		// if (primitive) {
		// this.clazz = componentTypeName;
		// } else {
		this.clazz = componentTypeName;
		// }
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property) {
		methodBuilder.addCode("$T.asByteArray($T.asList($L." + getter(beanClass, property) + ", $T.class))",
				ProcessorHelper.class, CollectionUtility.class, beanName, ArrayList.class);
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String objectName) {
		methodBuilder.addCode("$T.asByteArray($T.asList($L, $T.class))", ProcessorHelper.class, CollectionUtility.class,
				objectName, ArrayList.class);
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,
			String cursorName, String indexName) {
		if (primitive) {
			methodBuilder.addCode(
					"$L." + setter(beanClass, property, "$T.as$LTypeArray($T.asList($L.TYPE, $L.getBlob($L)))"),
					beanName, CollectionUtility.class, primitiveType(), ProcessorHelper.class, primitiveType(),
					cursorName, indexName);
		} else if (TypeUtility.isString(clazz)) {
			methodBuilder.addCode(
					"$L." + setter(beanClass, property, "$T.asStringArray($T.asList(String.class, $L.getBlob($L)))"),
					beanName, CollectionUtility.class, ProcessorHelper.class, cursorName, indexName);
		} else if (TypeUtility.isTypeWrappedPrimitive(clazz)) {
			String name = nc.convert(clazz.toString().substring(clazz.toString().lastIndexOf(".") + 1));
			methodBuilder.addCode(
					"$L." + setter(beanClass, property, "$T.as$LArray($T.asList($L.class, $L.getBlob($L)))"), beanName,
					CollectionUtility.class, name, ProcessorHelper.class, name, cursorName, indexName);
		} else {
			String name = nc.convert(clazz.toString().substring(clazz.toString().lastIndexOf(".") + 1));
			methodBuilder.addCode(
					"$L." + setter(beanClass, property, "$T.asArray($T.asList($L.class, $L.getBlob($L)))"), beanName,
					CollectionUtility.class, ProcessorHelper.class, name, cursorName, indexName);
		}
	}

	/**
	 * Convert to primitive type name.
	 * 
	 * @return primitive type name
	 */
	public String primitiveType() {
		String value = nc.convert(clazz.toString());

		if ("Char".equals(value))
			value = "Character";
		if ("Int".equals(value))
			value = "Integer";

		return value;
	}

	/*
	 * @Override public void generateRead(Builder methodBuilder, String
	 * cursorName, String indexName) { if (primitive) {
	 * methodBuilder.addCode("$T.asList($T.TYPE, $L.getBlob($L))",
	 * CollectionUtility.class, ProcessorHelper.class, clazz, cursorName,
	 * indexName); } else
	 * methodBuilder.addCode("$T.asList($T.class, $L.getBlob($L))",
	 * CollectionUtility.class, ProcessorHelper.class, clazz, cursorName,
	 * indexName); }
	 */

	/*
	 * @Override public void generateDefaultValue(Builder methodBuilder) {
	 * methodBuilder.addCode("null"); }
	 */

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName,
			ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("$L." + setter(beanClass, property, "null"), beanName);
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "BLOB";
	}

}
