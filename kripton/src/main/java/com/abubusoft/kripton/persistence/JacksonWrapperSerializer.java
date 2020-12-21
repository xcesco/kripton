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
package com.abubusoft.kripton.persistence;

import java.io.IOException;

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;


/**
 * The Class JacksonWrapperSerializer.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class JacksonWrapperSerializer implements SerializerWrapper {
	
	/** The jackson generator. */
	public JsonGenerator jacksonGenerator;

	/**
	 * Instantiates a new jackson wrapper serializer.
	 *
	 * @param jacksonSerializer the jackson serializer
	 * @param supportedFormat the supported format
	 */
	public JacksonWrapperSerializer(JsonGenerator jacksonSerializer, BinderType supportedFormat) {
		this.jacksonGenerator = jacksonSerializer;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.persistence.SerializerWrapper#close()
	 */
	@Override
	public void close() {
		try {
			if (!jacksonGenerator.isClosed())
				jacksonGenerator.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e));
		}
		
	}
}
