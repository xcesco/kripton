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
package commons.kripton86.test7;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.annotation.BindType;


/**
 * The Class Bean7.
 */
@BindType
public class Bean7 {

	/** The id. */
	@BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;
	
	/** The ida. */
	@BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
	public long ida;
	
	/** The test. */
	public String test;
	
	
}
