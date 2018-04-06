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
package com.abubusoft.kripton.map;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

// TODO: Auto-generated Javadoc
/**
 * The Class BindMapVisitor.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class BindMapVisitor {

	/**
	 * The Enum VisitorStatusType.
	 *
	 * @author Francesco Benincasa (info@abubusoft.com)
	 */
	public static enum VisitorStatusType {
		
		/** The run. */
		RUN, 
		
		/** The stop. */
		STOP
	}

	/**
	 * Visit map.
	 *
	 * @param name the name
	 * @param map the map
	 * @param listener the listener
	 * @param status the status
	 * @return the visitor status type
	 */
	@SuppressWarnings("unchecked")
	static VisitorStatusType visitMap(String name, Map<String, Object> map, BindMapListener listener, VisitorStatusType status) {
		for (Entry<String, Object> item : map.entrySet()) {
			if (item.getValue() == null || item.getValue() instanceof String) {
				visit(item.getKey(), (String) item.getValue(), listener, status);
			} else if (item.getValue() instanceof List) {
				visitList(item.getKey(), (List<Object>) item.getValue(), listener, status);
			} else if (item.getValue() instanceof Map) {
				visitMap(item.getKey(), (Map<String, Object>) item.getValue(), listener, status);
			}

			if (status == VisitorStatusType.STOP)
				return status;
		}

		return status;
	}

	/**
	 * Visit list.
	 *
	 * @param name the name
	 * @param list the list
	 * @param listener the listener
	 * @param status the status
	 * @return the visitor status type
	 */
	@SuppressWarnings("unchecked")
	static VisitorStatusType visitList(String name, List<Object> list, BindMapListener listener, VisitorStatusType status) {
		int i = 0;
		for (Object item : list) {
			if (item == null || item instanceof String) {
				visit(name + "." + i, (String) item, listener, status);
			} else if (item instanceof List) {
				visitList(name + "." + i, (List<Object>) item, listener, status);
			} else if (item instanceof Map) {
				visitMap(name + "." + i, (Map<String, Object>) item, listener, status);
			}
			i++;

			if (status == VisitorStatusType.STOP)
				return status;
		}

		return status;
	}

	/**
	 * Visit.
	 *
	 * @param name the name
	 * @param value the value
	 * @param listener the listener
	 * @param status the status
	 */
	static void visit(String name, String value, BindMapListener listener, VisitorStatusType status) {
		listener.onField(name, value, status);
	}

	/**
	 * Execute.
	 *
	 * @param map the map
	 * @param listener the listener
	 */
	@SuppressWarnings("unchecked")
	public static void execute(Map<String, Object> map, BindMapListener listener) {
		if (listener == null)
			throw (new KriptonRuntimeException("listener can not be null"));
		if (map == null)
			throw (new KriptonRuntimeException("map can not be null"));

		VisitorStatusType status = VisitorStatusType.RUN;

		for (Entry<String, Object> item : map.entrySet()) {
			if (item.getValue() == null || item.getValue() instanceof String) {
				visit(item.getKey(), (String) item.getValue(), listener, status);
			} else if (item.getValue() instanceof List) {
				visitList(item.getKey(), (List<Object>) item.getValue(), listener, status);
			} else if (item.getValue() instanceof Map) {
				visitMap(item.getKey(), (Map<String, Object>) item.getValue(), listener, status);
			}

			if (status == VisitorStatusType.STOP)
				return;

		}

	}

}
