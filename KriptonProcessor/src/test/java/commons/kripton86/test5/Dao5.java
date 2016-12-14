package commons.kripton86.test5;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Bean5.class)
public interface Dao5 {

	@BindSqlSelect
	Bean5 selectAll();
		
}
