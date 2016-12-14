package commons.kripton86.test4;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Bean4.class)
public interface Dao4 {

	@BindSqlSelect
	Bean4 selectAll();
		
}
