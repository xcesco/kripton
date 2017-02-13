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
package com.abubusoft.kripton.processor.core;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BindTypeProcessor;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import edu.emory.mathcs.backport.java.util.Arrays;

@BindType
public class ModelClass<E extends ModelProperty> extends ModelBucket<E, TypeElement> implements ModelElement, ModelWithAnnotation {

	protected List<ModelAnnotation> annotations;

	Map<String, TypeName> typeVariableMap;

	public ModelClass(TypeElement element) {
		this(element.getQualifiedName().toString(), element);
	}

	@SuppressWarnings("unchecked")
	public ModelClass(String name, TypeElement beanElement) {
		super(name, beanElement);

		this.annotations = new ArrayList<ModelAnnotation>();

		typeArgs = TypeUtility.getTypeArguments(beanElement);

		BindType annotationBeanType = beanElement.getAnnotation(BindType.class);
		List<String> typeVariables = new ArrayList<String>();
		if (annotationBeanType != null) {
			typeVariables = Arrays.asList(annotationBeanType.typeVariables());
		}
		List<String> typeParameters = AnnotationUtility.extractAsClassNameArray(BindTypeProcessor.elementUtils, beanElement, BindType.class, AnnotationAttributeType.TYPE_PARAMETERS);

		AssertKripton.assertTrue(typeVariables.size() >= typeParameters.size(), "Class '%s' has incorrect definition of type variables/parameters on annotation @BintType(typeVariables, typeParameters)",
				beanElement.asType());

		if (typeVariables.size() > 0) {
			String[] temp;

			AssertKripton.assertTrue((typeParameters.size() == 0) || (typeVariables.size() == typeParameters.size()),
					"Class '%s' has an incorrect definition of type variables/parameters on annotation @BintType(typeVariables, typeParameters)", beanElement.asType());

			if (StringUtils.hasText(typeVariables.get(0))) {
				typeVariableMap = new HashMap<>();
				int i = 0;
				for (String key : typeVariables) {
					if (StringUtils.hasText(key)) {
						temp = key.split(",");
						for (String alias : temp) {
							if (typeParameters.size() > 0) {
								typeVariableMap.put(alias.trim(), TypeUtility.typeName(typeParameters.get(i)));
							} else {
								typeVariableMap.put(alias.trim(), TypeUtility.typeName(typeArgs.get(i)));
							}
						}
					}
					i++;
				}

			} else {
				AssertKripton.assertTrue(typeVariableMap == null && typeArgs.size() < 2, "Class '%s' use more than one type variables in its class hierarchy. Try to use @BintType(typeVariables)",
						beanElement.asType());
			}
		}

	}

	List<? extends TypeMirror> typeArgs;

	public List<? extends TypeMirror> getTypeArgs() {
		return typeArgs;
	}

	public boolean hasTypeArgs() {
		return (typeArgs != null && typeArgs.size() > 0) || (typeVariableMap != null);
	}

	public String getSimpleName() {
		return element.getSimpleName().toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void accept(ModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}

	public ModelAnnotation getAnnotation(Class<? extends Annotation> value) {
		String canonicalName = value.getCanonicalName();

		for (ModelAnnotation item : annotations) {
			if (item.getName().equals(canonicalName)) {
				return item;
			}
		}

		return null;
	}

	public void addAnnotation(ModelAnnotation annotation) {
		annotations.add(annotation);
	}

	public boolean containsAnnotation(Class<? extends Annotation> annotation) {
		return getAnnotation(annotation) != null;
	}

	@Override
	public boolean hasAnnotation(Class<? extends Annotation> annotationClazz) {
		return getAnnotation(annotationClazz) != null;
	}

	public TypeName resolveTypeVariable(TypeName inputTypeName) {
		if (inputTypeName.toString().contains(".") || inputTypeName.isPrimitive() || inputTypeName.isBoxedPrimitive()) {
			return inputTypeName;
		}

		if (!hasTypeArgs())
			return inputTypeName;

		if (typeVariableMap != null && typeVariableMap.containsKey(inputTypeName.toString())) {
			TypeName type = typeVariableMap.get(inputTypeName.toString());
			return type;
		} else if (typeVariableMap == null) {
			TypeName resolved = TypeUtility.typeName(typeArgs.get(0));
			// if we found a type variable not yet bound, we bound it.
			typeVariableMap = new HashMap<>();
			typeVariableMap.put(inputTypeName.toString(), resolved);

			return resolved;
		}

		AssertKripton.assertTrue(false, "In class hierarchy of '%s' there is a unresolved type variable named '%s'. Define it with @BindType(typeVariables)", this.getElement().getQualifiedName(),
				inputTypeName.toString());

		return inputTypeName;
	}

}
