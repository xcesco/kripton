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
package com.abubusoft.kripton.processor.bind.model;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.binder.xml.internal.MapEntryType;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;

public class BindProperty extends ModelProperty {

	public BindProperty(Element element) {
		super(element);
		
		nullable=true;
		elementInCollection=false;
		xmlInfo=new XmlInfo();
	}
	public XmlInfo xmlInfo;
	
	public int order;

	public String jacksonName;

	public boolean nullable;

	/**
	 * if true, means property is to write into a collection
	 */
	public boolean elementInCollection;

	public boolean isNullable() {
		return !elementInCollection;
	}

	public class XmlInfo {
		/**
		 * tag name used for item or collection (if element is a collection)
		 */
		public String tag;
		
		/**
		 * tag name for collection's element
		 */
		public String tagElement;
		
		public XmlType xmlType;
		
		public MapEntryType mapEntryType;

		public boolean wrappedCollection;
		
		/**
		 * If true, this element is a collection with a tag for collection and a tag for each element 
		 * 
		 * @return
		 * 		true if this element is a wrapped collection
		 */
		public boolean isWrappedCollection()
		{
			return wrappedCollection;
		}
		

	}

	public static BindPropertyBuilder builder(TypeName rawTypeName, BindProperty property) {
		return new BindPropertyBuilder(rawTypeName, property);
	}
	
	public static class BindPropertyBuilder
	{		
		public BindPropertyBuilder(TypeName rawTypeName, BindProperty property) {
			this.rawTypeName=rawTypeName;
			this.parentProperty=property;
		}

		public BindProperty build()
		{
			BindProperty property=new BindProperty(null);
			
			property.jacksonName=parentProperty.jacksonName;
			property.order=parentProperty.order;
			property.elementInCollection=true;
			property.xmlInfo.xmlType=XmlType.TAG;
			property.xmlInfo.tag=parentProperty.xmlInfo.tagElement;
			property.xmlInfo.tagElement=null;
			
			return property;
		}
		
		protected TypeName rawTypeName;
		
		protected BindProperty parentProperty;
	}

	public boolean isElementInCollection() {
		return elementInCollection;
	}

}
