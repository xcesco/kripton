/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package bind.feature.typeAdapter.kripton87;

import java.net.MalformedURLException;
import java.net.URL;

import com.abubusoft.kripton.TypeAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class StringUrlTypeAdapter.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class StringUrlTypeAdapter implements TypeAdapter<String, URL> {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toJava(java.lang.Object)
	 */
	@Override
	public String toJava(URL dataValue) {
		return dataValue.toExternalForm();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toData(java.lang.Object)
	 */
	@Override
	public URL toData(String javaValue) {
		try {
			return new URL(javaValue);
		} catch (MalformedURLException e) {
			throw(new RuntimeException(e));
		}
	}


}
