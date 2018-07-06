package sqlite.feature.pkstring.relations.err1;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Album {
	public long id;

	public String name;

	@BindSqlRelation(foreignKey = "albumId")
	protected List<Song> songs;

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
}
