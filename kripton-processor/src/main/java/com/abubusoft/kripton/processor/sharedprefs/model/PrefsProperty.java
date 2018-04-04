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
package com.abubusoft.kripton.processor.sharedprefs.model;

import java.util.List;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindPreferenceAdapter;
import com.abubusoft.kripton.android.sharedprefs.PreferenceType;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ManagedModelProperty;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelEntity;
import com.abubusoft.kripton.processor.core.TypeAdapterHelper;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAnnotationException;
import com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransformer;

public class PrefsProperty extends ManagedModelProperty {

	private static Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
	
	public PrefsProperty(@SuppressWarnings("rawtypes") ModelEntity entity, Element element, List<ModelAnnotation> modelAnnotations) {
		super(entity, element, modelAnnotations);

		String name = AnnotationUtility.extractAsString(element, BindPreference.class, AnnotationAttributeType.VALUE);
		if (!StringUtils.hasText(name)) {
			name = converter.convert(element.getSimpleName().toString());
		}

		preferenceKey = name;

		// @BindPreferenceAdapter
		ModelAnnotation annotationBindAdapter = this.getAnnotation(BindPreferenceAdapter.class);
		if (annotationBindAdapter != null) {
			typeAdapter.adapterClazz = annotationBindAdapter.getAttributeAsClassName(AnnotationAttributeType.ADAPTER);
			typeAdapter.dataType = TypeAdapterHelper.detectDestinationType(entity.getElement(), typeAdapter.adapterClazz);

			// check type adapter
			checkTypeAdapter(entity, element.asType(), typeAdapter, annotationBindAdapter);
			
			PrefsTransform transform = PrefsTransformer.lookup(TypeUtility.typeName(typeAdapter.dataType));

			if (!transform.isTypeAdapterAware()) {
				String msg = String.format("In class '%s', property '%s' is converted in unsupported target type '%s' by @%s", entity.getElement().toString(), getName(), TypeUtility.typeName(typeAdapter.dataType),
						BindPreferenceAdapter.class.getSimpleName());
				throw (new IncompatibleAnnotationException(msg));
			}
		}
	}

	protected String preferenceKey;

	public String getPreferenceKey() {
		return preferenceKey;
	}

	/**
	 * kind of preference associated
	 */
	protected PreferenceType preferenceType;

	public PreferenceType getPreferenceType() {
		return preferenceType;
	}

	public void setPreferenceType(PreferenceType preferenceType) {
		this.preferenceType = preferenceType;
	}

}
