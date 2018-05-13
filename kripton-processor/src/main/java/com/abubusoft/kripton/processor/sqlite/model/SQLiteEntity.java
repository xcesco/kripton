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
package com.abubusoft.kripton.processor.sqlite.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.Finder;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.Touple;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;

/**
 * The Class SQLiteEntity.
 */
public class SQLiteEntity extends ModelClass<SQLProperty> implements Finder<SQLProperty> {

	/** The table name. */
	private String tableName;

	/**
	 * Set of entities for which there's a foreign in this entities. In other
	 * words, represents entities from which this entity depends.
	 */
	public Set<SQLiteEntity> referedEntities = new HashSet<>();

	/** The schema. */
	public SQLiteDatabaseSchema schema;

	/**
	 * Set of relation field declared.
	 * <ol>
	 * <li>name of property in parent entity</li>
	 * <li>property used as relation. This property is not referenced in the
	 * property set.</li>
	 * <li>child entity</li>
	 * <li>relation type</li>
	 * </ol>
	 * 
	 */
	public List<Touple<SQLProperty, String, SQLiteEntity, SQLRelationType>> relations = new ArrayList<Touple<SQLProperty, String, SQLiteEntity, SQLRelationType>>();

	/**
	 * Instantiates a new SQ lite entity.
	 *
	 * @param schema
	 *            the schema
	 * @param bindEntity
	 *            the bind entity
	 */
	public SQLiteEntity(SQLiteDatabaseSchema schema, BindEntity bindEntity) {
		super(bindEntity.getElement());

		this.annotations = bindEntity.getAnnotations();
		this.schema = schema;

		buildTableName(BaseProcessor.elementUtils, schema);
	}

	/**
	 * Check how many PK are defined in entity. Only one field can be PK.
	 * 
	 * @return number of PK
	 */
	public int countPrimaryKeys() {
		int countAnnotation = 0;

		for (SQLProperty item : collection) {
			if (item.isPrimaryKey()) {
				countAnnotation++;
			}
		}

		return countAnnotation;
	}

	/**
	 * True if there is a primary key.
	 *
	 * @return true if there is a primary key
	 */
	public SQLProperty getPrimaryKey() {
		for (SQLProperty item : collection) {
			if (item.isPrimaryKey()) {
				return item;
			}
		}

		// try to get id
		SQLProperty id = findPropertyByName("id");

		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.core.Finder#getTableName()
	 */
	@Override
	public String getTableName() {
		return tableName;
	}

	/**
	 * Builds the table name.
	 *
	 * @param elementUtils
	 *            the element utils
	 * @param schema
	 *            the schema
	 * @return the string
	 */
	private String buildTableName(Elements elementUtils, SQLiteDatabaseSchema schema) {
		tableName = getSimpleName();
		tableName = schema.classNameConverter.convert(tableName);

		String temp = AnnotationUtility.extractAsString(getElement(), BindSqlType.class, AnnotationAttributeType.NAME);
		if (StringUtils.hasText(temp)) {
			tableName = temp;
		}

		return tableName;

	}

	/**
	 * find a relation specifing parent field name, that is the name of the
	 * relation
	 * 
	 * @param parentFieldName
	 * @return
	 */
	public Touple<SQLProperty, String, SQLiteEntity, SQLRelationType> findRelationByParentProperty(
			String parentFieldName) {
		for (Touple<SQLProperty, String, SQLiteEntity, SQLRelationType> item : relations) {
			if (item.value0.getName().equals(parentFieldName)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * <p>Given an entity, find all field that are foreign key to entity.</p>
	 * 
	 * 
	 * @param entity
	 *            referred entity
	 * @param fieldName
	 * @return list of foreign key o empty list
	 */
	public List<SQLProperty> getForeignKeysToEntity(SQLiteEntity entity, String fieldName) {
		List<SQLProperty> result = new ArrayList<>();
		if (StringUtils.hasText(fieldName)) {
			for (SQLProperty item : this.collection) {
				if (item.isForeignKey() && entity.getName().equals(item.foreignParentClassName)
						&& item.getName().equals(fieldName)) {
					result.add(item);
				}
			}
		} else {
			for (SQLProperty item : this.collection) {
				if (item.isForeignKey() && entity.getName().equals(item.foreignParentClassName)) {
					result.add(item);
				}
			}
		}
		return result;
	}

}
