package android.kripton_22;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.database.ColumnType;

@BindType
@BindTable(name="Table")
@BindAllFields
public class Bean01 {
	
	public long id;
	
	@BindColumn(name="hello", value=ColumnType.PRIMARY_KEY, nullable=true)
	public String name;
	
	public double value;
}
