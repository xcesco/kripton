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
package com.abubusoft.kripton.processor.bind;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindModel;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.abubusoft.kripton.processor.bind.transform.BindTransformer;
import com.abubusoft.kripton.processor.bind.transform.ByteArrayBindTransform;
import com.abubusoft.kripton.processor.bind.transform.ObjectBindTransform;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.PropertyFactory;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAnnotationException;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;
import com.abubusoft.kripton.xml.MapEntryType;
import com.abubusoft.kripton.xml.XmlType;

public abstract class BindEntityBuilder {

	public static class InnerCounter {
		int counter;

		public void inc() {
			counter++;
		}

		public int value() {
			return counter;
		}

	}

	private static AnnotationFilter classAnnotationFilter = AnnotationFilter.builder().add(BindType.class).build();

	private static AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindXml.class).add(BindDisabled.class).add(BindAdapter.class).build();

	public static BindEntity build(final BindModel model, final Elements elementUtils, TypeElement element) {
		final InnerCounter counterPropertyInValue = new InnerCounter();
		final Converter<String, String> typeNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		final TypeElement beanElement = element;

		final BindEntity currentEntity = new BindEntity(beanElement.getSimpleName().toString(), beanElement);

		// tag typeName
		String tagName = AnnotationUtility.extractAsString(elementUtils, beanElement, BindType.class, AnnotationAttributeType.VALUE);
		if (StringUtils.hasText(tagName)) {
			currentEntity.xmlInfo.label = tagName;
		} else {
			currentEntity.xmlInfo.label = typeNameConverter.convert(beanElement.getSimpleName().toString());
		}

		//AnnotationUtility.buildAnnotations(elementUtils, currentEntity, classAnnotationFilter);

		final boolean bindAllFields = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindType.class, AnnotationAttributeType.ALL_FIELDS, Boolean.TRUE);

		PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<BindEntity, BindProperty>() {

			@Override
			public BindProperty createProperty(BindEntity entity, Element propertyElement) {
				return new BindProperty(currentEntity, propertyElement, AnnotationUtility.buildAnnotationList(propertyElement));
			}

		}, propertyAnnotationFilter, new PropertyCreatedListener<BindEntity, BindProperty>() {

			@Override
			public boolean onProperty(BindEntity entity, BindProperty property) {
				// if we are build Map, the model are not null
				boolean contextExternal = (model == null);

				// if @BindDisabled is present, exit immediately
				if (property.hasAnnotation(BindDisabled.class)) {
					if (bindAllFields) {
						return false;
					} else {
						throw new InvalidDefinition(String.format("@%s can not be used with @%s(allField=false)", BindDisabled.class.getSimpleName(), BindType.class.getSimpleName()));
					}
				}

				boolean enabled = bindAllFields;
				ModelAnnotation annotationBind = property.getAnnotation(Bind.class);
				enabled = enabled || (annotationBind != null && AnnotationUtility.extractAsBoolean(elementUtils, property, annotationBind, AnnotationAttributeType.ENABLED));

				// if we are not in external context and element is not enabled,
				// we have to analyze in every case.
				if (!enabled && !contextExternal) {
					return false;
				}

				ModelAnnotation annotationBindXml = property.getAnnotation(BindXml.class);

				property.order = 0;
				property.mapKeyName = Bind.MAP_KEY_DEFAULT;
				property.mapValueName = Bind.MAP_VALUE_DEFAULT;
				// label for item and collection elements are the same for
				// default
				property.label = typeNameConverter.convert(property.getName());
				property.xmlInfo.labelItem = property.label;
				property.xmlInfo.wrappedCollection = false;
				property.xmlInfo.xmlType = XmlType.valueOf(XmlType.TAG.toString());
				property.xmlInfo.mapEntryType = MapEntryType.valueOf(MapEntryType.TAG.toString());

				// @BindAdapter
				ModelAnnotation annotationBindAdapter = property.getAnnotation(BindAdapter.class);
				if (annotationBindAdapter != null) {
					//property.typeAdapter.adapterClazz = annotationBindAdapter.getAttributeAsClassName(AnnotationAttributeType.ADAPTER);
					//property.typeAdapter.dataType = annotationBindAdapter.getAttributeAsClassName(AnnotationAttributeType.DATA_TYPE);

					BindTransform transform = BindTransformer.lookup(TypeUtility.typeName(property.typeAdapter.dataType));

					if (!transform.isTypeAdapterSupported()) {
						String msg = String.format("In class '%s', property '%s' uses @BindAdapter with unsupported 'dataType' '%s'", beanElement.asType().toString(), property.getName(),
								property.typeAdapter.dataType);
						throw (new IncompatibleAnnotationException(msg));
					}

					if (property.getPropertyType().isPrimitive()) {
						String msg = String.format("In class '%s', property '%s' is primitive of type '%s' and it can not be annotated with @BindAdapter", beanElement.asType().toString(),
								property.getName(), property.getPropertyType().getTypeName());
						throw (new IncompatibleAnnotationException(msg));
					}
				}

				// @Bind management
				if (annotationBind != null) {
					int order = AnnotationUtility.extractAsInt(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.ORDER);
					property.order = order;

					String tempName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.VALUE);
					if (StringUtils.hasText(tempName)) {
						// for the moment are the same
						property.label = tempName;
						property.xmlInfo.labelItem = property.label;
					}

					// map info
					String mapKeyName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.MAP_KEY_NAME);
					if (StringUtils.hasText(mapKeyName))
						property.mapKeyName = mapKeyName;

					String mapValueName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.MAP_VALUE_NAME);
					if (StringUtils.hasText(mapValueName))
						property.mapValueName = mapValueName;
				}

				// @BindXml management
				if (annotationBindXml != null) {
					String mapEntryType = AnnotationUtility.extractAsEnumerationValue(property.getElement(), BindXml.class, AnnotationAttributeType.MAP_ENTRY_TYPE);
					if (StringUtils.hasText(mapEntryType))
						property.xmlInfo.mapEntryType = MapEntryType.valueOf(mapEntryType);

					// define element tag typeName
					String tempElementName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), BindXml.class, AnnotationAttributeType.XML_ELEMENT_TAG);
					if (StringUtils.hasText(tempElementName)) {
						property.xmlInfo.labelItem = tempElementName;
						property.xmlInfo.wrappedCollection = true;
					}

					String xmlType = AnnotationUtility.extractAsEnumerationValue(property.getElement(), BindXml.class, AnnotationAttributeType.XML_TYPE);
					if (StringUtils.hasText(xmlType))
						property.xmlInfo.xmlType = XmlType.valueOf(xmlType);

				}

				if (property.xmlInfo.xmlType == XmlType.ATTRIBUTE) {
					BindTransform transform = BindTransformer.lookup(property.getPropertyType().getTypeName());

					// check if property is a array
					if (property.isBindedArray() && !(transform instanceof ByteArrayBindTransform)) {
						String msg = String.format("In class '%s', property '%s' is an array and it can not be mapped in a xml attribute", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					// check if property is a collection
					if (property.isBindedCollection()) {
						String msg = String.format("In class '%s', property '%s' is a collection and it can not be mapped in a xml attribute", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					// check if property is a map
					if (property.isBindedMap()) {
						String msg = String.format("In class '%s', property '%s' is an map and it can not be mapped in a xml attribute", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					if (transform != null && transform instanceof ObjectBindTransform) {
						String msg = String.format("In class '%s', property '%s' is an object and it can not be mapped in a xml attribute", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}
				}

				if (property.xmlInfo.xmlType == XmlType.VALUE || property.xmlInfo.xmlType == XmlType.VALUE_CDATA) {
					counterPropertyInValue.inc();

					BindTransform transform = BindTransformer.lookup(property.getPropertyType().getTypeName());

					// check if property is a array
					if (property.isBindedArray() && !(transform instanceof ByteArrayBindTransform)) {
						String msg = String.format("In class '%s', property '%s' is an array and it can not be mapped in a xml value", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					// check if property is a collection
					if (property.isBindedCollection()) {
						String msg = String.format("In class '%s', property '%s' is a collection and it can not be mapped in a xml value", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					// check if property is a map
					if (property.isBindedMap()) {
						String msg = String.format("In class '%s', property '%s' is a map and it can not be mapped in a xml value", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					if (transform != null && transform instanceof ObjectBindTransform) {
						String msg = String.format("In class '%s', property '%s' is an object and it can not be mapped in a xml value", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}
				}

				if (counterPropertyInValue.value() > 1) {
					String msg = String.format("In class '%s', property '%s' and other properties are mapped in a xml value, but only one property for class can be a xml value",
							beanElement.asType().toString(), property.getName());
					throw (new IncompatibleAttributesInAnnotationException(msg));
				}

				property.bindedObject = BindTransformer.isBindedObject(property);

				// if it's an object, we need to avoid to print field typeName
				// (like
				// object transform usually do).
				// set inCollection to true, permits this.
				if (property.bindedObject && contextExternal) {
					property.inCollection = true;
				}

				return true;

			}

		});

		// if we don't have model, we dont save bean definition
		if (model != null) {
			model.entityAdd(currentEntity);
		}
		return currentEntity;
	}
}
