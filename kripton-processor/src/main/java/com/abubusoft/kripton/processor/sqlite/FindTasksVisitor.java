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

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;

/**
 * The Class FindTasksVisitor.
 */
public class FindTasksVisitor extends SimpleAnnotationValueVisitor7<Void, String> {

	/** The in tasks. */
	boolean inTasks = false;

	/** The current value. */
	Pair<Integer, String> currentValue;
	
	/** The tasks. */
	ArrayList<Pair<Integer, String>> tasks=new ArrayList<Pair<Integer, String>>();  

	/**
	 * Gets the tasks.
	 *
	 * @return the tasks
	 */
	public ArrayList<Pair<Integer, String>> getTasks() {
		return tasks;
	}

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleAnnotationValueVisitor6#visitInt(int, java.lang.Object)
	 */
	@Override
	public Void visitInt(int i, String p) {
		//System.out.printf(">> %s intValue: %d\n", p, i);

		if (inTasks && AnnotationAttributeType.VERSION.getValue().equals(p)) {
			// add value
			currentValue = (currentValue == null) ? new Pair<Integer, String>() : currentValue;			
			currentValue.value0=i;
			
			if (currentValue.value0!=null && StringUtils.hasText(currentValue.value1)) {
				tasks.add(currentValue);
				currentValue=null;
			}
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleAnnotationValueVisitor6#visitAnnotation(javax.lang.model.element.AnnotationMirror, java.lang.Object)
	 */
	@Override
	public Void visitAnnotation(AnnotationMirror a, String p) {
		if (AnnotationAttributeType.UPDATE_TASKS.getValue().equals(p)) {
			inTasks = true;
		}

		for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : a.getElementValues().entrySet()) {
			String key = entry.getKey().getSimpleName().toString();
			entry.getValue().accept(this, key);
		}

		if (AnnotationAttributeType.UPDATE_TASKS.getValue().equals(p)) {
			inTasks = false;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleAnnotationValueVisitor6#visitType(javax.lang.model.type.TypeMirror, java.lang.Object)
	 */
	@Override
	public Void visitType(TypeMirror t, String p) {
		//System.out.printf(">> %s classValue: %s\n", p, t.toString());
			
		if (inTasks && AnnotationAttributeType.TASK.getValue().equals(p)) {
			// add value
			currentValue = (currentValue == null) ? new Pair<Integer, String>() : currentValue;
			
			currentValue.value1=t.toString();
			
			if (currentValue.value0!=null && StringUtils.hasText(currentValue.value1)) {
				tasks.add(currentValue);
				currentValue=null;
			}
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
