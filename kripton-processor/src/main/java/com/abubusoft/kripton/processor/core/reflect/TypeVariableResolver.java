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
package com.abubusoft.kripton.processor.core.reflect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.annotation.BindTypeVariables;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BindTypeSubProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

// TODO: Auto-generated Javadoc
/**
 * <p>Resolver for type variables used in class or interface hierarchy.</p> 
 * 
 * <p>Usually it is used in <code>BindType</code> bean and <code>Dao</code> definitions.</p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class TypeVariableResolver {

	/** The declared type argument list. */
	List<TypeName> declaredTypeArgumentList;
	
	/** The type variable map. */
	Map<String, TypeName> typeVariableMap;
	
	/** The element. */
	TypeElement element;
	
	/** The active. */
	boolean active;
	
	
	/**
	 * Instantiates a new type variable resolver.
	 *
	 * @param element the element
	 * @param typeArgs the type args
	 * @param typeVariableMap the type variable map
	 */
	public TypeVariableResolver(TypeElement element, List<TypeName> typeArgs, Map<String, TypeName> typeVariableMap) {
		this.element=element;
		this.declaredTypeArgumentList=typeArgs;
		this.typeVariableMap=typeVariableMap;
		this.active=(typeArgs != null && typeArgs.size() > 0) || (typeVariableMap.size()>0);
	}
	
	/**
	 * Checks for type variables.
	 *
	 * @return true, if successful
	 */
	public boolean hasTypeVariables() {
		return active;
	}
	
	/**
	 * Builds the.
	 *
	 * @param element the element
	 * @return the type variable resolver
	 */
	public static TypeVariableResolver build(TypeElement element)
	{
		List<TypeName> typeArgs = TypeUtility.getTypeArguments(element);
		Map<String, TypeName> typeVariableMap=new HashMap<>();
		
		BindTypeVariables annotationBeanType = element.getAnnotation(BindTypeVariables.class);
		List<String> typeVariables = new ArrayList<String>();
		if (annotationBeanType != null) {
			typeVariables = Arrays.asList(annotationBeanType.value());
		}
		
		List<String> typeParameters = AnnotationUtility.extractAsClassNameArray(BindTypeSubProcessor.elementUtils, element, BindTypeVariables.class, AnnotationAttributeType.TYPE_PARAMETERS);

		AssertKripton.assertTrue(typeVariables.size() >= typeParameters.size(), "%s '%s' has incorrect definition of type variables/parameters on annotation @BindTypeVariables",
				element.getKind()==ElementKind.CLASS ? "Class" :"Interface", element.asType());

		if (typeVariables.size() > 0) {
			String[] temp;

			AssertKripton.assertTrue((typeParameters.size() == 0) || (typeVariables.size() == typeParameters.size()),
					"%s '%s' has an incorrect definition of type variables/parameters on annotation @BindTypeVariables(typeVariables, typeParameters)", element.getKind()==ElementKind.CLASS ? "Class" :"Interface", element.asType());

			if (StringUtils.hasText(typeVariables.get(0))) {
				int i = 0;
				for (String key : typeVariables) {
					if (StringUtils.hasText(key)) {
						temp = key.split(",");
						for (String alias : temp) {
							if (typeParameters.size() > 0) {
								typeVariableMap.put(alias.trim(), TypeUtility.typeName(typeParameters.get(i)));
							} else {
								typeVariableMap.put(alias.trim(), typeArgs.get(i));
							}
						}
					}
					i++;
				}

			} else {
				AssertKripton.assertTrue(typeVariableMap == null && typeArgs.size() < 2, "%s '%s' use more than one type variables in its class hierarchy. Try to use @BindTypeVariables",
						element.getKind()==ElementKind.CLASS ? "Class" :"Interface", element.asType());
			}
		}
		
		
		return new TypeVariableResolver(element, typeArgs, typeVariableMap);
	}
	
	/**
	 * Resolve.
	 *
	 * @param inputType the input type
	 * @return the type name
	 */
	public TypeName resolve(TypeName inputType) {
		if (!hasTypeVariables())
			return inputType;
		
		if (inputType.isPrimitive() || inputType.isBoxedPrimitive() || inputType.equals(TypeName.get(String.class))) {
			return inputType;
		}
		
		if (TypeName.VOID.equals(inputType)) return inputType;
		
		TypeName resolved=null;
		if (inputType instanceof ParameterizedTypeName)
		{
			ParameterizedTypeName inputParametrizedTypeName=(ParameterizedTypeName)inputType;
			
			TypeName[] typeArguments=new TypeName[inputParametrizedTypeName.typeArguments.size()];
			int i=0;
			for (TypeName item: inputParametrizedTypeName.typeArguments)
			{
				typeArguments[i]=resolve(item);
				i++;
			}
			
			resolved=ParameterizedTypeName.get((ClassName)resolve(inputParametrizedTypeName.rawType), typeArguments);
			
			return resolved;
		} else if (inputType instanceof ArrayTypeName)
		{
			ArrayTypeName inputTypeName=(ArrayTypeName)inputType;
			
			resolved=ArrayTypeName.of(resolve(inputTypeName.componentType));
			
			return resolved;
		} else {
			// it's not a type variable
			if (inputType.toString().contains(".")) {
				return inputType;
			}
			// ASSERT: simple typeName 
			if (typeVariableMap.containsKey(inputType.toString())) {
				TypeName type = typeVariableMap.get(inputType.toString());
				return type;
			} else if (declaredTypeArgumentList.size()==1){
				resolved = declaredTypeArgumentList.get(0);
				// if we found an unique type variable, we use it.
				//typeVariableMap = new HashMap<>();
				//typeVariableMap.put(inputTypeName.toString(), resolved);

				return resolved;
			}
			
			AssertKripton.assertTrue(false, "In type hierarchy of %s '%s' there is a unresolved type variable named '%s'. Define it with @BindTypeVariables", element.getKind()==ElementKind.CLASS ? "class" :"interface", element.getQualifiedName(),
					inputType.toString());
			
			return resolved;
		}
		
	}
	
}
