package sqlite.feature.pkstring.relations.err2;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Song {

	@BindSqlColumn(columnType=ColumnType.PRIMARY_KEY)
	public String name;
	
	@BindSqlColumn(parentEntity=Album.class)
	public String albumId;
}
