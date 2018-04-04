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
package com.abubusoft.kripton.processor.bind.model;

import java.util.List;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.ModelType;
import com.abubusoft.kripton.xml.MapEntryType;
import com.abubusoft.kripton.xml.XmlType;
import com.squareup.javapoet.TypeName;

public class BindProperty extends ModelProperty {

	public static class BindPropertyBuilder {
		protected BindProperty parentProperty;

		protected TypeName rawTypeName;

		protected XmlType xmlType;

		/**
		 * element's label, or collection's label (for xml binding)
		 */
		private String label;

		private boolean nullable;

		protected boolean inCollection;

		public BindPropertyBuilder inCollection(boolean inCollection) {
			this.inCollection = inCollection;

			return this;
		}

		public BindPropertyBuilder(TypeName rawTypeName, BindProperty property) {
			this.rawTypeName = rawTypeName;

			if (property != null) {
				this.parentProperty = property;
				this.nullable = property.nullable;
				this.xmlType = property.xmlInfo.xmlType;
				this.label = property.label;
			}
			this.inCollection = true;
		}

		public BindPropertyBuilder(TypeName parameterTypeName) {
			this.rawTypeName = parameterTypeName;
			this.parentProperty = null;
			this.nullable = true;
			this.inCollection = true;
		}

		public BindProperty build() {
			BindProperty property = new BindProperty(null, null, null);

			property.propertyType = new ModelType(rawTypeName);
			property.order = parentProperty != null ? parentProperty.order : 0;
			property.inCollection = this.inCollection;

			property.label = label;

			property.xmlInfo.xmlType = this.xmlType;
			property.xmlInfo.labelItem = null;

			property.nullable = this.nullable;

			return property;
		}

		public BindPropertyBuilder xmlType(XmlType xmlType) {
			this.xmlType = xmlType;

			return this;
		}

		public BindPropertyBuilder elementName(String label) {
			this.label = label;
			return this;
		}

		public BindPropertyBuilder label(String elementTag) {
			this.label = elementTag;
			return this;
		}

		public BindPropertyBuilder nullable(boolean value) {
			this.nullable = value;
			return this;
		}

	}

	public class JacksonInfo {

		// public String jacksonName;
	}

	public class XmlInfo {

		public MapEntryType mapEntryType = MapEntryType.TAG;

		/**
		 * tag typeName used for item or collection (if element is a collection)
		 */
		// public String itemTag;

		/**
		 * tag typeName for collection's item
		 */
		public String labelItem;

		public boolean wrappedCollection;

		public XmlType xmlType = XmlType.TAG;

		/**
		 * If true, this element is a collection with a tag for collection and a
		 * tag for each element
		 * 
		 * @return true if this element is a wrapped collection
		 */
		public boolean isWrappedCollection() {
			return wrappedCollection;
		}
	}

	public static BindPropertyBuilder builder(TypeName rawTypeName, BindProperty property) {
		return new BindPropertyBuilder(rawTypeName, property);
	}

	public static BindPropertyBuilder builder(TypeName parameterTypeName) {
		return new BindPropertyBuilder(parameterTypeName);
	}

	/**
	 * if true, means property is to write into a collection
	 */
	public boolean inCollection;

	public boolean nullable;

	public boolean bindedObject;

	public int order;

	public XmlInfo xmlInfo;

	public JacksonInfo jacksonInfo;

	public String mapKeyName;

	/**
	 * property's label
	 */
	public String label;

	public String mapValueName;

	public BindProperty(BindEntity entity, Element element, List<ModelAnnotation> modelAnnotations) {
		super(entity, element, modelAnnotations);

		nullable = true;
		inCollection = false;
		xmlInfo = new XmlInfo();
		jacksonInfo = new JacksonInfo();
		
		ModelAnnotation annotationBindAdapter = this.getAnnotation(BindAdapter.class);
		// this is needed for table generation in sqlite
		ModelAnnotation annotationBindSqlAdapter = this.getAnnotation(BindSqlAdapter.class);		
		AssertKripton.assertTrueOfInvalidDefinition((annotationBindAdapter==null && annotationBindSqlAdapter==null) || (annotationBindAdapter==null)!=(annotationBindSqlAdapter==null) , this, "@BindAdapter and @BindSqlAdapter can not be used together");
		if (annotationBindAdapter==null) {
			annotationBindAdapter=annotationBindSqlAdapter;
		}		
		
		if (annotationBindAdapter != null) {
			typeAdapter.adapterClazz = annotationBindAdapter.getAttributeAsClassName(AnnotationAttributeType.ADAPTER);
			typeAdapter.dataType = detectDestinationType(entity.getElement(), typeAdapter.adapterClazz);
			
			// check type adapter
			checkTypeAdapter(entity, element.asType(), typeAdapter, annotationBindAdapter);
		}

	}

	public boolean isInCollection() {
		return inCollection;
	}

	public boolean isNullable() {
		return nullable;
	}

	public boolean isBindedObject() {
		return bindedObject;
	}

	public boolean isBindedCollection() {
		return propertyType.isCollection();
	}

	public boolean isBindedArray() {
		return propertyType.isArray();
	}

	public boolean isBindedMap() {
		return propertyType.isMap();
	}

}
