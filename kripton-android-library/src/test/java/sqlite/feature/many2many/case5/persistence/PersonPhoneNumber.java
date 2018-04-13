package sqlite.feature.many2many.case5.persistence;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import sqlite.feature.many2many.case5.model.Person;
import sqlite.feature.many2many.case5.model.PhoneNumber;

/**
 * <p>
 * Generated entity implementation for <code>PersonPhoneNumber</code>
 * </p>
 */
@BindTable(
    name = "person_phone_number"
)
public class PersonPhoneNumber {
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
      parentEntity = Person.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long personId;

  /**
   * Foreign key to PhoneNumber model class
   */
  @BindColumn(
      parentEntity = PhoneNumber.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long phoneNumberId;
}
