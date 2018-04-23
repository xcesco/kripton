package sqlite.feature.relations.error4;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Album {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String name;

//	@BindRelation(foreignKey = "albumId")
	public List<Artist> songs;

}
