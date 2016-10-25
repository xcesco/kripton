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

import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;


/**
 * BinderWriter write or serialize POJO into XML or JSON format guided by schema.
 * 
 * @author xcesco
 *
 */
public interface BinderJsonWriter extends BinderWriter {
	
	/**
	 * 
	 * @param source an POJO
	 * @param out a character stream
	 * @throws WriterException if writes fail
	 */
	public void writeList(@SuppressWarnings("rawtypes") List source, Writer out) throws WriterException, MappingException;
	
	/**
	 * 
	 * @param source an POJO
	 * @param os an output stream of bytes.
	 * @throws WriterException if writes fail
	 */
	public void writeList(@SuppressWarnings("rawtypes") List source, OutputStream os) throws WriterException, MappingException;
	
	/**
	 * 
	 * @param source an POJO
	 * @return a string representation
	 * @throws WriterException if writes fail
	 */
	public String writeList(@SuppressWarnings("rawtypes") List source) throws WriterException, MappingException;
	

}
