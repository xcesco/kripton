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
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;


/**
 * The Class ModelType.
 */
public class ModelType {

	/** The type name. */
	protected TypeName typeName;

	/**
	 * Sets the type name.
	 *
	 * @param typeName the new type name
	 */
	public void setTypeName(TypeName typeName) {
		this.typeName = typeName;
	}

	/**
	 * Gets the type name.
	 *
	 * @return the value
	 */
	public TypeName getTypeName() {
		return typeName;
	}

	/**
	 * Instantiates a new model type.
	 * @param entity 
	 *
	 * @param type the type
	 */
	public ModelType(TypeMirror type) {
		// super(type.toString());		
		
		this.typeName = TypeUtility.typeName(type);
	}

	/**
	 * Instantiates a new model type.
	 *
	 * @param containerClassName the container class name
	 * @param argClassName the arg class name
	 */
	public ModelType(String containerClassName, String argClassName) {
		this.typeName = ParameterizedTypeName.get(TypeUtility.className(containerClassName), TypeUtility.typeName(argClassName));
		// super(LiteralType.of(containerClassName, argClassName).getValue());
		// this.name= TypeUtility.typeName(LiteralType.of(containerClassName,
		// argClassName).getValue());
	}

	/**
	 * Instantiates a new model type.
	 *
	 * @param typeName the type name
	 */
	public ModelType(TypeName typeName) {
		// super(typeName.toString());
		this.typeName = typeName;
	}

	/**
	 * Checks if is equals.
	 *
	 * @param value the value
	 * @return true, if is equals
	 */
	public boolean isEquals(String value) {
		return TypeUtility.isEquals(this.typeName, value);
	}

	/**
	 * Checks if is array.
	 *
	 * @return true, if is array
	 */
	public boolean isArray() {
		return TypeUtility.isArray(typeName);
	}

	/**
	 * Checks if is primitive.
	 *
	 * @return true, if is primitive
	 */
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

	/**
	 * Checks if is list.
	 *
	 * @return true, if is list
	 */
	public boolean isList() {
		return TypeUtility.isList(typeName);
	}

	/**
	 * Checks if is collection.
	 *
	 * @return true, if is collection
	 */
	public boolean isCollection() {
		return TypeUtility.isCollection(typeName);
	}

	/**
	 * Checks if is map.
	 *
	 * @return true, if is map
	 */
	public boolean isMap() {
		return TypeUtility.isMap(typeName);
	}

}
