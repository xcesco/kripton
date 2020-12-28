package sqlite.feature.time.case1;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import java.util.List;

public interface DaoBase<T> {
  @BindSqlInsert
  int insert(T value);

  @BindSqlUpdate(where="year=:value.year")
  int update(T value);

  @BindSqlSelect(where="year=:value.year")
  List<T> select(T value);

  @BindSqlDelete(where="year=:value.year")
  int delete(T value);

}
