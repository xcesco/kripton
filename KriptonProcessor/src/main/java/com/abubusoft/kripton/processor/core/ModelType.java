/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package com.abubusoft.kripton.processor.core;

import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class ModelType {

	protected TypeName typeName;

	public void setTypeName(TypeName typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the value
	 */
	public TypeName getTypeName() {
		return typeName;
	}

	public ModelType(TypeMirror type) {
		// super(type.toString());
		this.typeName = TypeUtility.typeName(type);
	}

	public ModelType(String containerClassName, String argClassName) {
		this.typeName = ParameterizedTypeName.get(TypeUtility.className(containerClassName), TypeUtility.typeName(argClassName));
		// super(LiteralType.of(containerClassName, argClassName).getValue());
		// this.name= TypeUtility.typeName(LiteralType.of(containerClassName,
		// argClassName).getValue());
	}

	public ModelType(TypeName typeName) {
		// super(typeName.toString());
		this.typeName = typeName;
	}

	public boolean isEquals(String value) {
		return TypeUtility.isEquals(this.typeName, value);
	}

	public boolean isArray() {
		return TypeUtility.isArray(typeName);
	}

	public boolean isPrimitive() {
		return TypeUtility.isTypePrimitive(typeName);
	}

	/**
	 * <p>
	 * if element is a ParameterizedTypeName or array, return the first type
	 * parameter or the component typeName. Otherwise null.
	 * </p>
	 * 
	 * @return if element is a ParameterizedTypeName or array, return the first
	 *         type parameter or the component typeName. Otherwise null.
	 */
	public TypeName getTypeParameter() {
		if (typeName instanceof ParameterizedTypeName) {
			ParameterizedTypeName temp = (ParameterizedTypeName) typeName;
			return temp.typeArguments.get(0);
		} else if (typeName instanceof ArrayTypeName) {
			ArrayTypeName temp = (ArrayTypeName) typeName;
			return temp.componentType;
		}

		return null;
	}

	public boolean isList() {
		return TypeUtility.isList(typeName);
	}

	public boolean isCollection() {
		return TypeUtility.isCollection(typeName);
	}

	public boolean isMap() {
		return TypeUtility.isMap(typeName);
	}

}
