package sqlite.kripton209.model2;

import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;

@BindDaoMany2Many(entity1 = User.class, entity2 = Device.class, tableName="user_2_device")
public interface UserDeviceDao {

}