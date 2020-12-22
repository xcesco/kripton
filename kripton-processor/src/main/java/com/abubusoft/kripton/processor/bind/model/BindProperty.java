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
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelEntity;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.ModelType;
import com.abubusoft.kripton.processor.core.TypeAdapterHelper;
import com.abubusoft.kripton.xml.MapEntryType;
import com.abubusoft.kripton.xml.XmlType;
import com.squareup.javapoet.TypeName;


/**
 * The Class BindProperty.
 */
public class BindProperty extends ModelProperty {

	/**
	 * The Class BindPropertyBuilder.
	 */
	public static class BindPropertyBuilder {
		
		/** The parent property. */
		protected BindProperty parentProperty;

		/** The raw type name. */
		protected TypeName rawTypeName;

		/** The xml type. */
		protected XmlType xmlType;

		/** element's label, or collection's label (for xml binding). */
		private String label;

		/** The nullable. */
		private boolean nullable;

		/** The in collection. */
		protected boolean inCollection;

		private ModelEntity parent;

		/**
		 * In collection.
		 *
		 * @param inCollection the in collection
		 * @return the bind property builder
		 */
		public BindPropertyBuilder inCollection(boolean inCollection) {
			this.inCollection = inCollection;

			return this;
		}

		/**
		 * Instantiates a new bind property builder.
		 *
		 * @param rawTypeName the raw type name
		 * @param property the property
		 */
		public BindPropertyBuilder(TypeName rawTypeName, BindProperty property) {
			this.rawTypeName = rawTypeName;			

			if (property != null) {
				this.parentProperty = property;
				this.parent=property.getParent();
				this.nullable = property.nullable;
				this.xmlType = property.xmlInfo.xmlType;
				this.label = property.label;
			}
			this.inCollection = true;
		}

		/**
		 * Instantiates a new bind property builder.
		 *
		 * @param parameterTypeName the parameter type name
		 */
		public BindPropertyBuilder(TypeName parameterTypeName) {
			this.rawTypeName = parameterTypeName;
			this.parentProperty = null;
			this.nullable = true;
			this.inCollection = true;
		}

		/**
		 * Builds the.
		 *
		 * @return the bind property
		 */
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

		/**
		 * Xml type.
		 *
		 * @param xmlType the xml type
		 * @return the bind property builder
		 */
		public BindPropertyBuilder xmlType(XmlType xmlType) {
			this.xmlType = xmlType;

			return this;
		}

		/**
		 * Element name.
		 *
		 * @param label the label
		 * @return the bind property builder
		 */
		public BindPropertyBuilder elementName(String label) {
			this.label = label;
			return this;
		}

		/**
		 * Label.
		 *
		 * @param elementTag the element tag
		 * @return the bind property builder
		 */
		public BindPropertyBuilder label(String elementTag) {
			this.label = elementTag;
			return this;
		}

		/**
		 * Nullable.
		 *
		 * @param value the value
		 * @return the bind property builder
		 */
		public BindPropertyBuilder nullable(boolean value) {
			this.nullable = value;
			return this;
		}

	}

	/**
	 * The Class JacksonInfo.
	 */
	public class JacksonInfo {

		// public String jacksonName;
	}

	/**
	 * The Class XmlInfo.
	 */
	public class XmlInfo {

		/** The map entry type. */
		public MapEntryType mapEntryType = MapEntryType.TAG;

		/**
		 * tag typeName for collection's item
		 */
		public String labelItem;
		
		/** The wrapped collection. */
		public boolean wrappedCollection;

		/** The xml type. */
		public XmlType xmlType = XmlType.TAG;

		/**
		 * Namespace used for element
		 */
		public String namespace;

		/**
		 * If true, this element is a collection with a tag for collection and a
		 * tag for each element.
		 *
		 * @return true if this element is a wrapped collection
		 */
		public boolean isWrappedCollection() {
			return wrappedCollection;
		}
	}

	/**
	 * Builder.
	 *
	 * @param rawTypeName the raw type name
	 * @param property the property
	 * @return the bind property builder
	 */
	public static BindPropertyBuilder builder(TypeName rawTypeName, BindProperty property) {
		return new BindPropertyBuilder(rawTypeName, property);
	}

	/**
	 * Builder.
	 *
	 * @param parameterTypeName the parameter type name
	 * @return the bind property builder
	 */
	public static BindPropertyBuilder builder(TypeName parameterTypeName) {
		return new BindPropertyBuilder(parameterTypeName);
	}

	/** if true, means property is to write into a collection. */
	public boolean inCollection;

	/** The nullable. */
	public boolean nullable;

	/** The binded object. */
	public boolean bindedObject;

	/** The order. */
	public int order;

	/** The xml info. */
	public XmlInfo xmlInfo;

	/** The jackson info. */
	public JacksonInfo jacksonInfo;

	/** The map key name. */
	public String mapKeyName;

	/** property's label. */
	public String label;

	/** The map value name. */
	public String mapValueName;

	/**
	 * Instantiates a new bind property.
	 *
	 * @param entity the entity
	 * @param element the element
	 * @param modelAnnotations the model annotations
	 */
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
			typeAdapter.dataType = TypeAdapterHelper.detectDestinationType(entity.getElement(), typeAdapter.adapterClazz);
			
			// check type adapter
			checkTypeAdapter(entity, element.asType(), typeAdapter, annotationBindAdapter);
		}

	}

	/**
	 * Checks if is in collection.
	 *
	 * @return true, if is in collection
	 */
	public boolean isInCollection() {
		return inCollection;
	}

	/**
	 * Checks if is nullable.
	 *
	 * @return true, if is nullable
	 */
	public boolean isNullable() {
		return nullable;
	}
	
	public static String xmlName(BindProperty property) {
		String namespace=property.xmlInfo.namespace;
		String label=property.label;
		if (StringUtils.hasText(namespace)) {
			return namespace+":"+label;
		}
		
		return label;
	}

	/**
	 * Checks if is binded object.
	 *
	 * @return true, if is binded object
	 */
	public boolean isBindedObject() {
		return bindedObject;
	}

	/**
	 * Checks if is binded collection.
	 *
	 * @return true, if is binded collection
	 */
	public boolean isBindedCollection() {
		return propertyType.isCollection();
	}

	/**
	 * Checks if is binded array.
	 *
	 * @return true, if is binded array
	 */
	public boolean isBindedArray() {
		return propertyType.isArray();
	}

	/**
	 * Checks if is binded map.
	 *
	 * @return true, if is binded map
	 */
	public boolean isBindedMap() {
		return propertyType.isMap();
	}

	public static String xmlNameForItem(BindProperty property) {		
		String namespace=property.xmlInfo.namespace;
		String label=property.xmlInfo.labelItem;
		if (StringUtils.hasText(namespace)) {
			return namespace+":"+label;
		}
		
		return label;
	}

}
