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
package sqlite.feature.foreignKey;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(BeanA_1.class)
public interface DaoBeanA_1 {

	@BindSqlSelect
	List<BeanA_1> selectAll();
	
	@BindSqlSelect(where="id=${id}")
	List<BeanA_1> selectById(long id);
	
	@BindSqlSelect(fields="id",where="valueString=${dummy}")
	List<BeanA_1> selectByString(@BindSqlParam("dummy") String value);
	
	@BindSqlInsert
	int insert(BeanA_1 bean);
	
	@BindSqlUpdate(where="valueString=${bean.valueString}")
	int update(BeanA_1 bean);
}
