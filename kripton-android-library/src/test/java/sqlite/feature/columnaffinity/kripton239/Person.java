package sqlite.feature.columnaffinity.kripton239;

import com.abubusoft.kripton.android.ColumnAffinityType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Person {

	public long id;
	
	@BindColumn(columnAffinity=ColumnAffinityType.BLOB)
	public String name;
}
