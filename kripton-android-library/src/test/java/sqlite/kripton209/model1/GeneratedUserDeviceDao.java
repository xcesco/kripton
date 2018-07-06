package sqlite.kripton209.model1;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import java.util.List;

@BindDao(UserDevice.class)
@BindGeneratedDao(
    dao = UserDeviceDao.class
)
@BindDaoMany2Many(
    entity1 = User.class,
    entity2 = Device.class
)
public interface GeneratedUserDeviceDao extends UserDeviceDao {
  @BindSqlSelect(
      where = "id=:id"
  )
  UserDevice selectById(@BindSqlParam("id") long id);

  @BindSqlSelect(
      where = "userId=:userId"
  )
  List<UserDevice> selectByUserId(@BindSqlParam("userId") Long userId);

  @BindSqlSelect(
      where = "deviceId=:deviceId"
  )
  List<UserDevice> selectByDeviceId(@BindSqlParam("deviceId") Long deviceId);

  @BindSqlDelete(
      where = "id=:id"
  )
  int deleteById(@BindSqlParam("id") long id);

  @BindSqlDelete(
      where = "userId=:userId"
  )
  int deleteByUserId(@BindSqlParam("userId") Long userId);

  @BindSqlDelete(
      where = "deviceId=:deviceId"
  )
  int deleteByDeviceId(@BindSqlParam("deviceId") Long deviceId);

  @BindSqlInsert
  int insert(@BindSqlParam("bean") UserDevice bean);
}
