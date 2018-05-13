package sqlite.feature.columnaffinity.kripton239;

import com.abubusoft.kripton.android.ColumnAffinityType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Person {

	public long id;
	
	@BindSqlColumn(columnAffinity=ColumnAffinityType.BLOB)
	public String name;
}
