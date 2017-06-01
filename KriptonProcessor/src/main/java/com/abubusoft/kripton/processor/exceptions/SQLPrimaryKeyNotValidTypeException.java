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
package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;

public class SQLPrimaryKeyNotValidTypeException extends KriptonProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public SQLPrimaryKeyNotValidTypeException(SQLEntity entity, ModelProperty property) {
		super("Bean '" + entity.getName() + "' have " + property.getName() + " as primary key but it is not Long or long type field");
	}
}
