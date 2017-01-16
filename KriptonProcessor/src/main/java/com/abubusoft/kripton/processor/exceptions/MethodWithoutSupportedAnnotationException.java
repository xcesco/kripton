/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
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

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;

public class MethodWithoutSupportedAnnotationException extends KriptonProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public MethodWithoutSupportedAnnotationException(SQLDaoDefinition daoDefinition, ModelMethod method) {
		super("Method '" + method.getName() + "' of DAO '" + daoDefinition.getName() + "' is not marked with any valid annotation (" +BindSqlInsert.class.getSimpleName() + ", "
				+ BindSqlUpdate.class.getSimpleName() + ", " + BindSqlDelete.class.getSimpleName() + ", " + BindSqlSelect.class.getSimpleName() + ", "
				+ ")");
	}
}
