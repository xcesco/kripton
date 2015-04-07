package kripton22;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.database.ColumnType;

@BindType
@BindTable(name="Table")
@BindAllFields
public class Bean01 {
	
	@BindColumn(value=ColumnType.PRIMARY_KEY)
	public long id;
	
	@BindColumn(name="hello")
	public String name;
	
	public double value;
}
