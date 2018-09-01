package sqlite.feature.immutable.many2many;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;

/**
 * <p>
 * Generated entity implementation for <code>PersonCity</code>
 * </p>
 */
@BindSqlType(
    name = "person_city"
)
public class PersonCity {
  /**
   * Primary key
   */
  @BindSqlColumn(
      columnType = ColumnType.PRIMARY_KEY
  )
  private long id;

  /**
   * Foreign key to Person model class
   */
  @BindSqlColumn(
      parentEntity = Person.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  private long personId;

  /**
   * Foreign key to City model class
   */
  @BindSqlColumn(
      parentEntity = City.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  private long cityId;

  public PersonCity(long id, long personId, long cityId) {
    this.id=id;
    this.personId=personId;
    this.cityId=cityId;
  }

  public long getId() {
    return this.id;
  }

  public long getPersonId() {
    return this.personId;
  }

  public long getCityId() {
    return this.cityId;
  }
}
