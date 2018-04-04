package sqlite.kripton209.model2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(User.class)
public interface UserDao {

	@BindSqlInsert
	void insert(User user);

	@BindSqlSelect
	List<User> getAllUsers();

	@BindSqlSelect(where = "id = ${id}")
	User getUserById(long id);

}
