package sqlite.feature.relations.error1;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Album {
	public long id;
	public String name;
	
	@BindSqlColumn("a")
	@BindSqlRelation(foreignKey="albumId")
	public List<Song> songs;
}
