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
  private long id;

  /**
   * Foreign key to City model class
   */
  @BindSqlColumn(
      parentEntity = City.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  private long cityId;

  /**
   * Foreign key to Person model class
   */
  @BindSqlColumn(
      parentEntity = Person.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  private long personId;

  public CityPerson(long id, long cityId, long personId) {
    this.id=id;
    this.cityId=cityId;
    this.personId=personId;
  }

  public long getId() {
    return this.id;
  }

  public long getCityId() {
    return this.cityId;
  }

  public long getPersonId() {
    return this.personId;
  }
}
