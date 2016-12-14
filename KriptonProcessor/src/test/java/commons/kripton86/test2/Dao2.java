package commons.kripton86.test2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Bean2.class)
public interface Dao2 {

	@BindSqlSelect
	Bean2 selectAll();
	
	Bean2 selectWrong();
}
