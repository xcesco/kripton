package sqlite.feature.many2many.case7;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;

/**
 * <p>
 * Generated entity implementation for <code>CityPerson</code>
 * </p>
 */
@BindSqlType(
    name = "city_person"
)
public class CityPerson {
  /**
   * Primary key
   */
  @BindSqlColumn(
      columnType = ColumnType.PRIMARY_KEY
  )
  public long id;

  /**
   * Foreign key to City model class
   */
  @BindSqlColumn(
      parentEntity = City.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long cityId;

  /**
   * Foreign key to Person model class
   */
  @BindSqlColumn(
      parentEntity = Person.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long personId;
}
