package sqlite.feature.relations.error4;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindRelation;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Artist {

	public long id;

	public String name;

	@BindRelation(foreignKey = "albumId")
	public List<Song> songs;
}
