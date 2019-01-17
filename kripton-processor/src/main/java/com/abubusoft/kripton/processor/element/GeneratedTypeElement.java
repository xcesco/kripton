/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.processor.element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.Finder;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * The Class GeneratedTypeElement.
 */
public class GeneratedTypeElement implements Finder<SQLProperty> {

	/** The package name. */
	public String packageName;

	/** The type spec. */
	public TypeSpec typeSpec;

	/** The table name. */
	protected String tableName;

	/** The refered entities. */
	public Set<SQLiteEntity> referedEntities = new HashSet<>();

	/** The index. */
	public List<String> index = new ArrayList<>();

	/** The properties. */
	public List<SQLProperty> properties = new ArrayList<SQLProperty>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.core.Finder#getTableName()
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Instantiates a new generated type element.
	 *
	 * @param packageName
	 *            the package name
	 * @param typeSpec
	 *            the type spec
	 * @param tableName
	 *            the table name
	 * @param index
	 *            the index
	 */
	public GeneratedTypeElement(String packageName, TypeSpec typeSpec, String tableName, String index) {
		this.packageName = packageName;
		this.typeSpec = typeSpec;
		this.tableName = tableName;

		if (StringUtils.hasText(index)) {
			this.index.add(index);
		}
	}

	/**
	 * Gets the qualified name.
	 *
	 * @return the qualified name
	 */
	public String getQualifiedName() {
		if (StringUtils.hasText(packageName)) {
			return packageName + "." + typeSpec.name;
		}

		return typeSpec.name;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return getQualifiedName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.core.Finder#getSimpleName()
	 */
	public String getSimpleName() {
		return typeSpec.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.core.Finder#getCollection()
	 */
	public List<SQLProperty> getCollection() {
		return properties;
	}

	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public TypeName getClassName() {
		return TypeUtility.className(getQualifiedName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.core.Finder#findPropertyByName(java.lang.
	 * String)
	 */
	@Override
	public SQLProperty findPropertyByName(String name) {
		name = name.toLowerCase();
		for (SQLProperty item : properties) {
			if (item.getName().toLowerCase().equals(name)) {
				return item;
			}
		}

		return null;
	}


}
