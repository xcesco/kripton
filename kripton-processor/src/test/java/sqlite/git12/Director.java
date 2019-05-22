package sqlite.git12;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

import android.annotation.NonNull;

@BindSqlType(name = "director")
public class Director {
	@BindSqlColumn(columnType = ColumnType.PRIMARY_KEY, value = "mid")
	public long id;
	@NonNull
	@BindSqlColumn(value = "full_name", columnType = ColumnType.UNIQUE)
	public String fullName;
	@BindSqlColumn(enabled = false)
	public int age;
}