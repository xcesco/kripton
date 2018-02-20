package sqlite.feature.join.model;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

/**
 * Created by xcesco on 20/02/2018.
 */
@BindDao(Book.class)
public interface BookDao extends DaoBase<Book> {

    @BindSqlSelect(jql="SELECT * FROM Book " +
            "INNER JOIN Loan ON Loan.bookId == Book.id " +
            "WHERE Loan.userId == ${userId} "
    )
    List<Book> findBooksBorrowedByUser(long userId);
}
