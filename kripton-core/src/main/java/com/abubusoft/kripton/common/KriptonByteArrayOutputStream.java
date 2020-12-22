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
package com.abubusoft.kripton.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.abubusoft.kripton.exception.KriptonRuntimeException;


/**
 * Acts as a ByteArrayOutputStream, but allow direct access to array of byte
 * inside, without copy it.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonByteArrayOutputStream extends ByteArrayOutputStream {
	
	/**
	 * Instantiates a new kripton byte array output stream.
	 */
	public KriptonByteArrayOutputStream() {
	}

	/**
	 * Instantiates a new kripton byte array output stream.
	 *
	 * @param size the size
	 */
	public KriptonByteArrayOutputStream(int size) {
		super(size);
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Gets the byte buffer.
	 *
	 * @return the byte buffer
	 */
	public byte[] getByteBuffer() {
		return buf;
	}

	/**
	 * Gets the byte buffer copy.
	 *
	 * @return the byte buffer copy
	 */
	public byte[] getByteBufferCopy() {
		return Arrays.copyOf(buf, count);
	}

	/* (non-Javadoc)
	 * @see java.io.ByteArrayOutputStream#close()
	 */
	@Override
	public void close() {
		try {
			super.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}
	}
}
