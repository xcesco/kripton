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
/**
 * 
 */
package com.abubusoft.kripton.processor;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.android.sharedprefs.PreferenceType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.bind.BindTypeBuilder;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindModel;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.PropertyFactory;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;

/**
 * Annotation processor for json/xml/etc 
 * 
 * @author xcesco
 *
 */
public class BindProcessor extends BaseProcessor {

	private BindModel model;

	private AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindSharedPreferences.class).build();
	
	private AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindPreference.class).build();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes
	 * ()
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();

		annotations.add(BindType.class.getCanonicalName());
		annotations.add(BindSharedPreferences.class.getCanonicalName());

		return annotations;
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
			count++;
			if (count > 1) {
				return true;
			}

			model = new BindModel();
			int itemCounter = 0;

			parseBindType(roundEnv);
			for (Element item : globalBeanElements.values()) {
				if (item.getKind() == ElementKind.ENUM) {
					//SPTransformer.register(typeName(item), new EnumTransform(typeName(item)));
				}

			}

			// Put all @BindSharedPreferences elements in beanElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				if (item.getKind() != ElementKind.CLASS) {
					String msg = String.format("%s %s, only class can be annotated with @%s annotation", item.getKind(), item, BindSharedPreferences.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}

				createBindMapper(item);

				itemCounter++;
			}

			if (itemCounter == 0) {
				warn("No class with %s annotation was found", BindSharedPreferences.class);
			}

			for (BindEntity item : model.getEntities()) {
				BindTypeBuilder.generate(elementUtils, filer, item);
			}

		} catch (Exception e) {
			String msg = e.getMessage();
			error(null, msg);

			if (DEVELOP_MODE) {
				logger.log(Level.SEVERE, msg);
				e.printStackTrace();
			}
		}

		return true;
	}

	private String createBindMapper(Element element) {
		Element beanElement = element;
		String result = beanElement.getSimpleName().toString();

		final BindEntity currentEntity = new BindEntity(beanElement.getSimpleName().toString(), (TypeElement) beanElement);

		AnnotationUtility.buildAnnotations(elementUtils, currentEntity, classAnnotationFilter);

		boolean temp1 = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindType.class, AnnotationAttributeType.ATTRIBUTE_ALL_FIELDS, Boolean.TRUE);
		boolean temp2 = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindSharedPreferences.class, AnnotationAttributeType.ATTRIBUTE_ALL_FIELDS, Boolean.TRUE);

		if (!temp1 && temp2) {
			String msg = String.format("In class '%s', inconsistent value of attribute 'allFields' in annotations '%s' and '%s'", currentEntity.getSimpleName(), BindType.class.getSimpleName(),
					BindSharedPreferences.class.getSimpleName());
			throw (new IncompatibleAttributesInAnnotationException(msg));
		}

		final boolean bindAllFields = temp1 && temp2;
		{
			PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<BindProperty>() {

				@Override
				public BindProperty createProperty(Element element) {
					return new BindProperty(element);
				}
			}, propertyAnnotationFilter, new PropertyCreatedListener<BindProperty>() {

				@Override
				public boolean onProperty(BindProperty property) {
					if (property.getPropertyType().isArray() || property.getPropertyType().isList()) {
						property.setPreferenceType(PreferenceType.STRING);
					} else {
						if (property.isType(Boolean.TYPE, Boolean.class)) {
							property.setPreferenceType(PreferenceType.BOOL);
						} else if (property.isType(Integer.TYPE, Integer.class)) {
							property.setPreferenceType(PreferenceType.INT);
						} else if (property.isType(Long.TYPE, Long.class)) {
							property.setPreferenceType(PreferenceType.LONG);
						} else if (property.isType(Float.TYPE, Float.class)) {
							property.setPreferenceType(PreferenceType.FLOAT);
						} else {
							property.setPreferenceType(PreferenceType.STRING);
						}
					}

					if (!bindAllFields && !property.hasAnnotation(Bind.class) && !property.hasAnnotation(BindPreference.class)) {
						// skip field
						return false;
					}

					if (bindAllFields || property.hasAnnotation(Bind.class) || property.hasAnnotation(BindPreference.class)) {

						ModelAnnotation annotation = property.getAnnotation(BindPreference.class);
						// if field disable, skip property definition
						if (annotation != null && AnnotationUtility.extractAsBoolean(elementUtils, property, annotation, AnnotationAttributeType.ATTRIBUTE_ENABLED) == false) {
							return false;
						}

						return true;
					}

					return false;
				}
			});

			model.entityAdd(currentEntity);
			return result;
		}

	}

}
