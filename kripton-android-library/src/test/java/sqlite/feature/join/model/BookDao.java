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
package sqlite.feature.join.model;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Created by xcesco on 20/02/2018.
 */
@BindDao(Book.class)
public interface BookDao extends DaoBase<Book> {

    /**
     * Find books borrowed by user.
     *
     * @param userId the user id
     * @return the list
     */
    @BindSqlSelect(jql="SELECT * FROM Book " +
            "INNER JOIN Loan ON Loan.bookId == Book.id " +
            "WHERE Loan.userId == ${userId} "
    )
    List<Book> findBooksBorrowedByUser(long userId);
}
