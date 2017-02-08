/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
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
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;

public class BindEntity extends ModelClass<BindProperty> {

	public BindEntity(String name, Element beanElement) {
		super(name, beanElement);

		xmlInfo = new XmlInfo();
		
		typeArgs = TypeUtility.getTypeArguments((TypeElement) beanElement);
		AssertKripton.assertTrue(typeArgs.size()<2, "%s has a Kripton unsupported hierarchy  ",beanElement.asType());
		
	}
	
	public XmlInfo xmlInfo;
	
	List<? extends TypeMirror> typeArgs;

	public List<? extends TypeMirror> getTypeArgs() {
		return typeArgs;
	}

	public class XmlInfo {
		public String label;
	}

}
