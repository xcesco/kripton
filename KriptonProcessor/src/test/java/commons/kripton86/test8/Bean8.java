package commons.kripton86.test8;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean8 {

	@BindColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;
	
	public String test;
	
	
}
