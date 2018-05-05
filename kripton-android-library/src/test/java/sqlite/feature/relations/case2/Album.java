package sqlite.feature.relations.case2;

import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Album {
	public long id;
	public String name;
	
	@BindSqlRelation(foreignKey="albumId")
	public Song songs;
}
