/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sqlite.feature.custombean.case2;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindIndex;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

import androidx.annotation.NonNull;

@BindSqlType(indexes = { @BindIndex(value = { "bookId", "userId" }, unique = true) })
public class Loan {
	// Fields can be public or private with getters and setters.
	@NonNull
	public String id;

	public Date startTime;

	public Date endTime;

	@BindSqlColumn(parentEntity = Book.class)
	public String bookId;

	@BindSqlColumn(parentEntity = User.class)
	public String userId;
}
