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
package bind.feature.typeadapter.kripton87;

import java.net.MalformedURLException;
import java.net.URL;

import com.abubusoft.kripton.TypeAdapter;


/**
 * The Class UrlByteArrayTypeAdapter.
 */
public class UrlByteArrayTypeAdapter implements TypeAdapter<URL, byte[]> {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toJava(java.lang.Object)
	 */
	@Override
	public URL toJava(byte[] dataValue) {
		try {
			return new URL(new String(dataValue));
		} catch (MalformedURLException e) {
			throw(new RuntimeException(e));
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toData(java.lang.Object)
	 */
	@Override
	public byte[] toData(URL javaValue) {
		return javaValue.toExternalForm().getBytes();
	}
}
