package sqlite.kripton209.model2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

@BindDao(User.class)
public interface UserDao {
  @BindSqlSelect
  List<User> getAllUsers();

  @BindSqlSelect(where="id = ${id}")
  User getUserById(long id);


}
