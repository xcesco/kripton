package commons.kripton86.test7;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean7 {

	@BindColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;
	
	@BindColumn(columnType = ColumnType.PRIMARY_KEY)
	public long ida;
	
	public String test;
	
	
}
