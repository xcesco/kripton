package sqlite.feature.childselect.error2;

import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class Album {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String name;

	@BindSqlRelation(foreignKey = "albumId")
	protected Song songs;

	public Song getSongs() {
		return songs;
	}

	public void setSongs(Song songs) {
		this.songs = songs;
	}
}
