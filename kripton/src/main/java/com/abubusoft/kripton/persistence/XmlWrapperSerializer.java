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

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.xml.XMLSerializer;

// TODO: Auto-generated Javadoc
/**
 * The Class XmlWrapperSerializer.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class XmlWrapperSerializer implements SerializerWrapper {

	/** The xml serializer. */
	public XMLSerializer xmlSerializer;
	
	/**
	 * Instantiates a new xml wrapper serializer.
	 *
	 * @param xmlSerializer the xml serializer
	 */
	public XmlWrapperSerializer(XMLSerializer xmlSerializer) {
		this.xmlSerializer = xmlSerializer;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.persistence.SerializerWrapper#close()
	 */
	@Override
	public void close() {
		try {
			xmlSerializer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

}
