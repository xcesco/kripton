package commons.kripton86.test8;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean9 {

	@BindColumn(ColumnType.PRIMARY_KEY)
	public long id;
	
	@BindColumn(ColumnType.PRIMARY_KEY)
	public long ida;
	
	public String test;
	
	
}
