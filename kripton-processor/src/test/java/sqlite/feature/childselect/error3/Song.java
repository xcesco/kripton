package sqlite.feature.childselect.error3;

import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Song {
	public long id;
	public String name;
	
	@BindSqlColumn(parentEntity=Album.class)
	public long albumId;
}
