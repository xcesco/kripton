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
package com.abubusoft.kripton.processor.bind.model;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.TypeName;

/**
 * The Class BindModel.
 */
public class BindModel {
	
	/** The entities. */
	protected List<BindEntity> entities = new ArrayList<BindEntity>();

	/**
	 * Gets the entities.
	 *
	 * @return the entities
	 */
	public List<BindEntity> getEntities() {
		return entities;
	}

	/**
	 * Entity add.
	 *
	 * @param item the item
	 */
	public void entityAdd(BindEntity item) {
		entities.add(item);
	}

	/**
	 * Check if type element is present in set of class 
	 * @param typeName
	 * @return
	 */
	public boolean hasEntityOfType(TypeName typeName) {
		for (BindEntity item: entities) {
			if (TypeUtility.isEquals(typeName, item.getElement().asType().toString())) {
				return true;
			}
		}
		
		return false;
	}

}
