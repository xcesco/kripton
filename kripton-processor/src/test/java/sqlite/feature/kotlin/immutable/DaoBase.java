package sqlite.feature.kotlin.immutable;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

public interface DaoBase<E> {
    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.IGNORE)
    boolean insert(E bean);
}
