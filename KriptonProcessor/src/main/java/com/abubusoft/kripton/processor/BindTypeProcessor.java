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
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.binder.xml.internal.MapEntryType;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.bind.BindTypeBuilder;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindModel;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.transform.BindTransformer;
import com.abubusoft.kripton.processor.bind.transform.EnumTransform;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.PropertyFactory;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.utils.StringUtility;

/**
 * Annotation processor for json/xml/etc
 * 
 * @author xcesco
 *
 */
public class BindTypeProcessor extends BaseProcessor {

	private BindModel model;

	private AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).add(BindSharedPreferences.class).build();

	private AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindPreference.class).build();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#getSupportedAnnotationTypes ()
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotations = new LinkedHashSet<String>();

		annotations.add(BindType.class.getCanonicalName());

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
					BindTransformer.register(typeName(item), new EnumTransform(typeName(item)));
				}

			}

			// Put all @BindSharedPreferences elements in beanElements
			for (Element item : roundEnv.getElementsAnnotatedWith(BindType.class)) {
				if (item.getKind() != ElementKind.CLASS && item.getKind() != ElementKind.ENUM) {
					String msg = String.format("%s %s, only class can be annotated with @%s annotation", item.getKind(), item, BindType.class.getSimpleName());
					throw (new InvalidKindForAnnotationException(msg));
				}
				
				if (item.getKind() == ElementKind.ENUM) continue;
				
				analyzeBindedClass(item);

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

	private String analyzeBindedClass(Element element) {
		final Converter<String, String> typeNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		Element beanElement = element;
		String result = beanElement.getSimpleName().toString();

		final BindEntity currentEntity = new BindEntity(beanElement.getSimpleName().toString(), (TypeElement) beanElement);

		// tag name
		String tagName = AnnotationUtility.extractAsString(elementUtils, beanElement, BindType.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
		if (StringUtility.hasText(tagName)) {
			currentEntity.xmlInfo.tagName = tagName;
		} else {
			currentEntity.xmlInfo.tagName = typeNameConverter.convert(beanElement.getSimpleName().toString());
		}

		AnnotationUtility.buildAnnotations(elementUtils, currentEntity, classAnnotationFilter);

		final boolean bindAllFields = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindType.class, AnnotationAttributeType.ATTRIBUTE_ALL_FIELDS, Boolean.TRUE);

		{
			PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<BindProperty>() {

				@Override
				public BindProperty createProperty(Element element) {
					return new BindProperty(element);
				}
			}, propertyAnnotationFilter, new PropertyCreatedListener<BindProperty>() {

				@Override
				public boolean onProperty(BindProperty property) {					
					if (!bindAllFields && !property.hasAnnotation(Bind.class)) {
						// skip field
						return false;
					}

					if (bindAllFields || property.hasAnnotation(Bind.class) || property.hasAnnotation(BindXml.class)) {						
						ModelAnnotation annotationBindXml = property.getAnnotation(BindXml.class);
						// if field disable, skip property definition
						if (annotationBindXml != null && (AnnotationUtility.extractAsBoolean(elementUtils, property, annotationBindXml, AnnotationAttributeType.ATTRIBUTE_ENABLED) == false)) {
							return false;
						}
						
						ModelAnnotation annotationBind = property.getAnnotation(Bind.class);
						// if field disable, skip property definition
						if (annotationBind != null && (AnnotationUtility.extractAsBoolean(elementUtils, property, annotationBind, AnnotationAttributeType.ATTRIBUTE_ENABLED) == false)) {
							return false;
						}
						
						// set the order
						int order = AnnotationUtility.extractAsInt(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.ATTRIBUTE_ORDER);
						property.jacksonName=typeNameConverter.convert(property.getName());
						property.order=order;
						

						String tempName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
						if (StringUtility.hasText(tempName)) {
							property.xmlInfo.tag = tempName;
						} else {
							property.xmlInfo.tag = typeNameConverter.convert(property.getName());
						}
						
						// define element tag name
						String tempElementName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), BindXml.class, AnnotationAttributeType.ATTRIBUTE_XML_ELEMENT_TAG);
						if (StringUtility.hasText(tempElementName)) {
							property.xmlInfo.tagElement = tempElementName;
							property.xmlInfo.wrappedCollection=true;
						} else {
							property.xmlInfo.tagElement = property.xmlInfo.tag;
							property.xmlInfo.wrappedCollection=false;
						}
						
						String xmlType=AnnotationUtility.extractAsEnumerationValue(elementUtils, property.getElement(), BindXml.class, AnnotationAttributeType.ATTRIBUTE_XML_TYPE);
						if (xmlType==null) xmlType=XmlType.TAG.toString();
						property.xmlInfo.xmlType=XmlType.valueOf(xmlType);
						
						String mapEntryType=AnnotationUtility.extractAsEnumerationValue(elementUtils, property.getElement(), BindXml.class, AnnotationAttributeType.ATTRIBUTE_MAP_ENTRY_STRATEGY);
						if (mapEntryType==null) mapEntryType=MapEntryType.ELEMENTS.toString();
						property.xmlInfo.mapEntryType=MapEntryType.valueOf(mapEntryType);

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
