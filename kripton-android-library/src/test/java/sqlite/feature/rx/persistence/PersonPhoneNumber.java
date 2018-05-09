package sqlite.feature.rx.persistence;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import sqlite.feature.rx.model.Person;
import sqlite.feature.rx.model.PhoneNumber;

/**
 * <p>
 * Generated entity implementation for <code>PersonPhoneNumber</code>
 * </p>
 */
@BindSqlType(
    name = "person_phone_number"
)
public class PersonPhoneNumber {
  /**
   * Primary key
   */
  @BindSqlColumn(
      columnType = ColumnType.PRIMARY_KEY
  )
  public long id;

  /**
   * Foreign key to Person model class
   */
  @BindSqlColumn(
      parentEntity = Person.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long personId;

  /**
   * Foreign key to PhoneNumber model class
   */
  @BindSqlColumn(
      parentEntity = PhoneNumber.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long phoneNumberId;
}
