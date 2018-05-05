/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.join.model;

import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * Created by xcesco on 20/02/2018.
 */
@BindTable
public class Loan extends Entity {
    
    /** The start time. */
    public Date startTime;

    /** The end time. */
    public Date endTime;

    /** The book id. */
    @BindSqlColumn(parentEntity = Book.class)
    public long bookId;

    /** The user id. */
    @BindSqlColumn(parentEntity = User.class)
    public long userId;

}
