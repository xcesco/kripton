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
package com.abubusoft.kripton.processor.sqlite.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelBucket;

public class SQLiteDatabaseSchema extends ModelBucket<SQLDaoDefinition, TypeElement> {
	
	public Converter<String, String> classNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
	
	public Converter<String, String> columnNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

	protected Map<String, SQLEntity> entities=new HashMap<String, SQLEntity>();

	public String fileName;
	
	public String generatedClassName;

	/**
	 * @return the generatedClassName
	 */
	public String getGeneratedClassName() {
		return generatedClassName;
	}

	public int version;
	
	public boolean generateLog;

	public boolean generateAsyncTask;

	public boolean generateCursor;
	
	public SQLiteDatabaseSchema(TypeElement item, String schemaFileName, int schemaVersion, boolean log, boolean asyncTask, boolean generateCursor) {	
		super(item.getSimpleName().toString(), item);
		
		this.fileName=schemaFileName;
		this.version=schemaVersion;
		this.generateLog=log;
		this.generateAsyncTask=asyncTask;
		this.generateCursor=generateCursor;
		this.generatedClassName="Bind"+getName();
	}

	public void clear() {
		entities.clear();
	}

	public void addEntity(SQLEntity value) {
		entities.put(value.getName(), value);
	}

	public Collection<SQLEntity> getEntities() {
		return entities.values();
	}
	
	public SQLEntity getEntity(String entityClassName) {
		return entities.get(entityClassName);
	}

	public boolean isLogEnabled() {
		return generateLog;
	}

}
