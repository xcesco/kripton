/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;

public class FindIndexesVisitor extends SimpleAnnotationValueVisitor7<Void, String> {

	boolean inTasks = false;

	Pair<List<String>, Boolean> currentValue;

	/** The tasks. */
	ArrayList<Pair<List<String>, Boolean>> indexes = new ArrayList<Pair<List<String>, Boolean>>();

	/**
	 * Gets the tasks.
	 *
	 * @return the tasks
	 */
	public ArrayList<Pair<List<String>, Boolean>> getAllIndexes() {
		return indexes;
	}

	@Override
	public Void visitBoolean(boolean b, String p) {
		if (inTasks && AnnotationAttributeType.UNIQUE.getValue().equals(p)) {
			// add value
			currentValue.value1 = b;
		}

		return null;
	}

	public ArrayList<Pair<List<String>, Boolean>> getUniqueIndexes() {
		return getIndexes(true);
	}

	public ArrayList<Pair<List<String>, Boolean>> getNotUniqueIndexes() {
		return getIndexes(false);
	}

	protected ArrayList<Pair<List<String>, Boolean>> getIndexes(boolean unique) {
		ArrayList<Pair<List<String>, Boolean>> result = new ArrayList<Pair<List<String>, Boolean>>();

		for (Pair<List<String>, Boolean> item : indexes) {
			if (item.value1 == unique) {
				result.add(item);
			}

		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.lang.model.util.SimpleAnnotationValueVisitor6#visitString(java.lang
	 * .String, java.lang.Object)
	 */
	@Override
	public Void visitString(String s, String p) {
		if (inTasks && AnnotationAttributeType.VALUE.getValue().equals(p)) {
			currentValue.value0.add(s);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.lang.model.util.SimpleAnnotationValueVisitor6#visitAnnotation(javax
	 * .lang.model.element.AnnotationMirror, java.lang.Object)
	 */
	@Override
	public Void visitAnnotation(AnnotationMirror a, String p) {
		if (AnnotationAttributeType.INDEXES.getValue().equals(p)) {
			inTasks = true;
		}

		if (inTasks) {
			currentValue = new Pair<List<String>, Boolean>(new ArrayList<String>(), false);
			for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : a.getElementValues().entrySet()) {
				String key = entry.getKey().getSimpleName().toString();
				entry.getValue().accept(this, key);
			}
			indexes.add(currentValue);
		}

		if (AnnotationAttributeType.INDEXES.getValue().equals(p)) {
			inTasks = false;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.lang.model.util.SimpleAnnotationValueVisitor6#visitArray(java.util.
	 * List, java.lang.Object)
	 */
	@Override
	public Void visitArray(List<? extends AnnotationValue> vals, String p) {
		for (AnnotationValue val : vals) {
			val.accept(this, p);
		}
		return null;
	}
}
