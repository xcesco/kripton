package sqlite.kripton209.model2;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;

/**
 * <p>
 * Generated entity implementation for <code>UserDevice</code>
 * </p>
 */
@BindSqlType(
    name = "user_2_device"
)
public class UserDevice {
  /**
   * Primary key
   */
  @BindSqlColumn(
      columnType = ColumnType.PRIMARY_KEY
  )
  private long id;

  /**
   * Foreign key to User model class
   */
  @BindSqlColumn(
      parentEntity = User.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  private Long userId;

  /**
   * Foreign key to Device model class
   */
  @BindSqlColumn(
      parentEntity = Device.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  private Long deviceId;

  public UserDevice(long id, Long userId, Long deviceId) {
    this.id=id;
    this.userId=userId;
    this.deviceId=deviceId;
  }

  public long getId() {
    return this.id;
  }

  public Long getUserId() {
    return this.userId;
  }

  public Long getDeviceId() {
    return this.deviceId;
  }
}
