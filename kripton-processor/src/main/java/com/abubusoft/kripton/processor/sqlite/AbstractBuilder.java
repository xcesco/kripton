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
package com.abubusoft.kripton.processor.sqlite;

import javax.annotation.processing.Filer;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * The Class AbstractBuilder.
 */
public abstract class AbstractBuilder {
	
	/** The element utils. */
	protected Elements elementUtils;
	
	/** The filer. */
	protected Filer filer;
	
	/** The class builder. */
	protected Builder classBuilder;
	
	/** The model. */
	protected SQLiteDatabaseSchema model;
	
	/**
	 * Instantiates a new abstract builder.
	 *
	 * @param elementUtils the element utils
	 * @param filer the filer
	 * @param model the model
	 */
	public AbstractBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		this.model=model;
		this.elementUtils = elementUtils;
		this.filer = filer;
		
	}
		
}
