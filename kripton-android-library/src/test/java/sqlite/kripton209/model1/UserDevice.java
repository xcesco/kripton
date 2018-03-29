package sqlite.kripton209.model1;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.orm.ForeignKeyAction;

/**
 * <p>
 * Generated entity implementation for <code>UserDevice</code>
 * </p>
 */
@BindTable(
    name = "user_device"
)
public class UserDevice {
  /**
   * Primary key
   */
  @BindColumn(
      columnType = ColumnType.PRIMARY_KEY
  )
  public long id;

  /**
   * Foreign key to User model class
   */
  @BindColumn(
      foreignKey = User.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long userId;

  /**
   * Foreign key to Device model class
   */
  @BindColumn(
      foreignKey = Device.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long deviceId;
}
