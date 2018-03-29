package sqlite.kripton186.persistence;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.orm.ConflictAlgorithmType;

public interface AbstractDao<E> {

    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
    int insert(E bean);

    @BindSqlSelect(where = "id = ${id}")
    E selectById(long id);

    @BindSqlDelete(where ="id = ${id}")
    boolean deleteById(long id);


}
