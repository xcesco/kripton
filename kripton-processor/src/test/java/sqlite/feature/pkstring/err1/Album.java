package sqlite.feature.pkstring.err1;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Album {
	
	@BindSqlColumn(columnType=ColumnType.PRIMARY_KEY)
	public Date name;
	

	
}
