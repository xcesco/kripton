package sqlite.git20.immutable;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindIndex;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

import android.annotation.NonNull;

@BindSqlType(name = "director", indexes = { @BindIndex("title") })
public class Movie {
	@BindSqlColumn(columnType = ColumnType.PRIMARY_KEY, value = "mid")
	public long id;
	@NonNull
	@BindSqlColumn(value = "title")
	public String title;	
}