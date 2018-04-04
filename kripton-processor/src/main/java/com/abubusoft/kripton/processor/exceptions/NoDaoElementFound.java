/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
/**
 * 
 */
package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.ClassName;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class NoDaoElementFound extends KriptonProcessorException {

	public NoDaoElementFound() {
		super(String.format("No bean with @%s annotation is present in to-compile sources", BindDao.class.getSimpleName().toString()));
	}
	
	public NoDaoElementFound(SQLiteDatabaseSchema schema, ClassName entityClassName) {
		super(String.format("No DAO definition found in data set '%s' for entity '%s'", schema.getElement().toString(), entityClassName.toString() ));
	}

	private static final long serialVersionUID = 1L;

}
