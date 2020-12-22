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
package com.abubusoft.kripton;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

/**
 * Context implementation for Jackson derived mapping context.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class AbstractJacksonContext extends AbstractContext {

	/** inner factory. */
	public JsonFactory innerFactory;

	/**
	 * constructor.
	 */
	public AbstractJacksonContext() {
		innerFactory = createInnerFactory();
	}

	/**
	 * create a factory to build inner factory.
	 *
	 * @return the json factory
	 */
	protected abstract JsonFactory createInnerFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.AbstractContext#createParser(byte[])
	 */
	@Override
	public JacksonWrapperParser createParser(byte[] data) {
		return createParser(new ByteArrayInputStream(data));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.AbstractContext#createParser(java.io.File)
	 */
	@Override
	public JacksonWrapperParser createParser(File file) {
		try {
			return new JacksonWrapperParser(innerFactory.createParser(file), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.AbstractContext#createParser(java.io.InputStream)
	 */
	@Override
	public JacksonWrapperParser createParser(InputStream in) {
		try {
			return new JacksonWrapperParser(innerFactory.createParser(in), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.AbstractContext#createParser(java.io.Reader)
	 */
	@Override
	public JacksonWrapperParser createParser(Reader reader) {
		try {
			return new JacksonWrapperParser(innerFactory.createParser(reader), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.AbstractContext#createParser(java.lang.String)
	 */
	@Override
	public JacksonWrapperParser createParser(String content) {
		try {
			return new JacksonWrapperParser(innerFactory.createParser(content), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.AbstractContext#createSerializer(java.io.File)
	 */
	@Override
	public JacksonWrapperSerializer createSerializer(File file) {
		return createSerializer(file, JsonEncoding.UTF8);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.AbstractContext#createSerializer(java.io.File,
	 * com.fasterxml.jackson.core.JsonEncoding)
	 */
	@Override
	public JacksonWrapperSerializer createSerializer(File file, JsonEncoding encoding) {
		try {
			return new JacksonWrapperSerializer(innerFactory.createGenerator(file, encoding), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.AbstractContext#createSerializer(java.io.OutputStream)
	 */
	@Override
	public JacksonWrapperSerializer createSerializer(OutputStream out) {
		return createSerializer(out, JsonEncoding.UTF8);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.AbstractContext#createSerializer(java.io.OutputStream,
	 * com.fasterxml.jackson.core.JsonEncoding)
	 */
	@Override
	public JacksonWrapperSerializer createSerializer(OutputStream out, JsonEncoding encoding) {
		try {
			return new JacksonWrapperSerializer(innerFactory.createGenerator(out, encoding), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.AbstractContext#createSerializer(java.io.Writer)
	 */
	@Override
	public JacksonWrapperSerializer createSerializer(Writer writer) {
		try {
			return new JacksonWrapperSerializer(innerFactory.createGenerator(writer), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

}
