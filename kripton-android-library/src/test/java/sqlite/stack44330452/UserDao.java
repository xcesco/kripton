package sqlite.stack44330452;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(User.class)
public interface UserDao {
	@BindSqlSelect
	public List<User> loadUser();
}
