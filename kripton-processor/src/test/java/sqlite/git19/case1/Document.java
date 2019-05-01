package sqlite.git19.case1;

import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Document {

	public long id;
	
	@BindSqlColumn(value="order")
	public String name;	
}
