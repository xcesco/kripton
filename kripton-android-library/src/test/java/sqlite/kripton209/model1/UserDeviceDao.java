package sqlite.kripton209.model1;

import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;

@BindDaoMany2Many(entity1 = User.class, entity2 = Device.class)
public interface UserDeviceDao {

}