/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

import com.abubusoft.kripton.processor.core.AnnotationAttributeType;


/**
 * The Class FindSqlTypeAdapterVisitor.
 */
public class FindSqlTypeAdapterVisitor extends SimpleAnnotationValueVisitor7<Void, String> {

	/** The in adapter. */
	boolean inAdapter = false;
	
	/** The adapters. */
	List<String> adapters=new ArrayList<String>();  

	/**
	 * Gets the adapters.
	 *
	 * @return the adapters
	 */
	public List<String> getAdapters() {
		return adapters;
	}

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleAnnotationValueVisitor6#visitAnnotation(javax.lang.model.element.AnnotationMirror, java.lang.Object)
	 */
	@Override
	public Void visitAnnotation(AnnotationMirror a, String p) {
		if (AnnotationAttributeType.TYPE_ADAPTERS.getValue().equals(p)) {
			inAdapter = true;
		}

		for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : a.getElementValues().entrySet()) {
			String key = entry.getKey().getSimpleName().toString();
			entry.getValue().accept(this, key);
		}

		if (AnnotationAttributeType.TYPE_ADAPTERS.getValue().equals(p)) {
			inAdapter = false;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleAnnotationValueVisitor6#visitType(javax.lang.model.type.TypeMirror, java.lang.Object)
	 */
	@Override
	public Void visitType(TypeMirror t, String p) {
		//System.out.printf(">> %s classValue: %s\n", p, t.toString());
			
		if (inAdapter && AnnotationAttributeType.ADAPTER.getValue().equals(p)) {
			// add value			
			adapters.add(t.toString());			
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleAnnotationValueVisitor6#visitArray(java.util.List, java.lang.Object)
	 */
	@Override
	public Void visitArray(List<? extends AnnotationValue> vals, String p) {
		for (AnnotationValue val : vals) {
			val.accept(this, p);
		}
		return null;
	}
}
