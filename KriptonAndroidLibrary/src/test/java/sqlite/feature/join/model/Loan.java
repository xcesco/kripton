package sqlite.feature.join.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

import java.sql.Date;

/**
 * Created by xcesco on 20/02/2018.
 */
@BindTable
public class Loan extends Entity {
    public Date startTime;

    public Date endTime;

    @BindColumn(foreignKey = Book.class)
    public long bookId;

    @BindColumn(foreignKey = User.class)
    public long userId;

}
