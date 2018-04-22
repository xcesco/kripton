package sqlite.feature.relations.error1;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindRelation;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Album {
	public long id;
	public String name;
	
	@BindColumn("a")
	@BindRelation(foreignKey="albumId")
	public List<Song> songs;
}
