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

import java.io.InputStream;
import java.io.Reader;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.xml.XMLParser;
import com.fasterxml.jackson.core.JsonEncoding;


/**
 * The Class XmlWrapperParser.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class XmlWrapperParser implements ParserWrapper {
	
	/** The xml parser. */
	public XMLParser xmlParser;

	/**
	 * Instantiates a new xml wrapper parser.
	 *
	 * @param context the context
	 * @param inputStream the input stream
	 * @param supportedFormat the supported format
	 */
	public XmlWrapperParser(BinderContext context, InputStream inputStream, BinderType supportedFormat) {
		this.xmlParser = new XMLParser();
		this.xmlParser.setInput(inputStream, JsonEncoding.UTF8.getJavaName());
	}

	/**
	 * Instantiates a new xml wrapper parser.
	 *
	 * @param context the context
	 * @param reader the reader
	 * @param supportedFormat the supported format
	 */
	public XmlWrapperParser(BinderContext context, Reader reader, BinderType supportedFormat) {
		this.xmlParser = new XMLParser();
		this.xmlParser.setInput(reader);
	}

	@Override
	public boolean hasMoreToken() {
		return this.xmlParser.hasNext();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.persistence.ParserWrapper#close()
	 */
	@Override
	public void close() {
		try {
			xmlParser.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}

	}

}
