package sqlite.feature.relations.case2;

import com.abubusoft.kripton.android.annotation.BindRelation;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Album {
	public long id;
	public String name;
	
	@BindRelation(foreignKey="albumId")
	public Song songs;
}
