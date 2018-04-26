package sqlite.feature.many2many.case7;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;

/**
 * <p>
 * Generated entity implementation for <code>CityPerson</code>
 * </p>
 */
@BindTable(
    name = "city_person"
)
public class CityPerson {
  /**
   * Primary key
   */
  @BindColumn(
      columnType = ColumnType.PRIMARY_KEY
  )
  public long id;

  /**
   * Foreign key to City model class
   */
  @BindColumn(
      parentEntity = City.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long cityId;

  /**
   * Foreign key to Person model class
   */
  @BindColumn(
      parentEntity = Person.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long personId;
}
