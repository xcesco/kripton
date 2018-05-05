package sqlite.feature.columnaffinity.kripton239;

import com.abubusoft.kripton.android.ColumnAffinityType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Person {

	public long id;
	
	@BindSqlColumn(columnAffinity=ColumnAffinityType.BLOB)
	public String name;
}
