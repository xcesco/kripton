package sqlite.feature.many2many;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

/**
 * <p>
 * Entity implementation for entity <code>Person2City</code>
 * </p>
 */
@BindType
@BindTable(
    name = "Person2City"
)
public class Person2City {
  /**
   * Primary key
   */
  @BindColumn(columnType = ColumnType.PRIMARY_KEY)
  public long id;
}
