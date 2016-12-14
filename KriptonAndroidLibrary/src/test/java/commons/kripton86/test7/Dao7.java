package commons.kripton86.test7;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Bean7.class)
public interface Dao7 {

	@BindSqlSelect
	Bean7 selectAll();
		
}
