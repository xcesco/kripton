/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton;

import java.io.InputStream;
import java.io.Reader;

import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;


/**
 * BinderReader read or deserialize XML or JSON into POJO guided by schema.
 * 
 * @author bulldog
 *
 */
public interface BinderReader {
	
	/**
	 * Read or deserialize an input stream into a POJO of specific type.
	 * 
	 * @param type target type
	 * @param source an input stream
	 * @return POJO of target type
	 * @throws ReaderException if reads fail
	 */
	public <T> T read(Class<? extends T>type, InputStream source) throws ReaderException, MappingException;
	
	/**
	 * Read or deserialize an string into a POJO of specific type. 
	 * 
	 * @param type target type
	 * @param source a string
	 * @return POJO of target type
	 * @throws ReaderException if reads fail
	 */
	public <T> T read(Class<? extends T>type, String source) throws ReaderException, MappingException;
	
	/**
	 * Read or deserialize a character stream reader into a POJO of specific type.
	 * 
	 * @param type target type
	 * @param source a character stream reader
	 * @return POJO of target type
	 * @throws ReaderException if reads fail
	 */
	public <T> T read(Class<? extends T>type, Reader source) throws ReaderException, MappingException;

}
