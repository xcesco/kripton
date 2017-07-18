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
package sqlite.kripton93;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

@BindDao(Bean93.class)
public interface Bean93Dao {
	
	@BindSqlSelect(where="typeName like ${name} || '%'")
	Bean93 selectByBean(String name);
	
	@BindSqlSelect
	List<Bean93> selectAll();

	@BindSqlInsert
	boolean insertDefault(Bean93 bean);
	
	@BindSqlInsert(conflictAlgorithm=ConflictAlgorithmType.ABORT, includePrimaryKey=true)
	boolean insertAbort(Bean93 bean);
	
	@BindSqlInsert(conflictAlgorithm=ConflictAlgorithmType.FAIL, includePrimaryKey=true)
	boolean insertFail(Bean93 bean);
	
	@BindSqlInsert(conflictAlgorithm=ConflictAlgorithmType.IGNORE, includePrimaryKey=true)
	boolean insertIgnore(Bean93 bean);
	
	@BindSqlInsert(conflictAlgorithm=ConflictAlgorithmType.REPLACE, includePrimaryKey=true)
	boolean insertReplace(Bean93 bean);
	
	@BindSqlInsert(conflictAlgorithm=ConflictAlgorithmType.ROLLBACK, includePrimaryKey=true)
	boolean insertRollback(Bean93 bean);
}