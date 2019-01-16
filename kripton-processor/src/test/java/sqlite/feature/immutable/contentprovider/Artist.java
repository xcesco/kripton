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
package sqlite.feature.immutable.contentprovider;

import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by xcesco on 02/10/2017.
 */
@BindType
@BindSqlType
public class Artist extends Entity {
	
	/** The name. */
	private String name;

	/**
	 * @param id
	 * @param name
	 */
	public Artist(long id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
