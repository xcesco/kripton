package commons.kripton86.test8;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Bean8.class)
public interface Dao8 {

	@BindSqlSelect
	Bean9 selectAll();
		
}
