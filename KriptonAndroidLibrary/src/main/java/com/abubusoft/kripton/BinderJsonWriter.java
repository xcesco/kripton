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
import java.util.Collection;
import java.util.Map;

import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;


/**
 * BinderWriter write or serialize POJO into XML or JSON format guided by schema.
 * 
 * @author xcesco
 *
 */
public interface BinderJsonWriter extends BinderWriter {
	
	public void writeCollection(Collection<?> source, Writer out) throws WriterException, MappingException;
	
	public void writeCollection(Collection<?> source, OutputStream os) throws WriterException, MappingException;
	
	public String writeCollection(Collection<?> source) throws WriterException, MappingException;
	
	public void writeMap(Map<?, ?> source, Writer out) throws WriterException, MappingException;
	
	public void writeMap(Map<?, ?> source, OutputStream os) throws WriterException, MappingException;
	
	public String writeMap(Map<?, ?> source) throws WriterException, MappingException;

}
