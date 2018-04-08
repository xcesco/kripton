/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.kripton56.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.kripton56.entities.MessageEntity;
import sqlite.kripton56.entities.OwnerType;

// TODO: Auto-generated Javadoc
/**
 * Created by xcesco on 27/09/2016.
 */

@BindDao(MessageEntity.class)
public interface DaoMessage {

	/**
	 * Update by id.
	 *
	 * @param id the id
	 * @param ownerType the owner type
	 * @return true, if successful
	 */
	@BindSqlUpdate(where=" id = ${id}")
	boolean updateById(long id, OwnerType ownerType);
}
