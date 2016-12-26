package commons.benchmark.persistence;

import java.util.ArrayList;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import commons.benchmark.model.User;

@BindDao(User.class)
public interface UserDao {

	@BindSqlInsert
	long insert(User bean);
	
	@BindSqlSelect
	ArrayList<User> selectAll();
	
	
	
}
