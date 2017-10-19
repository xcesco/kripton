package sqlite.feature.many2many;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;

/**
 * <p>
 * Generated entity implementation for <code>PersonCity</code>
 * </p>
 */
@BindTable(
    name = "person_city"
)
public class PersonCity {
  /**
   * Primary key
   */
  @BindColumn(
      columnType = ColumnType.PRIMARY_KEY
  )
  public long id;

  /**
   * Foreign key to Person model class
   */
  @BindColumn(
      foreignKey = Person.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long person_id;

  /**
   * Foreign key to City model class
   */
  @BindColumn(
      foreignKey = City.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long city_id;
}
