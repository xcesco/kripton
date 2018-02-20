package sqlite.feature.join.model;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;

/**
 * Created by xcesco on 20/02/2018.
 */

public interface DaoBase<E extends Entity> {
    @BindSqlInsert
    void insert(E entity);
}
