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
package com.abubusoft.kripton.android.sqlite;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

/**
 * <p>
 * Extracts from an content provider URI, its path segments, after check that URI has
 * right lenght, just to prevent URI manipulation. An example of URI is:
 * </p>
 * 
 * <pre>
 * content://androi.authority/masters/${master}/details/${detail}/subdetails/${subdetail}
 * </pre>
 *
 * <p>
 * With param value:
 * </p>
 * 
 * <pre>
 * content://androi.authority/masters/1/details/2/subdetails/3
 * </pre>
 * 
 * <p>Path segment index start from 0, so index of parameters are:</p>
 * 
 * <ul>
 * 		<li><b>master</b>: 1</li>
 * 		<li><b>detail</b>: 3</li>
 * 		<li><b>subdetail</b>: 5</li>
 * </ul>
 * 
 */
public class ContentProviderURIParamsExtractor {
	private String[] array;

	/**
	 * <p>
	 * Split an URI and check its size in terms of segment path.
	 * </p>
	 * 
	 * @param uri
	 * @param expectedPathSegments
	 */
	public ContentProviderURIParamsExtractor(String uri, int expectedPathSegments) {
		array = uri.split("/");

		if (array.length != expectedPathSegments) {
			throw (new KriptonRuntimeException(String.format("Uri '%s' has a wrong path size", uri)));
		}
	}

	/**
	 * <p>Extract a specific segment by its index.</p>
	 * 
	 * <p>Path segment index start from 0.</p>
	 * 
	 * @param segmentIndex
	 * @return
	 */
	public String getPathSegment(int segmentIndex) {
		return array[3 + segmentIndex];
	}

}