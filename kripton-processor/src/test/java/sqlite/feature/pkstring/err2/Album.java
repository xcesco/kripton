package sqlite.feature.pkstring.err2;

import java.sql.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Album {
	
	@BindSqlColumn(columnType=ColumnType.PRIMARY_KEY)
	public String name;
	

	
}
