package sqlite.feature.many2many.case5.persistence;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import sqlite.feature.many2many.case5.model.Person;
import sqlite.feature.many2many.case5.model.PhoneNumber;

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
   * Foreign key to PhoneNumber model class
   */
  @BindSqlColumn(
      parentEntity = PhoneNumber.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  private long phoneNumberId;

  public PersonPhoneNumber(long id, long personId, long phoneNumberId) {
    this.id=id;
    this.personId=personId;
    this.phoneNumberId=phoneNumberId;
  }

  public long getId() {
    return this.id;
  }

  public long getPersonId() {
    return this.personId;
  }

  public long getPhoneNumberId() {
    return this.phoneNumberId;
  }
}
