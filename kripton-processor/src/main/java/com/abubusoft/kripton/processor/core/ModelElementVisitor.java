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
package com.abubusoft.kripton.processor.core;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;

// TODO: Auto-generated Javadoc
/**
 * The Interface ModelElementVisitor.
 *
 * @param <T> the generic type
 * @param <P> the generic type
 */
public interface ModelElementVisitor<T extends ModelClass<P>, P extends ModelProperty> {
	
	/**
	 * Visit.
	 *
	 * @param schema the schema
	 * @param entity the entity
	 * @throws Exception the exception
	 */
	void visit(SQLiteDatabaseSchema schema, T entity) throws Exception;

	/**
	 * Visit.
	 *
	 * @param property the property
	 * @throws Exception the exception
	 */
	void visit(P property) throws Exception;	
}