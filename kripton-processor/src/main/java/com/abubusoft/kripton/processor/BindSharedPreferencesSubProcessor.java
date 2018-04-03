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
/**
 * 
 */
package com.abubusoft.kripton.processor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindPreferenceAdapter;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.android.sharedprefs.PreferenceType;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.bind.BindEntityBuilder;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.PropertyFactory;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;
import com.abubusoft.kripton.processor.sharedprefs.BindSharedPreferencesBuilder;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsEntity;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsModel;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;

/**
 * <p>Annotation processor for shared preferences.</p>
 * 
 * <p>This processor is one-step processor.</p>
 * 
 * 
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindSharedPreferencesSubProcessor extends BaseProcessor {

	private PrefsModel model;

	private AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindSharedPreferences.class).build();

	private AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(BindDisabled.class).add(BindPreference.class).add(BindPreferenceAdapter.class).build();

	
	protected Set<Class<? extends Annotation>> getSupportedAnnotationClasses() {
		Set<Class<? extends Annotation>> annotations = new LinkedHashSet<Class<? extends Annotation>>();

		annotations.add(BindSharedPreferences.class);		

		return annotations;
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		model = new PrefsModel();

		parseBindType(roundEnv);

		// Put all @BindSharedPreferences elements in beanElements
		for (Element item : roundEnv.getElementsAnnotatedWith(BindSharedPreferences.class)) {
			AssertKripton.assertTrueOrInvalidKindForAnnotationException(item.getKind() == ElementKind.CLASS, item, BindSharedPreferences.class);

			analyzeSharedPreferences((TypeElement) item);
		}

		return true;
	}

	/**
	 * @throws IOException
	 */
	public void generateClasses() throws IOException {
		for (PrefsEntity item : model.getEntities()) {
			BindSharedPreferencesBuilder.generate(elementUtils, filer, item);
		}
	}

	private String analyzeSharedPreferences(final TypeElement sharedPreference) {
		Element beanElement = sharedPreference;
		String result = beanElement.getSimpleName().toString();

		// create equivalent entity in the domain of bind processor
		final BindEntity bindEntity = BindEntityBuilder.parse(null, sharedPreference);
		final PrefsEntity currentEntity = new PrefsEntity(beanElement.getSimpleName().toString(), (TypeElement) beanElement,
				AnnotationUtility.buildAnnotationList((TypeElement) beanElement, classAnnotationFilter));

		final boolean bindAllFields = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindType.class, AnnotationAttributeType.ALL_FIELDS, Boolean.TRUE);

		PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<PrefsEntity, PrefsProperty>() {

			@Override
			public PrefsProperty createProperty(PrefsEntity entity, Element propertyElement) {
				return new PrefsProperty(currentEntity, propertyElement, AnnotationUtility.buildAnnotationList(propertyElement));
			}
		}, propertyAnnotationFilter, new PropertyCreatedListener<PrefsEntity, PrefsProperty>() {

			@Override
			public boolean onProperty(PrefsEntity entity, PrefsProperty property) {
				// if @BindDisabled is present, exit immediately
				if (property.hasAnnotation(BindDisabled.class)) {
					if (bindAllFields) {
						return false;
					} else {
						throw new InvalidDefinition("@BindDisabled can not be used with @BindType(allField=false)");
					}
				}

				if (property.getPropertyType().isArray() || property.getPropertyType().isList()) {
					property.setPreferenceType(PreferenceType.STRING);
				} else {
					if (property.isType(Boolean.TYPE, Boolean.class)) {
						property.setPreferenceType(PreferenceType.BOOL);
					} else if (property.isType(Short.TYPE, Short.class)) {
						property.setPreferenceType(PreferenceType.INT);
					} else if (property.isType(Character.TYPE, Character.class)) {
						property.setPreferenceType(PreferenceType.STRING);
					} else if (property.isType(Integer.TYPE, Integer.class)) {
						property.setPreferenceType(PreferenceType.INT);
					} else if (property.isType(Long.TYPE, Long.class)) {
						property.setPreferenceType(PreferenceType.LONG);
					} else if (property.isType(Float.TYPE, Float.class)) {
						property.setPreferenceType(PreferenceType.FLOAT);
					} else if (property.isType(Double.TYPE, Double.class)) {
						property.setPreferenceType(PreferenceType.STRING);
					} else {
						property.setPreferenceType(PreferenceType.STRING);
					}
				}

				if (!bindAllFields && !property.hasAnnotation(BindPreference.class)) {
					// skip field
					return false;
				}

				// if field disable, skip property definition
				ModelAnnotation annotation = property.getAnnotation(BindPreference.class);
				if (annotation != null && AnnotationUtility.extractAsBoolean(property, annotation, AnnotationAttributeType.ENABLED) == false) {
					return false;
				}

				if (bindEntity.contains(property.getName())) {
					BindProperty bindProperty = bindEntity.get(property.getName());
					if (bindProperty.isBindedArray() || bindProperty.isBindedCollection() || bindProperty.isBindedMap() || bindProperty.isBindedObject()) {
						property.bindProperty = bindProperty;
					}

				} else {
					throw (new KriptonRuntimeException(String.format("In class '%s' property '%s' has a wrong definition to create SharedPreference", sharedPreference.asType(), property.getName())));
				}

				return true;
			}

		});

		model.entityAdd(currentEntity);
		return result;

	}
}
