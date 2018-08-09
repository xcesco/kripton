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

package sqlite.feature.custombean.case1;


import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import android.arch.lifecycle.LiveData;

@BindDao(Loan.class)
public interface LoanDao {

    @BindSqlSelect
    LiveData<List<Loan>> findAllLoans();

    @BindSqlSelect(jql="SELECT Loan.id, Book.title, User.name, Loan.startTime, Loan.endTime From Loan " +
        "INNER JOIN Book ON Loan.bookId = Book.id " +
        "INNER JOIN User ON Loan.userId = User.id ", resultType=LoanWithUserAndBook.class)
    LiveData<List<LoanWithUserAndBook>> findAllWithUserAndBook();

/*
    @BindSqlSelect(jql="SELECT Loan.id, Book.title as title, User.name as name, Loan.startTime, Loan.endTime " +
            "FROM Book " +
            "INNER JOIN Loan ON Loan.book_id = Book.id " +
            "INNER JOIN User on User.id = Loan.userId " +
            "WHERE User.name LIKE :userName " +
            "AND Loan.endTime > :after "
    )
    LiveData<List<LoanWithUserAndBook>> findLoansByNameAfter(String userName, Date after);
*/
    @BindSqlInsert
    void insertLoan(Loan loan);

    @BindSqlDelete
    void deleteAll();
}
