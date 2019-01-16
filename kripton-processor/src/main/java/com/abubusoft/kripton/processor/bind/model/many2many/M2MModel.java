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
package com.abubusoft.kripton.processor.bind.model.many2many;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class M2MModel.
 */
public class M2MModel {

	/** The entities. */
	List<M2MEntity> entities=new ArrayList<M2MEntity>();

	/**
	 * Gets the entities.
	 *
	 * @return the entities
	 */
	public List<M2MEntity> getEntities() {
		return entities;
	}

	/**
	 * Entity add.
	 *
	 * @param entity the entity
	 */
	public void entityAdd(M2MEntity entity) {
		entities.add(entity);		
	}
		
	
	
}
