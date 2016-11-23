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
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

public class BindProperty extends ModelProperty {

	public BindProperty(Element element) {
		super(element);
		
		xmlInfo=new XmlInfo();
	}
	public XmlInfo xmlInfo;
	
	public int order;

	public String jacksonName;

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

	public static BindPropertyBuilder builder(TypeName rawTypeName) {
		return new BindPropertyBuilder(rawTypeName);
	}
	
	public static class BindPropertyBuilder
	{		
		public BindPropertyBuilder(TypeName rawTypeName) {
			this.rawTypeName=rawTypeName;
		}

		public BindProperty build()
		{
			return new BindProperty(null);
		}
		
		protected TypeName rawTypeName;
	}

}
