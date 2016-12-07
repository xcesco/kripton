package com.abubusoft.kripton.processor.bind;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.binder.xml.internal.MapEntryType;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.bind.model.BindModel;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.abubusoft.kripton.processor.bind.transform.BindTransformer;
import com.abubusoft.kripton.processor.bind.transform.ObjectBindTransform;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.PropertyFactory;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility.PropertyCreatedListener;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;

public class BindEntityBuilder {

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

	private static AnnotationFilter propertyAnnotationFilter = AnnotationFilter.builder().add(Bind.class).add(BindXml.class).add(BindDisabled.class).build();

	public static BindEntity build(final BindModel model, final Elements elementUtils, Element element) {
		final InnerCounter counterPropertyInValue = new InnerCounter();
		final Converter<String, String> typeNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		final Element beanElement = element;

		final BindEntity currentEntity = new BindEntity(beanElement.getSimpleName().toString(), (TypeElement) beanElement);

		// tag name
		String tagName = AnnotationUtility.extractAsString(elementUtils, beanElement, BindType.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
		if (StringUtils.hasText(tagName)) {
			currentEntity.xmlInfo.tagName = tagName;
		} else {
			currentEntity.xmlInfo.tagName = typeNameConverter.convert(beanElement.getSimpleName().toString());
		}

		AnnotationUtility.buildAnnotations(elementUtils, currentEntity, classAnnotationFilter);

		final boolean bindAllFields = AnnotationUtility.getAnnotationAttributeAsBoolean(currentEntity, BindType.class, AnnotationAttributeType.ATTRIBUTE_ALL_FIELDS, Boolean.TRUE);

		PropertyUtility.buildProperties(elementUtils, currentEntity, new PropertyFactory<BindProperty>() {

			@Override
			public BindProperty createProperty(Element element) {
				return new BindProperty(element);
			}
		}, propertyAnnotationFilter, new PropertyCreatedListener<BindProperty>() {

			@Override
			public boolean onProperty(BindProperty property) {
				// if we are build Map, the model are not null
				boolean contextExternal = (model == null);
				
				// if @BindDisabled is present, we don't need it
				ModelAnnotation annotationBindDisabled = property.getAnnotation(BindDisabled.class);
				if (annotationBindDisabled != null)
					return false;

				boolean enabled = bindAllFields;
				ModelAnnotation annotationBind = property.getAnnotation(Bind.class);
				enabled = enabled || (annotationBind != null && AnnotationUtility.extractAsBoolean(elementUtils, property, annotationBind, AnnotationAttributeType.ATTRIBUTE_ENABLED));

				// if we are not in external context and element is not enabled, we have to analyze in every case.				
				if (!enabled || !contextExternal) {
					return false;
				}

				ModelAnnotation annotationBindXml = property.getAnnotation(BindXml.class);

				property.jacksonInfo.jacksonName = typeNameConverter.convert(property.getName());
				property.order = 0;
				property.xmlInfo.tag = typeNameConverter.convert(property.getName());
				property.xmlInfo.tagElement = property.xmlInfo.tag;
				property.xmlInfo.wrappedCollection = false;
				property.xmlInfo.xmlType = XmlType.valueOf(XmlType.TAG.toString());
				property.mapKeyName = Bind.MAP_KEY_DEFAULT;
				property.mapValueName = Bind.MAP_VALUE_DEFAULT;
				property.xmlInfo.mapEntryType = MapEntryType.valueOf(MapEntryType.TAG.toString());			

				// @Bind management
				if (annotationBind != null) {
					int order = AnnotationUtility.extractAsInt(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.ATTRIBUTE_ORDER);
					property.order = order;

					String tempName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.ATTRIBUTE_VALUE);
					if (StringUtils.hasText(tempName)) {
						property.xmlInfo.tag = tempName;
					}

					// map info
					String mapKeyName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.ATTRIBUTE_MAP_KEY_NAME);
					if (StringUtils.hasText(mapKeyName))
						property.mapKeyName = mapKeyName;

					String mapValueName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), Bind.class, AnnotationAttributeType.ATTRIBUTE_MAP_VALUE_NAME);
					if (StringUtils.hasText(mapValueName))
						property.mapValueName = mapValueName;
				}

				// @BindXml management
				if (annotationBindXml != null) {
					String mapEntryType = AnnotationUtility.extractAsEnumerationValue(elementUtils, property.getElement(), BindXml.class, AnnotationAttributeType.ATTRIBUTE_MAP_ENTRY_TYPE);
					if (StringUtils.hasText(mapEntryType))
						property.xmlInfo.mapEntryType = MapEntryType.valueOf(mapEntryType);

					// define element tag name
					String tempElementName = AnnotationUtility.extractAsString(elementUtils, property.getElement(), BindXml.class, AnnotationAttributeType.ATTRIBUTE_XML_ELEMENT_TAG);
					if (StringUtils.hasText(tempElementName)) {
						property.xmlInfo.tagElement = tempElementName;
						property.xmlInfo.wrappedCollection = true;
					}

					String xmlType = AnnotationUtility.extractAsEnumerationValue(elementUtils, property.getElement(), BindXml.class, AnnotationAttributeType.ATTRIBUTE_XML_TYPE);
					if (StringUtils.hasText(xmlType))
						property.xmlInfo.xmlType = XmlType.valueOf(xmlType);

				}

				if (property.xmlInfo.xmlType == XmlType.ATTRIBUTE) {
					// check if property is a array
					if (property.getPropertyType().isArray()) {
						String msg = String.format("In class '%s', property '%s' is an array and it can not be mapped in a xml attribute", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					// check if property is a collection
					if (property.getPropertyType().isCollection()) {
						String msg = String.format("In class '%s', property '%s' is an array and it can not be mapped in a xml attribute", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					// check if property is a map
					if (property.getPropertyType().isMap()) {
						String msg = String.format("In class '%s', property '%s' is an array and it can not be mapped in a xml attribute", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					BindTransform transform = BindTransformer.lookup(property.getPropertyType().getName());
					if (transform != null && transform instanceof ObjectBindTransform) {
						String msg = String.format("In class '%s', property '%s' is an object and it can not be mapped in a xml attribute", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}
				}

				if (property.xmlInfo.xmlType == XmlType.VALUE || property.xmlInfo.xmlType == XmlType.VALUE_CDATA) {
					counterPropertyInValue.inc();
					
					// check if property is a array
					if (property.getPropertyType().isArray()) {
						String msg = String.format("In class '%s', property '%s' is an array and it can not be mapped in a xml value", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					// check if property is a collection
					if (property.getPropertyType().isCollection()) {
						String msg = String.format("In class '%s', property '%s' is an array and it can not be mapped in a xml value", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					// check if property is a map
					if (property.getPropertyType().isMap()) {
						String msg = String.format("In class '%s', property '%s' is an array and it can not be mapped in a xml value", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}

					// TODO check objects
					BindTransform transform = BindTransformer.lookup(property.getPropertyType().getName());
					if (transform != null && transform instanceof ObjectBindTransform) {
						String msg = String.format("In class '%s', property '%s' is an object and it can not be mapped in a xml value", beanElement.asType().toString(), property.getName());
						throw (new IncompatibleAttributesInAnnotationException(msg));
					}
				}
				
				if (counterPropertyInValue.value() > 1) {
					String msg = String.format("In class '%s', property '%s' and other properties are mapped in a xml value, but only one property for class can be a xml value", beanElement.asType().toString(), property.getName());
					throw (new IncompatibleAttributesInAnnotationException(msg));
				}

				property.bindedObject=BindTransformer.isBindedObject(property);
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
